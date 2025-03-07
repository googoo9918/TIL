## Controller

package com.inc.itms.web.controller.itsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.inc.itms.common.enums.Message;
import com.inc.itms.common.util.StrUtil;
import com.inc.itms.web.model.BodyResponse;
import com.inc.itms.web.model.dto.SessionVO;
import com.inc.itms.web.service.com.CommonService;
import com.inc.itms.web.service.file.UploadService;
import com.inc.itms.web.service.itsm.ItsmService;
import com.inc.itms.web.service.log.LogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("sessionVO")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/itsm")
public class ItsmController {

    private final LogService logService;
    private final ItsmService itsmService;
    private final CommonService commonService;
    private final UploadService uploadService;

    /**
     * ITSM > ITSM 요청현황 첫 화면
     * (기존) itsm_m01
     */
    @PostMapping(value = "/itsm_m01")
    public String itsm_m01(Locale lang,
                           @RequestParam Map<String, Object> paramMap,
                           ModelMap modelMap,
                           SessionVO sessionVO,
                           HttpServletRequest req) {
        // 예: default 작성일
        final String DEFAULT_REG_DATE = "2025-01-01";
        try {
            modelMap.put("REG_DATE_F", DEFAULT_REG_DATE);
            // 세션에 저장된 고객사 목록 등
            List<Map<String, String>> client_list = sessionVO.getClientList();
            modelMap.put("client_list", client_list);

            return "/itsm/itsm_m01"; // 요청현황 목록 JSP
        } catch (Exception e) {
            handleErrorLogging(req, paramMap, e);
            return "error/error";
        }
    }

    /**
     * ITSM 요청현황 목록 조회 (Ajax)
     */
    @PostMapping("/selectItsmList.json")
    @ResponseBody
    public BodyResponse selectIssueList(@RequestParam Map<String, Object> paramMap,
                                        SessionVO sessionVO,
                                        HttpServletRequest req) {
        try {
            // 필터링 시 '나의작성건' = Y => user_id = sessionVO.getUserId()
            paramMap.put("user_id", sessionVO.getUserId());
            List<Map<String, String>> rows = itsmService.selectItsmList(paramMap);

            return BodyResponse.success(rows);
        } catch (Exception e) {
            e.printStackTrace();
            handleErrorLogging(req, paramMap, e);
            return BodyResponse.failure(Message.FAIL.getMessage());
        }
    }

    /**
     * ITSM 요청 상세 (등록/수정) 화면 이동
     */
    @PostMapping(value = "/itsm_m01_s01")
    public String issue_m01_s01(Locale lang,
                                @RequestParam Map<String, Object> paramMap,
                                ModelMap modelMap,
                                SessionVO sessionVO,
                                HttpServletRequest req) {
        try {
            String rqNo = (String) paramMap.get("rq_no");
            String rqRegUser = "";

            //고객사
            List<Map<String, String>> client_list = sessionVO.getClientList();
            modelMap.put("client_list", client_list);

            // 1. 상세 조회(req_id 존재) -> m01_s02
            // 2. 상세 조회(내가 수정한 건) -> rq 생성 -> m01_s01
            if (rqNo != null && !rqNo.isEmpty()) {
                Map<String, String> rq = itsmService.selectRq(paramMap);

                String reg_user_nm = new StringBuilder()
                        .append(rq.get("rq_dept_nm"))
                        .append(" / ")
                        .append(rq.get("rq_user_nm"))
                        .append(" ")
                        .append(rq.get("rq_pos_nm"))
                        .toString();

                rq.put("reg_user_nm", reg_user_nm);

                if (rq == null) {
                    // 없으면 에러 처리
                    return "error/authError";
                }

                modelMap.put("rq", new Gson().toJson(rq));
                rqRegUser = StrUtil.nullToStr(rq.get("reg_user"));
            }

            // 2. 등록 or 내가 작성한 문건 수정 -> m01_s01
            if (rqNo == null || rqRegUser.equals(sessionVO.getUserId())) {
                //회사명, 부서명, 유저명, 직위명
                modelMap.put("corp_nm", sessionVO.getCorpNm());
                modelMap.put("dept_nm", sessionVO.getDeptNm());
                modelMap.put("user_nm", sessionVO.getUserNm());
                modelMap.put("pos_nm", sessionVO.getPosNm());

                //요청유형(서비스요청, 변경, 장애)
                List<Map<String, String>> rqTypeList = itsmService.selectRqTypeList();
                modelMap.put("rq_type", rqTypeList);

                //긴급도(긴급, 보통)
                List<Map<String, String>> rqUrgencyList = itsmService.selectRqUrgencyList();
                modelMap.put("rq_urgency", rqUrgencyList);

                //요청범주, 상세요청범주
                List<Map<String, Object>> rqCategoryList = itsmService.selectCategoryList();
                String rqCategoryJson = new Gson().toJson(rqCategoryList);
                modelMap.put("rq_category", rqCategoryJson);

                return "/itsm/itsm_m01_s01";
            }

            // 작성자가 아니면 다른 화면 or 권한 에러
            return "/itsm/itsm_m01_s02"; // 혹은 "error/authError" 등

        } catch (Exception e) {
            e.printStackTrace();
            handleErrorLogging(req, paramMap, e);
            return "error/error";
        }
    }


    @PostMapping(value = "/getSubCategories.json")
    @ResponseBody
    public BodyResponse selectSubCategoryList(@RequestBody Map<String, Object> requestBodyMap,
                                              SessionVO sessionVO,
                                              HttpServletRequest req) {
        try {
            String upperId = (String) requestBodyMap.get("upperId");
            System.out.println("upperId:" + upperId);
            if (upperId == null || upperId.isEmpty()) {
                return BodyResponse.failure(Message.REQUEST_BODY_MISSING_ERROR.getMessage());
            }

            List<Map<String, String>> subCategories = itsmService.selectSubCategoryList(upperId);
            return BodyResponse.success(subCategories);
        } catch (Exception e) {
            handleErrorLogging(req, requestBodyMap, e);
            return BodyResponse.failure(Message.FAIL.getMessage());
        }
    }

    /**
     * 요청 등록/수정 처리 예시 (Ajax)
     */
    @PostMapping("/insertOrUpdateRq.json")
    @ResponseBody
    public BodyResponse insertOrUpdateRq(MultipartHttpServletRequest mfReq,
                                         @RequestParam Map<String, Object> map,
                                         HttpServletRequest req,
                                         SessionVO sessionVO) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> paramObj = mapper.readValue(StrUtil.nullToStr(map.get("paramObj")), Map.class);

            paramObj.put("user_id", sessionVO.getUserId());
            paramObj.put("company_id", sessionVO.getCompanyId());

            System.out.println("paramObj:" + paramObj);

            String uploadId = "itsm_request";

            String rqNo = (String) paramObj.get("rq_no");
            boolean first = (rqNo == null || rqNo.isEmpty());
            System.out.println("rqNo:" + rqNo);
            System.out.println("first:" + first);

            if (first) {
                rqNo = commonService.getSeqNo("CSD");
                paramObj.put("rq_no", rqNo);
            }
            if (mfReq.getFiles("files_" + uploadId).size() > 0) {
                Map<String, Object> swUploadParam = new HashMap<>();
                swUploadParam.put("upload_id", uploadId);
                swUploadParam.put("ref_pk1_" + uploadId, rqNo);
                swUploadParam.put("ref_tbl_" + uploadId, "itsm_request");

                Map<String, Object> swUploadResultMap = uploadService.commonFileUpload(mfReq, swUploadParam);

                if (swUploadResultMap == null)
                    return BodyResponse.builder().data(swUploadResultMap).successYN("N").message(Message.FILE_NOT_EXIST.getMessage()).build();
                else if (!"Y".equals(swUploadResultMap.get("successYN")))
                    return BodyResponse.builder().data(swUploadResultMap).successYN("N").message(StrUtil.nullToStr(swUploadResultMap.get("message"))).build();
            }

            // DB 처리
            if (first) itsmService.insertRq(paramObj);
            else itsmService.updateRq(paramObj);

