# JSP 기초

## 클라이언트에서 서블릿으로 요청하는 방식
- 서블릿(Servlet)
    - 클라이언트 요청 시, 서버에서 해당 요청을 처리하고 응답 생성
    - HTTP 요청 처리 + 동적 웹 페이지 생성

## 서블릿에서 세션
- 세션
    - 클라이언트와 서버 간의 상태 유지
    - HTTP는 stateless, 이를 해결하기 위한 세션

- 세션, 쿠키, 토큰 비교
| 개념 | 설명 |
|------|------|
| **세션 (Session)** | 서버에 저장되는 사용자 상태 정보 |
| **쿠키 (Cookie)** | 클라이언트(브라우저)에 저장되는 작은 데이터 |
| **토큰 (Token)** | 사용자의 인증 정보를 담은 문자열로, 클라이언트와 서버 간 인증에 사용 |

---

| 비교 항목 | **세션(Session)** | **쿠키(Cookie)** | **토큰(Token)** |
|-----------|----------------|----------------|----------------|
| **저장 위치** | 서버 | 클라이언트(브라우저) | 클라이언트 (브라우저 또는 모바일) |
| **유효 범위** | 서버가 설정한 시간 동안 유지 | 브라우저에 설정된 만료 시간까지 유지 | 만료 시간이 설정된 경우까지 유지 |
| **보안** | 상대적으로 안전 (서버에서 관리) | 클라이언트에 저장되므로 보안에 취약 | 암호화 가능하여 비교적 안전 |
| **속도** | 요청 시마다 서버에서 조회 필요 → 속도 느림 | 클라이언트에서 직접 접근 가능 → 속도 빠름 | 클라이언트에서 검증 가능하여 빠름 |
| **사용 목적** | 로그인 상태 유지 (인증) | 사용자 환경 설정, 자동 로그인 | API 인증, JWT 기반 인증 |
| **서버 부하** | 세션 데이터가 많아지면 부하 증가 | 서버에 저장되지 않으므로 영향 없음 | 서버에 저장하지 않아 부하 적음 |
| **데이터 저장 가능 여부** | 여러 데이터 저장 가능 | 4KB 이하의 작은 데이터 저장 가능 | JWT 사용 시 클레임(Claim) 정보 저장 가능 |
| **만료 시간** | 기본적으로 30분 (설정 가능) | 브라우저가 설정한 만료 시간까지 | 보통 1시간~24시간 (설정 가능) |
| **주요 사용 예시** | 로그인 정보 유지 | 자동 로그인, UI 설정 저장 | API 인증, OAuth 2.0 |

---

[세션, 쿠키, 토큰, CDN 참고링크](https://hongong.hanbit.co.kr/%EC%99%84%EB%B2%BD-%EC%A0%95%EB%A6%AC-%EC%BF%A0%ED%82%A4-%EC%84%B8%EC%85%98-%ED%86%A0%ED%81%B0-%EC%BA%90%EC%8B%9C-%EA%B7%B8%EB%A6%AC%EA%B3%A0-cdn/)

## JSP 지시어
- JSP 지시어
    - JSP 페이지의 동작 방식을 설정
    - 컴파일 단계에서 처리, 전체적 속성 제어
### JSP 지시어 비교

| 지시어 | 설명 | 예제 |
|--------|------|------|
| **`page`** | JSP 페이지의 환경을 설정 | `<%@ page contentType="text/html;charset=UTF-8" %>` |
| **`include`** | 다른 JSP 파일을 현재 파일에 포함 (정적 포함) | `<%@ include file="header.jsp" %>` |
| **`taglib`** | JSTL 및 커스텀 태그 라이브러리를 사용할 때 필요 | `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>` |

### Page 지시어
주요 속성:
| 속성 | 설명 | 예제 |
|------|------|------|
| `language` | JSP에서 사용할 언어(Java) | `<%@ page language="java" %>` |
| `contentType` | 응답의 데이터 타입 | `<%@ page contentType="text/html;charset=UTF-8" %>` |
| `pageEncoding` | JSP 파일의 인코딩 방식 | `<%@ page pageEncoding="UTF-8" %>` |
| `import` | Java 클래스를 import | `<%@ page import="java.util.Date" %>` |
| `errorPage` | 에러 발생 시 이동할 페이지 | `<%@ page errorPage="error.jsp" %>` |
| `isErrorPage` | 현재 페이지가 에러 처리 페이지인지 여부 | `<%@ page isErrorPage="true" %>` |

## JSP 스크립트 요소 
- Java 코드 직접 삽입 및 실행
    - 다만, 유지보수 및 가독성 향상을 위해 JSTL과 EL 사용 권장
    - 개념만 익혀두고, 사용은 지양할 것

| 요소 | 설명 | 문법 |
|------|--------------------------------------|---------------------------|
| **선언문 (`<%! %>`)** | 클래스 변수나 메서드를 선언할 때 사용 | `<%! 변수 선언 또는 메서드 %>` |
| **스크립트릿 (`<% %>`)** | JSP 페이지에서 Java 코드를 실행할 때 사용 | `<% 실행할 Java 코드 %>` |
| **표현식 (`<%= %>`)** | 값을 출력할 때 사용 (자동으로 `out.print()` 처리) | `<%= 출력할 값 %>` |

## JSP 내장 객체
- JSP에서 기본적으로 제공하는 객체
    - 별도 선언 없이 사용 가능
- 컨테이너가 자동으로 생성하여 제공
- 주요 역할
    - 클라이언트 요청 처리, 서버 응답 관리, 세션 유지 등
    - ex) `requset`, `response`, `application`, `session`, `page`
- 다만, 이는 View에서 비즈니스 로직을 처리하는 것과 같음
    - 지양할 것
## JSTL
- JSTL(JavaServer Pages Standard Tag Library)
    - Java 코드 최소화, 태그 형식으로 쉽게 표현
    - `<% %>`등의 Java 코드 최소화 가능
- 주요 기능 및 태그 라이브러리

| 기능 | URI | 태그 라이브러리 접두사 |
|------|----------------------------|------------------|
| **코어(Core)** | `http://java.sun.com/jsp/jstl/core` | `c` |
| **포맷(Formatting)** | `http://java.sun.com/jsp/jstl/fmt` | `fmt` |
| **XML 처리** | `http://java.sun.com/jsp/jstl/xml` | `x` |
| **SQL 처리** | `http://java.sun.com/jsp/jstl/sql` | `sql` |
| **함수(Function)** | `http://java.sun.com/jsp/jstl/functions` | `fn` |

- 문법
```jsp
<%@ taglib uri="태그 라이브러리의 URI" prefix="접두사" %>
```
- 사용 예제

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- 변수 설정 --%>
<c:set var="price" value="25000" />
<c:set var="currentDate" value="<%= new java.util.Date() %>" />

<%-- 조건문 사용 --%>
<c:if test="${price > 20000}">
    <p>가격이 20,000원을 초과했습니다.</p>
</c:if>

<%-- 숫자 포맷 적용 --%>
<p>가격: <fmt:formatNumber value="${price}" type="currency" /></p>

<%-- 날짜 포맷 적용 --%>
<p>현재 날짜: <fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>

<%-- 문자열 함수 사용 --%>
<p>문자열 길이: ${fn:length('Hello JSTL')}</p>

<%-- 반복문 사용 --%>
<ul>
    <c:forEach var="num" begin="1" end="3">
        <li>리스트 항목 ${num}</li>
    </c:forEach>
</ul>
```