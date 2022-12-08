## 개발 환경 구축
- EC2 인스턴스 접속 - 세션매니저
- **1. JAVA 설치**
  - `sudo apt update`
    - 패키지 정보 최신 상태로 업데이트
  - `sudo apt install openjdk-11-jre-headless`
    - java 11 설치
  - `java -version`
    - 설치 확인
- **2. AWS CLI 설치**
  - `curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"`
  - `sudo apt install unzip`
  - `unzip awscliv2.zip`
  - `sudo ./aws/install`
  - `aws --version`
    - ASW CLI 설치 여부 확인
- **3. CodeDeploy Agent 설치**
  - `sudo apt install ruby-full`
  - `sudo apt install wget`
  - `cd /home/ubuntu`
  - `sudo wget https://aws-codedeploy-ap-northeast-2.s3.ap-northeast-2.amazonaws.com/latest/install`
  - `sudo chmod +x ./install`
  - `sudo ./install auto > /tmp/logfile`
  - `sudo service codedeploy-agent status`
    - 서비스 실행중인지 확인
## Github Actions
- Github가 공식적으로 제공하는 빌드, 테스트 및 배포 파이프라인을 자동화할 수 있는 CI/CD 플랫폼
- 레포지토리에서 `Pull Request`나 `push`를 트리거로 작업 워크플로우를 구성할 수 있음
  - Workflow: 하나 이상의 작업이 실행되는 자동화 프로세스
    - 워크플로는 `.yml` 파일에 의해 구성
    - `.github/workflows` 디렉토리 이하에 위치함
  - private 레포는 용량과 시간이 제한됨
  - public 레포는 무료 사용 가능