            return BodyResponse.success(paramObj);

        } catch (Exception e) {
            handleErrorLogging(req, null, e);
            return BodyResponse.failure(Message.FAIL.getMessage());
        }
    }

    /**
     * 요청 삭제 (Ajax)
     */
    @PostMapping("/deleteRq.json")
    @ResponseBody
    public BodyResponse deleteRq(@RequestBody Map<String, Object> map,
                                 HttpServletRequest req,
                                 SessionVO sessionVO) {
        try {
            itsmService.deleteRq(map, sessionVO);

            return BodyResponse.success();
        } catch (Exception e) {
            handleErrorLogging(req, null, e);
            return BodyResponse.failure(Message.FAIL.getMessage());
        }
    }

    // 에러 로깅 처리
    private void handleErrorLogging(HttpServletRequest req, Map<String, Object> paramMap, Exception e) {
        try {
            Map<String, String> errorLog = logService.makeErrorLog(this.getClass().getName(), req, paramMap, e);
            logService.insertErrorLog(errorLog);
        } catch (Exception loggingException) {
            log.error("error: " + loggingException.getMessage());
        }
    }
}



## Mapper

package com.inc.itms.web.mapper.itsm;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ItsmMapper {

    // ITSM 요청현황 목록
    List<Map<String, String>> selectItsmList(Map<String, Object> param);

    // 요청유형 목록
    List<Map<String, String>> selectRqTypeList();

    // 긴급도 목록
    List<Map<String, String>> selectRqUrgencyList();

    // 카테고리 목록
    List<Map<String, Object>> selectCategoryList();

    Map<String, String> selectRq(Map<String, Object> paramMap);

    // 등록
    int insertRq(Map<String, Object> paramMap);

    // 수정
    int updateRq(Map<String, Object> paramMap);

    // 삭제
    int deleteRq(String rq_no);

    List<Map<String, String>> selectSubCategoryList(String upperId);

    Map<String, Object> findByRqNo(String rqNo);
}


## Service

package com.inc.itms.web.service.itsm;

import com.inc.itms.common.enums.Message;
import com.inc.itms.common.exception.MessageException;
import com.inc.itms.web.mapper.file.UploadMapper;
import com.inc.itms.web.mapper.itsm.ItsmMapper;
import com.inc.itms.web.model.dto.SessionVO;
import com.inc.itms.web.service.file.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItsmService {
    private final ItsmMapper itsmMapper;
    private final UploadMapper uploadMapper;
    private final UploadService uploadService;


    //ITSM 요청현황 목록
    public List<Map<String, String>> selectItsmList(Map<String, Object> paramMap) {
        return itsmMapper.selectItsmList(paramMap);
    }

    //요청유형 목록
    public List<Map<String, String>> selectRqTypeList() {
        return itsmMapper.selectRqTypeList();
    }

    //긴급도 목록
    public List<Map<String, String>> selectRqUrgencyList() {
        return itsmMapper.selectRqUrgencyList();
    }

    //요청범주, 상세요청범주
    public List<Map<String, Object>> selectCategoryList() {
        return itsmMapper.selectCategoryList();
    }


    public Map<String, String> selectRq(Map<String, Object> paramMap) {
        return itsmMapper.selectRq(paramMap);
    }

    public List<Map<String, String>> selectSubCategoryList(String upperId) {
        return itsmMapper.selectSubCategoryList(upperId);
    }

    public void insertRq(Map<String, Object> paramObj) {
        itsmMapper.insertRq(paramObj);
    }

    public void updateRq(Map<String, Object> paramObj) throws Exception {
        findVerifiedRqByRqNo(paramObj.get("rq_no").toString());
        itsmMapper.updateRq(paramObj);
    }


    /*
     * 삭제
     */
    public void deleteRq(Map<String, Object> param, SessionVO sessionVo) throws Exception {
        String rqNo = param.get("rq_no").toString();
        findVerifiedRqByRqNo(rqNo);

        itsmMapper.deleteRq(rqNo);

        Map<String, Object> fileSearchMap = new HashMap();
        fileSearchMap.put("company_id", sessionVo.getCompanyId());
        fileSearchMap.put("ref_pk1", param.get("rq_no"));

        List<Map<String, Object>> selectAttFileList = uploadMapper.selectAttFileList(fileSearchMap);
        for (Map<String, Object> file : selectAttFileList) {
            Map<String, Object> fileMap = new HashMap<>();
            fileMap.put("company_id", sessionVo.getCompanyId());
            fileMap.put("file_id", file.get("file_id"));


            Map<String, String> uploadResult = uploadService.deleteAttFile(fileMap);
            if (!"Y".equals(uploadResult.get("successYN"))) {
                throw new MessageException(uploadResult.get("message"));
            }
        }
    }

    private Map<String, Object> findVerifiedRqByRqNo(String rq_no) throws Exception {
        return Optional.ofNullable(itsmMapper.findByRqNo(rq_no))
                .orElseThrow(() -> new Exception(Message.FAIL.getMessage()));
    }


}

## mapper.xml

<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.inc.itms.web.mapper.itsm.ItsmMapper">

    <!-- ITSM 요청현황 목록 -->
    <select id="selectItsmList" parameterType="map" resultType="map">
        SELECT sc1.code_nm AS client_nm
        , ir.req_no
        , sc2.code_nm AS req_stat
        , ir.title
        , sc3.code_nm AS req_type
        , DATE_FORMAT(ir.reg_date, '%Y-%m-%d %H:%i:%s') as reg_date
        , iu.user_nm as reg_user
        FROM itsm_request ir
        LEFT JOIN sys_code sc1 ON ir.client_id = sc1.code_id AND sc1.grp_id = 'G0000'
        LEFT JOIN sys_code sc2 ON ir.req_stat = sc2.code_id AND sc2.grp_id = 'G0008'
        LEFT JOIN sys_code sc3 ON ir.req_type = sc3.code_id AND sc3.grp_id = 'G0021'
        LEFT JOIN inc_user iu ON ir.req_id = iu.user_id
        WHERE 1=1
        <if test="client_id != null and client_id != ''">
            AND ir.client_id = #{client_id}
        </if>
        <if test="reg_user != null and reg_user != ''">
            AND iu.user_nm = #{reg_user}
        </if>
        <if test='isMine != null and isMine == "Y"'>
            AND ir.req_id = #{user_id}
        </if>
        <if test="keyword != null and keyword != ''">
            AND ir.title LIKE CONCAT('%', #{keyword}, '%')
        </if>
        <if test="reg_date_f != null and reg_date_f != ''">
            AND DATE_FORMAT(ir.reg_date, '%Y%m%d') BETWEEN #{reg_date_f} AND #{reg_date_e}
        </if>
        ORDER BY ir.reg_date DESC
    </select>

    <!-- 요청자(로그인 사용자) 정보  -->
    <!--    <select id="selectReqUser" parameterType="map" resultType="map">-->
    <!--        SELECT DISTINCT-->
    <!--            iu.dept_nm,-->
    <!--            iu.user_nm,-->
    <!--            iu.pos_nm,-->
    <!--            c.company_name-->
    <!--        FROM inc_user iu-->
    <!--            LEFT JOIN itsm_request ir on ir.req_id = iu.user_id-->
    <!--            LEFT JOIN company c on ir.company_id = c.company_id-->
    <!--        WHERE iu.user_id = #{userId}-->
    <!--    </select>-->

    <!-- 요청유형 목록 -->
    <select id="selectRqTypeList" resultType="map">
        SELECT code_id as code,
               code_nm as name
        FROM sys_code
        WHERE grp_id = 'G0021'
          AND use_yn = 'Y'
        ORDER BY sort_order
    </select>

    <!-- 긴급도 목록-->
    <select id="selectRqUrgencyList" resultType="map">
        SELECT code_id as code,
               code_nm as name
        FROM sys_code
        WHERE grp_id = 'G0022'
          AND use_yn = 'Y'
        ORDER BY sort_order DESC
    </select>

    <!-- 요청 범주, 상세 요청 범주-->
    <select id="selectCategoryList" resultType="map">
        /* 요청범주 */
        SELECT sort_order,
               code_id,
               code_nm,
               ref_pk1,
               '1' AS level
        FROM sys_code
        WHERE grp_id = 'G0020'
          AND use_yn = 'Y'
        UNION ALL
        /* 상세요청범주 */
        SELECT sort_order,
               code_id,
               code_nm,
               ref_pk1,
               '2' AS level
        FROM sys_code
        WHERE grp_id = 'G0023'
          AND use_yn = 'Y'
        ORDER BY level, sort_order
    </select>

    <!-- 요청 단건 조회 -->
    <select id="selectRq" parameterType="map" resultType="map">
        SELECT ir.reg_user
             , ir.client_id
             , ir.req_no
             , ir.req_type                        AS rq_type
             , sc1.code_nm                        AS rq_type_nm
             , ir.urgency                         AS rq_urgency
             , sc2.code_nm                        AS rq_urgency_nm
             , DATE_FORMAT(ir.due_date, '%Y%m%d') AS req_date
             , ir.title                           AS rq_title
             , ir.content                         AS rq_contents
             , ir.req_cat                         AS rq_category
             , sc3.code_nm                        AS rq_category_nm
             , ir.detail_req_cat                  AS rq_sub_category
             , sc4.code_nm                        AS rq_sub_category_nm
             , c.company_name                     AS rq_corp_nm
             , iu.dept_nm                         AS rq_dept_nm
             , iu.user_nm                         AS rq_user_nm
             , iu.pos_nm                          AS rq_pos_nm
        FROM itsm_request ir
                 LEFT JOIN sys_code sc1 on ir.req_type = sc1.code_id
                 LEFT JOIN sys_code sc2 on ir.urgency = sc2.code_id
                 LEFT JOIN sys_code sc3 on ir.req_cat = sc3.code_id
                 LEFT JOIN sys_code sc4 on ir.detail_req_cat = sc4.code_id
                 LEFT JOIN company c on ir.company_id = c.company_id
                 LEFT JOIN inc_user iu on ir.req_id = iu.user_id
        WHERE ir.req_no = #{rq_no}
    </select>

    <!-- 상세요청범주 목록-->
    <select id="selectSubCategoryList" parameterType="string" resultType="map">
        SELECT code_id,
               code_nm
        FROM sys_code
        WHERE grp_id = 'G0023'
          AND ref_pk1 = #{upperId}
          AND use_yn = 'Y'
        ORDER BY sort_order
    </select>

    <!-- 8) 등록 -->
    <insert id="insertRq" parameterType="map">
        INSERT INTO itsm_request
        (req_no,
         company_id,
         client_id,
         req_id,
         req_type,
         urgency,
         due_date,
         req_cat,
         detail_req_cat,
         title,
         content,
         req_stat,
         reg_user,
         reg_date)
        VALUES (#{rq_no},
                #{company_id},
                #{client_id},
                #{user_id},
                #{rq_type},
                #{rq_urgency},
                #{req_date},
                #{rq_category},
                #{rq_sub_category},
                #{rq_title},
                #{rq_contents},
                'G000810',
                #{user_id},
                NOW())
    </insert>

    <!-- 9) 수정 -->
    <update id="updateRq" parameterType="map">
        UPDATE itsm_request
        SET req_type       = #{rq_type},
            urgency        = #{rq_urgency},
            due_date       = #{req_date},
            req_cat        = #{rq_category},
            detail_req_cat = #{rq_sub_category},
            title          = #{rq_title},
            content        = #{rq_contents}
        WHERE req_no = #{rq_no}
    </update>


    <!-- 10) 삭제 -->
    <delete id="deleteRq" parameterType="string">
        DELETE
        FROM itsm_request
        WHERE req_no = #{rq_no}
    </delete>


    <select id="findByRqNo" parameterType="string" resultType="map">
        SELECT *
        FROM itsm_request
        WHERE req_no = #{rq_no}
    </select>
