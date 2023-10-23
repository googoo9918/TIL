### 목차
- [소프트웨어 분석 및 설계](#소프트웨어-분석-및-설계)
  - [Introduction](#introduction)
    - [소프트웨어](#소프트웨어)
    - [Object-oriented Programming](#object-oriented-programming)
    - [객체](#객체)
    - [추상화](#추상화)
    - [캡슐화](#캡슐화)
    - [상속](#상속)
    - [다형성](#다형성)
    - [정리](#정리)
  - [Use Case Analysis](#use-case-analysis)
    - [Unified Process for Developing OOSW](#unified-process-for-developing-oosw)
    - [OOAD with UML](#ooad-with-uml)
    - [Requriement analysis](#requriement-analysis)
    - [Use Case Analysis](#use-case-analysis-1)
  - [Domain Model](#domain-model)
    - [Conceptual Class Diagram](#conceptual-class-diagram)
  - [System Sequence Diagram \& Contracts](#system-sequence-diagram--contracts)
    - [System Sequence Diagram](#system-sequence-diagram)
    - [Operation Contracts(작업 규약)](#operation-contracts작업-규약)
  - [Case Study for OOA](#case-study-for-ooa)
  - [Package Diagram](#package-diagram)
    - [Logical Architecture](#logical-architecture)
    - [계층을 이용한 설계](#계층을-이용한-설계)
    - [Typical Layers in OO System](#typical-layers-in-oo-system)
    - [Package Diagram](#package-diagram-1)
    - [Steps for Package Diagram](#steps-for-package-diagram)
    - [Guideline for Package Diagram](#guideline-for-package-diagram)
    - [Designing Objects](#designing-objects)
  - [Interaction Diagram](#interaction-diagram)
    - [Dynamic Object Modeling](#dynamic-object-modeling)
    - [Sequence Diagram](#sequence-diagram)
    - [Communication Diagram](#communication-diagram)
  - [Interaction Diagram(2)](#interaction-diagram2)
    - [Combined Fragments](#combined-fragments)
    - [Exception Itneraction](#exception-itneraction)
    - [Weak Order of Sequences](#weak-order-of-sequences)
    - [Atomic Interaction](#atomic-interaction)
    - [Interaction Reference](#interaction-reference)
  - [Class Diagram](#class-diagram)
    - [Notations in Class Diagram](#notations-in-class-diagram)
    - [Types of Class Relationship](#types-of-class-relationship)
  - [Class Diagram(2)](#class-diagram2)
    - [Association class](#association-class)
    - [Whole-part Relationship](#whole-part-relationship)
    - [Composition Relationship](#composition-relationship)
    - [Aggregation VS Composition](#aggregation-vs-composition)
    - [Inheritance Relationship](#inheritance-relationship)
  - [State\&Activity Diagram](#stateactivity-diagram)
    - [State Machine Diagram](#state-machine-diagram)
    - [Activity Diagram](#activity-diagram)
  - [Case Study for OOD](#case-study-for-ood)
    - [State Machine Diagrams](#state-machine-diagrams)
    - [Activity Diagram](#activity-diagram-1)
    - [Logical Architecture](#logical-architecture-1)
    - [Sequence Diagrams](#sequence-diagrams)
    - [Class Diagram](#class-diagram-1)
  - [SOLID](#solid)
    - [SRP](#srp)
    - [OCP](#ocp)
    - [LSP](#lsp)
    - [ISP](#isp)
    - [DIP](#dip)
# 소프트웨어 분석 및 설계
## Introduction
### 소프트웨어
- 소프트웨어 개발의 어려움
  - 실체를 감지하기 어려움 -> 품질 평가가 어려움
  - 다수 사용자를 대상, 다양한 기능 개발 요구
  - 계속 수정하면 설계 구조가 약화 될 수 있음
- 소프트웨어 위기
  - 하드웨어의 발달로 SW의 수요가 늘고, 규모가 커짐
    - 다만 SW 공급을 높이기 위한 생산성 향상은 더딤
  - 즉, SW 개발 속도가 하드웨어 개발 속도를 따라가지 못해 사용자 요구사항을 감당할 수 없음
- 해결책
  - 소프트웨어 공학
    - 품질 좋은 대규모 소프트웨어 시스템을 정해진 시간과 비용으로 개발하거나 발전시키는 체계적인 절차/방법론
    - 분석, 설계, 모델링, 구현, 테스트, 관리
- 분석과 설계의 필요성
  - 최적의 비용으로 협력해 목표하는 바를 이루려면, 정교한 분석과 설계 필요
  - 설계의 특징
    - 정답이 명확하지 않고, 좋은 설계를 찾기 어려움
    - 그림 작업이 많음(추상적임)
    - 반복, 시행 착오가 많음
    - 의사 소통이 중요
  - 설계의 목적
    - 생각, 이야기, 표현하기 위한 디자인
- 분석
  - 도메인 분석, 문제 정의, 요구 추출, 요구 분석, 요구 명세화 등
    - 고객의 문제를 SW적으로 해결하기 위해 고객의 비즈니스 환경과 문제, 문제를 해결하는 데 필요한 기술을 파악
    - What과 How를 명확히 분리하는 것이 중요함
      - 해결책(How) 보다는 **문제를 정의하는 것(What)**에 집중
- 설계
  - 쓸 수 있는 기술을 이용, 요구를 어떻게(HOW) 구현할 것인지 결정
    - 시스템 설계, 아키텍쳐, 상세 설계, 인터페이스 설계, 자료 설계
  - 구현 보다는 요구사항을 실현시키는 conceptual solution에 집중
- 모델링
  - 복잡한 SW를 바로 구현하는 것은 어려움
    - 동작과 구성을 도면도처럼 자세히 표현
    - 사용 사례 모델링, 정적 모델링, 동적 모델링
- 객체지향
  - 시스템이 객체로 구성되고, 관련된 객체 사이의 상호작용으로 동작
  - 현대 SW는 대부분 객체지향 방법론에 따라 개발
    - 보안이 중요한 은행, 군사 도메인 등 제외
    - 모듈화, 재사용성, 확장성, 유연성, 유지보수성 향상
  - 객체지향 분석
    - 문제 영역에서 개념을 객체로 표현하고 기술하는데 중점
  - 객체지향 설계
    - 객체들이 어떻게 협동하는지 중점

### Object-oriented Programming
- 객체지향 프로그래밍
  - 프로그램을 여러 객체라는 기본 단위로 나누고 객체 사이의 상호 작용으로 동작하는 것으로 인식하고 기술하는 방식
- 순차적/절차적 프로그래밍
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/98e4a125-915b-4f97-b1f1-d53afcd88529)
  - 순차적(sequential)
    - 명시된 입력을 받아서 명시된 순서로 처리하고 순차적으로 결과를 냄
  - 절차적(procdural)
    - 프로그램을 여러 작은 함수(procedure)들의 단계적 호출로 구성
      - 단계의 절차가 아닌, 함수의 절차이므로 꼭 순차적이지 않을 수 있음
    - 큰 문제를 작은 문제로 나눠 접근(top-down)
    - 문제점
      - 데이터의 처리 방법만 구조화, 데이터 자체는 구조화 하지 못함
      - 데이터와 함수를 분리하여 생각, 관리가 어려움
      - 프로그램 규모가 커지면 함수들이 서로 얽혀 유지보수 어려움
        - 데이터 변경 되면 수정해야 하는 함수 많아짐
        - 함수나 호출 순서가 바뀌면 결과값에 변경이 있을 수 있음
- 객체지향 프로그래밍
  - 데이터와 함수를 하나의 덩어리로 묶어 생각
  - 작은 문제를 해결할 수 있는 객체를 만들고, 이 객체를 조합해 큰 문제를 해결(bottom-up)
  - 독립성/신뢰성이 높게 만들면 이후에 수정없이 재사용 가능
    - 생산성 대폭 향상
- 절차적 vs 객체지향
  - PP에서는 함수가 *계층적인 구조*를 이룸  
  - OOP는 클래스의 모임으로 이루어지고, *관계*로 표현
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ff8cfca3-c690-4e62-9e24-839f8ee25ea6)

### 객체
- 객체의 의미
  - 어떠한 상태 값과 행위를 갖고 있는 데이터
- 절차지향적으로 객체 구성 시
  - 상태는 구조체로 표현
  - 행위는 구조체로 표현된 상태를 입력으로 받는 함수로 표현
  - 즉, **데이터와 함수가 분리**되어 있음
- 객체지향에서 객체
  - 데이터(상태)와 행위를 하나로 묶어서 관리
    - 캡슐화
- 클래스
  - 동일한 속성과 행위를 수행하는 객체의 집합
    - 클래스는 데이터 타입으로 인식하기도 함
  - 즉, 객체는 클래스의 인스턴스임
- 인스턴스 변수 vs 클래스 변수
  - 인스턴스 변수
    - 필드 변수, 각 인스턴스에 존재하는 데이터를 저장하기 위한 변수
    - 클래스 내에 선언되는 변수, 객체 생성 시마다 *매번 새로운 변수 생성*
    - 다른 객체와 공유하지 않음
  - 클래스 변수
    - 클래스에 속하는 객체들이 공유할 수 있도록 해주는 변수
    - static으로 선언, 처음 딱 한 번만 생성됨
  - 메모리 생성 위치
```java
public class StaticTest{
    static int classvar = 10;
    int instance var;
    public void method(){
        int localVar = -100;
    }
}
```
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e34d5701-2c5a-4b80-aa3a-b6d35ac054d5)
  - static method 사용 시 주의 사항
    - static method는 오직 클래스 변수로만 접근 가능(인스턴스 변수를 사용하지 못함)
      - static method도 클래스 변수와 로드되는 시점이 동일
      - 따라서 객체가 생성되지 않은 상황에서도 변수를 사용할 수 있어야 함(인스턴스 변수는 아직 생성되지 않음)
    - this 키워드 사용 불가
      - this는 호출 당시 실행 중인 객체를 가리키는 레퍼런스
      - static method는 객체가 생성되지 않은 상황에서 호출이 되어야 함
    - 클래스 변수가 필요한 이유
      - 클래스 안에 정의한 메서드에 의해 널리 사용되는 디폴트/상수 값
      - 그 외 전역변수로 사용히 필요할 경우
        - 캡슐화의 우너칙에 예외 이므로, 무분별히 남발하지 말 것
- 변수 vs 객체
  - 변수는 객체를 가리키는 nametag(reference)
  - 특정 시점에 객체를 담고 있을 수도, 아닐 수도 있음
  - 한 객체는 여러 변수에 의해 가리켜질 수도 있음
  - 하나의 변수가 다른 객체를 가리킬 수도 있음
- 오퍼레이션 vs 메서드
  - 오퍼레이션
    - 수행되어야 할 연산을 추상화 한 것
      - 행위가 어떻게 구성 되는지 선언만 하고 내부 구현은 신경 쓰지 않음
    - 특정 클래스에서 operation의 구현체

### 추상화
- 사물의 중요한 특징에 집중, 덜 중요한 상세 사항은 가려냄
  - 공통된 특징은 올리고, 세부사항은 감춤
  - ex) 리모컨으로 tv 조작 시 -> 사용자는 버튼을 누를 뿐, 어떤 동작이 이뤄지는지는 모름
  - ex2) 자료구조에서 추상 자료형을 생각할 시
    - 사용자는 사용에 집중
    - 구현자는 구현에 집중
  - ex3) 사람 객체를 모델링 할 때
    - 상황에 따라 필요한 것만 선별하여 속성/행동을 추려냄
    - 여러 종류의 사람의 공통 개념적 특정을 묶어 한 단계 높은 추상화를 할 수 있음
- 데이터 추상화
  - 여러 자료형을 하나로 묶어 더 높은 단계로 추상화 함
    - ex) 일반화
- 프로시저 추상화
  - 함수를 어떻게 호출하고, 무엇을 수행하는지만 알게 하고 함수 내부 자세한 사항(구현)은 감춤
- 추상화는 디자인의 중요 원칙임
  - 설계 시 추상적인 계약(요구사항, 인터페이스)을 실제 구현된 구체적인 부분(클래스, 모듈)과 분리하여 설계
  - What과 How를 분리
  - 고수준의 전체 아키텍쳐를 고려, 이후에 저수준의 구현 세부 사항을 다룸

### 캡슐화
- 캡슐화
  - 변수와 메소드를 캡슐로 감싸는 개념
    - 데이터와 행위를 클래스 안으로 *숨김*
    - black box로서 input/output과 연계된 interface만 공개하고, **내부 구체 사항은 감춤** (추상화)
      - 사용자가 알 필요 없는 정보는 외부에서 접근하지 못하도록 제한(정보 은닉)
- 캡슐화 정보 은닉 예시
  - 클래스로 데이터와 함수를 묶음
  - 데이터는 외부에서 접근 할 수 없게 하고, 개발자가 허락한 함수만 사용하게 함
    - 데이터를 보호함으로써 의도치 않은 동작을 방지할 수 있음
- 응집도(cohesion)
  - 클래스 안 요소들이 얼마나 밀접하게 관련되어 있는지
- 결합도(coupling)
  - 어떤 기능을 실행하는 데 다른 클래스에 얼마나 의존적인지
- 높은 응집도와 낮은 결합도를 유지해야 요구사항 변경에 유연하게 대처 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8b795190-1f50-4fe7-9260-3c5ebe9c1967)
  - 캡슐화는 정보 은닉을 통해 높은 응집도, 낮은 결합도를 갖도록 도와줌
- 예시
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/50db5e61-b6e7-49f5-8e55-6c91fc3cf0d6)

### 상속
- 일반화의 의미
  - 여러 개체들이 가진 공통된 특성을 부각, 하나의 개념으로 성립시키는 과정(추상화)
  - OOP에서는 여러 개의 서브 클래스로부터 공통점을 찾아내고, 하나의 슈퍼 클래스를 도출하는 과정을 의미
- 상속
  - 슈퍼 클래스에 정의한 변수와 메서드를 서브 클래스가 묵시적으로 소유하도록 함
    - 공통 속성, 메서드를 반복적으로 정의하지 않아도 됨
- IS`-`A 관계
  - An A is a kind of B가 성립하면 A는 B의 일종이다로 해석, A 클래스가 B의 서브 클래스가 될 수 있음
    - Rectangle은 Shape의 일종
    - Shape은 Rectangle의 일종
- 메서드 오버라이딩
  - 슈퍼클래스에 정의된 메서드를 서브클래스에서 재정의
  - 이름은 공유, 각 서브 클래스의 맥락에 맞게 내용을 바꿀 수 있음
  - 주의
    - `Person person = new Student()`의 경우
    - Person 타입 참조를 갖지만, 실제로 가리키는 객체는 Student 타입임
      - 오버라이드 하였다면 Student의 메서드가 실행, 안했다면 Person의 메서드가 실행됨

### 다형성
- Poly(many) + morphism(form)
  - 어떤 객체의 속성이나 기능이 상황에 따라 여러 가지 형태를 가질 수 있는 성질
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/88b01e5f-9e40-4248-85a4-b3a01542f074)
- 장점
  - 코드를 간결히 표현
  - 변경 사항에 유연히 대처
- 추상 클래스(abstract class)
  - render 오퍼레이션 구현 방법이 다 달라, render의 구현을 슈퍼 클래스인 HTMLElement에서 할 수 없음
  - 오퍼레이션만 정의하는 것을 가상 함수 또는 추상 메서드라 지칭
  - HTMLElement을 추상 클래스라 지칭함
- 추상 클래스 특징
  - 추상 메서드를 갖는 클래스는 추상 클래스로 선언해야 함
  - 동작이 정의되어 있지 않아 객체를 생성할 수 없음
  - 상속을 받은 서브 클래스는 추상 메서드를 오버라이딩 해서 구현해야 함

### 정리
![image](https://github.com/googoo9918/TIL/assets/102513932/7339b748-7525-42b9-bf49-a52c6752f6e6)

## Use Case Analysis
### Unified Process for Developing OOSW
- 객체지향 SW 개발 절차
  - 소프트웨어 개발, 배포, 관리에 대한 체계적인 방법
  - 폭포수 모델, Iterative model(에자일) 등
- Unified Process(UP)
  - 객체지향 SW를 개발할 때 주로 사용되는 반복적 SW 개발 절차
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/feed7997-2639-45f6-bef7-c37c914085f4)
    - 고정된 기간 내에서 반복적으로 수행
    - Agile 정신으로부터 영향을 받음
  - 4 Phases of Unified Process
    - Phase 1. Inception(도입부)
      - 프로젝트의 방향(vision), 요구 및 사용사례(case), 범위(scope), 비용(cost)등을 *대략적*으로 파악
    - Phase 2. Elaboration(세부화)
      - Inception 단계에서 정의된 초기 계획과 개념을 *자세히 확장/명시화*
      - 초기 설계 및 계획을 확립하고, risk를 관리
    - phase 3. Construction(구축)
      - 이전 단계에서 정의된 요구사항, 아킽텍처 및 설계를 기반으로 *SW 반복적 개발*
    - phase 4. Transaction(전환)
      - 사용자들이 소프트웨어를 사용할 수 있도록 준비하는 단계(테스트, 배포, 유지보수)
  - Key practices of Unified Process
    - 초기 단계에서 어떤 작업이 high-risk & high value 인지 파악
    - 초기 단계에서 *핵심 아키텍처 구축* 및 이를 중점으로 개발
    - UML 이용, *시각적 모델링*
    - 사용자로부터 *지속적으로 평가와 피드백*
    - 다양한 문서, 모델, 코드와 같은 *산출물(artifacts)* 생성되고 관리

### OOAD with UML
- Object-oriented analysis(OOA)
  - Domain concepts 또는 objects를 파악 및 도출
- Object-oriented desingn(OOD)
  - SW objects를 정의(static)
  - 요구사항을 만족할 수 있도록 객체 간 협력을 정의(dynamic)
- Example with UML
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/07cbd115-f3ad-44a1-957e-1b7bc0368962)
    - Use Case Diagram은 Behaviour(동적) Diagram
- UML(Unified Modeling Language)
  - 시스템의 설계를 시각화 하여 표준화된 방법론을 제공
    - 설계를 표현하는 수단임

### Requriement analysis
- 요구 분석
  - Requirements(요구)
    - 시스템이 반드시 따라야하는 기능, 내부 성능, 조건 등을 말함
  - Requirement analysis(요구 분석)
    - 시스템이 정말 필요한 것이 무엇인지 찾고 정리하는 과정
    - 고객 및 개발자에게 모두 명확히 표현되어야 함
- 요구의 유형(FURPS)
  - 기능적 요구(functional)
    - Function(기능): 시스템이 외형적으로 나타내는 기능(일반적인 CRUD)
      - ex) 사용자 인증, 데이터 검색 및 처리
  - 비기능적 요구(non-functional)
    - Usability(사용성): SW를 쉽게 이해/사용 하게 할 것
      - ex) 사용자 인터페이스, 도움말 문서 등
    - Reliability(신뢰성): SW가 안정적으로 동작하게 할 것
      - ex) 오류 복구, 장애 안정성 등
    - Performance(성능): SW의 성능에 관련된 사항
      - ex) 응답 시간, 처리량, 대기 시간 등
    - Supportability(지원성): SW 요지 보수 및 지원
      - ex) 시스템 업그레이드, 기술 지원 등
    - ex)
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/d2d619f6-c8d3-47d4-baa8-a09cc8fba342)
- 요구를 정리하는 방법
  - 요구 분석서(SRS)의 작성
    - 문제, 배경, 환경 및 시스템, 기능적 요구, 비기능적 요구
    - 기능적 요구는 시스템이 사용되는 시나리오를 기반으로 사용 사례(use-case)를 기술
      - Use-case diagram/Use-case specification 작성
    - 비기능적 요구는 시스템 설계에 부여되는 상세 및 제약 사항
      - 보통 문서의 형태로 정리함

### Use Case Analysis
- 사용 사례 분석(use-case) 분석
  - 시스템 사용에 대한 시나리오로 사용자 관점에서 시스템을 모델링
  - 사용자가 시스템에 대하여 바라는 바를 표현
  - 일반적으로 *기능적 요구*에 대해 사용 사례 분석
- 사용 사례 분석 과정
  - 시스템 요구 분석 -> System 및 actors 정하기 -> Use case 정하기 -> Use case diagram 그리기 -> Use case specification 문서 작성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3c2d1175-a9b3-431a-b9f9-c17b814b6d88)
- Systems
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8c25748d-5a43-4caf-88d0-c7cfee92a0fe)
  - 만들려고 하는 대상
  - 직사각형으로 표시
    - 직사각형 내로 시스템의 범위를 정함
- Actors
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ac7a64c7-ea23-4318-92e4-5f39fbf57aa2)
  - 시스템을 사용하려고 하는 대상
  - 사람, 조직, 디바이스, 다른 시세틈 등등
  - Stick figure로 표현
    - 시스템 밖에 위치해야 함
    - 카테고리로 표현 되어야 함
  - Primary actors
    - 시스템의 사용을 시작하는 actor, 왼쪽에 위치
  - Secondary actors
    - 요청에 반응하여 서비스를 제공하는 actor, 오른쪽에 위치
- Use-case
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f415c02f-f38f-4ecd-83b0-5904b3880773)
  - 시스템이 제공하는 기능, *타원형*으로 표현
    - 동사로 시작, 충분한 설명을 포함하되 최대한 간결하게 표현
    - 논리적 순서로 use cases를 배치
- Association Relationship
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/212f23e5-ef3c-43dc-88eb-3639199e3e37)
  - Actor와 use-case 간의 상호 작용, 선으로 이어 표현
- Inclusion Relationship
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ea976f67-3bac-4579-9ffe-41ef26d589c2)
  - Base use case가 수행되면 included use case는 반드시 수행되어야 하는 경우
    - ex) 로그인 -> 비밀번호 인증 과정
- Extension Relationship
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/fcb040ac-a75b-431c-b4c0-a005d4655062)
  - Base use case가 수행되고 특정 조건을 만족하는 상황에 extension 수행
    - ex) 로그인 <- 로그인 에러
- Inclusion vs Extension
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8d34aef6-6ed7-42fd-9a75-32fdb87b0282)
  - Inclusion은 반드시 수반되어 수행
  - extension은 수행 될 수도, 안될 수도 있음
- Multiple Inclusions
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0ba48da3-8391-4152-ae0f-8ea8502d563d)
- Generalization Relationship
  - 유사한 actor/use case에서 공통된 부분을 묶어 일반화한 관계
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/dd097aca-500f-40a1-af17-53b564362aa3)
- Extension Points
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/430cdacb-1936-4bdc-b506-209a7b9bb3a3)
  - 확장 관계의 detailed version
    - 필요하면 조건에 대한 내용을 노트로 추가
- 사용 사례 명세서(Specification)
  - 사용자 시나리오와 요구 사항을 문서화
  - 가이드 라인
    - 사용 사례 이름, 액터, 목적, 시작 조건, 관련 사용 사례, 사건의 흐름, 종료 조건
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/bbd00f45-d17e-4052-98db-2866e3f28525)

## Domain Model
- 도메인 모델
  - 도메인에서 상호 연결된 개념 또는 실세계 객체를 시각적으로 표현
    - 도메인은 SW로 다루고자 하는 주제의 영역임 
  - 초기 분석 단계에서 전체 도메인을 대략적으로 파악하는 용도로 수행
    - 도메인 내의 개체, 속성, 관계 및 동작을 파악
  - domain concepts을 분석하는 것이 목표
  - Lower representation gap
    - 분석 단계와 설계 단계의 표현적인 차이를 줄일 수 있음
- Lower Representation Gap
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/6e86e5ae-534f-4583-96bb-f87008ea1fd5)
    - 추상적 부분을 잘 만들어두면, 구체화가 용이함
- Domain Modeling
  - 도메인 모델 표현법
  - 일반적으로 class diagram을 기반으로 하나, *단순화된 형태*로 표현
    - 즉, 추상적인 class diagram임
    - operation은 고려하지 않음
    - Domain objects를 표현, 관계를 표현, 속성을 표현
  - OOA에서 대략적인 클래스의 관계 도식화 --> OOD에서 상세한 class diagram을 작성
  - 따라서 Domain model을 종종 conceptual calss diagram이라고도 칭함
- Steps for Domain Modeling
  - 1. 목표 도메인의 business concept를 나열
  - 2. concept에 대응되는 domain object 정하기
  - 3. object 간 연관 관계 파악
  - 4. 각 관계에 대해 구체적 이름 부여
  - 5. role/multiplicity 추가
  - 6. arrtribute 추가
  - 7. conceptuial class diagram 완성
- Simple Case Study
  - 1. Business concept 결정
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/58136788-0410-4f93-b934-d7b013dc3832)
      - 요구에서 중점적으로 나오는 핵심 용어나 개념을 파악
      - 구현적인 세부사항은 business concept가 될 수 없음
      - Use-case 명세서에서 자주 등장 하는 용어일 수록 concept가 될 가능성이 있음
        - 명사 또는 명사구의 형태로 간결히 추출
  - 2. Concept에 대응되는 class 정하기
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/2c5f5ddf-e5bc-4613-8ffa-c13e0b125fb8)
  - 3 & 4. object 간의 관계 파악 및 이름 부여
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/b154442c-3576-4b40-98a8-880919d814a4)
    - type name `-` verb phrease `-` type name
  - 5. role/multiplicity를 추가
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/d7e2815a-dd38-443d-af27-91f529aae474)
  - 6. attribute를 추가
    - 간단한 속성 또는 pure data value만 기재
    - 만약 복잡한 속성이 존재한다면, Concept로 따로 분류하고 연관 시켜줄 것
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/343cbb7c-b47e-4184-afa2-40f7482300df)
  - 7. conceptual class diagram 완성
    - 이 단계에서 operation은 표현하지 않음
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/aa07c46b-c2ee-4bc6-9ad8-f6d89eeb4047)
- Notes on Domain Model
  - Domain model에서는 concept를 표현하는 것으로 설꼐 단계에서 software objects와는 *구별*되어야 함
  - software objects
    - software class, artifacts related to system implementation, Methods
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/6a46d421-7c24-418d-af2c-b63c2478d91d)
  - Domain model은 하나의 정답으로 존재하는 것이 아닌, 여러 개의 모델과 표현을 가질 수 있음
    - 누가 어떻게 분석했는지에 따라 달라질 수 밖에 없음
    - Domain modle의 핵심적인 목적은 고객 & 개발진에서의 **문제 이해**와 **의사 소통**을 위함

### Conceptual Class Diagram
- Class Diagram
  - 시스템을 구성하는 클래스와 그들 사이의 관계를 표현하는 UML diagram
    - 분석 단계에서는 domain model을 표현할 때
    - 설계 단계에서는 class diagram을 표현할 때 사용
      - 각 class에 detail 정보가 추가적으로 표현
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/e50ae4bb-4eff-479b-8a27-7fd713de417f)
- Conceptual Class Diagram
  - Domain model을 표현하는 diagram
  - Domain objects와 그들의 관계를 표현
  - Domain objects, Attributes, Associations, Roles, Multiplicity
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/eb371546-8a4a-4e55-955f-cdb261ccc43e)
- Association & Role
  - 연관 관계
    - 두 개념(클래스)가 연관되어 있을 때 선을 그어 표시
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a6edeb60-3886-4fbb-a602-3f483ab3bba2)
  - 역할(role)
    - 연관 관계를 가지면, 각 클래스의 객체는 해당 관계에서 *특정 역할*을 수행
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/24211bf1-3867-4797-b05b-163944569e0b)
- Direction of Association
  - 양방향 연관 관계
    - 두 클래스의 객체들이 서로의 존재를 인식
    - 연결한 선에 화살표 없이 표현
  - 단방향 연관 관계
    - 한쪽으로만 방향성이 있어서 한쪽만 상대방의 존재를 인식하는 경우
    - 연결한 선에 화살표로 표현
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/52d1b840-185b-4a4b-8be2-9f14e15a18af)
- Multiplicity
  - 연관된 객체의 수(다중성)을 표현
    - 연결 된 선 위에 표시하면, 단순 1인 경우는 생략하기도 함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c1da9393-2aab-413e-b955-fb60195a25d7)
  - 다중성 표시의 예시
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/08539922-99a8-4695-b3e3-14915ed96df6)

## System Sequence Diagram & Contracts
### System Sequence Diagram
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b66aad07-c261-4e4d-9f80-e88f33baeccb)
- 시스템 시퀀스 다이어그램(SSD)
  - 하나의 사용 사례(use-case)의 특정 시나리오에 대해 외부 actor가 생성하는 이벤트와 시스템과의 상호작용을 순서대로 시각화한 것
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ea959bea-d162-4cc6-9a12-85d0b278f515)
    - SSD의 필요성
      - 어떤 이벤트가 시스템에서 발생하는 파악/예측하기 위함
      - 시스템의 동작을 블랙 박스로 생각
        - Input/Output 판단
      - 외부의 actor가 시스템에 요청 했을 때, 시스템의 동작을 표현
- SSD는 사용 사례(use-case)로 부터 유도됨
  - 한 actor는 시스템을 향해 시스템 이벤트들을 생성
  - 일반적으로 SSD에서는 해당 사용 사례에서 main success 시나리오를 기반으로 작성
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a7520742-391f-4193-a07d-cea45637bf10)
- SSD vs Sequence Diagram
  - 시스템 시퀀스 다이어그램
    - 외부 actor와 시스템 간의 상호작용을 시간적 순서대로 표현
    - **분석 단계**에서 수행, 시스템의 **외부** 동작에 집중
  - 시퀀스 다이어그램
    - 특정 operation을 수행하기 위해 actor 및 내부 객체간 메시지 흐름을 나타냄
    - **설계 단계**에서 수행, 시스템의 **내부** 동작을 설명하고 모델링
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/636bcc82-b84d-4139-b4a1-d73c4b26c5f4)
- 시스템 시퀀스 다이어그램의 구성 요소
  - 시스템(black box로 간주)
    - conceptual class name System
  - 시스템과 직접적으로 상호작용하는 외부 actors(stick figure)
  - 시스템 이벤트
    - 시스템과 actors 간 상호작용(순차적으로 표현)
- Notations in SSD
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c92bd107-c4ad-4100-864d-095c4d89e44a)
- System operations의 의미
  - system이 public interface를 통해 제공하는 operations
    - System은 black box로 간주, 외부 actor에 의해 생성되는 event에 의해 호출됨
    - Operations는 system에 할당
    - system operations의 전체 집합을 *system interface*라 칭함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/df1b8271-cce6-447a-8498-75a59dae2643)
- Example of SSD
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/41313146-36a4-4c89-8fb4-f2756759efe9)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/193bef3e-85c3-4fec-b95a-e15c489aa772)

### Operation Contracts(작업 규약)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/808475a2-22f4-4972-bd5c-dfa1cfa58c21)
- Operation Contract(작업 규약)
  - System operation의 *동작 및 제약 조건*을 기술
  - Use-case보다 더 세밀한 설명
  - 설계 단계에서 객체의 method를 설계할 때 operation contract가 활용됨
  - 특정 system operation이 수행되고, domain 모델에서 객체의 세부 변화를 기술하는 데 사용됨
    - Pre-condition: opertaion이 실행되기 전에 필요한 조건/제약사항
    - Post-condition: operation이 완료된 후 기대되는 결과와 시스템의 상태 변경
  - Format of Operation Contract
    - System operation 별로 작성
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/279d02bc-38df-4a16-b036-ee90c3b67c61)
- Example
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/38a6bc30-86d4-4833-8f1c-b361020537f9)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/7168b8ce-8153-44db-a7ca-2302c9bf5727)
  - Notes on Postconditions
    - Postcondition은 domain model에서 객체의 상태 변화를 기술
      - db에 반영되는 결과
      - 해당 system operation이 수행하는 *도중*에 발생하는 행위를 뜨샇는 것이 아님
      - Operation이 종료된 후, domain model에서 **객체 도는 관계의 변화**를 관찰해서 기술
    - 일반적으로 postcondition은 과거형으로 기술
      - A SalesLineItem was created
      - Create a SalesLineItem(X)
  - Operation contracts 작성을 통한 update
    - Contracts를 명세화 하는 도중에 domain model에서 새로운 개념, 속성, 연관관계가 드러날 수 있음
    - 이를 통해 domain model의 표현을 강화할 수 있음
    - Operation이 명확하지 않거나 use-case의 설명이 너무 추상적인 경우
      - contract 작성을 통해 명확화 할 수 있음
  - Common Mistakes
    - 개념간 association의 forming/broken을 많이 간과함
    - ex) 새로운 instance 생성, 기존 instance 삭제
- Example
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/087ae67e-f152-40d1-8ad7-da20b881b350)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f7b6ad04-3971-48d0-8ecd-cb6896b96349)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/24562ccd-a6fa-4ec6-ab60-5d5695568bef)
- Analysis flow
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ee975678-d114-4274-aca9-3d04306fea0c)

