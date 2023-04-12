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

## 3주차
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

## 4주차
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