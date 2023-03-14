# 시스템 프로그래밍
## 1주차
### POSIX
- Portable Operating System Interface + X(UNIX)
  - 이식 가능 운영 체제 인터페이스
- ![image](https://user-images.githubusercontent.com/102513932/224866087-a416c17c-0b5e-4128-a909-ba1d4f040c20.png)
  - unix 계열의 os의 apllication과 os kernel 사이의 library나 shell interface를 정의하는 표준
    - 마치 api와 같이 동작하며, CLI(shell)로 제공됨
    - 표준을 POSIX가 정의, 개발자는 이에 맞춰 application을 구성함
    - 만약 printf(), putc()와 같은 함수들이 os kernel마다 다르면 os마다 코드를 수정해야하는 불상사 발생
  - POSIX를 준수하여 application 개발 시, POSIX를 준수하는 os에서 범용적으로 실행 가능
  - 서로 다른 unix os의 공통 api를 정리하여 이식성이 높은 유닉스 응용 프로그램 개발이 목적
- 주로 library와 shell interface를 정의함
  - Source code compatibility(호환성)을 위함이지 binary level에서 적용되는 것은 아님
  - 실행 파일이 같지는 않으므로, 코드를 통해 운영체제 마다 재 컴파일하고 재실행해야 함
- system call과 binary format은 정의하지 않음
  - 위 두개는 os kernel마다 달라지기 때문
  - source code compatibility만 제공한다 생각할 것
- C 기반 함수, 변수로 복잡한 system call을 단순히 다룰 수 있도록 도와주는 api 정도로 이해할 것
- 예시
  - ![image](https://user-images.githubusercontent.com/102513932/224867144-6113f1e1-3a2e-4fe9-8580-0461116a924d.png)
  - 일부는 C 표준과 통합되어 있음

>> kernel : 운영 체제의 핵심이 되는 컴퓨터 프로그램으로, 시스템의 모든 것을 완전히 통제함 <br>
>> shell :  운영 체제 상에서 다양한 운영 체제 기능과 서비스를 구현하는 인터페이스를 제공하는 프로그램 <br>
>> ex) 명령 프롬포트는 윈도우의 CLI 쉘

### Pointer
- POSIX를 학습하기 위한 C 언어에서 가장 중요한 부분
- 저장 공간의 주소를 저장함
- 메모리는 단순 bit 이기 때문에, 타입의 일치가 필수적임
- null이면 단순 pointer의 변수 공간만 잡아 놓은 것, invalid address
- 역참조
  - *, ->, [] 사용
  - ``` y= px[0] 과 y = *px 는 동일 문법```
  - 주의
    - ![image](https://user-images.githubusercontent.com/102513932/224868331-3da7a7fe-5b56-4822-86c3-6de6bf1efc71.png)

### C/POSIX Text I/O
- stdio.h에 정의되어 있음
- POSIX는 모든 I/O가 file 형태로 되어있음
  - FILE *
- 모든 POSIX process에서는 세 개의 파일이 항상 존재
  - stdin, stdout, stderr
- ![image](https://user-images.githubusercontent.com/102513932/224869041-0d636526-c262-44b0-8f4a-dc20b4d1a5a7.png)
  - fputs와 fprintf는 stdout, stderr 중 선택 가능함
  - gets는 사이즈가 정의되어 있지 않아 위험함, 지양할 것

## 2주차
### C ToolChain
- ![image](https://user-images.githubusercontent.com/102513932/224871558-0dca4cc3-a277-45b3-9f99-af2e50004333.png)
  - CPP : C Preprocessor, 전처리기
- ![image](https://user-images.githubusercontent.com/102513932/224871729-1b808a36-8354-452e-beae-169af758d611.png)