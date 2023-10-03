# 데이터베이스 응용
## 트랜잭션 이론
### 트랜잭션 개념
- 트랜잭션은 하나의 논리적 작업을 수행하는 데이터베이스 연산의 순서임
  - 즉, 연산의 논리적 단위이며 데이터를 접근하고 갱신하는 DB 프로그램의 논리적 수행 단위임
  - ex) start transaction ~ commit
- 트랜잭션 관리
  - 다양한 시스템 장애를 극복하는 **회복 기능**
  - 다수개의 트랜잭션을 동시에 수행하였을 대 발생하는 문제점을 해결하는 **동시성 제어 기능**
- ACID
  - 원자성(atomicity)
    - all-or-nothing
    - 연산 모두가 수행되거나 어느 연산도 수행되지 않아야 함
  - 일치성(consistency)
    - 단일 트랜잭션의 수행은 데이터 무결성을 유지함
    - 트랜잭션 시작 시 무결성 제약 만족 -> 종료 시에도 무결성제약을 만족 시켜야함
    - 다만, 트랜잭션 수행 중간에는 무결성제약을 만족하지 않을 수 있음
  - 고립성(isolation)
    - 다수 개의 트랜잭션이 동시 수행되어도 사용자에게는 본인 트랜잭션만이 홀로 수행되고 있는 느낌을 줌
  - 지속성(durability)
    - 완료된 트랜잭션의 결과는 후에 시스템 장애가 발생하여도 데이터베이스 상태에 반영되어야 함
- ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/679772af-863a-4982-ae2b-ea67b57b027c)
  - 3번 라인을 수행하고 6번 라인 수행 전에 시스템 장애 발생 시 
    - 3번 라인 효과를 DB 시스템에서 제거해야함
    - 원자성에 의해!
  - 3번과 6번을 수행하고 commit까지 진행 후 DB 시스템 장애 발생 시
    - 지속성에 의해, 위 수행 라인은 데이터베이스 상태에 반영되어 있어야 함
- ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/d9961ecf-060c-4671-bc06-828761ce7a4b)
  - 이와 같은 예제는 고립성 요구사항에 위배됨
  - T2가 접근하는 데이터 A,B는 T1의 트랜잭션 중간값이기 때문
    - 실행 중간 값은 무결성 제약을 만족하지 않을 수 있고, 실제로 상기 예제는 무결성 제약을 위배하고 있음
  - 트랜잭션을 직렬실행 하면 고립성은 자연스럽게 제공되나, 현실적으로 불가능
- 트랜잭션 상태
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/c994518a-be96-4d75-bb22-bb4d87721183)
  - 수행(Active) -> 부분 완료(partially committed) -> 조치(ex 로그 기록) -> 완료(Committed)
  - 수행 중인 상태에서 트랜잭션의 모든 문장이 실행되면 부분 완료 상태가 됨
  - 트랜잭션 완료를 위한 조치(ex 로그에 기록) 가 성공적으로 수행되면 트랜잭션은 완료 상태가 됨
  - 만약 여러한 경우로 완료 전 트랜잭션 연산을 수행하지 못하는 상황이 발생한다면, 트랜잭션이 철회(aborted)상태가 됨
    - 이 경우, 트랜잭션 초기에 시작할 상태로 DB를 원상 복귀 시켜야 함
- 동시 실행
  - 시스템은 여러 개의 트랜잭션을 동시에 수행함
    - 자원 활용성 증가 및 평균 응답 시간 감소
    - 마치 운영체제에서 개별 프로세스의 동시성 수행!
  - 다만, 동시성 제어를 해줘야 함

### 직렬가능
- 올바른 실행
  - 다수 개의 트랜잭션을 동시 수행 시, 올바른 트랜잭션 실행의 정확한 정의는 무엇인가?
  - 트랜잭션을 순차적으로 수행하는 *직렬수행* 방법은 항상 올바르다
  - 다만 현실적으로 운영 불가능