</mapper>

## views
### m01
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /***********************************************************
   * 설명   : ITSM > ITSM 요청현황
   * 작성자 : 정동규
   * 작성일  : 2025.02.12
   ***********************************************************/
%>
<script>
    $(document).ready(function () {
        //이 객체는 itsm 요청 현황 페이지의 모든 기능 포함(//Todo: 이름 변경 요망(issue로 남겨야 하는 변수도 있음, 주의 요망))
        itsm_m01 = {
            name: 'itsm_m01',

            init: function() {
                // 이슈발생일자 세팅
                datePickerInit(this.name + " #reg_date_f");
                datePickerInit(this.name + " #reg_date_e");

                var now = new Date();
                //default 작성일이 있는 경우
                if("<c:out value="${REG_DATE_F}"/>" != ""){
                    $( '#' + this.name + ' #reg_date_f' ).datepicker( "setDate", "<c:out value="${REG_DATE_F}"/>"); //메인화면에서 카운트클릭시
                }else{
                   //없는 경우, 이번달 1일로 설정한다.
                    $( '#' + this.name + ' #reg_date_f' ).datepicker( "setDate", new Date(now.getFullYear(), now.getMonth(), 1) );
                }
                // 이번달 말일
                $( '#' + this.name + ' #reg_date_e' ).datepicker( "setDate", new Date(now.getFullYear(), now.getMonth() + 1, 0) );

                // 이벤트 등록
                this.regist_event();

                // 이전 검색 폼 데이터 있다면 세팅
                this.getSearchParameter();


                // 그리드 생성
                var $grid = $('#' + this.name + '_grid');

                var colModel = [
                    { coltitle: '고객사',    		name: 'client_nm',      width: 100, align: 'center',   sortable: false, editable: false, classes: 'ui-ellipsis'},
                    { coltitle: '요청번호',    		name: 'req_no',  	    width: 100, align: 'center',   sortable: false, editable: false, classes: 'ui-click'},
                    { coltitle: '요청상태', 		name: 'req_stat',       width: 80, align: 'center',     sortable: false, editable: false},
                    { coltitle: '요청제목',    	    name: 'title',		    width: 200, align: 'center',     sortable: false, editable: false, classes: 'ui-ellipsis'},
                    { coltitle: '요청유형',			name: 'req_type', 	    width: 80, align: 'center',   sortable: false, editable: false},
                    { coltitle: '작성일자',    		name: 'reg_date',       width: 80, align: 'center',   sortable: false, editable: false},
                    { coltitle: '작성자',    		name: 'reg_user',	    width: 70, align: 'center',   sortable: false, editable: false},

                    { coltitle: '',    			name: 'read_yn',	hidden: true},
                ];
                itsm_m01.colModel = colModel;

                // 화면 grid HEADER명
                var colNames = new Array();
                for (i in colModel) {
                    colNames.push(colModel[i].coltitle);
                }


                $grid.jqGrid({
                    url: '/itsm/selectItsmList.json',
                    datatype: "json",
                    mtype: "post",
                    postData: itsm_m01.getSearchParam(),
                    colNames: colNames,
                    colModel: colModel,

                    // --------- paging option start ----------- //
                    pgbuttons: false,
                    viewrecords: false,
                    pgtext : "",
                    pginput : false,
                    // --------- paging option end ----------- //

                    height: '450',        // 그리드의 높이를 지정한다.
                    loadui: 'disable',

                    autoWidth: true,
                    shrinkToFit: true,   // Grid 전체 넓이에 맞춰서 자동으로 컬럼크기를 설정함 (default : true)
                    cellEdit: false,
                    rownumbers: false,
                    rowNum: -1,           // 전체건 표기

                    multiselect: true,

                    beforeSelectRow: function (rowid, e) {
                        return false;
                    },

                    // 그리드 건수 출력
                    gridComplete: function() {
                        $('#itsm_m01 #total_td').html("전체 <strong>"+$('#itsm_m01_grid').getGridParam('records')+"</strong>건");
                    },
                    // 데이터 이후 실행 콜백 함수
                    loadComplete: function(data){
                        $("#itsm_m01 #list_nodata").remove();
                        hideLoading(true);

                        // 로드 중 오류 발생 시
                        if(data.rows == null){//오류
                            $("#itsm_m01 #itsm_m01_grid.ui-jqgrid-btable").after("<p id='list_nodata' style='margin-top:20px;text-align:center;font-size:16px;color:red;'>"+data.response.message+"</p>");
                        } else if(data.total != "-0" && data.rows.length == 0){//정상,데이터없음
                            $("#itsm_m01 #itsm_m01_grid.ui-jqgrid-btable").after("<p id='list_nodata' style='margin-top:20px;text-align:center;font-size:16px'>조회된 데이터가 없습니다.</p>");
                            commonGridResize("itsm_m01_grid", "itsm_m01_grid_area"); /* jqGrid 넓이 보정*/
                        }else if(data.total == null){//정상,데이터있음
                            // 미확인건 선택처리
                            $grid.jqGrid('hideCol', 'cb'); //체크박스컬럼 삭제
                            var totalRow = $grid.getRowData(); //모든 데이터 가져옴
                            for(var i = 0; i < totalRow.length; i++){
                                if(totalRow[i].read_yn == "N"){
                                    $grid.setSelection(i+1);
                                }
                            }

                            commonGridResize("itsm_m01_grid", "itsm_m01_grid_area"); /* jqGrid 넓이 보정*/
                        }
                    },
                    // 특정 셀 선택시 함수 호출
                    onCellSelect: function(rowid, iCol, cellcontent, e) {
                        if(iCol == 2) {
                            setSearchParameter('itsm_m01_form')
                            // 이슈번호(요청번호)가 같이 넘어가게 된다.
                            main.goScreenSubmit('/itsm/itsm_m01_s01', 'prev_url=/itsm/itsm_m01&rq_no=' + cellcontent);
                        }
                    },
                });

                commonGridResize("itsm_m01_grid", "itsm_m01_grid_area"); /* jqGrid 넓이 보정*/

                //브라우져 Resize Event
                window.addEventListener("resize", () => commonGridResize("itsm_m01_grid", "itsm_m01_grid_area"));
            },

            getSearchParam: function() {
                // form 데이터 직렬화(JSON 객체 형태로 변환)
                // client_id, reg_user, keyword, reg_date_f, reg_date_e
                var searchParam = $('#itsm_m01_form').serializeForm(function(date){
                    return $.datepicker.formatDate('yymmdd', date);
                })

                // isMine 비어있거나 null이면 빈 문자열 기본값 할당
                if(isNull(searchParam.isMine)) searchParam.isMine = "";

                return searchParam;
            },

            getSearchParameter: function(){
                // 각각의 파라미터를 나눠 배열로 만듬
                var s_arr = (decodeURIComponent($('#SEARCH_PARAMETER').val().replace(/\+/g,' '))).split("&");//한글처리
                var ss_arr = null;
                for(var i=0;i<s_arr.length;i++){
                    // 키, 값 분류
                    ss_arr = s_arr[i].split("=");
                    if(ss_arr.length == 2){
                        // 나의작성 건 확인
                        if(ss_arr[0] == 'isMine' && ss_arr[1] == 'Y') $('#'+ss_arr[0]).prop('checked', true);
                        else if (ss_arr[0] == 'type_id_1') {
                            $('#'+ss_arr[0]).val(ss_arr[1]);
                            // // type_id_1에 따른 type_id_2 옵션 동적 생성
                            // var level_2_list = itsm_m01.sys_type_list.filter((e) => e.type_level == '2' && e.upper_type_id == ss_arr[1]);
                            //
                            // $("#itsm_m01 #type_id_2").empty();
                            // $("#itsm_m01 #type_id_2").append('<option value="">-선택-</option>');
                            // $.each(level_2_list, function (index, item) {
                            //     $("#itsm_m01 #type_id_2").append("<option value=" + item.type_id + ">" + item.type_nm + "</option>");
                            // });
                        }
                        // 나머지 경우에는 단순 값 설정
                        else $('#'+ss_arr[0]).val(ss_arr[1]);
                    }
                }

            },

            regist_event: function() {
                // type_id_1 변경 이벤트(사용자가 드롭다운 메뉴 변경 시 작동)
                // type1 변경 시, type1에 따라 동적으로 type2 업데이트
                // $("#itsm_m01 #type_id_1").change(function(){
                //     var level_2_list = itsm_m01.sys_type_list.filter((e) => e.type_level == '2' && e.upper_type_id == $(this).val());
                //
                //     $("#itsm_m01 #type_id_2").empty();
                //     $("#itsm_m01 #type_id_2").append('<option value="">-선택-</option>');
                //     $.each(level_2_list, function (index, item) {
                //         $("#itsm_m01 #type_id_2").append("<option value=" + item.type_id + ">" + item.type_nm + "</option>");
                //     });
                // })

                // 검색 버튼 클릭 이벤트
                $("#itsm_m01 #btn_search").click(function(){
                    showLoading();
                    // 검색 결과 반영(grid 새로 로드)
                    setTimeout(function() {
                        var $grid = $('#itsm_m01_grid');

                        $grid.jqGrid('clearGridData');
                        $grid.jqGrid('setGridParam', {postData: itsm_m01.getSearchParam() });
                        $grid.trigger('reloadGrid');
                    }, 200);
                })

                // 신규 등록 버튼 클릭 이벤트
                $("#itsm_m01 #btn_add").click(function(){
                    // 현재 검색 조건 저장
                    setSearchParameter('itsm_m01_form')

                    // m01_s01로 이동
                    main.goScreenSubmit('/itsm/itsm_m01_s01', 'prev_url=/itsm/itsm_m01');
                })

                // 엑셀 다운로드 버튼 클릭 이벤트
                $("#itsm_m01 #btn_download").click(function(){
                    var searchParam = itsm_m01.getSearchParam();
                    var param = new Object();

                    // json으로 변환
                    param.excelId = 'itsmExcel';
                    param.excelNm = 'ITSM 요청현황';
                    param.searchParam = JSON.stringify(searchParam);
                    param.excelUseCssYn = 'Y';
                    param.excelType = 'xlsx';

                    // 엑셀 다운로드
                    excel.downloadJqGrid(param, itsm_m01.colModel);
                })

                // 검색 필터 변경 시 자동 검색 실행
                $('#itsm_m01_form input[type="text"]:not(.hasDatepicker)').keyup(function(e){
                    // 엔터 입력 시 자동 검색
                    if(e.key == 'Enter') $("#itsm_m01 #btn_search").click();
                })

                // 고객사, 요청 유형 변경 시 자동 검색 실행
                $('#itsm_m01_form #client_id, #itsm_m01_form #type_id_1, #itsm_m01_form #type_id_2').change(function(e){
                    $("#itsm_m01 #btn_search").click();
                })

                // 작성자 필터 체크박스
                $('#itsm_m01_form #isMine').change(function(e){
                    // form 안에 ID reg_user 선택 -> 빈 문자열로 초기화
                    $('#itsm_m01_form #reg_user').val('')
                    // 입력 필드를 readonly 활성/비활성 (체크 확인)
                    $('#itsm_m01_form #reg_user').prop('readonly', $('#itsm_m01_form #isMine').prop('checked'))

                    $("#itsm_m01 #btn_search").click();
                })

                //작성일자 입력값 검증
                $('#itsm_m01_form #reg_date_f, #itsm_m01_form #reg_date_e').off('focusout').on('change', function(e){
                    var one = $(this), another = (this == $('#itsm_m01_form #reg_date_f')[0]) ? $('#itsm_m01_form #reg_date_e') : $('#itsm_m01_form #reg_date_f');
                    var dt_val = formatDate( one.datepicker("getDate") ), val = one.val();

                    if(val == '' || val != dt_val) {
                        gfnAlertMsg2('날짜형식이 정확하지 않습니다.', function(){
                            one.datepicker("setDate", another.val())
                        });
                        return;
                    }

                    // 시작 날짜가 종료 날짜보다 크면, 종료 날짜를 시작 날짜와 동일하게 설정
                    if( $('#itsm_m01_form #reg_date_f').val() > $('#itsm_m01_form #reg_date_e').val() ) {
                        $('#itsm_m01_form #reg_date_e').datepicker("setDate",  $('#itsm_m01_form #reg_date_f').val())
                    }

                    $("#itsm_m01 #btn_search").click();
                })
            },
        }

        itsm_m01.init();
    });