## Case Study for OOA
![image](https://github.com/googoo9918/TIL/assets/102513932/373bc2eb-78e0-4b86-8172-2caf5e2fc985)
![image](https://github.com/googoo9918/TIL/assets/102513932/3ebd02de-0f6b-442d-ad02-0831a2e52bb8)
![image](https://github.com/googoo9918/TIL/assets/102513932/1a4e5660-3dc1-485c-bc49-0d1f92d0d3a5)
![image](https://github.com/googoo9918/TIL/assets/102513932/f7889853-0dd8-4879-8a6a-4cf24de94431)
![image](https://github.com/googoo9918/TIL/assets/102513932/08c2abb7-3b79-41a8-994a-35b5ce835c9d)
![image](https://github.com/googoo9918/TIL/assets/102513932/de91572b-2bce-4990-87a7-a0c66acab5cc)
![image](https://github.com/googoo9918/TIL/assets/102513932/6a122fdb-4933-46bb-9259-4a6e8717649d)
![image](https://github.com/googoo9918/TIL/assets/102513932/c473a6b3-b688-4a81-a94e-51600dd62dac)
![image](https://github.com/googoo9918/TIL/assets/102513932/560a13a3-102d-4ca4-953c-fbc1c22e3701)
![image](https://github.com/googoo9918/TIL/assets/102513932/6f70259b-d208-432a-9e39-8183410ea096)
![image](https://github.com/googoo9918/TIL/assets/102513932/9e76e511-e77d-49dc-ae19-d8fa45056c4f)
![image](https://github.com/googoo9918/TIL/assets/102513932/513e68bf-1c8c-4c09-9b88-5209916b8e38)
  - New loan instance was created
  - New Associations between loan and item , loan and borrower were created
![image](https://github.com/googoo9918/TIL/assets/102513932/80d7a831-51fb-4161-915b-feb8dd6022d6)
  - 1 item available was set to true
  - Old loan instance was deleted
  - Association between loan and item was broken
  - Association between loan and borrow was broken
## Package Diagram
- 객체지향 분석(OOA)에서 객체지향설계(OOD)로의 전환
  - Unified process에서 각 iteration 마다, 요구사항/OOA에서 설계/구현으로의 전환이 일어남
  - 반복적, 점진적 분석 및 설계 수정
  - 분석에서는 "올바른 일"에 중점
  - 설계에서는 "올바르게 동작하도록" 중점
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3e4f967f-4a48-4f62-a1b3-b763375ff65b)

### Logical Architecture
- 논리적 아키텍처
  - 클래스들을 package, subsystem, layer와 같은 큰 단위로 구성
  - 배치의 방법을 고려하지 않고 논리적인 수준에서 구조를 고민
    - 배치를 고려하는 것은 deployment diagram
  - Package diagram으로 논리적 아키텍처 시각화 가능
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/fc671f97-a42f-4852-b1b9-bd33825b9776)

### 계층을 이용한 설계
- 계층
  - 응집된 책임을 가지며 클래스, 패키지 또는 서브시스템을 그룹화
- 기본 아이디어
  - 큰 규모의 시스템을 계층들로 조직화, 각 계층은 연관된 책임을 가짐
  - 높은 계층이 낮은 계층의 서비스를 호출할 수 있도록 계층 구성
- 장점
  - 관심분리, 하위 수준과 상위 수준의 서비스 분리
    - 재사용성 증가
  - 일부 계층만 새로운 구현으로 대체 가능
    - 모듈성 증가

### Typical Layers in OO System
- 사용자 인터페이스 계층
  - 액터와의 입출력 담당
- 응용 로직과 도메인 계층
  - 요구사항을 충족하는 도메인 개념을 나타냄
- 기술적 서비스 계층
  - 객체, DB 연결, 오류 로깅 서비스와 같이 기술적 서비스를 제공하는 객체나 서브시스템
  - 여러 시스템에서 재사용 가능
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d791eb52-e4c2-4132-ac3c-c7b694162cf1)

### Package Diagram
- 패키지 다이어그램
  - 어떤 구성 요소라도 그룹(package)으로 조직화되면 시각화할 수 있음
  - 패키지는 중첩되어 포함될 수 있음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e4218768-97b7-4100-b22e-1eb571d8d69c)
- Notations
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/51ede0fd-f6f0-41bc-8fdd-6c21c2c66c97)
  - Tier = Logical layer
  - Vertical Layers
    - 수직으로 분할
  - Horizontal Partition
    - 한 계층 내에서 병렬적인 서브시스템, 패키지를 수평으로 표현
  - Dependency
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/f996ff12-92f3-4587-90f6-d45ae895b93e)
    - A에서 B에 있는 요소를 사용 시, A는 B를 의존한다 함
    - **점선** 화살표로 표기

### Steps for Package Diagram
1. Package 파악
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/830516ee-3346-4e12-a488-342520993b1e)
    - 동일한 주제 영역, 개념/목적에 밀접하게 관련, 동일한 사용사례 등 함께 묶음
2. Layer에 배치
  - UI, Domain, Service Layer에 맞게 배치
  - 의존관계 또한 추가
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ecc935a6-2bdd-42a3-80ba-a4f2714d5bef)
3. Printer Management System
- OOA 결과물로부터 package화
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/405190cd-6c4b-4d00-8753-d1d63b7b1be7)

- SSD와 System Operations의 연관
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/fa135df4-ebac-447a-abf9-b5214bdecbfa)
  - UI 계층의 객체는 UI 계층에서 도메인 계층으로 요청을 전달 및 위임
  - UI 계층에서 도메인 계층으로 보내는 메시지는 SSD에 나타난 메시지와 동일

### Guideline for Package Diagram
- 도메인 계층의 클래스 이름은 도메인 모델에서 따옴
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/666fee0d-ce66-4b19-b394-5857a6c24fd8)
- 논리적 모델과 물리적 모델을 섞어 그리지 않음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/a301e591-af22-4254-a52b-e3a10abb8484)
- 모델-뷰 분리 원칙
  - 모델은 도메인 계층의 객체를 의미
  - 모델(도메인) 객체는 뷰(UI) 객체에 대한 직접적인 지식을 가져선 안됨
    - Sales/Register 등의 도메인 객체는 뷰 객체가 아니므로, 화면에 표시, 색상 변경, 창 닫기 등을 도메인 객체가 요청할 수 없음
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/00e634e9-6654-4dc9-b919-b4032f1305c2)
  - 이유
    - 유지보수
      - UI는 자주 변하나 도메인/데이터는 상대적으로 덜 변함
    - 관심의 분리
      - UI 개발은 사용자 관점에서 편의성을 최대화 하는데 초점
      - 모델 개발은 데이터 처리에 초첨
    - 싱글 모델-멀티 뷰
      - 하나의 데이터를 여러 모습으로 사용자에게 보여주는 상황이 많음