- 틀린 실행 현상
  - 오손읽기(dirty read)
    - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/cf6f078e-fd04-410a-9621-54104f097fb9)
    - 완료되지 않은 값(uncommitted value, dirty value)을 읽는 연산을 뜻함
    - T1이 철회를 하면 T2 또한 철회해야함
  - 갱신 손실(lost update)
    - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/ac21de75-60b7-490c-84ef-fb97c912acc9)
    - T2가 쓴 값을 T1이 덮어쓰는 현상
    - T2의 효과는 DB에서 사라지게 됨
  - 반복불가 읽기(unrepetable read)
    - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/15d7582e-b14b-4179-b153-cfe068a16808)
    - T1이 처음에 읽는 A값과 후에 읽은 A 값에 차이가 있음
    - A 값을 읽을 때마다 다른 값이 나오는 현상!
  - 바르지 못한 실행으로 발생할 수 있는 현상은 오직 이 세 가지 뿐임
- 스케줄(역사)
  - 스케줄은 동시적으로 수행되는 다수 트랜잭션에 속하는 연산이 수행된 *시간적 순서*
  - 트랜잭션의 모든 연산이 스케줄에 나와 있어야 함
  - 트랜잭션의 마지막 문장은 commit 또는 asbort임
    - 이는 명시적으로 수행 or 암시적 시스템 수행됨
- 스케줄1
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/efe5aa2f-8e58-4a0c-a129-b2ec9e502ee7)
  - 초기값 A:100, B:200
  - A계좌에서 B계좌로 $50 혹은 10%을 송금함
  - 트랜잭션 수행 후에도 A+B 값의 변화는 없음
- 스케줄2
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/53d38fe6-8241-40fb-80a8-4c6732592866)
  - 마찬가지로 A+B 값에 변화는 없음
- 스케줄3
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/7d8dd289-9ef8-4a01-a5ed-5e3d68321489)
  - 직렬 스케줄은 아니지만, 그 효과가 스케줄 1과 동일함
- 스케줄4
  - ![image](https://github.com/googoo9918/YourssuAssignment/assets/102513932/0b7fc438-fe89-4172-bb90-4e0fa91494a1)
  - 스케줄 4는 올바른 스케줄이 아님
  - 초기값 A=100, B=200
  - T2 write(A)후 상태
    - A=90, B=200
  - T1 write(A)후 상태
    - A=50, B=200
      - 이때 A에서 lost update가 발생했음
  - T1 write(B)후 상태
    - A=50, B=250
  - T2 write(B)후 상태
    - a=50, B=210
  - 트랜잭션 수행 후에 A+B값에 변화가 있음
- 예제 정리
  - 스케줄 4는 올바른 수행이 아님
    - 스케줄 4의 효과가 스케줄 1 또는 스케줄 2의 효과와 모두 다르기 때문
    - 만약 직렬수행 스케줄 중 하나와 효과가 같다면, 올바른 스케줄(직렬가능 스케줄)임
- 직렬 가능
  - 스케줄이 직렬수행 스케줄 결과와 동일하면 이를 **직렬가능(serializable) 스케줄**이라 칭함
  - 스케줄에 관련되는 트랜잭션의 개수가 n개이면 직렬 스캐줄은 n개의 트랜잭션을 나열하는 개수와 동일함
    - 총 n!임
  - 직렬 가능 스케줄은 n!의 직렬 스케줄 중에서 적어도 하나의 직렬 스케줄과 동일한 결과를 가져야 함 
  - 앞서 스케줄 3은 직렬 스케줄 1과 동일한 결과를 보이므로, 직렬가능 스케줄임
  - 이처럼 스케줄 결과의 동일함을 정의하는 방식은 *충돌 직렬가능* 스케줄과 *뷰 직렬가능성* 스케줄이 존재
- 충돌 연산
  - 동일한 데이터에 대한 연산만을 고려
  - 동일한 데이터에 대한 두 개 연산 중에서 최소한 한 개 연산이 쓰기 연산이면, 두 연산은 *충돌*함
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/612b2d6a-f949-4782-b309-a6fc4709c296)
    - 층돌연산은 연관되는 트랜잭션의 직렬실행 순서를 결정
      - 비충돌연산은 두 연산의 순서를 스케줄에서 바꿔도 연산 효과가 동일하나
      - 충돌 연산은 순서를 바꾸면 **효과가 다르게 나오기** 때문