## Github Actions를 통한 배포 Flow
- ![image](https://user-images.githubusercontent.com/102513932/206486667-c5f3c5f5-d61d-4130-b817-29c8220796ed.png)

- **Github Actions**
  - 설정 파일(`.yml`)에 따라 레포지토리에 특정 변동사항을 트리거로 작동됨
    - 이 페이지에서는 `main` 브랜치에 `push` 하는 경우 작동하도록 설정
  - `main` 브랜치에 적용된 변동 사항을 기준으로 프로젝트를 빌드
    - 빌드를 마친 프로젝트를 AWS의 S3 버킷에 저장, Code Deploy에 S3에서 EC2로 배포 명령을 내림
- **S3**
  - 저장소로써 사용
  - Github Actions에서 빌드한 결과물이 압축되어 S3으로 전송되고, 버킷에 저장됨
- **Code Deploy**
  - Github Actions로부터 배포 명령을 받은 Code Deploy는 S3에 저장되어있는 빌드 결과물을 EC2 인스턴스로 이동함
- **EC2**
  - Code Deploy에 의해 빌드 과정을 거친 프로젝트가 EC2 인스턴스로 전달됨
    - `.yml`(설정 파일)과 `.sh`(쉘 스크립트)에 의해 각 배포 결과를 로그로 저장 후 빌드 파일(`.jar`)을 실행함

## 리소스 설정하기
- Public Repository 생성
  - ![image](https://user-images.githubusercontent.com/102513932/206491570-6735ca10-c9e4-4290-a6f0-dc1d72150eb0.png)
- Github Actions 생성
  - ![image](https://user-images.githubusercontent.com/102513932/206491702-edc6fb27-7963-40be-9c6c-9cdc8c390dce.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206491836-771252e3-51aa-4a61-b557-4ee9775b6fdc.png)
    - 워크플로 생성, 빈 yml 파일로 설정 혹은 추천 워크플로 구성 선택
  - ![image](https://user-images.githubusercontent.com/102513932/206492310-4320e6db-f97e-44c3-addc-bc42fc4a08f8.png)
    - 현재 수정사항 Commit하고 Push 시 Github Actions 바로 실행
  - ![image](https://user-images.githubusercontent.com/102513932/206492339-f40eb13c-43e8-4c9e-9725-cbfeb74670f3.png)
    - Actions 탭에서 워크플로 단계별 진행상황 확인 가능
  - ![image](https://user-images.githubusercontent.com/102513932/206492389-40d00ec5-807c-4d6e-bade-17f79571ee99.png)
    - 성공 시 초록색 체크, 실패 시 X 모양
- Github Action 수정
  - S3 버킷에 프로젝트 빌드한 결과물 전송
- Github Secret 등록
  - ![image](https://user-images.githubusercontent.com/102513932/206493212-29bb449c-304b-4cac-a2c3-34183b301fe4.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206493249-a834cae2-d79e-4a2f-bbad-b4a45b19053e.png)
    - IAM User 생성 시 볼 수 있는 액세스 키 ID 값 입력
  - ![image](https://user-images.githubusercontent.com/102513932/206493299-74e9790f-c68e-47a3-9904-026619c846c7.png)
    - 비밀 액세스 키 값 입력
  - ![image](https://user-images.githubusercontent.com/102513932/206493722-1216b692-b5b4-4097-ad9f-f347f29c5306.png)
    - 등록 완료
- Github Action 설정파일 수정
```yml
name: Java CI with Gradle

on:
  push:
    branches: [ "main" ]

permissions:
  contents: read
  
env:
  S3_BUCKET_NAME: be-0-name
#  이름 설정 주의
jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    - name: Run chmod to make gradlew executable
      run: chmod +x ./gradlew

    - name: Build with Gradle
      uses: gradle/gradle-build-action@67421db6bd0bf253fb4bd25b31ebb98943c375e1
      with:
        arguments: build
    
    # build한 후 프로젝트를 압축합니다.
    - name: Make zip file
      run: zip -r ./practice-deploy.zip .
      shell: bash
    
    # Access Key와 Secret Access Key를 통해 권한을 확인합니다.
    # 아래 코드에 Access Key와 Secret Key를 직접 작성하지 않습니다.
    - name: Configure AWS credentials
      uses: aws-actions/configure-aws-credentials@v1
      with:
        aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY }} # 등록한 Github Secret이 자동으로 불려옵니다.
        aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }} # 등록한 Github Secret이 자동으로 불려옵니다.
        aws-region: ap-northeast-2
    
    # 압축한 프로젝트를 S3로 전송합니다.
    - name: Upload to S3
      run: aws s3 cp --region ap-northeast-2 ./practice-deploy.zip s3://$S3_BUCKET_NAME/practice-deploy.zip 
    # 버킷 이름 설정 주의

    # CodeDeploy에게 배포 명령을 내립니다.
    - name: Code Deploy
      run: >
        aws deploy create-deployment --application-name be-0-name
        --deployment-config-name CodeDeployDefault.AllAtOnce
        --deployment-group-name be-0-name-group
        --s3-location bucket=$S3_BUCKET_NAME,bundleType=zip,key=practice-deploy.zip
appspec.yml
# be-0-name 이라 써져있는 두 부분 수정 요망
```

## 빌드파일 배포 및 실행
- CodeDeploy 애플리케이션 생성
  - ![image](https://user-images.githubusercontent.com/102513932/206494853-cbbca7b1-9e19-417a-98b6-d02a1b448fc3.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206494878-3f0bdbd1-3f6b-47dd-b843-3370f1b7b5e9.png)
- 배포 그룹 생성
  - ![image](https://user-images.githubusercontent.com/102513932/206494945-51d263e5-8fa6-47c5-87a9-f1b37fb270be.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206495090-50a9f3ae-d485-4c4d-8fc0-ab8ff05a2a34.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206495147-21c5f9ae-89af-4a53-8da7-b22544aede21.png)
  - ![image](https://user-images.githubusercontent.com/102513932/206495184-fb996322-6afd-4125-8463-b28492f42e4c.png)
- .yml 파일 설정
  - 최상위 디렉토리에 `appspec.yml` 파일 생성
```yml
version: 0.0
os: linux
files:
  - source:  /
    destination: /home/ubuntu/action
    overwrite: yes

permissions:
  - object: /
    pattern: "**"
    owner: ubuntu
    group: ubuntu

hooks:
  ApplicationStart:
    - location: scripts/deploy.sh
      timeout: 60
      runas: ubuntu
```
  - scripts 폴더 생성 후 디렉토리 내부에 `deploy.sh` 파일 생성
```sh
#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/action/build/libs/practice-githubAction-deploy-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)

echo "> 현재 시간: $(date)" >> /home/ubuntu/action/deploy.log

echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/action/deploy.log

echo "> build 파일 복사" >> /home/ubuntu/action/deploy.log
DEPLOY_PATH=/home/ubuntu/action/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/action/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/action/deploy.log
else
  echo "> kill -9 $CURRENT_PID" >> /home/ubuntu/action/deploy.log
  sudo kill -9 $CURRENT_PID
  sleep 5
fi


DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/action/deploy.log
sudo nohup java -jar $DEPLOY_JAR >> /home/ubuntu/deploy.log 2>/home/ubuntu/action/deploy_err.log &
```

## 배포 결과 및 로그 확인
- ![image](https://user-images.githubusercontent.com/102513932/206496413-65771025-a295-4f89-996a-61ae32664201.png)
  - 배포 결과 확인 가능 (8080 포트 유의할 것)
- 혹은 `ps -ef | grep java` 를 통해 실행 여부 확인 가능

### 에러 발생 시
- ![image](https://user-images.githubusercontent.com/102513932/206496889-64f545e8-5fbc-4ebe-bc23-b985ac763f0c.png)
- ![image](https://user-images.githubusercontent.com/102513932/206496955-b1352888-dd89-4d89-abb9-baa3abb6e97b.png)
  - 체크, 세부 정보 보기 클릭
- ![image](https://user-images.githubusercontent.com/102513932/206497102-359abfcd-2130-476e-8788-373759e8bbd9.png)
  - `View events` 클릭
- ![image](https://user-images.githubusercontent.com/102513932/206497160-733159f7-6bf9-4869-9c33-1c3f7ce5f39a.png)
  - 오류 코드 -> ScriptMissing 클릭
- ![image](https://user-images.githubusercontent.com/102513932/206497202-201e2961-0399-48be-bfbe-940a1517af21.png)
  - 오류 코드 확인 가능