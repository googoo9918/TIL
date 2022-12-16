### 목차
- [Git 기본](#git-기본)
  - [Git 학습의 이유](#git-학습의-이유)
  - [필수 파일](#필수-파일)
    - [README.md](#readmemd)
    - [.gitignore](#gitignore)
    - [LICENSE](#license)
  - [다양한 Github기능](#다양한-github기능)
    - [Github Issue](#github-issue)
    - [Github Milestones](#github-milestones)
    - [Github Project(칸반)](#github-project칸반)
  - [1. Fork / Clone / Push / Pull / Pull Request](#1-fork--clone--push--pull--pull-request)
    - [Fork](#fork)
    - [Clone](#clone)
    - [Push](#push)
    - [Pull](#pull)
    - [Pull Request](#pull-request)
  - [2. to CLI? GUI?](#2-to-cli-gui)
  - [3. 설정 \& 프로젝트 관리 시작](#3-설정--프로젝트-관리-시작)
- [시간 여행하기](#시간-여행하기)
  - [변화를 타임캡슐에 담아 묻기](#변화를-타임캡슐에-담아-묻기)
  - [과거로 돌아가는 두 가지 방법](#과거로-돌아가는-두-가지-방법)
  - [여러 branch 만들어보기](#여러-branch-만들어보기)
  - [branch 병합하기](#branch-병합하기)
- [협업](#협업)
- [기타](#기타)
  - [문제 발생 시 참고할 flow chart](#문제-발생-시-참고할-flow-chart)
  - [Git 설정](#git-설정)
  - [도움말 보기](#도움말-보기)
  - [세팅 및 초기화](#세팅-및-초기화)
  - [Stage \& Commit](#stage--commit)
  - [Branceh \& Merge](#branceh--merge)
  - [비교 및 검사](#비교-및-검사)
  - [공유 및 업데이트](#공유-및-업데이트)
  - [히스토리 수정](#히스토리-수정)
  - [임시 저장](#임시-저장)
- [Coz' Git flow](#coz-git-flow)
  - [main 브랜치](#main-브랜치)
  - [dev 브랜치](#dev-브랜치)
- [보조 브랜치](#보조-브랜치)
  - [feature 브랜치](#feature-브랜치)


## Git 기본

### Git 학습의 이유
- GIT는 VCS(Version Control System)의 한 종류이다.
  - 프로젝트의 시간과 차원을 자유롭게 넘나들 수 있다.
  - 메모리 문제 뿐 아니라, 각종 복합적 문제 해결 가능.
- 특정 시점에 생성된 백업 복사본 : 스냅샷
  - 하나하나 스냅샷을 만들어 주는 작업 : commit
    - 이전 기록들에 대한 추적 가능
    - 버전 관리 + 협업에 유용
- Git/GitHub
  - Git은 로컬에서 버전을 관리해주는 프로그램이며, Github은 Git을 클라우드 방식으로 구현한 서비스이다.
  - ![image](https://user-images.githubusercontent.com/102513932/186949719-b48fc23a-ebaf-41df-ad45-42f7b9f718aa.png)
<br><br>

### 필수 파일
#### README.md
- 오픈소스 프로젝트에 들어갔을 때 가장 먼저 확인할 수 있는 정보
- 간단한 소개 페이지 처럼 작성 가능
- 프로젝트 이름, 핵심 기능 소개, 팀원 소개를 꼭 포함할 것
#### .gitignore
- gitignore dotfile은 igt으로 관리하지 않는 파일 모음임
  - 개인이 따로 관리해야 하는 중요한 secret token
  - 다른 동료와 공유할 필요가 없는 설정 파일
  - 그 외 공유할 필요 없는 파일을 기록
  - git이 이를 파악하지 않고, push 할 때도 github 레포에 push 되지 않음
#### LICENSE
- 해당 코드의 라이센스를 표기함
- 깃허브에 public하게 공개된 레포도 라이센스에 따라 사용을 할 수도, 못할 수도 있음
- 회사에서 사용하는 코드는 private로 관리하고 외부에 공개하지 않아 라이센스 정보를 따로 표기하지 않기도 함
- 하지만 모정의 이유로 public으로 공개된다면 LICENSE를 명확하게 표기해야 함

### 다양한 Github기능
- Issue
  - 프로젝트에 새로운 기능을 제안하거나, 버그를 찾아 제보하는 등의 프로젝트 이슈를 의미
- Milestone
  - 이정표 역할을 하며, 태스크 카드(Issue)를 그룹화하는 데 사용됨
  - Milestone에 연결된 태스크 카드(Issue)가 종료되면 Milestone마다 진행 상황이 업데이트 되는 것을 볼 수 있음
- Pull Request
  - 작업한 내용을 중요 git branch에 합칠 수 있는지 확인하는 요청
  - Pull Request에서 커밋한 코드를 따로 선택하여 해당 부분에 코멘트를 달 수 있음
- Project
  - Github 내에서 업무 관리를 해줄 수 있게 돕는 새로운 기능

#### Github Issue
- Issue 생성
  - ![image](https://user-images.githubusercontent.com/102513932/208006093-af501e3c-b463-45b0-bbc7-d7f7457449fe.png)
- 테스크 작성
  - ![image](https://user-images.githubusercontent.com/102513932/208006183-fb87dc42-e0b1-4ae9-b4bb-30f3848e13c1.png)
  - Assignes : 해당 테스크를 맡은 사람을 지정
  - Labels: 태스크 카드에 라벨링을 할 수 있음
  - Projects: Projects를 지정할 수 있음
  - Milestone: Milestone을 지정할 수 있음
  - 완료 후 Close issue
- Github Issue 템플릿 카드
  - 이슈 반복 생성 용이
  - Settings(레포지토리 Setting임) -> Features -> Issues -> Set up templates
    - Issues 체크가 안되어 있다면 체크하고 새로고침 할 것
    - 템플릿 제작 하면, 이유 생성 시 템플릿 그대로 사용 가능

#### Github Milestones
- 이슈, PR 그룹의 진척도를 확인하는데 사용함
  - 하나의 요구사항을 여러 개의 마일스톤으로 나눠도 되고, 여러 개의 마을스톤을 하나로 합쳐도 괜찮음
- 이슈의 양이 적으면 개별로 관리해도 전체 진척도를 알아보기 쉽지만 프로젝트가 커지고 많은 양의 이슈가 생기면 진척도를 알아보기 어려움
  - 여러 개의 이슈들을 각각의 마일스톤으로 그룹화
- Milestones 생성
  - `Issue` -> `Milestones` -> `New milestone(create a Milestone)`
- Milestones 세부 내용 작성
  - 이름, Due date, 설명 작성
- Milestones 생성 확인
  - ![image](https://user-images.githubusercontent.com/102513932/208007898-a94857c4-6593-4a55-8edc-b224d69389d6.png)
  - 계획에 알맞은 갯수만큼의 Milestone과 Task를 만들어 관리할 것

#### Github Project(칸반)
- 깃허브 Project는 작업을 계획하고 트래킹하는데 사용되는 도구
- Project 생성
  - `Projects` -> `New project` -> New project 클릭
  - 테이블 또는 보드를 선택한 후 Create 버튼 클릭
- Project 이름 및 접근 설정
  - 오른쪽 상단의 버튼을 눌러 Settings를 클릭
  - 프로젝트의 이름과 간단한 설명을 추가하고 (자동 저장)
  - 프로젝트 이름 변경 확인
  - 왼쪽 탭에 `Manage access` 클릭
  - Base Role을 Read로 설정
  - Invite collaborators에서 권한 설정 및 초대 가능
- Issue 연결
  - `#`으로 자신의 레포지토리를 찾음
  - 레포지토리 선택 시 issue나 PR을 선택 가능
    - Add multiple items를 통해 다중 선택 가능
  - 레포지토리에 작성한 issue들이 project에 추가됨
- Project 설정
  - 각 이슈마다 상태를 설정할 수 있음
  - 기본적으로 `Todo`, `In Progress`, `Done` 세 가지 상태가 있음
  - ![image](https://user-images.githubusercontent.com/102513932/208020015-210a0154-a1ef-48d0-a6d0-cd4e269d4e76.png)
    - Labels, PR, Reviewers, Repository, Milestone등 새로운 칼럼을 넣을 수 있음
  - ![image](https://user-images.githubusercontent.com/102513932/208020089-3b7305a5-07ae-4eb5-b8ce-c3ba51efa431.png)
    - 그룹 아이콘 클릭 시, 그룹으로 나눠서 볼 수 있음
  - ![image](https://user-images.githubusercontent.com/102513932/208020414-88c96704-6681-415d-932e-354c3aff2520.png)
    - 그룹 안에서도 Assigness, Status, Milestone, Reporistory 등으로 나눌 수 있음
  - ![image](https://user-images.githubusercontent.com/102513932/208020491-fec05dd7-264f-4925-bdaf-38c192b945f2.png)
    - 칸반보드로도 볼 수 있음
  - 설정 완료 시 우측 상단에 있는 `Save changes` 버튼 클릭
    - 자동 수정 가능성 존재
- Project 적용
  - Projects 탭에서 Add project 클릭 후 프로젝트 선택
    - ![image](https://user-images.githubusercontent.com/102513932/208020588-c5594cb3-4edd-44a0-9e51-aff7106ac527.png)
  - 프로젝트 적용 확인 가능
    - ![image](https://user-images.githubusercontent.com/102513932/208020672-f7e1ee78-df57-49f7-948b-9c2daaf3a341.png) 
  - Issue 생성 시 Projects를 지정하면 자동으로 트래킹 됨

### 1. Fork / Clone / Push / Pull / Pull Request
- - Remote Repository / Local Repository
  - ![image](https://user-images.githubusercontent.com/102513932/186948339-acbd45e7-418c-4e49-a442-1d177e2fe4c1.png)
  - Local Repository 하부 영역
    - 세 가지 상태 : Committed, Modified, Staged
      - ![image](https://user-images.githubusercontent.com/102513932/187073046-889aa7f0-dae4-4482-a4b8-296fc03f1981.png)
        - Untracked area : git이 관리하고 있지 않은 영역
        - Tracked area : git의 관리를 받고 있는 영역
          - unmodified
            - 기존에 commit했던 파일을 수정하지 않은 상태
          - modified
            - 기존에 commit했던 파일을 수정한 상태
          - staged
            - commit이 가능한 상태 (add를 통해)

    - git status로 상태 확인 가능
       - ![image](https://user-images.githubusercontent.com/102513932/187072370-4604d36d-d9c6-419c-a14c-e4f34e226c19.png)
    - ![image](https://user-images.githubusercontent.com/102513932/187072750-5423af51-397d-4d8a-a59b-c11d6eb9837c.png)
    - ![image](https://user-images.githubusercontent.com/102513932/187072769-cfd74d96-d6b6-4965-b3b9-b1dae5369850.png)
     - git restore
       - commit 되지 않은 local repository의 변경 사항 폐기 가능
       - ```git restore <파일명>```
     - git reset "HEAD^"
       - 가장 최신의 commit 취소 가능
       - HEAD는 연속된 ^의 shortcut
         - HEAD~3 == HEAD^^^
       - HEAD^ == HEAD~1
#### Fork
  - 다른 원격 저장소에 있는 코드를 내 원격 저장소로 옮기는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948530-598dff97-a9c8-4516-b851-ca9e0f2bf1e9.png)
  - 실습
    - ![image](https://user-images.githubusercontent.com/102513932/187072018-b66ab3a3-3089-4129-867d-dd8b7c6310bd.png)
      - 우측 상단 Fork 클릭 
    - ![image](https://user-images.githubusercontent.com/102513932/187072040-eb2514a5-82f9-4914-9e42-9778ee097467.png)
      - create fork 클릭시 fork 작업 완료
      - 현재 kimcoding의 git-project는 나의 remote repository.
      - kimcoding이 fork를 하면, git-project는 kimcoding의 repository에 복사본으로 저장되어 있음.
#### Clone
  - 내 Remote 레포에서 Local 레포로 코드를 옮기는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948727-c4afc1d1-1a4b-4a6a-a293-cfa8842247c3.png)
  - 실습
    - ```git clone <레포지토리 주소>```
      - 원격 repository를 내 로컬에서 이용할 수 있도록 복사
#### Push
  - Local 레포에서 Remote 레포로 commit을 업로드 하는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948972-105ecea3-4a8d-4953-9624-ecdb5f927287.png)
#### Pull
  - Remote 레포에서 변경사항이 있을 때 Local 레포로 가져오는 작업
    - ![image](https://user-images.githubusercontent.com/102513932/186949097-09317b26-c56a-4608-94c4-f44b58be6258.png)<br><br>
- Pull Request
  - 내가 push한 변경 사항에 대해 사람들에게 알리는 것
    - 줄여서 PR이라 지칭
    - ![image](https://user-images.githubusercontent.com/102513932/187073831-614029b6-c148-47b1-979d-656107a09dbf.png)
  - GitHub 상 Remote Repository에서도 Compare&Pull request 버튼 확인 가능
    - push 내용에 대해 간단히 요약, 동료들이 코드를 보지 않고도 내용 파악할 수 있게 해줌
#### Pull Request
- ![image](https://user-images.githubusercontent.com/102513932/208067142-dce95345-b77c-41df-bf3f-5b4f41fb6509.png)
  - PR을 보내기 전, Upstream의 브랜치 위치를 제대로 확인하고 PR을 보내야 함
- ![image](https://user-images.githubusercontent.com/102513932/208067321-c80e2518-6348-4388-aca9-57e8a784cb7d.png)
  - 브랜치 확인 시, Create pull request 버튼 클릭
- ![image](https://user-images.githubusercontent.com/102513932/208067417-38d8493a-0ff1-415a-af89-77ba1b193edb.png)
  - 추가할 부분이 없다면 그대로 진행 Create pull request 진행
- ![image](https://user-images.githubusercontent.com/102513932/208067579-50a4d5f6-b55c-4812-b03f-d29d07bd4387.png)
- ![image](https://user-images.githubusercontent.com/102513932/208067606-0458e6b3-fbff-4c7d-bf2b-abe9c5fc86a7.png)
  - Upstream 레포지토리 dev 브랜치에 PR된 이슈를 확인할 수 있음
- ![image](https://user-images.githubusercontent.com/102513932/208068260-52011d2a-42fa-4226-8082-e26852e10d3c.png)
  - 브랜치 위치를 확인했고, 팀원들과 협의가 끝났다면 본격적으로 `Merge`를 진행함
- ![image](https://user-images.githubusercontent.com/102513932/208068389-d79aaaca-3349-46b3-94a4-ed2d88019abe.png)
  - confirm merge를 진행함
- ![image](https://user-images.githubusercontent.com/102513932/208068472-ac945455-dd02-46ab-9c41-6cd4050f1780.png)
  - 정상적으로 Merge를 완료했을 때 나오는 화면
### 2. to CLI? GUI?
- CLI
  - 터미널 
  - git에서 뭔가 실행할 때
- GUI
  - 소스트리 접근 가능
  - 프로젝트의 상태를 자세히 살펴볼 때
<br><br>

### 3. 설정 & 프로젝트 관리 시작
- 적당한 위치에 폴더 생성 후 VS Code로 열람(폴더 열기)
 - git init 입력
   - terminal -> new terminal
   - new repository 생성시 사용하는 git 명령어 입력
 - git status 입력
   - 현재 폴더 상황 확인 가능
<br><br>


## 시간 여행하기

[누구나 쉽게 이해할 수 있는 Git 입문~버전 관리를 완벽하게 이용해보자](https://backlog.com/git-tutorial/kr/)
### 변화를 타임캡슐에 담아 묻기
- ```git add```
  - 프로젝트에서 일어난 변화를 캡슐에 담을 때
    - 문서에서 ctrl + s 필수
  - . 은 현재 폴더 안에 모든 것을 의미
    - ```git add .```
    - 올리지 말아야 할 파일까지 모두 add될 수 있으니 주의 요망
- ```git commit```
  - 타임캡슐 묻기(변경 사항 저장)
  - ```git commit -m "메시지"```
- ```git diff```
  - commit 전후 파일 내용 비교하기
- ```git log```
  - 커밋 내역 확인 가능
- ```git commit -am "메시지"```  
  - add와 commit 동시에 가능
  - 단, 새로 추가된 untracked 파일이 없을 때만 가능
  - 그냥 따로 하는것을 권장.
<br><br>

### 과거로 돌아가는 두 가지 방법
- ```git reset```
  - 원하는 시점으로 돌아간 뒤 이후 내역들을 지운다.
  - ```git log```
    - 되돌아갈 시점 커밋 해시 복사
  - ```git reset --hard``` (돌아갈 커밋 해시) 
- ```git revert```
  - 원하는 시점의 커밋을 거꾸로 실행
  - 내역들을 유지하며 특정 시점으로 돌아가고 싶을 때
  - 협업 시 자주 사용
    - 한번 공유된 commit은 revert를 사용하기.
  - 다른 이유로 충돌이 생길 수 있음
    - 문제 파일 삭제 혹은 변경 후
      - ```git revert --continue```로 마무리
  - 커밋하지 않고 revert하기
    - ```git revert -no-commit (되돌릴 커밋 해시)```
    - 원하는 다른 작업을 추가한 후 함께 커밋 가능
    - 취소하려면 git reset --hard
<br><br>

### 여러 branch 만들어보기
- branch: 분기된 가지(다른 차원)
  - 프로젝트를 하나 이상의 모습으로 관리할 때 사용
  - 여러 작업들이 각각 독립되어 진행될 때 사용
    - 신기능1,2..., 코드개선, 긴급수정
    - 각각의 차원에서 작업한 뒤 확정된 것을 메인 차원에 통합
  - 모든 작업을 하나의 프로젝트 폴더에서 진행할 수 있음
- 브랜치 생성 / 이동 /삭제
  - ```git branch add-coach```
    - add-coach라는 이름의 브랜치 생성
  - ```git branch```
    - 브랜치 목록 확인
  - ```git switch add-coach```
    - add-coach 브랜치로 이동
  - ```git switch -c new-teams```
    - new-teams 브랜치로 생성과 동시에 이동
  - ```git branch -d add-coach```
    - add-coach 브랜치 삭제
  - ```git branch -D add-coach```
    - 지워질 브랜치에만 있는 내용의 커밋이 있는 경우
    - 다른 브랜치로 가져오지 않은 내용이 있는 브랜치 삭제시
    - -D로 강제 삭제
  - ```git branch -m (기존 브랜치명) (새 브랜치명)```
    - 브랜치 이름 바꾸기
  - git log 이용시 주의사항
    - 위치한 브랜치에서의 내역만 확인 가능
  - 여러 브랜치의 내역 보기
    - ```git log -all -decorate -oneline -graph```
    - 소스트리에서도 확인 가능
### branch 병합하기
- merge
  - 두 브랜치를 한 커밋에 이어붙임
    - 브랜치 사용내역을 남길 필요가 있을 때
```python
# 기능 개발 진행
git commit -m "기능1의 기능1"
git commit -m "기능1의 기능2"

# 머지를 위해 main 브랜치로 전황
git switch main

# main 브랜치로 feat/todo 브랜치를 병합
git merge feat/todo

# 단, 프로젝트 개발 시에는 브랜치를 로컬에서 합치기 보다는 Github의 pull request를 통해 변경 내역을 충분히 확인하고 난 다음에 머지하는 경우가 더 많기 때문에, 로컬에서 머지하지 않고 브랜치를 push하여 pull request를 요청하는 것을 권장함
# pull request가 성공적으로 마무리 됐을 때, 브랜치를 삭제하는 버튼을 눌러 쉽게 삭제할 수 있음
```
- rebase
  - 브랜치를 다른 브랜치에 이어붙임
    - 한 줄로 깔끔히 정리된 내역을 유지하기 원할 때
    - 이미 팀원과 공유된 커밋들에 대해서는 사용 권장X

## 협업

1. init 으로 기존 디렉토리를 Git Repository로 변경
- ```git init```
2. remote and origin 으로 나의 Remote Repository에 연결
- ``` git remote add origin <Repository 주소>```
  - Local Repository에 Remote Repository 연결
3. remote add pair로 pair의 Remote Repository에 연결
- ```git remote add pair <Repository 주소>```
  - Local Repository에 Remote Repository 연결
  - 이름은 편의상 pair
4. remote -v로 연결된 Remote Repository 확인
- ```git remote -v ```
  - 현재의 Local Repository와 연결된 모든 Remote Repository 목록 확인
5. pull로 Remote Repository의 작업 내용 가져오기
- ```git pull <shortname> <branch>```
  - Remote Repository의 해당 branch 내용을 Local Repository로 가져오기
  - ex) ```git pull pair master```
  - 브랜치 이름 주의
  - 받아오는 내용은 자동 병합됨
6. 충돌 해결하기
- git status로 충돌 파일 확인
  - ![image](https://user-images.githubusercontent.com/102513932/187078622-6c7d5892-c45f-4877-aa1b-d2567a8fcd27.png)
- Accept Current Change
  - 내가 수정한 내용으로 파일 반영
- Accept Incoming Change
  - Remote Repository의 내용으로 파일 반영
7. push로 마무리

## 기타
### 문제 발생 시 참고할 flow chart
- ![icG8qdmMf0NPDoMfoBvyO-1660887701502](https://user-images.githubusercontent.com/102513932/208068742-3b618207-d487-4815-8e2c-f6740afa35f9.png)
### Git 설정
- 로컬 레포지토리와 연결할 유저 정보를 설정함
```
# 버전 히스토리를 식별할 때 사용할 이름을 설정
$ git config --global user.name "[firstname lastname]"

# 각 기록과 연결할 이메일 주소를 설정함
$ git config --global user.email "[valid-email]"
```

### 도움말 보기
- help 명령어를 이용하여 각 명령어 및 옵션 기능 확인 가능
```
# git에서 제공하는 모든 명령어를 볼 수 있음
$ git help -all

# 특정 command에서 사용할 수 있는 모든 옵션을 볼 수 있음
$ git [command] -help
```

### 세팅 및 초기화
- 레포지토리를 초기화하거나 존재하는 레포지토리를 클론할 수 있음
```
# 현재 디렉을 기준으로 Git 저장소가 생성됨
$ git init

# URL을 통해 리모트 레포지토리를 로컬 레포지토리에 복제함
$ git clone [url]
```

### Stage & Commit
- 스테이지 영역을 이용하여 커밋 가능
```
# 다음 커밋을 위해 현제 디렉토리에서 수정된 파일 확인 가능
$ git status

# 다음 커밋을 위해 파일을 추가(스테이지)함
$ git add [file]

# 추가한 파일을 언스테이징함, 변경 사항은 유지됨
$ git reset[file]

# 스테이지되지 않은 변경 사항을 보여줌
$ git diff

# 스테이지했지만 커밋하지 않은 변경 사항을 보여줌
$ git diff --staged

# 스테이지된 컨텐츠를 메시지와 함께 커밋함 (스냅샷 생성)
$ git commit -m "[decriptive message]"
```

### Branceh & Merge 
- 작업 내역을 브랜치로 분리해 컨텍스트를 변경, 통합할 수 있음
```
# 브랜치 목록을 보여줌, * 표시로 현재 작업중인 브랜치 확인 가능
$ git branch

# 현재 커밋에서 새로운 브랜치 생성
$ git branch [branch-name]

# 다른 브랜치로 전환
$ git switch [branch-name]
$ git checkout [branch-name]

# 새로운 브랜치를 생성하고 해당 브랜치로 전환함
$ git switch -c [branch-name]
$ git checkout -b [branch-name]

# 현재 브랜치에 특정 브랜치의 히스토리를 병합함
$ git merge [branch-name]

# 현재 브랜치의 모든 커밋 히스토리를 보여줌
$ git log
```

### 비교 및 검사
- 로그 및 변경 사항을 검사할 수 있음
```
# 브랜치B에 없는 브랜치A의 모든 커밋 히스토리를 보여줌
$ git log branchB..branchA

# 해당 파일의 변경 사항이 담긴 모든 커밋 표시 (파일 이름 변경돚 표시)
$ git log --follow [file]

# 브랜치A에 있지만 브랜치B에 없는 것의 변경 내용(diff)를 표시함 (branch간 상태 비교)
$ git diff branchB...branchA
```

### 공유 및 업데이트
- 특정 레포지토리의 업데이트 사항을 검색하여 로컬 레포지토리를 업데이트할 수 있음
```
# url을 통해 특정 리모트 레포지토리를 별칭으로 추가함
$ git remote add [alias] [url]

# 별칭으로 추가한 리모트 레포지토리에 있는 모든 브랜치 및 데이터를 로컬로 가져옴
$ git fetch [alias]

# 리모트 브랜치를 현재 작업중인 브랜치와 병합하여 최신 상태로 만들 수 있음
$ git merge [alias]/[branch]

# 로컬 브랜치의 커밋을 리모트 브랜치로 전송함
$ git push [alias] [branch]

# 리모트 레포지토리의 정보를 가져와 자동으로 로컬 브랜치에 병합함
$ git pull
```

### 히스토리 수정
- 브랜치 또는 커밋을 수정하거나 커밋 히스토리를 지울 수 있음
```
# 특정 브랜치의 분기 이후 커밋을 현재 작업중인 브랜치에 반영함
$ git rebase [branch]

# 특정 커밋 전으로 돌아가며 스테이지된 변경 사항을 모두 지움
$ git reset --hard [commitish]
```

### 임시 저장
- 브랜치를 전환하기 위해 변경되었거나 추적중인 파일을 임시로 저장할 수 있음
```
# 수정하거나 스테이지된 변경사항을 스택에 임시 저장하고 현재 작업 내역에서 지움
$ git stash

# 스택에 임시 저장된 변경사항의 목록을 보여줌
$ git stash list

# 스택에 임시 저장된 변경사항을 다시 현재 작업 내역에 적용함
$ git stash apply

# 스택에 임시 저장된 변경사항을 다시 현재 작업 내역에 적용하고 스택에서 삭제함
$ git stash pop

# 스택에 임시 저장된 변경사항을 삭제함
$ git stash drop
```

## Coz' Git flow
- ![image](https://user-images.githubusercontent.com/102513932/208028062-83eb4dc3-2900-4166-8bab-cc1ed134b0c2.png)
### main 브랜치
- 대표적인 기능이 완성
- 기존 계획했던 레이아웃이나 전체적인 디자인 얼추 완성
- 클라이언트, 서버, DB가 공개된 웹에서 장성적으로 통신 가능
- 최소한의 보안 마련됨
  - 기밀 정보 조회를 위해 인증 토큰, 세션이 꼭 필요한가?
  - 브라우저에게 비밀번호가 노출되는가?
### dev 브랜치
- 다음 버전 배포를 위해 개발중인 브랜치
- 가능하면 프론트와 백의 결과를 합쳐 확인해볼 수 있을 정도의 준비가 되어야 함
- CI/CD가 잘 구축되어 있다면 dev 브랜치의 코드도 배포를 해두고 수시로 확인할 수 있음
- main과 dev는 팀원의 코드 리뷰를 받고 진행하는 것이 적성임
  - 코드 리뷰까지는 아니더라도, 점검하는 것 정도는 필요함

## 보조 브랜치
### feature 브랜치
- 기능 개발, 리팩토링, 문서 작업, 단순 오류 수정 등 다양한 작업을 기록하기 위한 브랜치임
- 분류를 세세하기 나누기 위해 refactor, fix, docs, chore 과 같이 세세하게 커밋 메시지나 브랜치 명에 prefix를 달기도 함
```
hash (브랜치 명) 커밋 메시지
2f85eea (feat/create-todo) feat: Todo 추가 기능
2ad0805 (fix/var-name) fix: 변수 네이밍 컨벤션에 맞게 변수명 변경 (ismale => isMale)
e7ce3ad (refactor) refactor: 불필요한 for 루프 삭제
```
- 보통 각 개인의 로컬 레포에서 만들고 작업함
- 개인으로 작업하는 일이 많아 브랜치 생성이나 삭제에 대해 크게 신경쓰지 않아도 됨
- 작은 기능이라도 브랜치를 새로 만들고, 자주 커밋하고, 자주 push 하여 결과를 공유하는 것이 바람직함