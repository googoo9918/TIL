
## CORS
- [CORS(Cross-Origin Resource Sharing)](https://developer.mozilla.org/ko/docs/Web/HTTP/CORS)
  - 보안 상의 이유로, 브라우저는 스크림트에서 시작한 교차 출처 HTTP 요청을 제한함
    - 웹 애플리케이션은 자신의 출처와 동일한 리소스만 불러올 수 있음
    - 다른 출처의 리소스를 불러오려면 그 출처에서 올바른 CORS 헤더를 포함한 응답을 반환해야 함
  - CORS 체제는 브라우저와 서버 간의 안전한 교차 출처 요청 및 데이터 전송을 지원
    - 최신 브라우저는 `XMLHttpRequest` 또는 `Fetch` 같은 API에서 CORS를 사용하여 교차 출처 HTTP 요청의 위험을 완화함
  - ![image](https://user-images.githubusercontent.com/102513932/194309637-0bd0756e-d1b5-4ba3-a15c-0c2d526b635a.png)
    - 위 Image는 같은 도메인에 GET request를 보내니 아무 문제 없음
    - 아래 image는 domain-b에 요청을 하기 때문에 CORS가 전송됨 

### Simple request
### preflighted request
### redirect
- 위 3개 추후에 다시 정리할 것.. ㅠㅠ


