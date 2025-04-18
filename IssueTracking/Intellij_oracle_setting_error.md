## 인텔리제이에서 오라클 Dependency를 가져오지 못해 생긴 문제점
- 프로젝트를 잘 진행하던 중, DB에 쿼리가 날라가는 순간 문제가 생기게 되었다.
- 현재 붙고 있는 DB는 EUC-KR 인코딩 방식을 사용하는 DB인지라, 인코딩 문제가 생긴 것이었음
- 따라서 최차장님과 설정을 비교해본 결과, 나는 Oracle Dependency의 ojdbc11을 사용하고 있었고, 최차장님은 ojdbc8을 사용하고 있었다.
    - 찾아보니, 최신 버전인 ojdbc11은 구식 버전인 EUC-KR 인코딩 방식을 지원하지 않을 확률이 높다고...
- 따라서 odjbc8로 변경해보니, 이번엔 다른 문제가 생겼다
    - 에러 로그를 저장해놓거나, 에러명이라도 기억해 놨으면 좋았을 것
- 최차장님께서 ojdbc8을 gradle이 가져오지 못해서, 직접 `java build path`를 하셨다고 말씀하여
    - runtimeOnly이던 jdbc:ojdbc8을 implementation으로 바꿔보자, 나도 해당 의존성을 제대로 가져오지 못하고 있는 것을 확인할 수 있었다
        - **왜??? 인지는 아직도 잘 모르겠다..**
    - 따라서 이를 추가하기 위해 프로젝트 루트/libs에 `ojdbc8-21.9.0.0.jar`를 추가하였다.
        - 이후 Project Structure에서
            - Libraries -> ojdbc8-21.9.0.0 추가
            - 까지 하면, Modules탭에 라이브러리가 올라와 있는 것을 볼 수 있다.
            - Export를 체크하고, Apply 및 OK 를 적용해주면 된다.
- 이후 실행을 해보니, 이번에도 인코딩 문제가 발생하였다(ㅠㅠ)
    - 로그를 잘 확인해보니, orai18n.jar가 필요한데 없다는 로그를 확인할 수 있었다.(ojdbc8-full을 다운받으면 된다)
    - 따라서 위와 같은 방식으로 추가해주었다.
- 이후 정상 실행 되었으나,
    - `java.nio.file.NoSuchFileException: C:\Users\DBInc\Project\visit\libs\orai18n-mapping.jar`
        - 위와 같은 에러 로그가 잔뜩 뜨긴 한다.
        - 다만, 정상 동작하고 실행에 문제는 없으니... 일단 넘어가기로 했다.

- 해결은 하였으나 풀리지 않는 문제가 두 개나 남아 있다 ㅠㅠ