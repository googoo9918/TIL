목차
------------------
- [절대 경로, 상대 경로](#절대-경로와-상대-경로)<br>
- [관리자 권한](#관리자-권한)<br>
- [기본 명령어](#기본-명령어)<br>
- [텍스트 에디터 nano](#텍스트-에디터-nano)<br>
- [패키지](#패키지)<br>
- [패키지 매니저 -apt](#패키지-매니저---apt)<br>
- [RWX 권한](#rwx-권한)<br>

CLI(Command-line interface)
===================================

절대 경로와 상대 경로
-------------------------------
- 절대 경로
  - 기준점 : 루트 디렉토리(/)
    - 루트디렉토리 ~ 특정 디렉토리/파일 까지의 경로
  - ex) ```/home/[username]/helloWorld/hello/```
- 상대 경로
  - 기준점 : 현재 위치(.)
    - 상위 폴더 : ```..```
    - 폴더 내부 : 슬래시(/)
      - ex) ./ : 현재 폴더 내부
      - ex) ./hi : 현재 폴더 내부 폴더 hi
- 루트 디렉토리(/)
  - 리눅스 파일 체제의 최상위 디렉토리
- 홈 디렉토리(~)
  - 사용자의 홈 디렉토리(사용자 계정명과 동일)가 만들어지는 디렉토리

관리자 권한
----------------------------------
- 운영체제와 긴밀한 연결, 주의 요망
- 루트폴더는 Linux 관리자의 영역
  - 일반 사용자의 권한으로 폴더및 파일의 생성, 변경, 수정 불가
- 어떠한 상황에도 일반 사용자에게 관리자 권한(루트 권한)을 넘기지 않음
  - 프로그램 설치, 변경, 삭제의 경우 해당 권한만 전달
- 사용자와 관리자의 명확환 분리를 통해 운영체제 보호
- 사용자가 관리자 권한을 필요로 할 때, `sudo` 이용
- sudo
  - 사용자 환경에서, 관리자 권한을 획득
  - ```mkdir jm``` vs ```sudo mkdir sm```
    - ```ls -l```로 확인시, 폴더의 소유자가 다름.(사용자/Root)



기본 명령어
-----------------------------------
[리눅스 명령어 모음 BEST 50 초보자 및 전문가용 - 도라가이드](https://dora-guide.com/linux-commands/)
- 프롬포트
  - CLI의 명령줄 대기모드
  - 터미널에서 글자가 입력되는 한 줄의 공간
- pwd
  - 현재 위치 확인
  - print working directory
- mkdir
  - 새로운 디렉토리(=폴더) 생성하기
  - make directories
  - mkdir ex1
- ls
  - 특정 디렉토리에 포함된 파일, 디렉토리 확인
  - list
  - -l
    - 디렉토리나 파일의 포맷(권한,크기,...) 전부 표현
    - ex) drwxr-x--- 3 lg   lg   4096 Aug 22 11:02 .
  - -a
    - all, 숨겨있는 디렉토리를 포함해 모든 항목을 터미널에 출력
  - -al(-la)
    - 위 두 옵션 혼합
- cd
  - 디렉토리에 진입하기
  - change directory
- touch
  - 파일 생성하기
  - ex) touch hi.txt
- echo
  - echo [string]
  - 텍스트나 문자열을 보여준다.
- `>`
  - 실행 결과를 파일로 저장하기
  - ex) ls > ls.txt
    - ls.txt에 ls 명령어의 실행 결과가 저장.
  - ex) echo 1013as@naver.com > hi.txt
    - 메일 주소가 hi.txt에 저장
- cat
  - 파일의 내용을 터미널에 출력
  - 파일의 내용이 큰 경우
    - head , tail, more, less 사용 용이
- rm
  - 디렉토리나 파일 삭제
    - 휴지통을 거치지 않고 바로 삭제되니 주의 요망
  - remove
  - 파일을 삭제할 때는 옵션 필요 X
  - -r
    - 디렉토리 삭제시
    - recursive
      - 특정 행동 순환적 반복
      - 하위 파일부터 먼저 삭제하여 재귀적으로 삭제
  - -f
    - 질문을 받지 않고 지울 때
    - force
      - 특정 행위 강제
- mv
  - 디렉토리나 파일의 이름을 변경
    - ex) mv bye.txt helloworld.txt
  - 디렉토리나 파일의 위치 변경
    - mv bye.txt bye
      - 뒤에 오는 것은 항상 디렉토리
  - move