- 충돌 직렬가능 스케줄
  - 비충돌 연산을 서로 바꿔 직렬 스케줄이 되면, *충돌 직렬가능 스케줄*이라 칭함
- 충돌 직렬가능 예제1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0360c778-b5c7-45b5-a4be-05be49fa5f25)
  - 스케줄 5에서 T2의 wirte(A)는 T1의 read(B), write(b)와 비충돌하므로 서로 위치를 바꿀 수 있음
    - 동일하지 않은 데이터에 대한 연산이기 때문
    - read(A)또한 마찬가지
  - 따라서 서로 위치를 바꾸면 결과적으로 스케줄6이 나오게 되며, 스케줄6은 직렬 스케줄임
  - 이 경우, 스케줄5는 *충돌 직렬가능 스케줄*임
- 충돌 직렬가능 예제2
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/66a01f25-68dc-44fe-8e94-a5ab31a17c6a)
  - 상호 교환되지 않으므로 충돌 직렬가능 스케줄이 아님
- 뷰 직렬가능 정의
  - 다음 3가지 조건을 만족해야함
  - 1. 초기 읽기
    - 스케줄에서 변수를 처음 읽는 트랜잭션이 직렬 스케줄에서도 그 변수를 처음 읽어야 함
  - 2. 최종 쓰기
    - 스케줄에서 변수를 마지막으로 쓰는 트랜잭션이 직렬 스케줄에서도 그 변수를 마지막으로 써야 함
  - 3. 값 전달
    - 한 트랜잭션 T1이 다른 트랜잭션 T2에게 변수의 값을 전달하면, 직렬 스케줄에서도 T1이 T2에게 값을 전달해야 함
- 뷰 직렬가능 예제0
  - ![KakaoTalk_20231002_141837606](https://github.com/googoo9918/TIL/assets/102513932/35d024ee-3a33-4966-ae5a-773f1f45999f)
  - 좌측은 뷰 직렬가능, 우측은 충돌 시 값 전달이 직렬스케줄과 맞지 않으므로 뷰 직렬가능 스케줄이 아님
- 뷰 직렬가능 예제1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/53351076-28d0-4226-8a88-9e21184260e4)
    - 스케줄 8은 충돌연산만으로 구성되어 있으므로 충돌 직렬 가능 스케줄은 아님
    - 스케줄 8은 직렬 스케줄 `<T5,T6,T7>`과 효과가 동일한 뷰 직렬가능 스케줄임
    - T5 읽기 연산에서 보는 데이터 Q는 직렬 스케줄 `<T5,T6,T7>`의 T5 읽기 연산이 보는 데이터 값과 동일
    - 스케줄 8의 데이터 Q에 대한 마지막 쓰기 연산은 T7의 쓰기 연산인데, 이는 <T5,T6,T7>의 T7쓰기 연산과 동일함
    - 값 전달은 위 예제에서는 일어나지 않고 있음
- 충돌 직렬가능 대비 뷰 직렬가능
  - 뷰 직렬가능 스케줄 중의 일부가 충돌 직렬가능 스케줄임
  - 모든 충돌 직렬가능 스케줄은 뷰 직렬가능 스케줄임
  - 즉, 직렬가능 스케줄이 뷰 직렬가능 스케줄을 포함하고, 뷰 직렬가능 스케줄이 충돌 직렬가능 스케줄을 포함함
- 다른 직렬가능 스케줄
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/a24f5cee-292d-4b82-b863-e6a1b8e3e361)
  - 위 스케줄은 충돌 직렬가능 스케줄도 아니고, 뷰 직렬가능 스케줄도 아니지만
    - swap할 수 있는 비충돌 연산이 없음
    - 초기 읽기, 초기 쓰기 적용x
  - 직렬 스케줄 `<T1, T2>`과 동일한 결과를 보이므로, 직렬 가능 스케줄임

### 직렬가능 시험
- 충돌 직렬가능 시험
  - 주어진 스케줄이 충돌 직렬가능 스케줄인지를 판별하기 위해서는 *선행 그래프*를 사용하면 됨
  - 여기서 노드는 트랜잭션을 표현함
  - 동일한 데이터에 대해 **충돌되는 연산**이 존재 시
    - 데이터 접근에 대한 선후 관계에 따라 에지(edge)를 생성함
