Git
====================
<br><br>
Git 시작하기
-------------------------
0. Git 학습의 이유
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
1. Fork / Clone / Push / Pull / Pull Request
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
- Fork
  - 다른 원격 저장소에 있는 코드를 내 원격 저장소로 옮기는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948530-598dff97-a9c8-4516-b851-ca9e0f2bf1e9.png)
  - 실습
    - ![image](https://user-images.githubusercontent.com/102513932/187072018-b66ab3a3-3089-4129-867d-dd8b7c6310bd.png)
      - 우측 상단 Fork 클릭 
    - ![image](https://user-images.githubusercontent.com/102513932/187072040-eb2514a5-82f9-4914-9e42-9778ee097467.png)
      - create fork 클릭시 fork 작업 완료
      - 현재 kimcoding의 git-project는 나의 remote repository.
      - kimcoding이 fork를 하면, git-project는 kimcoding의 repository에 복사본으로 저장되어 있음.
- Clone
  - 내 Remote 레포에서 Local 레포로 코드를 옮기는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948727-c4afc1d1-1a4b-4a6a-a293-cfa8842247c3.png)
  - 실습
    - ```git clone <레포지토리 주소>```
      - 원격 repository를 내 로컬에서 이용할 수 있도록 복사
- Push
  - Local 레포에서 Remote 레포로 commit을 업로드 하는 것
    - ![image](https://user-images.githubusercontent.com/102513932/186948972-105ecea3-4a8d-4953-9624-ecdb5f927287.png)
- Pull
  - Remote 레포에서 변경사항이 있을 때 Local 레포로 가져오는 작업
    - ![image](https://user-images.githubusercontent.com/102513932/186949097-09317b26-c56a-4608-94c4-f44b58be6258.png)<br><br>
- Pull Request
  - 내가 push한 변경 사항에 대해 사람들에게 알리는 것
    - 줄여서 PR이라 지칭
    - ![image](https://user-images.githubusercontent.com/102513932/187073831-614029b6-c148-47b1-979d-656107a09dbf.png)
  - GitHub 상 Remote Repository에서도 Compare&Pull request 버튼 확인 가능
    - push 내용에 대해 간단히 요약, 동료들이 코드를 보지 않고도 내용 파악할 수 있게 해줌
    -  
1. to CLI? GUI?
- CLI
  - 터미널 
  - git에서 뭔가 실행할 때
- GUI
  - 소스트리 접근 가능
  - 프로젝트의 상태를 자세히 살펴볼 때
<br><br>
3. 설정 & 프로젝트 관리 시작
- 적당한 위치에 폴더 생성 후 VS Code로 열람(폴더 열기)
 - git init 입력
   - terminal -> new terminal
   - new repository 생성시 사용하는 git 명령어 입력
 - git status 입력
   - 현재 폴더 상황 확인 가능
<br><br>
4. git에게 맡기지 않을 것들
- 배제하는 경우
  - 포함할 필요가 없을 때
    - 자동으로 생성 또는 다운로드 되는 파일들 (빌드 결과물, 라이브러리)
  - 포함하지 말하야 할 때
    - 보안상 민감한 정보를 담은 파일
- .gitignore 파일 사용해서 배제할 요소 지정 가능
  - .gitignore 파일 생성
  - 배제하고 싶은 파일명 입력
    - https://git-scm.com/docs/gitignore 참고
    - ![image](https://user-images.githubusercontent.com/102513932/186900956-0190769c-2bd5-43f8-840f-ee4ec57f51e4.png)<br><br>

시간 여행하기
----------------------------
1. 변화를 타임캡슐에 담아 묻기
- ```git add```
  - 프로젝트에서 일어난 변화를 캡슐에 담을 때
    - 문서에서 ctrl + s 필수
  - . 은 현재 폴더 안에 모든 것을 의미
    - ```git add .```
    - 올리지 말아야 할 파일까지 모두 add될 수 있으니 주의 요망
- ```git commit```
  - 타임캡슐 묻기(변경 사항 저장)
  - 
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
1. 과거로 돌아가는 두 가지 방법
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
3. 여러 branch 만들어보기
- branch: 분기된 가지(다른 차원)
  - 프로젝트를 하나 이상의 모습으로 관리할 때 사용
  - 여러 작업들이 각각 독립되어 진행될 때 사용
    - 신기능1,2..., 코드개선, 긴습수정
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
3.1 branch 병합하기
- merge
  - 두 브랜치를 한 커밋에 이어붙임
    - 브랜치 사용내역을 남길 필요가 있을 때
- rebase
  - 브랜치를 다른 브랜치에 이어붙임
    - 한 줄로 깔끔히 정리된 내역을 유지하기 원할 때
    - 이미 팀원과 공유된 커밋들에 대해서는 사용 권장X
    - ..