</script>


<!-- Content -->
<div id="itsm_m01">
    <div class="subtitle2" id="commonMenuPath"></div>

    <!-- search -->
    <form id="itsm_m01_form" name="itsm_m01_form">
        <div class="tableWrap">
            <table class="tableB">
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
              <tbody>
                <tr>
                    <th>고객사</th>
                    <td>
                        <select id="client_id" name="client_id" style="width: 70%;">
                            <option value="">-전체-</option>
                            <c:forEach items="${client_list}" var="ROW" varStatus="status">
                                <option value="<c:out value="${ROW.client_id}" />">
                                    <c:out value="${ROW.client_name}" />
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>작성자</th>
                    <td>
                        <input type="text" id="reg_user" name="reg_user" class="wi150p" style="width: 70%">
                        <input type="checkbox" id="isMine" name="isMine" value="Y">
                        <label for="isMine">나의작성건</label>
                    </td>
                </tr>
                <tr>
                    <th>요청제목</th>
                    <td>
                        <input type="text" id="keyword" name="keyword" style="width: 70%" placeholder="제목">
                    </td>
                    <th>작성일자</th>
                    <td>
                        <input type="text" id="reg_date_f" name="reg_date_f" class="wi120p" maxlength="10" required style="width: 35%;">
                        &nbsp;~&nbsp;
                        <input type="text" id="reg_date_e" name="reg_date_e" class="wi120p" maxlength="10" required style="width: 35%;">
                    </td>
                </tr>
              </tbody>
            </table>
        </div>
    </form>

    <div class="tableProArea">
        <div class="tablePro_total" id="total_td">전체 <strong>0</strong>건</div>
        <div class="tablePro_right">
            <p>
                <a style="cursor:pointer;" id="btn_search" class="bluebtn"><i class="xi-search"></i> 검색</a>
                <a style="cursor:pointer;" id="btn_add" class="bluebtn"><i class="xi-plus-square-o"></i> 등록</a>
                <a style="cursor:pointer;" id="btn_download" class="bluebtn"><img src="/resources/images/icon_excel.png" alt="엑셀다운로드"> 다운로드</a>

            </p>
        </div>
    </div>

    <!-- grid -->
    <div class="tableWrap" id="itsm_m01_grid_area">
        <table id="itsm_m01_grid"></table>
    </div>
    <!-- grid -->
