### 1
- POSIX
  - Portable operating system interface + UNIX
  - OS Kernel과 application 사이에서 shell interface나 libray를 정의하는 표준이다.
  - binary level에서는 적용되지 않으며
    - binary instruction은 같지 않기 때문에 os마다 재컴파일하고 재실행 해야한다.
  - code compatability(코드 호환성)을 목적으로 한다.
  - system call과 binary format은 정의하지 않는다
    - os kernel마다 달라지기 때문이다.
  - open, read, write, exit, wait
- Pointer
  - invalid address임
  - 입력
    - scanf
    - fscanf
      - 스트림을 지님
      - 스트림 인자 제일 앞
    - gets
      - 사용 지양, 크기를 지정하지 않기 때문에 버퍼 오버플로우가 발생할 수 있음
    - fgets
      - 스트림을 지님
      - 스트림 인자 제일 뒤
  - 출력
    - printf
    - fprintf
    - puts
    - fputs
- ToolChain
  - 전처리기
    - .i 파일 생성, #ifdef, #define 등 전처리 지시자 resolve
    - -E
  - 컴파일러
    - .s 파일 생성, machine-defendent한 파일 생성
    - -S
  - 어셈블러
    - .o 파일 생성, machine-excutable instructions 생성
    - -c
  - 링커
    - excutable 파일 생성
    - -o
  - unresolved symbols는 외부에서 선언된 변수로, 링커를 통해 resolve 되거나 외부 library를 통해 resolve 된다.
  - -Wall, Werror, O2, -std=c99
- Word
  - word는 아키텍쳐에서 정해지는 메모리의 기본 단위로, native integer size이며, cpu에서 한 번에 처리할 수 있는 데이터의 단위이다.
  - Bus는 데티어 전송 공간이다.
    - 1wire당 1bit 차지
    - 실제 데이터가 10bit면 .. 22bit 버려지는 예시
- Padiing
  - Padding은 데이터를 정렬해서 저장하기 위해 추가하는 공간이다.
    - 사용하지 않으면, 정렬되지 않는 데이터에 접근할 떄 Bus error를 일으키거나, 성능이 떨어질 수 있다.
    - 또한, 내가 숨기고싶은 데이터가 노출되는 등의 상황이 이러날 수 있음
- Pointer to structs
  - stride
    - double * ptr; ptr + 1 = 0 + 8
    - (char *)&complex
    - malloc(sizeof(struct complex));
- const char *buffer = mem
  - mem을 1바이트씩 짤라서 보겠음
  - 리틀 엔디안은.... 큰 바이트의 값을 낮은 주소부터 저장하는 것이지
  - 1의 보수 0 표현 --> 0000, 1111
  - 2의 보수 0 표현 -> 0000
- Process
  - 프로그램은 단순 binary instructions임
  - 프로세스는 독자적인 메모리와 system resource를 지님
  - UNIX는 프로세스가 독립된 환경에서 구동하는 것을 보장함
    - 프로세스가 독자적인 메모리를 지니게 함
    - CPU가 프로세스마다 할당되어 있는 것처럼 느끼게 함
    - 시스템 서비스가 프로세스에 헌신하는 것처럼 느끼게 함
    - 이를 위해서는 harware assistance가 필요한데, 커널이 이를 관장함
  - 로딩
    - kernel이 시작하며, ld-linux.so가 종료함(dynamic linker/loader shared object)
    - kernel이 ELF의 부분과 로더를 메모리로 이동시킴
    - kernel이 로더를 호출하여 프로그램에서 데이터를 변경시킴
    - 로더가 프로그램 시작 지점으로 jump
  - section -> linker, segment -> loader
  - ELF header에는 format, endianess
  - segment table : 로더에게 데이터의 위치를 알려줌
  - section table : 링커에게 섹션의 정보를 알려줌
  - Text
    - binary instructions, 명령어 저장
  - BSS
    - 초기화되지 않은 전역변수
  - Data
    - 초기화 된 전역변수
  - text, data는 elf에서 memory layout으로 바로 전달
  - bss는 실행 시점에 동적으로 커널이 데이터를 할당함
    - section에 변수의 정보는 포함되어 있지만, 실제 값이 들어가 있지는 않음
    - 실제 값은 실행 시점에 운영체제에 의해 0으로 초기화됨
  - stack,heap은 kernel에 의해 결정됨
    - size 조절은 loader?
    - un-free memory는 OS에 의해
  - brk는 heap 영역에 존재하는 break point로, 이동 가능
  - heap segment는 할당된 영역보다 더 큰 공간을 사용하려고 할 때 발생함
  - 다른점
    - 현재는 section을 랜덤화 하여 보안성을 높임
    - 실행파일에 포함되지 않는 외부 라이브러리 존재
  - static vs dynamic
    - size: 컴파일 시점에 결정됨 vs 실행 중 동적으로 변경됨
    - release: 프로그램 종료 시 vs 영역이 종료되거나, free 호출될 때
    - resize: ?
  - function
    - argument를 특정 스택이나 register에 저장
    - 현재 pc값 stack에 저장
    - 현재 pc값을 실행하고자 하는 function의 pc값으로 변경
    - return 값 register에 저장
    - stack 조회
    - 저장했던 pc값으로 변경
  - syscall
    - syscall argument를 특정 register에 저장
    - syscall number를 다른 register에 저장
    - syscall 호출
    - protect domain 변경(user -> supervisor)
    - kernel 영역으로 이동 후 syscall 실행
    - return 값 register에 저장
    - sysret(System return)호출
    - domain 변경 (to user)
    - 호출한 function으로 이동
