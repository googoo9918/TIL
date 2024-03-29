## 목차
- [운영체제](#운영체제)
  - [Introduction](#introduction)
    - [운영체제란?](#운영체제란)
    - [Abstraction](#abstraction)
    - [Policy, etc](#policy-etc)
  - [Operating Systems](#operating-systems)
    - [Hand operated System](#hand-operated-system)
    - [일괄 처리(Batch)](#일괄-처리batch)
    - [Automatic Job Sequencing](#automatic-job-sequencing)
    - [Spooling](#spooling)
    - [초기 Multiprogramming](#초기-multiprogramming)
    - [TimeSharing](#timesharing)
    - [Multitasking](#multitasking)
  - [Operating System Structure](#operating-system-structure)
    - [System Structure](#system-structure)
    - [Layering](#layering)
    - [User Mode와 Kernel Mode](#user-mode와-kernel-mode)
    - [Monolithic Kernel](#monolithic-kernel)
    - [Micro Kernel](#micro-kernel)
    - [Hypervisor](#hypervisor)
  - [Process](#process)
    - [컴파일러](#컴파일러)
    - [링커](#링커)
    - [로더](#로더)
    - [Process Concept](#process-concept)
    - [Process State](#process-state)
    - [PCB(Process Control Block)](#pcbprocess-control-block)
    - [Context Switch](#context-switch)
    - [프로세서(CPU) 구조에 따른 Context Swtich의 차이](#프로세서cpu-구조에-따른-context-swtich의-차이)
    - [Process Creation](#process-creation)
    - [Process Termination](#process-termination)
  - [Fork-Exec](#fork-exec)
    - [Fork](#fork)
    - [Exec](#exec)
    - [Zombie process](#zombie-process)
  - [Computer Architecture](#computer-architecture)
    - [단일 Bus 구조](#단일-bus-구조)
    - [병목현상(Bottleneck)](#병목현상bottleneck)
    - [계층적 버스 구조](#계층적-버스-구조)
    - [이벤트 처리 기법: Interrupt](#이벤트-처리-기법-interrupt)
    - [이벤트 처리 기법: Trap](#이벤트-처리-기법-trap)
    - [I/O Device Basic Concepts](#io-device-basic-concepts)
    - [I/O 처리 기법: Polling](#io-처리-기법-polling)
    - [I/O 처리 기법: DMA(Direct Memory Access)](#io-처리-기법-dmadirect-memory-access)
    - [I/O Device Access 기법: I/O Instruction](#io-device-access-기법-io-instruction)
    - [I/O Device Access 기법: Memory Mapped I/O](#io-device-access-기법-memory-mapped-io)
  - [CPU Scheduling](#cpu-scheduling)
    - [Scheduling이란 무엇인가](#scheduling이란-무엇인가)
    - [Process 수행 사이클의 구성](#process-수행-사이클의-구성)
    - [Scheduling의 종류](#scheduling의-종류)
    - [Scheduling Criteria](#scheduling-criteria)
    - [Scheduling Algorithms- FCFS](#scheduling-algorithms--fcfs)
    - [Shortest Job First Scheduling(SJF)](#shortest-job-first-schedulingsjf)
    - [Priority Scheduling](#priority-scheduling)
    - [Round Robin Scheduling](#round-robin-scheduling)
    - [Multilevel Queue Scheduling](#multilevel-queue-scheduling)
    - [Multilevel Feedback Queue Scheduling](#multilevel-feedback-queue-scheduling)
    - [Multiple Processor Scheduling](#multiple-processor-scheduling)
  - [IPC(Inter Process Communication)](#ipcinter-process-communication)
    - [Shared memory](#shared-memory)
    - [Message Passing](#message-passing)
    - [Pipe](#pipe)
    - [Signal](#signal)
    - [Shared Memory](#shared-memory-1)
    - [Message Queue](#message-queue)
    - [Socket](#socket)
  - [Thread](#thread)
    - [Thread \& CPU Utilization](#thread--cpu-utilization)
    - [Thread의 구성 요소](#thread의-구성-요소)
    - [Multi-Threaded Program 장점](#multi-threaded-program-장점)
    - [User and Kernel Threads](#user-and-kernel-threads)
    - [Mapping of User \& Kernel Thread: Many-to-One](#mapping-of-user--kernel-thread-many-to-one)
    - [Thread Issues](#thread-issues)
  - [Sychronization(1)](#sychronization1)
    - [Sharing Resources](#sharing-resources)
    - [Race Condition](#race-condition)
    - [Critical Section](#critical-section)
    - [Synchronization Instruction](#synchronization-instruction)
    - [Semaphores(세마포어)](#semaphores세마포어)
    - [Monitor](#monitor)
  - [Synchronization(2)](#synchronization2)
    - [Bounded-Buffer Problem](#bounded-buffer-problem)
    - [Readers-Writers Problem](#readers-writers-problem)
    - [Dining-Philosophers Problem](#dining-philosophers-problem)
  - [Memory Management(1)](#memory-management1)
    - [주소 공간](#주소-공간)
    - [물리 주소와 가상 주소](#물리-주소와-가상-주소)
    - [가상 메모리](#가상-메모리)
    - [Paging](#paging)
    - [Page Table](#page-table)
    - [Translation Look-aside Buffers(TLB)](#translation-look-aside-bufferstlb)
    - [다양한 Paging Table](#다양한-paging-table)
  - [Memory Management(2)](#memory-management2)
    - [Page Replacement](#page-replacement)
    - [Swapping](#swapping)
    - [Contiguous Memory Allocation](#contiguous-memory-allocation)
    - [Fragmentation](#fragmentation)
# 운영체제
## Introduction
### 운영체제란?
- 운영체제는 하드웨어를 손쉽고 효율적으로 사용할 수 있는 **Abstraction**을 제공
  - Low level이 아닌, High level에서 진행하기 위해 Abstraction 필요
    - ex) Process(CPU), Address Space(Memory), File(Disk), Port(Network)
- 자원의 공유 및 분배를 위한 Policy 결정

### Abstraction
- Program
  - 일련의 순차적으로 작성된 명령어 모음
  - Disk와 같은 Secnodary Storage에 바이너리 형태로 저장
- Abstraction: Process
  - 실행되고 있는 프로그램의 *추상화*
  - Program Counter, Stack, Data Section으로 구현
  - 왜 필요한가?
    - CPU와 같은 Hardware Component에게 각 Program을 구분하고 인식 및 실행 할 수 있도록 함
- Abstraction: Address Space
  - Process가 차지하는 메모리 공간
  - 왜 필요한가?
    - Protection Domain
      - 다른 프로세스의 주소 공간 침범 불가
        - 실행 context 보호 및 Privacy Issue
    - Memory Mapped
      - I/O Device의 관리
        - 프로세스는 매핑된 주소 공간을 통해 I/O 장치에 데이터를 읽고 쓸 수 있음
- Abstraction: File
  - Process에서 읽고 쓸 수 있는 Persistent(비휘발성) Storage
  - 실제 File이 저장되는 위치를 Process는 알지 않음
    - Disk에서 가져오는게 아니라 RAM을 통해 가져오기 때문
  - 왜 필요한가?
    - 어디까지가 Process의 Binary Data인지, 해당 Binary Data가 어디에 저장되어 있는지 관리 및 유지가 필요하기 때문
      - 운영체제가 어떤 데이터가 실행 가능한지, 어디서 시작하고 끝나는지, 필요한 메타데이터는 무엇인지 관리가 가능하게 함
- Abstraction: Port
  - 컴퓨터 시스템이 메시지를 주고 받는 Communication Endpoint
  - 왜 필요한가?
    - 어떤 Process(또는 User)가 통신의 대상인지 구분 필요(+Privacy Issue)

### Policy, etc
- Policy는 왜 필요한가?
  - PC, Server, Data Center, Smartphone, 자동차, 원자력 발전소, IoT Systems등 현재 운영체제가 쓰이는 곳은 많지만 성능, 안전, Privacy등 중요성이 다 다르기 때문
- software의 구분
  - System software
    - 시스템을 구동시키는 SW
    - OS, Compiler, Assembler, 펌웨어
  - Applcation software
    - 특정 용도로 사용
- 운영체제의 특징
  - OS는 항상 동작
  - 통제 기능으로서, 자원에 대한 관리/감시 활동
    - Supervisor Mode(<-> User Mode)
    - 어떤 process가 어떤 자원을 사용하고 있고, 어떤 자원을 할당할 것인가?
  - 하드웨어에 대한 제어 기능
    - Device Driver
- OS and Kernel
  - OS = Kernel
  - OS = Kernel + GUI + Library
  - Kernel
    - 운영체제의 핵심 부분
      - 자원 할당, 하드웨어 인터페이스, 보안 등 담당 
  - GUI
    - 그래픽 사용자 인터페이스
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e3a16e00-459e-4811-9458-51fe7e3f049a)

## Operating Systems
### Hand operated System
  - 1950년대 초반
    - 기계적인 스위치(프로그램) 이용, 1bit 단위로 컴퓨터에 입력되어 실행
    - abstraction 존재 X
  - 1950년대 중반
    - 모든 프로그램은 기계어로 쓰여짐
    - 플러그 보드에 와이어링을 통해 컴퓨터 기능 제어
    - 프로그래밍 언어 및 운영체제라는 존재 X
      - 영구적인 저장장치 X
  - 1960년대 초반
    - 펀치카드 등장
    - 프로그래밍한 카드로 컴퓨터 구동
      - 플러그 보드 대체
- Mainframe
  - Business Machinery로 쓰이며 가치 발생
  - 계산을 하는데 주로 사용
### 일괄 처리(Batch)
  - 아주 단순한 OS 개념
  - FIFO식 처리(비선점 방식)
    - 결과가 나올때 까지 중간에 User Interaction 불가능
      - 하나의 덱이 하나의 프로그램을 구성
    - 사람이 Job을 Scheduling
  - CPU는 빈번히 Idle 상태로 전환됨
    - 기계적인 I/O 장치와 전기 장치인 CPU 사이에 현격한 속도 차이 존재
### Automatic Job Sequencing
  - 좀 더 나은 OS
  - 사람의 관여 없이, 여러 개의 프로그램 순차적 실행
    - 이전 작업 종료 시 다음 작업 실행, 일괄 처리보다 성능 향상
    - 스케줄을 담당하는 소프트웨어가 프로그램을 실행
      - 하나의 덱이 여러 프로그램을 구성
  - 문제점
    - I/O에 의해 CPU가 Idle 상태로 전환되는 문제는 해결 X
- ![image](https://github.com/googoo9918/TIL/assets/102513932/daa90d50-17a0-469b-a25d-19bb98b4b0e3)
  - I/O 요청을 받을때까지 멈춰있음
### Spooling
  - 초기 해결책
  - Simultaneous Peripheral Operatiopn On-Line
  - I/O와 Computatuion을 동시에 진행
    - ex) 프린터 Spooling
      - 인쇄할 문서를 디스크나 메모리의 버퍼에 로드
      - 프린터는 버퍼에서 인쇄할 데이터를 가져옴
      - 프린터가 인쇄하는 동안 CPU는 다른 작업 수행 가능
  - 이를 통해 사용자는 여러 개의 인쇄 작업을 프린터에 순차적으로 요청
    - 이전 작업의 종료를 기다리지 않고, 버퍼에 인쇄 작업을 로드하여 자신의 인쇄 작업을 요청
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/69ef5731-b621-4a9c-bf6f-8e119add5ffd)
### 초기 Multiprogramming
  - 2개 이상의 작업을 동시에 실행
    - 운영체제는 여러 개의 작업을 메모리에 동시에 유지
    - 현재 실행중인 작업이 I/O를 하면, 다음 작업을 순차적으로 실행
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/e371d9fa-fc25-49c5-be7f-bfe82f090dcd)
  - CPU 활용도 증가, Idle Time 감소
  - 단점
    - First Come First Served
    - 사용자는 여전히 실행중인 작업에 대해서는 관여할 수 없음
      - 사용자는 작업을 제출하고 결과를 기다려야함
    - 다른 Job이 수행되기 위해선 현재 수행되는 Job이 I/O를 해야함
      - 공평성을 유지할 필요 발생
      - High Priority로 수행할 필요 생김
    - Job Scheduling으로는 해결 불가
### TimeSharing
  - CPU 실행 시간을 타임 슬라이스로 나눠 실행
    - 타임 슬라이스 동안 CPU를 점유하고, 시간이 끝나면 CPU를 양보
    - 여러 개의 작업들이 CPU 스위칭을 통해 실행
    - CPU 스위칭이 빈번하게 일어남
      - 사용자는 실행중인 프로그램에 관여가 가능
        - 텍스트를 편집하거나 명령어를 입력하는 등, 상호작용이 가능함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/116a3bec-ff87-4b70-a49c-791b1e8b3a76)
### Multitasking
  - 여러 개의 태스크들이 CPU와 같은 자원을 공유하도록 하는 방법
    - 하나의 작업은 동시에 실행될 수 있는 테스크로 나눠질 수 있음
      - ex) fork()
    - 멀티태스킹은 사용자가 동시에 여러 개의 프로그램을 실행할 수 있도록 함
    - CPU가 Idle 상태일 때는 Background 작업을 실행 가능하도록 함
    - TimeSharing이 MultiTasking임
  - Issue
    - 복잡한 메모리 관리 시스템
      - 동시에 여러 프로그램이 메모리에 상주
    - Concurrent Execution 제공
      - CPU 스케줄링 필요
    - 동기화 필요

## Operating System Structure
### System Structure
  - 운영체제 설계 시 소프트웨어의 구조를 신중히 고려
    - 개발, 수정, 디버깅, 유지 보수, 확장 용이
  - 좋은 디자인 목표?
    - 설계하고자 하는 시스템의 목적과 부합해야함
- OS Design Principle
  - Policy
    - **무엇**이 되게 할 것인가?
  - Mechanism
    - 무엇을 **어떻게** 할 것인가?
  - 즉, Policy를 Mechanism을 통해 해석
  - Mechanism과 Policy의 분리를 통해 운영체제 설계를 *Module*화 할 수 있음
### Layering
  - OS의 복잡도를 낮추기 위한 방안
  - Layer는 정의가 명확한 함수들로 이뤄짐
    - 하나의 Layer는 인접한 Layer와만 통신
    - 단, 레이어 수가 많으면 시스템 오버헤드가 증가함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/945eb10f-1e55-4581-b38a-7c1de83bd715)
    - 장점
      - 수정이 다른 Layer와 독립적
  - OS는 보통 User, Kernel Mode처럼 Layering도 지원하지만, 보통 기능 단위로 구분하여 모듈화를 더 많이 함
### User Mode와 Kernel Mode
  - Layering의 예시
  - System Protection을 위해 필요
    - 실행 Mode의 권한에 따라 접근할 수 있는 메모리, 실행 가능한 명령어가 제한됨
  - 각각의 모드별로 권한 설정
  - Kernel Mode
    - 모든 권한을 가진 실행 Mode
    - 운영체제가 실행되는 Mode
    - I/O 장치 제어 명령어 및 레지스터 접근 가능
  - User Mode
    - Kernel Mode에 비해 낮은 권한
    - 어플리케이션이 실행되는 Mode
    - Privilege 명령어 실행 불가
  - 실행 Mode 전환(Mode Switch)
    - 시스템 콜
      - User Mode에서 Kernel Mode로 진입하기 위한 통로
      - ex) Open, Write, Shm
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/4eab6db5-5c43-4b17-bc68-39394cfe43d6)
- Kernel
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/59e7db6b-0229-4454-931b-93ca6d7a2d13)
    - layering 되어 있음
    - Kernel 내부는 Module화
### Monolithic Kernel 
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d3832e33-3296-4fbd-b3be-11c772f05c52)
  - Kernel의 모든 Service가 같은 주소 공간에 위치
  - 어플리케이션은 자신의 주소 공간에 커널 코드 영역을 매핑하여 커널 서비스 이용
  - H/W(하드웨어) 계층에 관한 **단일한** Abstraction을 정의
    - 이를 위해 라이브러리나 어플리케이션에 **단일한** 인터페이스 제공
  - 장점
    - 어플리케이션과 Kernel 서비스가 같은 주소공간에 위치, 시스템 콜 및 Kernel 서비스 간 데이터 전달 시 *오버헤드가 적음*
  - 단점
    - 모든 서비스 모듈이 하나의 바이너리로 이루어짐, 일부분의 수정이 전체에 영향을 미침
    - 각 모듈이 유기적으로 연결, Kernel의 크기가 커질수록 유지보수가 어려움
    - 한 모듈의 버그가 전체에 영향을 끼침
### Micro Kernel
  - Kernel Service를 기능에 따라 모듈화, 각각 독립된 주소 공간에서 실행
  - 이러한 모듈을 서버라 지칭, 서버는 독립된 프로세스로 구현됨
  - 마이크로 커널은 IPC, 어플리케이션의 서비스 콜 전달과 같은 단순한 기능만 제공
  - 장점
    - 다 따로 구현되어 있기 때문에 의존성이 낮음
      - Monolithic보다 독립적인 개발 가능
      - Kernel의 개발 및 유지보수 용이
    - Kernel 서비스 서버의 간단한 시작 및 종료 가능
      - 불필요한 서비스의 서버는 종료, 많은 메모리 및 CPU resource 확보 가능
    - 이론적으로 Monolithic보다 안정적
      - 문제 있는 서비스는 서버를 재시작하여 해결
    - 서버 코드가 Protected Memory에서 실행, 검증된 S/W 분야에 적합
  - 단점
    - 독립된 서버들 간의 통신 및 Context Switching으로, 낮은 성능을 보임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/cdfbf6c8-a3b8-4150-a562-1778169d815c)
### Hypervisor
  - 하나의 물리적 시스템에서 여러 다른 운영 체제를 동시에 실행할 수 있도록 함
  - 가상화된 컴퓨터 하드웨어 자원을 제공하기 위한 관리 계층
    - 게스트 OS와 하드웨어 사이에 위치
    - 게스트 OS는 Hypervisor가 제공하는 가상화된 하드웨어 자원을 이용하는 운영체제
  - 각 게스트 OS들은 서로 다른 가상머신에서 수행, 서로의 존재를 알지 못함
  - 각 게스트 OS간의 CPU, 메모리등 시스템 자원을 분배하는 최소한의 역할 수행
  - 장점
    - 하나의 물리 시스템에서 여러 종류의 게스트 OS 운용 가능
      - 한 서버에서 다양한 서비스 동시 제공
    - 실제 컴퓨터가 제공하는 것과 다른 형태의 ISA 제공
      - 다른 하드웨어 환경으로 컴파일된 게스트 OS및 응용프로그램 실행 가능
  - 단점
    - 하드웨어를 직접적으로 사용하는 다른 운영체제에 비해 성능이 떨어짐
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c8ef3a0c-bb27-492e-ab36-a8547ff9237d)

## Process
- ![image](https://github.com/googoo9918/TIL/assets/102513932/f8898412-4d48-4d6e-b224-26f8466f476a)
### 컴파일러
  - Source Code를 CPU가 이해할 수 있는 기계어로 표현된 *Object* 파일로 변환
  - Object(.o file)
    - 기계어로 구성
    - 자체로는 수행 불가
    - 프로세스로 변환되기 위한 정보 삽입 
    - Relocatable Addresses
      - 심볼들의 주소가 상대적인 값으로 표현
### 링커
  - 여러 Object 파일과 라이브러리를 연결 -> 메모리로 로드될 수 있는 하나의 Executable로 변환
  - Executable
    - 특정한 환경에서 수행될 수 있는 파일
      - Header, Text, Data 포함
      - Absolute Addresses로 표현
        - 주소가 절대값으로 표현
  - 컴파일러와 링커는 결과물이 수행될 OS와 CPU에 따라 다른 형태의 파일을 만듬
### 로더
  - Executable을 실제 메모리에 올려주는 역할
  - 동작 과정
    - Executable의 Header를 읽고, Text와 Data의 크기를 결정
    - 프로그램을 위한 Address Space 생성
    - 실행 명령어와 Data를 Address Space로 복사
    - 프로그램의 Argument를 Stack으로 복사
    - CPU내 Register 초기화 후, Start-up Routine으로 점프
### Process Concept
  - Abstraction
    - Execution Unit: 스케줄링의 단위
    - Protection Domain: 서로 침범 불가
  - Implemented with
    - pc, stack, Data section
  - 프로세스는 디스크에 저장된 프로그램으로부터 변환되어 메모리로 로딩됨
### Process State
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2fdd345a-49a7-41bc-9b4f-ad9a7cd91be3)
  - New
    - the process is being created
  - Running
    - Instructions are being executed
  - Waiting
    - The process is waiting for some event to occur
    - I/O event와 같은 데이터 송수신 대기
  - Ready
    - The process is wating to be assigned to a processor
    - 이 상태부터 CPU 스케줄링 다시 가능
  - Terminated 
    - The process has finished execution
### PCB(Process Control Block)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/808fee33-af1f-4c05-8786-8a6e4adf5cbe)
    - 각 프로세스는 OS에서 PCB로 표현
### Context Switch
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c63d56a1-a0dc-417d-9971-3301f05bbd45)
    - save하고 reload하는 동안 오버헤드
    - CPU가 새로운 프로세스로 switch
    - kernel은 old process의 state를 save하고 new process를 load 해야함
    - 오버헤드를 가짐
    - switching time은 hardware support 및 OS 스케쥴링 설계에 의존
### 프로세서(CPU) 구조에 따른 Context Swtich의 차이
  - CISC(Compelx Instruction Set Computing)
    - 복잡한 명령어 셋 구성 -> 효율 높임, 클럭 속도 저하
      - 복잡한 명령어니까 CPU가 많은 사이클(클럭)을 수행하지 못함
    - 복잡한 회로 -> 물리 공간 차지 -> 레지스터 용량 저하(더 적은 레지스터 용량)
  - RISC(Reduced Instruction Set Computing)
    - 간단한 명령어 셋 구성 -> 클럭 속도 높임 -> 빠른 수행 속도
    - 절약된 물리적 공간에 보다 많은 레지스터 장착
      - Context swtich시 레지스터 내용 변경에 보다 큰 오버헤드 발생
      - 그냥 갯수가 더 많아서.. 라고 생각하자 정확한 내용은 아닌듯
### Process Creation
  - 프로세스는 시스템에서 Concurrently하게 실행될 수 있음
  - dynamically하게 실행 및 종료될 수 있음
  - OS는 process creation과 termination mechanism 제공
  - Process Creation
    - fork()
    - Resource Sharing
      - Parent and child share all resource
      - child share subset of parent's resource
      - parent and child share no resource
      - 위 3 中 선택 가능
    - Execution
      - Parent and child execut concurrently
      - Parent waits until children teminate
- Process Creation in Memory View
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0014b7d2-35ab-4af0-8067-6dbeff7e2133)
    - Child Process is a duplicate of the parent process
    - Child process에 새 프로그램이 load될 수 있음
### Process Termination
  - 프로세스는 마지막 명령어를 실행 완료하면 종료됨
  - 종료 요청은 일반적으로 exit system call을 통해 OS에 알려짐
    - 이를 통해 실행 완료를 알리고, 자원 회수 및 프로세스 제거를 요청함
  - 비정상적인 프로세스 종료
    - abort 함수는 프로세스를 비정상적으로 종료시키는데 사용됨
      - 프로세스는 즉시 종료되고, 어떠한 정리 작업도 수행되지 않음
      - *SIGABRT* 시그널이 해당 프로세스에 전달
        - 프로세스에 오류가 발생했음을 알리고, 프로세스가 즉시 종료되도록 함
    - 코어 덤프 생성
      - 비정상적 종료 발생 시, 시스템은 코어 덤프를 생성
      - 프로세스 종료 당시의 스냅샷 저장(디버깅 및 원인 분석에서 사용)
- Cooperation Processes
  - 프로세스간 영향을 끼칠 수 없음
  - IPC 등으로 Process끼리의 협력 시
    - 정보 공유, 모듈화, 편의, 유지보수 등 이점 존재
    - 프로세스가 너무 많아지면 contextswitch나 IPC에서의 오버헤드가 많이 생기니 주의할 것

## Fork-Exec
### Fork
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0ad6bca3-ea5a-4243-ace2-4a47e2b9c9cb)
    - 부모에게 생성한 process pid를, 자식에게 0을 리턴
    - 자식 프로세스는 부모 프로세스의 복제본임
    - 실패 시 -1 return
  - limitation?
    - 모듈화를 하는게 아닌, 하나의 코드내에서 두 프로세스가 동작하고 있는 것이 제한적
    - 해결
      - 주로 child에 exec를 하여 해결
### Exec
  - code(text)와 data가 새 프로그램으로 대체됨
  - 모든 데이터는 교체되지만, 명시적으로 close-on-exec을 설정하지 않는 한 모든 열린 파일디스크립터는 열려있음
  - exec는 모든 스레드에 영향을 미침
- fork와 차이점
  - 부모 프로세스를 유지하는가?, 새로 만드는 프로세스가 유형?
### Zombie process
  - 자식 프로세스가 defunct로 표시되고, 상태코드 Z(Zombie)로 표시되는 경우
  - 자식 프로세스가 종료되었는데 부모 프로세스가 wait()을 통해 정보를 회수하지 않은 경우
  - 실제로 좀비 프로세스는 실행이 다 끝난 상황이므로 메모리나 CPU를 점유하고 있진 않음
    - 다만, Wait이 없거나 늦게 작용했다는 등의 이유로 PID가 반환되지 않는 상황임
    - 즉, 해당 프로세스가 이미 '사망'했지만, '정보'는 살아있는 상태

## Computer Architecture
### 단일 Bus 구조
- ![image](https://github.com/googoo9918/TIL/assets/102513932/4b4d6a2a-cb42-4a16-87c1-650b64363ea4)
  - Bus
    - CPU, RAM, I/O 장치 간 데이터 전송 통로
  - 단일 Bus
    - 하나의 시스템 버스에 여러가지 모듈 연결
    - CPU, Memory, I/O의 속도가 비슷했던 초창기 모델
    - CPU의 속도를 따라가지 못해 병목 현상 발생
### 병목현상(Bottleneck)
- 같은 버스에 연결된 디바이스들 사이의 속도 차이로 발생
- CPU > Memory >> I/O 속도로 격차 커짐
  - 외부로 엮여있는 네트워크는 보통 I/O보다 느리다고 봄 
  - 빠른 디바이스 처리량을 느린 디바이스가 처리하지 못함
- 전체 시스템 속도는 느린 디바이스의 속도로 제한

### 계층적 버스 구조
- ![image](https://github.com/googoo9918/TIL/assets/102513932/c1770223-13d4-4b67-a9bc-3323352addc0)
  - 이중 버스
    - CPU와 I/O 속도 격차로 인한 병목 현상 해결
    - 빠른 CPU와 메모리는 시스템 버스
    - I/O 장치는 I/O 버스에 연결

### 이벤트 처리 기법: Interrupt
- 비동기적 이벤트를 처리하기 위한 기법
  - 비동기적이란, 현재 작업의 완료와 관계없이 새로운 작업을 시작할 수 있음을 뜻함
  - ex) 네트워크 패킷 도착 이벤트, I/O 요청
    - 주로 프로세서 외부의 이벤트에 의해 발생
- ![image](https://github.com/googoo9918/TIL/assets/102513932/1fe7269b-9456-4ec4-a73e-784c7d241fec)
- Interrupt 처리 순서
  - Interrupt Disable
    - Interrupt A가 도착했을 때, 다른 Interrupt가 들어오는 것을 막음
  - 현재 실행 상태를 저장
  - ISR(Interrupt Service Routine)로 점프
    - Context Switch
  - 저장한 실행 상태(State)를 복원
  - Interrupt로 중단된 지점부터 다시 시작
- Interrupt끼리도 우선 순위 존재, 하드웨어 장치마다 다르게 설정
- ISR은 짧아야함
  - 너무 길면 Interrupt가 아님, 그냥 새로운 프로세스를 실행시키는게 맞음
  - 너무 길면 Disable때문에 다른 Interrupt가 처리되지 못함
- Time Sharing은 Timer Interrupt로 가능

### 이벤트 처리 기법: Trap
- 동기적 이벤트를 처리하기 위한 기법
  - 동기적 이벤트는 한 작업이 완료되면 다음 작업 진행
  - Divide by Zero와 같은 프로그램 에러에 의해 발생
    - 주로 프로그램 내부의 예외 상황이나 명시적 시스템 호출에 의해 발생
- ![image](https://github.com/googoo9918/TIL/assets/102513932/5f51adb9-68a0-4e5e-9a33-3a2b32f275b7)
  - Trap Handler에 의해 처리
    - 이 자체가 프로그램 behavior임
  - Interrupt와 달리 실행 상태를 저장 및 복원하지 않음
    - Trap은 동기적인 이벤트이기 때문
      - 프로세서는 이미 해당 명령어나 호출이 발생한 부분을 알고, 파악할 수 있음
### I/O Device Basic Concepts
- Device Registers
  - 보통 하드웨어 장치는 4종류의 Register를 가짐
  - Control, Status, Input, Output Register
  - Register들은 메인 메모리의 일부 영역에 Mapping
    - CPU에서 바로 접근 가능
- I/O Controller
  - High-Level의 I/O 요청을 Low-Level Machine Specific Instruction으로 해석하는 회로
    - 상위 수준의 I/O 요청을 기계어로 변환함
    - 장치와 직접 상호작용
      - HDD, 키보드, 마우스등과 직접 통신
### I/O 처리 기법: Polling
- Loop 안에서 특정 이벤트의 도착 여부를 확인하며 기다리는 방법
- Interrupt Handler 등록과 반대되는 개념
- Controller나 장치의 처리 속도가 매우 빠른 경우, Polling은 Event 처리 기법으로 적당함
- 이벤트 도착 시간이 긴 경우, Polling은 CPU Time을 낭비
  - 프로세서 코어 하나는 Loop를 확인하는데 사용되기 때문

### I/O 처리 기법: DMA(Direct Memory Access)
- Pooling을 사용할 경우, 모든 I/O 연산은 CPU에 의해 진행됨
- 전송하는 데이터가 큰 경우, CPU를 Polling을 위해 I/O Device의 상태 확인 및 버스에 데이터를 쓰는 행위에 사용하는 것은 낭비임
- DMA Controller라는 프로세서 사용
  - CPU와 DMA Controller 간의 통신으로 I/O 수행
  - CPU가 DMA Controller에게 I/O 요청 시
    - DMA Controller는 CPU를 대신하여 I/O Device와 Main Memory 사이의 데이터 전송 수행
    - CPU는 이 시간동안 다른 일을 수행할 수 있음
  - 즉, I/O 전용 CPU임
- DMA Read
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f0fe651b-c759-4ac7-ba46-551ca62a5029)
- DMA Write
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c170c88f-2aa4-4f1e-a0fb-d666e8899a15)
- DMA VS Polling
  - DMA는 추가적인 HW 필요
  - DMA를 최대한 활용하기 위해서는 적당한 Parallelism(병렬 처리) 필요
    - 여러 DMA 전송을 통해 여러 장치가 동시에 메모리에 접근하고 데이터를 전송할 수 있음
  - 특수, 특정 목적에 대한 시스템에서는 DMA가 필요 없음
    - 설계 비용도 증가하고, OS 레벨의 오버헤드도 증가함

### I/O Device Access 기법: I/O Instruction
- Controller는 보통 1개 혹은 그 이상의 Register를 가짐
  - CPU는 컨트롤러의 레지스터와 상호 작용하여 I/O 작업을 수행함
    - Register의 Bit Pattern을 읽고 씀


### I/O Device Access 기법: Memory Mapped I/O
- Device Register들을 Memory 공간에 Mapping 하여 사용
  - Register들은 주소 공간의 일부로 여겨짐

## CPU Scheduling
### Scheduling이란 무엇인가
- CPU Scheduling
  - 어덯게 Process에게 CPU의 사용을 할당할 것인지
  - Multiprogramming에 기반함
    - Memory 내의 Ready State Process 중 하나에게 CPU 할당
  - 가능한 CPU가 쉬는 시간을 최소화하고, Throughtput을 극대화해야함
### Process 수행 사이클의 구성
- CPU-I/O Burst Cycle
  - CPU Burst: CPU로 연산을 *수행*하는 시간
  - I/O Burst: I/O 처리를 위해 *기다*리는 시간
  - 일반적인 프로세스는 두 Burst를 번갈아 가며 수행
- Process 분류에 따른 CPU Burst의 특징
  - CPU-Bound Process
    - 계산이 많은 작업에 초점
    - 짧은 주기의 긴 CPU Burst
      - CPU 연산이 많고(짧은 주기) 길이도 김
  - I/O-Bound Process
    - I/O 처리 작업에 초점
    - 긴 주기의 짧은 CPU Burst
      - CPU 연산이 적고(긴 주기) 길이가 짧음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d7a3895c-baa6-4df5-81e8-56e1a089244b)
  - 서로 다른 Process, System에도 대채적으로 위 그래프와 같은 경향
  - Frequency는 Number of process
  - 보통 CPU 3~5ms의 CPU Burst와 100ms의 I/O가 번갈아가며 실행됨

### Scheduling의 종류
- 비선점형 스케줄링(Non-preemptive Scheduling)
  - 프로세스가 자발적으로 실행을 중단하거나 종료될때까지 계속 실행됨
  - "Running" -> "Waiting"
    - I/O 접근 시
  - "Ready" -> "Running"
  - Multiprogramming의 기본 스케줄링, OS가 강제로 CPU 사용을 해제하지 못함
    - 들어온 순서대로 실행되어야함
- 선점형 스케줄링(Preemptive Scheduling)
  - 그 외 다른 Scheduling 기법
    - "Running" -> "Ready"
    - 수행종료
  - OS가 현재 CPU를 사용하는 Process의 수행을 정지할 수 있음

### Scheduling Criteria
- 스케쥴링의 기준
- CPU 사용률(CPU Utilization)
  - 전체 시스템 시간 중 CPU가 작업을 처리하는 시간 비율
- 처리량
  - CPU가 단위시간 당 처리하는 프로세스 개수
- 응답 시간
  - 요청 후 첫 응답이 올 때까지 시간
- 대기 시간
  - Process가 *Ready Queue* 내에서 대기하는 시간 총합
- Turnaround Time
  - Process가 시작해서 끝날 때까지 걸리는 시간
- 이상적인 스케줄러
  - 최대의 CPU 사용률, 처리량
  - 최소 응답시간, 대기시간
  - 모든 조건 만족은 불가능
    - 시스템 용도에 따라 요구사항 달라짐
### Scheduling Algorithms- FCFS
- 먼저 CPU 할당을 요청한 Process에 CPU 할당
  - FIFO Queue사용 구현
  - 비선점형
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b3a7e8b8-ea40-450b-8009-385c6882e2ea)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/74bf4ce7-2955-4663-8da6-e187f0fa3c1e)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/1068084c-88a4-435d-99c7-5ec6380f7496)
    - 작업의 수행 순서에 따라 대기 시간 변동 가능

### Shortest Job First Scheduling(SJF)
- 다음 CPU Burst Time이 가장 짧은 Process에 CPU 먼저 할당
  - 최소 평균 대기 시간 제공
  - 비선점형 방식
  - 선점형 방식
    - CPU Burst Time이 현재 프로세스보다 짧은 시간을 가진 프로세스가 생기면 CPU 양보
      - Shortest Remaining Time First Scheduling(SRTF)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/4766578a-156a-444d-8bac-2e4b71216ea7)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/d0d0b15b-28d6-444d-b477-101d470e99d8)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/94834b6d-7f5b-4068-8c0e-11f1045ad9b2)

### Priority Scheduling
- 미리 주어진 Priority에 따라 CPU 할당
  - 비선점형 방식
  - 선점형 방식
    - 더 높은 Priority가 있는 경우 CPU 양보
  - 문제점
    - 기아 상태
      - 낮은 Priority를 가진 Process는 전혀 수행되지 않을 수 있음
    - 해결
      - Aging 기법
      - 할당을 기다리는 시간에 따라 Priority 증가

### Round Robin Scheduling
- CPU를 시간 단위(Time Quantum)로 할당
  - 선점형
  - Time Quantum만큼 수행한 process는 Ready Queue의 끝으로 들어가 다시 할당을 기다림
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f7ec1752-c074-4da2-8313-797e8d59edfd)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c6025766-f73f-4573-9c61-ab871ddb531d)
  - Time Quantum
    - 큰 경우, FCFS와 다를게 없음
    - 작은 경우, Context Swtich 오버헤드가 증대할 수 있음
    - 레지스터가 너무 많은 경우, Context Switch가 많아질테니 조금 클 필요가 있음
### Multilevel Queue Scheduling
- Ready Queue를 여러 개의 Queue로 분리, 각기 다른 Scheduling Algorithm 사용
  - 큐 사이에도 스케줄링 필요
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d398c5f7-d435-4f9e-b15c-79c7f8053303)
- Foreground Queue
  - Interactive한 동작이 필요한 Process를 위한 큐
  - 주로 RR
- Background Queue
  - CPU 연산 작업을 주로 수행하는 Process를 위한 큐
  - 주로 FCFS
- 문제점
  - 높은 우선순위 큐가 계속 CPU를 점유하는 경우, 기아 상태 발생 가능

### Multilevel Feedback Queue Scheduling
- Multilevel Queue에서 Process들이 서로 다른 Queue로 이동할 수 있도록 한 Scheduling 기법
  - 큐 사이에도 스케줄링 필요
    - 큐 개수, 각 큐마다 스케줄링 기법, Queue 이동의 기준, 특정 서비스에서 Queue 이동 등
  - Aging의 한 방법으로 사용
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f98f9db7-ea80-448c-8deb-46d894c8f23c)
    - Q0에서 8 ms동안 수행
    - 1에서 미종료시, Q1으로 이동, 16ms 동안 수행
    - 2에서도 종료되지 않으면 Q2로 이동, FCFS로 수행

### Multiple Processor Scheduling
- 여러 개의 CPU를 사용하는 System의 경우, CPU Schdeuling이 더욱 복잡해짐
- 비대칭 멀티프로세싱(Asymmetric Multiprocessing)
  - 하나의 CPU만이 시스템 자료 구조 관리
  - 모든 CPU가 접근할 경우보다 데이터 공유가 간단히 이뤄짐
  - Master, Slave
- 대칭 멀티프로세싱(Symmetric Multiprocessing)
  - 모든 프로세서가 동등한 상태
  - 각 프로세서는 같은 메모리를 공유
- Load Balancing
  - CPU마다 각각의 Ready Queue
    - 일부 CPU에 Process 집중 가능
  - CPU 모두에 하나의 Ready Queue
    - 사용 가능한 CPU에 차례대로 Process 배정

## IPC(Inter Process Communication)
- IPC
  - process간 데이터 및 정보를 주고받기 위한 Mechanism
  - Kernel에서 IPC를 위한 도구 제공
    - System call 형태로 process에게 제공
  - 필요성
    - Process 협력 모델 구현을 위함
### Shared memory
- ![image](https://github.com/googoo9918/TIL/assets/102513932/283f83a6-f3f3-4fe1-a937-06c371f37b1c)
  - Process의 특정 Memory 영역 공유
  - 공유 Memory 영역을 통해 통신 수행
  - 응용 프로그램 레벨에서 통신 기능 제공
    - 공유 메모리 설정 이후 통신은 Kernel 관여 없이 진행 가능
  - Kernel 관여가 없기 때문에 빠르지만, 동기화 문제 발생 가능
    - Locking이나 Semaphore을 통해 해결
    - Lock을 통해 다른 프로세스가 데이터에 접근할 수 없도록 함
    - 사실 동기화 해결은 Kernel을 통한 방식이긴 함

### Message Passing
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3c23b27f-1385-454f-8e43-9a4f666b9cdc)
  - Process간 Memory 공유 없이 동작 가능
  - 고정길이, 가변길이 메시지 송수신
  - *Kernel을 통한* 메시지 통신 기능 제공
    - Kernel에서 데이터 버퍼링, Kernel이 동기화 제공
  - Pipe, Message Queue, Socket 등
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e27e4b24-c083-4493-9660-e081f38a4bc9)
  - Kernel 없이 Read/Write, 그러나 동기화 문제 남아있음
  - Kernel 경유 Send/Recive
    - Process B가 받기 전에 Context Switch를 통해 실행되지 않았으면, 그건 아직 받지 못한것임
      - Shared Memory는 써놓는 것이니, Cotnext Switch와는 무관

### Pipe
- ![image](https://github.com/googoo9918/TIL/assets/102513932/295c427c-e756-4352-a0ed-e85e51f4fa1b)
  - 하나의 Process가 다른 Process로 데이터를 직접 전달
    - 데이터는 한 쪽 방향으로만 이동 가능
    - 양방향 통신을 위해서는 두 개의 Pipe 필요
    - 1대1 의사소통만이 가능
    - 보낸 순서대로만 수신 가능
    - 용량 제한 존재
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d9949426-e27f-4987-9a5e-570729127ebe)
```c
int main(void)
{
    int n, fd[2];
    pid_t pid;
    char line[MAXLINE];

    if (pipe(fd) < 0) {
        perror("pipe error");
        exit(-1);
    }
    // 실패 시
    if ( (pid = fork()) < 0) {
        perror("fork error");
        exit(-1);
    // 부모 프로세스
    } else if (pid > 0) {
        /* parent */
        close(fd[0]);
        //Read Pipe 닫음
        write(fd[1], "hello, world.\n", 14);
        waitpid(pid, NULL, 0);
    } else {

    /* child */
        close(fd[1]);
        n = read(fd[0], line, MAXLINE);
        write(STDOUT_FILENO, line, n);
    }
    
    fflush(stdout);

    exit(0);
}
```

### Signal
- 특정 Process에게 Kernel을 통해 Event를 전달 
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e07a64e7-70a5-4636-99b3-a7fb0cc76fce)
  - 송신 Process
    - 여러 신호 중 하나를 특정 Process에 보냄
      - 이 동작은 수신 Process의 상태에 무관하게 수행
  - 수신 Process
    - 신호 종류에 따라 신호 처리 방법 지정 가능
      - 무시
      - 단순히 신호를 붙잡아 둠
      - 특정 처리 루틴(Signal handler)를 두어 처리
  - 비동기적인 동작
    - Process A가 Signal을 Process B에게 보내더라도, Signal 처리는 Process B가 Scheduling 되어야 가능함
    - 즉, 서로 다른 프로세스가 서로의 상태나 실행 타이밍에 직접적으로 의존하지 않고 실행 가능함
```c
void SignalHandlerChild(int signo)
{
    printf("signal handler\n");
    fflush(stdout);
}

int main(void)
{
    pid_t pid;
    struct sigaction act_child;

    act_child.sa_handler = SignalHandlerChild;
    act_child.sa_flags = 0;
    sigemptyset( &act_child.sa_mask );
    //특정 signal의 처리 방법을 지정함
    // SIGUSR1에 핸들러 함수로 SignalHandlerChild를 지정
    sigaction( SIGUSR1, &act_child, 0 );


    switch ( pid = fork() )
    {
    case -1:
        perror("fork failed ");
        exit(-1);

    case 0:
        sigpause(SIGUSR1); //Sigpause는 단순히 신호를 붙잡아 두는 것임
        return 0;

    default:
        sleep(3);
        kill(pid, SIGUSR1); //child에게 SIGUSR1을 보냄(특정 프로세스에 신호를 보냄)
        waitpid(pid, 0, 0);
    }
    return 0;
}
```

### Shared Memory
- ![image](https://github.com/googoo9918/TIL/assets/102513932/bdb1a9b5-4fa7-4393-ba85-092c4ae400c6)
  - 두 개 이상의 Proceess 들이 하나의 Memory 영역을 공유하여 통신
    - Memory 할당 시에만 Kernel의 개입 존재
  - 동시 변경 방지를 위해 Process 간 동기화 요망
```c
#include <sysshm.h> //Shared Memory 사용 가능 헤더 파일
#define SHM_SIZE        1024

void ChildRun(int shmid);

int main(int argc, char **argv)
{
    int         shmId, pid;
    char        *ptrShm;

    //shmget을 통해 shared memory를 만듬
    if ( (shmId = shmget(IPC_PRIVATE, SHM_SIZE, SHM_R | 
                          SHM_W)) < 0 ) {
        perror("shmget error");
        exit(-1);
    }

    //shmat을 통해 위에서 만든 sm을 process에 할당함
    if ( (ptrShm = shmat(shmId, 0, 0)) == (void *)-1 ) {
        perror("shmat error");
        exit(-1);
    }

    ptrShm[0] = 11;
    ptrShm[1] = 22;

    printf("Parent : %d, %d\n", ptrShm[0], ptrShm[1]);

    switch ( pid = fork() )
    {
    case 0:
        ChildRun(shmId);
        return 0;

    case -1:
        perror("fork error");
        exit(-1);

    default:
        break;
    }

    waitpid(pid, NULL, 0);

    printf("Parent : %d, %d\n", ptrShm[0], ptrShm[1]);

    if ( shmdt(ptrShm) < 0 ) {
        perror("shmctl error");
        exit(-1);
 
}

    if ( shmctl(shmId, IPC_RMID, 0) < 0 ) {
        perror("shmctl error");
        exit(-1);
    }

    return 0;
}

//현재 sm은 parent에만 연결되어 있고, child에는 연결되어있지 않음
void ChildRun(int shmid)
{
    int         shmId;
    char        *ptrShm;

    shmId = shmid;
    // 마찬가지로 자식 프로세스에도 sm을 연결해줌
    if ( (ptrShm = shmat(shmId, 0, 0)) == (void *)-1 ) {
        perror("shmat error");
        exit(-1);
    }

    printf("Child  : %d, %d\n", ptrShm[0], ptrShm[1]);
    printf("Child  : Modify value.\n");
    ptrShm[0] = 33;
    ptrShm[1] = 44;

    //shmdt를 통해 sm 해제
    if ( shmdt(ptrShm) < 0 ) {
        perror("shmctl error");
        exit(-1);
    }
}

```

### Message Queue
- ![image](https://github.com/googoo9918/TIL/assets/102513932/943f4e99-4998-4f7b-843d-da2bb60d8ed6)
  - *고정된 크기*를 갖는 Message의 연결 리스트를 이용하여 통신
  - Message 단위의 통신
    - Message의 형태는 통신하고자 하는 Process간 약속 필요
  - 여러 Process 동시 접근 가능
    - 동기화 필요
```c
//Mesage에 대한 정의
typedef struct _MSG {
    long type;
    char message[256];
} MSG, *PMSG, **PPMSG;
#define MSG_ sizeof(MSG)

int main(void)
{
    pid_t pid;
    key_t msg_id;
    MSG msg;
    
    //msgget을 통해 msg_id 설정(메시지 큐를 만드는 function)
    if ( -1 == (msg_id = msgget(IPC_PRIVATE, 0660 | IPC_CREAT)) ) {
        perror( "msgget failed" );
        exit(1);
    }

    switch ( pid = fork() )
    {
    case -1:
        perror( "fork failed " );
        exit(-1);

    case 0:
        msg.type = 1;
        strcpy( msg.message, "Hello, world.");
        //msgsnd를 통해 메시지를 send
        msgsnd( msg_id, &msg, MSG_-sizeof(long), 0 );
        return 0;

    default:
        waitpid(pid, 0, 0);
        //메시지를 받을 공간 할당
        memset( &msg, 0, MSG_ );
        //메시지 큐에 있는 메시지 수신
        msgrcv( msg_id, &msg, MSG_-sizeof(long), 1, 0 );

        printf("PARENT - message from child : %s\n", msg.message);
        fflush(stdout);
    }

    if ( -1 == msgctl(msg_id, IPC_RMID, 0) ) {
        perror( "msgctl failed" );
        exit(-1);
    }

    return 0;
}
```

### Socket
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d5ebf5c9-149f-43d6-8c00-14ca792e1fc4)
  - Port를 사용하기 때문에 Machine Boundary와 관계 없음
    - 물리적 위치나 특정 HW 환경에 구애받지 않음
  - Remote Machine은 Local Machine의 Port만 보임
    - 원격 시스템에서는 포트만 보이고, 내부 프로세스 구조나 소켓 정보는 안보임


## Thread
- 정의
  - Execution Unit
  - Process 내의 실행 흐름
  - Process 보다 작은 단위
  - Protection Domain은 존재하지 않음
- Process와 차이점
  - Process 간 Memory는 독립적
    - Thread는 Code, Data, File 영역 공유
  - Process는 IPC 필요(고비용)
    - Thread는 공유되는 메모리를 통해 통신
  - Process Context Switch(고비용) VS Thread Context Switch(저비용)
    - 같은 Memory 영역을 사용하므로 Switch 비용이 적음
    - Process보다 Thread의 고유 정보 수가 적기 때문에 비용이 적음
### Thread & CPU Utilization
- Thread 수와 Throughput
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2c2f18d6-5add-461e-9346-629d186e84b1)
    - Thread의 수가 증가할 수록 대체적으로 Utilization, Throughput 증가
      - 임계값을 넘어가면 다시 감소
        -  Thread의 수가 많아질 수록 Context Switching이 빈번하게 일어나고, 이에 따른 오버헤드가 증가하기 때문
      - CPU의 수가 많은 System일 수록 Thread를 이용하는 것이 유리
        - 더 많은 Thread를 병렬적으로 실행할 수 있음

### Thread의 구성 요소
- Thread는 하나의 실행 흐름이므로 관련된 자료 구조가 필요
  - Thread ID
    - Thread 식별자
  - Program Counter
    - 현재 실행중인 Instruction 주소
  - Register Set
    - CPU Register 값
  - Stack
  - 동일 Process 내에 있는 다른 Thread와 공유하는 것
    - Code
      - Program의 Code Section
    - Data
      - Process의 Data Section
    - File
      - Porcess에서 Open한 File
- ![image](https://github.com/googoo9918/TIL/assets/102513932/22b64975-b30b-4d51-a716-6151b631c44c)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/18cc51c3-f8eb-4d7e-8da3-52cb7697d6c5)

### Multi-Threaded Program 장점
- Responsiveness(반응성)
  - Program의 특정 부분을 담당하는 Thread가 Block되거나 시간이 걸려도
  - 다른 Thread는 실행되고 있음, User 입장 Interactive
- Resource Sharing
  - Thread들 간에는 Process의 Memory와 다른 자원들 공유
    - 동기화 문제는 잊지 말 것
- Economy
  - Thread는 하나의 Process Memory에서 실행함
  - 새 Process 생성보다 새 Thread 생성이 비용이 적게 듬
- Scalability(확장성)
  - 여러 개의 Thread가 각각 다른 Processor에서 동시 실행 가능
- 주의점
  - Big에서 4개의 CPU(코어), 하나의 Shared Cache
  - LITTLE에서 2개의 CPU(코어), 하나의 Shared Cache
    - 각 클러스터에서 Multicore는 Cache를 공유함을 기억하라!
    - 따라서 Data, Code 등 Process의 자원을 공유하는 멀티쓰레드 프로그래밍에 효율적
  - T1부터 T4가 BIG, T5부터 T6이 LITTLE에 할당
  - T2와 T5 통신시
    - T2 -> Shared Cache -> DRAM -> Shared Cache -> T5로 통신해야함
    - 따라서 LITTLE 클러스터가 비어있다고 해서 무작정 쓰레드를 할당한다면, 오히려 쓰레드 간 통신에서 오버헤드가 더 크게 생길 수 있음
    - LITTLE에서 코어를 안쓰고 있어도, BIG에서 그냥 대기하는게 나을 수도 있음 

### User and Kernel Threads
- User Thread(User-level Threading)
  - 멀티 쓰레드를 User 레벨에서 관리하는 경우
  - 일반적으로 User Level의 Library를 통해 구현
  - Library에서 Thread를 생성, Scheduling과 관련된 관리를 함
  - 장점
    - 동일한 Memory 영역에서 Thread가 생성 및 관리 되므로 이에 대한 속도가 빠름
  - 단점
    - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/f2e34690-50e4-4256-a3df-7e1ee078b709)
    - 여러 개의 User Thread 중 하나의 Thread가 System Call 요청으로 Block되면, 나머지 모든 Thread 역시 Block 됨
    - 커널은 한 번에 하나의 Thread만 처리할 수 있음!
    - 커널은 여러 User Thread들을 하나의 Process로 간주함
- Kernel Thread(Kernel-level Threading)
  - 운영체제에서 Thread를 지원
  - Kernel 영역에서 Thread 생성, Scheduling 등을 관리
  - 장점
    - Thread가 System Call 호출하여 Block시, Kernel은 다른 Thread를 실행
    - 즉, 전체적인 Thread Blocking이 없음!
      - Thread Blocking이 아예 없는건 아님
    - Multiprocessor 환경에서 Kernel이 여러 개의 Thread를 다른 Processor에 할당할 수 있음
      - User-level Threads는 커널에 하나의 Process로 인식되기 때문에, 다른 프로세서에 스케줄링 하는 것을 고려하지 않음
  - 단점 
    - User Thread보다 생성 및 관리가 느림
      - 생성 및 관리를 위해 시스템 호출이 필요하기 때문
      - Kernel-level Thread 간 context switching의 비용이 더 많음

### Mapping of User & Kernel Thread: Many-to-One
- Many-to-One
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/da20a31c-05ca-4fb8-86f6-b9b629b3d8ec)
  - Thread 관리는 User Level에서 이뤄짐(User level Threading)
  - 여러 개의 User Level Thread들이 하나의 Kernel Thread로 Mapping
  - Kernel Thread를 지원하지 못하는 System에서 사용
  - 한계점
    - 한 번에 하나의 Thread만 Kernel에 접근 가능
      - 하나의 Thread가 System Call시, 나머지 Thread들은 대기
      - 진정한 Concurrency는 지원하지 못함
      - 동시에 여러 개의 Thread가 System Call 사용 불가
    - Kernel의 입장에서 여러 개의 Thread는 하나의 Process이므로, MultiProcessor더라도 여러 개의 Processor에서 동시 수행 불가능
      - 실제로는 Thread Library에서 여러 개의 Thread를 하나의 processor에 Scheduling 함
- One-to-One
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/34ca1d43-5d61-403d-9c48-8544ddb62470)
  - 각각의 User Thread를 Kernel Thread로 Mapping
  - User Thread가 생성되면, 그에 따른 Kernel Thread가 생성
    - System Call 호출 시 다른 Thread가 Block 되는 문제 해결
    - 해당 Thread는 block되어도 다른 Thread는 계속 실행
  - 여러 개의 Thread를 Multiprocessor에서 동시 수행 가능
  - 한계점
    - Kernel Thread도 한정된 자원, 무한정으로 생성할 수 없음
    - Thread를 생성 및 사용 시 개수에 대한 고려 필요 
- Many-to-Many
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/583979a6-f96b-48d0-8940-2fe51ebd9c29)
  - 여러 개의 User Thread를 여러 개의 Kernel Thread로 Mapping
  - Many-to-One과 One-to-One Modle의 문제점을 해결
  - Kernel Thread는 생성된 User Thread와 같거나, 적은 수 만큼 생성되어 적절히 Scheduling
    - One-to-One 처럼 사용할 Thread의 수에 대한 고민이 줄음
    - Many-to-one처럼 System Call 사용 시 다른 Thread들이 Block되는 현상에 대해 걱정할 필요 없음

### Thread Issues
- Thread로 인한 운영체제 변화
  - 기존에는 Stsyem Call Semantice는 Process를 기준으로 작성됐음
  - Thread의 개념에 대한 고려
    - fork(), exec() 
      - Thread 지원을 위한 변화 필요
    - Thread 종료 문제
      - 함께 일하는 Thread의 종료는 Process보다 복잡함
- Multi-threaded Programming에 대한 지원
  - Thread Scheduling
  - Thread 간 통신 방법
  - Thread가 사용할 Memory 공간 할당
- Creation
  - fork and exec System call
    - Multithreaded Program에서 fork와 exec의 의미가 달라져야 함
  - fork
    - 하나의 progrma 내에서 Thread가 fork 호출 시
      - 모든 Thread를 갖고 있는 Process를 만들 것인가?
      - fork를 요청한 thread만을 복사한 process를 만들 것인가?
    - 참고로, Linux에서는 2가지 버전의 fork를 만들어 각각의 경우를 처리함
  - exec
    - fork를 하여 모든 Thread를 복사한 경우, exec 수행 시 모든 Thread들은 새로운 Program으로 교체됨
      - 교체될 Thread들의 복사는 불필요한 작업임
      - 따라서 fork를 하고 exec를 수행할 경우, fork를 요청한 Thread만이 복사되는 것이 더 바람직함
      - 그러나 fork를 하고 exec를 수행하지 않는 경우, 모든 Thread의 복사가 필요하기도 함
- Cancellation
  - Thread Cancellation
    - Thread의 작업이 끝나기 전 외부에서 작업을 중지시키는 것
    - 하나의 Thread에서 중지 명령이 결국은 다른 Thread의 모든 작업을 중지시켜야 하는 경우도 존재
    - 자원이 Thread에 할당된 경우 문제
      - System의 자원을 사용하고 있는 Thread가 중지 될 경우, 할당된 자원을 함부로 해제할 수 없음
        - 다른 Thread가 그 자원을 사용하고 있을 수도 있기 때문
        - Malloc()은 free()없이는 해제되지 않음
      - 따라서 Thread 별로 주기적으로 cancelation point를 두고, 사용하지 않는 메모리를 확인하고 해제함으로써 메모리 누수를 피함
- Thread Pools
  - Thread가 자주 생성되고 제거되는 상황에서, 새로운 Thread를 만드는 시간이 실제 Thread가 동작하는 시간보다 긴 경우가 있음
  - 해결 방안
    - Thread Pool을 만들고, Process 실행 시 정해진 수 만큼의 Thread를 만들고 Pool에 할당
    - 새로운 Thread가 필요하면 Pool에서 가져오고, 작업이 끝나면 Thread를 제거하지 않고 다시 Pool에 넣어둠
      - 마치 버퍼처럼 동작
        - 응답시간 개선
      - 만들고 삭제하는 오버헤드가 저장하고 꺼내는 오버헤드로 줄어듬
        - 메모리 오버헤드와 context switching 비용이 줄어듬
  - Pool을 통해 제한된 수의 Thread를 관리
    - 너무 많은 Thread 생성에 따른 System 부하를 줄일 수 있음
    - 실행될 수 있는 스레드의 최대 수를 제한함으로써, 리소스 사용을 제어하고 시스템이 과부화 상태에 빠지는 것을 방지함
    - 이떄 Pool에서 관리할 Thread의 수는 유동적으로 결정됨
- Thread간 IPC
  - Thread들은 같은 Process의 Data 영역을 공유, 자연스러운 Shared Memory 가능
  - IPC 최소화
  - 물론, 다른 Process에 존재하는 다른 Thread와의 통신은 IPC임

## Sychronization(1)
### Sharing Resources
- Thread는 고유의 Stack을 같기 때문에, 지역 변수는 쓰레드끼리 공유되지 않음
- 다른 쓰레드의 stack에 지역 변수의 포인터를 보내거나, 공유하거나, 저장하는 것 자체가 문제가 될 수 있음
- 공유 가능한 변수
  - 전역 변수(Global variables)
    - static data segment에 저장됨
  - 동적 할당 객체(Dynamic objects)
    - heap에 저장됨

### Race Condition
- Concurrency는 non-deterministic(결정되지 않은) 결과를 초래할 수 있음
  - 같은 input data에서 결과가 다르게 나올 수 있음
- 공유 데이터에 대한 동시 접근은 데이터의 일관성(Consistency)를 해칠 수 있음
  - Race Condition
    - 공유 데이터에 대해 여러 Process가 동시에 접근, 변경을 시도하는 상황
- ex)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/36655106-160e-4faf-b46f-1202a29d3c32)

### Critical Section
- 여러 Process(Thread)들이 공유하는 데이터에 접근하는 Code 영역
- 한 번에 오직 하나의 Process(Thread)만이 Critical Section에 진입해야 함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2b4b3e74-f4ee-4ed9-8615-9d339a5c75e3)
- Critical Section 해결 조건
  - Mutual Exclusion(상호 배제)
    - 만약 Process A가 Critical Section에 진입해 있다면, 다른 모든 Process는 진입할 수 없어야 함
  - Progress(진행)
    - 만약 어떤 Process도 Critical Section에 있지 않고, Crtical Section에 진입하고자 하는 Process가 존재한다면
      - Remainder Section에서 실행중이 아닌 Process들(=진입하려는 Process들) 만이 누가 진입할지 결정할 수 있어야 함
        - 동기화 매커니즘에 따라 결정을 따르게 됨
      - 결정은 무한히 연기될 수 없음(Deadlock-free)
  - Bounded Waiting
    - Process가 Critical Section에 진입할 때까지 걸리는 시간에 Limit이 존재해야 함
      - starvation-free
  - deadlock-free는 시스템이 정지하지 않는 것을 보장하지만, 모든 프로세스가 공정하게 처리되는 것을 보장하진 않음
    - starvation-free는 모든 프로세스가 결국에 자원에 접근할 수 있는 기회를 갖는 것을 보장함
- 두 Process를 위한 알고리즘
  - Shared Variable: `int turn = 0;`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1f7b22d9-3620-4c6e-aa58-eba79d5c38e3)
    - 만족 조건: Mutual Exclusion
    - 불만족 조건: Progress, Bounded Waiting
      - P1의 우선순위가 P0보다 높은 비선점형 스케줄링인 경우
- 다른 Algorithm
  - `boolean flag[2];`, `flag[0] = flag[1] = false`로 초기화
  - `flag[0] = true`일 때, P0이 critical section에 진입 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/970af2da-b83c-485d-93e9-807afa87a125)
    - 만족 조건: Mutual Exclusion
    - 불만족 조건: Progress, Bounded Waiting
      - 두 process가 동시에 flag[0]과 flag[1]을 true로 변경한 경우
- Peterson Solution
  - Shared Variables
    - `int turn;`, `boolean flag[2]; flag[0] = flag[1] = false`
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/47f24a75-aa1a-4cf6-bfad-7dc898acc7e6)
      - 두 프로세스가 서로에 대해 우선순위를 줌
      - turn 변수는 양보를 나타내고, flag[] 배열은 진입 의사를 나타냄
        - turn = 1은 다음 차례는 P1의 것이라는 의미
      - flag 선언과 turn의 선언의 순서가 바뀌게 되면 Mutual Exclusion을 불만족 할 수 있음
  - Peterson Soluiton의 한계
    - 3개 이상의 Process에서 구현?
    - 확장된 Algorithm에서 증명은 NP 문제임
    - 하드웨어로 처리하면 알고리즘이 간단해짐
      -  ![image](https://github.com/googoo9918/TIL/assets/102513932/ff022e2a-caa3-4705-bbe5-43b320cc069c)
         -  Acquire Lock ~ Releas Lock을 한 단위로 봄
      - 혹은 Critical Section에서 Interrupt를 Disable 할 수 있음
        - 그러나 User Program이 Interrupt를 Control 하는 것은 바람직 하지 않음
        - Scalable 하지 않음
        - 따라서 동기화를 위한 Instruction이 도입됨

### Synchronization Instruction
- CPU에서 지원하여 원자적으로 수행되는 명령어 이용
  - 하드웨어의 지원을 받아 구현됨
  - 원자적 -> All or Nothing
- Test and Set 명령어
```c
boolean TestAndSet(boolean *target){
  boolean rv = *target;
  *target = true;
  return rv;
  // target이 true면 true를 리턴
  // target이 false면 false를 리턴하고 true로 설정
}
```
- Mutual Exclusion with Test-and-Set
  - Shared Variables: `boolean lock = false`
  - Process P1
    ```c
    do{
      while(TestAndSet(&lock));
        critical section
      lock = false;
        remainder section
    }
    ```
  - lock이 true인 경우(다른 스레드가 critical section 사용중인 경우)
  - while 루프에 머물면서 lock이 false로 설정될 때까지(Release lock 할 때까지) 계속 체크함
    - busy waiting
- Swap 명령어
    ```c
    void Swap(boolean *a, boolean *b){
      boolean temp = *a
      *a = *b;
      *b = temp;
    }
    ```
- Mutual Exclustion with Swap
  - Shared Variables: `boolean lock;`, `boolean waiting[n];`
  - process P1
    ```c
    do{
      waiting[i] = true;
      while (waiting[i] == true)
        swap(&lock, &waiting[i]);
          critical section
        lock = false;
          remainder section
    }
    ``` 
    - lock이 true이고 waiting도 true라면, 계속해서 swap을 진행하면서 while 루프를 돌게됨
    - lock이 false가 된다면, waiting은 false가 되고 lock은 true가 되면서 이 스레드는 critical section으로 돌입할 수 있게 됨
- 위와 같은 Instruction의 한계점
  - 동기화 Instruction을 통해 Mutual Exclustion은 해결되나, Bounded Waiting 등의 조건은 User Program에서 제공해야 함
  - 그러나 Bounded Waiting이 주어진 문제마다 차이가 있기 때문에, User Program에서 제대로 처리하는 것을 기대하기 어려움
    - 보다 포괄적인 동기화가 필요

### Semaphores(세마포어)
- 세마포어
  - 두 개의 원자적 연산을 갖는 정수 변수
    - 원자적 연산
      - Wait() 또는 P()
      - Signal() 또는 V()
    - 세마포어 변수는 2개의 원자적인 연산에 의해서만 접근됨
- P는 Critical Section 들어가기 전에, V는 나와서 수행
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2f166e31-1e20-40d2-a3f4-641ae7947403)
  - 서로 독립적, 원자적으로 수행
  - 하나의 프로세스가 P를 수행하는 동안, 다른 Process가 P나 V를 수행하지 못함
- Semaphores의 구현
  - Busy Waiting 이용
    ```c
    P(s){
      while(S<=0);
        s = s - 1;
    }

    V(s){
      s = s + 1;
    }
    ```
    - 단점
      - CPU Cycle 낭비 가능
      - 대기 중인 Process 중 누가 Critical Setction에 진입할지 결정하지 않음
      - Sychronization Instruction과 다를게 없음
  - Sleep Queue 이용
      - Busy Waiting 방식의 CPU Cylce 낭비 문제 해결
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/fae7a520-3348-479c-8838-928f5422e626)
        - P연산
          - 세마포어의 값 감소, 0이하라면 현재 프로세스를 Sleep Queue에 추가하고 sleep()
        - V연산
          - 세마포어 값 증가, 값이 0이하라면, Sleep Queue에서 프로세스를 제거하고 wakeup(P)
      - FIFO 방식 Queue 라면, Bounded Waiting 해결!
- Semaphores(세마포어)의 종류
  - Counting Semaphore
    - Semaphore 값은 범위가 정해져 있지 않음
    - 초기값은 가능한 자원의 수로 정해짐
  - Binary Semaphore
    - Semaphore value가 가질 수 있는 값은 0과1
    - Counting Semaphore보다 구현이 간단함
  - Binary Semaphore는 test-and-set과 같은 Hardware 도움 받는 Instruction을 통해 구현 가능
  - Binary Semaphore를 이용하여 Counting Semaphore 구현 가능
- Binary Semaphore의 구현
  - Test and Set을 통해 구현
  - Semaphore S: 현재 Critical Section에 진입한 Process가 있는지 나타냄(True or False)
  - P(s)
    - While(test_and_set(&s));
  - V(s)
    - s = false;
- Counting Semaphore의 구현
  - Binary Semaphore를 이용한 Counting Semaphore 구현
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/d6971ad9-6335-48bb-91f9-525c714481e4)
- Semaphores의 구현
  - Kernel이 Single Thread인 경우
    - P/V를 System Call로
    - Kernel 내에서 처리하여 Semaphore 동작 구현'
    - Kernel 내의 수행이 비선점형, kernel에 들어간 것이 Critical Section에 들어간 것임
  - Kernel이 MultiThread인 경우
    - P/V를 System Call로
    - Kernel 내에서 별도로 동기화를 해야함
- Semaphores의 단점
  - Deadlock 발생 가능
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/bbc47891-ecd4-41a9-82de-87e67cbbb448)
  - P와 V의 연산이 분리되어 있음, 잘못 사용할 경우 대책이 없음
    - P() -> Critical Section -> P()
      - Deadlock 발생
    - V() -> Critical Section -> P()
      - 여러 Process가 Critical Section에 진입 가능, Mutual Exclusion 보장 불가
### Monitor
- High-level 언어에서의 동기화 방법
- 한 순간에 하나의 Process만 Monitor에서 활동하도록 보장
  - 공유데이터와 공유데이터를 접근하는 코드를 하나의 모니터(객체)에 구성
- Application은 Semaphore와 같이 P와 V연산에 대한 고려 없이 Procedure를 호출하는 것 만으로 동기화 해결 가능
  - 프로그래머는 동기화 제약 조건(P/V)를 명시적으로 코드화 하지 않아도 됨
- ![image](https://github.com/googoo9918/TIL/assets/102513932/19c2d49b-a177-4b6e-9ea6-5d5e3e2329f0)
  - Entry Queue에서 대기하는 Process들은 Monitor를 사용하기 위해 기다림
    - 한 번에 하나의 스레드만이 모니터 함수 실행 가능
  - Shared Data를 사용하기 위해서는 Monitor에 진입하여 제공되는 Operation을 통해야 함
- ex)
  - 데이터베이스의 트랜잭션
    - Transaction 내에 묶인 하나의 작업 단위가 반드시 완전히 수행
    - 만약 어느 하나라도 실패 시, 전체 명령문 취소

## Synchronization(2)
### Bounded-Buffer Problem
- N개의 Item을 삽입할 수 있는 Buffer에 여러 생상자와 여러 소비자 접근 시
  - 생산자
    - 하나의 Item을 생산해 Buffer에 저장
      - Buffer에 대해 여러 Producer가 동시에 item 추가하려고 할 때 Race Condition 발생 가능
  - 소비자
    - Buffer에서 하나의 Item을 가져옴
      - 소비자가 동시에 Buffer에서 Item을 빼려고 할 때 Race Condition 발생 가능
- ![image](https://github.com/googoo9918/TIL/assets/102513932/0e56a3ad-7963-4dcb-9c73-d087a7a07089)
  - 한 Producer가 data를 넣고 있을 때
    - 다른 Producer나 Consumer는 접근하면 안됨
- Bounded-Buffer Problem 구현
  - Buffer 구현
    - `boolean buffer[n];`
    - `buffer[0....n-1] = empty;`
  - 생산자 Operation
    - Buffer 배열 중, empty인 index를 찾아 full로 바꿈
    - `buffer[m] = full;`
  - 소비자 Operation
    - Buffer 배열 중, full인 index를 찾아 empty로 바꿈
    - `buffer[m] = emty;`
- Bounded-Buffer Problem 세마포어
  - Empty: Buffer 내에 저장할 공간이 있음을 표시
    - 생산자의 진입 관리
    - `sem_t empty;`
  - Full: Buffer 내에 소비할 아이템이 있음을 표시
    - 소비자의 진입 관리
    - `sem_t full`
  - Mutex: Buffer에 대한 접근 관리
    - 생산자, 소비자가 empty, full 세마포어 진입 시 buffer의 상태 값 변경
    - `pthread_mutex_t mutex`
  - value 초기값
    - `Full = 0, Empty = n, Mutex =1`
    - Buffer에 Full인 entry가 0개, Empty인 entry가 n개
- 생산자 Process
  ```c
  do{
    produce an item in nextp

    P(empty) //버퍼에 적어도 하나의 공간이 생기기를 기다림
    //sem_wait(&empty)
    P(mutex) //critical section에 진입하기 위해 기다림
    //pthread_mutex_lock(&mutex)
    ...
    add nextp to buffer
    ...

    V(mutex) //critical section에서 빠져나왔음을 알림
    //pthread_mutex_unlock(&mutex)
    V(full); //버퍼에 아이템이 있음을 알림
    //sem_post(&full)
  }while(1);
  ```
- 소비자 Process
  ```c
  do{
    P(full) //버퍼에 적어도 하나의 아이템이 들어가기를 기다림
    //sem_wait(&full)
    P(mutex);
    remove an item from buffer to nextc

    V(mutex);
    V(empty); //버퍼에 하나의 빈 공간이 생겼음을 알림
    //sem_post(&empty)
    consume the item in nextc
  }while(1);
  ```

### Readers-Writers Problem
- 여러 Readers와 Writers가 하나의 공유 데이터를 읽거나 쓰기 위해 접근
- Readers: 공유 데이터를 읽음
  - 여러 Reader는 동시에 Data를 읽을 수 있음
- Writers: 공유 데이터에 씀
  - Writer가 데이터를 수정하기 위해서는, Reader 혹은 다른 Writer가 작업을 하고 있지 않아야 함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/a5c6acb7-d0c7-4862-b22b-dbc79d789133)
  - Read는 같이 할 수 있으나, Write는 독립적임
- Solution 1
  - `int readCount`
    - 버퍼에 현재 몇 개의 Reader가 접근 중인지 표시
  - `Semaphore wrt`
    - Writers 사이의 동기화 관리
    - `sem_t wrt`
  - `Semaphore mutex`
    - Readcount와 wrt에의 접근이 원자적으로 수행됨을 보장하기 위한 세마포어
    - `pthread_mutex_t mutex`
  - 초기값
    - `Mutex = 1, wrt = 1, readcount =0`
  - Writer
  ```c
  P(wrt) //entry section
  writing is performed
  V(wrt); //exit section
  ```

  - Reader
  ```c
  P(mutex); //readcount와 wrt 접근을 위함
    readcount++;
    if(readcount ==1) //처음 읽고 있는 상황이면
      P(wrt); //wrt에 대한 세마포어를 통해 어떤 wrtier도 수행되고 있지 않음을 판별
  V(mutex);
    reading is performed
  P(mutex);
    readcount --;
    if(readcount == 0)
      V(wrt);
  V(mutex);
  ```
  - 문제점
    - Writer의 Starvation
      - Readcount가 0일 대만 V(wrt)가 수행되어 p(wrt)로 대기하고 있던 Writer가 수행할 수 있음
      - 첫 번쨰 Reader(Readcount ==1)가 P(wrt)만 통과하면 다른 Reader들은 P(mutex)에 대한 P(mutex)만 기다리면 언제든지 수행할 수 있음
        - Writer와 상관없이 진입 가능
      - 따라서 여러 Reader들이 계속해서 진입할 경우, ReadCount는 0까지 떨어지지 않을 수 있음
  - 해결책
    - Write process에 우선순위를 부여함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a2f25056-d268-4ae5-a7f5-9e3f6f5dc9ba)
      - S1
        - readcount 변수에 대한 접근을 동기화하는데 사용하는 세마포어
      - S2
        - writeCount 변수에 대한 접근을 동기화하는데 사용되는 세마포어
      - rd, wrt
        - 실제 데이터 읽기와 쓰기 작업을 동기화하는 데 사용되는 세마포어
      - wrtpending
        - 쓰기를 대기 중인 작가가 있음을 나타내는 세마포어
      - wrtCount
        - 현재 데이터를 쓰려고 대기 중인 작가의 수 추적
      - readCount
        - 현재 데이터를 읽고 있는 독자의 수 추적
      - Writer
        - writeCount 접근을 위해 S2 세마포어를 얻음
        - writeCount 증가
        - 처음 쓰는 거라면, rd 세마포어를 얻음
          - 쓰기 작업을 시작하기 전에, 읽기 작업을 차단함
        - S2 사메포어 해제
        - wrt 세마포어 획득 ~ 해제
        - S2 획득
        - writeCount 감소
        - 더이상 쓸 사람이 없다면, rd 세마포어 해제
        - S2 해제
      - Reader
        - 쓰기를 대기하는 사람이 있는지 확인
          - 근데 이게 의미가 있나 싶음(Writer에서 wrtpending에 대한 wait을 하지 않음)
          - 만약 있다면, 쓰는 사람이 대기하는 중이라면 더 이상 readcount는 증가하지 않겠지
        - rd에 대한 세마포어를 얻음
          - 현재 쓰기 작업 진행중이라면, 세마포어를 얻지 못함
        - s1에 대한 세마포어를 얻음
        - readcount 증가, wrt 세마포어 획득
        - s1, rd, wrtpending에 대한 세마포어 해제
        - 읽기 진행
        - s1 획득
        - readcount 감소
        - wrt, s1 해제

### Dining-Philosophers Problem
- 5명의 철학자가 한 식탁에서 함께 식사
  - 젓가락이 5개 뿐
    - 자신의 바로 옆 젓가락만 집을 수 있음
    - 두 젓가락을 모두 집어야 식사를 할 수 있음
    - 식사를 하고 난 다음에 두 젓가락을 모두 내려놓을 수 있음
  - Deadlock과 Starvation이 발생하는 경우
    - 모두 자신의 오른쪽 젓가락을 집은 경우
- Solution 1
  - 모든 철학자가 자신의 왼쪽 또는 오른쪽 젓가락부터 집도록 함
  - `boolean waiting[n];`
  - 세마포어
    - `chopstick[5]`
      - 각각 젓가락에 대한 동기화 관리
  - 초기값은 모두 1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/cfd34c6a-24d7-43e0-b0c5-9edba05c0442)
    - while loop 내에서 think와 eat을 반복
    - P(chopstick[i])
      - 왼쪽 젓가락을 잡으려고 시도함
    - P(chopstick[(i+1) % 5])
      - 오른쪽 젓가락을 잡으려고 시도함
    - V(chopstick[i])
      - 왼쪽 젓가락을 내려놓음
    - V(chopstick[(i+1) % 5])
      - 오른쪽 젓가락을 내려 놓음
    - 문제점
      - 동시에 chopstick을 잡으면 deadlock 발생
      - 예를 들어, 모든 철학자가 왼쪽 젓가락을 집는 경우
- 개선안
  - Deadlock 해결 방안
    - 한 번에 최대 4명의 철학자만 식탁에 앉도록 함
    - 젓가락 상태 미리 검사, 양 쪽의 젓가락이 모두 사용 가능할 때만 젓가락을 집도록 함
    - 철학자에게 번호를 부여, 홀수인 철학자는 왼쪽 젓가락, 짝수인 철학자는 오른쪽 젓가락을 먼저 집도록 함
  - 위 해결방안은 Starvation까지 해결하진 못함
    - 각각 방안에 대해 Starvation에 대한 고려 추가 가능
      - 한 차례 굶은 철학자 우선권 부여 등
- Solution 2
  - 양 쪽의 젓가락을 한 번에 집는 방법
    - 젓가락 상태 미리 검사, 양 젓가락이 모두 사용할 때만 젓가락을 집도록 하는 방법
    - `state[5]`
      - 각 철학자의 상태를 기록(THINKING, HUNGRY, EATING)
    - 문제 해결을 위한 세마포어
      - mutex: 젓가락을 집거나 놓는 수행을 Critical Section으로 관리하기 위한 세마포어
        - 초기값: 1
      - Self[5]: 철학자 각각이 젓가락 두 개를 잡기를 기다리는 세마포어
        - 초기값: 모든 원소가 0
        - Self[i]
          - 철학자 i가 HUNGRY 상태이더라도, 다른 젓가락 하나를 사용할 수 없을 경우 Waiting하기 위한 세마포어
    - Process
      ```c
      do{
        ...
        think
        ...
        take_chopstics(i);
        ...
        eat
        ...
        put_chopsticks(i)
        ...
      }while(1);
      ```
    - take_chopsticks  
      ```c
      take_chopsticks(int i)
      {
        P(mutex);
        state[i] = HUNGRY;
        test(i);
        V(mutex);
        P(self[i]);
      }
      ```
      - P(self[i])는 Test의 V(self[i])에 의해 결정됨
        - 먹을 수 있는 상황이라면 V(self[i])를 통해 값을 올리고, P(self[i])가 동작하지 않도록 함
    - put_chopsticks
      ```c
      put_chopsticks(int i)
      {
        P(mutex);
        state[i] = THINKING;
        test(LEFT);
        test(RIGHT);
        V(mutex);
      }
      ``` 
      - test(LFFT, RIGHT)를 통해 양쪽의 철학자 상태를 검사, 먹을 차례를 기다리는 철학자에게 signal을 보내줌
    - test(int i)
      ```c
      test(int i)
      {
        if(state[i] == HUNGRY &&
           state[LEFT] != EATING &&
           state[RIGHT] != EATING){
              state[i] = EATING;
              V(self[i]);
           }
      }
      ``` 
      - test를 수행한 철학자 i가 HUNGRY 상태고, 양쪽의 철학자가 모두 젓가락을 집지 않은 상태(NOT EATING)이면 take_chopsticks()에서 wait했던 자신의 세마포어 self[i]에 signal을 보내어 EAT으로 진행
  - 해설
    - 이 전략은 철학자 좌우 젓가락이 사용 가능할 때만 Critical Section에 진입
      - i번째 식사를 하기 위해서는, i-1과 i+1의 철학자가 EATING 상태가 아니어야 함

## Memory Management(1)
### 주소 공간
- Process에서 참조할 수 있는 주소 범위
  - Process와 1:1 관계
- 주소 공간의 크기
  - CPU의 주소 버스(Address Bus) 크기에 의존
  - 주소 버스가 32Bit인 System에서 주소 공간의 크기
    - 2^32개의 서로 다른 주소에 대한 식별자를 만들 수 있음
    - 0 ~ 2^32-1 까지의 주소 범위를 Addressing 할 수 있음
  - 32 bit 주소 버스를 가진 System이 주소 1개당 1Byte의 Memory를 접근할 수 있다면, 이 System이 Address할 수 있는 주소 공간의 크기는 몇 Byte인가?
    - 2^10, KB / 2^20, MB / 2^30, GB --> 4GB
### 물리 주소와 가상 주소
- 물리 주소(Physical Address)
  - 컴퓨터의 메인 메모리를 접근할 때 사용되는 주소
  - 기억 장치의 주소 레지스터에 적재되는 주소
- 가상 주소(Logical Address or Virtual Address)
  - Process 관점에서 사용하는 주소
  - CPU 관점의 주소는 물리 주소, 가상 주소 모두 가능
  - Logical이기 대문에 주소 공간을 의미 있는 단위로 나누어 사용하지 않음
- 초창기 컴퓨터의 주소 관리
  - 물리 주소를 Compile Time에 생성
    - 컴파일러는 프로세스를 실제 물리 주소로 매핑함
    - 따라서 시작 주소의 위치가 바뀔 경우 다시 Compile 해야함
  - 다양한 Program이 실행됨에 따라 Compile Time에 물리 주소를 정하기 어려워짐
    - 1개의 Program이 실행될 경우 문제 없음
    - 멀티 프로그래밍의 경우, 여러개의 프로그램을 동시에 Memory에 Load 하기 어려움
      - 가상 주소를 생성하기 시작
- Compile Time
  - 컴파일러가 Symbol Table을 만들고, 주소는 Symbol Table Relative한 주소로 이루어짐
  - 컴파일된 .O 파일은 주소 0부터 시작(Relocatable)
- Link Time
  - .O 파일들과 System에서 제공하는 Library를 묶어서 Symbol Table에 의존적이지 않은 주소를 만들어 냄
  - 만들어진 Executable 파일은 하나의 주소 공간으로, 주소는 0부터 시작함
- Load Time
  - Program의 실행을 위해 Loader는 실행 파일을 Memory로 Load함
  - 주소 공간 전체가 Memory에 올라간다면, Load 시 물리 주소에 대한 Binding이 일어남
    - Base Register를 통해 물리 주소로 바꿔서 실행
    - 메모리에 로드된 후 위치를 변경할 수 없음
  - 만일 Program의 시작 주소를 바꾸려면, Load를 다시 해야함
- Execution Time
  - Process가 실행 되는동안 물리 주소가 결정되고 바뀜
    - 그때그때 필요에 따라 물리 주소로 변환됨
    - 주로 가상 메모리 시스템에서 흔히 사용
  - MMU와 같은 특별한 Hardware가 필요
- CPU에서 사용하는 주소에 따른 변환 방법
  - CPU에서 Physical Relative Address를 사용하는 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/915e6f5a-ec5b-45b4-8228-9a4e918d51ca)
      - Program 내 Instruction들의 주소를 시작 주소(Base Address)로 부터 상대적인 Offset으로 표현
      - 시작 주소 결정 시, 시작 주소 + 상대 주소의 합으로 절대 주소 생성 가능
  - CPU에서 Virtual Address를 사용하는 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/44f6625e-5ebf-4a5f-91f3-ebb6c97e9288)
      - Translation의 속도가 중요한 요소가 됨
- Memory Management Unit(MMU)
  - Virtual Address와 Physical Address 간의 변환을 수행하는 Hadrware 장치
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0013facd-84e1-49f8-a28d-a94423898493) 
### 가상 메모리
- 가상 메모리
  - 물리적으로 존재하진 않지만, Process가 바라보는 공간
- Basic Idea
  - Process가 수행 되기 위해 Program의 모든 부분이 Physical Memory에 있을 필요는 없음
    - 현재 실행되고 있는 Code 부분만이 실제 Memory에 있으면 Process는 실행 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d9ce69c2-c090-4dbf-a0e0-8c80f425d82d)
- 가상 메모리 구현
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2c0902fb-0268-4015-8fbb-8d705963e981)
    - 가상 메모리의 특정 영역을 미리 물리 메모리에 올려놓음
    - 나머지 부분은 SSD에 올리고 로드할 수도 있음
- Address Mapping Table
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/6c92d2ae-9dbc-4966-8ba9-60f5d75ac298)
    - 가상 주소 페이지가 더 많기 때문에 물리 주소 페이지에 다 들어가지 못하는 경우가 있을 수밖에 없음
    - 그래서 재배치가 일어날 수 있음
      - 순차적으로 매핑되어 있지 않음
### Paging
- 주소 공간을 동일한 크기인 Page로 나눠 관리
- 보통 1 Page의 크기는 4KB로 나눠 사용
  - 크기가 너무 작으면 메모리와 SSD 간 통신이 너무 많이 일어나게 됨
  - 크기가 너무 크면 메모리에 많은 프로세스를 올리지 못함
- 프레임(Frame, Physical Page)
  - 물리 Memory를 고정된 크기로 나눴을 때, 하나의 Block
- 페이지(Page)
  - 가상 Memory를 고정된 크기로 나눴을 때, 하나의 Block
- 각각의 프레임 크기와 페이지 크기는 같음
- Page가 하나의 Frame을 할당 받았다는 건, 물리 Memory에 위치하고 있다는 것임
  - Frame을 할당 받지 못한 Page들은 외부 저장장치(Backing Stroage, SSD 등)에 저장됨
    - Backing Storage도 Page, Frame과 같은 크기로 나눠져 있음
- CPU가 관리하는 모든 주소는 두 부분으로 나뉨
  - Page 번호
    - 각 Process가 가진 Page 각각에 부여된 번호
    - ex) 1번 Process는 0부터 63번까지의 Page를 갖고 있음
  - Page 주소
    - 각 Page의 내부 주소를 가리킴
    - ex) 1번 Process 12번 Page의 34번째 Data
    - 1 Page의 크기가 4KB(2^2 * 2^10)니까 offset은 12bit 사용
      - Byte를 bit단위까지 나눠줄 필요는 없음
        - 어차피 사용하는 최소 단위가 1Byte임
- 중간점검
  - 0. 128MB의 물리 Memory를 4KB 단위로 Paging 하려고 하면, 몇 개의 Frame이 필요한가?
    - 2^7 * 2^20 / 2^2 * 2^10 = 2^27 / 2^12 = 2^15
  - 1. 4GB의 Logical Address를 Paging 하려고 하면, 총 몇 개의 Page가 필요한가?(Page의 크기는 4KB)
    - 2^2 * 2^30 / 2^12 = 2^10 = 2^20
  - 2. Page의 크기가 4KB일 때, 한 Page의 Memory를 Access 하기 위한 주소 Bit는 몇 Bit인가?
    - 2^12, 12bit
### Page Table
- 페이지 테이블(Page Table)
  - 각 Process의 Page 정보 저장
  - Process마다 하나의 Page Table을 가짐
  - Index: Page 번호
  - 내용
    - 해당 Page에 할당된 물리 Memory(Frame)의 시작 주소
    - 이 시작 주소와 Page 주소(offset)를 결합, 원하는 Data가 있는 물리 Memory 주소를 알 수 있음
      - Page 주소는 물리 주소와 가상 주소 동일
- 페이지 테이블의 구현
  - Page Table은 물리 Memory에 위치
  - PTBR(Page Table Base Register)가 물리 Memory 내 Page 테이블을 가리킴
  - PTLR(Page Table Length Register)이 Page Table의 Size를 나타냄
- ![image](https://github.com/googoo9918/TIL/assets/102513932/37946592-658b-4477-90d8-480bac181bde)
  - Page Talbe을 이용한 VA, PA Mapping
- ![image](https://github.com/googoo9918/TIL/assets/102513932/f8128889-bb4e-4959-818e-822f233ca955)
  - Page Table을 이용한 주소 변환
- PTE(Page Table Entry)
  - Page Table의 Record
  - 각 필드 내용
    - Page Base Address
      - 해당 Page에 할당된 Frame의 시작 주소
      - 이를 통해 물리 Memory에 접근
    - Flag Bits
      - Page에 대한 접근, 변경, 할당된 Frame, 읽기/쓰기에 대한 권한 등이 있음
### Translation Look-aside Buffers(TLB)
- Paging 방식에서는 Data로의 접근이 항상 두 번의 Memory 접근을 거쳐야 함
  - Page Table에 한 번, 물리 Memory 내의 Data에 한 번
  - 속도를 떨어트림
  - 해결 방법 TLB in MMU
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/9d5145fd-422a-4e00-aa77-577e158a1434)
    - Page Table을 이용해 변환된 주소(Page Table Entry)를 TLB에 저장
    - 다음 접근 시, TLB에 저장된 값을 이용하여 빠르게 변환된 주소를 얻을 수 있음
      - TLB는 Register이기에, 빠른 수행 가능
    - TLB Hit Ratio
      - TLB 내에서 원하는 주소를 찾을 수 있는 확률
      - 높을 수록 Memory 접근 속도가 빠름
### 다양한 Paging Table
- Multi-level Page Table의 필요성
  - System의 발전에 따라 가상 주소 공간도 매우 큰 용량을 요구하게 됨
  - Page Table의 크기가 커지고, 차지하는 공간에 의해 Paging이 잘 이뤄질 수 없음
    - ex) 32Bit 가상 주소 공간을 갖는 System
      - Page 크기 4KB인 경우
      - Page Table 크기: 1MB * 4B = 4MB
        - 1MB는 Page 번호의 갯수, 4B는 보통 Page Table Entry의 크기
        - 한 프로세스가 4MB 만큼의 페이지 테이블을 갖게됨
        - 100개의 프로그램이 running 되는 경우, 400MB 만큼 page table이 차지함
  - Page Table 자체도 Paging된 공간에 저장
- 2 Level Page Table 구현
  - Outer Page Table을 하나 더 두어, Page Table을 가리키도록 함
  - 20 Bit를 차지하는 Page 번호를 다시 나눔
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/6ea5e46f-546e-41d5-89d0-cf0e62ad9137)
    - 10 Bit Page 번호
    - 10 Bit Page 주소
    - 도식
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/db742e12-3273-488f-bdd3-8dea31208d97)
    - Table Walk
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/b52ef940-fba9-4b06-9f47-21d7af4c8b46)
        - Table Walk에 걸리는 시간은 증가함
    - 12KB Process Mapping
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/20baf0cc-66a1-4ef9-85c4-78b07d5768a4)
      - 1개의 L1 Page Table, 3개의 L2 Page Table을 이용하여 Mapping 가능
- Inverted Page Table
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/615fec7a-7dc8-40a8-9650-4a669ffd5837)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/02b0ba77-115b-4f26-9c99-9648cf2f4762)
    - 기존 방식은 Page #을 이용하여 Frame #을 검색
    - Inverted Page Table은 ProcessId와 Page를 통해 물리적 메모리에 직접 Index를 매김 
      - Page Table Index: Frame의 번호
    - System 전체에 하나의 Page Table만 두게됨
      - 예를 들어 물리 메모리가 8GB(2^33)라면, 2^33 / 2^12 = 2^21 만큼의 Page 수를 갖게 됨
      - 하나의 페이지 테이블 엔트리 크기가 4B라 하면, 2^23(8MB)만큼의 Page Table 크기를 가짐
    - Page Table은 보다 적은 용량을 차지하게 되지만, Table을 검색하는데 시간이 오래 걸리게 됨
      - 해쉬 테이블 등을 사용하여 단축 가능
- Demanding Paging
  - Process의 실행을 위한 모든 Page를 Memory에 올리지 않고, 필요한 Page의 요청이 발생할 때 메모리에 올리는 Paging 기법
  - Paging Service를 통해 한 Process에 필요한 Page를 Memory와 Secondary Storage 간에 이동시킴
  - Valid and Invalid Page
  - 장점
    - 실행을 위한 물리 Memory 구성의 시간이 줄어듬
    - Process의 전체 이미지를 Memory에 올리지 않기 때문에, 실제 필요한 전체 물리 Memory의 양을 줄일 수 있음
  - 단점
    - 참조하려는 Page가 Valid한 경우
      - 실제 물리 Memory에 있기 때문에 정상적인 참조가 일어남
    - 참조하려는 Page가 Invalid한 경우
      - 실제 물리 Memory에 없으므로 이에 대한 처리 필요
      - Page Fault 발생
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/f683334c-abde-45f9-8565-6357b1c41ef0)
  - Page Fault
    - Process가 Page를 참조하였을 때 해당 Page가 할당 받은 Frame이 없는 경우
    - Page Fault Handler가 수행하는 내용
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/0c5f89ca-03cb-4cf5-96c6-432d4a84d8ba)
      - 새로운 Frame을 할당 받음
      - Backing Storage에서 Page의 내용을 다시 Frame에 불러들임
      - Page Table을 재구성
      - Process의 작업 재시작
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/b46a1441-aefc-4576-b18a-73a62c581b9c)
      - Page fault 발생 빈도는 프레임의 개수와 반비례
        - 프레임의 개수가 많아지면 page를 많이 저장할 수 있으니 일반적으로 더 좋아짐
        - 다만, FIFO 알고리즘 같은 경우 벨라디의 역설이 존재하기도함
    - Locality
      - 일반적인 경우
        - ![image](https://github.com/googoo9918/TIL/assets/102513932/8cb0991a-cb17-4ecd-bb63-765e83551bb1)
      - Video Playing
        - ![image](https://github.com/googoo9918/TIL/assets/102513932/30295848-1013-4d74-90e2-54e44d791571)
        - 주로 예측하기 어려운 패턴을 가질 때..
          - 대용량 DB 순차적 스캔, 대용량 파일 처리, 가상화 환경 등...

## Memory Management(2)
### Page Replacement
- Memory 과다 할당 상태
  - Memory 내에 위치한 User Process의 수가 증가함에 따라 발생
  - 모든 User Process가 사용하는 Page 수보다 물리 Memory의 Frame 수가 적은 상황
- 해결 방법
  - Page Falut 처리에 Page Replacement를 추가
    - 물리 Memory에 위치한 Page를 Disk에 저장하고, 요구된 Page가 해당 Frame을 할당 받도록 하는 방법
- Page Fault with Page Replacement
  - 디스크에서 요구된 Page의 위치를 찾음
  - 물리 Memory에서 Free Frame을 찾음
    - Free Frame이 있으면 사용
    - 없으면, Page Replacement Algorithm을 사용하여 교체할 Frame을 선택
    - 교체할 Frame을 Disk에 저장, Page Table을 변경
  - Page Table에 변경 내용 저장
  - User Process 재시작
- ![image](https://github.com/googoo9918/TIL/assets/102513932/eb22185c-2cb7-4df5-a6b2-700eecd43ec8)
- Page Replacement 고려 사항
  - 각각의 User Process에게 어떻게 Frame을 분배해 줄 것인가?
    - Frame Allocation Algorithm
  - Page 교체가 필요할 때 어떻게 교체할 Page를 고를 것인가?
    - Page Replacement Algorithm
  - 위 알고리즘은 모두 Page 교체에 의한 I/O 작업 수행 햇쇠룰 최대한 줄이려는 목적을 가짐
    - 적합한 Algorithm의 사용은 System의 성능을 크게 좌우함
    - I/O 작업은 매우 큰 비용을 사용하고, SSD는 쓰기 횟수가 정해져 있기 때문
- Page Replacement Algorithms
  - 어떤 Page Replacement Algorithm을 사용할 것인가?
    - 가장 낮은 Page Fault 발생 빈도를 가진 Algorithm
    - 즉, 가장 낮은 I/O 작업 횟수를 요구하는 Algorithm
  - 여러 Algorithm들을 비교하기 위해 아래 환경 가정
    - 세 개의 Frame 할당
      - Page Fault 발생 빈도는 Frame의 개수와 반비례
    - Page를 참조하는 순서 --> 20번
      - 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1
    - 한 번 참조된 Page는 교체가 일어나기 전에는 물리 Memory에 위치
- Optimal Agorithm
  - 앞으로 가장 오랫동안 사용되지 않을 Page 부터 먼저 교체
    - 이론상 최적, 그러나 앞으로 어떤 Page가 사용될지 미리 알 수 없기 때문에 실제 구현 불가능
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/74fded2e-43b0-464c-9c47-7e16d62ed3da)
- FIFO Algorithm
  - 먼저 Frame이 할당된 Page를 먼저 교체
  - locality가 낮은 멀티미디어 Data에서 사용하기 적합
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d5ca42c0-ff79-42c6-9545-58383a49c7a6)
- SCR Algorithm
  - Second Chance Replacement
  - FIFO 기법의 단점을 보완
  - 오랫동안 주 기억장치에 존재하던 Page 중에서 자주 사용되는 Page 교체를 방지하기 위해 고안
  - FIFO Queue를 만들고 사용하되, 참조 Bit(Reference bit)를 두어 Page를 관리함
    - 참조 Bit(Reference Bit)
      - 최초로 Frame에 Load 될 때와 Page가 참조되었을 때마다 1로 Set
      - 일정 주기마다 다시 0으로 Reset
  - 제거 대상으로 선택된 Page의 참조 Bit가 1로 Setting된 경우, 최근에 사용된 Page이므로 제거하는 대신 참조 Bit만 0으로 Reset
  - 참조 비트 없이 Page Table Hit이 발생한 경우 해당 Frame을 Queue의 맨 끝으로 옮기는 방식으로 구현하기도 함
    - 이건 사실 LRU와 동일함
- Clock Algorithm
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/545b80da-1f5e-48b6-a003-54f71246417f)
  - SCR 기법의 발전형
    - 큐를 순회하는데 있어서 효율상 이점을 제공함
  - Circular Queue를 사용하여 Frmae을 관리
  - 다음에 제거될 Page를 가리키는 Hand라는 Pointer를 둠
  - Hand는 Queue를 따라 1칸씩 이동함
  - Hand가 가리키는 Page의 참조 Bit가 1이라면, 최근 접근한 Page이므로 제거하는 대신 참조 Bit만 0으로 Reset
- LFU Algorithm
  - Least Frequently Used
  - 사용 빈도가 가장 적은 Page를 교체하는 기법
  - 지금까지 가장 적게 참조된 Page가 교체 대상으로 선택
    - 참조 횟수가 Page Table에 추가되어야 함
  - Program 실행 초기에 많이 사용된 Page는 그 후로 사용되지 않더라도 Frame을 계속 차지하는 문제가 있음
- NRU Algorithm
  - Not Recently Used
    - 최근에 사용하지 않은 Page를 교체하는 기법
  - Page마다 참조 Bit와 변형 Bit를 두어 관리함
    - 참조 Bit
      - 최초로 Frame에 Load 될 떄와 Page가 참조되었을 때마다 1로 Set
      - 일정 주기마다 다시 0으로 Reset
        - 주기에 대한 시점을 모든 페이지가 알고 있어야 됨
    - 변형 Bit
      - 최초로 Frame에 Load 될 때는 0
      - Page의 내용이 바뀔 때 1로 Set
    - 교체 우선순위
      - 참조 0, 변형 0
      - 참조 1, 변형 0
      - 참조 0, 변형 1
      - 참조 1, 변형 1
- LRU Algorithm
  - Least Recently Used
  - 가장 오랜 시간 참조되지 않은 Page부터 먼저 교체함
    - 주로 가장 최적의 알고리즘 (Locality 고려)
  - 구현 방법
    - Counter의 사용
      - 참조된 시간을 기록
    - Queue의 사용
      - 한 번 사용한 Page를 Queue의 가장 위로 이동시킴
        - 가장 위의 Page: 가장 최근에 사용된 Page
        - 가장 아래의 Page: 가장 오래 전에 사용된 Page
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/8c5c42bb-eb53-44f4-b4c7-5404a803f623)

### Swapping
- Page Out으로 Memory 부족을 해결하지 못할 경우 필요한 기법
  - Page out은 활성화되지 않은 페이지를 디스크로 옮겨 메모리 공간을 확보하는 것
  - 개별 페이지 단위로 작동함
- Swap Out 대상이 된 Process 전체를 Secondary Storage로 보냄
  - 보다 극단적인 메모리 부족 상황에서 사용됨
  - 페이지 단위의 메모리 관리만으로 해결이 어려울 때 사용
- 이렇게 Page Out이나 Swapping에 사용되는 Secondary Storage(Backing Storage)를 Swap 영역이라함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/a711bf35-79e6-410a-9c8b-8b0b808c8fa2)
### Contiguous Memory Allocation
- ![image](https://github.com/googoo9918/TIL/assets/102513932/7d896cab-cffc-41d8-ad0b-12e923567f2b)
  - Single Partition Allocation
    - 가장 단순하게 Memory를 사용
    - User Program 영역을 한 번에 1개의 User Program만 사용
  - Multiple Partition Allocation
    - [1]의 방법에 Multiprogramming 개념을 추가하여 User Program 영역을 여러 개의 User Program이 사용하도록 함
    - Partition과 Program의 크기가 명확하게 들어맞지 않기 때문에 일정 공간이 낭비될 수밖에 없음
  - No Partition
    - 각 Program이 필요에 따라 전체 User Program 영역을 사용
    - Page/Swap Out 시 Garbage Collection 필요
      - 프로그램이 쓰는 페이지를 수집하는 과정이 필요
- Memory Allocation Problem
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8e729f58-4451-4a97-b331-4a9c3360c9c9)
  - User Program이 Load될 때, 물리 Memory의 OS 영역을 제외한 User 영역에 배치됨
  - Protection, Relocation, Swap 기법 사용
  - Program을 Memory에 Load할 때, Memory의 빈 공간 중 어디에 Program을 Load할 것인가?
  - First-fit
    - 가장 먼저 발견한 곳에 배치
  - Best-fit
    - 사용가능한 공간 중 가장 작은 곳에 배치
### Fragmentation
- External Fragmentation
  - Contiguous Memory Allocation에서 발생
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/8bee2ff1-7bf9-47ca-92f6-c1434bfc2a68)
  - Program에게 할당 후 남은 Memory의 총 공간은 새로운 할당 요청에 충분하지만, 그 공간이 연속적이지 않아 사용할 수 없는 경우
    - Paging을 통해 해결 가능
  - 연속적 메모리 할당과 해제의 결과로 발생됨
- Internal Fragmentation
  - 프로세스의 크기가 3998B인 경우, 페이지 크기는 4KB
    - 2B의 Internal Fragmentation이 발생
  - 개별 할당된 메모리 블록 내부에서 발생
    - 프로세스의 메모리 요구량과 할당된 블록의 크기 사이의 불일치에서 기인함
  - Paging 대신 Segmentation 사용 등, 가변 크기 메모리 할당 방식으로 해결 가능
- Protection과 Relocation
  - Protection
    - Contigous Memory Allocation 방법 사용 시, OS의 Memory 영역과 User Program의 Memory 영역은 서로 구분되어야 함
    - 서로의 영역을 침범하지 못하도록 보호해야함
  - Relocation
    - User Program은 재배치 가능한 주소로 표현됨
    - 재배치 가능한 주소를 이용, Program이 어느 위치에 load 되더라도 쉽게 Code의 주소를 결정할 수 있어야 함
  - Protection과 Relocation을 위한 Hardware의 지원
    - Limit Register와 Relocation Register를 이용하여 구현
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/35a259f1-3472-4a59-a788-669c81f0b0fc)
      - Limit Register
        - 참조가 허용되는 주소의 최대값
        - 이를 통해 참조하는 주소가 허용되는 영역인지 판별
      - Relocation Register
        - Program이 차지하는 주소 영역 중 첫 번째 주소
        - 재배치 가능한 주소를 통해 실제 물리 Memory의 주소로 참조 가능하게 함