# DAFramework
## WEB_INF
### meta_bangmun.xls
- 현재 프레임워크에 서블릿매핑이 없음
    - 엑셀로 관리
    - WAS heap memory에 엑셀이 올라감
        - Servelet Mapping을 대신하게 됨
- Command
    - 각 이벤트에 대한 메타데이터, KEY 중복이 불허
        - 중복 시 FW 기동중지
    - Command = [UC_ID] + '.' + [EV_ID]
- UC 명
    - 모듈 혹은 화면 단위로, 통상 구분가능한 단위를 의미
- EV 명
    - UC에서 행할 수 있는 기능들을 의미
- EV ID
    - 이벤트에 대한 ID, 유형별 영문자1자 + SEQ 2글자로 구성
    - L## Form Load(화면 로딩)
    - R## Retrieve(조회 쿼리 유형)
    - C## insert(등록 쿼리 유형)
    - U## update(수정 쿼리 유형)
    - D## delete(삭제 쿼리 유형)
    - X## CRUD(복합적 쿼리 유형)
    - S## system(시스템 동작 유형)
    - Z## 기타 유형
- Class
    - 이벤트 로직에 대응하는 java class명
    - CLASS = [UC_ID] + [EV_ID] + 'AC'
- Component
    - 컴포넌트의 Full 경로
    - [Package] + '.' + [Class]
- 업무유형
    - NA: 일반 로직(자원할당 X)
    - Tx: DB 로직(DB커넥션 할당)
    - Page: 단순 페이지 로직
        - 업무로직 X, No Java
    - SAP
        - SAP RFC 연동 로직(SAP커넥션 할당)
- 입력유형
    - WEB: 일반 WEB 요청
    - AJAX: AJAX WEB 요청
    - FIEL: 멀티파트 WEB 요청
    - XML: XML형 요청 파싱
    - WEB-LTO: WEB 유형에 LTO 처리 기능 추가
    - AJAX-LTO: AJAX 유형에 LTO 처리 기능 추가
    - X: 입력 파싱 없음
- 출력유형
    - JSP: 일반 웹페이지 포워드
    - XML: XML 출력
    - FILE: 파일 다운로드 처리
    - EXCEL: 엑셀파일 다운로드
    - X: 출력없음
    - CHAINED: 타 이벤트 연결
    - BRANCH: 타 이벤트 분기
    - CHAINED-TX: CHAINED의 트랜잭션 연동형
    - BRANCH-TX: BRANCH의 트랜잭션 연동

### conf
- 설정 파일 저장
- 유의깊게 살펴볼만한 것은 딱히 없는듯..?

### components
- class 파일 저장

```java
// 실제로 값이 담겨져 있다고 가정
LTO subject = new LTO();
for(int i=0; i< subject.size(); i++){
			System.out.println("jdg2: subject:" + subject.get(i).getMap());
}
```
LTO는 LIST + MTO(HashMap) --> 실제 데이터는 List에만 저장된다.
subject를 List에 저장.
근데 get을 하면.. a.get(idx)를 MTO로 변환해서 반환?????
넣을 떄 mto로 넣었음.
그니까 사실 LTO는, ArrayList<MTO> 인거임 이건 즉, ArrayList<HashMap>과 동일함.