- process lifecycle
  - 자식 0, 부모: 자식 pid, 에러면 <0
  - exec()는 현재 프로세스에서 다른 프로세스를 실행하는 것
  - posix_spawn은 효율적인 fork+exec)_
  - wait()
    - 자식 프로세스가 종료되었는지 확인할 수 있는..
    - 좀비는 자식이 죽었는데 reap을 안한거임
    - 매개변수? int fd일껄?
    - 반환값? 종료상태랑 뭐..
  - 고아프로세스
    - init이 부모 프로세스가 됨
  - 종료시점
    - 메인 함수 종료
- LinKing
  - 모듈화, 효율성
    - 모듈화
      - 분업화 가능
    - 공간, 시간
      - 공간 : 실행 파일의 크기를 줄일 수 있음
      - 시간 : 변경 후 별도의 재컴파일 가능
- symbol이란, 프로그램의 여러 부분에서 참조할 수 있는 식별자를 뜻한다
  - kernel에 의해 실행 파일의 symbol table에 저장됨
  - symbol table은 구조체 포인터로 구성되며, symbol의 이름, 위치 등의 정보를 포함한다.
- symbol resolution, relocation
- relocatable object
- excutable object file
- shared object
- ELF 포맷을 지님
- global symbol
  - non static 함수, 전역 변수
- extern symbol
  - 외부 참조
- local symbol
  - static 붙은 함수, 변수
- 강한게 약한걸 잡아먹음
- 강한거 여러개 있으면 오류
- 약한거 여러개면 랜덤 서택
- 웬만하면 전역변수 사용 x, 쓸거면 extern도 같이 쓸 것, static 사용 권장, 쓸거면 초기화 할 것
- 링킹 전에는 상대적 위치 저장 -> 후에는 절대적 메모리 주소 저장
- 보통 하나의 파일에 function 다 넣거나
  - 라이브러리를 따로 설치하지 않아도 됨!! 이식성이 높음
  - 용량이 과도하게 커짐
- 각 소스 파일마다 function 참조함
  - 중복 참조가 너무 많아짐
  - 필요한 바이너리만 링크, 공간 및 시간 효율 향상
- static libraires
  - 정적 라이브러리


```c
int fd, fd2, pipepd, pid;
fd = open("ex1.txt");
fd2 = open("ex2.txt");
pipe(pipepd);
pid = fork();
if(pid==0){
    dup2(fd1,1);
    close(fd);
    close(fd2);
    close(pipepd[0]);
    close(pipepd[1]);
    exit(fd2);
    write(1, "Sample", 7);
}
else
{
    int status;
    dup2(fd2,1);
    waitpid(pid,&fd2,0);
    close(fd);
    close(fd2);
    close(pipepd[0]);
    close(pipepd[1]);
    printf("abcd");
}
```

```cpp
#include <iostream>
#include <string>
using namespace std;

class Node{
public:
    char data;
    Node* next;
    Node(char data) : data(data), next(nullptr){}
};

class Stack{
private:
    Stack() : top(nullptr){}
    ~Stack(){
        Node* cur = top;
    while(cur != nullptr){
        Node* temp = cur;
        cur = cur -> next;
        delete temp;
        }
    }

    void push(char data){
        Node* node = new Node(Data);
        node->next = top;
        top = node;
    }

    char pop(){
        if(isEmpty()){
            cout << "스택이 비어있습니다." << endl;
            exit(1);
        }
        char temp = top->data;
        Node* node = top;
        top = top->next;
        delete node;
        return temp;
    }

    char peek(){
        if(isEmpty()){
            cout << "스택이 비어있습니다." << endl;
            exit(1);
        }
        return top->data;
    }

    bool isEmpty(){
        return top ==nullptr;
    }
};

bool checkBracket(string str){
    Stack stack;
    for(int i =0; i< str.length(); i++){
        char ch = str[i];
        if(ch == '(' || ch == '{' || ch == '['){
            stack.push(ch);
        }
        else if(ch == ')' || ch == '}' || ch ==']'){
            if(stack.isEmpty()) return false;
            char openCh = stack.pop();
            if((ch == ')' && openCh != '(') || (ch == '}' && openCh != '{') || (ch==']' && openCh !='[')){
                return false;
            }
        }
    }
    return stack.isEmpty();
}

int main(){
    string str = "{(a+b)*c-d";
    if(checkBracket(str)){
        cout << str << " : 괄호가 맞습니다." << endl;
    }
    else{
        cout << str << " : 괄호가 틀렸습니다."<< endl; 
    }
    return 0;
}
```