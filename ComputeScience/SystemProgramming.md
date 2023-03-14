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
  - 점선
    - 실행파일에 외부 library를 포함할 수도(static linking), 포함하지 않을 수도(dynamic linking) 있음
- C Preprocessor
  - 컴파일러에서 컴파일 될 수 있도록 코드를 전처리함 -> .i 파일 생성됨
  - include, definde, ifdef, endif, if, endif 등 처리 (모두 `#`생략)
- C Compiler
  - 컴파일러는 C를 machine-dependent한 assembly code로 변환함 -> .s 파일 생성됨
    - 어떤 architecture를 타겟으로 하는지에 다라 assembly code의 형태가 다르게 됨
    - ex) x86, ARM, mips, ...
- Assembler
  - assembly code를 machine-executable instructions로 변환함 -> .o 파일 생성됨
    - ex) op code / add r0, r1, r2, ...
  - 오브젝트 파일은 다음을 포함
    - constant data(상수 등), Static symbols(지역 변수 등), locally-defined globals(전역 변수 등), unresolved symbols(extern 변수 등)
    - 컴파일 단계에서 .c파일 마다 따로 컴파일을 진행하기 때문에 외부에서 선언된 extern 변수는 unresolved symbols로 남아있게 됨
- Linker
  - 다수의 object 파일을 실행파일(executable)로 변환함
  - ![image](https://user-images.githubusercontent.com/102513932/224873890-a1fecec9-0b26-44a4-9bf5-fa6c21c836e3.png)
    - 해결 안된 unresolved 는 외부 library를 통해 해결 가능

### Compiling Hello world
```c
#include <stdio.h>

int main(int argc, char *argv[]){ 
    // argc는 메인함수에 전달되는 정보의 개수를 의미
    // argv는 전달되는 실질적인 정보로 문자열의 배열을 의미함, 첫 번쨰 문자열은 프로그램의 실행경로로 항상 고정
    // ex) HelloWorld.exe I Love You Always
    // argc = 5 , agrv[0] : C:\Users\ceo\Desktop\새 폴더 "HelloWorld.exe"
    // argv[1] : "I", argv[2] : "Love", argv[3] : "You", argv[4] : "Always"
    return 0;
}
```
- 보통 컴파일 할 때 
  - `gcc -o helloworld helloworld.c`
    - helloworld : binary 파일명
- 가능한 다양한 옵션
  - `gcc -Wall -Werror -O2 -g -std=c99 -o helloworld helloworld.c`
    - Wall : 모든 경고 메시지를 켬
    - Werror : 모든 Warning을 error 취급
    - O2 : 컴파일 코드 최적화 단계 조절
    - g : 디버깅 정보 포함(gdb 등을 이용해 분석 시 사용)
    - std=c99 : 1999 iso C 표준을 따름
- 전처리기가 header를 포함시켜주고, 컴파일러가 assembly를 생성해주고, assmebler가 object 파일을 생성해주고, linker가 helloworld(executable binary)를 생성해줌
- 단계별 컴파일
  - `gcc -E` : 전처리를 실행하고 컴파일을 중단함
    - `#include <file>` 
      - System에 이미 환경변수로 지정되어 있는 파일을 쓸 때 사용
    - `#include "file"`
      - 다른 경로로 저장한 header 사용 시
  - `gcc -S` : c코드에서 assembly로 변환
    - ![image](https://user-images.githubusercontent.com/102513932/224876139-cc34c6b6-dc1b-42b5-87e3-bf7b9bf7ad0e.png)
  - `gcc -c` : 컴파일만 하고 링크를 수행하지 않으며, 오브젝트 파일을 생성함
  - `gcc -o` : 바이너리 형식의 출력 파일을 생성하고 이름 또한 지정함