- 선행 그래프 예제1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/499017fb-0f0e-4ef3-8ec8-c09bf33f24d8)
    - 스케줄 10에 대한 선행 그래프를 작성한 예제
    - 선행 그래프에 사이클이 있으므로 스케줄 10은 충돌 직렬가능 스케줄이 아님
- 선행 그래프 예제2
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8ea500f6-d2a3-4f68-891b-77393f7ec39e)
    - 선행 그래프에 대한 관심은 사이클 존재 여부임
      - 중복되는 에지는 그래프 작성 시에 표기하지 않아도 됨
      - 또한, T5에는 write가 없으므로, 작성하지 않아도 됨
    - 스케줄 11에 의한 선행 그래프에는 사이클이 없으므로, 스케줄 11은 충돌 직렬가능 스케줄임
      - 모든 충돌연산에 대해서 에지를 그려야 함에 유의할 것!
- 선행 그래프의 사이클 판별은 저렴함
  - 선행 그래프는 방향성 그래프이고, 방향성 그래프에서 사이클 존재 여부 판별은 쉬움
  - 위상 정렬의 관점에서 보자
    - 위상 정렬이란, 부분 순서를 만족시키면서 전체 노드를 정렬하는 방식
    - 스케줄 11에서 갖고 있는 부분 순서
      - T1->T3->T4
      - T1->T2->T4
    - 따라서, 5!/4!/2!임
      - 1. T5는 아무 상관이 없고, 순서 연관이 있는 4 원소 -> 5!/4!
        - T2와 T3는 변동 가능하므로 *2!
      - 2. 순서가 있는 배열이 2번 존재한다고 생각하면, 5!/4!/2!임
- 뷰 직렬 가능 시험
  - 뷰 직렬가능 시험은 NP-complete임
### 회복 가능
- 회복가능 스케줄(Recoverable Schedules)
  - 직렬 가능 스케줄 문제와는 별개로, 스케줄을 회복 관점에서도 고려해야 함
  - 아무리 직렬 가능 스케줄이어도 그 스케줄이 회복 관점에서 문제가 있으면 사용할 수 없음
- 데이터를 읽은 트랜잭션(T1)은, 그 데이터를 쓴 트랜잭션(T2) 후에 완료(commit)해야 함
  - 만약 T2가 롤백 된다면, T1도 롤백 되어야 하기 때문
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/a7c7c6ed-9e74-46f5-8b4c-e534c27d0430)
    - T9은 현재 완료되지 않은 값(A)를 읽고 있음, 그런데 먼저 커밋을 해버리니 만약 T8이 철회를하게 되면, 읽은 값이 잘못된 값이니 따라서 철회를 해야 하는데 이미 완료를 해버려서 철회를 못하는 상황이 되어버림
    - 완료를 하고도 취소를 해야되니까 Durable 성질을 위배하고, Consistency 또한 위배함
- 연쇄 철회(Cascading Rollbacks)
  - 트랜잭션 하나의 철회가 다른 트랜잭션 철회를 유도함
    - 이를 방지하려면 기본적으로 완료된 읽기만을 허용하여아 함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/dc3add47-00a8-4886-9af6-39512cea0cae)
    - 이 예제에서 T11과 T12의 read는 dirty read임
    - 읽는 값이 committed된 값이 아니므로
    - T10이 철회를 하면 T11 및 T12도 함께 철회를 해야 함
      - 이는 T11과 T12입장에서는 트랜잭션의 분리성(isolation)이 제대로 지원되지 않아 철회를 하는 꼴이 되기 때문임
    - 따라서 DBMS는 원칙적으로 연쇄 철회 현상이 발생하지 않도록 트랜잭션을 스케줄 해야 함
    - 참고로, 상기 스케줄은 회복가능 스케줄임
      - 회복 가능 스케줄은 관련 트랜잭션의 완료 순서에만 제약을 주기 때문
