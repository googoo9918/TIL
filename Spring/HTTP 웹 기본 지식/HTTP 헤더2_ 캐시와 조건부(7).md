### 목차
- [캐시 기본 동작](#캐시-기본-동작)
  - [캐시가 없을 때](#캐시가-없을-때)
  - [캐시 적용](#캐시-적용)
- [검증 헤더와 조건부 요청1](#검증-헤더와-조건부-요청1)
  - [캐시 만료후에도 서버에서 데이터를 변경하지 않았을 때](#캐시-만료후에도-서버에서-데이터를-변경하지-않았을-때)
  - [정리](#정리)
- [검증 헤더와 조건부 요청2](#검증-헤더와-조건부-요청2)
  - [조건부 요청 헤더 예시](#조건부-요청-헤더-예시)
  - [Last-Modified, If-Modified-Since 단점](#last-modified-if-modified-since-단점)
  - [ETag, If-None_Match](#etag-if-none_match)
  - [검증 헤더 정리](#검증-헤더-정리)
- [캐시와 조건부 요청 헤더](#캐시와-조건부-요청-헤더)
  - [Cache-Control](#cache-control)
  - [Pragma](#pragma)
  - [Expires](#expires)
  - [검증 헤더와 조건부 요청 헤더](#검증-헤더와-조건부-요청-헤더)
- [프록시 캐시](#프록시-캐시)
  - [Cache-Control](#cache-control-1)
- [캐시 무효화](#캐시-무효화)
  - [Cache-Control](#cache-control-2)
  - [no-cache vs must-revalidate](#no-cache-vs-must-revalidate)
## 캐시 기본 동작
### 캐시가 없을 때
![image](https://user-images.githubusercontent.com/102513932/199150631-e7c29138-0ff1-4dda-b40d-4def7652c3fc.png)
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150642-7307dd92-df74-45ea-b028-f39166ca3611.png)
- 두 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150660-40e08585-7b57-452f-9428-57cb1e9ad360.png)
- **느린 사용자 경험**
  - 데이터가 변경되지 않아도 계속 네트워크를 통해 데이터를 다운받아야 함
  - 인터넷 네트워크는 매우 느리고 비쌈
  - 브라우저 로딩 속도가 느림

### 캐시 적용
![image](https://user-images.githubusercontent.com/102513932/199150820-1bf0e4e8-ef38-424e-861d-b03604afc05f.png)
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150846-5d2cc98b-3542-455f-af33-2abed8e798ea.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199150862-890f48bf-8ac0-4045-98c6-8aa4f6db9bee.png)
- 두 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150949-91d2eed8-dfac-454c-9cc7-907ad34c557b.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199150966-4752f799-ee68-4043-9e7b-75db75ee58e8.png)
- **빠른 사용자 경험**
  - 캐시 덕분에 캐시 가능 시간동안 네트워크를 사용하지 않아도 됨
  - 비싼 네트워크 사용량을 줄일 수 있음
  - 브라우저 로딩 속도가 매우 빠름
- 세 번째 요청_ 캐시 시간 초과 시
  - ![image](https://user-images.githubusercontent.com/102513932/199157728-6afb52bf-5523-42da-8c75-e931baaef808.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199157743-44f07ced-aa22-4e20-99c2-58a85f8c7b9e.png)
    - 캐시 유효 시간 초과 시, 서버를 통해 데이터를 다시 조회한 후 캐시를 갱신
      - 이때 다시 네트워크 다운로드가 발생
      - 불필요한 리소스 사용을 줄일 수 있는가? -> 검증 헤더 사용
## 검증 헤더와 조건부 요청1
- 캐시 유효 시간이 초과해 서버에 다시 요청하면 두 가지 상황 발생 가능
  - 1. 서버에서 기존 데이터를 변경
  - 2. 서버에서 기존 데이터 변경X
### 캐시 만료후에도 서버에서 데이터를 변경하지 않았을 때
  - 데이터를 전송하는 대신 저장해 두었던 캐시 재사용 가능
  - 단, 클라이언트의 데이터와 서버의 데이터가 같다는 사실을 확인할 수 있는 방법 필요
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199158048-1370f5ed-8651-4ea4-be0c-39ca3ec85030.png)
    - Last-Modifed *검증 헤더* 추가
    - 응답 결과를 캐시에 저장
- 두 번째 요청_ 캐시 시간 초과
  - ![image](https://user-images.githubusercontent.com/102513932/199158255-18ad637a-6031-4332-83c2-bad7ad23e366.png)
    - 요청에 캐시가 갖고있는 데이터 최종 수정 시점 포함
      - if-modified-since *조건부 요청* 추가
  - ![image](https://user-images.githubusercontent.com/102513932/199158340-a6be5d37-e230-4496-a17e-982e21b0ab08.png)
    - 서버가 갖고 있는 데이터와 비교
  - ![image](https://user-images.githubusercontent.com/102513932/199158429-d311d494-74ac-439c-8b22-ba71d41ddeaa.png)
    - 304 Not Modified 응답
    - 최종 수정일이 같다면 **HTTP BODY를 제외**하고, 헤더만 보냄
      - 리소스를 줄일 수 있음
    - 브라우저 캐시 최신화 (응답 결과 사용, 헤더 데이터 갱신)
      - 이후 웹 브라우저에서 캐시 사용
### 정리
  - 캐시 유효 시간이 초과해도 서버의 데이터가 갱신되지 않았다면 304 Not Modified + 헤더 메타 정보만 응답(바디 제외)
  - 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보 갱신
  - 이후 캐시가 유효할 때까지 클라이언트는 캐시에 저장되어 있는 데이터 재활용
  - 결과적으로 네트워크 다운로드는 발생하지만, 용량이 비교적 적은 헤더 정보만 다운로드
  - 매우 실용적인 해결책임!
## 검증 헤더와 조건부 요청2
- 검증 헤더
  - 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터
  - Last-Modified, ETag
- 조건부 요청 헤더
  - 검증 헤더로 조건에 따른 분기
  - If-Modified_Since: Last-Modified와 같이 사용
  - If-None-Match: ETag와 같이 사용
  - 조건이 만족하면 200 OK
  - 조건 불만족 시 304 Not Modified 
### 조건부 요청 헤더 예시
  - If-Modified-Since: 이후 데이터 수정된다면?
  - 데이터 미변경 시
    - 캐시: 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 10:00:00
    - 304 Not Modified, 헤더 데이터만 전송(BODY 미포함)
      - 캐시로 리다이렉션
    - 전송 용량 0.1M(헤더 0.1M, 바디 1.0M)
  - 데이터 변경 시
    - 캐시: 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 *11*:00:00
    - 200 OK, 모든 데이터 전송(BODY 포함)
    - 전송 용량 1.1M(헤더 0.1M, 바디 1.0M)
### Last-Modified, If-Modified-Since 단점
  - 1초 미만(0.x초) 단위로 캐시 조정 불가능
  - 날짜 기반의 로직 사용
    - 데이터를 수정해서 날짜값이 다르지만, 같은 데이터를 다시 수정해 최종적인 데이터 결과가 똑같은 경우
      - 날짜는 갱신됐지만 데이터는 변경되지 않았을 수 있음
      - 하지만 전체 데이터를 다시 다운로드 받음
  - 서버에서 별도의 캐시 로직을 관리하고 싶은 경우 -> ETag 사용
    - ex) 스페이스나 주석처럼 크게 영향이 없는 변경에서 캐시를 유지하고 싶은 경우
### ETag, If-None_Match
  - ETag(Entity Tag)
  - 캐시용 데이터에 임의의 고유한 버전 이름을 달아둠
    - ex) ETag: "v1.0", ETag: "a2jiodwjekjl3"
  - 데이터 변경 시 이 이름을 바꿔서 변경(Hash 다시 생성)
    - ex) ETag: "aaaaa" -> Etag: "bbbbb"
  - 단순하게 ETag만 보내서 같으면 유지, 다르면 다시 받음
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199159916-c79815cb-65f4-4951-9b90-c2d03ab4e514.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199160006-808f0098-62ec-4523-b734-429d6dc8716e.png)
- 두 번째 요청_캐시 시간 초과
  - ![image](https://user-images.githubusercontent.com/102513932/199160416-45ad163f-b0d7-4404-a4c9-f98c913d997a.png)
    - ETag와 함께 요청 전송
  - ![image](https://user-images.githubusercontent.com/102513932/199160446-533a2ec4-292f-4eef-ac43-a7229bd9efb4.png)
    - 서버 데이터 ETag와 비교
  - ![image](https://user-images.githubusercontent.com/102513932/199160773-f4926568-6665-4e58-8e69-51b157897dff.png)
    - HTTP 헤더만 응답으로 전송
    - 브라우저 캐시에서, 응답 결과를 재사용하여 헤더 데이터 갱신
    - 캐시 상태 유효해짐 -> 이후 데이터 캐시에서 조회 가능
### 검증 헤더 정리
  - ETag, If-None-Match 
  - ETag만 서버에 보내 같으면 유지, 다르면 다시 받음
  - 캐시 제어 로직을 서버에서 완전히 관리
  - 클라이언트는 단순히 값을 서버에 제공(클라이언트는 캐시 매커니즘을 모름)
  - ex)
    - 서버는 배타 오픈 기간인 3일 동안 파일이 변경되어도 ETag을 변경하게 유지
    - 애플리케이션 배포 주기에 맞춰 ETag 모두 갱신
## 캐시와 조건부 요청 헤더
- 캐시 제어 헤더
  - Cache-Control: 캐시 제어
  - Pragma: 캐시 제어(하위 호환)
  - Expires: 캐시 유효 기간(하위 호환)
### Cache-Control
- 캐시 지시어(directives)
  - Cache-Control: max-age
    - 캐시 유효 시간, 초 단위
  - Cache-Control: no-cache
    - 데이터는 캐시해도 되지만, 캐시를 쓰기 전에 조건부 요청을 통해 항상 원(origin) 서버에 검증하고 사용
      - 중간 서버가 아닌 원서버에서 확인해야함!
      - 데이터가 바뀌지 않았을때만 사용 가능
  - Cache-Control: no-store
    - 데이터에 민감한 정보가 있으므로 저장하면 안됨
      - 메모리에서 사용 후 최대한 빨리 삭제
### Pragma
- 캐시 제어(하위 호환)
  - Pragma: no-cache
  - HTTP 1.0 하위 호환
    - 지금은 거의 사용하지 ㅇ낳음
### Expires
- 캐시 만료일 지정(하위 호환)
  - expires: Mon, 01 Jan 1990 00:00:00 GMT
  - 캐시 만료일을 정확한 날짜로 지정
  - HTTP 1.0부터 사용
  - 지금은 더 유연한 Cache-Control: max-age 권장
    - 초단위가 훨씬 유연하게 사용 가능함
  - Cache-Control: max-age와 함께 사용 시 Expires는 무시
### 검증 헤더와 조건부 요청 헤더
- 검증 헤더(Validator)
  - ETag: "v1.0", ETag: "asid93jkrh2l"
  - Last-Modified: Thu, 04 Jun 2020 07:19:24 GMT
- 조건부 요청 헤더
  - If-Match, If-None-Match: ETag 값 사용
  - If-Modified-Since, If-Unmodified-Since: Last-Modified 값 사용
## 프록시 캐시
- 원 서버 직접 접근
  - ![image](https://user-images.githubusercontent.com/102513932/199164118-e676f588-a749-459c-8518-8d6fbede106c.png)
- 프록시 캐시 도입
  - 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199164206-33783853-6960-47d3-9c2d-6f119add730d.png)
    - 프록시 캐시 서버를 통해 더 빠르게 접근할 수 있게됨
### Cache-Control
- 캐시 지시어(directives)_기타
  - Cache-Control: public
    - 응답이 public 캐시에 저장되어도 됨
  - Cache-Control: private
    - 응답이 해당 사용자만을 위한 것임, private 캐시에 저장해야 함(기본값)
  - Cache-Control: s-maxage
    - 프록시 캐시 서버에만 적용되는 max-age
  - Age:60 (HTTP 헤더)
    - 오리진 서버에서 응답 후 프록시 캐시 내에 머문 시간(초)
## 캐시 무효화
### Cache-Control
- 확실한 캐시 무효화 응답
  - 캐시 요청을 안해도, 웹 브라우저 임의로 캐시를 적용하는 경우가 있음
    - 이를 방지하기 위함
  - Cache-Control: no-cache, no-store, must-revalidate
  - Pragma: no-cache
    - HTTP 1.0 하위 호환
- 캐시 지시어(directives)_확실한 캐시 무효화
  - Cache-Control: no-cache
    - 데이터는 캐시해도 되지만, 항상 원 서버에 검증하고 사용
  - Cache-Control: no-store
    - 데이터에 민감한 정보가 있으므로 저장하면 안됨
      - 메모리에서 사용하고 최대한 빨리 삭제
  - Cache-Control: must-revalidate
    - 캐시 만료후 최초 조회 시 원 서버에 검증해야함
    - 원 서버 접근 실패 시 반드시 오류가 발생해야함 - 504(Gateway Timeout)
    - must-revalidate는 캐시 유효 시간이라면 캐시를 사용
  - Pragma: no-cache
    - HTTP 1.0 하위 호환
### no-cache vs must-revalidate
- no-cache 기본 동작
  - ![image](https://user-images.githubusercontent.com/102513932/199164903-e0cff1cd-d3f8-4604-82b2-4351d4888534.png)
- no-cache
  - ![image](https://user-images.githubusercontent.com/102513932/199164942-ce335573-2ad4-4686-b765-a2d159cb590b.png)
    - 원 서버 접근 불가 시 오류 발생이 아닌, 오래된 데이터라도 보여주는 응답 정책
- must-revalidate
  - ![image](https://user-images.githubusercontent.com/102513932/199164974-3ca1ed79-e65a-4018-8fdd-7b1ffa010659.png)
    - 원 서버 접근 불가 시 오류 발생시킴 