### Designing Objects
- Object model의 종류
  - Static model(정적 모델)
    - 패키지, 클래스 이름, 속성, 오퍼레이션 등의 정의를 설계하는데 도움
  - Dynamic model(동적 모델)
    - 로직, 코드, 메서드 등을 설계하는데 도움
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3b76b1cd-ad35-4a5f-bf89-6ddc81ceb25e)
- Static and dynamic object modeling
  - 가장 흔하게 사용되는 정적 객체 모델링 방법은 class diagram
    - dynamic modeling도 정적 모델만큼 중요함
    - 동적 객체 모델링을 통해 상호작용에 대한 세부 정보 파악
  - Static UML tools
    - Class diagram, Package diagram, Deployment diagrma
  - Dynamic UML tools
    - sequence diagram, State diagram, Activity diagram

## Interaction Diagram
### Dynamic Object Modeling
- ![image](https://github.com/googoo9918/TIL/assets/102513932/43ed927c-ff5b-420d-8205-8ff1a4443364)
  - 동적 모델링
    - 시스템 기능을 만족하기 위해 각 블록이 어떻게 상호작용하는지 나타냄
    - 클래스의 인스턴스로 생성된 객체가 특정 시간에 다른 객체에 어떻게 메시지를 보내 상호작용하는지 나타냄
    - 객체, 시간, 메시지 호출 순서, 논리적 흐름 등이 표현됨
  - Interaction Diagram의 종류
    - Sequenc diagram, Communication diagram
- Seqeuence diagram
  - 시간 순으로 객체 간 메시지 교환 및 상호작용을 나타냄
- Communication diagram
  - 상호작용 객체 간 연관을 표현, 주고 받는 메시지로 구성
    - 메시지에 번호를 붙여 순서 표시
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9e38dc31-7050-4428-ba1d-6eb48510b9d1)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d4083c6d-cbce-4577-b2ae-abfe2101ad34)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b7e42f5e-ff84-4e18-9eb1-884caad0ecf2)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1ddb0e3f-f0d7-4194-8e64-5ffb1c20b079)
  - makePayment()라는 메시지를 Register 객체에 전송
  - Register 객체는 makePayment() 메시지를 Sale 객체로 전달
  - Sale 객체는 Payment 객체를 생성
  - 서로 동일한 정보를 담고 있으나, 표현법이 다름(상호 변환 쉬움)
    - Sequence: fence style
    - Communication: network style