- 연속적인 철회가 필요 없는 스케줄(Cascadeless Schedules, Avoids cascading aborts(ACA))
  - 연속적인 철회가 필요 없는 스케줄 또는 연속 철회를 방지하는 스케줄은 기본적으로 완료된 데이터 읽기만을 허용함
  - 즉, 읽으려고 하는 데이터에 대해 데이터를 마지막으로 쓴 트랜잭션이 완료(commit)하기를 요구하는 스케줄임
- 스케줄 관계
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/7170a61f-fc96-43b9-8860-5fc7ce3c1528)
  - RC(recoverable)
    - 회복가능 스케줄, 데이터를 읽은 트랜잭션이 데이터를 쓴 트랜잭션 후에 완료(commit)하면 됨
  - ACA(avoids cascading aborts)
    - 연쇄 철회를 방지하는 스케줄, 트랜잭션이 커밋되기 전에 다른 트랜잭션들이 해당 트랜잭션에 의해 변경된 데이터를 *읽지* 못하게 함
  - ST(strict)
    - 제한적인 스케줄, 트랜잭션이 데이터를 변경하면 이 트랜잭션이 커밋되기 전까지 다른 어떤 트랜잭션도 그 데이터 항목을 *읽거나 쓸 수* 없음
  - SR(conflict serializable)
    - 충돌 직렬 가능 스케줄, 비충돌 연산을 서로 바꾸어 직렬 스케줄이 되는 스케줄
  - DB 시스템이 지원하는 스케줄은 충돌 직렬가능 스케줄과 ACA 스케줄(혹은 회복가능 스케줄)의 교집합 부분
- ST가 아닌 ACA 스케줄
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/05b2615f-d4a0-43a5-a013-eac5e07faf30)
  - T2는 T1이 쓴 데이터 X를 overwrite하지만, 읽는 것은 완료(commit) 이후에 진행하고 있음
- 동시성 제어
  - 우리가 원하는 스케줄은 충돌 직렬가능 스케줄임과 동시에 회복 가능(RC) 또는 연쇄철회를 방지(ACA)하는 스케줄임
  - 트랜잭션을 우선 실행 한 후 판별 여부는 가능하나, 이미 수행한 후이므로 의미가 없음
  - 우리가 원하는 것은 위 규약을 준수하면서 트랜잭션을 수행하면, 우리가 원하는 스케줄이 나오게 하는 것임
    - 이를 **동시성 제어 규약**이라 칭함
  - 다음 장에서 동시성 제어 규약인 록킹(locking)에 대해 살펴볼 것
- 요약
  - isolation 관점에서의 판단 기준은 conflict serialization(직렬 가능)과 view serialization(뷰 직렬 가능)
  - 회복 관점에서는 recoverable, cascadeless, strict 존재
  - 각 차이점 명확히 인지할 것

## 연습문제 1장
### 1
- Explain the ACID properties of transactions
  - Atomicity(원자성)
    - All-or-Nothing
    - 연산 모두가 실행되거나, 어느 연산도 수행되지 않아야 함
  - Consistency(일치성)
    - 단일 트랜잭션의 수행은 데이터 무결성을 유지해야함
      - 다만, 트랜잭션 수행 중간에는 무결성제약을 만족하지 않을 수 있음
  - Isolation(독립성)
    - 다수 개의 트랜잭션이 동시 수행되어도 사용자에게는 본인 트랜잭션만이 홀로 수행되고 있는 느낌을 줌
  - Durability(지속성)
    - 트랜잭션 완료 후 시스템에 오류가 생겨도, 데이터베이스 상태에 반영되어야 함
### 2
- Consider a file system in Unix
- What are the steps involved in creation and deletion of files, and in writing data to a file(파일과 생성과 삭제, 파일에 데이터를 쓰는 과정에 관련된 단계를 설명하라)
  - 파일 시스템에서 파일에 저장 영역이 할당되고, 파일에 고유한 i-number(파일 디스크립터)가 주어지며 i-list에 i-node 항목이 삽입됨
  - 파일의 삭제는 정확히 반대의 단계를 포함함
