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
  - [Virtual Memory](#virtual-memory)
    - [A System Using Physical Addressing, Virtual Addressing](#a-system-using-physical-addressing-virtual-addressing)
    - [Virtual Memory](#virtual-memory-1)
    - [Process, Physical Layout](#process-physical-layout)
    - [Memory Management Unit(MMU)](#memory-management-unitmmu)
    - [VM as a Tool for Caching](#vm-as-a-tool-for-caching)
    - [DRAM Cache Organization](#dram-cache-organization)
    - [Enabling Data Structure: Page Table](#enabling-data-structure-page-table)
    - [Demand paging](#demand-paging)
    - [Localiy to the Rescue Again](#localiy-to-the-rescue-again)
    - [Virtual Memory as a Tool for Memory Management](#virtual-memory-as-a-tool-for-memory-management)
    - [VM as a Tool for Memory Protection](#vm-as-a-tool-for-memory-protection)
  - [Memory mapping and Copy-on-Write(CoW)](#memory-mapping-and-copy-on-writecow)
    - [Sharing Revisited: Shared Objects](#sharing-revisited-shared-objects)
    - [Copy-on-Write(CoW)](#copy-on-writecow)
    - [fork Function Revisited](#fork-function-revisited)
    - [Copy-on-Write the Resque!](#copy-on-write-the-resque)
    - [The fork Function Revisited](#the-fork-function-revisited)
    - [execve Function Revisited](#execve-function-revisited)
    - [User\_Level Memory Mapping](#user_level-memory-mapping)
    - [Example: Using mmap to Copy Files](#example-using-mmap-to-copy-files)
    - [mmap vs malloc](#mmap-vs-malloc)
    - [Advantages of using mmap](#advantages-of-using-mmap)
    - [The C stack](#the-c-stack)
  - [Address Translation](#address-translation)
    - [Address Translation With a Page Table](#address-translation-with-a-page-table)
    - [Address Translation: Page Hit](#address-translation-page-hit)
    - [Page tables in memory, like other data](#page-tables-in-memory-like-other-data)
    - [Speeding up Translation with a TLB](#speeding-up-translation-with-a-tlb)
    - [Multi Level Page Tables](#multi-level-page-tables)
    - [Cute Trick for Speeding Up L1 Access](#cute-trick-for-speeding-up-l1-access)
  - [Functions and Stack](#functions-and-stack)
    - [Stack](#stack)
    - [Stack Operation](#stack-operation)
    - [Variable Declarations](#variable-declarations)
    - [Automatic Variable](#automatic-variable)
    - [Function Call Nesting](#function-call-nesting)
    - [Stack Frames](#stack-frames)
    - [Example Stack Frame](#example-stack-frame)
    - [A Call Stack](#a-call-stack)
    - [Memory alignment code](#memory-alignment-code)
  - [POSIX Signals](#posix-signals)
    - [POSIX Signals](#posix-signals-1)
    - [Multitasking / Concurrency](#multitasking--concurrency)
    - [Signal](#signal)
    - [Asychronous Reception](#asychronous-reception)
    - [Signal List](#signal-list)
    - [Signal Concepts](#signal-concepts)
    - [Sending Signals](#sending-signals)
    - [Example](#example)
    - [Signal Handler](#signal-handler)
    - [Example2](#example2)
    - [Handling Signals](#handling-signals)
    - [Example 3](#example-3)
    - [Example 3\_2](#example-3_2)
    - [Signal Reception](#signal-reception)
    - [Shared Data](#shared-data)
    - [Example 4](#example-4)
    - [Signal Concurrency](#signal-concurrency)
  - [Unix Shell](#unix-shell)
    - [Shell](#shell)
    - [Words](#words)
    - [Statements](#statements)
    - [Builtin Commands](#builtin-commands)
    - [External Commands](#external-commands)
    - [Variables](#variables)
    - [Command Interpolation](#command-interpolation)
    - [Environment](#environment)
  - [Globbing](#globbing)
    - [Pipes and Redirection](#pipes-and-redirection-1)
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


## Virtual Memory
### A System Using Physical Addressing, Virtual Addressing
- 실제 주소 mapping으로 프로그램을 그대로 memory에 올리는 방식
  - 보안 취약성, 정보 노출 위험성 존재
  - 여러 프로세스가 하나의 cpu 사용 시 메모리 관리가 어려움
  - 옛날 차량이나, 엘리베이터 등 굉장히 단순한 시스템이나 예전 시스템에 사용
- MMU(memory management unit)사용, virtual memory로 상대주소 사용
  - 여러 프로세스 구동 및 보안 용이

### Virtual Memory
- progrram의 address space와 memory의 physical layout을 분리
  - virtual address는 프로그램 주소공간에 위치
  - physical address는 DRAM과 같은 hardware에 위치
- virtual memory 사용 시 위 2개는 같을 필요 없음
- Why?
  - DRAM을 virtual address의 캐시로 사용, main memory를 효율적으로 쓸 수 있음
    - physical memory보다 virtual memory 공간이 훨씬 더 큼
  - 각 프로세스가 자신만의 linear address space를 갖기 때문에 메모리 운영이 쉬움
  - 주소 공간을 독립시켜서 프로세스 간 간섭이 불가하게 함

### Process, Physical Layout
- Process Layout
  - 모든 프로세스는 NULL을 가리키는 unmapped memory를 지님
  - 모든 address space(virtual address)에 접근 가능
  - 각 프로세스는 다른 프로세스의 메모리에 접근 불가능함
  - 이 개념들은 서로 모순적이지만, virtual 메모리가 이를 가능하게함
- Physical Layout
  - RAM은 머신마다, 플랫폼마다 서로 다르며 OS마다 제한된 영역도 다름
  - 따라서 역사적으로 프로그램들은 여러 제한을 겪었음
  - 현재는 가상 메모리가 이러한 문제를 해결함
  - 그렇다고 kernel이 physical layout을 몰라도 된다는건 아님

### Memory Management Unit(MMU)
- MMU는 address를 translate함
  - cpu가 virtual address를 통해 메모리를 사용하려고 함
  - mmu가 virtual address를 받아서 physical address로 변환
  - 변환한 주소로 physical memory로 접근
- virtual address로 DRAM에 바로 접근한다는 것은 틀린 표현임(Physical address로만 접근 가능)

### VM as a Tool for Caching
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/4ffaad1e-c693-4b88-9884-5f7cf3e4b405)
  - cpu는 mmu를 통과하기 전까지 Virtual Address 사용
  - Main memory(Physical memory)는 Physical Address 사용
  - MMU가 관리하는 page map(page table)존재
    - MMU는 보통 CPU 내부에 위치하고, Page Table은 보통 메인 메모리에 위치함
    - virtual address와 physical address의 호환에 대한 정보가 담겨있음
    - 모든 virtual address가 translation을 갖고 있지 않을 수도 있음
  - 모든 data가 DRAM(Main memory)에 있는게 아니라, disk에도 존재함
  - 서로 다른 VP가 같은 PP를 가리킬 수도 있음 (ex, shared library)

### DRAM Cache Organization
- DRAM은 virtual memory의 cache 형태로 사용됨
  - DRAM은 SRAM보다 50~ 100배 느림
  - Disk는 DRAM보다 10000배 느림
- 결과
  - 4KB ~ 4MB에 해당하는 Large page size
    - 큰 페이지 크기 사용으로 메모리 관리에 필요한 오버헤드를 줄임
  - Fully associative(완전 연관성)
    - 가상 페이지를 물리 페이지에 자유롭게 매핑할 수 있게함
    - 다만, 이로 인해 mapping function이 복잡해짐
  - 정교하고 비싼 교체 알고리즘
    - 어떤 페이지를 교체할 것인지 결정
  - Write-back rather than wirte-through
    - Write-back
      - 수정된 데이터가 캐시에만 반영, 비교적 늦게 메인 메모리에 반영
      - 메모리 버스의 트래픽을 줄임
    - write-through
      - 데이터가 수정될 때마다 캐시와 메인 메모리 모두 반영
      - 트래픽 증가

### Enabling Data Structure: Page Table
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/5e386fdb-44cf-4e49-9bc8-bb649a0b133a)
  - VP는 Virtual Page, PP는 Physical Page
  - Page table은 page table entries의 배열임
  - page table entry에는 virtual address를 physical address로 변환하는 정보가 들어있음
    - Phsyical **Physical page number**나 disk 주소를 지님
  - physical memory 순서 상관없음(어차피 fully-associate)
  - Page hit
    - VP1,2,7,4
    - CPU가 참조하려는 Virtual Page가 Physical memory에 로드된 페이지(Physical page)에 해당하는 경우
    - MMU가 빠르게 가상 주소를 물리 주소로 변환할 수 있음
    - Valid값이 1임
  - Page fault
    - Physical memory에 로드되지 않은 Virtual Page에 엑세스하려고 할 때 발생
    - VP3, 6
    - Physical memory에 없는 경우
    - Valid값이 0임
  - Handling Page Fault
    - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/b798d8a9-2a02-40d5-a329-8a6d83768cd6)
    - MMU가 페이지 폴트를 감지하고 운영체제에 알림
    - 운영체제는 필요한 페이지를 디스크에서 찾고, 이 페이지를 물리 메모리의 사용 가능한 공간에 로드함
    - 마지막으로 운영 체제는 Page Table을 업데이트함 
    - Page fault handler가 victim을 고름
      - 교체될 대상, 현재 VP4
      - valid와 mapping 변환, Disk에서 값 가져옴
      - 재시도 -> hit

### Demand paging
- 필요할 때(page fault가 발생했을 때)만 Virtual Page를 Physical memory로 올림, 우선적으로 올리지 않음
- 전체 프로세스를 메모리에 올리는 것이 아닌, 필요한 페이지만 Physical memory로 가져옴 

### Localiy to the Rescue Again
- 가상 메모리는 비효율적으로 보이지만, locality로 인해 잘 동작할 수 있음
- 프로그램은 working set이라 불리는 virtual page 집합에만 접근하는 경향이 있음
  - 만약, 더 작은 temporal locality(시간적 지역성)을 가지면 더 작은 working set을 가질 것임
    - 시간적 지역성: 최근 접근한 데이터의 주변 공간에 다시 접근하는 소프트웨어의 패턴
- working set의 크기가 main memory의 크기보다 작으면, 처음에 데이터를 올릴 때를 제외하면(compulsory misses) 좋은 성능을 지닐 것임
  - 그만큼 더 작은 시간적 지역성을 갖고 있다는 뜻
- working set 크기의 총합이 main memory의 크기보다 크다면, page가 많이 swap(page fault)될 것이고 많은 성능 패널티가 읶을 것임
  - 이를 Thrashing이라 지칭

### Virtual Memory as a Tool for Memory Management
- 각 프로스세는 고유의 virtual address space를 지님
  - 각 프로세스는 메모리를 단순한 linear array로 볼 것임
  - 향상된 mapping을 통해 VP를 물리 메모리 전체에 분산 시키고, 지역성을 향상 시킬 수 있음
- 메모리 할당 단순화
  - 각 가상 페이지는 어느 물리페이지에든 매핑될 수 있음
- 프로세스 간 코드와 데이터 공유
  - 서로 다른 Virtual Page를 동일한 Physical Page에 저장할 수 있음
    - ex, shared library
- 링킹과 loading입장에서도, linear memory space로 접근하면 되기 때문에 매우 간편함
  - shared libraries를 위한 Memory-mapped region이 stack과 heap 사이에 존재
  - .text와 .data섹션이 page 단위로 존재
    - 사용될 때마다 로드(demanding page)

### VM as a Tool for Memory Protection
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/f404c42f-f460-492c-9a4f-e0ec7e8ad6b6)
  - Page Table entries에 여러 permission bit가 존재하고, mmu가 이를 체크함
  - 마치 memory protection처럼 동작

## Memory mapping and Copy-on-Write(CoW)
### Sharing Revisited: Shared Objects
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/ba9ecb6b-df66-49c7-97a8-bf20a73c0c58)
  - Shared object는 physical memory에서 공통된 부분으로 나타날 수 있음
  - process1에서 data를 변경했을 때, process2에서도 변경된 부분이 보일 수 있음
    - 혹은 덮어썼을 때 문제 발생 가능
### Copy-on-Write(CoW)
- common data로 메모리를 효율적으로 운영할 수 있는 것은 맞음
  - 변경 부분이 공통된 process에 보이게 하고 싶지 않을 때 CoW 사용
  - 읽을 때는 상관 없고, 데이터를 변경할때만 사용됨
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/874e6f0b-4e80-4f7d-b825-157a9195e80d)
  - 데이터를 변경하고자 할 때, 변경하고자 하는 Page를 copy해서 physical memory에 새로 load함
  - process2는 copy하지 않은 data를 계속 가리킴

### fork Function Revisited
- fork() 사용 시, 자식은 부모와 같은 virtual memory와 page table을 갖게됨
  - 처음에는 동일하지만, 디스크에서 페이지를 스왑하는 과정에서 서서히 다르게 변화하게 됨
  - 일부 변경 사항은 두 프로세스 모두에게 보일 수 있지만, 이는 일반적으로 좋지 않기 때문에 CoW를 통해 이를 해결함
    - Physical page의 매핑도 늘어나게 됨

### Copy-on-Write the Resque!
- 공유하는 것이 메모리 효율측면에서는 좋지만, 변경된 data를 공유하고 싶지 않을 떄 CoW 사용
- page table은 프로세스마다 존재
  - fork에서 page table 복제 시, 모든 page를 read-only로 복사함
  - 그리고 Data section같이 쓸 수 있는 영역은 copy-on-write 상태로 놔둠
  - 이에 대해 쓰기 작업을 시도할 때, CoW를 적용해 복사본을 만들게 됨

### The fork Function Revisited
- fork 시스템 호출 시
  - mm_struct, vm_area_struct, page table의 복사본을 만듬
    - mm_struct: 리눅스 커널에서 프로세스의 메모리 관리 정보를 저장하는 구조체
    - vm_area_struct: 가상 메모리 영역의 정보를 나타냄
  - 두 프로세스의 각 페이지를 읽기 전용으로 표시
  - vm_area_struct를 cow로 표시
- 각 프로세스는 virtual memory의 정확한 복사본을 갖게됨
- 후속 쓰기 작업은 CoW에 의해 새로운 페이지를 생성
- mm_struct
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/1cbaf083-316c-47bc-8b57-5329b5687930)
    - 프로세스 메모리 레이아웃이 구조체의 형태로 존재
    - 각각 프로세스 마다 존재
- vm_area_struct
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/18c0242f-8f08-4e38-9ad5-2569b1561e99)
    - 각각의 세그먼트가 저장되어 있음
    - 링크드리스트로 존재하며, 권한에 대한 내용 존재
    - memory mapping같이 .so 파일이 저장되는 부분은 file 형태로 존재함

### execve Function Revisited
- execve 사용 시, 다른 프로그램을 실행하고 자신을 종료함
- vm_area_struct를 전부 반환하고 새로 할당 받음
- 새로운 text의 시작 포인트로 pc값이 이동하고, main function이 실행됨

### User_Level Memory Mapping
- `void *mmap(void *addr, size_t length, int prot, int flags, int fd, off_t offset)`
  - 가상 메모리를 관리하는데 사용되는 시스템 콜
    - 파일이나 장치를 메모리에 매핑하거나 프로세스 간 공유 메모리를 생성하는 등의 목적으로 사용
  - 첫 인자는 addr이나 start
    - null값으로 넣으면 커널이 알아서 시작 주소를 설정함
  - prot: Protection 관련 권한 설정
  - flags: 특정 파일 매핑 가능
  - fd: anynomous는 -1로 설정 가능
  - 매핑 영역의 시작 주소를 리턴함
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/98229269-eba7-4df3-90bb-d2198601edaa)

### Example: Using mmap to Copy Files
```C
void mmapcopy(int fd, int size){
/* Ptr to memory mapped area */
char *bufp;
bufp = mmap(NULL, size,PROT_READ,MAP_PRIVATE,fd, 0);
write(1, bufp, size);
return;
}
//mmap()은 매핑된 메모리 영역의 시작 주소를 반환함
//이후 write를 이용하여 이 메모리 영역의 내용을 표준 출력으로 전송함 
//데이터를 사용자 공간으로 복사할 필요 없이 파일의 내용을 표준 출력으로 직접 전송
```

```c
/* mmapcopy driver */
int main(int argc, char **argv)
{
struct stat stat;
int fd;

/* Check for required cmd line arg */
if (argc != 2) {
printf("usage: %s <filename>\n",
argv[0]);
exit(0);
}

/* Copy input file to stdout */
fd = Open(argv[1], O_RDONLY, 0);
Fstat(fd, &stat);
mmapcopy(fd, stat.st_size);
exit(0);
}

//open으로 읽고
//Fstat으로 파일 정보를 struct stat 타입의 변수에 저장함
//stat.st_size는 파일의 크기를 나타내게 됨
//mmapcopy를 통해 열려있는 파일의 파일 디스크립터와 파일 크기를 인수로 넣고
//파일의 내용을 메모리에 매핑하고, 매핑된 내용을 표준 출력으로 전송함 
```

### mmap vs malloc
- mmap은 system call이고, malloc은 그냥 interface임
- mmap은 큰 메모리 할당, malloc은 작은 memory 할당
  - malloc에서 할당된 data는 heap에만 저장되는게 아니라, threshold(128KB)를 넘어가게 되면 Memory mapped region에 할당됨
  - mmap은 Memory mapped region에 할당됨

### Advantages of using mmap
- Lazy loading
  - 필요할 때까지 Virtual Page를 Physical memory에 로드하지 않음
- Memory management
  - munmap()은 메모리를 즉시 운영체제에 반환하지만 free()는 메모리를 OS에 반드시 반환하는 것은 아님
  - 예를 들어, free()로 반환하는게 100Byte라 가정
    - Physical Page의 단위는 4KB이기 때문에 100 Byte를 해제한다 하더라도 나머지 페이지가 아직 비어있지 않다면 이 페이지는 반환되지 않고 남아있게 됨, 이는 효율적이지 않음
    - 다만 munmap()은 page를 그대로 os에 반환함

### The C stack
- 프로그램의 스택은 커널이 관리한다고 배웠지만, 더 정확하게는 kernel은 스택을 관리하기 위해 mmu를 구성함
- 커널은 스택의 크기를 결정하고, 아직 물리 메모리에 로딩되지 않은 Virtual Page들을 읽기와 쓰기가 가능한 상태로 설정함
- Physical memory에 올라가 있는 Virtual Page보다 더 많은 공간이 스택에 필요해지면, 마찬가지로 page fault가 발생하고 새로운 page가 할당됨.

## Address Translation
### Address Translation With a Page Table
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/99e23eaa-eb05-449b-a997-299c4f138081)
  - VPN(Virtual Page Number)
    - 페이지 테이블에서 해당 페이지를 찾는 데 사용되는 인덱스
    - VPN을 통해 page table에 접근하여 PPN(Physical Page Number)로 변환
      - 이후 PPN로 Physical Memory로 접근
    - 이 과정에서 PTBR 이용
  - PTBR(Page Table Base Register)
    - 현재 프로세스의 Physical page table의 address를 지님
    - TLB miss가 난 경우 사용하여 Page table로 접근
  - VPO(Virtual Page Offset)
    - 패이지 내에서 특정 바이트를 찾는데 사용되는 인덱스
    - 4KB(2^12 Byte) Page 단위에서, 총 12bit 차지
    - VPO는 PPO로 그대로 사용됨


### Address Translation: Page Hit
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/e299fa52-832f-4835-b7e4-a4fa3977b597)
  - CPU에서 Virtual Addtrss를 MMU로 보냄
    - MMU는 CPU안에 존재함
  - PTBR에서 얻은 PTEA(Page Table Entries address)를 통해 Page Table에 접근하여 PTE(Page Table entry)를 얻어옴
    - 이때, Page Table은 main memory에 있음
    - PTE에는 virtual address를 physical address로 변환하는 정보가 들어있음 기억하라
  - PTE의 valid bit이 1이면 hit, VPN을 PPN으로 변환하여 Physical Address를 얻음
    - 이때부터는 L1 Cache ~ main memory에 접근

### Page tables in memory, like other data
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/4efe0121-2dd1-4f31-aa89-18e8bb9c55fd)
  - 만약에 PTE가 다른 메모리처럼 캐시에서 관리된다고 가정
  - 데이터를 넣기에도 모자란 캐시에, PTE까지 넣으면 용량이 아까움
  - PTE에서 fault 발생 시, 오버헤드가 중첩될 가능성 또한 존재함

### Speeding up Translation with a TLB
- TLB(Translation Lookaside Buffer)
  - 실제 MMU는 TLB를 통해 address translation를 캐싱하고 있음
    - 여기서 address translation은 vm->pm이므로, PTE를 캐싱한다고 봐도 무방함
- TLB Hit
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/9a577349-cd9c-4509-8a92-387a14c5eb54)
  - TLB도 CPU에 있음 주의
  - MMU가 VA를 받으면 바로 main memory의 page table로 access 하는게 아닌, TLB에 VPN이 있는지 확인함
  - 있다면 TLB에서 PTE를 반환 후 바로 cache로 접근
  - 즉, Page Table로 접근하지 않아도 되기 때문에 Main memory에 접근하지 않아도 됨
- TLB Miss
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/d8d26b41-3ab2-4d79-bb7f-83a1fdff4113)
    - TLB Miss가 발생한 경우 Page Table(Main memroy)로 접근하여 PTE를 가져옴
    - 가져온 PTE를 TLB에 저장하고, MMU에도 전달함
    - 가져온 PTE를 바탕으로 PA로 변환, cache에 접근함
- cf
  - TLB 크기가 작아도 90% 이상의 address translation을 커버할 수 있음

### Multi Level Page Tables
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/9e3d7b6e-c0d2-4ef8-b2a3-33327f95bfac)
  - 각 프로세스마다 page table을 지님
  - 따라서 Page Table은 단순 array 형태로 저장되지 않고 tree 형태로 저장됨
  - 멀티 레벨로 관리됨
    - 주로 4~5 level이 있으며 각 level이 같은 크기를 지님
    - 이를 통해 계층적 접근
  - 따라서 virtual address는 48~57 bit를 지님
    - VPO(12 bit) + VPN(9bit *4~5)
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/b52a11eb-27c0-4c37-9b0c-e29461665c73)
  - VPO는 PPO로 바로 변환됨
  - VPN은 TLB Hit 시에는 TLB를 통해 PPN으로 변환되고
    - TLB Miss 시에는 계층별로 4단계로 나눠져서, 단계별로 PTE를 찾게됨

### Cute Trick for Speeding Up L1 Access
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/dd4ad21c-8916-4572-adda-628e9e5393c1)
  - 그런데 VPN이 PPN으로 변환되기 전, 미리 cache를 조회할 수 있는 방법이 있음
  - VPO와 PPO는 같기 때문에 이를 이용하여 cache를 미리 조회하는 것(Virtually indexed)
    - 캐시 라인은 고유한 인덱스를 갖는데, 이 인덱스를 결정하는 데 필요한 비트를 VPO가 포함하고 있기 때문에 VPO만으로 캐시 라인의 위치를 결정할 수 있음
    - 이를 통해 CPU Cycle을 1 Cycle 줄일 수 있고, 5~10퍼 정도의 성능 향상을 꾀할 수 있음
    - 다만, VPO의 12bit 안에 Cache index가 있어야 가능함
      - 즉, 캐시의 크기가 페이지 크기 이하여야 함
    - 따라서 L1 Cache를 무작정 늘리면, 오히려 성능이 안좋아질 수 있음
  - 이를 통해 주소 변환 과정의 시간을 단축할 수 있지만, 다른 가상 주소가 동일한 물리적 주소를 가리킬 수 있음
    - 이를 해결하기 위해 캐시 항목에 태그를 붙여 관리함(Physically Tagged)
      - 캐시 태그(CT)를 통해 구분
    - 즉, 실제 캐시 항목의 유효성 검사는 물리 주소를 사용해 이뤄짐
  - 결국 tag를 확인할 때 Physical address에 대한 정보가 있어야 되기 때문에 똑같은 시간을 기다려야 하는 것이 아닌가 하는 의문점이 들 수 있음
    - 가상 주소를 사용한 캐시 접근과 주소 변환이 병렬적으로 이뤄지기 때문에, 캐시를 조회하는 시간을 줄일 수 있음


## Functions and Stack
### Stack
- automatic variable은 그냥 local variable이라 생각
- stack도 platform에 따라 구현하는 방식이 달라짐
- stack은 메모리에서 아래 방향으로 grow 함을 기억하라
  - base가 high address
  - top이 lower address임
  - 데이터가 들어올 수록 높은 메모리 주소에서 낮은 메모리 주소로 향함
    - ex) base의 주소: 31
  - push는 top을 아래방향으로 증가, pop은 top을 윗 방향으로 증가
### Stack Operation
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/91034c4c-62ee-495b-89fb-2dc765243364)
  - `push int i=42;`
    - 4 byte만큼 저장, top 위치 변동
  - `push double d = 2.0;`
    - alignment를 위해 padding 추가(4 Byte)
    - 8 byte만큼 저장, top 위치 변동
  - `push struct{ int x=3; int y=5; }`
    - y가 먼저 저장되고 x가 나중에 저장됨
    - stack에서는 마지막에 사용할 데이터를 먼저 저장하기 때문
      - x를 먼저 선언했으므로, x를 먼저 사용할 것이라 판단하고 x를 마지막에 저장하는 것
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/bbd32f2b-8327-4425-988a-51899e07b3b3)
  - d 주소를 access 하려면 top+8을 하면 됨
  - top은 register에 저장되어 있고, 이를 stack pointer라고 지칭함
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/d04fa87d-14c1-4395-bfca-c345d9be4545)
  - function 종료 시, stack이 다 반환되고 top이 그만큼 올라옴

### Variable Declarations
- 변수 선언 시
  - 컴파일러가 data를 위해 stack 공간을 예약함(메모리 할당)
  - 데이터의 메모리 공간에 이름을 부여
    - 프로그래머가 데이터를 참조하고 조작 가능하게 함

### Automatic Variable
- 모든 로컬 변수는 자동 변수임
  - 함수나 메서드의 호출이 종료되면 자동 해제
  - 코드 블록 내에서만 유효함
  - 사용되기 전에 allocation 되있어야 함
  - function이 끝날 때 까지는 valid 해야함
- 지역변수에 관해서는 프로그래머가 순서나 위치에 대해 예측할 수 없음
  - 컴파일러가 알아서 지정하기 때문
  - 보통 register를 이용해 function 동작 시 사용됨
- 그렇다고 구조체 내부 순서까지 변동되진 않음
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/4c0200f2-5ddd-4d04-94f4-3c247353ae44)

### Function Call Nesting
- 함수 호출 중첩
  - 한 번에 2개의 함수를 부를 수는 없음
  - can call only one function at a time
- Function call simplify
  - 새로운 function location(Program Counter)로 jump
  - function code 실행
  - 이전 pc값(pc+4)으로 돌아옴
- complicated
  - automatic variable 할당
  - 다른 function call(pc값 저장)
  - register값 일시 저장
    - 함수가 레지스터를 사용하려고 하는데, 다른 값으로 이미 채워져 있을 때
    - 레지스터의 현재 값을 스택에 push하고, 작업을 완료하고 다시 pop하여 복원
  - 이러한 경우 함수가 스택 프레임을 필요로 함
    - 스택 프레임은 함수의 실행 상태를 유지하는데 필요한 모든 정보를 보관
    - argument, automatic(local) variables, return address, temporarlly save register..
 
### Stack Frames
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/27cd522b-5702-49d7-bfe9-be4d2f09ef01)
- 스택이 증가하는 방향으로 function 마다 stack frame이 잡히게 됨
  - processor registers
    - 위 설명처럼 스택을 이용하여 register를 자신만의 것처럼 사용 가능
  - local variables 저장
    - 모든 local variable이 한 번에 stack에 allocate됨
    - 컴파일러가 만들어낸 명령어에 따라 stack pointer의 증가분으로 size를 유추하는 것이지 size가 실제로 저장되진 않음
  - 호출된 함수의 인수
    - 호출하는 함수는 호출되는 함수에게 인수를 전달해야 하기 때문
    - 6개 이상 사용 시 성능이 안좋아짐
      - 보통 register를 6개 정도 이용하기 때문
    - 구조체가 저장하는 논리처럼, 스택에 push될 때 reverse order로 push됨
  - 호출된 함수의 반환 위치
    - program counter값 저장(사실+4가 저장)
    - 프로세서에 있는 machine instruction의 주소

### Example Stack Frame
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/27cd522b-5702-49d7-bfe9-be4d2f09ef01)
  - exactly which elements are part of which frame is negotiable
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/c75105bf-470c-4fba-b841-e12ea3f6e858)
  - 할당 시
    - Set-up code
    - `call` instructions
  - 해제 시
    - Finish code
    - `ret` instructions
  - `RBP`
    - Base Pointer 레지스터
    - 현재 실행중인 함수의 Base 스택 프레임을 가리킴
  - `RSP`
    - Stack Pointer 레지스터
    - 현재 스택의 Top을 가리킴
      - 가장 최근에 스택에 push된 요소
- Example
  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/e3a380ea-abbc-457f-a7e9-0838ca6cc5b5)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/59878784-b431-4374-bdab-e293f3e82d38)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/c377aa34-ecbf-4183-8f8c-e88c02ee2559)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/f30f3dbb-4fe9-4e2e-8bf7-e843ce215a35)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/802fd5a3-8976-4bda-bd89-7c58c698961c)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/21a15658-d766-45c8-8308-0e550e02c1ff)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/82501eab-8bb0-4296-9435-298ebffae648)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/fc13b4e6-70ab-493f-8ac2-920f2b058765)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/6ba19b2d-00a4-40cd-93ed-ce2492c64ded)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/8249c9da-efe2-4ec3-b245-2574ad4a86d4)

  - ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/43948ba2-f96b-4650-a378-472eef9f5195)
  -  function return이 일어나면 스택이 없어지는데, 스택 포인터만 옮겨온거지 메모리에 데이터가 남아있을 수는 있음

### A Call Stack

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/df38a69c-9150-4c9e-b748-58009c8f70e4)
  - pc값 저장

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/7c8bf11f-07cf-4be7-bfd8-caef83cf932e)
  - i variable 저장(stack pointer 이동)

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/794ec100-e2cc-4cef-9b02-65c72211a980)
  - foo 실행
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/57cb6047-9900-4d4e-bb66-f29a66075452)
  - bar()를 위한 준비
  - 여기서 두 개의 i는 서로 다른 i임
  - 다른 주소 공간에 저장됨 주의 

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/8e81431c-8a4e-4d08-bb5e-999db3077ec4)
  - 반환을 위해 foo pc값 저장

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/b1715876-2f94-4f10-a3fb-0365f7f4b4ea)
  - 함수 실행을 위한 메모리 할당
  - j값 저장 

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/0095f166-1c59-4805-a291-6179782aaeea)
  - bar() 실행

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/0bddb8ad-5631-4be5-aa62-920a7dc8217e)
  - bar() 실행, i값 변경
  - 어디에 저장된 i값이 변경되는지 잘 확인할 것

- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/44e02c87-768f-45bb-9d31-40971e0f2034)
  - bar이 사용했던 스택 모두 반환
  - 완전 clear된 것은 아니고 스택 포인터가 달라진 것일 수 있음
    - 메모리에 값이 남아있을 수 있다.

### Memory alignment code
**시험에 나옴 중요**
```c
#include <stdio.h>

// size is 8, 4 + 1, then round to multiple of 4 (int's size),
struct stu_a {
    int i; // 4 bytes
    char c; // 1 byte
    // char pad[3]; // 3 bytes padding
    // 가장 크기가 큰 변수를 기준으로 alignment 진행
    // 3byte padding
    // 따라서 총 size가 8byte가 됨
};

// size is 16, 8 + 1, then round to multiple of 8 (long's size),
struct stu_b {
    long l; // 8 bytes
    char c; // 1 byte
    // char pad[7]; // 7 bytes padding
    // 총 size가 16byte가 됨
};

// size is 24, l need padding by 4 before it, then round to multiple of 8 (long's size),
struct stu_c {
    int i; // 4 
    // char pad[4]; // 4 bytes padding after int var
    long l; // 8
    char c; // 1
    // char pad[7]; // 7 bytes padding
    // 4byte와 7byte 패딩이 추가되어 총 사이즈가 24byte가 됨
};

// size is 16, 8 + 4 + 1, then round to multiple of 8 (long's size),
struct stu_d {
    long l; // 8 
    int i; // 4
    char c; // 1
    // char pad[3];
    // 선언되는 순서를 바꿔주게 되면, 3byte 패딩만 들어가게 됨
    // 총 size 16 byte
};

// size is 16, 8 + 4 + 1, then round to multiple of 8 (double's size),
struct stu_e {
    double d; // 8
    int i; // 4
    char c; // 1
    // char pad [3];
    // stu_d와 같음
};

// size is 24, d need align to 8, then round to multiple of 8 (double's size),
struct stu_f {
    int i; // 4
    // char pad[4];
    double d; // 8
    char c; // 1
    // char pad[7];
    // stu_c와 같음
};

// size is 4,
struct stu_g {
    int i; //4
};

// size is 8,
struct stu_h {
    long l; // 8
};

// test - padding within a single struct,
// struct의 size 호출
int test_struct_padding() {
    printf("%s: %ld\n", "stu_a", sizeof(struct stu_a));
    printf("%s: %ld\n", "stu_b", sizeof(struct stu_b));
    printf("%s: %ld\n", "stu_c", sizeof(struct stu_c));
    printf("%s: %ld\n", "stu_d", sizeof(struct stu_d));
    printf("%s: %ld\n", "stu_e", sizeof(struct stu_e));
    printf("%s: %ld\n", "stu_f", sizeof(struct stu_f));

    printf("%s: %ld\n", "stu_g", sizeof(struct stu_g));
    printf("%s: %ld\n", "stu_h", sizeof(struct stu_h));

    return 0;
}

// test - address of struct,
int test_struct_address() {
    printf("%s: %ld\n", "stu_g", sizeof(struct stu_g));
    printf("%s: %ld\n", "stu_h", sizeof(struct stu_h));
    printf("%s: %ld\n", "stu_f", sizeof(struct stu_f));

    struct stu_g g;
    struct stu_h h;
    struct stu_f f1;
    struct stu_f f2;
    int x = 1;
    long y = 1;

    //시작주소 출력
    printf("address of %s: %p\n", "g", &g);
    printf("address of %s: %p\n", "h", &h);
    printf("address of %s: %p\n", "f1", &f1);
    printf("address of %s: %p\n", "f2", &f2);
    printf("address of %s: %p\n", "x", &x);
    printf("address of %s: %p\n", "y", &y);

    // g is only 4 bytes itself, but distance to next struct is 16 bytes(on 64 bit system) or 8 bytes(on 32 bit system),
    printf("space between %s and %s: %ld\n", "g", "h", (long)(&h) - (long)(&g));

    // h is only 8 bytes itself, but distance to next struct is 16 bytes(on 64 bit system) or 8 bytes(on 32 bit system),
    printf("space between %s and %s: %ld\n", "h", "f1", (long)(&f1) - (long)(&h));

    // f1 is only 24 bytes itself, but distance to next struct is 32 bytes(on 64 bit system) or 24 bytes(on 32 bit system),
    printf("space between %s and %s: %ld\n", "f1", "f2", (long)(&f2) - (long)(&f1));

    // x is not a struct, and it reuse those empty space between structs, which exists due to padding, e.g between g & h,
    printf("space between %s and %s: %ld\n", "x", "f2", (long)(&x) - (long)(&f2));
    printf("space between %s and %s: %ld\n", "g", "x", (long)(&x) - (long)(&g));

    // y is not a struct, and it reuse those empty space between structs, which exists due to padding, e.g between h & f1,
    printf("space between %s and %s: %ld\n", "x", "y", (long)(&y) - (long)(&x));
    printf("space between %s and %s: %ld\n", "h", "y", (long)(&y) - (long)(&h));

    return 0;
}

int main(int argc, char * argv[]) {
    test_struct_padding();
// stu_a: 8
// stu_b: 16
// stu_c: 24
// stu_d: 16
// stu_e: 16
// stu_f: 24
// stu_g: 4
// stu_h: 8

    test_struct_address();
// stu_g: 4
// stu_h: 8
// stu_f: 24
// address of g: 0x7ffeb4c353c8
// address of h: 0x7ffeb4c353d0
// address of f1: 0x7ffeb4c353e0
// address of f2: 0x7ffeb4c35400
// address of x: 0x7ffeb4c353cc
// address of y: 0x7ffeb4c353d8
// space between g and h: 8
// g가 4바이트고 h가 8바이트 이기 때문에 4바이트 패딩이 생김

// space between h and f1: 16
// h는 8바이트고 f1은 24바이트.
// f가 double형을 포함하고 있기 때문에 이 간격을 맞추기 위해
// 8바이트 패딩이 생긴다고 생각하자. 

// space between f1 and f2: 32
// 쨌든 str_f의 시작주소는 8바이트 경계에 맞춰져있어야 한다.
// 8바이트 패딩이 생김

// space between x and f2: -52
// space between g and x: 4
// space between x and y: 12
// space between h and y: 8

// 흠.. 너무 깊게 생각하지 말 것
    return 0;
}
```


```c
#include <stdio.h>

struct A {
    double d1;  // 8
    char c1;    // 1 // PAD[3]
    int i1;     // 4
    char c2;    // 1 // PAD[7]
    // 총 24Byte
};

// 이는 완전 연속된 주소로 들어감
// 메모리를 훨씬 적게 쓸 수 있음
// 혹은 #pragma pack(1) 사용
struct __attribute__((packed)) A_packed {
    double d1;  
    char c1;    
    int i1;
    char c2;
    //총 14Byte
};

int main()
{
    struct A a;
    struct A_packed a_p[2];
    
    
    printf("sizeof A = %ld\n", sizeof(a));
    printf("address of a.d1 = %08x\n", &a.d1);
    printf("address of a.c1 = %08x\n", &a.c1);
    printf("address of a.i1 = %08x\n", &a.i1);
    printf("address of a.c2 = %08x\n", &a.c2);
    
    printf("sizeof A_packed = %ld\n", sizeof(a_p));

    printf("address of a_p[0].d1 = %08x\n", &(a_p[0].d1));
    printf("address of a_p[0].c1 = %08x\n", &(a_p[0].c1));
    printf("address of a_p[0].i1 = %08x\n", &(a_p[0].i1));
    printf("address of a_p[0].c2 = %08x\n", &(a_p[0].c2));

    printf("address of a_p[1].d1 = %08x\n", &(a_p[1].d1));
    printf("address of a_p[1].c1 = %08x\n", &(a_p[1].c1));
    printf("address of a_p[1].i1 = %08x\n", &(a_p[1].i1));
    printf("address of a_p[1].c2 = %08x\n", &(a_p[1].c2));
    
// sizeof A = 24
// address of a.d1 = e213e760
// address of a.c1 = e213e768
// address of a.i1 = e213e76c
// address of a.c2 = e213e770
// sizeof A_packed = 28
// 배열로 선언해도 둘이 완전 붙어서 선언됨!
// 패딩을 없앨 수는 있지만, 정렬되지 않은 data이기 때문에 조심해야 함
// address of a_p[0].d1 = e213e780
// address of a_p[0].c1 = e213e788
// address of a_p[0].i1 = e213e789
// address of a_p[0].c2 = e213e78d
// address of a_p[1].d1 = e213e78e
// address of a_p[1].c1 = e213e796
// address of a_p[1].i1 = e213e797
// address of a_p[1].c2 = e213e79b
// 주소값을 잘 살펴보면, 패딩이 없는 것을 알 수 있다.
    
    
    
    return 0;
}
```

## POSIX Signals
### POSIX Signals
- pipe와 같은 interprocess communication임
- 프로그램에서 concurrency(동시성)을 만들 수 있음

### Multitasking / Concurrency
- 여러 syscall을 기억해보자
- fork() -> 새 process 생성
- exit() -> 현재 process terminate
- wait(), waitpid() -> wait, reap terminated children
- execve() -> 현재 process에서 새 program 실행

### Signal
- signal은 process에게 특정 이벤트를 알리는 small message임
  - signal은 id를 제외한 data를 delivery 하지 않음
    - 갖는 정보는 id와 signal이 도착했다는 사실임
  - signal은 integer로 구성되어 있음
  - 커널에 의해 보내짐
    - 다른 프로세스의 요청에 따라 생길 수 있는데, 이 경우에도 커널을 통함
  - asynchronous(비동기, 동시에 똑같이 진행되지 않음)
    - 언제든지 수신될 수 있음
    - 단, 명령어의 동작은 보장하기 때문에 명령어의 동작 이후 작동


### Asychronous Reception
- signal은 block될 수도 있고 ignore될 수도 있음
  - 블록하는 경우, 시그널을 받지 않게 됨
  - 무시하는 경우, 도착했음을 알지만 따로 동작을 취하지 않음
- 시그널은 두 프로세스의 instruction 사이에서도 수신될 수 있음
  - 비동기적이기 때문
- 사용자 정의 signal handler
  - 프로세스는 signal을 수신했을 때 실행할 함수인 signal handler를 정의할 수 있음
  - 특정 시그널에 대한 응답으로서 실행됨

### Signal List
- ![image](https://github.com/googoo9918/software-project-bokha/assets/102513932/66bd9012-b008-474f-92f8-3feb197076bb)
  - SIGKILL이나 SIGSTOP은 process를 강제로 종료시키거나 멈추는 signal 이므로, caught되거나 block되거나 ignore되지 않음
- `man 7 signal`을 통해 system 기준 signal 메뉴얼 페이지를 볼 수 있음
  - 아키텍쳐, 시스템에 따라 signal이 달라질 수 있음

### Signal Concepts
- Sending a signal
  - 커널이 목표 프로세스의 특정 상태를 업데이트 함으로써 signal을 send(deliver)함
  - 내부적으로 생성된 signal
    - SIGFPE(0으로 나누려고 할 때, 부동 소수점 예외)
    - SIGCHILD(자식 프로세스가 종료될 때)
  - 외부적으로 생성된 시그널
    - ctrl+c나, kill 시스템 호출, 네트워크 끊김
  - 이처럼 프로세스 내, 외부에서 발생한 이벤트에 대한 정보를 전달함

- Receiving a signal
  - 시그널을 수신하는 것은 목표 프로세스가 커널이 보낸 시그널에 어떤 방식으로든 반응할 때 발생
  - 시그널 명시적 무시
    - 메시지는 받지만 어떠한 행동도 취하지 않을 수 있음
  - 기본 동작 실행
    - 가장 기본인 default action 수행
  - 시그널 핸들러 함수 호출
    - 사용자 정의 함수를 통해 특정한 반응을 정의함
- Default actions
  - Abort
    - Process 종료
  - Dump
    - 프로세스 종료 및 코어 덤프
    - 코어 덤프는 프로세스의 메모리 상태를 기록한 파일, 원인 디버깅 시 사용
  - Ignore
    - 시그널이 무시됨
  - Stop
    - 프로세스 정지
  - Continue
    - 프로세스가 stop 됐을 때, 다시 running state로 변경
- Signal semantics
  - 시그널이 보내졌지만 아직 받지 않은 경우, 그 시그널은 대기 중(pending)임
  - signal이 pending(보류)되는 경우, 특정 signal은 최대 한 개만 pending 되어 있을 수 있음
    - 시그널이 큐에 쌓이지 않고, 무시됨
  - 서로 다른 signal은 pending 중첩 가능
  - 시그널의 블록
    - 프로세스는 특정 시그널의 수신을 차단할 수 있음
      - 전달될 수는 있지만, 시그널이 차단 해제 될 때까지 수신되진 않음
    - 물론, SIGKILL과 SIGSTOP은 block 할 수 없음

### Sending Signals
- SIGINT
  - ctrl-c로 주로 사용되는 시그널
  - default action -> terminate process
- SIGTSTP
  - ctrl-z로 주로 사용되는 시그널
  - default action -> suspend process
- `int kill(pid_t pid, int sig)`
  - process에서 직접 signal을 보낼 수 있음
  - pid에 sig를 보냄

### Example
```c
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <signal.h>

#define N (10)

int main(void)
{
	pid_t pid[N];
	int i, child_status;
	for (i = 0; i < N; i++) { 
		if ((pid[i] = fork()) == 0) {
			while (1); /* Child infinite loop */
      // N개의 child process를 만들고, id값을 pid[i]에 저장함
      // child process는 무한 루프를 돌게 됨
		}
	}
	/*Parent terminates the child processes */
		for (i = 0; i < N; i++) {
			printf("Killing process %d\n", pid[i]);
			kill(pid[i], SIGINT);
      //자식 프로세스를 다 종료시킴
		}

	/*Parent reaps terminated children */
		for (i = 0; i < N; i++) {
			pid_t wpid = wait(&child_status);
			if (WIFEXITED(child_status)) //정상 종료시
				printf("Child %d terminated with exit status %d\n",
					wpid, WEXITSTATUS(child_status));
			else //비정상 종료시(SIGINT로 종료도 비정상 종료로 판단)
				printf("Child %d terminated abnormally\n", wpid);
		}
	return 0;
}
```
- 결과
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/416ebcfe-7f94-4231-80b0-aa3dfcc817ab)
  - signal을 받아서 처리하는 순서와 child가 죽는 순서는 무관함
  - 따라서 위와 아래의 출력 순서가 다름


### Signal Handler
- default action이 아닌 사용자 정의 action이 가능
- SIGKILL이나 SIGSTOP은 signal handler로 catch할 수 없음
- `sighandler_t signal(int sig, sighandler_t handler)`
  - sig를 받았을 때 handler에 해당하는 function을 수행하겠다는 의미
  - sighandler_t는 보통 void로 선언
  - 주의점
    - 모든 system이 `signal()`을 사용하는건 아니므로 확인해야함
  - handler
    - SIG_IGN
      - 특정 시그널 무시
      - `signal(SIGINT, SIG_IGN)` -> 프로세스가 SIGINT 시그널을 받아도 무시하도록 설정
    - SIG_DFL
      - 기본 동작을 수행하도록 설정
    - 그 외의 값
      - 특정 시그널 핸들러의 주소
      ```c
      void my_handler(int signum){
        printf("Received signal %d\n", signum);
        exit(1);
      } 

      int main(){
        signal(SIGINT, my_handler);
      }
      ```

### Example2
```c
void int_handler(int sig)
{
	printf("Process %d received signal %d\n", getpid(), sig);
	exit(22);
}
int main(void)
{
	pid_t pid[N];
	int i, child_status;
	signal(SIGINT, int_handler);
	for (i = 0; i < N; i++) {
		if ((pid[i] = fork()) == 0) {
			while (1); /* Child infinite loop */
		}
	}
	/
		*Parent terminates the child processes * /
		for (i = 0; i < N; i++) {
			printf("Killing process %d\n", pid[i]);
			kill(pid[i], SIGINT);
      //여기서 signal(SIGINT, int_handler)에 의해 SIGINT는
      // int_handler에 의해 관리됨
      // -> process getpid() receiver signal sig
      // -> Child wpid teminate with exit status 22로 동작
      // exit(22)라서 22가 출력된 거임
		}
	/
		*Parent reaps terminated children * /
		for (i = 0; i < N; i++) {
			pid_t wpid = wait(&child_status);
			if (WIFEXITED(child_status))
				printf("Child %d terminated with exit status %d\n",
					wpid, WEXITSTATUS(child_status));
			else
				printf("Child %d terminated abnormally\n", wpid);
		}
	return 0;
}
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3cd850ec-901a-4134-b66b-7fb3d6c42c2d)


### Handling Signals
- Pending signals are not queued
  - 동일한 타입의 signal이 여러 번 발생하면 그 중 하나만 기록됨
  - 시그널 핸들러가 실행중일 때, 새로 도착한 시그널은 block됨
  - 시스템 호출이 시그널에 의해 중단된 후, 자동으로 재시작되지 않는 경우가 있음
    - read()와 같은 시스템 호출은 시그널 전달로 인해 중단되면 자동으로 재시작되지 않을 수 있음
    - 이 경우, error condition을 반환
      - 오류 코드는 EINTR임

### Example 3
```c
#define N (10)
pid_t pid[N];
int ccount = 0;
void handler (int sig) {
pid_t id = wait(NULL);
ccount--;
printf ("Received signal %d from pid %d\n", sig, id);
//SIGCHLD 시그널 발생 시, wait으로 상태값 받아와서 print 출력
}
int main(void) {
int i;
ccount = N;
signal (SIGCHLD, handler);
// 자식 프로세스가 종료되면 부모프로세스에게 SIGCHLD 시그널을 보내게 됨
for (i = 0; i < N; i++) {
if ((pid[i] = fork()) == 0) {
exit(0); /* child */
//자식 프로세스를 종료시킴
}
}
while (ccount > 0)
//종료되지 않은 자식 process가 있을 때
sleep (5);
return 0;
}

// 여러개의 child process가 exit할 때 SIGCHLD라는 동일 signal을 보내게 될 것임
// 동일 signal이라서 pending 될 수 없고, 모든 child에 대해서 signal이 처리되지 않음
// wait()은 blocking 함수, 자식 process가 return값을 반환하기 전이라면
// stuck 상태로 남아있음
// 즉, signal handler가 끝나고 나서 다음 signal이 도착하면 문제가 없지만,
// signal handler가 작업중일 때 다음 signal(SIGCHLD)가 도착하면 block 되는 것임
// 실제로 실행해보니 8개의 결과만 나왔음(정상 10개)

// cf
// 만약 while((id=wait(NULL))>0) 을 사용해도 결과가 10개가 다 출력된다
// SIGCHLD signal은 씹힐 수 있지만,
// wait function이 종료된 자식 process를 다 찾아서 signal handling을 마무리한다.
// handler 호출 자체는 8번일 수 있겠으나, while문에 의하여 마무리가 가능한 것
```

### Example 3_2
```c
#define N (10)
pid_t pid[N];
int ccount = 0;
void handler (int sig) {
pid_t id;
while((id=waitpid(-1, NULL, WNOHANG))>0) {
ccount--;
printf ("Received signal %d from pid %d\n", sig, id);
}
}
int main(void) {
int i;
ccount = N;
signal (SIGCHLD, handler);
for (i = 0; i < N; i++) {
if ((pid[i] = fork()) == 0) {
exit(0); /* child */
}
}
while (ccount > 0)
sleep (5);
return 0;
}
// WNOHANG: 기다리는 PID가 종료되지 않아 
// 즉시 종료 상태를 회수할 수 없는 상황에서
// 호출자는 차단되지 않고 반환값으로 0을 받음
// 따라서 while((id=waitpid(-1, NULL, WNOHANG))>0) 으로 처리
// 아직 처리되지 않은 종료된 자식 프로세스가 있으면 계속 반복함
// SIGCHLD 시그널 핸들러가 호출될 때마다 종료된 모든 자식 프로세스를 확인
// 모두 처리할 때까지 반복함
// WNOHANG을 사용하지 않으면, 지정된 자식 프로세스가 종료될 때까지 block 상태가 됨
// 한 자식 프로세스의 종료를 기다리는 동안 다른 자식 프로세스가 종료되면
// 그에 대한 SIGCHLD 시그널을 놓칠 수 있음
// WNOHANG 옵션을 사용하면 waitpid()가 block되지 않고 바로 반환되므로
// 여러 개의 SIGCHLD 시그널이 발생해도 무시되지 않고 모두 처리할 수 있게 됨
// while문을 사용하고, 첫 번째 인자인 -1이 임의의 자식 프로세스를 대상으로
// 하기 때문에 가능한 것도 유의하자.
```

### Signal Reception
- 커널은 시그널을 언제든 전달할 수 있음
  - 시그널을 전달 받았을 때 프로세스는
    - pc를 스택에 넣음
    - signal handler로 jump
    - signal handler 실행
    - pc값 pop해서 반환받음
- function과 동일

### Shared Data
- signal이 언제 전달될지 모름
  - signal handler에서 shared data에 접근하는 것은 피하는게 좋음
  - 만약 shared data가 다른 프로세스에 의해 수정중일 때 signal handler가 데이터를 읽거나 쓴다면 데이터 오염이나 프로그램 오류가 발생할 수 있음

### Example 4
```c
void foo(int signum){
    int status;
    while(wait(&status) > 0){
        printf("status: %d\n", status);
    }
}

int main(){
    signal(SIGCHLD, foo);
    int i;
    pid_t pid;
    
    for(int j=0; j<30; ++j){
        pid = fork();
        if(pid == 0){ //child process이면
            for(i =0; i<3; ++i){
                printf("child process..\n");
                if(j>5){
                    sleep(1);
                }
            }
            exit(0);
        }
    }
    for(i=0; i<30; ++i){
        printf("parent procss..%d\n", i);
        sleep(1);
    }
    exit(0);
}
// status를 반환할 때, while이 한 번 끝나고
// child process가 return 된게 없으면 handler function이 종료되고
// parent process..%d의 문장들이 출력되어야 함
// 그런데, wait 함수는 main function을 block한 상태로 기다리기 때문에
// child process가 전부 exit 할 때(status : %d가 30번 출력 된 후)까지 
// main이 권한을 얻지 못함 따라서 다 출력된 후 parent process..%d 가 다 출력됨
// status : %d가 출력되기 전에 parent process..%d가 출력될 수는 있는데,
// 이는 어떤 child process도 아직 exit을 호출하지 않은 상태이기 때문이다.

// 만약 여기서 while(wait)이 아니라 while(waitpid(0, &status, WNOHANG)>0)을
// 사용하면 main을 blocking 하지 않기 때문에 status : %d 사이에 parent process..%d
// 가 계속해서 출력될 수 있다.

// status: %d가 연속으로 출력되는 경우는, waitpid를 하고 출력하는 도중에 새로 exit
// 한 child가 있는 경우겠지??
```

### Signal Concurrency
```c
//list 앞에 node를 하나 덧붙이는 function
void prepend(struct ListNode *node){
  node->next = list;
  list = node;
}

void handler(int sig){prepend(new_listnode());}

int main(int argc, char *argv[]){
  signal(SIGINT, &handler);
  prepend(new_listnode());
  // 현재 시그널 핸들러와 main함수가 둘 다 prepend()를 수행하고 있음
  // 이 경우, 결과를 예상할 수 있는가?
  return 0;
}

//1 void prepend(struct ListNode * node){
//2   node->next = list;
//3   list = node;
//4 }

//시그널이 도착하는 순간에 따라 결과가 다 달라짐

//line 2전에 signal이 올때, list는 2 items를 다 contain함
//아직 node->next를 list에 연결하지 않았기 때문에,
//handler가 먼저 prepend 함수 flow를 실행하게 됨
//이후 Main이 prepend를 실행하게 됨
//M(by Main)->H(by Handler..)->N->N->N->...

//line 2~3 사이에 올 때
//list는 main node만 contaion 하게 됨
//main의 line2에 의해 M 노드의 next는 list(이 전 리스트의 시작지점)을 가리키게 됨
//Handler의 line2에 의해 H 노드의 next도 list를 가리키게 됨
//Handler의 line3에 의해 list의 시작 주소가 H 노드가 됨
//Main의 line3에 의해 list의 시작 주소가 M 노드가 됨
//따라서 H와의 연결은 없어지고.. M->N->N->N...이 됨
//첫 N에 H가 연결은 되어 있겠지만, 접근할 수 있는 방법이 없음
//따라서 memory leaked(누수) 발생

//line 3 이후에 올 때
//M이 먼저 연결되고, 이후에 H가 연결되기 때문에
//H->M->N->N->N->...이 되겠지
```

## Unix Shell
### Shell
- Shell은 사용자가 시스템에 주로 접근하는 인터페이스
- system call에 대한 직접적이고 안정적인 접근을 제공
- interactive
  - command aliasing
    - `alias l = 'ls -CF`
    - l을 ls -CF의 역할을 하도록 만들 수 있음
  - Recall and modification of recent commands
    - 최근 command 호출
    - history or 방햔키 사용
  - program environment로 사용 가능
    - 권장하는 방법은 x, 애플리케이션을 만들기보다는 data를 정리하는 script 용도로 사용할 것
    - 변수, 조건문, 반복문, 프로시저, 예외 처리 가능
      - `NAME=DONG`
      - `echo $NAME` -> DONG 출력
### Words
- Everything in the shell is a string
  - shell은 이 string을 단어 단위로 쪼갬, 단어는 whitespace로 구분
  - 명령어에서 first word는 어떤 작업을 수행할지 알려줌
  - 공백을 단어에 포함하려면
    - `'`, `''`, '\' 사용
      - single, double quote 쌍 잘 맞춰줘야함
      - 특수문자 앞에는 backslash 사용
        - `echo hello\?`

### Statements
- Single statement
  - 단일 명령문은 이전 명령어가 끝나고 시작
  - newline, ;, && 로 끝나고 이는 명령어의 종료를 표시함
    - `ls;ls;ls;ls` 로 한 enter에 명령어 4개 실행 가능
    - `echo a && echo b`로 명령어 2개 실행 가능
      - 앞 명령어가 제대로 실행되면 뒤 명령어도 실행
      - `a \n b`로 출력됨

### Builtin Commands
- shell이 자체적으로 갖고있는 command
  - 외부 프로그램을 실행하지 않고, 명령어에 대한 내부 코드를 실행함
    - 새 프로세스를 생성하거나 외부 프로그램을 실행하는 오버헤드 없이 즉시 실행
  - shell의 내부 상태 변경
    - 일부 명령어는 shell의 내부 상태(internal state)를 변경해야 되는데, 이런 작업은 외부 프로그램을 통해 진행할 수 없음
      - fork 이후에는 shell의 internal state를 변경할 수 없으므로, cd와 같은 shell의 현재 작업 디렉토리를 변경하는 명령어는 내장 명령어로 구현되어야 함 

### External Commands
- builtin command를 제외하면 모두 external command임 
- `fork()`, `exec()`
  - 외부 명령어를 실행하기 위해 `fork` 호출 후 `exec`를 호출하여 외부 명령어 실행
  - 명령어의 first word는 실행할 binary file의 이름임
  - 나머지 단어는 이진 파일에 전달되는 argument 임

### Variables
- shell에서도 변수 사용 가능함
- `VAR=value`
  - `=` 앞뒤로 white space가 존재하면 안됨
- Special Variables
  - `$0`
    - first word, binary file의 이름
  - `$1-$9`
    - argument, 1~9까지 순서대로 저장됨
    - 두자리 숫자부터는 제대로 인식하지 못함
  - `$#`
    - argument 개수 저장
  - `$@, $*`
    - 전체 argument 출력
  - `$?`
    - 이전 명령어의 return값
  - `$!`
    - 이전 명령어의 process id
  - `$$`
    - 현재 명령어의 process id
  - `$IFS`
    - Input field seperator
      - string 입력 시, 기준이 되는 단어 설정
      - default는 white space
- IFS
  - 변경 가능
  - `IFS=X` , `var="AXBXCXDX"`
    - X가 공백이니까 double quote가 들어가야함
  - `echo $var`
    - A B C D 출력됨
  - 당연히 함부로 바꾸면 안되겠지?
- 변수 삽입
  - `$VAR`
    - 단순 변수 삽입 시
    - ex `Hello $VAR` -> `Hello world`
  - `${VAR}`
    - 복잡한 변수 이름이나, 추가적인 작업 수행 시
    - `${VAR}_file` -> `log_file`
      - `VAR`값 뒤에 _file 문자열을 추가함
    - 만약 `$VAR_file`로 사용하면, VAR_file 자체를 변수로 해석하게됨

### Command Interpolation
- 명령어의 출력을 다른 명령어의 삽입
- `$(command)`
  - 괄호 안의 명령어를 실행하고 출력을 삽입할 수 있음
  - ex
    - `current_dir=$(pwd)`
    - pwd는 변수가 아니라 명령어니까 소괄호를 사용함에 주의하자!!
    - pwd 명령어를 실행하여 현재 작업 디렉토리의 경로를 current_dir에 저장

### Environment
- POSIX 시스템에서 모든 프로세스는 환경을 가짐
  - 환경은 키-값 쌍의 집합
  - 환경은 부모 프로세스로부터 자식 프로세스로 상속됨
- shell 변수를 환경에 추가하기 위해서 `export` 사용
  - `export VAR [VAR2 ...]`
  - `export VAR="hello, world"`
  - `export PATH=$PATH:/my/new/directory`
- `env`를 통해 현재 환경 변수 확인 가능
- shell은 환경 조작을 위해 `setenv()`, `putenv()` 등의 함수를 사용함

## Globbing
- shell은 파일의 패턴 매칭을 통해 file 탐색이 가능함
  - `*`
    - 0개 이상의 문자열 일지시킴
    - ex `*.txt` -> .txt로 끝나는 모든 파일
  - `?`
    - 정확히 하나의 문자를 일치시킴
    - ex `?.txt` -> .txt 앞에 한 글자만 오는 모든 파일
  - `[]`
    - 대괄호 안 문자 중 하나와 일치시킴
    - 범위를 지정하여 여러 문자 일치시킬 수 있음
    - ex `[a-z].txt` -> .txt 앞에 a~z 중 한 글자가 오는 모든 파일
  - ex
    - `touch file1.txt file2.txt file3.txt`
    - `for file in * do echo "$file" done`
      - 파일 전체 출력
    - `for file in file* do echo "$file" done`
      - file로 시작하는 모든 파일 출력
    - `for file in ????????? do echo "$file" done`
      - 9글자로 구성된 모든 파일 출력
      - `.`이나 txt 같은 확장자도 글자 개수에 포함됨
    - `for file in ????[0-9]* do echo "$file" done`
      - 4글자 + 0~9까지의 숫자 한개로 시작하는 모든 파일 출력
    - `for file in *.[ch] do echo "$file" done`
      - .c나 .h로 끝나는 모든 파일 출력

### Pipes and Redirection
- 파일 디스크럽터와 파이프를 조작 가능함
  - `|`를 통해 파이프 생성 가능
    - `pipe()` 사용
  - 파일 디스크립터에 파일 열기
    - `<`, `>`, `<<`, `>>` 사용
    - `< file` : 표준 입력을 해당 파일에 연결
    - `> file` : 표준 출력을 해당 파일에 연결
    - `2> file` : 표준 오류를 해당 파일에 연결
    - `N> file` , `N< file` : 해당 파일을 N번 파일 디스크립텅 ㅔ연결
    - `>>`` 사용시, 기존 파일에 append함
      - `>`는 overwrite임
    - `dup2()` syscall 사용
  - 파일 디스크립터의 내용 복사
    - `>&`
    - `dup2()` syscall 사용
  - ex
    - `echo "Hello,World" > input.txt`
      - 문자열을 input.txt에 저장
    - `cat input.txt | wc -l`
      - pipe를 이용하여 파일의 라인 개수를 count함
    - `cat input.txt | wc -l > output.txt`
      - 파일의 라인 개수를 output.txt에 저장
    - `wc -w < /usr/share/dict/words`
      - 우측 파일 경로의 파일에서 단어 개수를 count함
    - `cut -d` ` -f5 > totals.txt`
      - 명령어의 출력을 totals.txt에 저장
    - `stats -bmean variant-a.txt > means.txt`
      - overwrite
    - `stats -bmean variant-b.txt >> means.txt`
      - append
      - 두 개의 다른 명령의 출력을 `means.txt`에 저장
- 파이프라인
  - 파이프 라인은 파이프(`|`)로 연결된 일련의 명령어임
  - 각 명령어는 표준 출력에 작성하고, 표준 입력에서 읽음
- `cmd1 | cmd2`
  - `pipe()`를 사용하여 파이프 생성
  - 각 명령어에 대해 `fork()` 실행
  - `dup2()`를 사용해 파이프의 fd(file descriptor)와 명령어의 표준 입출력 연결
    - `pipefd[1]`을 `cmd1`의 표준 출력에, `pipefd[0]`을 `cmd2`의 표준 입력에 연결함
    - cmd1의 출력이 cmd2의 입력이 되겠지
- 새 파일을 열지 않고 fd 복제 가능
  - `N>&M`은 dup2(N,M)과 동일함
    - dup2(N,M)은 M이 가리키는 곳을 N이 가리키는 곳으로 변경함을 기억하라.
  - ex
    - `echo Could not open file 1>&2`
    - `Could not open file`이라는 에러 메시지를 표준 출력에서 표준 에러로 리다이렉션