- Sequence Vs Communication
  - Sequence
    - 시간 축에 초점을 두고 사건 흐름을 나타냄
  - Communication
    - 객체 관계에 초점을 두고 흐름을 나타냄
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c6633247-33dd-48de-b2f0-aae437e1fc83)
### Sequence Diagram
- 시퀀스 다이어그램 기본 요소
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1289ce44-4bdd-450d-a4d1-330c2baa3be5)
  - 객체: box로 표기
  - 메시지: 보내진 방향으로 화살표 표기
    - Found message: 시퀀스 다이어그램에서 메시지 전송 시작점
      - Actor 존재 시, actor가 시작점
  - 라이프라인: 객체가 시스템에 존재하는 기간
    - box에 lifeline이 붙으면 lifeline box라 하는데, 필요에 따라 세분화 가능
  - Activation box
    - 객체가 활성화 되어서 실행되는 기간
  - Type of messages
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/543f6a66-5fdf-4eb0-af1b-e8b43e8ec73f)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/1da205d4-4a79-4974-ac26-0ef426cead44)
    - Synchronous message
      - Sender가 response message를 받을 때까지 기다려야 함
    - Asynchronous message
      - Sender가 응답을 기다리지 않고 진행할 수 있음
    - Response message
      - 요청 메시지에 응답에 해당하는 메시지
      - 명확한 응답의 경우 생략 가능
    - Message Syntax
      - `return = message(parameter:parameterType): returnType`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ffb504aa-8672-42ca-b4f4-013cab96faba)
    - Found message
      - 보낸 사람이 누군지 모르는 경우 보통 시작점
    - Lost message
      - 받는 사람이 누군지 모르는 경우
    - self message
      - 자기 자신에게 보내는 메시지
    - Time-consuming message
      - Message with duration: 보내서 받는데 걸리는 시간
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/6d328e2f-8a57-4fc8-b975-c84e420d72f0)
    - 생성의 경우 관용적으로 점선에 <<created>> 메시지 표시
      - Target instance의 lifeline box를 향함
    - 소멸의 경우 <<destroy>> 메시지와 X로 표시
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/dd6a77a7-d41b-4920-bd4b-580fcdd90b11)
    - 조건: 단일 메시지에 대해 조건을 명시하는 표기법
      - [condition] message와 같이 표기
        - condition이 만족되는 상황에만 message 전송
    - 반복: 단일 메시지를 여러 번 보내는 경우 표기
      - [for loop condition] message와 같이 표기
