### URL
- URL(Uniform Resource Locator)
  - 서버가 제공되는 환경에 존재하는 파일의 위치를 나타냄
  - ex) https://codestates.com:443
    - codestates.com 주소가 가리키는 서버의 기본 폴더를 뜻함
    - 슬래시(/)를 이용해 서버의 폴더에 진입하거나 파일 요청 가능
  - ![image](https://user-images.githubusercontent.com/102513932/193194453-099bee46-8870-426e-a096-15bf96e2a94f.png)
  - scheme, hosts, url-path로 구분 가능
    - scheme
      - 통신 방식(프로토콜) 결정
      - 일반적인 웹 브라우저에서는 http(s) 사용
    - hosts
      - 웹 서버의 이름이나 도메인, IP를 사용하며 주소를 나타냄
    - url-path
      - 웹 서버에서 지정한 루트 디렉토리에서 시작, 웹 페이지, 이미지 ,동영상 등이 위치한 경로와 파일명을 나타냄.
  - URI(Uniform Resource Identifier)
    - 브라우저의 검색창을 클릭하면 나타나는 주소
    - URI는 URL을 포함하는 상위개념
    - URL의 기본 요소인 scheme, hosts, url-path에 더해 query, bookmark를 포함
    - query
      - 웹 서버에 보내는 추가적인 질문
      - ex) ```http://www.google.com:80/search?q=Java``` 입력 시 , 구글에서 Java 검색 결과 나옴
    - ![image](https://user-images.githubusercontent.com/102513932/193195146-52450c21-1975-409b-bd77-9604deec78d2.png)

### Domain
- 숫자로만 구성된 IP 주소의 단점을 보완하기 위해 사용, 로마자로 나타낸 인터넷 사이트 주소
  - ![image](https://user-images.githubusercontent.com/102513932/193195325-6506de43-086b-4269-aa74-2e3eb6b61b2d.png)
- 도메인 관리
  - ![image](https://user-images.githubusercontent.com/102513932/193195599-83e9531b-34ab-45ff-9f26-197e9e4b2c8b.png)
  - ICANN이라는 비영리 단체에서 4억개의 도메인 관리
  - Registry : 도메인 관리 기관
    - 각 도메인 정보의 데이터베이스 관리, registry에 따라 도메인 종류가 달라진다.
  - Registrar : 중개 등록 업체
    - Registry의 데이터베이스에 직접 도메인 정보 등록 가능.

### 도메인 종류
- gTLD(generic Top Level Domain)
  - 전세계에서 등록 가능
    - .com, .net, .org, .edu, .gov, .int, .mil, .biz, ..
  - reistrar
    - VERISIGN, 가비아, 후이즈
- ccTLD(country code Top Level Domain)
  - 각국 네트워크 정보센터에서 위임받아 관리
    - .kr, .us, .jp, ..
  - registy
    - 한국인터넷진흥원
  - registrar
    - 가비아, 후이즈


### DNS
- Domain Name System
  - 호스트의 도메인 이름을 IP주소로 변환 혹은 IP주소를 도메인 이름으로 변환할 수 있도록 개발된 데이터베이스 시스템
- 모든 PC는 IP주소가 있지만, 모든 IP주소가 도메인 이름을 갖는건 아님
  - 로컬 PC를 나타내는 127.0.0.1은 localhost로 사용할 수 있지만, 그 외 모든 도메인 이름은 일정 기간동안 대여
  - 대여한 도메인 이름과 IP 주소는 별도 서머에서 매칭
- ![image](https://user-images.githubusercontent.com/102513932/193202810-06de3ba7-d497-4811-ab74-1676e7eeb75c.png)
  - 브라우저 검색창에 naver.com 입력시, 입력은 DNS에서 IP주소(125.209.222.142)를 찾음
  - IP 주소에 해당하는 웹 서버로 요청을 전달, 클라이언트와 서버가 통신할 수 있도록 함.
- #### Domain Name
  - ```sub-domain.domain.TLD.```
    - Top Level Domain
      - .com, .r, .net등 도메인의 가장 오른쪽에 위치
      - kr,us와 같은 국가 코드를 사용하는 도메인은 co,ac와 같은 2단계 도메인과 함께 사용 가능
    - sub-domain
      - 서브도메인은 일반적으로 `www, m`와 같은 제일 왼쪽에 위치한 도메인들 해당
      - 호스트의 이름으로 불리기도 함
      - 웹사이트의 특정 부분을 나눠서 보여줘야 하는 경우 사용 
      - m(모바일), www(기본), store(스토어)등의 도메인에 따라 사이트의 구성이 달라짐
- #### Domain Name Server(zone)
  - 안정성을 위해 최소한 두 개 이상의 서버가 하나의 도메인 네임 담당
    - 과부하 및 서비스 거부 공격에 대해 효율적 대응 가능
  - Root 네임 서버
    - 각 최상위 도메인 네임 서버들의 주소를 알고 있음
      - 최상위 도메인 네임 서버는 권한 있는 네임 서버의 주소를 알고 있음
  - TLD 네임 서버
    - Top-Levle domain, 도메인 등록 기관에서 관리하는 서버
  - 권한 있는 네임 서버
    - `example.com` 등의 도메인 IP 주소 및 도메인 정보를 관리하는 권한을 가진 서버