</div>

### m01_s01
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    /***********************************************************
     * 설명   : ITSM > ITSM 요청현황 > ITSM 요청상세(등록/수정)
     * 작성자 : [작성자명] 정동규
     * 작성일 : [작성일자] 2025.02.18
     ***********************************************************/
%>
<style type="text/css">
    span {
        word-break: break-word;
        white-space: pre-wrap;
    }
</style>

<script>
    var itsm_m01_s01 = {
            // 화면 파라미터
            prev_url: '${param.prev_url}',   // 이전 화면으로 돌아가기 위한 URL 파라미터
            rq_no: '${param.rq_no}',         // 요청번호 (빈값이면 새 등록, 값 있으면 상세 조회(수정 및 삭제 가능)
            rq: {},                  // 요청 상세 정보
            // 카테고리 목록 (JSON 문자열)
            categoryList: '${rq_category}',

            init: function () {
                // 화면 로딩 시점에 필요한 함수들을 순차적으로 호출
                itsm_m01_s01.parse_data();       // 카테고리 JSON 파싱
                itsm_m01_s01.set_form();         // 폼 초기 세팅
                itsm_m01_s01.regist_event();     // 각종 이벤트 등록
            },

            parse_data: function () {
                // categoryList를 실제 JS 배열로 변환
                // 컨트롤러에서 DB조회 후 JSON으로 전달받은 것을 화면에서 파싱
                try {
                    itsm_m01_s01.categoryList = JSON.parse(itsm_m01_s01.categoryList);
                } catch (e) {
                    // 파싱 실패 시 빈 배열로 세팅
                    itsm_m01_s01.categoryList = [];
                }
            },

            // 폼 세팅
            set_form: function () {
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\r/gi,"\\r");
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\n/gi,"\\n");
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\t/gi,"\\t");

                try {
                    itsm_m01_s01.rq = JSON.parse(JSON.stringify(${rq}));
                } catch (e) {
                    // 실패 시 빈 객체
                    itsm_m01_s01.rq = {};
                }

                // 요청번호 표시
                if (itsm_m01_s01.rq_no != '') {
                    $('#itsm_m01_s01_form #rq_no').text(itsm_m01_s01.rq_no);
                }

                var now = new Date();
                datePickerInit("itsm_m01_s01_form #req_date");
                $("#itsm_m01_s01_form #req_date").datepicker("setDate", new Date(now.getFullYear(), now.getMonth(), now.getDate() + 5));

                // 폼에 값 채우기 (populate)
                // name과 JSON key가 일치하면 자동 매핑
                $('#itsm_m01_s01_form').populate(itsm_m01_s01.rq, {
                    reset: false,
                    hasDatepicker: true
                });
                // 카테고리(level=1) 옵션 세팅
                itsm_m01_s01.set_category_level1();

                // 등록 전/후 상태 구분
                // rq 객체가 비었다면 => 새 등록
                // rq 객체가 있으면 => 수정 모드
                if (Object.keys(itsm_m01_s01.rq).length == 0) {
                    // 새 등록
                    $('#itsm_m01_s01 #btn_delete').hide();  // 새 등록 시 삭제 버튼 숨김

                    // (2) 요청유형, 긴급도 라디오 기본 세팅 (서비스요청 / 보통)
                    $('#rq_type_G002101').prop('checked', true);  // 서비스요청
                    $('#rq_urgency_G002202').prop('checked', true);  // 보통

                } else {
                    // 이미 등록된 건(수정)
                    $('#itsm_m01_s01 #btn_save').html('<i class="xi-save"></i> 수정');  // 버튼 문구 변경
                    $('#itsm_m01_s01 #btn_delete').show();                            // 삭제 버튼 표시

                    $('#client_id').val(itsm_m01_s01.rq.client_id).prop('disabled', true);

                    // 라디오 버튼(요청유형, 긴급도) 체크
                    if (!isNull(itsm_m01_s01.rq.rq_type)) {
                        $('#rq_type_' + itsm_m01_s01.rq.rq_type).prop('checked', true);
                    }
                    if (!isNull(itsm_m01_s01.rq.rq_urgency)) {
                        $('#rq_urgency_' + itsm_m01_s01.rq.rq_urgency).prop('checked', true);
                    }

                    // 수정 모드에서 카테고리 값 설정
                    if (!isNull(itsm_m01_s01.rq.rq_category)) {
                        //요청범주 기본값 가져오기
                        $('#rq_category').val(itsm_m01_s01.rq.rq_category);

                        itsm_m01_s01.set_category_level2(itsm_m01_s01.rq.rq_category);

                        //상세요청범주 기본값 가져오기
                        if (!isNull(itsm_m01_s01.rq.rq_sub_category)) {
                            $('#rq_sub_category').val(itsm_m01_s01.rq.rq_sub_category);
                        }
                    }
                }

                // 로딩 표시 해제
                hideLoading(true);
            },

            // 카테고리(level=1) 옵션 세팅
            set_category_level1: function () {
                // categoryList에서 level이 '1'인 것만 필터링
                var list = itsm_m01_s01.categoryList.filter(function (item) {
                    return parseInt(item.level, 10) === 1;
                });
                var $cat = $('#rq_category');
                // var $sub = $('#rq_sub_category');

                // -선택-
                $cat.empty();
                $cat.append('<option value="">-선택-</option>');

                // AJAX로 수정하며 주석 처리
                // $sub.empty();
                // $sub.append('<option value="">-선택-</option>');

                // level=1 목록 반복문
                list.forEach(function (item) {
                    $cat.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                });
            }
            ,

            //상세요청범주 옵션
            set_category_level2: function (upperId) {
                // categoryList에서 level=2 이고, ref_pk1==upperId 인 것만 필터링
                var list = itsm_m01_s01.categoryList.filter(function (item) {
                    return item.level == '2' && item.ref_pk1 == upperId;
                });
                var $sub = $('#rq_sub_category');

                // level=2 목록 반복문
                list.forEach(function (item) {
                    $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                });
            }
            ,

            // 상세요청범주(level=2) 옵션(With AJAX)
            set_category_level2_ajax: function (upperId) {
                // categoryList에서 level=2 이고, ref_pk1==upperId 인 것만 필터링
                // var list = itsm_m01_s01.categoryList.filter(function (item) {
                //     return item.level == '2' && item.ref_pk1 == upperId;
                // });
                // var $sub = $('#rq_sub_category');
                //
                // // level=2 목록 반복문
                // list.forEach(function (item) {
                //     $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                // });
                var $sub = $('#rq_sub_category');
                $sub.empty();
                $sub.append('<option value="">-선택-</option>');

                // AJAX 호출로 하위 카테고리 가져오기
                ajax.json('/itsm/getSubCategories.json', {upperId: upperId}, function (data) {
                    if (data.successYN === 'Y' && data.rows) {
                        var list = data.rows;
                        // 하위 카테고리 드롭다운 채우기
                        list.forEach(function (item) {
                            $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                        });
                    } else {
                        // 오류 처리
                        gfnAlertMsg(data.response.message);
                    }
                }, true);
            }
            ,

            // 이벤트 및 버튼 동작
            regist_event: function () {
                // 목록 버튼 클릭
                // 이전 화면(prev_url)로 돌아감
                $('#itsm_m01_s01 #btn_list').click(function () {
                    main.goScreenSubmit(itsm_m01_s01.prev_url, '');
                });

                // 저장(등록/수정) 버튼
                $('#itsm_m01_s01 #btn_save').click(function () {
                    if (!isValidObj('itsm_m01_s01_form #client_id')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_title')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_category')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_sub_category')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_contents')) return;

                    // 폼 직렬화 (serializeForm)
                    // datePicker 값은 yymmdd 형식으로 변환
                    var param = $('#itsm_m01_s01_form').serializeForm(function (date) {
                        return $.datepicker.formatDate('yymmdd', date);
                    });

                    // 요청번호 세팅 (새 등록이면 빈값, 수정이면 실제 rq_no)
                    param.rq_no = itsm_m01_s01.rq_no;

                    // FormData로 변환 (첨부파일 업로드용)
                    var formData = new FormData();
                    formData.append("paramObj", JSON.stringify(param));

                    // 첨부파일 (upload_id="itsm_request")를 filesBuffer_itsm_request 배열에서 가져옴
                    for (var i = 0; i < filesBuffer_itsm_request.length; i++) {
                        formData.append("files_itsm_request", filesBuffer_itsm_request[i]);
                    }

                    // AJAX로 등록/수정 처리
                    // '/itsm/insertOrUpdateRq.json' 엔드포인트 호출
                    ajax.formData("/itsm/insertOrUpdateRq.json", formData, function (res) {
                        // 성공 여부 확인
                        if (res && res.successYN == "Y") {
                            // 정상 저장 후 이전 화면으로 이동
                            main.goScreenSubmit(itsm_m01_s01.prev_url, '');
                        } else {
                            // 실패 시 메시지 출력
                            gfnAlertMsg(res.response.message);
                        }
                    }, false);
                });

                // 삭제 버튼
                // 삭제 여부 확인 후 '/itsm/deleteRq.json' 호출
                $('#itsm_m01_s01 #btn_delete').click(function () {
                    gfnConfirmMsg('삭제하시겠습니까?', function () {
                        ajax.json('/itsm/deleteRq.json', {rq_no: itsm_m01_s01.rq_no}, function (data) {
                            if (data.successYN == 'Y') {
                                // 삭제 완료 시 목록 화면(itsm_m01)으로
                                main.goScreenSubmit('/itsm/itsm_m01', '');
                            } else {
                                // 오류 메시지
                                gfnAlertMsg(data.response.message);
                            }
                        });
                    });
                });

                // 요청범주(rq_category) 선택 시
                // 하위 카테고리(rq_sub_category) 동적 생성
                // ajax 추가로, 설정 변경
                $('#rq_category').change(function () {
                    // itsm_m01_s01.set_category_level2($(this).val());
                    var selectedValue = $(this).val();
                    if (selectedValue) {
                        itsm_m01_s01.set_category_level2_ajax(selectedValue);
                    } else {
                        // 선택값이 없으면 하위 카테고리 초기화
                        var $sub = $('#rq_sub_category');
                        $sub.empty();
                        $sub.append('<option value="">-선택-</option>');
                    }
                });
            }
        }
    ;

    // 문서 로드 후 init 실행
    // hideLoading()으로 로딩 표시 제거
    $(document).ready(function () {
        itsm_m01_s01.init();
        hideLoading();
    });