- Example
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b8a94254-f959-4131-baac-bb12fdd198af)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1c462ceb-c910-45cd-9afd-12e5919441db)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c4a2f0ae-4399-46ea-8076-ef312f362c18)

### Communication Diagram
- 커뮤니케이션 다이어그램 구성 요소
  - 연관된 객체는 화살표 없는 직선으로 연결
  - Message 표기
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/8fc70ceb-0963-4fc0-b5e6-aa6ec00aa279)
    - 메시지는 선 위에 화살표와 같이 표기
    - 메시지 순서를 숫자로 앞에 같이 표기
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e973bd70-3cad-448f-afa5-5a053e181ee6)


## Interaction Diagram(2)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/a82bce57-5327-4703-a202-c45ab1184100)

### Combined Fragments
- In Sequence Diagram
  - 시퀀스 다이어그램의 요소를 그룹화 하고 조건에 따른 실행 흐름을 시각화하기 위해 사용
  - Frame으로 범위를 명시, Interaction operatior과 operand로 표기
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0f9bb152-164f-4233-a988-0a94e04c08ee)
    - Operatior: 해당 fragment의 의미와 operands를 어떻게 사용할지 결정
    - Operand: 특정 조건이 만족될 때 수행되는 공간
      - 조건이 없다면 항상 실행
    - Guard condition
      - 수행 조건을 명시해야 할 때 사용
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/870dcb37-eaba-4585-91c4-a0c4bc9987d7)
    - alt
      - 조건에 따라 다른 흐름을 표현해야 할 때(if-else if-else)
      - 조건은 []로 표시, [else]는 predefine
    - opt
      - 조건이 성립할 때문 수행(if-then)
      - 하나의 operand만 있음
  - loop fragment
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/71ef053a-c6a4-42a9-a6db-28485aed3ec6)
    - loop(min,max) or (min..max)
    - 최소 min에서 최대 max만큼 반복됨
    - *은 no upper limit을 의미
    - Guard가 false 될 때 loop 종료