- cp
  - 디렉토리나 파일을 복사
  - copy
  - 파일 복사시
    - ex) cp helloworld.txt hiCompute.txt
  - 디렉토리 복사시
    - -r 옵션 사용

텍스트 에디터 nano
-----------------------------
- 그냥 vi 쓰는게..?
- nano 실행
  - ```nano 파일이름```
- 파일 열기
  - ```^R``` 로 파일 open 가능
  - ```^T``` 롤 파일 및 디렉토리 탐색 후 open 가능
  - 그냥 첫 문법대로 하는게...
- 파일 편집 후 종료
  - ```^X```
- 파일 저장
  - ```^O```


패키지
-----------------------
- 여러 파일을 담고 있는 압축 파일
  - 프로그램 파일 
  - 프로그램 설치 파일
  - 프로그램 설치 설명서
  - 프로그럼 정보 파일
- 패키지 매니저
  - 패키지의 설치, 변경, 삭제등 관리 용이
  - 앱 스토어 느낌
    - 없으면 각 프로그램의 저장소를 직접 찾아 설치해야함
    - 없으면 업데이트 또한 주기적으로 저장소에서 확인해야 함.
  - 모든 패키지의 저장소 위치 저장
  - 업데이트 확인 / 프로그램 제거

패키지 매니저 - apt
----------------------------
- sudo
  - 관리자 권한 사용

- 패키지 목록 갱신 : ```sudo apt update```
  - 패키지를 다운로드 할 수 있는 정보 업데이트
- 업그레이드 가능한 패키지 목록 출력 : ```apt list --upgradable```
- 전체 패키지 업그레이드 : ```sudo apt upgrade```
- 특정 패키지만 업그레이드 : ```sudo apt --only-upgrade install 패키지 이름 ```
- 패키지 설치 : ```sudo apt install 패키지 이름```
- 설치된 패키지 보기 : ```apt list --installed```
- 패키지 검색 : ```apt search 검색어```
- 패키지 정보 확인 : ```apt show 패키지 이름```
- 패키지 삭제 : ```sudo apt remove 패키지 이름```

RWX 권한
------------------------
![image](https://user-images.githubusercontent.com/102513932/186846122-23306746-69f6-4d7f-a425-82b5f4bf6bc8.png)

- 폴더면 - / 디렉토리면 d
- user
  - 파일의 소유자
    - 기본적으로 파일 제작자
- group
  - 여러 user 포함 가능
  - 그룹에 속한 모든 user는 동일한 권한을 가짐
- other
  - 파일에 대한 액세스 권한
  - 파일을 만들지 않은 다른 모든 user
  - global 권한 설정
- chmod
  - 권한 변경 명령어
  - change mode
  - 로그인한 소유자와 사용자가 다른 경우
    - sudo 이용해서 권한 설정 가능
  - Operator과 accessor 유형 표기로 변경
    - ![image](https://user-images.githubusercontent.com/102513932/186848441-48226a8c-0148-41f8-a40f-46b4b0852fc6.png)
```
chmod g-r filename # removes read permission from group
chmod g+r filename # adds read permission to group
```
```
hello.java : -rw-r--r--
chmod a=rw hello.java # -rw-rw-rw-
chmod u= hello.java # ----rw-rw-
chmod a+rx hello.java # -r-xrwxrwx
chmod go-wx hello.java # -r-xr--r--
chmod a= hello.java # ----------
chmod u+rwx hello.java # -rwx------
```
- 이진수 3bits의 합으로 표현
  - 각 영역의 boolean 값으로 표현하기
  - ![image](https://user-images.githubusercontent.com/102513932/186849445-7b0c208b-7658-4aac-a29d-9ad5352f2157.png)
```
# u=rwx (4 + 2 + 1 = 7), go=r (4 + 0 + 0 = 4)
chmod 744 hello.java # -rwxr--r--
```
- ![image](https://user-images.githubusercontent.com/102513932/186849672-2f59fd11-72dd-4de0-bdf7-70464ca3250d.png)
<br><br>
<br><br>