</script>

<!-- Content -->
<div id="itsm_m01_s01">
    <div class="subtitle2" id="commonMenuPath"></div>

    <!-- 기본정보 -->
    <div class="display_flex">
        <div class="subtitle3">
            <h4>기본정보</h4>
        </div>
        <div class="btnWrap">
            <p class="tr">
                <!-- 저장 버튼 (등록/수정 겸용), 삭제 버튼, 목록 버튼 -->
                <a style="cursor:pointer;" class="bluebtn" id="btn_save">
                    <i class="xi-save"></i> 저장
                </a>
                <a style="cursor:pointer; display:none;" class="bluebtn" id="btn_delete">
                    <i class="xi-trash"></i> 삭제
                </a>
                <a style="cursor:pointer;" class="bluebtn" id="btn_list">
                    <i class="xi-bars"></i> 목록
                </a>
            </p>
        </div>
    </div>

    <form id="itsm_m01_s01_form" name="itsm_m01_s01_form">
        <div class="tableWrap">
            <table class="tableB">
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
                    <tr>
                        <!-- 고객사 -->
                        <th class="point">
                            <label for="client_id">고객사</label>
                        </th>
                        <td>
                            <select id="client_id" name="client_id" style="width:200px;" required>
                                <option value="">-선택-</option>
                                <c:forEach items="${client_list}" var="ROW">
                                    <option value="<c:out value='${ROW.client_id}'/>">
                                        <c:out value='${ROW.client_name}'/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <label for="rq_no">요청번호</label>
                        </th>
                        <td>
                            <span id="rq_no" name="rq_no"></span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="corp_nm">소속회사</label>
                        </th>
                        <td>
                            <span id="corp_nm" name="corp_nm"><c:out value="${corp_nm}"/></span>
                        </td>
                        <th>
                            <label for="reg_user_nm">작성자</label>
                        </th>
                        <td>
                            <span id="reg_user_nm" name="reg_user_nm"><c:out value="${dept_nm}"/> / <c:out
                                    value="${user_nm}"/> <c:out value="${pos_nm}"/></span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- 요청정보 -->
        <div class="display_flex">
            <div class="subtitle3">
                <h4>요청정보</h4>
            </div>
        </div>
        <div class="tableWrap">
            <table class="tableB">
                <colgroup>
                    <col width="15%">
                    <col width="40%">
                    <col width="15%">
                    <col width="30%">
                </colgroup>
                <tbody>
                    <!-- 1행: 요청유형(라디오), 납기 요청일 -->
                    <tr>
                        <th class="point">요청유형</th>
                        <td>
                            <!-- Controller에서 rqTypeList 제공 -->
                            <!-- 라디오 버튼 반복 -->
                            <c:forEach var="type" items="${rq_type}">
                                <input
                                        type="radio"
                                        id="rq_type_${type.code}"
                                        name="rq_type"
                                        value="${type.code}"
                                        style="vertical-align:middle;"
                                />
                                <label for="rq_type_${type.code}">
                                    <c:out value="${type.name}"/>
                                </label>
                                &nbsp;&nbsp;
                            </c:forEach>
                        </td>
                        <th>
                            납기요청일
                        </th>
                        <td>
                            <input
                                    type="text"
                                    id="req_date"
                                    name="req_date"
                                    class="wi120p"
                                    maxlength="10"
                                    required style="width: 35%;"
                            />
                        </td>
                    </tr>
                    <!-- 2행: 긴급도(라디오) -->
                    <tr>
                        <th class="point">긴급도</th>
                        <td colspan="3">
                            <!-- Controller에서 rqUrgencyList 제공 -->
                            <!-- 라디오 버튼 반복 -->
                            <c:forEach var="ug" items="${rq_urgency}">
                                <input
                                        type="radio"
                                        id="rq_urgency_${ug.code}"
                                        name="rq_urgency"
                                        value="${ug.code}"
                                        style="vertical-align:middle;"
                                />
                                <label for="rq_urgency_${ug.code}">
                                    <c:out value="${ug.name}"/>
                                </label>
                                &nbsp;&nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                    <!-- 3행: 요청제목 -->
                    <tr>
                        <th class="point">
                            <label for="rq_title">요청제목</label>
                        </th>
                        <td colspan="3">
                            <input
                                    type="text"
                                    id="rq_title"
                                    name="rq_title"
                                    style="width:90%;"
                                    maxlength="200"
                                    required
                            />
                        </td>
                    </tr>
                    <!-- 4행: 요청범주, 상세요청범주 -->
                    <tr>
                        <th class="point">
                            <label for="rq_category">요청범주</label>
                        </th>
                        <td>
                            <select id="rq_category" name="rq_category" style="width:200px;" required></select>
                        </td>
                        <th class="point">
                            <label for="rq_sub_category">상세요청범주</label>
                        </th>
                        <td>
                            <select id="rq_sub_category" name="rq_sub_category" style="width:200px;" required></select>
                        </td>
                    </tr>
                    <!-- 5행: 요청내용 -->
                    <tr>
                        <th class="point">
                            <label for="rq_contents">요청내용</label>
                        </th>
                        <td colspan="3">
                            <textarea
                                    id="rq_contents"
                                    name="rq_contents"
                                    rows="6"
                                    style="width:90%;"
                                    maxlength="2000"
                                    required
                            ></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>

    <!-- 첨부파일 모듈 -->
    <jsp:include page="/file/uploadForm" flush="true">
        <jsp:param name="ref_tbl" value="itsm_request"/>
        <jsp:param name="ref_pk1" value="${param.rq_no}"/>
        <jsp:param name="ref_pk2" value=""/>
        <jsp:param name="ref_pk3" value=""/>
        <jsp:param name="readonly_yn" value="N"/>
        <jsp:param name="upload_id" value="itsm_request"/>
        <jsp:param name="sub_title_nm" value="첨부파일"/>
    </jsp:include>

    <div class="btnWrap">
        <p class="tc">
            <!-- 동일한 버튼: 저장, 삭제, 목록 -->
            <a style="cursor:pointer;" class="bluebtn" id="btn_save">
                <i class="xi-save"></i> 저장
            </a>
            <a style="cursor:pointer; display:none;" class="bluebtn" id="btn_delete">
                <i class="xi-trash"></i> 삭제
            </a>
            <a style="cursor:pointer;" class="bluebtn" id="btn_list">
                <i class="xi-bars"></i> 목록
            </a>
        </p>
    </div>