### Exception Itneraction
- Usage of break fragment
  - 예외처리와 비슷
  - Guard condition 항시 명시
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b450a410-cbd8-4251-8de0-df54064f7984)
  - Guard가 true인 경우
    - Break안의 operand가 수행
    - 둘러싸고 있는 나머지 operand는 수행하지 않음
    - 다음 higher level fragment에서 sequence 흐름이 이어짐
- EX
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/71532208-0400-49e3-8586-ff2266608408)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/4cd18aca-8990-40a5-8abc-b721ffc70e7f)

### Weak Order of Sequences
- Usage of seq fragment
  - Operands간 weak sequence를 나타낼 때 사용
  - Weak sequnce
    - 다른 lifeline에서의 메시지들의 순서는 자유로우나, 하나의 lifeline에서의 메시지들의 경우 순서는 시간 축을 따름 
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/ea14d9f1-3def-4790-8e21-16256b74ea08)
  - 따로 표시가 안되어 있으면 모두 seq임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/337ad508-8cf1-427c-948d-10e0f443f3e7)
    - google 검색과 bing/yahoo 검색 순서는 상관 없음
    - 다만, bing을 먼저 검색하고 yahoo를 검색해야함
- Usage of strict fragement
  - 서로 다른 operand간 순서를 엄격히 제약하고자 할 때 사용
    - 기본값은 seq임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/a23b3d4f-8572-459a-8941-948ae98710b5)
- Usage of par fragment
  - 서로 다른 operand의 동시 수행을 표현할 때 사용
    - operand 내의 순서는 유지되나, 서로 다른 operands 내의 순서는 상관 없음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/4113b22b-e2ec-4b2a-bef1-a1fd6d8984ec)

### Atomic Interaction
- Usage of critical fragment
  - Critical region(atomic area)을 나타낸 것으로, critical region 내에 있는 메시지들 사이에 region 밖의 다른 메시지들이 끼어들 수 없음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/87d287ba-a46b-486c-8629-34d54e31227f)
    - 위 두 operand들은 병렬적으로 수행되나, c->d 사이에 다른 메시지가 올 수 없음

### Interaction Reference
- Usage of ref fragment
  - ref fragment를 통해 다른 sequence diagram(sd)를 가리키게 할 수 있음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/fa2ffc10-c2bf-46c4-9b9b-ecb9c524cdf7)
- 같은 메시지를 한 collection 내 개별 요소에게 iterate 하여 보내야 할 때
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/080d9074-fcfa-410c-bd6c-24af08fa632f)
- Time Constraints
  - 메시지 전송, 수신 시간, duration 등의 제약 표기 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2e2d021f-6f87-4ea4-a591-43e74e14c106)

## Class Diagram
- ![image](https://github.com/googoo9918/TIL/assets/102513932/0f6dc1c0-a3ce-4482-8e2c-5488f67b313f)
  - 시스템을 구성하는 클래스와 클래스 간 관계를 도식화
  - Seqeunce diagram을 그리다 보면 클래스와 메소드들이 등장
    - Dynamic, Static view를 동시에, 반복적으로 그림
- Domain model과 design class diagram의 차이
  - Domain model
    - 개념적인(conceptual) 관점에서 시스템을 이루는 도메인 객체 관계 표현
  - Design class diagram
    - software 관점에서 시스템 내부의 클래스와 관계를 세부적으로 표현
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/bf72b1d8-2d6e-44e1-9f86-cfaf29dd726a)

### Notations in Class Diagram
- 클래스
  - Attribute와 operations의 집합체
  - 클래스의 instance를 object라 지칭
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9e141c3d-358f-465f-82fb-fcab9a8a16d2)
    - Attribute는 객체마다 다름
    - Operation은 모든 객체에서 동일
- 가시성(visibility)
  - 클래스 안의 속성을 누가 접근할 수 있는지 명시
  - 접근 지정자 표기
  - +: public
  - -: private
    - 해당 클래스 내에서만 접근
  - #: protected
    - 클래스 자기 자신과 상속 받는 서브 클래스에서만 접근 가능
