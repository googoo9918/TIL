## 목차
- [시스템 프로그래밍](#시스템-프로그래밍)
  - [C\_POSIX](#c_posix)
    - [POSIX](#posix)
    - [Pointer](#pointer)
    - [C/POSIX Text I/O](#cposix-text-io)
    - [C ToolChain](#c-toolchain)
    - [Compiling Hello world](#compiling-hello-world)
  - [Mem\_representation](#mem_representation)
    - [Bit, Word](#bit-word)
    - [Bus](#bus)
    - [CPU \<-\> Memory Transfer](#cpu---memory-transfer)
    - [Hexadecimal](#hexadecimal)
    - [Padding and Alignment](#padding-and-alignment)
    - [Pointers to Structures](#pointers-to-structures)
    - [Exploring Representation](#exploring-representation)
  - [Process v1](#process-v1)
    - [Process](#process)
    - [Executable Formats](#executable-formats)
    - [Loading](#loading)
    - [ELF Structure](#elf-structure)
    - [ELF Sections](#elf-sections)
    - [Program to Process](#program-to-process)
    - [Basic Layout](#basic-layout)
    - [Text, Data, Bss](#text-data-bss)
    - [Stack, Heap](#stack-heap)
    - [Static / Dynamic Allocations](#static--dynamic-allocations)
    - [Process Environment](#process-environment)
    - [System Call](#system-call)
    - [Invoking a Function](#invoking-a-function)
    - [Invoking a System Call](#invoking-a-system-call)
  - [Process Lifecycle](#process-lifecycle)
    - [Process Creation](#process-creation)
    - [Fork in Action](#fork-in-action)
    - [fork()/exec() Model](#forkexec-model)
    - [exec()](#exec)
    - [Process Termination](#process-termination)
    - [Detecting Process Termination](#detecting-process-termination)
    - [More Environment](#more-environment)
    - [current working directory](#current-working-directory)
    - [Environment Variables](#environment-variables)
    - [Files](#files)
  - [Linking](#linking)
    - [Why Linkers?](#why-linkers)
    - [What Do Linkers Do?](#what-do-linkers-do)
    - [Three kinds of Binary Files](#three-kinds-of-binary-files)
    - [Executable and Linkable Format(ELF)](#executable-and-linkable-formatelf)
    - [ELF object file Format](#elf-object-file-format)
    - [Linker Symbols](#linker-symbols)
    - [Sybmol Resolution](#sybmol-resolution)
    - [Local Symbols](#local-symbols)
    - [Linker Resolves Duplicate Symbol Definitions](#linker-resolves-duplicate-symbol-definitions)
    - [Linker's Symbol Rules](#linkers-symbol-rules)
    - [Global Variables](#global-variables)
    - [Relocation Entries](#relocation-entries)
    - [Packaging Commonly Used Functions](#packaging-commonly-used-functions)
    - [old-fashioned Solution : Static Libraries](#old-fashioned-solution--static-libraries)
    - [Modern Solution: Shared Libraries](#modern-solution-shared-libraries)
  - [Library Interpositioning](#library-interpositioning)
    - [Compile-time Interpositioning](#compile-time-interpositioning)
    - [Link-time InterPositioning](#link-time-interpositioning)
    - [Load/Run-time Interpositioning](#loadrun-time-interpositioning)
  - [Input/Output](#inputoutput)
    - [I/O Kernel Services](#io-kernel-services)
    - [Everything is a File](#everything-is-a-file)
    - [File Types](#file-types)
    - [Regular Files](#regular-files)
    - [Directories](#directories)
    - [File Descriptors](#file-descriptors)
    - [File Modes](#file-modes)
    - [System Call Failures](#system-call-failures)
    - [Opening Files](#opening-files)
    - [Reading](#reading)
    - [Writing](#writing)
    - [Closing](#closing)
    - [UNIX I/O Example](#unix-io-example)
    - [UNIX I/O Example with perror()](#unix-io-example-with-perror)
    - [Opening Streams](#opening-streams)
    - [Binary I/O](#binary-io)
    - [Reading and Writing](#reading-and-writing)
    - [Errors and EOF](#errors-and-eof)
    - [Standard I/O Example](#standard-io-example)
    - [System Call Overhead](#system-call-overhead)
    - [Buffering and Performance](#buffering-and-performance)
    - [Buffer Example](#buffer-example)
    - [Buffer Example2](#buffer-example2)
  - [Pipes and Redirection](#pipes-and-redirection)
    - [UNIX Pipes](#unix-pipes)
    - [Creating a pipe](#creating-a-pipe)
    - [Mechanism](#mechanism)
    - [Buffer Capacity](#buffer-capacity)
    - [Fork and Pipe](#fork-and-pipe)
    - [More on File Descriptors](#more-on-file-descriptors)
    - [How the Unix Kernel Represents Open Files](#how-the-unix-kernel-represents-open-files)
    - [Fork and File Descriptors](#fork-and-file-descriptors)
    - [Pipe File Descriptor Gotcha](#pipe-file-descriptor-gotcha)
    - [Safe Pipe and Fork](#safe-pipe-and-fork)
    - [Copying a Descriptor](#copying-a-descriptor)
    - [Redirecting Standard Output](#redirecting-standard-output)
# 시스템 프로그래밍
## C_POSIX
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
    - O2 : 컴파일 코드 최적화 단계 조절(O3가 가장 aggressive)
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
    - system libraries와 link
    - `ldd`를 통해 linkage 확인 가능

## Mem_representation
### Bit, Word
- 컴퓨터에게 메모리는 단순 bits이고, 컴퓨터는 data type을 알 수 없음
- 메모리를 단어(word)로 표현하는 것이 더 정확할 수 있음
  - word는 보통 native integer size임
    - 컴퓨터 설계시 정해지는 메모리의 기본 단위, CPU가 한 번에 처리할 수 있는 데이터 단위
    - 예를들어 x86-64에서는 64bits(8byte), ARM Cortex-A32에서는 32bits(4byte)
  - 프로세스의 word 크기와 Memory Bus의 너비가 다른 경우, word는 Memory Bus의 너비가 될 수도 있음
- ![image](https://user-images.githubusercontent.com/102513932/230872422-911a42fe-ebf2-490c-b5c5-7ffdecf5048f.png)

### Bus
- Bus는 CPU와 메모리 간에 데이터를 전송하는 통로 역할을 함
- Bus는 전선의 수를 나타내는 width가 존재
  - width는 한 번에 전송할 수 있는 비트 수를 결정함
- 각 wire가 one bit를 전송함
- 모든 bus는 width 만큼 bit를 전송하고, 이 과정에서 몇 bit는 버려질 수 있음
  - ex) width가 32bit인 경우, 실제 데이터 10bit가 전송되면 의미 없는 것은 버려짐

### CPU <-> Memory Transfer
- CPU는 word를 통해 memory로 부터 data를 가져옴
- register는 CPU가 요청을 처리하는데 필요한 데이터를 일시적으로 저장하는 기억장치임
  - register에는 word들이 배치되어 있고, register의 width는 native integer size임
  - register의 word의 width는 bus width와 같을 수도, 다를 수도 있음
    - x86-64는 같음
    - register 32bit, memory bus 64bit라 가정 
      - register 2개로 데이터를 받아야 함
    - register 64bit이고, memory bus 32bit라 가정
      - 메모리에 접근(전송)을 여러 번 해야 함

>> 우리는 word에 의미를 부여할 때(data type을 정할 때) convention을 이용함 <bf>
>> 이말은 즉슨, memory에 저장된 data의 의미는 프로그래머와 시스템 설계자 사이의 합의에 의해 결정된다는 것이다.

### Hexadecimal
- 우리는 16진수를 자주 사용함
  - 하나의 hex digit에 four bit를 담을 수 있기 때문에 편리함

### Padding and Alignment
- 데이터 정렬(Alignment)을 위해 Padding을 사용
  - 예를 들어 1 word가 32bit일 때, 28bit 짜리 구조체를 연속으로 저장한다면 28bit와 4bit의 패딩으로 data 크기를 맞춰 저장함
  - 28bit를 붙여서 저장하지 않는다는 점에 유의할 것
- 저장할 때나, 전송할 때, 조회할 때 모두 Padding을 포함한 한 덩어리를 사용함
- Padding을 사용하지 않을 경우, Password 노출과 같은 여러 문제가 발생할 수 있음
- 구조체 정렬 시 가장 큰 크기의 데이터 타입으로 나눠 떨어지는 메모리 주소가 되도록 정렬함
  - 예를 들어 구조체가 int와 char로 구성되어 있다면, 구조체 멤버 변수들은 char 1바이트에 대해 3바이트의 padding이 추가된 뒤에 int 4바이트와 배치됨
- ex
  - ![image](https://user-images.githubusercontent.com/102513932/231060099-3946eb4f-559a-4a5f-b780-5919588773be.png)
- 많은 시스템에서 정렬되지 않은 접근은 에러를 야기함
  - 정렬되지 않은 접근은 크기로 균등히 나눠져 있지 않은 데이터를 조작하려고 하는 것이기 때문임
  - bus error를 일으킴
- 에러가 일어나지 않는 시스템도 있는데, 정렬되지 않은 접근은 매우 느림
  - 여러번 memory 접근을 하기 때문임
    - 메모리는 정렬된 word만 가져올 수 있기 때문에 여러번 접근을 통해 가져옴
  - x86-64는 이에 해당함

### Pointers to Structures
- 포인터는 컴파일러에게 어떤 타입을 쓸 것인지 얘기해주는 것임
- 잘못된 연산을 하는 것을 막을 수 없으니.. 유의해서 사용하라
```c
struct Complex{
  double r;
  double i;
}complex;
struct Complex *pc = &complex;

// pc->i == (*pc).i

// 포인터는 보폭(stride)를 지님
double *dptr = &somedouble;
// dptr의 값이 0이라면, dptr+1의 값은 1이 아닌 8임.
// double이 8 byte 크기 자료형이기 때문에..

double *d = &compelx;
//위와 같이 잘못된 포인터 타입을 선언하면 warning이 발생하게 되는데, 가급적이면 해결하고 넘어가는 것이 좋다.

char *bytes = (char *)&compelx; 
//일부러 1byte씩 확인하고 싶을 때는, 위와 같이 형변환을 통해 확실히 명시해서 warning이 없도록 하자.

#include <stdlib.h>
void *malloc(size_t size); 
//void형 포인터를 리턴하므로, 무엇이든 지정할 수 있음
// ex) int *array = malloc(10 * sizeof(int));
void free(void *ptr);
// 동적할당과 해제
// ex) free(array); -> malloc 개수만큼 있어야 한다.

// 구조체 malloc 예시
struct Complex{
  double r;
  double i;
};

struct Complex *c = malloc(sizeof(struct Complex)); //결국 size만 잘 지정해준다면..

c->r = 1.0;
c->i = 0.0;

free(C);
```

### Exploring Representation

```c
#include <stdio.h>

void dump_mem(const void *mem, size_t len){
  const char *buffer = mem; //cast to char * --> 1byte 단위로 끊어서 보겠다
  size_t i;

  for(i=0; i< len; i++){
    if(i>0 && i%8 ==0) { //8번째 바이트가 나온 뒤 줄 바꿈
      printf("\n");
    }

    printf("%02x ", buffer[i] & 0xff); // --> 1byte씩 찍어보겠다
    // sign extension을 피하기 위함임(0xff 는 1111 1111 이다.)
  }
  if( i>1 && i%8 != 1){
    puts("");
  }
}
```

```c
int x = 98303; // 0x17fff
dump_mem(&x, sizeof(x));

//Output : ff 7f 01 00
// 왜 0x17fff가 ff 7f 01 00으로 나타나는가?
// 우리는 주로 리틀 엔디안을 사용하기 때문임(x86 프로세서가 integer를 대표하는 방식이기도 함)
```

```c
char c = 0x80;
//2^7이고, 1000 0000임

int i = c;
// 맨 앞자리가 1이므로 음수로 인식하고, sign extension이 일어나게 됨
dump_mem(&i, sizeof(i));

//Output: 80 ff ff ff
//integer 사용으로 Sign extension 발생 -> 앞자리가 전부 1로 채워짐
//따라서 signed 자료형을 사용할 때 유의해야 함
//양수는 큰 문제가 되지 않지만, 음수 사용 시에는 2의 보수를 사용해야 함
//0과 1을 뒤집은게 1의 보수인데, 밑 사진과 같이 0에 대한 표현이 2가지가 나오게 됨 -> 따라서 잘 사용하지 않음
//2의 보수는 0의 표현이 유일하기 때문ㅇ, 사용이 용이함
//2의 보수를 쉽게 계산하기 위해서는 0과 1을 flip 시키고, 1을 더하면 된다.(밑 사진 참고할 것)
```
- 1의 보수
  - ![image](https://user-images.githubusercontent.com/102513932/231191242-34864337-b09a-4250-a0da-3b4b50624737.png)
- 2의 보수
  - ![image](https://user-images.githubusercontent.com/102513932/231191651-90947b9c-c7ff-4c12-9bb8-9a384efebfed.png)

```c
char c = 0x80;
int i = c;

// c는 부호가 있는 자료형이므로, 현재 -128과 동일함.
// int형인 i로 형변환이 일어나면서 1의 복사가 일어나지만, 값은 -128로 동일하다.
// 11111111 11111111 11111111 10000000이 i에 저장된 값이지만
// 결국 2의 보수로 생각해 봤을 때, -128로 동일한 값이 나오게 된다.
```
- ![image](https://user-images.githubusercontent.com/102513932/231201714-658de940-f4bd-4a5c-8478-afdca0105421.png)
  - 웬만큼 작은 부동 소수점이 아니라면 굳이 double을 쓸 필요는 없음
  - 정확도에서는 double을, memory 측면에서는 float 사용을 고려할 것

## Process v1
### Process
- 프로세스는 실행되고 있는 프로그램을 뜻함
  - 프로그램이 단순 기계어의 집합이라면, process는 사용중인 memory 공간과 system resource등도 포함함
  - ex) 작업관리자 메모리 사용량
- UNIX는 각 프로세스가 다른 프로세스와 격리된 보호된 환경에서 실행되도록 보장함
  - 독자적인 메모리를 가짐
  - 각 프로세스가 전용 CPU를 갖고 있는 것처럼 보이게 함
  - 시스템 서비스가 각 프로세스에 전념하고 있다는 착각을 줌
    - 커널에 의해 관리되기 때문에 파일, 소켓 및 장치와 같은 리소스가 프로세스 간 적절히 공유되고 보호됨
  - 하드웨어가 위와 같은 사항들이 유지되도록 도움

### Executable Formats
- 각각의 실행파일(exe 파일)은 format을 가짐
  - 프로그램의 환경변수 정보 포함
  - 프로그램 code가 binary instruction으로 저장되어 있음
  - metadata포함(header, external libraries)
- ELF(Executable and Linking Format)
  - 현대의 많은 시스템이 ELF를 사용함
  - object를 load하고 execute하기 위한 정보 포함
  - link 하기위한 정보 포함

### Loading
- 로딩은 프로그램이 프로세스(실행)가 되는 과정임
- ![image](https://user-images.githubusercontent.com/102513932/231208185-26000913-8aa4-4040-a3b2-20657c22705f.png)
  - 리눅스에서는, 커널(kernel)이 로딩을 시작하고 ld-linux.so(dynamic linker/loader for linux shared object)가 로딩을 마침
  - kernel은 ELF 실행 파일의 다양한 부분과 loader를 memory로 이동시킴
    - loader 또한 kernel의 한 부분임
  - kernel은 loader를 호출하여 메모리 내의 프로그램 데이터에 다양한 변경을 수행하게 함
  - loader는 프로그램의 시작 지점으로 jump하게 됨

### ELF Structure
- ELF 파일은 여러 section과 segment로 나눠져 있음
  - section에는 linker를 위한 파일이 존재함
  - segment에는 loader를 위한 파일이 존재함
- ![image](https://user-images.githubusercontent.com/102513932/231210849-2e4c8699-4ba5-4e2e-8aba-14e285fcfc27.png)
  - ELF Header는 object의 type을 알려줌
    - ex) platform, endianness
  - segment table은 loader에게 파일의 부분이 메모리에 배치되어야 하는 위치를 알려줌
  - section table은 linker를 위해 section을 알려줌
    - 여러개 object 파일이 묶여 실행 파일이 됐으므로 각 파일별 정보가 section에 들어가 있음

### ELF Sections
- Text
  - 실제 프로그램 코드 저장
  - binary화 된 instruction 존재
  - 실제 processor가 해야하는 동작들 저장
- Data
  - String, const, global variables 변수 저장
  - compile time에 값이 정의되는 변수는 Data Section에 저장됨
- Bss
  - complie time에 값이 정의되지 않고 runtime에 정의되는 변수 저장
  - ex) 초기화되지 않고 선언만 된 전역변수, 정적 변수

### Program to Process
- 실행 파일이 메모리에 로드되어 프로세스가 됨
- 프로세스의 메모리 레이아웃은 ELF 섹션을 따르게 됨
- 시스템은 로드된 레이아웃에 의미를 추가함
- 대부분의 POSIX 시스템은 동일한 레이아웃을 사용


### Basic Layout
- ![image](https://user-images.githubusercontent.com/102513932/231226477-2638ea13-abb4-4336-a8be-7c1ddd5cc267.png)
  - 가장 낮은 주소는 절대 사용되지 않음
    - null pointer 선언 시 이 공간을 가리키게 됨
  - text와 data section은 ELF file로 부터 바로 전달됨
  - BSS 섹션에 할당되는 메모리 공간은 프로그램 실행 시 동적으로 할당되므로(실행 파일 로드 시 커널이 할당), 파일 내에 직접 나타나지 않음
  - stack과 heap은 loader에 의해 결정되며, 실행 중에 동적으로 변경됨
    - brk는 break pointer로, heap이 동적 할당 받은 memory보다 더 큰 공간을 사용할 때 실행을 중단 시킴 (Heap Segment)
- 현대의 시스템에서는 조금 다르지만, 논리적 개념 자체는 유지됨
  -  ELF 파일에는 없지만 프로그램에서는 사용되는 공유 라이브러리를 사용하기도 함
  -  section을 랜덤화 하여, 외부 공격을 방지함

### Text, Data, Bss
- text
  - 실제 프로그램 명령어 포함
  - 어셈블러는 어셈블리 코드를 기계어 코드로 변환하는데, 이 기계어 코드는 링커에 의해 .text 섹션에 배치됨
- Data
  - compile time에 초기화 된 변수와 상수를 저장함
  - linker가 data를 ELF에 넣으면, 커널이 process memory에 로드함
- BSS
  - compile time에 값이 없는(초기화 되지 않은) 변수를 저장
  - Block Started by Symbol
  - 컴파일러가 BSS section 내 변수를 식별하고 위치를 기록하긴 하지만, ELF 파일에 변수를 저장하진 않음
    - BSS section은 프로그램 실행(load) 시 동적으로 할당되기 때문(커널이 시행)


### Stack, Heap
- stack
  - 함수 내에서 사용하는 지역변수
- heap
  - malloc과 같은 동적할당 시 사용됨
- 둘 다 크기가 가변적임
- ![image](https://user-images.githubusercontent.com/102513932/231425155-8a3dff84-8d1d-4781-b585-099bb619c911.png)
  - grow downward when functions are called and shrinks when they return
  - ```kernel``` manages the size of the stack automatically
  - function call은 argument와 Local variables를 포함
- ![image](https://user-images.githubusercontent.com/102513932/231425640-6f066a00-280d-45a6-a068-707f216d0ec3.png)
  - Heap은 자동적으로 늘어나진 않음
  - ```kernel```이 brk(program break)를 unmapped memory와 heap 사이에서 유지함
  - grow upward -> brk(program break) be moved
- ![image](https://user-images.githubusercontent.com/102513932/231426251-26c2ecb2-97b1-4b67-92f8-38db74d7c7f2.png)
  - 초기화된 전역 변수 --> data section
    - 단, const가 붙거나 문자열 literal처럼 상수라면 .rodata(read only)
  - 초기화 되지 않은 전역 변수 --> bss section
    - static은 함수 내부에 있어도 전역 변수로 인식할 것
  - function code는 text section
  - malloc과 free는 heap 관련
  - 함수 내부 지역변수는 stack 저장

### Static / Dynamic Allocations
- Static
  - data와 BSS section의 size는 compile time에 알 수 있음
  - 이 메모리는 프로그램이 종료됐을 때 release됨
- Dynamic
  - heap과 stack은 프로그램이 실행되고 있을 때 resize됨
  - 함수의 호출/종료와 malloc/free에 의해 크기가 계쏙 변함
    - 만약에 un-freed memory가 있다면 프로그램 종료 시 OS에 의해 해제됨

### Process Environment
- 프로세스는 복잡한 환경을 가짐
- Kernel Service
  - POSIX system이 프로세스마다 메모리 독립이나, cpu 독점 착각이 가능하게 했었고 이를 위해 hardware assistance가 필요했다는 것을 기억하라
  - kernel만이 이러한 hardware assistance를 구성할 수 있음
  - 따라서 process는 kernel에 많은 자원을 요청하게 됨
  - 사실 상기 작업은 system call을 통해 수행됨
- Process Lifecycle
  - process의 memory 공간은 kernel에 의해 생성됨
  - 즉, procee는 kernel에 의해 생성됨
  - process가 종료되면 다음과 같은 resource는 정리되어야 함
    - memory, Files, shared resources(ex: GPU)
- Kernel/Userspace Seperation
  - 커널은 모든 하드웨어 리소스를 관장함
  - 이를 위해 supervisor mode로 run 됨
  - process는 user mode로 run되고, 이 환경 또한 kernel에 의해 생성됨
    - 이를 userspace라 지칭함


### System Call
- Supervisor mode와 user mode는 보호 도메인이고, 서로 system call을 통해 상호 작용함
- 보호 도메인(protection domain)을 움직이는 것은 여간 쉬운일이 아님
  - 그래서, system call은 간단한 function이 될 수 없음
  - x86_64에서는 software interrupt를 통해 system call에 접근함

### Invoking a Function
- 일반 함수가 실행될 떄
  - stack이나 특정 register에 argument를 place
  - 현재 pc값을 stack에 저장
  - function의 첫 번째 명령어로 pc값을 변경함
- 일반 함수 종료 시
  - return value를 특정 register에 저장
  - stack에 있는 pc값 조회
  - 다시 돌아가기 위해 현재 pc값을 조회한 pc값으로 변경

### Invoking a System Call
- syscall 호출 시
  - system call number가 특정 register에 저장됨
  - system call argument는 다른 register에 저장됨
  - 이후 syscall 호출
  - CPU hardware
    - protection domain 변경 (user -> supervisor)
    - kernel 영역으로 이동하여, syscall 실행시킴
- 실행 이후 종료 시
  - return vlaue 특정 register에 저장
  - sysret(System return)명령어 호출
  - CPU hardware
    - user mode로 다시 돌아옴
    - syscall 호출한 function으로 돌아감 
- 시스템 콜은 특정 프로세서 명령어를 사용함(아키텍쳐마다 다를 수 있음)
- 다른 프로세서에서 호환되는 모델마다 명령어가 다를 수 있음
- 운영체제마다도 다를 수 있음
- 고유한 시스템 콜이 있다고 생각할 것
- 이런 까다로운 작업을 `C library`와 같은 wrapper function으로 편하게 이용하고 있음

## Process Lifecycle
### Process Creation
- fork()
  - UNIX에서 new process를 만드는 유일한 syscall
  - fork()를 call한 process와 `동일한 동작`을 하는 process를 하나 더 만듬
    - 이를 자식 프로세스라 부름
    - PId는 다름
      - 자식의 PPID는 부모의 PID와 같음
    - new process를 위해 새 memory 공간을 할당함
    - 새 memory space에 현재 process의 content를 붙여 넣음
  - fork() 성공 시 반환 값
    - 부모 프로세스 : 자식 프로세스의 PID 반환
    - 자식 프로세스 : 0을 반환
    - 에러발생 시: -1을 반환
  - 모든 POSIX process는 부모 process를 지님
    - 만약 부모 프로세스가 종료되면, 자식 프로세스는 고아프로세스가 됨
    - 이 고아 프로세스는 `init`에게 맡겨지게 됨(init이 부모 프로세스가 됨)
### Fork in Action
```c
pid_t pid = fork();

if(pid == 0){
  puts("In child");
} else{
  printf("In parent, child PID = %d\n", pid);
}

//output
//In parent, child PID = 9095
//In child
//원래라면 분기로 나눠져 둘 중 하나의 출력문만 실행되어야 하지만, fork() 함수 호출 시점부터 자식과 부모 프로세스가 독립적으로 실행되기 때문에, 두 개의 출력문이 모두 실행된다.
// 순서는 예측할 수 없다.
```

### fork()/exec() Model
- fork()는 creator와 same program을 만듬
- exec()는 현재 process image를 new program으로 교체함
  - 성공 시 반환값은 따로 존재하지 않음 
  - fork()와 exec()를 결합하면 현재 프로세스를 복제한 후 자식 프로세스에서 새로운 프로그램을 실행할 수 있음
  - posix_spawn() 은 fork와 exec()의 기능 결합
    - 최신에 많이 사용되는만큼, 더 효율적임

### exec()
- exec() 시스템 호출에 여러 변형이 있음(ex: execl(), execv(), exelp())
  - 모두 현재 프로세스의 메모리 공간에서 새 실행 파일을 로드하고 실행하는 목적을 수행함
```c
excel("/bin/ls", "ls", "-F", "/", NULL);
// /bin/ls는 ls 실행 파일의 경로임
// "ls"는 실행 중인 프로그램의 이름
// -F는 ls 명령에 전달되는 옵션
// "/"는 나열할 디렉토리를 지정
// NULL은 인수 목록의 끝을 나타냄

// 새로운 프로세스를 실행한 뒤 경로와 여러 옵션을 붙여 ls명령어를 실행시킨거임.  

// output:
// afs/
// boot/
// dev/
// etc/
// ...
```

### Process Termination
- 종료 시점
  - exit()을 call 했을 때
    - 종료 시 정수 인수(argument) 리턴 
  - main()으로 return 될 떄
    - 종료 시 정수 반환 값 리턴
  - 특정 signal을 catch 하는데 실패했을 때(제대로 된 응답을 못받았을 때)
    - ex) SIGSEGV 시그널(세그멘테이션 폴트)를 받았지만 이를 catch하기 위한 시그널 핸들러가 없는 경우
  - 특정(종료) signal을 받았을 때
    - 신호에 의해 종료되었음을 나타내는 특수 값 반환
    - 어떤 신호가 종료를 유발했는지도 나타냄

### Detecting Process Termination
- wait() system call은 부모 프로세스에서 자식 프로세스 중 하나가 종료될 때까지 대기하는데 사용됨
  - reaping : 프로세스가 종료된 후 그 프로세스에 대한 정보를 회수하는 과정
    - 주로 부모 프로세스가 자식 프로세스가 종료될 때 그 결과를 회수하여 시스템 리소스를 정리함
  - 만약 자식 프로세스가 종료되었는데 reaping되지 않았다면 시스템에서 여전히 PID를 차지하고 있는 상태임
  - 좀비 프로세스는 memory space에 올라와 있어서 자원을 계속해서 소모하게 됨
  - 고아 프로세스는 부모 프로세스가 먼저 종료된 경우로, init 프로세스가 reap 하게됨

```c
pid_t pid = fork();
if (pid == 0){ //child에서 수행
  puts("In child");
  exit(42); //42를 종료 상태로 반환함 --> status 값으로 넘겨지게 됨
} else{ //부모 process에서 수행
  int status;
  waitpid(pid, &status, 0); //특정 pid로 부터 &status 값을 받으면 다음줄 실행
  printf("Child exited with status %d\n", WEXITSTATUS(status));
  //WEXITSTATUS()를 통해 status 변수에서 자식 프로세스의 종료 상태를 추출하고 출력 -> 42 출력
}

//Output :
// In child
// Child exited with status 42
// 당연히 순서가 정해져 있겠지? 
// 부모 process는 waitpid에서 자식 process가 끝날때 까지 못넘어가니까.. 
```

### More Environment
- 프로세스 환경은 다음도 포함함
  - 현재 working directory
  - 환경 변수
  - open files
- 상기 환경은 kernel에 의해 유지됨
### current working directory
  - 모든 process는 working directory를 지님
  - 프로세스가 실행되는 동안 파일 시스템에 대한 상대적 기준점 역할을 함
    - 쉽게 말해, 상대 파일 경로는 현재 작업 디렉토리를 기준으로 함
  - cf
    - chdir()을 통해 작업 디렉토리 변경 가능
    - 조회를 위한 getcwd()
    - getwd()는 버퍼 크기를 확인하지 않아 오버플로우가 일어날 수 있으니 사용 지양

### Environment Variables
- 환경변수는 프로세스가 실행되는 동안 사용할 수 있는 설정 값이나 정보를 저장함
  - 모든 프로세스는 환경 변수를 가짐
  - 환경 변수들은 environ 이라는 전역 배열에 저장됨
    - fork() 시 커널에 의해 복제됨
  - getenv()를 통해 특정 환경 변수 검색 가능
  - setenv()를 통해 환경 변수 설정 가능

### Files
- 커널은 모든 프로세스에 대해 open 파일을 유지함
  - 이 파일들은 정수 파일 descriptor로 식별됨
  - 프로세스는 이 파일 디스크립터를 사용하여 파일을 읽고 쓸 수 있음
- stdin, stout, stderr은 각각 파일 디스크립터 0, 1, 2로 정의됨
  - 표준 입력, 표준 출력, 표준 오류
- 각 파일 디스크립터에 대해 가장 최근에 읽거나 쓴 위치가 저장됨
  - 이 위치 정보를 통해 파일 내에서 읽고 쓰기 작업의 시작점을 결정할 수 있음
- 파일 디스크립터는 fork()에 의해 복제됨 
- 파일 디스크립터는 exec()에 의해 선택적으로 닫힐 수 있음
  - 새로운 프로그램이 실행되기 전 현재 프로세스의 파일 디스크립터를 닫을지 여부를 결정 할 수 있음
  - 자식 프로세스가 부모 프로세스의 파일 디스크립터를 상속받지 않고 새로운 실행 환경 설정 가능

## Linking
```c
//main.c
int sum(int *a, int n); // sum의 실제 정의는 다른 c 파일에 되어 있음

int array[2] = {1,2};

int main()
{
  int val = sum(array,2);
  return val;
}

//sum.c
int sum(int *a, int n)
{
  int i,s = 0;

  for(i=0; i<n; i++){
    s+= a[i];
  }
  return s;
}
```

- ![image](https://user-images.githubusercontent.com/102513932/232183883-d382a2ec-214c-4f7d-a149-cce0de04cdac.png)
  - source file은 전처리기, 컴파일러, 어셈블러에 의해 object파일이 됨
  - 지금 main.o와 sum.o는 ELF 포맷을 따르지만 실행 가능한 오브젝트 파일은 아님 (relocatable object file임)
  - prog는 ELF 포맷을 따르는 실행 가능한 파일
    - 사실 binary도 일종의 object 파일이라 보는 것이 맞음
  - 즉, 링킹 과정을 거치기 전에는 실행 불가능한 오브젝트 파일(== relocatable object file), 링킹 이후에는 실행 가능한 오브젝트 파일임


### Why Linkers?
- Modularity(모듈화)
  - 프로그램을 작은 소스파일로 구성할 수 있음
    - 코드의 복잡성이 줄어들고, 분업이 가능함
  - 공통 기능을 모아 놓은 라이브러리를 만들 수 있음
  - 코드 재사용 가능
- Efficiency(효율성)
  - 시간
    - 별도의 컴파일 가능
    - 변경 후 재 컴파일 불필요, 시간 절약 가능
  - 공간
    - 공통 함수를 하나의 파일(라이브러리)로 모을 수 있음
      - 실행 파일의 크기를 줄일 수 있으며, 라이브러리의 복사본이 여러 번 저장되지 않아 저장 공간이 절약됨
    - 실행 파일과 실행 중인 메모리 이미지에는 실제 사용되는 함수에 대한 코드만 포함됨
      - 라이브러리에서 사용하는 함수가 호출되는 경우에만 해당 함수가 메모리에 로드됨


### What Do Linkers Do?
- Symblol resolution(심볼 해석)
  - 심볼은 전역 변수와 함수와 같이 프로그램의 여러 부분에서 참조할 수 있는 식별자를 의미함
  - static이 붙은 지역 변수도 가능함
```c
void swap() {…} /* 심볼 swap을 정의 */
swap(); /* 심볼 swap을 참조 */
int *xp = &x; /* 심볼 xp를 정의하고, x를 참조 */
```
  - 심볼 정의는 어셈블러에 의해 오브젝트 파일의 심볼 테이블에 저장
    - 심볼 테이블은 구조체 배열로 구성됨
    - 각 엔트리에는 심볼의 이름, 크기, 위치 등의 정보 포함
  - During symbol resolution step, the linker associates each symbol reference with exactly one symbol definition
    - 여러개의 symbol이 겹치는게 있을 때, 한 개로 정확히 mapping 해줌
- Relocation(재배치)
  - ELF Format을 따르는 여러개의 오브젝트 파일을 한 개의 ELF Format에 재배치함
    - 이게 실행파일이 됨
  - 여러 오브젝트 파일의 코드와 데이터 섹션을 하나의 섹션으로 병합함
  - 각 심볼의 상대적 위치를 오브젝트 파일에서 최종 실행 파일의 절대 메모리 위치로 변경함
  - 이러한 심볼들에 대한 모든 참조를 업데이트하여 새로운 위치를 반영하게 됨


### Three kinds of Binary Files
- Relocatable object file(.o file)
  - 코드와 데이터를 포함, 다른 재배치 가능한 오브젝트 파일과 결합해 실행 가능한 오브젝트 파일을 생성할 수 있는 형태임
  - 전처리, 컴파일, 어셈블 처리를 거친 후 상태 (링킹 이전)
- Executable object file(a.out file)
  - 코드와 데이터를 포함, 메모리에 직접 복사되어 실행될 수 있는 형태
  - 링커를 통해 재배치 가능한 오브젝트 파일들과 라이브러리가 결합되어 생성됨
- shared object file(.so file)
  - 공유 오브젝트 파일
  - 특수한 종류의 재배치 가능한 오브젝트 파일
  - 메모리에 로드되어 로드 시간이나 실행 시간에 동적으로 연결될 수 있는 형태
    - **load time, run-time**
  - 실행중인 프로그램에 필요한 코드와 데이터를 제공함
  - 여러 프로그램에서 동시 사용 가능
  - 윈도우 시스템에서는 동적 링크 라이브러리(Dynmaic Link Libraries, DLLs)라고도 불림

### Executable and Linkable Format(ELF)
- ELF format은 object 파일의 표준 이진 형식임
- .o 파일, .out 파일, .so 파일 모두 ELF format을 따름
- ELF binaries라고도 칭함

### ELF object file Format
- ![image](https://user-images.githubusercontent.com/102513932/232185286-8b2711ea-8ef1-44b8-a067-57c045fce218.png)
- Elf header
  - word size, byte odering(엔디안), file type(.o, exec, .so), machine type
- Segment header table
  - Page size, virtual addresses memory segments, segment sizes
- .text section
  - code
- .rodata
  - data section 에서 문자열 literal, const 변수 등 상수
- .data section
  - 초기화된 전역 변수
- .bss(Block started by symbol or Better Save Space) section
  - 초기화되자 않은 전역 변수
  - 프로그램이 실행되기 전에 어떤 값도 갖지 않으므로, 이 섹션은 디스크 상에서 공간을 차지 않음
    - 대신 프로그램이 메모리에 로드될 때 운영 체제에 의해 자동으로 0으로 초기화 됨
- ![image](https://user-images.githubusercontent.com/102513932/232186965-013cddd0-d764-4090-9193-19b1f21b31bc.png)
  - .symbtab section
    - symbol table section
    - 함수 및 정적 변수 이름 포함
    - 각 섹션의 이름 및 위치 정보 저장
  - .rel.text section
    - .text section의 relocation 정보 저장
      - linker가 relocation 시 수정해야할 명령어의 주소와 수정 방법에 대한 정보 포함
  - .rel.data section
    - .data 섹션에 대한 재배치 정보를 저장함
    - linker가 병합된 실행 파일에서 수정해야할 포인터 데이터의 주소와 수정 방법에 대한 정보 포함
  - .debug 섹션
    - 컴파일 시 `-g` 옵션 사용할 때, 디버깅 정보 포함
  - section header table
    - 각 섹션의 오프셋(상대 주소)과 크기 포함


### Linker Symbols
- Global symbols
  - 모듈 m에 의해 정의되지만, 다른 모듈에서도 참조할 수 있는 심볼
  - non-static c 함수와 non-static 전역 변수가 이에 해당
- External symbol
  - 모듈 m에서 참조되지만, 다른 모듈에서 정의되는 **전역 심볼**
  - 다른 모듈에서 제공되는 기능이나 데이터에 대한 종속성을 지님
- Local symbols
  - 모듈 m에서만 정의되고 참조되는 심볼
  - c 함수, 전역변수나 지역변수를 static 속성으로 정의한 경우에 해당됨
  - 해당 모듈 내에서만 사용되고, 다른 모듈에서는 볼 수 없음
  - 지역심볼과 지역 변수는 다른 개념임 주의

### Sybmol Resolution
- ![image](https://user-images.githubusercontent.com/102513932/232189104-b3e74591-271c-43bc-b814-f706385abd94.png)
  - main.c 입장에서 symbol을 살펴봄
  - array, main은 Global symbol
    - main.c에 의해 정의되고, 다른 모듈에서도 참조할 수 있음
  - sum은 External symbol임
  - static이 붙은 함수나 전역변수, 지역변수가 없으니, Local symbol은 없겠지

### Local Symbols
- non-static C 변수 vs local static C 변수
  - static이 붙지 않은 변수는 스택에 저장됨
  - static이 붙은 변수는 .bss(초기화 되지 않은 변수인 경우)나 .data(초기화 된 전역인 경우)에 저장됨
    - 여기서 만약 전역변수라면 symbol이고, 지역변수라면 symbol이 아님
```c
int f()
{
  static int x=0;
  return x;
}
int g()
{
  static int x = 1;
  return x;
}
// 컴파일러는 .data section에 각 x를 저장함
// 또한, static이 붙은 지역변수는 local symbol로 처리되기 때문에
// symbol table에 x.1 , x.2와 같이 unique한 name으로 저장하게 됨
// 변수명을 달리해서 충돌을 피하기 위함임
```

### Linker Resolves Duplicate Symbol Definitions
- Program sybols are either string or weak
  - strong : 초기화된 전역 변수
  - weak : 초기화되지 않은 전역 변수
```c
//p1.c
int foo = 5; //strong

p1(){ } //strong

int foo; //weak
p2() { } //strong
//p2.c
```

### Linker's Symbol Rules
- Rule 1
  - Multiple strong symbols are not allowed
    - strong symbol은 단 한번만 정의되어야 함
    - 여러 번 정의되면 linked error
- Rule 2
  - strong symbol 하나와 다수의 weak symbol이 주어지면, strong symbol을 선택함
    - 따라서 초기화되지 않은 변수는 초기화된 전역 변수에 잡아 먹힐 수 있음
      - 따라서 웬만하면 전역 변수 사용을 권장하지 않음
      - static 변수는 해당 함수 내에서만 접근 가능하니 괜찮음
- Rule 3
  - 다수의 weak symbol만 존재한다면, 무작위로 하나를 선정하게 됨
    - ```gcc -fno-common`으로 변경할 수는 있음
- 즉 전역변수는 이름이 같으면 서로 초기화가 되어있어도 문제이고, 한쪽만 초기화가 되어있어도 문제임

### Global Variables
- 가능하다면 사용을 피하라
- 가능하다면 static을 사용하라
- 전역 변수를 사용할 것이라면, 초기화를 해라
  - strong symbol을 위해
- 외부 전역 변수를 참조할 것이라면, extern을 사용하라
  - 이를 통해 이름 충돌을 방지할 수 있음

### Relocation Entries
- ![image](https://user-images.githubusercontent.com/102513932/232197050-8d2cfee0-5b80-4dac-824d-caeef7b441cf.png)
  - 아직 링킹을 하기 전이므로, 정확한 주소값을 넣지 않은 상황임
    - relocate 시 주소가 바뀌기 때문
- ![image](https://user-images.githubusercontent.com/102513932/232197098-d6e448fa-bc70-4ada-8d5d-e4c3398d6b0c.png)
  - 실행 파일에서는 주소 값이 정확하게 들어가게 됨
  - 4004e3과 4004e8의 차이는 pc값 차이임

### Packaging Commonly Used Functions
- 예전의 방식인 정적 링킹에 대한 내용임
- How to package functions commonly used by programmers?
- 1. 모든 함수를 하나의 소스 파일에 넣기
  - 예전에는 하나의 binary에 모든 function의 정보를 넣음
  - 장점 : librayr를 깔지 않아도 실행 가능함(이식성이 높음), 프로젝트 구조가 간단해짐
  - 단점: 용량이 과도하게 커짐 -> 공간 및 시간 효율이 떨어짐, 커다란 파일은 가독성이 떨어지고 유지보수가 어려움
- 2. 각 function을 각 소스 파일에 넣음
  - 장점: 필요한 바이너리만 링크하여 공간 및 시간 효율이 향상됨
  - 단점: 프로젝트 구조가 복잡해지고 관리가 어려워짐, 어차피 하나의 실행파일로 만들 것이라 효율이 떨어짐


### old-fashioned Solution : Static Libraries
- Static libraries(.a archive file)
  - 예전에는 아카이브 파일(정적 라이브러리)에 common function을 다 넣어서 해결함
  - linker가 각 오브젝트 파일에 아카이브 파일을 포함해서 실행파일을 만들게 됨
  - 하지만 이 아카이브 파일이 점점 커져서 binary file이 너무 커지게 됨
- 또한 더 큰 문제는
  - cli에 순서를 바꿔 입력하면 에러도 생김
    - 마지막에 static libraries를 넣었어야 했음
  - ex) a.o b.o ... libc.a (O) , libc.a a.o b.o ... (X)
- 따라서 현재는 **shared libraries**를 사용함  

### Modern Solution: Shared Libraries
- 정적 라이브러리 단점
  - 저장된 실행 파일 중복: 각 실행 파일이 필요 함수에 대해 별도의 정적 라이브러리를 포함해야 함
  - 실행 중인 실행 파일의 중복: 여러 프로그램이 동일한 라이브러리 사용 시, 각 프로그램의 메모리에 동일한 라이브러리 코드가 중복으로 로드됨
  - 시스템 라이브러리가 업데이트 되면, 각 애플리케이션을 다시 링크해야함
- 해결책으로 등장한 공유 라이브러리(Shared Libraries)
  - 위에서 나온 공유 오브젝트 파일(.so(윈도우는 .dll 파일))과 일맥 상통함
  - 코드와 데이터를 포함하는 오브젝트 파일로, 애플리케이션에 동적으로 로드 및 링크됨
  - load time이나 run-time에 dynamically하게 로드 및 링크될 수 있음
  - DLL(Dynamic Link Libraries)(in window), .so 파일(in UNIX)등의 다른 이름으로도 불림
- 장점
  - 디스크 공간 절약: 각 실행 파일이 동일한 라이브러리를 공유, 디스크 공간이 절약됨
  - 라이브러리 업데이트 용이: 공유 라이브러리가 업데이트되면, 해당 라이브러리를 사용하는 모든 애플리케이션에 자동으로 적용됨
    - 다시 링크할 필요가 없음
- 링킹 시점에는 .so 파일의 정보만 갖고 있고, 코드를 갖고 있진 않음
  - 즉, 실행 파일은 라이브러리에 대한 참조 정보만 포함됨
  - 프로그램이 실행되는 시점에 라이브러리와 연결됨


## Library Interpositioning
- 강력한 linking과 관련된 기술로, function call을 임의의 다른 function으로 intercept 함
- compile time, Link time, Load/run time에 가능
- 주로 보안, 디버깅, 모니터링 및 프로파일링에서 사용
  - 보안
    - 중간에 interpositioning을 통해 코드 정보를 암호화
  - 모니터링
    - function의 호출 횟수를 새거나, Malloc을 tracing 하는 등
```c
// int.c
#inlcude <stdio.h>
#include <malloc.h>

int main(){
  int *p = malloc(32);
  free(p);
  return(0);
}
// 목적: **프로그램을 중단시키지 않고**
// **소스 코드를 변경하지 않고** 
// allocated adn freed blocks의 사이즈 측정

// solution: malloc과 free 함수를 interpose 
// in compile time, link time, load/run time
```

### Compile-time Interpositioning
- ![image](https://user-images.githubusercontent.com/102513932/232274591-1bc3bb04-ec41-4599-82ae-370983251f63.png)
  - printf부분이 각각 추가된 것을 알 수있음
    - by Wrapper function
- ![image](https://user-images.githubusercontent.com/102513932/232274609-19afc04b-8162-4a80-80ad-6a741129cce3.png)
  - int.c의 코드를 수정하면 안되니, 매크로를 통해 redirection
  - `-DCOMPILETIME`을 통해 1로 설정, ifdef 작동하게 함
  - `-c`를 통해 오브젝트 파일 생성
  - `-I.`을 통해 현재 directory로 path 포함
- 정리
  - function이 매크로를 통해 사용자 정의 함수로 전환됨

### Link-time InterPositioning
- ![image](https://user-images.githubusercontent.com/102513932/232274747-d85fb2a2-d786-4592-9d24-d95c8adda4a3.png)
  - 언더바(__)는 그냥 prefix라 생각할 것
  - compile-time 코드와 구조 자체는 같음
  - `__real_malloc`을 `__wrap_malloc`으로 변경
- ![image](https://user-images.githubusercontent.com/102513932/232274759-f28ee507-02c9-4a4e-9eaf-c26391e0873b.png)
  - `-DLINKTIME`을 통해 `#ifdef` 동작
  - `-Wl`은 linker에게 콤마 뒤에 있는 부분을 wrapping 하라고 명령
  - `--wrap, <fucntion>`에 대한 설명 참조
    - `<function>` -> `__wrap_<function>`
- 정리
  - 링커를 사용하여 특수한 이름 해석 적용(malloc ->__wrap_malloc, __real_malloc -> malloc)
  - 이를 통해 링커가 사용자 정의 함수를 기존 라이버르리 함수와 교체하도록 지시

### Load/Run-time Interpositioning
- 실행파일이고, 이미 동작하고 있으니 Shared Object를 통해 해결
- ![image](https://user-images.githubusercontent.com/102513932/232275139-1becd256-bf17-489a-b783-bbdc9ddaa562.png)
  - 로더가 `"./mymalloc.so"` 파일을 올린 상태에서 `./intr` 실행
- 정리
  - 동적 링킹을 사용하여 다른 이름으로 라이브러리의 malloc과 free를 로드함
  - dlsym 함수를 사용하여 실행 시간에 기존 라이브러리 함수를 다른 이름으로 가져옴

## Input/Output
### I/O Kernel Services
- c standard library에 text I/O using을 지님
  - fread(), fgets(), printf(),...
  - 사실 이건 다 system call을 기반으로 하고 있음
### Everything is a File
- UNIX에서는, 모든 것이 파일임
  - ex
    - /proc/<pid>/stack
      - 특정 process의 stack 정보까지 파일로 존재함
    - /dev/null
      - data를 포함하지 않으며, 뭘 써도 버려짐
      - null을 위한 파일
    - /dev/urandom
      - 암호학적으로 안전한 파일, 읽을 때마다 랜덤 data를 내놓음
    - 심지어 커널 그 자체도 파일임!
  - 파일은 바이트의 나열임

### File Types
- Regular file
- Directory
- Socket
- Symbolic links
- Character and block devices

### Regular Files
- text file 과 binary 파일로 구분
  - text file
    - ASCII or Unicode characters
    - sequence of text lines
      - terminated by newline char('\n')
      - Linux와 Mac OS: '\n'(0xa)
      - wndows and internet protocols: `'\r\n'`(0xd 0xa)
  - binary file(ELF header 존재)
    - everything else
  - 커널은 이 두 파일의 차이점을 모름(header로 구분)

### Directories
- link 배열로 구성
  - 각 link는 filename과 매핑
- 각 디렉토리는 최소 두 개의 entries를 지님
  - `.`(dot)
    - 자신에 대한 link
  - `..`(dot dot)
    - 상위 폴더에 대한 link
- leaf node가 아닌 것은 다 디렉토리임
  - leaf node여도, 마지막이 `/`로 끝나면 디렉토리임
- 커널은 각 프로세스마다 current working directory(cwd)를 유지함

### File Descriptors
- 파일 디스크럽티너느 특정 프로세스에서 파일 오픈 시 오픈된 파일을 integer로 구분하는 것임
- 0: standard input
- 1: standard output
- 2: standard error

### File Modes
- rwx(user)rwx(group)rwx(other permission)
  - r: read, w: write, x: execute
- chmod로 변경 가능
- user: file's owner
- group: apply to members of the file's group
- other permission: apply to all other users
- ex
  - 750(111101000b):rwxr-x---

### System Call Failures
- System call은 실패 시 음수를 반환함
- 실패 시, gloval variable errno가 이유로 설정됨
  - errno.h에 errno가 정의되어 있음
- `perror()`, `strerror()`을 통해 errno를 사람이 읽을 수 있는 형태로 변환 가능

### Opening Files
```c
#include <fcntl.h>
int open(const char *path, int flags, mode_t mode);
int creat(const char *path, mode_t mode);

// creat() 시스템 콜은
// open(path, O_CREAT|O_WRONLY|O_TRUNC, mode);와 같음
// 없으면 만들고, 쓰기 전용, 0byte부터 작성
// 위 두개 함수는 성공 시 filedescriptor를 리턴함
```
- Flag
  - ![image](https://user-images.githubusercontent.com/102513932/232278079-a09ccd29-8938-4b2e-83cc-cdc6687a9774.png)

### Reading
```c
#include <unistd.h>
int read(int fd, void *buffer, size_t bytes);
// fd에서 읽어서 buffer에 byte만큼 저장함
// open file 대상으로 동작 가능
// string에서 마지막에 들어오는 null값은 안읽음
// return value: 0(end of file), >0(bytes read), <0(error)
```

### Writing
```c
#include <unistd.h>
int write(int fd, const void *buffer, size_t bytes);
// buffer에서 byte만큼 fd에 작성함
// return value: >=0 (bytes written), <0(error)
```

### Closing
```c
#include <unistd.h>
int close(int fd);
// free 처럼, open한 파일은 항상 close 해야함
```

### UNIX I/O Example
- ![image](https://user-images.githubusercontent.com/102513932/232278319-94bef40e-4876-4b23-8e89-1b9e757352a4.png)
  - `argv[1]` fd open, 0보다 작으면 에러 처리
    - 0보다 크면, fd에 file descriptor 저장및 while문 실행
  - buf의 크기가 1024 byte니, fd를 1024 byte만큼 읽어 buf에 저장함
  - read가 정상 동작했으면, stdout에 buf 출력
  - 정상 동작시 1(true) return, 아닌 경우 0(false) return

### UNIX I/O Example with perror()
```c
if((bytes = read(fd, buf, sizeof(buf)))<0){
  perror("read");
  exit(1);
}

if((bytes = write(fd, buf, sizeof(buf)))<0){
  perror("write");
  exit(1);
}
// 둘 다 동작이 실패한 경우 perror을 통해 오류를 출력하고 실행을 종료함
```
- ![image](https://user-images.githubusercontent.com/102513932/232279141-88a87f0c-8ce5-4b5c-bd73-0c1fe75a137b.png)
  - 실패했을 시 perror로 다 에러 출력 처리
    - open의 flag 변경 시 에러 출력
  - `lseek()`는 몇 번째 byte로 file pointer(offset)를 옮길 것인지 결정

### Opening Streams
- standard I/O는 엄밀히 말하면 file 이 아니라 stream을 향해 동작함
```c
FILE *fopen(const char *path, const char *mode);
// ex) FILE *fp = fopen("file.txt", "r");
// 파일을 열지 못할 경우 NULL 반환
FILE *fdopen(int fd, const char *mode);
// 이미 열린 파일 디스크립터를 기반으로 파일 포인터를 생성
// ex) int fd = open("file.txt", O_RDONLY);
// FILE *fp = fdopen(fd, "r"); // 권한 맞춰줘야함
// 파일 디스크립터를 추가로 생성하는 것은 아님(다른 권한으로 열 수 없음)
```
- ![image](https://user-images.githubusercontent.com/102513932/232280082-2516a3de-34b2-4e88-8eb6-5b67037d046b.png)

### Binary I/O
- 윈도우 시스템에서는 개행 문자를 읽을 때 자동으로 '\r\n'으로 변환함
  - 이러한 변환을 방지하고 이진 파일로 작업하기 위해서 fopen 모드 문자 뒤에 "b"를 붙여 사용함
  - ex) FILE *fp = fopne("somefile", "rb"); 
- POSIX 시스템에서는 "b" 플래그가 무시됨
  - POSIX 시스템에서 커널은 텍스트 파일과 이진 파일을 구분하지 못하기 때문
    - 어차피 header를 통해 파일의 종류를 구분함

### Reading and Writing
```c
#include <stdio.h>
size_t fread(void *dest, size_t size, size_t nmemb, FILE *fp);
// dest: 데이터를 저장할 버퍼의 포인터
// size: 각 데이터 항목의 크기
// nmemb: 읽어들일 데이터 항목의 개수(count)
// fp: 파일 스트림의 파일 포인터
// ex) fread(buf, 1, sizeof(buf), fp)
size_t fwrite(const void *buf, size_t size, size_t nmemb, FILE *fp);
// buf에 저장된 데이터를 size 크기의 nmemb 개수만큼 fp에 씀
// 물론 open으로 미리 열어놔야함
// 위 함수는 binary data 기반으로 동작
// printf, scanf는 text data 기반으로 동작
// return value: number of items read/written
// error나 파일 끝이면 0 return
```

### Errors and EOF
- UNIX I/O와 다르게, standard I/O는 EOF와 error의 return value가 같음
```c
int feof(FILE *fp);
int ferror(FILE *fp);
// 파일 스트림에서 오류나 EOF를 감지하기 위한 두 가지 function
// EOF에 도달하거나 에러가 발생한 경우, 0이 아닌 값을 반환함
// 그렇지 않으면 0을 반환함

void clearerr(FILE *fp);
// 파일 스트림의 오류 및 EOF 상태를 재설정하는 데 사용됨
// 이전에 발생한 오류 및 EOF 상태를 지울 수 있음
// 단, 실제 오류가 해결되지 않았다면 동일 오류가 다시 발생할 수 있기 떄문에, 호출 전에 해당 오류를 적절히 처리할 수 있어야 함
```

### Standard I/O Example
- ![image](https://user-images.githubusercontent.com/102513932/232280725-d41cad4f-b924-493a-9eac-dd0ef8bc2c61.png)
  - 파일 open 실패 시 null 반환
  - `!feof(fp)` -> 에러가 발생하지 않으면 0 return -> !0 -> true
  - fp에서 sizeof(buf)만큼 (1024 Byte) 1Byte씩 읽어서 buf에 저장
  - buf에서 bytes 만큼 1byte씩 가져와 stdout에 출력
  - 파일 스트림 fp와 표준 출력에서 오류가 발생했는지 확인하고, 오류가 발생했다면 프로그램이 종료됨

### System Call Overhead
- 매번 UNIX I/O의 System Call을 사용하는 것은 성능적으로 안좋음
  - syscall의 동작 과정을 기억하라
    - protect domain을 바꾸고, 레지스터에 등록하고, 다시 바꾸고, .
- standard I/O는 overhead를 줄이기 위해 **buffering(stream)**을 사용함
  - ex, fread() for 1byte는 read a full disk block
    - stream에 데이터를 다 올려놓고 거기서 1btye만 읽음
  - 이는 성능 최적화를 위한 중요한 전략인건 맞지만, 경우에 따라 올바른 동작에 영향을 줄 수있음
  - device I/O와 같이 정확한 읽기/쓰기 크기가 요구되는 경우 standard I/O의 버퍼링이 적절하지 않을 수 있음
    - 버퍼링으로 인해 일부 데이터만 쓰여지는 short write 문제가 발생할 수 있음
  - `int fflush(FILE *fp)`를 통해 해결 가능
    - 파일 스트림의 버퍼를 비워 디스크에 직접 쓰게함

### Buffering and Performance
- ![image](https://user-images.githubusercontent.com/102513932/232290916-45ae5160-01c2-45d3-8d79-0143644932a4.png)
  - User time used는 total += c 같은 작업
  - System time used는 syscall이 불려오는 시간
- ![image](https://user-images.githubusercontent.com/102513932/232290947-4b802415-b586-4446-8f78-8cef7101470d.png)
  - 모든 분야에서 standard I/O가 UNIX I/O보다 빠른 작업 시간을 가짐

### Buffer Example
- ![image](https://user-images.githubusercontent.com/102513932/232291095-c8e4b959-e6ae-42e3-8358-342e5dadaa88.png)
- ![image](https://user-images.githubusercontent.com/102513932/232291149-958606c3-6da4-41a5-8bf4-c881695c5b71.png)
  - fread는 fp로부터 일정량의 data(512 byte 이상 큰 데이터)를 가져와 buffer에 채움
- ![image](https://user-images.githubusercontent.com/102513932/232291177-084b5fcf-78e1-4d0e-8be2-fc9fe9631fd8.png)
  - 그러고 버퍼로부터 sizeof(len) 1개를 읽어와서 len의 주소에 저장함
- ![image](https://user-images.githubusercontent.com/102513932/232292747-c419bd63-17ec-4c29-b85c-b2ddd41ca632.png)
  - 이후 fread()에서는 buffer에서만 데이터를 읽어와 data의 주소에 저장
    - 1byte를 len 횟수만큼 읽겠지

### Buffer Example2
- ![image](https://user-images.githubusercontent.com/102513932/232293168-abb74cd3-fec5-4973-abf6-b3db89fee3e8.png)
  - 버퍼는 다음과 같은 상황에 출력 파일 디스크립터로 flush됨
    - 1. 개행문자를 만났을 때
    - 2. fflush나 exit 명령어가 호출되었을 때
    - 3. main 함수로 return 될 때
- ![image](https://user-images.githubusercontent.com/102513932/232294166-da41df9b-05a8-45e4-a93e-06b70027f0a6.png)
  - write는 한 번 사용됨
  - `printf("\n");` 에서 작동하고, 이후 `fflush(stdout)`이나 `exit(0)` 에서는 버퍼가 이미 비어있기 때문에 호출되지 않음

## Pipes and Redirection
### UNIX Pipes
- pipe는 process간 통신 시 사용함
  - 단방향으로 동작하며, 한 쪽으로 write 반대쪽으로 read
  - pipe도 file descriptor로 표현됨
    - 따라서 any file descriptor와 연결될 수 있음
    - 특히 표준 I/O 파일 디스크립터(0~2)에 주로 연결됨
      - 파이프를 표준 I/O 파일 디스크립터에 연결하면 한 프로세스의 출력이 다른 프로세스의 입력으로 사용될 수 있음

### Creating a pipe
```c
int pipefd[2]; 
// pipe[0] : read port, pipe[1]: write port
if(pipe(pipefd)<0){
  perror("pipe");
}
// pipe() system call은 pipe를 한 쌍의 file descriptor로 생성함
```
- ![image](https://user-images.githubusercontent.com/102513932/232296907-116cd3b6-246e-4915-aa0c-91228196bec4.png)

### Mechanism
- 파이프는 커널 버퍼를 통해 데이터를 전송하는 방법임
  - 데이터를 담는 커널 버퍼와 두 개의 파일 디스크립터로 구성됨
- ![image](https://user-images.githubusercontent.com/102513932/232297111-d27d13fc-6b6c-417f-b324-f4dc7d61ff58.png)
  - read file descriptor has a read pointer
    - pipefd[0]
- ![image](https://user-images.githubusercontent.com/102513932/232297195-30344c4d-abc8-4e74-a674-c6067115e489.png)
  - write file descriptor has a write pointer
    - pipefd[1]
- ![image](https://user-images.githubusercontent.com/102513932/232297694-6000ccb8-2b89-413b-a6ff-f5e2c771db6f.png)
  - write(pipefd[1], &wval, sizeof(wval));
  - 4byte 만큼 write, buffer 채움
    - write pointer는 4가 됨
- ![image](https://user-images.githubusercontent.com/102513932/232297933-f3a83f73-7fa7-4eed-b0ef-aac429afa34c.png)
  - read(pipefd[0], &rval, sizeof(rval));
    - buffer에서 4byte만큼 읽음
      - read pointer도 4가 됨
      - write pointer와 동일한 위치가 된다는 것이 중요!

### Buffer Capacity
- 파이프를 사용하여 데이터를 전송할 때, 커널 버퍼가 비어있는 경우에는 읽기 연산이 blocking됨
- 따라서 단일 프로세스(Single process) 내에서 파이프 통신을 사용할 경우 교착상태(deadlock)에 빠질 위험이 있음
  - ex, 프로세스가 파이프에 커널 버퍼 크기보다 많은 바이트를 쓰려고 하면, 쓰기 연산도 블로킹되어 대기하게 됨
  - 이 경우 다른 프로세스가 버퍼를 비워주는 읽기 연산을 수행하지 않기 때문에 파이프에 쓰려는 프로세스는 계속해서 대기하게 되고, 마찬가지로 deadlock에 빠짐
  - 이러한 문제를 해결하기 위해 일반적으로 별도의 프로세스 또는 스레드를 사용하여 동시에 읽기와 쓰기 연산을 수행하도록 설계함


### Fork and Pipe
- ![image](https://user-images.githubusercontent.com/102513932/232300596-2009240d-f046-43c0-8070-7a5a18715298.png)
  - process간 통신으로, 뭐가 먼저 동작할지는 모르지만 read를 먼저 하고 뒤늦게라도 write가 들어오면 아무런 문제 없음
  - 단, 서로 다른 process의 pipefd가 제대로 닫혀있지 않기 때문에 pipefd[0]이 EOF를 반환하진 못함

### More on File Descriptors
- fd를 다루는 구조
- FDT(File Descriptor Table)
  - 모든 프로세스가 지님
- OFT(Open File Table)
  - open file에 대한 metadata를 지님
  - Global한 Table
  - FDT가 참조할 수 있는 Table임
    - 파일의 직접적인 정보가 아닌, 파일의 위치나 어떤 권한으로 참조하는지, 몇개의 파일을 참조하는지 등의 정보를 지님
- v-node Table
  - 용량과 권한 같은 직접적인 정보는 이곳에 저장됨
- ![image](https://user-images.githubusercontent.com/102513932/232302133-318535d7-9d87-445b-b474-b7fdbfc64a2f.png)

### How the Unix Kernel Represents Open Files
- ![image](https://user-images.githubusercontent.com/102513932/232302370-5e02c815-11d2-4441-a5da-c0147b759e22.png)
  - 만약 똑같은 파일 이름으로 두 번 open 했어도, OFT에 서로 다른 table을 참조하는 것처럼 보임
    - 만약, **권한**까지 같다면 같은 OFT를 참조하는 것처럼 보임

### Fork and File Descriptors
- fork()는 file descriptor table 까지 복사함
  - 당연히 동일한 OFT를 참조함(권한 마저 같음)
- Before fork
  - ![image](https://user-images.githubusercontent.com/102513932/232302536-1e11346d-4b2f-40c8-be04-dc1d45bb9426.png)
- After fork
  - ![image](https://user-images.githubusercontent.com/102513932/232302542-e1019ba8-17fb-40f1-b11c-5fb08cc724a7.png)
  - refcnt만 1 증가하게 됨

### Pipe File Descriptor Gotcha
- single process 상태에서도 write 부분(pipefd[1]) 닫고 read만 하면 deadlock 상태에 빠지지 않고 EOF를 return 함
  - 이처럼 파이프의 읽기 끝(pipefd[0])은 쓰기 끝(pipefd[1])이 닫힐 떄 EOF를 반환함
    - 이는 파이프에서 더 이상 읽을 데이터가 없음을 나타냄
- 만약 fork()를 사용한다면, 부모 프로세스와 자식 프로세스 모두 동일한 파이프를 사용하게 됨
  - 파일 디스크립터 중 하나라도 열려 있으면 계속 열린 상태로 유지됨
  - 파이프의 쓰기 끝이 모두 닫혀야 읽기 끝에서 EOF를 반환할 수 있음
- 따라서 파이프의 읽기 끝에서 EOF를 반환하려면 쓰기 끝 파일 디스크립터가 모두 닫혀야 함
  - 쓰기 끝 파일 디스크립터가 열려있는 한, 파이프의 읽기 끝에서는 EOF를 반환하지 않음

### Safe Pipe and Fork
- ![image](https://user-images.githubusercontent.com/102513932/232304926-d94c540d-b139-462f-b9fe-7bf19a831013.png)
  - 안쓰는 쪽을 닫아줌으로써 pipefd[1]이 닫히면 pipefd[0]에서 EOF를 올바르게 return 할 수 있음
  - 만약 write와 read의 방향을 바꾸고 싶다면, 새로운 pipe를 생성해야 함

### Copying a Descriptor
```c
#include <unist.h>
int dup(int fd);
// ex) fd2 = dup(fd1)
// dup()는 file descriptor를 copy 하는 system call임
// 열린 파일 디스크립터를 인수로 받아 새로운 파일 디스크립터에 복사본을 만듬
// 새 파일 디스크립터는 가장 낮은 번호의 사용 가능한 파일 디스크립터를 사용하게 됨
int dup2(int oldfd, int newfd);
// dup2(fd1, fd2)
// dup2(fd1, 1) -> 1번 파일 디스크립터가 가리키는 곳을 fd1이 가리키는 곳으로 바꿈
// 첫 번째 인자로 원본 파일 디스크립터를, 두 번째 인자로 복사본을 만들어 사용하고자 하는 대상 파일 디스크립터를 받음

// dup과 dup2() 모두 원본과 복사본 파일 디스크립터가 같은 파일 테이블 항목을 공유하게 되므로, 둘 중 하나를 수정하면 다른 하나도 영향을 받게 됨
// 여기서 수정의 의미는, 오프셋 변경에 한정지음
```

### Redirecting Standard Output
```c
int fd;

fd = open("output.txt", O_WRONLY|O_CREAT, 0666); //(1)
dup2(fd,1); //(2)
// 1번 파일 디스크립터가 fd를 가리키게 함
close(fd); //(3)
// fd를 닫음
// fd를 닫는다고 해서 1번 파일 디스크립터도 닫히는건 아님

puts("Redirected output!");
// stdout인 터미널로 출력되는게 아닌, 1번 파일이 가리키는 "output.txt"로 해당 문장이 출력되게 됨

// OFT에서 refcnt는 (1)에서 1, (2)에서 2, (3)에서 1이 됨
```

- ![image](https://user-images.githubusercontent.com/102513932/232307277-c5ba04b0-657b-4b1a-9bfc-512b44aa69ee.png)
- ![image](https://user-images.githubusercontent.com/102513932/232307308-046ce9b7-3235-4f79-893c-e81d2bacd025.png)
  - call dup2(4,1)의 경우