</div>

### m01_s02

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    /***********************************************************
     * 설명   : ITSM > ITSM 요청현황 > ITSM 요청상세(등록/수정)
     * 작성자 : [작성자명] 정동규
     * 작성일 : [작성일자] 2025.02.18
     ***********************************************************/
%>
<style type="text/css">
    span {
        word-break: break-word;
        white-space: pre-wrap;
    }
</style>

<script>
    var itsm_m01_s01 = {
            // 화면 파라미터
            prev_url: '${param.prev_url}',   // 이전 화면으로 돌아가기 위한 URL 파라미터
            rq_no: '${param.rq_no}',         // 요청번호 (빈값이면 새 등록, 값 있으면 상세 조회(수정 및 삭제 가능)
            rq: {},                  // 요청 상세 정보
            // 카테고리 목록 (JSON 문자열)
            categoryList: '${rq_category}',

            init: function () {
                // 화면 로딩 시점에 필요한 함수들을 순차적으로 호출
                itsm_m01_s01.parse_data();       // 카테고리 JSON 파싱
                itsm_m01_s01.set_form();         // 폼 초기 세팅
                itsm_m01_s01.regist_event();     // 각종 이벤트 등록
            },

            parse_data: function () {
                // categoryList를 실제 JS 배열로 변환
                // 컨트롤러에서 DB조회 후 JSON으로 전달받은 것을 화면에서 파싱
                try {
                    itsm_m01_s01.categoryList = JSON.parse(itsm_m01_s01.categoryList);
                } catch (e) {
                    // 파싱 실패 시 빈 배열로 세팅
                    itsm_m01_s01.categoryList = [];
                }
            },

            // 폼 세팅
            set_form: function () {
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\r/gi,"\\r");
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\n/gi,"\\n");
                // itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\t/gi,"\\t");

                try {
                    itsm_m01_s01.rq = JSON.parse(JSON.stringify(${rq}));
                } catch (e) {
                    // 실패 시 빈 객체
                    itsm_m01_s01.rq = {};
                }

                // 요청번호 표시
                if (itsm_m01_s01.rq_no != '') {
                    $('#itsm_m01_s01_form #rq_no').text(itsm_m01_s01.rq_no);
                }

                var now = new Date();
                datePickerInit("itsm_m01_s01_form #req_date");
                $("#itsm_m01_s01_form #req_date").datepicker("setDate", new Date(now.getFullYear(), now.getMonth(), now.getDate() + 5));

                // 폼에 값 채우기 (populate)
                // name과 JSON key가 일치하면 자동 매핑
                $('#itsm_m01_s01_form').populate(itsm_m01_s01.rq, {
                    reset: false,
                    hasDatepicker: true
                });
                // 카테고리(level=1) 옵션 세팅
                itsm_m01_s01.set_category_level1();

                // 등록 전/후 상태 구분
                // rq 객체가 비었다면 => 새 등록
                // rq 객체가 있으면 => 수정 모드
                if (Object.keys(itsm_m01_s01.rq).length == 0) {
                    // 새 등록
                    $('#itsm_m01_s01 #btn_delete').hide();  // 새 등록 시 삭제 버튼 숨김

                    // (2) 요청유형, 긴급도 라디오 기본 세팅 (서비스요청 / 보통)
                    $('#rq_type_G002101').prop('checked', true);  // 서비스요청
                    $('#rq_urgency_G002202').prop('checked', true);  // 보통

                } else {
                    // 이미 등록된 건(수정)
                    $('#itsm_m01_s01 #btn_save').html('<i class="xi-save"></i> 수정');  // 버튼 문구 변경
                    $('#itsm_m01_s01 #btn_delete').show();                            // 삭제 버튼 표시

                    $('#client_id').val(itsm_m01_s01.rq.client_id).prop('disabled', true);

                    // 라디오 버튼(요청유형, 긴급도) 체크
                    if (!isNull(itsm_m01_s01.rq.rq_type)) {
                        $('#rq_type_' + itsm_m01_s01.rq.rq_type).prop('checked', true);
                    }
                    if (!isNull(itsm_m01_s01.rq.rq_urgency)) {
                        $('#rq_urgency_' + itsm_m01_s01.rq.rq_urgency).prop('checked', true);
                    }

                    // 수정 모드에서 카테고리 값 설정
                    if (!isNull(itsm_m01_s01.rq.rq_category)) {
                        //요청범주 기본값 가져오기
                        $('#rq_category').val(itsm_m01_s01.rq.rq_category);

                        itsm_m01_s01.set_category_level2(itsm_m01_s01.rq.rq_category);

                        //상세요청범주 기본값 가져오기
                        if (!isNull(itsm_m01_s01.rq.rq_sub_category)) {
                            $('#rq_sub_category').val(itsm_m01_s01.rq.rq_sub_category);
                        }
                    }
                }

                // 로딩 표시 해제
                hideLoading(true);
            },

            // 카테고리(level=1) 옵션 세팅
            set_category_level1: function () {
                // categoryList에서 level이 '1'인 것만 필터링
                var list = itsm_m01_s01.categoryList.filter(function (item) {
                    return parseInt(item.level, 10) === 1;
                });
                var $cat = $('#rq_category');
                // var $sub = $('#rq_sub_category');

                // -선택-
                $cat.empty();
                $cat.append('<option value="">-선택-</option>');

                // AJAX로 수정하며 주석 처리
                // $sub.empty();
                // $sub.append('<option value="">-선택-</option>');

                // level=1 목록 반복문
                list.forEach(function (item) {
                    $cat.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                });
            }
            ,

            //상세요청범주 옵션
            set_category_level2: function (upperId) {
                // categoryList에서 level=2 이고, ref_pk1==upperId 인 것만 필터링
                var list = itsm_m01_s01.categoryList.filter(function (item) {
                    return item.level == '2' && item.ref_pk1 == upperId;
                });
                var $sub = $('#rq_sub_category');

                // level=2 목록 반복문
                list.forEach(function (item) {
                    $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                });
            }
            ,

            // 상세요청범주(level=2) 옵션(With AJAX)
            set_category_level2_ajax: function (upperId) {
                // categoryList에서 level=2 이고, ref_pk1==upperId 인 것만 필터링
                // var list = itsm_m01_s01.categoryList.filter(function (item) {
                //     return item.level == '2' && item.ref_pk1 == upperId;
                // });
                // var $sub = $('#rq_sub_category');
                //
                // // level=2 목록 반복문
                // list.forEach(function (item) {
                //     $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                // });
                var $sub = $('#rq_sub_category');
                $sub.empty();
                $sub.append('<option value="">-선택-</option>');

                // AJAX 호출로 하위 카테고리 가져오기
                ajax.json('/itsm/getSubCategories.json', {upperId: upperId}, function (data) {
                    if (data.successYN === 'Y' && data.rows) {
                        var list = data.rows;
                        // 하위 카테고리 드롭다운 채우기
                        list.forEach(function (item) {
                            $sub.append('<option value="' + item.code_id + '">' + item.code_nm + '</option>');
                        });
                    } else {
                        // 오류 처리
                        gfnAlertMsg(data.response.message);
                    }
                }, true);
            }
            ,

            // 이벤트 및 버튼 동작
            regist_event: function () {
                // 목록 버튼 클릭
                // 이전 화면(prev_url)로 돌아감
                $('#itsm_m01_s01 #btn_list').click(function () {
                    main.goScreenSubmit(itsm_m01_s01.prev_url, '');
                });

                // 저장(등록/수정) 버튼
                $('#itsm_m01_s01 #btn_save').click(function () {
                    if (!isValidObj('itsm_m01_s01_form #client_id')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_title')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_category')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_sub_category')) return;
                    if (!isValidObj('itsm_m01_s01_form #rq_contents')) return;

                    // 폼 직렬화 (serializeForm)
                    // datePicker 값은 yymmdd 형식으로 변환
                    var param = $('#itsm_m01_s01_form').serializeForm(function (date) {
                        return $.datepicker.formatDate('yymmdd', date);
                    });

                    // 요청번호 세팅 (새 등록이면 빈값, 수정이면 실제 rq_no)
                    param.rq_no = itsm_m01_s01.rq_no;

                    // FormData로 변환 (첨부파일 업로드용)
                    var formData = new FormData();
                    formData.append("paramObj", JSON.stringify(param));

                    // 첨부파일 (upload_id="itsm_request")를 filesBuffer_itsm_request 배열에서 가져옴
                    for (var i = 0; i < filesBuffer_itsm_request.length; i++) {
                        formData.append("files_itsm_request", filesBuffer_itsm_request[i]);
                    }

                    // AJAX로 등록/수정 처리
                    // '/itsm/insertOrUpdateRq.json' 엔드포인트 호출
                    ajax.formData("/itsm/insertOrUpdateRq.json", formData, function (res) {
                        // 성공 여부 확인
                        if (res && res.successYN == "Y") {
                            // 정상 저장 후 이전 화면으로 이동
                            main.goScreenSubmit(itsm_m01_s01.prev_url, '');
                        } else {
                            // 실패 시 메시지 출력
                            gfnAlertMsg(res.response.message);
                        }
                    }, false);
                });

                // 삭제 버튼
                // 삭제 여부 확인 후 '/itsm/deleteRq.json' 호출
                $('#itsm_m01_s01 #btn_delete').click(function () {
                    gfnConfirmMsg('삭제하시겠습니까?', function () {
                        ajax.json('/itsm/deleteRq.json', {rq_no: itsm_m01_s01.rq_no}, function (data) {
                            if (data.successYN == 'Y') {
                                // 삭제 완료 시 목록 화면(itsm_m01)으로
                                main.goScreenSubmit('/itsm/itsm_m01', '');
                            } else {
                                // 오류 메시지
                                gfnAlertMsg(data.response.message);
                            }
                        });
                    });
                });

                // 요청범주(rq_category) 선택 시
                // 하위 카테고리(rq_sub_category) 동적 생성
                // ajax 추가로, 설정 변경
                $('#rq_category').change(function () {
                    // itsm_m01_s01.set_category_level2($(this).val());
                    var selectedValue = $(this).val();
                    if (selectedValue) {
                        itsm_m01_s01.set_category_level2_ajax(selectedValue);
                    } else {
                        // 선택값이 없으면 하위 카테고리 초기화
                        var $sub = $('#rq_sub_category');
                        $sub.empty();
                        $sub.append('<option value="">-선택-</option>');
                    }
                });
            }
        }
    ;

    // 문서 로드 후 init 실행
    // hideLoading()으로 로딩 표시 제거
    $(document).ready(function () {
        itsm_m01_s01.init();
        hideLoading();
    });