- derived attribute
  - 다른 정보로부터 해당 attribtue의 값이 계산되는 attribute
    - /name과 같이 /(slash)를 이용하여 표기
    - 혹은, {readOnly}를 붙이기도 함
      - 다른 attributes로부터 값이 결정되기 때문에 직접적인 수정을 허용하지 않음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/6a894abb-9464-472e-b437-a923999ec4e2)
- name and type
  - Name과 Data type을 표기
- multiplicity
  - 한 attribute가 갖는 값의 개수
  - [min..max], No upper limit --> [*]
- default value
  - Attirbute가 갖는 기본 값
- properties
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/5be2cd47-2519-4c67-8ff0-08327aa8ef62)
- Operation syntax
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/4a998b1f-3170-4c96-bee2-d9951d2116a1)
  - in: input parameter
  - out: output parameter
  - inout: combined input/output parameter
- Class variable and class operation
  - static이 붙는 attribute, operation
  - underline으로 표시함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/559c54ae-5086-4905-8825-50a9d5302218)
- Getter and setter
  - 모든 속성에 대해 getter/setter를 다 표시 하는 것은 장황해질 수 있음, 종종 생략
- Abstract operation and abstract class
  - Italic(기울어진) 글씨체로 표현
  - {abstract} property로 붙여 표현
  - class의 경우 <<abstract>>로 표현하기도 함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2f416c1d-b6cc-4d79-bab4-edae9df0f17d)

### Types of Class Relationship
- ![image](https://github.com/googoo9918/TIL/assets/102513932/101b2c2a-035a-4f57-a52c-b3a300b7a7c7)
  - 의존 관계
    - 한 클래스의 객체에서 다른 클래스의 객체를 사용하는 경우
      - 메소드의 파라미터, 반환 값으로 사용 or 지역 변수로 사용 시
  - Association
    - 한 클래스의 객체가 다른 클래스의 객체와 "장기간" 연관되어 동작할 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c42413fe-d3f4-4366-9195-30b338ce4ecf)
  - Navigability
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/bfb0fb14-9ad2-4ea3-a6cb-efb43e683254)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/869e79f0-5434-45c7-abd7-f7f47b59ddc8)
  - Binary Association as Attribute
    - Association 관계에서 attribute를 표현하는 방법
      - attribute text: type으로 association을 표현
      - association line
      - Both together
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/b4537e7c-eff0-4734-b6da-9f2eef3abff8)
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/baa173fe-ab29-4527-b037-e54c2922689a)
        - 보통 Data에 해당하는 attribute는 text로, 두 클래스간 연관 관계에 있는 attribute는 association line으로 표기

## Class Diagram(2)
### Association class
- 연관 클래스
  - 연관 관계에 속성이나 행위를 추가할 필요가 있을 때, 연관 관계를 클래스로 표현
    - 다대다 관계에서 사용
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/fc3444bf-d3ee-4c24-b975-cfedda680fbd)
      - 한 학생은 다수의 강좌 등록
      - 한 강좌는 다수의 학생 등록
      - Enrollment는 특정 학생의 특정 과목에 대한 성적 표현
        - 연관 관계를 클래스로 표현, 이를 *연관 클래스*라 지칭
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c1fdd0b6-f5de-4178-a9c1-72882848b6d3)
      - 연관 클래스를 풀어서 연관 관계로만 표현 가능
      - 일대다, 다대일 관계로 풀어서 표현 가능
      - 한 학생은 여러 과목 등록, 한 과목은 다수의 학생에 읳 ㅐ등록
      - 이때 Enrollment객체는 Student 객체, Course 객체, grade를 속성으로 가짐

### Whole-part Relationship
- 전체/부분 관계
  - 전체 개념에 해당하는 클래스(whole) 이릉 이루는 부품에 해당하는 클래스(part) 간 관계
    - 자동차/바퀴, 건물/방
    - 부분 개념이 모여서 전체 개념을 이룰 때
- Aggregation Relationship
  - 집합 관계
  - 부분이 전체에 **약하게** 속하는 관계 표현
  - 부분은 전체에 관계없이 독립적으로 존재 가능
  - 전체라는 객체가 소멸되어도, 부분에 해당하는 객체는 사라지지 않음
    - 전체와 부분이 생명 주기를 달리함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e17dc4fb-5a85-4641-909b-4189a0092cd0)
  - Car/Wheel, User/Address
    - wheel은 car와 상관 없이 독립적으로 존재 가능, 다른 이동수단에도 사용 가능
    - address는 user라는 개념에 상관 없이 독립적으로 존재 가능, 다른 곳에서도 사용 가능
  - 주의점
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/f62093d6-b36f-4203-b55a-fcc1495c42cd)
    - 집합 관계는 연관 관계에 포함되기 때문에 집합 관계를 association으로 표기 가능함
    - 단, 단순 연관 관계에 집합 관계를 적용할 수는 없음
    - 양방향 hollow diamond는 불가능함
    - 마찬가지로 2개의 링크를 통한 양방향 불가능

### Composition Relationship
- 합성 관계
  - 부분이 전체에 **강하게** 연관되는 관계를 표현
    - 전체와 부분이 생명 주기를 같이함
    - Building/Room, Book/Chapter
      - 건물이 없으면 방만으로는 의미 없음, 책이 없이 챕터만으로는 의미가 없음
- 표기법
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/55ffe0a7-1967-47f8-8715-cbdfcb09b4b8)
  - Filled diamond로 표기

### Aggregation VS Composition
- 전체와 부분의 생명 주기
  - 집합
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/04bca550-fbc7-4aa3-a0c6-6ff123ca0298)
    - 전체 객체가 소멸되더라도 부분 객체는 사라지지 않음
  - 합성
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/7da24b28-ba72-49d1-ab96-f7b92ea2abd2)
    - 전체 객체가 소멸되면 부분 객체도 사라짐
- ![image](https://github.com/googoo9918/TIL/assets/102513932/6f62daa7-642a-45c5-b58c-82d0b8c02ad4)

### Inheritance Relationship
- 상속 관계(일반화)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d9ee39cd-44eb-4782-b262-94c66f4061d9)
  - 공통된 특성을 슈퍼 클래스로 일반화
  - 서브 클래스의 인스턴스는 동시에 슈퍼 클래스의 간접적인 인스턴스
  - 서브 클래스는 private을 제외하고 슈퍼 클래스의 모든 것을 상속
  - 서브 클래스는 속성 및 관계를 가질 수 있음
- 일반화(추상 클래스)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1a9103c1-3298-4544-ae13-f91de216c041)
  - 추상 클래스는 서브 클래스의 공통된 특성을 강조
    - 슈퍼 클래스가 직접적으로 인스턴스화 되지 않음
    - 추상 클래스를 상속받는 서브 클래스만 인스턴스화 될 수 있음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/af8ab434-c182-4a8d-a096-aac9978c8135)

## State&Activity Diagram
- ![image](https://github.com/googoo9918/TIL/assets/102513932/4b76d830-52be-4a23-9e3b-248682a59b45)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3faa9691-57f4-4d87-a87f-9e8fc61773d3)

### State Machine Diagram
- 상태(state)
  - 시스템이나 객체는 보통 특정 상태(state)에 있음
    - 특정 이벤트 발생 시, 상태 변화
- 상태 다이어그램
  - 시스템이나 객체의 상태가 어떤 오퍼레이션에 의해 동적으로 변하는지 표현
- 상태 머신 다이어그램의 기본 구성 요소
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/49ee62c8-0f17-443d-8dce-87984e1e0c9e)
  - 상태(state)
    - 시스템이나 객체의 특정 상태
    - text로 상태 표현
  - 전이(transaction)
    - 상태의 변경을 표현
    - 화살표와 이벤트로 표기
- 상태 머신 다이어그램의 기본 구성 요소
  - 초기 상태
    - filled black circle
  - 최종 상태
    - circled dot
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f29aba6a-7edc-45e9-ac44-a2bdc7d87230)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ac96bfb5-a89b-4e5d-be4c-03821eea90ef)
- 상태
  - 특정 시점에서의 컨디션으로 passive/active 구분
    - passive
      - 전구 -> On and Off
    - active
      - 커피머신 -> Brewing
  - Doing state의 경우 상태 내부에 행위 기술 가능
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/6924eac2-5e15-431c-9c88-59aff595fde2)
    - do/behavior의 경우 행위 마무리 시 상태 변경이 있을 수도, 없을 수도 있음
