## 폐쇄망에서 gradle 빌드를 하며 생긴 문제점
1. 폐쇄망에서는 gradle을 가져올 수 없기 때문에, 인터넷망에서 의존성을 다운받고, 폐쇄망으로 옮기는 작업을 진행해야 한다.
- 이때, gradle 파일은 총 두 곳에서 관리된다.
    - 첫 번째는 Gradle user home
        - 홈 디렉토리(전역)의 gradle이다.
        - 주로 `C:\Users\DBInc\.gradle`에서 관리된다.
        - Settings/Build, Execution, Deployment/Gradle에서 지정할 수 있다.
        - 전역 설정 파일, 다운로드된 의존성 캐시, 로그 파일, Gradle 배포판 등이 존재
    - 두 번째는 프로젝트 디렉토리의 .gradle
        - 프로젝트 루트 밑에 존재한다.
        - 빋르 캐시, 증분 빌드를 위한 임시 파일, 프로젝트별 설정이 존재
    - 그런데, 실제 빌드 과정에선 프로젝트 디렉토리에서 홈 디렉토리에 있는 의존성을 바라보고 빌드가 실행된다.
        - 그리고 사실 프로젝트 자체도 폐쇄망에서 만들지 못하기 때문에(못하는건 아니지만 인터넷 망에서 진행하는 것이 좋다)
            - 인터넷 망 빌드 -> 폐쇄망으로 이동의 작업을 거쳤을 때 프로젝트 디렉토리의 .gradle은 이미 완성되어 있을 것이다.
    - 따라서 Gradle user home만 신경써주면 된다.
        - 이때, 굉장히 여러가지 뻘짓을 할 가능성이 높은데
            - 나는 gradle, gradlew 등을 직접 이용하여 여러 옵션을 추가하며 빌드를 진행하기도 했고
            - Gradle Distribution을 wrapper가 아니라 Local installation을 통해 진행을 해보기도 하고
            - 프로젝트 디렉과 홈 디렉의 gradle을 전부 libs 폴더에 몰아서 빌드를 해보기도 하였으나..
        - 결국 가장 핵심이 되는 부분은, **offline mode**를 설정하고, `gradle-wrapper.properties`를 수정하는 것이다.
            - ![Image](https://github.com/user-attachments/assets/3717d0b0-bf62-4cab-b285-cdea77e3bb82)
                - 토글에서 설정이 가능하다.
                - 이를 설정하지 않으면, gradle이 로컬 캐시에서 의존성을 가져오지 않고 계속 인터넷에서 dependencies를 가져오려고 하기 때문에 문제가 생긴다.
            - gradle-wrapper.properites는 보통 프로젝트를 생성하면 다음과 같이 생긴다
                ```
                <!-- Gradle 배포 파일이 저장될 기본 위치  -->
                distributionBase=GRADLE_USER_HOME
                <!-- Gradle 배포 파일이 저장될 경로 -->
                distributionPath=wrapper/dists
                <!-- 사용할 Gradle URL -->
                distributionUrl=https\://services.gradle.org/distributions/gradle-8.7-bin.zip
                <!-- Gradle Wrapper가 다운로드한 zip 파일을 저장하는 위치 -->
                zipStoreBase=GRADLE_USER_HOME
                <!-- 다운로드한 Gradle zip파일이 저장될 경로 -->
                zipStorePath=wrapper/dists
                ```
            - 여기서 뻘짓하지 말고, `distributionUrl`만 다음과 같이 바꿔주면 된다
                - `distributionUrl=file:///C:/Users/DBInc/gradle-8.13/gradle-8.13-bin.zip
                - 물론 해당 경로에 Gradle을 다운 받아서 -bin.zip 파일을 만들어서 넣어줘야 한다.
            - 이렇게 하면 빌드가 정상적으로 될 것이다.

- 그런데, 다음과 같은 사항은 생각해볼 필요가 있다.
    - 결국 이렇게 되면, 각 사용자 별로 문제가 생길 확률이 굉장히 높다
    - 따라서, 주로 **nexus**와 같은 private registry를 사용하여 dependency를 가져오는 방식이 주류인 것 같다.
`