</script>

<!-- Content -->
<div id="itsm_m01_s01">
    <div class="subtitle2" id="commonMenuPath"></div>

    <!-- 기본정보 -->
    <div class="display_flex">
        <div class="subtitle3">
            <h4>기본정보</h4>
        </div>
        <div class="btnWrap">
            <p class="tr">
                <!-- 저장 버튼 (등록/수정 겸용), 삭제 버튼, 목록 버튼 -->
                <a style="cursor:pointer;" class="bluebtn" id="btn_save">
                    <i class="xi-save"></i> 저장
                </a>
                <a style="cursor:pointer; display:none;" class="bluebtn" id="btn_delete">
                    <i class="xi-trash"></i> 삭제
                </a>
                <a style="cursor:pointer;" class="bluebtn" id="btn_list">
                    <i class="xi-bars"></i> 목록
                </a>
            </p>
        </div>
    </div>

    <form id="itsm_m01_s01_form" name="itsm_m01_s01_form">
        <div class="tableWrap">
            <table class="tableB">
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
                    <tr>
                        <!-- 고객사 -->
                        <th class="point">
                            <label for="client_id">고객사</label>
                        </th>
                        <td>
                            <select id="client_id" name="client_id" style="width:200px;" required>
                                <option value="">-선택-</option>
                                <c:forEach items="${client_list}" var="ROW">
                                    <option value="<c:out value='${ROW.client_id}'/>">
                                        <c:out value='${ROW.client_name}'/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <label for="rq_no">요청번호</label>
                        </th>
                        <td>
                            <span id="rq_no" name="rq_no"></span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="corp_nm">소속회사</label>
                        </th>
                        <td>
                            <span id="corp_nm" name="corp_nm"><c:out value="${corp_nm}"/></span>
                        </td>
                        <th>
                            <label for="reg_user_nm">작성자</label>
                        </th>
                        <td>
                            <span id="reg_user_nm" name="reg_user_nm"><c:out value="${dept_nm}"/> / <c:out
                                    value="${user_nm}"/> <c:out value="${pos_nm}"/></span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- 요청정보 -->
        <div class="display_flex">
            <div class="subtitle3">
                <h4>요청정보</h4>
            </div>
        </div>
        <div class="tableWrap">
            <table class="tableB">
                <colgroup>
                    <col width="15%">
                    <col width="40%">
                    <col width="15%">
                    <col width="30%">
                </colgroup>
                <tbody>
                    <!-- 1행: 요청유형(라디오), 납기 요청일 -->
                    <tr>
                        <th class="point">요청유형</th>
                        <td>
                            <!-- Controller에서 rqTypeList 제공 -->
                            <!-- 라디오 버튼 반복 -->
                            <c:forEach var="type" items="${rq_type}">
                                <input
                                        type="radio"
                                        id="rq_type_${type.code}"
                                        name="rq_type"
                                        value="${type.code}"
                                        style="vertical-align:middle;"
                                />
                                <label for="rq_type_${type.code}">
                                    <c:out value="${type.name}"/>
                                </label>
                                &nbsp;&nbsp;
                            </c:forEach>
                        </td>
                        <th>
                            납기요청일
                        </th>
                        <td>
                            <input
                                    type="text"
                                    id="req_date"
                                    name="req_date"
                                    class="wi120p"
                                    maxlength="10"
                                    required style="width: 35%;"
                            />
                        </td>
                    </tr>
                    <!-- 2행: 긴급도(라디오) -->
                    <tr>
                        <th class="point">긴급도</th>
                        <td colspan="3">
                            <!-- Controller에서 rqUrgencyList 제공 -->
                            <!-- 라디오 버튼 반복 -->
                            <c:forEach var="ug" items="${rq_urgency}">
                                <input
                                        type="radio"
                                        id="rq_urgency_${ug.code}"
                                        name="rq_urgency"
                                        value="${ug.code}"
                                        style="vertical-align:middle;"
                                />
                                <label for="rq_urgency_${ug.code}">
                                    <c:out value="${ug.name}"/>
                                </label>
                                &nbsp;&nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                    <!-- 3행: 요청제목 -->
                    <tr>
                        <th class="point">
                            <label for="rq_title">요청제목</label>
                        </th>
                        <td colspan="3">
                            <input
                                    type="text"
                                    id="rq_title"
                                    name="rq_title"
                                    style="width:90%;"
                                    maxlength="200"
                                    required
                            />
                        </td>
                    </tr>
                    <!-- 4행: 요청범주, 상세요청범주 -->
                    <tr>
                        <th class="point">
                            <label for="rq_category">요청범주</label>
                        </th>
                        <td>
                            <select id="rq_category" name="rq_category" style="width:200px;" required></select>
                        </td>
                        <th class="point">
                            <label for="rq_sub_category">상세요청범주</label>
                        </th>
                        <td>
                            <select id="rq_sub_category" name="rq_sub_category" style="width:200px;" required></select>
                        </td>
                    </tr>
                    <!-- 5행: 요청내용 -->
                    <tr>
                        <th class="point">
                            <label for="rq_contents">요청내용</label>
                        </th>
                        <td colspan="3">
                            <textarea
                                    id="rq_contents"
                                    name="rq_contents"
                                    rows="6"
                                    style="width:90%;"
                                    maxlength="2000"
                                    required
                            ></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>

    <!-- 첨부파일 모듈 -->
    <jsp:include page="/file/uploadForm" flush="true">
        <jsp:param name="ref_tbl" value="itsm_request"/>
        <jsp:param name="ref_pk1" value="${param.rq_no}"/>
        <jsp:param name="ref_pk2" value=""/>
        <jsp:param name="ref_pk3" value=""/>
        <jsp:param name="readonly_yn" value="N"/>
        <jsp:param name="upload_id" value="itsm_request"/>
        <jsp:param name="sub_title_nm" value="첨부파일"/>
    </jsp:include>

    <div class="btnWrap">
        <p class="tc">
            <!-- 동일한 버튼: 저장, 삭제, 목록 -->
            <a style="cursor:pointer;" class="bluebtn" id="btn_save">
                <i class="xi-save"></i> 저장
            </a>
            <a style="cursor:pointer; display:none;" class="bluebtn" id="btn_delete">
                <i class="xi-trash"></i> 삭제
            </a>
            <a style="cursor:pointer;" class="bluebtn" id="btn_list">
                <i class="xi-bars"></i> 목록
            </a>
        </p>
    </div>
</div>