- 전이
  - 이전 상태에서 다음 상태로 변경될 때 유발하는 조건이나 이벤트를 간선에 표기
  - Notation
    - Trigger[Guard]/ Action
      - Trigger: 전이를 발생시키는 event
      - Guard: boolean 조건
      - Action: 전이 이후 수행되는 행위
      - 각 요소는 경우에 따라 생략 가능함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/5bb72a8d-6e8d-47bb-aa32-fdec5ec496b8)
      - door
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/1a2c96c5-cfef-418c-99f0-df220f2c4382)
      - CD player
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/dcb01c2c-c1c9-4c8b-9d68-1cc1084ef652)
      - troll
- Internal behavior of state
  - state 내부에서 발생할 수 있는 행위를 표현
    - do/behavior
    - entry/behavior
      - 해당 state가 active될 때 수행
    - exit/behavior
      - 해당 state가 inactive될 때 수행
- Internal transaction of state
  - State내에서 reaction을 유발하는 전이
    - 다른 상태로 이동은 X
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e80356f0-d7e9-45a5-bd41-fa3b674adfc7)
- Composite States
  - 동시에 여러 stae를 가질 때 표기
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/640c3840-1a8b-4b0b-bb4e-bb74f06cddc1)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/7ceb3912-9cd8-45af-8afc-a86949e24a87)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/bb3d5f82-ef24-4620-8802-0e5b2cb43677)

### Activity Diagram
- 활동 다이어그램
  - 시스템 내에서 수행되는 작업이나 동작의 흐름을 시각적으로 표현
    - flow chart의 advance version
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e59b77d7-9e93-45f6-b30f-24a5a0165032)
  - ATM 사용 사례의 사용 및 활동 흐름
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/ce4361ed-2e51-479b-a40b-2e73ba0bbce2)
  - 상품 주문에 해당하는 활동 흐름
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/b8fb311d-c747-4abf-9ef8-95d9d07b5b3e)
  - 상품 주문에 해당하는 활동 흐름
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/4f5191e1-242b-4406-b593-06f52ec820c4)
- 스윕 레인
  - 활동 다이어그램은 여러 클래스와 연관 가능
  - 클래스 별로 나눠서 활동 흐름 표시
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/90851937-c3d0-442d-92a3-81101b909379)
- 신호
  - 특정 조건 하에서 시작되는 활동
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1e83df8f-7397-4919-b6d1-5fcdb8863d63)
- Loop 표현
  - plain notation
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c024de88-12e3-4af0-bd7f-a4bf21fc9750)
  - Loop Node
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/0ceba962-c01a-4521-9785-a97ed8c758c9)
  - Extension region
    - Collection의 개별 entry에 대해 반복적으로 수행
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/64f4d889-8af5-4ae4-9f41-7ecb5ca5a0d6)

## Case Study for OOD
- Library Management System
  - Actor-based use-cases
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/cb6f30e1-29bd-41dd-8e83-6a5710c40227)
  - Use-case Diagram
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/2fa4d9f8-54e1-4c5b-953d-09fbc6a88191)
  - Domain Model
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/d25761fc-b0b1-44f9-b880-ec981a49ba4e)
  - System Sequence Diagram(SSD)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/5df11e8e-4213-4896-9f29-47966435c16e)
### State Machine Diagrams
- Title과 Item 객체 상태 변화
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c5030668-c398-40a8-b0e7-c1a8368b0ce1)

### Activity Diagram
- Librarian's activities
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/99130008-8fb5-43b6-a09b-f3e6d87a8318)

### Logical Architecture
- ![image](https://github.com/googoo9918/TIL/assets/102513932/69aa9945-5309-4989-bc61-9f9cfe9073bb)

### Sequence Diagrams
- Login
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e69194c8-311f-463c-b09d-6820fb80f463)
- Add Borrower
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/90cc16b1-f8d0-405f-80cc-f15835e8cfc5)
- Add Item
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9dff4776-687e-42a1-9075-7f4cf52ed61a)

### Class Diagram
- Domain Model
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/746b758d-406b-44a3-b722-6f5b8dece31c)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/92cb697b-ef22-4c04-a606-02ea900aa4c3)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/089f68b5-8c65-4ae8-9b9d-a504017a4e0c)

## SOLID
### SRP
- SRP(Single Responsibility Principle)
  - 단일 책임 원칙
  - 모든 클래스는 단 하나의 책임만을 가져야 함
    - 지켜지지 않으면 변경에 취약
    - 여러 책임이 있으면 변경이 일어 났을 때 수정해야하는 코드가 많아짐
  - 책임 분리
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/125e5f00-7ee7-4f5f-8d4c-a5e8b5d0b6ae)

### OCP
- OCP(Open-closed Principle)
  - 개방-폐쇄 원칙
  - 확장에는 열려있어야 하며, 수정에는 닫혀있어야 함
    - 변경 사항 발생 시, 확장은 가능해야하나 수정은 없어야 함
  - 변해야 하는 것은 쉽게 변할 수 있게 하고, 변하지 않아야 할 것은 변화는 것에 영향을 받아선 안됨
  - 일반적으로 추상 클래스와 상속을 통해 OCP를 따름
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/bf0c3621-504b-4c80-8f2e-f1b17b8a180d)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/b2ea1230-ed31-4574-9d55-841ff18a2464)

### LSP
- LSP(Liskov Substitution Principle)
  - 리스코프 치환 원칙
  - 상위 타입의 객체를 하위 타입의 객체로 치환 해도, 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 함
    - 즉, 자식 클래스가 부모 클래스의 행동 규약을 지켜야함
  - 지켜지지 않는경우
    - 오버라이딩을 잘못 한 경우(매개변수 개수나 타입 다름)
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/b8b76f74-ab54-4055-b109-5d9cd072d158)
    - 부모의 의도와 다른 오버라이딩
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/62879500-a701-4937-a80a-32bfd9882c5a)
    - 잘못된 상속 관계 구성
      - ![image](https://github.com/googoo9918/TIL/assets/102513932/eff5a313-71d9-4a03-b608-2b523b7452be)

### ISP
- ISP(Interface Segregation Principle)
  - 인터페이스 분리 원칙
  - 클라이언트가 사용하지 않는 메서드에 의존하지 않아야함
    - 인터페이스에 클라이언트가 필요한 메서드만 포함될 수 있도록 분리
    - 인터페이스를 각각 사용에 맞게 분리
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/54081f44-a38c-465c-8f7a-b9107a77f27e)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/5aa8ce69-5da5-4c73-b0a7-a9d9e7fff511)
- SRP VS ISP
  - SRP는 클래스의 단일 책임을 강조, ISP는 인터페이스의 단일 책임을 강조
  - ISP는 클라이언트의 기준으로 인터페이스가 분리되어야 함
    - 만약, 비대해진 클래스를 SRP에 따라 여러 클래스로 나누고 각자의 인터페이스를 제공한다면 ISP를 따를 수도 있으나, 반드시 그런 것은 아님
      - 게시판의 여러 기능의 메서드를 제공하는 클래스가 있다고 가정
      - 클라이언트에 따라 일부 기능만 사용 가능
      - 일반 사용자는 게시판 삭제 불가, 관리자는 삭제 가능
      - 게시판 클래스는 게시판에 관련된 책임을 지므로 SRP를 만족함
      - 이 클래스의 모든 메서드가 들어있는 인터페이스가 클라이언트에 상관 없이 사용되므로 ISP에는 위반

### DIP
- DIP(Dependency Inversion Principle)
  - 의존 관계는 변화하기 어렵거나 변화가 거의 없는 것과 맺어야 함
  - 구체 클래스는 변화하기 쉽고, 추상클래스와 인터페이스는 변화하기 어려움
  - DIP를 만족하려면 구체적인 클래스보다 추상클래스 또는 인터페이스와 의존 관계를 맺도록 설계해야함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/32f99393-4906-47d1-a89c-5058bb27dc97)
- 기존에는 고수준의 클래스가 저수준의 클래스에 직접적으로 의존하였으나
  - 고수준 클래스와 저수준 클래스 둘 다 추상화(추상 클래스 또는 인터페이스)에 의존해야함

- SRP와 ISP는 객체가 커지지 않도록 돕고
- LSP와 DIP는 OCP를 지원
  - OCP는 추상화와 다형성을 통해 기능 확장을 하면서도 기존 코드를 수정하지 않도록 함
  - DIP는 변화 되는 부분의 추상화를 지원
  - LSP는 다형성을 도움