- Explain how the issues of atomicity and durability are relevant to the creation and delection of files and to writing data to files(원자성과 지속성 문제가 파일의 생성 및 삭제, 파일에 데이터를 쓰는 것과 어떻게 관련이 있는가?)
  - 유닉스 파일 시스템의 *사용자*에게 지속성은 중요하지만, 파일 시스템이 트랜잭션을 지원하지 않기 때문에 원자성은 일반적으로 관련이 없음
  - 그러나 파일 시스템을 *구현하는 사람*에게는 내부 파일 시스템 동작의 많은 부분이 트랜잭션 의미 체계를 가져야함
    - 파일의 생성/삭제에 관련된 모든 단계는 원자적이어야 함
    - 그렇지 않으면 파일 시스템에서 참조할 수 없는 파일이나 사용할 수 없는 영역이 생길 것

### 3
- Database-system implementers have paid much more attention to the ACID properties than have file-system implementers. Why might this be the case?(DB 시스템 구현자들은 파일 시스템 구현자들보다 ACID에 더 많은 주의를 기울였는데, 왜 그런지 설명하라)
  - 데이터베이스 시스템은 원자적이고 지속적인 효과가 필요한 중요한 작업을 주로 수행
  - 그 결과는 영구적인 방식으로 실제 세계의 영향을 미침
    - ex) 금전거래, 좌석 예약
  - 따라서 ACID 속성을 보장해야함
  - 반면, 대부분의 파일 시스템 사용자들은 ACID 속성을 지원하는 데 필요한 비용(돈, 시간, 디스크 공간 등)을 지불하려고 하지 않을 것임
    - 파일 수정과 같은 일상적인 작업이 비교적 ACID가 덜 중요하단 소리임
### 4
- Explain the distinction of the terms serial schedule and serializable schedule. Explain why the serial schedule is always correct too.(직렬 스케줄과 직렬 가능 스케줄이라는 용어의 구별에 대해 설명하고, 직렬 스케줄이 항상 올바르다는 것에 대해서도 설명하라)
  - 직렬 스케줄
    - 하나의 단일 트랜잭션에 속한 모든 명령이 함께 나타나는 스케줄
  - 직렬 가능 스케줄
    - 특정 직렬 스케줄과 결과 값이 동일해야함
  - 충돌 직렬가능과 뷰 직렬가능성은 동등성에 대한 두 가지 정의
  - 직렬 스케줄은 모든 트랜잭션의 연산이 연속적으로 실행되고, 다른 트랜잭션의 연산에 의해 방해 받지 않기 때문에 항상 올바름

### 5
- why do we emphasize conflict serializability rather than view serializability?(우리는 왜 뷰 직렬가능성보다 충돌 직렬가능성에 더 강조하는가?)
  - 대부분의 동시성 제어 프로토콜(직렬 가능한 스케줄만 생성되도록 보장하기 위한 프로토콜)은 충돌 직렬 가능성을 기반으로 사용되고, 충돌 직렬 가능성 스케줄의 부분 집함만 허용함
  - 뷰 직렬가능성은 일반적으로 테스트하는데 굉장히 까다로우며, 동시성 제어를 위해 매우 제한된 형태만이 사용됨
  - 즉, 충돌 직렬 가능성이 보다 효율적으로 사용되기 떄문에 프로토콜 및 시스템 구현에 더 적합하고, 뷰 직렬가능성은 테스트 및 구현이 복잡하기 때문에 제한적으로 사용됨

### 6
- What do you think about the following statement. Justify the answer.
- Concurrent execution of transactions is more important when data must be fetched from(slow) disk or when transactions are long, and is less important when data is in memory and transactions are short(트랜잭션이 동시에 실행되는 것은 데이터가(느린) 디스크에서 가져와야 할 때나 트랜잭션이 길 때 더 중요하고, 데이터가 메모리에 있고 트랜잭션이 짧을 때는 덜 중요하다)
  - 트랜잭션이 매우 길거나, 느린 디스크에서 데이터를 가져올 때는 완료하는 시간이 오래 걸리기 때문에 동시성이 없는 경우 다른 트랜잭션이 더 오랜 시간 기다려야 하고 평균 응답시간이 증가함
  - 또한 트랜잭션이 디스크에서 데이터를 읽을 때, CPU는 idle 상태가 됨
    - 따라서 리소스가 제대로 활용되지 않고, 오버헤드가 크기 때문에 동시 실행이 중요해짐
  - 그러나 트랜잭션이 짧거나 데이터가 메모리에 있을 때는 이런 문제가 발생하지 않아 동시성의 중요성이 상대적으로 줄어듬

