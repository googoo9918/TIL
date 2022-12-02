# AWS
## Cloud Computing
### Cloud
- 서버의 자원과 공간, 및 네트워크 환경 제공
  - 필요할 때 마다 컴퓨팅 능력 유연하게 조절 가능
  - 필요한 요금만 지불(온디맨드)
  - 다른 컴퓨터로 즉시 이주 가능
    - 스냅샷 이용
### Cloud 단점
- 운영 환경 자체가 클라우드 제공자에게 종속
  - 클라우드 서비스에 문제 생길 시 운영 환경에도 문제가 생기

### Saas, Paas, Iaas
- ![image](https://user-images.githubusercontent.com/102513932/205220836-646962f4-74dd-4315-8af9-10e7908036cc.png)
  - Saas(Software as a Service)
    - 당장 사용 가능한 소프트웨어 제공
    - 마이크로 소프트 오피스
  - Paas(Platform as a Service)
    - DB, 개발 플랫폼까지 제공하는 경우
    - Google App Engine
  - Iaas(Infrastructure as a Service)
    - 가상 컴퓨터까지 제공
    - AWS

## Deploy
![image](https://user-images.githubusercontent.com/102513932/205221340-ddf234ca-d4b0-42eb-83ee-56e59e52fe9b.png)
![image](https://user-images.githubusercontent.com/102513932/205221377-684fba7d-f570-43cc-8d42-72967b7ff1bf.png)
- Development 환경과 Production 환경은 서로 다를 수 있음
  - 버전, 비밀번호, 인증 정보, DB 접근 엔드포인트 등 모두 제각각
- 배포에서는 **환경 설정을 코드와 분리해야함**
  - 절대경로 대신 상대경로 사용
  - 환경에 따라 포트를 분기할 수 있도록 환경변수 설정
    - '.properties'등 이용
  - Docker와 같은 개발 환경 통일 솔루션 사용
    - 환경 자체를 메타 데이터로 담아서 모든 개발 환경을 통일시킴
- 어떠한 인증정보도 유출시키지 않고 코드가 오픈 소스가 될 수 있다면, 모든 설정이 정상적으로 코드 바깥으로 분리되어 있는지 확인 가능

## EC2(Elastic Compute Cloud)
![image](https://user-images.githubusercontent.com/102513932/205221979-3e38f8a1-4dd2-4246-b805-fefcf2d26fe7.png)
- AWS에서 제공하는 클라우드 컴퓨팅 서비스
  - 아마존에서 가상의 컴퓨터를 한 대 빌린다 생각
- 인스턴스는 한 대의 컴퓨터를 의미하는 단위, AWS에서 컴퓨터를 빌리는 것을 인스턴스를 생성한다 지칭
- 장점
  - 구성하는 데 필요한 시간이 짧음
  - 다양한 운영체제에 대한 선택 가능
    - 운영체제 뿐 아니라 CPU, RAM, 용량까지도 손쉽게 구성 가능
- AMI(Amazon Machine Image)
  - ![image](https://user-images.githubusercontent.com/102513932/205222474-261a0070-ce09-45a5-9e43-d732e4489413.png)
  - 소프트웨어 구성이 기재된 템플릿
    - 단순 운영체제만 깔려있는 템플릿 선택 가능
    - 특정 런타임이 설치되어 있는 템플릿이 제공되기도 함 (우분투 + node.js, 윈도우 + JVM 등)
  - Instatnce는 선택한 AMI를 토대로 구성됨
- EC2 인스턴스 생성의 의미
  - ![image](https://user-images.githubusercontent.com/102513932/205222697-eaa21904-06ca-4be3-84cf-1e7edd982613.png)
    - AMI를 토대로 운영체제, CPU, RAm 혹은 런타임 등이 구성된 컴퓨터를 빌리는 것
### RDS(Relational Database Service)
- ![image](https://user-images.githubusercontent.com/102513932/205222830-fdf60569-3687-4db8-91dc-c033c3d64e71.png)
  - AWS에서 제공하는 관계형 DB 서비스
- RDS 사용 이점
  - EC2 인스턴스에 DB 설치하는 경우
  - ![image](https://user-images.githubusercontent.com/102513932/205223170-30e282ca-c0e2-4abb-b543-9048df391575.png)
    - DB 관련해 자동으로 관리를 담당하는 부분이 매우 적음
    - 사용자가 직접 DB엔진의 설치와 버전 관리, DB 백업을 해야함
    - 가용성과 내구성이 확보되지 않아 DB에 저장된 데이터 유실 가능성 증가, 정상적으로 사용하지 못할 확률이 커짐
      - 추후 필요에 따라 DB 규모 확장 불리함
  - RDS를 이용하는 경우
    - ![image](https://user-images.githubusercontent.com/102513932/205223126-92e4c4cc-47ee-40a8-a10d-83c39966e8ba.png)
      - DB 유지보수와 관련된 일들을 RDS에서 전적으로 자동 관리
      - 초기 설정을 제외하고 DB에 저장된 데이터를 관리하기만 하면 됨
      - DB 엔진을 취사 선택하여 이용 가능

### S3(Simple Storage Service)
- 클라우드 스토리지
  - 인터넷 공간에 데이터를 저장하는 저장소
- S3
  - ![image](https://user-images.githubusercontent.com/102513932/205223440-03a76796-30df-4bbb-bb1b-2a5341c2fbc6.png)
    - AWS에서 제공하는 클라우드 스토리지 서비스
- S3 사용 시 이점
  - 뛰어난 접근성
  - 높은 확장성
    - 용량 무한히 확장 가능
    - 사용한 만큼반 비용 지불
      - 비용적인 측면에서 매우 효율적
  - 강력한 내구성
    - 파일 유실 가능성 감소
  - 가용성 보장
    - 스토리지에 저장된 파일을 정상적으로 사용할 수 있는 시간이 길어짐
  - 다양한 스토리지 클래스 제공
    - Standard 클래스
      - 데이터에 빠른 속도로 접근
      - 엑세스 요청에 대한 처리 속도가 빠름
      - 보관 비용이 비쌈
        - 오래 보관하는 목적으로는 효율적이지 않음
    - Glacier 클래스
      - 데이터에 엑세스하는 속도 느림
      - 데이터를 보관하는 비용이 저렴함
    - 정적 웹 사이트 호스팅 가능
      - ![image](https://user-images.githubusercontent.com/102513932/205224013-8df6eb12-9c28-4653-ac28-e0252f9bb199.png)
    - 버킷
      - 파일을 담는 바구니(최상위 디렉토리)
      - 무한히 많은 파일 저장 가능
      - 이름은 각 리전(버킷이 생성된 지역)에서 고유함
      - 버킷의 정책을 생성하여 엑세스 권한을 부여 가능
    - 객체
      - 객체는 버킷에 담기는 파일
      - 객체는 파일과 메타데이터로 구성
        - 파일은 키-값 페어 형식으로 데이터 저장
        - 파일의 값은 실제 데이터 저장(최대 5TB)
        - 파일의 키는 각각의 객체를 고유하게 만들어주는 식별자 역할
          - 키를 이용해 원하는 객체 검색 가능
        - 메타데이터는 객체의 생성일, 크기, 유형과 같은 객체에 대한 정보가 담긴 데이터
      - 모든 객체는 고유한 키를 가짐
      - URL 주소를 통해 객체에 접근 가능
      - URL 주소 형식 : `http://[버킷의 이름].S3.amazonaws.com/[객체의 키]`
- 전 세계에 있는 리전(AWS에서 클라우드 서비스를 제공하기 위해 운영하는 물리적인 서버 위치)에 데이터 센터(IDC) 존재
  - 다른 가용 영역에 백업을 해놓은 데이터를 통해 AWS는 높은 가용성과 내구성을 보장함
  - EC2, RDS, S3가 높은 가용성과 높은 내구성을 보장할 수 있는 이유
  
## 3 Tier-Architecture 배포 전략
- Client 배포
  - ![image](https://user-images.githubusercontent.com/102513932/205225407-9d373ba2-b005-41de-880c-33deeba7aff6.png)
  - S3 서비스를 통해 사용자들에게 Client 제공
    - 클라이언트 앱을 정적 파일로 빌드하여 제공
      - 빌드: 불필요한 데이터를 없애고, 여러 데이터를 통합/압축하여 배포하기에 최적화된 상태를 만듬
        - 데이터의 용량이 줄고, 웹 사이트의 로딩 속도가 빨라짐
  - 사용자의 거리가 먼 경우
    - ![image](https://user-images.githubusercontent.com/102513932/205225664-bcbb7ad4-470c-4fd7-b371-f316a0bf9ad7.png)
      - AWS 제공 CDN 서비스인 CloudFront를 통해 각지의 데이터센터에 데이터를 분산시켜 저장함
        - 가까운 지역에서 데이터를 주는 방식으로 사용자에게 더 빠르게 서비스 제공
- Server Application 배포 
  - ![image](https://user-images.githubusercontent.com/102513932/205225982-377c2975-881e-48d8-b969-edc0b24b7196.png) 
  - 사용자들이 제공받은 Client Application을 통해 요청을 전달할 Server Application 배포
    - AWS EC2 서비스 이용
  - RDS를 통해 EC2를 통해 배포된 Server Application의 데이터를 저장, 제공하는 DB를 배포할 수 있음
  - ASW 제공 Route 53 서비스를 통해 직관적인 도메인 주소를 이용하여 서비스에 접근할 수 있음