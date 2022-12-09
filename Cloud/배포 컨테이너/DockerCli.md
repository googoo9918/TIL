### 목차
- [Docker CLI(Command Line Interface)](#docker-clicommand-line-interface)
- [도커 이용하기](#도커-이용하기)
  - [docker/whalesay](#dockerwhalesay)
  - [Docker Hub: docker/whalesay](#docker-hub-dockerwhalesay)
- [Docker CLI(2) - Copy, Dockerfile](#docker-cli2---copy-dockerfile)
  - [Docker 컨테이너에 파일을 복사](#docker-컨테이너에-파일을-복사)
- [Pacman 실습](#pacman-실습)
  - [httpd 웹 서버](#httpd-웹-서버)
- [Docker 이미지 만들기](#docker-이미지-만들기)
- [Dockerfile로 Docker Image 만들기](#dockerfile로-docker-image-만들기)

## Docker CLI(Command Line Interface)
- [Docker docs](https://docs.docker.com/engine/reference/commandline/container_run/)
- 사용법 : Docker CLI, Docker-Compose CLI, API Reference
- 환경 및 빌드 파일 구성 : DockerFile, Docker-Compose File

## 도커 이용하기
![image](https://user-images.githubusercontent.com/102513932/205793673-856b9d92-0b17-4bb0-8911-bc29e49bc303.png)
- 도커 레지스트리 -> 이미지 -> 컨테이너
### docker/whalesay
- `docker/whalesay`로 예제 실습
- `docker/whalesay`는 레지스트리 계정, 레포지토리 이름, 태그 세 가지 정보로 구성됨
- ![image](https://user-images.githubusercontent.com/102513932/205793798-0c7a0703-eee4-46b5-9fe1-ef1038a83430.png)
  - 레지스트리
    - [Docker hub](https://hub.docker.com/)
    - 도커 이미지를 관리하는 공간
    - 다른 것을 지정하지 않으면, 도커 허브를 기본 레지스트리로 설정됨
    - 레지스트리는 Docker Hub, Private Docker Hub, 회사 내부용 레지스트리 등이 있을 수 있음
  - 레포지토리
    - 레지스트리 내에 도커 이미지가 저장되는 공간
    - 이미지 이름이 사용되기도 함
  - 태그
    - 이미지 별 버전 정보
    - 특별히 다른 것을 지정하지 않으면 `latest` 태그가 적용됨
- `docker/whalesay:latest`
  - Docker Hub 레지스트리에서
  - docekr 라는 유저가 등록한 whalesay 이미지 혹은 레포지토리에서
  - latest 태그를 가진 이미지

### Docker Hub: docker/whalesay
- [Docker Hub](https://hub.docker.com/)
- [docker/whalesay 이미지 안내 페이지](https://hub.docker.com/r/docker/whalesay))
- `docker image pull docker/whalesay:latest`
  - 최신 이미지를 받아옴
  - `image pull` : 레지스트리에서 이미지 혹은 레포지토리를 가져옴
- `docker image ls`
  - 이미지 리스트를 출력
  - ![image](https://user-images.githubusercontent.com/102513932/205795156-b9e66227-4a62-4c1f-9bef-2f961f36bb65.png)
- ![image](https://user-images.githubusercontent.com/102513932/205794719-505b49ff-e6d2-4187-86b6-2fb53fb91d36.png)
  - 받아온 이미지 실행
- `docker container run --name 컨테이너_이름 docker/whalesay:latest cowsya boo`
  - run
    - 컨테이너를 실행
  - OPTIONS
    - -name: 컨테이너의 이름을 할당함
  - COMMAND
    - 초기 컨테이너 실행 시 수행되는 명령어
    - cowsay: 컨테이너 실행 시 cowsay 명령어를 호출함
  - ARG
    - boo : COMMAND인 cowsay에 넘겨질 파라미터임
  - ![image](https://user-images.githubusercontent.com/102513932/205794908-ce8fc70a-701d-489c-9314-b5f1a6c40843.png)
    - 정상 실행 시 터미널에 출력되는 화면
- `docker container ps -a`
  - 모든 컨테이너의 리스트 출력
  - ![image](https://user-images.githubusercontent.com/102513932/205795177-24515f45-7b7c-4441-abf4-3a139e9ec954.png)
  - ps: 컨테이너의 리스트를 출력
  - a: 종료된 컨테이너를 포함해 모든 컨테이너를 출력
    - Default는 실행중인 컨테이너
- `docker container rm 컨테이너_이름`
  - rm: 컨테이너를 지칭해 삭제함
    - 컨테이너 명시할 때는 ps 명령을 통해 확인할 수 있는 NAMES 혹은 CONTAINER ID를 사용함
    - ![image](https://user-images.githubusercontent.com/102513932/205797446-8de52de9-b56b-42a4-97eb-fcc45c9edf94.png)
- `docker image rm docker/whalesay`
  - docker/whalesay 이미지 지우기
  - ![image](https://user-images.githubusercontent.com/102513932/205797512-1fad4c05-4ef0-475b-b1dc-d26f52e53997.png)
- `docker container run --name 컨테이너_이름 --rm docker/whalesay cowsay boo`
  - 세 가지 작업을 한 번에 실행
  - run : 컨테이너를 실행
    - **이미지가 없으면 이미지를 받아온 뒤 실행함**
  - -rm
    - 컨테이너를 일회성으로 실행
      - 컨테이너가 중지되거나 종료될 때, 컨테이너와 **관련된 리소스를 모두 제거**
  - ex) `docker container run -it --rm danielkraic/asciiquarium:latest`
    - it: -i, -t를 동시에 사용한 옵션
      - 사용자와 컨테이너 간에 인터렉션 필요 시
      - 현재 예제에서는 출력되는 화면을 사용자가 지속적으로 보기 위해 사용됨
      - Python 명령이 필요하거나 추가로 다른 입력을 받을 때 사용
    - image가 없으면 이미지를 받아온(run) 뒤 컨테이너를 일회성(rm) 실행(run), 컨테이너 종료 시 컨테이너 제거(rm)
      - 이미지는 제거되지 않는 점 주의

## Docker CLI(2) - Copy, Dockerfile
- 다른 사람이 제공한 이미지를 사용하는 경우, 원하는 모든 기능이 구성되어 있지 않을 수 있음
- 도커 이미지에 파일을 추가하고 이미지를 만드는 방법을 학습

### Docker 컨테이너에 파일을 복사
- 사용하는 도구가 도커 이미지에 모두 구성되어 있지 않은 경우도 있음
- ex)
  - 웹 서버는 도커 컨테이너로 실행
  - 웹 서버를 구성하는 파일은 직접 만들거나 가져온 파일 구성
- 장점
  - 서버에 문제가 생기는 것을 호스트와 별개로 파악할 수 있음
  - 문제가 생긴 서버를 끄고, 공장 초기화를 하듯 도커 이미지로 서버를 재구동 할 수 있음 
- 로컬에 있는 파일과 도커 이미지를 연결하는 방법
  - CP(Cpoy) 이용
    - 호스트와 컨테이너 사이에 파일을 복사
  - Docker Volume 기능 이용
    - 호스트와 컨테이너 사이에 공간을 마운트
      - 마운트: 저장 공간을 다른 장치에서 접근할 수 있도록 경로를 허용
        - 하나의 저장 공간을 이용하는 것처럼 보이게 하는 작업

## Pacman 실습
- ![image](https://user-images.githubusercontent.com/102513932/205821830-6d9ec666-5bb0-43e9-b2d3-b4e1076d5650.png)

### httpd 웹 서버
- 사용할 도커 이미지는 httpd(http daemon)임
  - httpd: Apache HTTP Server를 실행할 수 있는 오픈소스 웹서버 소프트웨어
  - httpd는 `/usr/local/apache2/htdocs/` 경로에 웹 서버와 관련된 파일들이 저장되어 있으면 해당 파일을 기반으로 웹 서버가 실행되도록 함
- `git clone https://github.com/codestates-seb/be-pacman-canvas.git`
  - `codestates-seb/be-pacman-canvas.git` 레포지토리를 클론함
- `docker container run --name httpdserver(컨테이너 이름) -p 818:80 httpd` 
  - `docker container run`명령어로 httpd 실행
  - `-p` 옵션은 로컬호스트의 포트와 컨테이너의 포트를 연결함
    - 818포트가 로컬호스트의 포트, 80번은 컨테이너의 포트
  - httpd는 일정 시간 연결 기록이 없으면 서버 가동이 중지됨
    - 실행 중이던 도커 컨테이너 중지 시, 다시 실행할 것
    - ![image](https://user-images.githubusercontent.com/102513932/205827782-984326b9-1bf1-46fd-a706-6e60889ded95.png)
- `127.0.0.1:818` 혹은 `localhost:818`을 통해 웹 서버가 작동하고 있는지 확인
  - ![image](https://user-images.githubusercontent.com/102513932/205824475-3bfd7344-62f8-41b8-9638-6eea379b5bf6.png)
- 서버 정상 가동 확인 후, 새로운 터미널을 열어 `docker container cp` 명령어를 입력해 로컬호스트에 있는 파일을 컨테이너에 전달함
  - 경로 입력 시 상대 경로와 절대 경로를 주의해서 작업할 것
  - `docker container cp` 명령은 앞 경로의 파일을 뒤 경로에 복사함
```java
// 지정된 경로에서 명령어 입력

// C:\Users\LG\be-pacman-canvas\src\main\resources\templates
docker container cp ./ 컨테이너_이름(httpdserver):/usr/local/apache2/htdocs/

// C:\Users\LG\be-pacman-canvas\src\main\resources\static
docker container cp ./ 컨테이너_이름(httpdserver):/usr/local/apache2/htdocs/
```
- ![image](https://user-images.githubusercontent.com/102513932/205828126-d2416439-ce30-49d9-af3a-66f72f9f5fce.png)
- `127.0.0.1:818` 혹은 `localhost:818`에 접속해 게임 서버가 구동되는지 확인
  - ![image](https://user-images.githubusercontent.com/102513932/205826977-0519da7b-833a-47f7-903e-6f5505a232a5.png)

## Docker 이미지 만들기
- 앞서 제작한 Docker Container를 이미지 파일로 변환
- `docker container commit 컨테이너_이름(httpdserver) my_pacman:1.0`
  - 구동한 Dokcer Container를 commit
  - ![image](https://user-images.githubusercontent.com/102513932/205827558-52fe7136-a63d-4550-9508-5edbf565027e.png)
- 생성된 이미지를 900 포트에서 웹 서버로 구동
`docker run --name my_web2 -p 900:80 my_pacman:1.0`
- ![image](https://user-images.githubusercontent.com/102513932/205828690-1e8ef95a-f505-4e81-b889-81b18fb3da76.png)

## Dockerfile로 Docker Image 만들기
- Dockerfile은 이미지 파일의 설명서라고 생각할 것
  - 파일의 확장자는 없으며, 파일명은 `Dockerfile`임
```java
FROM httpd:2.4 
// 베이스 이미지를 httpd:2.4 로 사용합니다.
COPY ./ /usr/local/apache2/htdocs/ 
// 호스트의 현재 경로에 있는 파일을 생성할 이미지 /usr/local/apache2/htdocs/ 에 복사합니다.
```

- `docker build` 명령은, Dockerfile로 도커 이미지 파일을 생성함
```java
// --tag 는 name:tag 형식으로 이미지를 생성할 수 있습니다.
// 지정한 경로에 있는 Dockerfile을 찾아서 빌드합니다.
docker build --tag my_pacman:2.0 . # 
// "."을 명령어에 꼭 포함
```

- 생성된 이미지를 이용해 901 포트에 웹 서버 구동
```
docker run --name my_web3 -p 901:80 my_pacman:2.0
```

  `127.0.0.1:901` 혹은 `localhost:901`을 통해 웹 서버 작동 확인