### 7
- What is a recoverable schedule? Why is recoverability of schedules important?(회복 가능한 스케줄이 뭐고, 왜 스케줄의 회복 가능성이 중요한가?)
  - 회복 가능한 스케줄은 T1과 T2 두 트랜잭션이 있을 떄, T2가 T1에 의해 쓰여진 데이터 항목을 읽는 경우, T1의 완료(커밋) 작업이 T2의 커밋 작업 이전에 나타나는 스케줄을 의미함
    - 즉, 데이터를 읽은 트랜잭션의 커밋 시점은 데이터를 쓴 트랜잭션 커밋한 후임
  - 중요한 이유는, 회복 가능하지 않으면 시스템을 rollback할 수 없는 불일치 상태가 될 수 있기 때문임

### 8
- Consider the precendence graph of Figure 1. Is the corresponding schedule conflict serializable?(선행 그래프를 봤을 때, 해당 스케줄은 충돌 직렬 가능 스케줄인가?)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f04ef7ed-0cf4-41dd-9f36-047517cee0bb)
    - 비순환적 그래프기 때문에 충돌 직렬화 가능한 스케줄이 존재함
    - 위상 정렬에 의해, `<T1, T2, T3, T4, T5>`, `<T1, T2, T4, T3, T5` 가능

### 9
- ![image](https://github.com/googoo9918/TIL/assets/102513932/ec647118-3f38-4eb7-bc01-b37728bca56b)
  - Is the history recoverable and/or cascadeless?(회복가능 여부, 연속적 철회 필요 없는 스케줄 여부)
  - 회복가능성
    - 한 트랜잭션이 다른 트랜잭션에 의해 수정된 데이터 항목을 읽은 경우, 데이터를 읽은 트랜잭션이 더 늦게 commit 되어야 한다는 사실은 이미 알고있었음
    - 그렇다면 abort에서는 어떻게 적용되는가?
      - abort의 경우, 데이터를 쓴 트랜잭션인 경우 회복 가능하지 않음
      - 위 스케줄의 경우, T2에서 write(B) 연산이 철회되므로, T3의 read(B)가 잘못된 연산이 되어버림.. 근데 commit을 진행했으니 회복 불가능함
  - 연속적 철회가 필요 없는 스케줄 여부
     - 연속적 철회가 필요 없으려면 완료된 값만을 읽어야함
     - 그래서 아님, 커밋되지 않은 값 읽는중

### 10
- ![image](https://github.com/googoo9918/TIL/assets/102513932/83775807-c767-41be-bcf8-e7a71f74d355)
  - (A) Draw a precendence graph for T1, T2, T3 and T4 for the schedule. Label each edge with the data item that is accessed
  - (b) is the schedule conflict serializable?(충돌 가능 스케줄인가?), if yes give all conflict-equivalent serial schedules(맞다면 직렬 가능한 스케줄 나열)
    - ![KakaoTalk_20231003_192009602](https://github.com/googoo9918/TIL/assets/102513932/b0f6c56f-4a70-482c-9ef5-c6ea09f62128)


### 11
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b94d5ea4-92b5-414e-a156-c16cba9d1b3d)
  - 상기 스케줄에 대한 선행 그래프를 작성하고, 충돌 직렬 가능성을 판별하라, 만약 맞다면 동등한 serial schedule을 모두 구할 것
  - 상기 스케줄의 view serializable 여부를 판정하고, 이유를 기술할 것
  - ![KakaoTalk_20231003_185951924](https://github.com/googoo9918/TIL/assets/102513932/6bf32bf2-f599-4516-86e8-e85cfe6ad8fb)


### 12
- 다음 문장의 참/거짓을 명시하고, 거짓일 경우 그 이유를 설명하라
  - view serializable한 스케줄은 항상 confilict serializable 하다
    - 거짓, Conflict serializable한 스케줄은 항상 view serializable 함
  - view serializable한 스케줄 판발연 precedence graph를 사용하며 polynomial time에 가능하다
    - 거짓, view serializable 스케줄 판단은 NP-complete 문제임