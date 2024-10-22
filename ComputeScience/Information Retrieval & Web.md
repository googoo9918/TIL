## 개요
- [개요](#개요)
- [웹서비스 아키텍처의 발전](#웹서비스-아키텍처의-발전)
  - [웹 서비스 아키텍처(Stage 1)](#웹-서비스-아키텍처stage-1)
  - [웹 서비스 아키텍처(Stage 2)](#웹-서비스-아키텍처stage-2)
  - [웹 서비스 아키텍쳐(Stage 3)](#웹-서비스-아키텍쳐stage-3)
  - [자바스크립트(Javascript)](#자바스크립트javascript)
- [웹서비스 아키텍처의 구성](#웹서비스-아키텍처의-구성)
  - [웹 아키텍처](#웹-아키텍처)
  - [Static vs Dynamic 컨텐츠](#static-vs-dynamic-컨텐츠)
    - [Static vs. Dynamic 콘텐츠](#static-vs-dynamic-콘텐츠)
    - [Static과 Dynamic 콘텐츠의 성능 차이](#static과-dynamic-콘텐츠의-성능-차이)
    - [Static과 Dynamic 콘텐츠 제공 과정](#static과-dynamic-콘텐츠-제공-과정)
  - [CDN(Content Delivery Network)](#cdncontent-delivery-network)
  - [캐싱](#캐싱)
  - [로드밸런서](#로드밸런서)
  - [방화벽/웹 방화벽/IDS/IPS](#방화벽웹-방화벽idsips)
- [웹 서비스 아키텍처의 구성](#웹-서비스-아키텍처의-구성)
  - [Apache Kafka](#apache-kafka)
- [HA(High Availability)](#hahigh-availability)
  - [Apache Zookeeper](#apache-zookeeper)
- [HTML1](#html1)
- [HTML#3(자바스크립트)](#html3자바스크립트)
  - [DOM(Document Object Model)](#domdocument-object-model)
  - [jQuery](#jquery)
  - [동기/비동기](#동기비동기)
  - [Node.js](#nodejs)
  - [HTTP](#http)

## 웹서비스 아키텍처의 발전
### 웹 서비스 아키텍처(Stage 1)
- HTML + HTTP
  - HTML은 콘텐츠를 제공
  - HTTP는 콘텐츠를 전송
- 파일
  - 정적(Static)
    - 저장된 파일을 그대로 전송
  - 동적(Dynamic)
    - 입력 데이터를 바탕으로 실시간으로 HTML을 생성하여 전송
    - DBMS를 통해 데이터 조회, 이를 기반으로 HTML 페이지 생성
    - 클라이언트트 서버에서 받은 HTML 파싱 및 랜더링
      - HTML이 주로 사용, JS는 부가적으로 사용
  - 동기식(Synchronous)
    - 클라이언트는 서버로부터 응답을 받을 때까지 기다린 후 화면에 표시함 

### 웹 서비스 아키텍처(Stage 2)
- AJAX / Web 2.0
  - AJAX는 비동기적인 데이터 전송 방식을 가능하게 함
    - 웹 페이지의 일부만을 업데이트할 수 있도록 해줌
    - Web 2.0은 사용자 상호작용이 강화된 동적인 웹을 지칭
  - MVC -> Multi-view 시스템에 대응
    - HTML 3개 만들거? -> x
    - JSON/XML - 동적으로 HTML 재구성
  - JSON(내용) + HTTP(전송)
    - 데이터 전송을 위해 HTML 대신 JSON 형식 사용
  - 웹 서버와 클라이언트 역할
    - 웹 서버는 클라이언트의 요청에 따라 JSON 형식의 데이터를 전송
    - 클라이언트는 수신한 JSON 데이터를 파싱, 렌더링하여 화면에 표시
    - JS가 주 역할, HTML은 보조 역할
  - 동적 콘텐츠의 DOM 조작
    - 클라이언트는 JSON 데이터를 통해 DOM(Document Object Model)을 직접 조작, 웹 페이지의 내용을 부분적으로 업데이트할 수 있음
      - 화면을 가공해서 표현할 수 있으며, 사용자에게 즉각적 반응 제공
  - 비동기 통신(Asynchronous)
    - 서버와의 상호작용을 필요할 때마다 처리, 페이지 전체를 다시 로드하지 않고 부분적 업데이트 가능
      - 사용자가 더 부드럽고 빠르게 웹 페이지와 상호작용

### 웹 서비스 아키텍쳐(Stage 3)
- JSON + HTTP
  - 여전히 JSON으로 데이터 전송, HTTP를 통함
- 양방향 통신(Bi-directional)
  - 웹 소켓을 이용, 클라이언트와 서버 간의 양방향 실시간 통신이 가능해짐
    - 연결이 유지된 상태에서 양방향으로 자유롭게 데이터 교환
    - HTTP처럼 요청-응답 구조 x
  - 실시간 통신(Real-time API)을 위해 Node.js같은 서버 기술 활용
    - Node.js는 비동기 처리를 기반으로 하기에, 실시간 처리가 필요한 웹 애플리케이션에 적합
      - 채팅, 주식거래 시스템과 같은 실시간 업데이트가 중요한 애플리케이션에서 주로 사용

### 자바스크립트(Javascript)
- jQuery.js
  - js 라이브러리, 브라우저 간의 호환성을 높이고, 독립성을 제공
    - 이를 통해 js 코드가 다양한 브라우저에서 일관되게 동작하도록 도와줌
- 웹표준(ECMAscript:ES)으로 채택
  - js는 ES 표준에 따라 발전해 왔고, ES6~9등의 버전으로 업데이트되었음
- HTML5(넓은 의미) = HTML5(좁은 의미) + CSS3 + ECMAscript(ES) + SVG(2D) + WebGL(3D)
  - 단순한 마크업 언어를 넘어, 다양한 웹 기술을 포함하는 광범위한 개념임
- 파편화 문제
  - 브라우저마다 지원하는 기능이 달라 Fragmentation 문제가 있었음
    - jQuery로 이러한 문제점 해결
- 프레임워크
  - Angular.js
    - 타임스크립트 사용, OOP와 DI 지원
  - React.js
    - 컴포넌트 기반, 뷰를 효율적으로 업데이트
  - Vue.js
    - React와 Angular의 장점을 결합한 구조 제공
  - 하이브리드 앱 프레임워크
    - 하나의 코드베이스로 ios와 android 동시 개발 가능
    - React Native
    - Flutter
      - Dart 언어 사용
- 브라우저 외부에서의 사용
  - 원래 브라우저에서만 실행되는 언어였으나
    - Node.js의 등장으로 서버나 스탠드얼론 환경에서도 사용 가능
    - Node.js는 js를 브라우저 밖에서 사용할 수 있는 서버 사이드 자바스크립트 런타임임
- 비동기 실행
  - 비동기 실행을 기본으로 지원
  - 독특한 특성

## 웹서비스 아키텍처의 구성
### 웹 아키텍처
- 1-티어
  - 웹 브라우저(HTTP 클라이언트) = 웹 서버(HTTP 서버)
- 2-티어
  - HTTP 클라이언트 - HTTP 서버/DB 클라이언트 - DB 서버
- 3-티어
  - HTTP 클라이언트 - HTTP 서버/WAS 클라이언트 - WAS서버/DB 클라이언트 - DB 서버
    - 클라이언트 요청 -> 웹 서버가 이를 WAS에 전달 -> WAS가 DB 서버에서 데이터를 가져와 응답
- 더 복잡해지면 n-티어
- 왜 티어를 나누는가?
  - 티어 분리를 통해 각 서버에 특화된 기능을 추가함
  - 0티어
    - Stand-alone 어플리캐이션, 클라이언트와 서버가 분리되지 않음
  - 1티어
    - 서버와 클라이언트 분리
    - 네트워크를 통해 서버와 통신
    - 정적 웹사이트 파일 제공이나, 간단한 API 요청에 응답하는 정도의 역할을 함
  - 2티어
    - DB 서버 분리
    - 클라이언트가 웹 서버를 통해 DB 서버에 접근
  - 3티어
    - WAS를 추가로 분리
      - 더 높은 확장, 보안, 안전성 제공
    - 분산 트랜잭션, 로드 밸런싱, 이중화, 동시성 제어 등 사용 가능
    - 비즈니스 로직 담당

### Static vs Dynamic 컨텐츠
#### Static vs. Dynamic 콘텐츠
- Static 콘텐츠
  - 미리 저장된 HTML 페이지를 그대로 클라이언트에 전송
  - 아파치 웹서버 or nginx
    - ex) http://abc.com/a.html
- Dynamic 콘텐츠
  - 사용자 요청에 따라 내용이 동적으로 생성
  - 프로그램 실행 -> DBMS 내용 검색 -> HTML로 변환하여 사용자에게 전송
  - 아파치 톰캣
    - ex) http:abc.com::8080/b.jsp
- 연동
  - 아파치가 받아서 톰캣에 넘겨주고 실행결과를 다시 아파치에게 넘겨줌
    - ex) http://abc.com/b.jsp
#### Static과 Dynamic 콘텐츠의 성능 차이
- 서버 부하
  - Dynamic 콘텐츠는 Static 콘텐츠에 비해 더 많은 서버 자원을 소모, 서버 부하 커질 수 있음
  - Dynamic 콘텐츠 요청이 많아질 수록 성능 저하 발생 가능
- 캐싱(Dynamic -> Static 컨텐츠화)
  - Dynamic 콘텐츠를 Static 콘텐츠로 변환하여 캐싱하면 성능을 향상시킬 수 있음
    - 서버 부하 감소 가능

#### Static과 Dynamic 콘텐츠 제공 과정
- Static 콘텐츠 제공
  - 웹 서버가 요청을 받아 HTML 파일 그대로 반환, 별도 연산이나 DB 접근 필요 x
- Dynamic 콘텐츠 제공 및 연동
  - 아파치와 같은 웹 서버가 요청 수신 후, 이를 웹 애플리케이션 서버 전달
  - 웹 애플리케이션 서버가 JSP, PHP 등의 동적 페이지 실행하여 HTML 생성 -> 아파치 서버로 반환 -> 클라이언트에게 전달

### CDN(Content Delivery Network)
- CDN은 콘텐츠를 빠르게 제공하기 위해 전 세계에 분산된 서버를 통해 레이턴시(지연 시간) 문제를 해결함
  - 사용자와 물리적으로 가까운 서버에 데이터를 저장, 사용자가 요청할 때 더 빠르게 콘텐츠를 제공
  - 자주 요청되는 콘텐츠를 이 서버들에 미리 복제해 둠
    - 가장 가까운 서버에서 콘텐츠를 보내므로, 지연 시간을 줄일 수 있음
### 캐싱
- 캐시
  - 자주 사용되는 데이터를 임시로 저장하는 메모리
  - 지역성
    - 시간적 지역성
    - LRU(Least Recently Used)
    - 공간적 지역성
    - LFU(Least Frequently Used)
  - Write 정책
    - Write-through
      - 캐시와 메모리를 동시 업데이트
    - Write-back
      - 캐시에서 먼저 업데이트, 추후에 메모리에 기록
      - ex) 변경된 데이터를 버퍼에 모아 두았다가, 메모리에 한 번에 쓰는 방식
- 버퍼
  - 주로 입출력 장치 간에 속도 차이를 해결하기 위해 사용
    - ex) 데이터를 순차적으로 읽거나 쓰는 과정에서 빠르게 데이터를 받아들이고 나중에 처리
    - ex) 대규모 데이터 처리 -> 데이터가 한꺼번에 들어오는 경우, 버퍼에 저장해 두고 천천히 처리하는 방식으로 시스템 성능을 향상시킴
  - 큐, 우선순위 큐
    - 우선순위 큐는 프로세스/스레드 스케줄링에 주로 사용
- 브라우저 캐시
  - 브라우저는 방문한 웹 페이지의 자원(HTML,CSS,JS 등)을 로컬에 저장
  - 재방문 시 빠르게 페이지 로드가 가능하게 함
- 포워드 프록시
  - 클라이언트를 대신하여 서버에 요청을 전달
  - 다수의 클라이언트가 같은 자원 접근 시 해당 자원을 캐싱, 전송을 빠르게 함
  - 프록시 서버는 자주 요청되는 페이지나 자원을 캐싱, 같은 요청이 들어오면 자원을 제공하여 부하를 줄임
  - 주로 방화벽과 같은 개념으로 사용
- 리버스 프록시
  - 서버를 대신하여 클라이언트 요청을 받고, 서버에 전달
  - 서버의 앞단에서 클라이언트 요청을 받아 적절한 서버에 전달(로드 밸런싱), 서버 측에서의 캐싱을 담당하며 웹 서버의 부담을 줄임
  - 캐시된 콘텐츠가 없는 경우
    - 서버에서 직접 데이터를 받아와 클라이언트에게 전달
  - 캐시된 콘텐츠가 있는 경우
    - 서버에 접근하지 않고 직접 캐시된 데이터를 클라이언트에 응답
  - ex) nginx

### 로드밸런서
- 로드 밸런서
  - 동일 내용을 여러 시스템에 복제해서 구성 -> 외부에서 요청이 들어오면 여러 서버 중 하나로 라우팅하여 부하를 분산함
    - 가용성 증가, 성능 및 응답 속도 개선
- L4 스위치
  - 네트워크 계층에서 동작
  - TCP/UDP 프로토콜과 포트 정보를 기반으로 트래픽 분산
  - AWS NLB
- L7 스위치
  - 애플리케이션 계층에서 동작
  - HTTP/HTTPS 요청의 헤더와 내용을 기반으로 트래픽 분산
    - URL, HTTP 메서드, 콘텐츠 종류 등 분석하여 분산 가능
    - 요청 내용을 세부적으로 분석할 수 있음 -> 세밀한 트래픽 관리 가능
  - AWS ALB

### 방화벽/웹 방화벽/IDS/IPS
- 방화벽
  - 네트워크를 보호하기 위해 IP 주소와 포트 정보를 기반으로 인바운드/아웃바운드 트래픽을 필터링하고 통제 
  - DMZ
    - 방화벽 외부에 존재하는 영역, 인터넷에서 접근할 수 있도록 설정된 서버들이 있는 구역
    - 방화벽은 외부에서 내부 네트워크로 들어오는 트래픽에 대해 공인 IP와 사설 IP 주소 간의 변환을 관리
      - 외부 인터넷이 연결되어야 하면 공인 IP, 외부 인터넷 접근 시 공인 IP 공유 방식으로 사용 시 사설 IP 사용
      - 포트포워딩 사용
        - NAT의 한 형태, 포트 번호까지 지정하여 라우팅 제어
  - whilelist vs blacklist
    - 허용할 ip 주소/포트 --> 화이트리스트
    - 차단할 ip 주소/포트 --> 블랙리스트
- 웹 방화벽
  - 웹 애플리케이션 계층에서 발생하는 공격을 탐지하고 차단
  - SQL 인젝션, XSS등 공격 방어
- IDS(Intrusion Detection System)
  - 침입 탐지 시스템
    - 네트워크나 시스템에서 이상한 패턴 감지 -> 관리자에게 경고
    - 차단 기능은 없음
- IPS(Intrusion Prevention System)
  - 침임을 차단하는 시스템
    - 악성 트래픽을 실시간으로 차단

## 웹 서비스 아키텍처의 구성
### Apache Kafka
- 고성능 분산 메시징 시스템
  - 대규모 데이터 스트림 처리 + 큐잉, 일관성 및 안전성 보장
- 구성 요소
  - Producer
    - 메시지 생성 -> kafka로 전송
  - Consumer
    - Kafka에서 메시지를 읽어감
  - Broker
    - Kafka 서버 자체로, 메시지 저장 및 관리
- 주요 기능
  - 분산 메시징
    - 메시지 분산 저장 -> 가용성 높임
  - 스트리밍/버퍼링
    - 실시간 데이터 스트림 처리 및 저장
      - ex) 로그 집계, 헬스 체크, 실시간 모니터링
  - 이벤트 소싱
    - 데이터를 시간 순서대로 기록, 과거 이벤트 추적
- Kafka 도입 전후의 구조 변화
  - 도입 전
    - 여러 데이터 소스와 시스템 간 복잡한 연결이 필요했음
  - 도입 후
    - Kafka가 중간에 위치하여 중앙 메시지 허브 역할을 수행
      - 데이터 소스와 애플리케션이 Kafka만을 통해 통신
- 요약
  - 큐잉 서비스는 트래픽 과부하를 방지, 안전성을 높임
  - Apache Kafka는 이러한 큐잉 기능을 고성능으로 제공하는 분산 메시징 플랫폼
  - 대량의 실시간 데이터를 안정적으로 처리 가능 + 복잡한 시스템 간 데이터 흐름을 중앙 집중화하여 관리 가능
- Kafka의 토픽 개념
  - 토픽이란, Kafka에서 메시지를 카테고리별로 구분하는 논리적 단위임
    - ![image](https://github.com/user-attachments/assets/f2ef4879-2389-4772-980a-babd483567fa)
    - Producer는 메시지를 특정 토픽에 쓰고, Consumer는 특정 토픽에서 메시지를 읽음
  - 파티션
    - ![image](https://github.com/user-attachments/assets/8e0ac688-8a46-41e1-bb9b-8fc274e852f9)
    - 각 토픽은 여러 파티션으로 나뉘어 질 수 있으며, 파티션 내의 메시지들은 offset으로 구분되어 순서대로 저장됨
    - 파티션을 통해 토픽을 병렬로 처리할 수 있음
  - offset
    - 각 메시지는 파티션 내에서 고유의 offset을 가지며, 컨슈머는 이 오프셋을 통해 어떤 메시지까지 읽었는지 추적함
- Kafka의 파티셔닝
  - 각 토픽은 여러 파티션으로 구성될 수 있으며, 파티션이 많을 수록 데이터는 더 많은 브로커에 분산됨
  - ex)
    - 토픽이 3개의 파티션으로 구성된 경우, 프로듀서가 메시지를 쓰면 각 파티션에 메시지가 할당됨
    - 컨슈머는 각 파티션에서 메시지를 병렬로 읽을 수 있어 데이터 처리 성능이 높아짐
  - ex)
    - 토픽 A에 3개의 파티션이 있고, Kafka 클러스터에 3개의 Broker가 있다고 가정
    - 파티션 0은 브로커 1에, 파티션1은 브로커 2에, 파티션 2는 브로커3에 분산됨
    - 토픽 A의 데이터는 모든 브로커에 분선 저장, 브로커 장애가 발생해도 다른 브로커에서 파티션 복구 가능
    - 또한 각 Consumer는 서로 다른 파티션에서 메시지를 읽을 수 있음
      - 여러 컨슈머가 동시에 서로 다른 파티션을 병렬로 처리할 수 있게 됨
- Kafka 브로커 및 클러스터
  - ![image](https://github.com/user-attachments/assets/30009fae-a037-4f9f-8aad-cd838a01abdc)
    - 브로커
      - Kafka Broker는 데이터를 저장하고 관리하는 Kafka 서버
      - 여러 개의 브로커가 클러스터를 구성, 토픽과 파티션을 여러 브로커에 분산 저장하여 가용성과 확장성 보장
    - ZooKeeper
      - Kafka의 동작을 관리하고 저장
      - Kafka 클러스터의 브로커 간 메타데이터 관리
      - 브로커와 컨슈머 간의 상태를 조정
- Kafka 클러스터 예시
  - ![image](https://github.com/user-attachments/assets/52507511-2c2d-4551-94f6-d1e4c608e88d)
    - 각 파티션은 하나 이상의 브로커에 복제되어 저장
      - 이를 리프리카라 부름, 특정 브로커가 장애를 겪어도 데이터 손실 방지
    - 리더, 팔로워
      - 각 파티션은 리더와 팔로워로 구성
      - 리더는 모든 쓰기와 읽기 요청을 처리
        - 팔로워는 리더의 데이터를 복제함
        - 리더 브로커가 장애를 겪으면 팔로워가 리더 역할을 승계함
    - 컨슈머 그룹
      - 컨슈머 그룹 내의 각 컨슈머는 특정 파티션에서만 데이터를 읽어옴
      - 여러 컨슈머가 데이터를 병렬로 처리 가능
      - ex) Consumper Group A는 Topic Foo의 파티션 처리, Consumer Group B는 Topic Bar의 파티션 처리
- Kafka가 빠른 이유
  - 제로카피
    - Kafka는 데이터를 처리할 때, 메모리에서 데이터를 복사하지 않고 데이터의 주소를 복사하는 방식을 사용
    - 데이터 복사로 인한 오버헤드를 줄여 성능을 높임
  - 리눅스/BSD 커널 최적화
    - Kafka는 BSD 네트워크 커널의 mbuf를 직접 이용, 데이터를 복사하지 않고 포인터로 데이터를 참조함으로써 성능을 향상시킴
    - 데이터가 캡슐화되면서 발생하는 오버헤드를 줄임
- Kafka vs RabbitMQ/ActiveMQ 성능 비교
  - ![image](https://github.com/user-attachments/assets/8dcfaa6c-fae5-4b02-a37f-f7ff57305353)
    - Kafka의 성능
      - Kafka는 대량의 메시지를 안정적으로 처리 가능
      - 배치 크기를 늘릴 수록 처리 속도가 빨라짐
    - RqbbitMQ와 ActiveMQ
      - 상대적으로 낮은 메시지 처리량
        - 특히 대용량 데이터 처리 시
      - 실시간 처리 최적화
        - 실시간 메시지 전송에 강점을 지님

## HA(High Availability)
- HA(Hight Availability)
  - 고가용성
    - 노드 장애가 발생하더라도 서비스는 계속 운영할 수 있도록 만드는 기술
    - 데이터 복제와 장애 발생 시 자동 전환 등..
- 장애 상황에 따른 처리 방식
  - 슬레이브 노드 장애 시
    - 다른 슬레이브 운영 중이라면 서비스는 문제없이 동작
    - 새로운 슬레이브 생성 후 복제를 재개하면 그만
  - 마스터 노드 장애 시
    - 서비스가 중지될 수 있음
    - 슬레이브 노드 중 하나를 새로운 마스터로 승격하여 서비스 재개
    - 새로운 슬레이브를 설정하여 복제
- 고가용성 구성 방식
  - Active-Standby 구성
    - 하나의 액티브 노드와 그 노드를 대신할 스탠바이 노드로 구성
    - Active 노드가 장애를 겪으면 Standby 노드가 Active 노드로 승격
    - 새 Standby 노드를 설정, 두 개의 노드가 유지되도록 함
    - Standby에서 Active로의 전환이 느리면 문제 발생할 수 있음
  - Active-Active 구성
    - 한 Active 노드가 장애를 겪어도 다른 Active 노드가 운영을 이어감
  - Active-Active 구성의 합의
    - 여러 노드가 동시에 Active로 동작, 데이터 처리에 있어 의견 충돌이 발생할 수 있음
    - 합의 방식
      - 보통 홀수 개의 노드를 두어 과반수 합의를 이끌어냄.
        - MYSQL에서는 그룹 복제(Group Replication) 같은 방식으로 구현

### Apache Zookeeper
- ZooKeeper 개요
  - 기능
    - 분산 시스템의 노드 간 상태 정보를 공유
    - 장애 복구 및 데이터 일관성 유지
  - 필요성
    - Haddop 2.x에서는 추천 사항
    - Kafka에서는 필수
      - 이를 통해 클러스터 상태와 리더 선출 관리
- ZooKeeper 주요 특징
  - 고가용성
    - ZooKeeper는 분산 시스템의 고가용성을 보장하기 위해 설계됨
    - 단일 장애 지점(SPOF) 제거
      - 단일 장애 지점을 보완하여 시스템의 안정성을 높임
    - 리더 선출
      - 분산 시스템에서 리더 노드를 선출하고 관리
- ZooKepper 아키텍쳐
  - ![image](https://github.com/user-attachments/assets/873fe5d3-babb-47e2-8365-9fdb9e622fa5)
    - Leader
      - 쓰기 요청 처리, 시스템 변경 사항을 슬레이브 노드(Follwer)와 동기화
    - Follower
      - Leader 노드의 변경 사항을 복제, Leader가 장애를 겪을 시 새로운 Leader 선출
    - Clients
      - ZooKeeper 클러스터에 접근, 데이터를 읽거나 쓰는 역할 수행
- ZooKeeper의 구성 방식
  - Master/Slave 구성
  - Active / Stand-by
  - Active / Active

## HTML1
- 웹서버
  - Apache2 / Nginx
  - 주로 정적 컨텐츠 실행
  - 주로 80번 포트 사용
    - 암호화 사용 시에는 443 사용
- 서블릿/JSP 엔진
  - Apache Tomcat / Resin
  - 주로 동적 컨텐츠 실행 
  - 주로 8080번 포트 사용
    - 암호화 사용 시 8443 포트 사용
- HTTP 암호화
  - 7계층 암호화
    - ALS(Application Layer Security)
      - Secure Http(shttp://)
        - 현재는 사용x
        - 응용 프로그램별 암호화
  - 4계층 암호화
    - TLS(Transport Layer Security)
      - HTTP with SSL/TLS(443/8443)
      - https://
      - 포트별 암호화
  - 3계층 암호화
    - NLS(Network Layer Security)
      - IP Security(IPsec)
      - IP별 암호화
      - 데이터 패킷 암호화, 주로 VPN에서 사용
- 웹서버(스태틱 + 다이나믹) 연동
  - HTML과 JSP/Servlet의 웹서버 연동
    - `http://a.b.c.d:80/sample.html`은 80번 포트를 사용, 정적 HTML을 제공
    - `https://a.b.c.d/sample.html`은 443 포트를 사용, 암호화된 형태로 THML을 제공
  - JSP/Servlet 처리
    - `http://a.b.c.d:8080/sample.jsp`은 8080 포트를 통해 JSP/Servlet 제공
    - `https://a.b.c.d:8443/sample.jsp`은 8443 포트를 통해 JSP/Servlet에 HTTPS 암호화를 적용한 경우
- Apache와 톰캣 연동
  - `http://a.b.c.d:80/sample.jsp`는 Apache 웹 서버와 Tomcat 서블릿 엔진이 연동된 형태
    - 정적 콘턴츠와 동적 콘텐츠를 함께 처리
- SSL/TLS
  - Secure Socket Layer, Transport Layer Security
    - 전송 계층에서 데이터를 암호화
      - 제3자가 데이터를 가로채더라도 내용을 이해하지 못하게 함
  - SSL 1.0 -> SSL 2.0 -> SSL 3.0 -> TLS 1.0 -> TLS 1.1 -> TLS 1.2 -> TLS 1.3
- 웹 브라우저의 기능
  - HTML 파서
    - 서버로부터 전달받은 HTML 문서를 파싱
  - HTML 렌더러
    - 파싱된 정보를 토대로 웹 페이지를 화면에 실제로 그리는 과정
  - HTTP 클라이언트
- HTML의 역사와 변화
  - HTML 4.0
    - CSS의 분화
      - 콘텐츠와 스타일을 분리하는 CSS(Cascading Style Sheets)가 도입됨
      - HTML3.2/CSS1.0 -> HTML4/CSS2 -> HTML5/CSS3
  - 파편화
    - HTML4.0에서는 Dynamic HTML(DHTML)의 개념이 도입됨
      - JS와 CSS를 사용해 웹 페이지를 동적으로 변경
  - UX와 RIA(Rich Internet Application)
    - Adobe Flex와 같은 기술을 통해 더 풍부한 사용자 인터페이스와 상호작용이 가능
- Tag 기반 언어
  - HTML은 태그 기반 언어, 각 태그는 특정 기능을 나타냄
  - `<태그명>` 으로 열고, `</태그명>`으로 닫음
  - `<br>`, `<hr>`(수평선) 등은 태그 x
- Well-formed vs Ill-formed
  - Well-formed
    - 문법에 맞게 작성된 HTML
  - Ill-formed
    - 문법이 잘못된 HTML
    - `<br>`과 `<br/>`은 모두 사용할 수 있지만, XHTML 표준에서는 소문자 사용과 태그를 닫는 방식이 더 엄격하게 요구됨
    - XHTML은 HTML의 확장형, 모든 태그는 반드시 소문자로 쓰이고, 닫는 태그를 포함해야 함
- HTML 예시
  - ![image](https://github.com/user-attachments/assets/4af12221-9b82-4280-b105-862a271258b1)
```html
<!DOCTYPE html>
<html>
    <head>
        <title>Hello World</title>
    </head>
    <body>
        <h1>Hello World</h1>
    </body>
</html>
<!-- <head>는 메타 정보나 스타일을 정의, 브라우저 탭에 표시됨 -->
<!-- <body>는 실제 화면에 나타나는 내용을 작성 -->
```

```html
<h1>Hello World</h1>
<!-- 이렇게 작성해도 위와 같은 결과가 나옴 -->
```
- Doctype
  - 문서의 스키마를 정의
  - 웹 브라우저는 Doctype 선언을 보고 어떤 HTML 버전으로 해석할지 결정
  - `<!DOCTYPE html>`
    - HTML5 문서임을 명시
- Tags
  - 태그를 사용하여 문서 구조 정의
  - `<h1>` ~ `<h6>`
    - 제목을 나타내는 태크
    - `<h1>`이 가장 큰 제목을 의미, `<h6>`은 가장 작은 제목을 의미
  - `<p>`
    - 문단을 나타내는 태그
    - 텍스트 블록을 구분하는 데 사용
  - `<a>`
    - 하이퍼링크를 나타내는 태그
  - `<img>`
    - 이미지를 삽입하는 태그
  - 태그명은 소문자로 사용하는 것이 권장
    - XHTML에서는 소문자로 사용하는 것이 필수
- Tags / Attributes
  - 태그는 각기 속성을 가질 수 있으며, 속성은 태그의 추가적인 정보를 정의함
  - `<a href="">`
    - href 속성은 <a>태그의 링크 경로를 지정
  - `<img src="">`
    - src 속성은 <img>태그의 이미지 파일의 경로를 지정함
  - 태그명뿐 아니라 속성명도 소문자로 작성하는 것이 권장됨
    - XHTML에서는 필수
  - 속성은 보통 `key="value"` 형식으로 작성됨
    - ex) `checked="checked"`는 체크박스가 선택된 상태를 나타냄
- 주석
  - `<!-- -->`로 주석처리
- 예시
  - ![image](https://github.com/user-attachments/assets/1fd177df-f7ca-4966-b600-8a1f9c1309f0)
```html
<table border="1">
    <tr>
        <th>name</th>
        <th>email</th>
    </tr>
    <tr>
        <td>seokjae ha</td>
        <td>sjha72@gmail.com</td>
    </tr>
</table>
<!-- <table>: 테이블을 생성하는 태그(border 떄문에 테이블에 테두리 표시) -->
<!-- <tr>: 테이블의 행을 정의 -->
<!-- <th> 테이블의 헤더 셀을 정의, 각 열의 제목이 됨 -->
<!-- <td> 테이블의 실제 데이터를 담는 셀 정의 -->
```

- 예제3
  - ![image](https://github.com/user-attachments/assets/ab90940b-1427-4c98-822b-c530f7d57ebc)
```html
<ul>
    <li>seokjae ha</li>
    <li>sjha72@gmail.com</li>
</ul>

<ol>
    <li>seokjae ha</li>
    <li>sjha72@gmail.com</li>
</ol>
<!-- <ul> : 순서 없는 목록 -->
<!-- <ol> : 순서 있는 목록 -->
<!-- 둘 다 <li>(list item)을 통해 항목 나열 -->
```

- Block/Inline Elements
  - 블록 엘리먼트
    - 페이지에서 전체 너비를 차지하는 요소
    - 한 줄을 독립적으로 처리, 다음 엘리먼트는 자동으로 새 줄로 넘어감
    - 대부분의 태그
  - 인라인 엘리먼트
    - 여러 개를 나열하면 한 줄로 출력(가로로 나열)
    - ex) `<a>` , `<img>`, `<span>`
- `<div>`와 `<span>`
  - `<div>` 태그(블록 엘리먼트)
    - 전체 영역을 묶어 레이아웃을 관리
  - `<span>`태그(인라인 엘리먼트)
    - 텍스트 일부에 스타일 적용 시 사용

- 웹 페이지의 레이아웃
  - 테이블을 이용한 화면 구성
    - border = "0"을 통해 테이블의 테두리를 없애고 레이아웃 구조만 사용
      - 과거에 사용
  - 리스트를 이용한 화면 구성
    - 현대에서는 리스트 `<u1>`, `<o1>`, `<li>`와 같은 요소에 css를 결합하여 사용
    - pc와 모바일 같은 여러 환경에 대응하기 위해서는 리스트 기반 레이아웃이 더 유연함
      - 반응형 웹 디자인
- CSS
  - 웹 페이지 스타일(폰트, 크기, 색상, 문단정렬 등)을 CSS로 지정
  - 페이지 내용과 스타일 분리
- CSS 예제
  - ![image](https://github.com/user-attachments/assets/eb739c6f-baae-48a0-9347-a33f0a1426eb)
```html
<!DOCTYPE html>
<html>
<head>
    <style>
        p {
            color: red;
            text-align: center;
            background-color: rgb(0, 0, 255);
        }
    </style>
</head>
<body>
    <p>Hello World!</p>
</body>
</html>
<!-- <style>태그를 통해 CSS 지정 -->
<!-- <p> 태그에 대한 CSS 스타일 정의, 모든 <p> 태그에 이 스타일이 적용됨 -->
```

- CSS 스타일 지정 방법
  - 해당 태그에 직접 지정
    - `<h1 style="color: red;">Hello World!</h1>`
      - 개별 태그에만 스타일 적용
  - `<style>` 태그를 이용한 내부 스타일링
    - `<head>` 태그 내에 `<style>` 태그 사용
  - 외부 스타일 시트 사용
    - `<link>` 태그를 사용해 외부 css 파일 연결
    - `<link href="styles/style.css" rel="stylesheet" type="text/css">`
- CSS 셀렉터
  - 태그 선택자
  ```html
  p {
    color: green;
  }
  <!-- 페이지의 모든 <p> 태그에 적용 -->
  ```
  - ID 선택자
  ```html
  #header {
    background-color: yellow;
  }
  <!-- id = "header"을 가진 요소에 적용 -->
  ```
  - 클래스 선택자
  ```html
  .highlight {
    font-weight: bold;
  }
  <!-- class = "highlight"를 가진 모든 요소에 적용 -->
  ```
- ID 선택자 예시
```html
<!DOCTYPE html>
<html>
<head>
    <style>
        #para1 {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
    <p id="para1">Hello World!</p>
    <p>Hello World!</p>
</body>
</html>
```
- 클래스 선택자 예시
```html
<!DOCTYPE html>
<html>
<head>
    <style>
        .center {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
    <h1 class="center">Hello World!</h1>
    <h1>Hello World!</h1>
    <p class="center">Hello World!</p>
</body>
</html>
```
- 복합 선택자 예시
```html
<!DOCTYPE html>
<html>
<head>
    <style>
        h1, h2, p {
            text-align: center;
            color: red;
        }
        p.center {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
    <h1>Hello World!</h1>
    <h2>Hello World!</h2>
    <p>Hello World!</p>
    <p class="center">Hello World!</p>
</body>
</html>
```

- CSS color
  - `color :azure;`
    - CSS에서 색상 지정 시 색상 이름 사용 가능
  - RGB(Red, Green, Blue)
    - `rgb(255,0,0)`
  - HSL(Hue, Saturation, Lightness)
    - 색조, 채도, 밝기 등을 사용하여 색상 지정
    - 디자이너가 주로 사용
  - RGBA
    - RGB 값에 투명도(Alpha)를 추가한 방식
    - 0(투명) ~ 1(불투명) 설정 가능
- CSS background-color
  - `background-color: lightblue`
    - 배경색 역시 색상 이름으로 지정 가능
  - Color Code(색상 코드)
    - `background-color: #ffcc00;`
  - `background-color: rgba(255, 0, 0, 0.5);  /* 반투명 빨간색 */`
  - `opacity: 0.7;  /* 요소 전체가 70% 불투명 */`
  - 알파 채널은 투명도를 의미
  - 24 비트 / 32비트 색상은 각각 3바이트(R,G,B)와 4바이트(R,G,B,A)로 색상을 표현
- CSS Border(경계선) 개념
  - CSS Border 스타일
```html
p {
    border-style: solid;
}
<!-- 실선 경계선이 적용된 <p> 태그 스타일 -->
<!-- solid(실선), dashed(점선), dotted(점점선) 사용-->
```

  - CSS Border 폭(width)
```html
p {
    border-width: 25px 10px 4px 35px;
}
<!-- 순서대로 위, 오른쪽, 아래, 왼쪽 -->
<!-- 네 면의 경계선 두께가 설정됨 -->
```
  - 축약 표현
    - 한 줄에 경계선 스타일, 두께, 색상을 모두 정의
    - `border: 25px solid red`
      - 경계선 두께는 25px, 실선, 색상은 빨간색

- CSS Box Model
  - ![image](https://github.com/user-attachments/assets/8e8562f0-23ce-4b73-9cbc-4e5366cfeead)
  - HTML 요소를 박스 형태로 모델링
    - 내용, 안쪽 여백, 경계선, 바깥 여백
```css
div {
    width: 300px;
    border: 15px solid green;
    padding: 50px;
    margin: 20px;
}
/* 콘텐츠 영역의 너비 300 */
/* 15픽셀 두께의 녹색 실선 경계선 설정 */
/* 안쪽 여백 50픽셀 */
/* 바깥 여백 20픽셀 */
```
- CSS Text
  - Color
    - `color: red`
  - Alignment
    - `text-aling: center;`
  - Transformation
    - `text-transform: uppercase`
  - shadow
    - `text-shadow: 2px 2px 5px grey`
- CSS Link
  - `a:link { color: blue }`
    - 방문하지 않은 일반 링크에 스타일 적용
  - `a:visited { color: purple }`
    - 방문한 링크에 스타일 적용
  - `a:hover { color: red; }`
    - 마우스를 링크 위에 올렸을 때 적용
  - `a:active { color: green; }`
    - 사용자가 클릭하는 순간 링크에 적용
- CSS List
  - 불릿 스타일(Bullet Style)
    - `<u1>`이나 `<o1>`과 같은 목록에 대해 불릿 모양 변경 가능
  - 이미지 불릿
    - 불릿 기호를 이미지로 변경할 수 있음
```css
ul {
    list-style-image: url('bullet.png');
}
/* list-style-image를 통해 이미지로 불릿 기호 대체 가능 */
```
- CSS Table
```css
/* 테이블의 경계썬 병합 */
table {
    border-collapse: collapse;
}

/* 테이블 행에 마우스를 올릴 때 배경색 변경 */
tr:hover {
    background-color: yellow;
}

/* 짝수 행마다 다른 배경색을 적용 */
tr:nth-child(even) {
    background-color: #f2f2f2;
}
```

- CSS Display
  - 화면에서 감추기
  - `display: none`
    - 요소를 화면에서 완전히 제거
  - `visibility: hidden`
    - 요소를 보이지 않게 하지만, 공간은 유지됨
  - 인라인/블록 표시 변경
    - `display: inline`
      - 요소를 인라인 요소로 표시, 블록 요소를 한 줄에 표시하고 싶을 때 사용
    - `display: block`
      - 요소를 블록 요소로 변환, 줄바꿈이 발생

## HTML#3(자바스크립트)
- jQuery.js
  - 브라우저 간 호환성 문제를 해결하기 윟 ㅐ만들어짐
  - 개발자는 다양한 브라우저에서 동일하게 동작하는 js 코드 작성 가능
- 실행 환경
  - 브라우저에서만 실행
    - 기본적으로 브라우저에서 실행
    - 일반적으로 HTML 문서 안에 포함되어 실행
  - 서버측 실행
    - Node.js 사용
    - 비동기 방식 실행
- Typescript
  - 객체 지향 버전의 자바스크립트
  - ex) Angular.js(필수), vue.js 및 React.js (권장 or 선택)
- JS 출력 예제
```Javascript
<!DOCTYPE html>
<html>
<head>
    <title>JavaScript Example</title>
</head>
<body>
    <h1>JavaScript Example</h1>
    <p id="demo"></p>
    <script>
        // id="demo"로 설정된 요소의 내용을 My First JavaScript로 변경
        document.getElementById("demo").innerHTML = "My First JavaScript";

        // HTML 문서에 hello라는 텍스트 출력, 페이지 로딩 시 텍스트를 추가로 출력하는 데 사용
        document.write("hello");

        // 사용자에게 경고창을 띄움
        window.alert("hi");

        // 브라우저 콘솔에 logging이라는 메시지 출력
        console.log("logging");
    </script>
</body>
</html>
```
- JS HTML 이벤트 연동 예제
```Javascript
<!DOCTYPE html>
<html>
<head>
    <script>
    // 버튼 클릭 시 실행되는 함수
    // id="demo"로 지정된 <p>태그의 내용을 Paragraph changed로 변경함
        function myFunction() {
            document.getElementById("demo").innerHTML = "Paragraph changed.";
        }
    </script>
</head>
<body>
    <h2>Demo JavaScript in Head</h2>
    <p id="demo">A Paragraph</p>
    // 버튼을 클릭하면 myFunction() 함수가 실행됨
    <button type="button" onclick="myFunction()">Try it</button>
</body>
</html>
```

### DOM(Document Object Model)
- DOM이란
  - HTML이나 XML 문서를 객체 형태로 변환하여 다루는 방식
  - 파일을 트리 구조로 인식, 문서의 각 요소를 객체로 다룸
- SAX vs DOM 파서
  - SAX(Simple API for XML)는 특정 태그만 파싱할 때 사용, DOM 파서보다 상대적으로 빠름
    - ex) `<a href="">가 몇 개인가?
  - DOM 파서는 문서 전체 구조를 파악한 후 처리, 속도는 느릴 수 있지만 문서 전체에 대해 작업
- DOM을 통한 문서 구조 변경
  - 브라우저에서 DOM을 사용해 문서 구조를 파악, 실시간 변경 가능
- DOM 특징 및 Virtual DOM
  - HTML DOM
    - HTML 파일이 DOM 파서를 통해 트리 구조로 변환
    - 요소 간 관계를 파악하고, JS를 사용해 조작 가능
  - DOM 단점
    - 트리 변환과 네비게이션이 느린 점이 있음, DOM을 조작하는 데 많은 성능 자원 소모
  - Virtual DOM
    - 실제 DOM을 변경하는 대신, 가상으로 메모리 상에서 DOM을 처리, 변경 사항이 생길 때만 실제 DOM에 반영
    - Double/Triple Buffering과 유사한 방식
  - JSON 변환
    - JSON -> HTML 변환 작업도 DOM을 통해 처리됨
- HTML과 DOM 트리
  - ![image](https://github.com/user-attachments/assets/b79bebe8-bf3a-436a-a417-b0c57563c632)
    - HTML 문서는 트리 구조로 변환됨
    - 각 태그 안 텍스트나 속성도 텍스트 노드와 속성 노드로 표현됨
- XML DOM vs HTML DOM
  - XML은 문서의 구조가 엄격하게 정의되어 있음
  - XML DOM은 DTD나 XML Schema와 같은 문서 구조 정의를 따라야 함
    - 모든 요소가 규칙에 맞는지 트리 형식으로 확인
  - HTML DOM은 유연한 규칙을 따름
    - 웹 페이지 요소를 트리 구조로 파악해 조작가능
- SAX와 DOM
  - DOM 트리 탐색
    - DOM 트리에서 자식, 형제, 부모 노드에 접근할 수 있음
    - `childNodes[0]`은 첫 번째 자식을 가져옴
    - `nextSibling` , `previousSibling`
      - 다음/이전 형제 노드를 가져옴
    - `parentNode`
      - 부모 노드를 가져옴
  - DOM 트리의 마지막 노드
    - 마지막 노드는 반드시 텍스트 노드임
  - `innerHTML`은 DOM API가 아님
    - 표준 API가 아니라 브라우저에 따라 다르게 동작할 수 있는 기능임
    - 따라서 `childNodes`나 `appendChild()`같은 함수를 사용해 요소의 자식을 다루는 것이 권장됨
- DOM 예제
```javascript
<script>
  // 새로운 <p> 요소를 생성
  const para = document.createElement("p");
  // 텍스트 노드 생성, 새 텍스트 추가
  const node = document.createTextNode("This is new.");
  para.appendChild(node); // <p>부모 요소에 새로운 자식 요소 추가</p>
  const element = document.getElementById("div1");
  element.appendChild(para); // <div id="div1">.... <p>This is new.</p></div>
</scripnt>
```
- DOM 요소 선택 메서드
  - `const element = document.getElementById("main");`
    - 특정 ID를 가진 HTML 요소 선택
  - `const element = document.getElementsByTagName("p");`
    - 특정 태그 이름을 가진 모든 요소를 선택
    - 접근은 인덱스 사용 ex) `element[0].innerHTML = "New Content`
  - `const element = document.getElementsByClassName("intro");`
    - 특정 클래스 이름을 가진 모든 요소를 선택
    - 접근은 인덱스 사용
- innerHTML과 nodeValue
  - innerHTML
  - `const myTitle = document.getElementById("demo").innerHTML;`
    - 요소의 전체 내용을 가져오거나 변경 시
    - HTML 태그를 포함하여 콘텐츠 설정
  - `const myTitle = document.getElementById("demo").childNodes[0].nodeValue;`
    - 텍스트 노드의 내용을 가져올 때 사용
    - `childNodes` 배열을 통해 텍스트 노드에 접근
- `documenth.body` 
  - 문서의 `<body>` 요소를 가리킴
- DOM 노드 추가/수정/삭제
```javascript
// 새로운 요소를 생성
const para = document.createElement("p"); 

// 텍스트 노드를 생성하여 요소에 텍스트 추가
const node = document.createTextNode("This is new.");

// 부모 요소에 새로운 자식 요소를 추가
para.appendChild(node); // <p>This is new.</p>
document.body.appendChild(para); // 본문에 추가

document.body.replaceChild(A, B) //기존 노드를 교체
node.remove(); // 요소 삭제
```

- 자바스크립트의 특징
  - 비동기 실행이 기본
    - 파일 로딩이 끝나기 전에 코드 실행
    - 실행 순서 문제 발생 가능
  - 가이드라인
    - `<script>` 태그를 문서의 제일 끝부분에 넣는 것이 권장됨
- 실행순서 보장 및 동기화
  - 콜백함수를 통해 동기화할 수 있음
  - 어떤 작업이 완료된 후 실행될 함수를 미리 지정
- 콜백 지옥
  - 콜백 함수를 연달아 사용하다 보면 코드가 중첩 구조가 깊어져 가독성이 떨어지는 문제

### jQuery
- jQuery의 특징
  - 브라우저 파편화 문제 해결
    - 모든 브라우저에서 동일하게 동작하는 API 제공
  - 비표준 기술
    - 표준이 아니지만js의 기능을 간편하게 사용할 수 있도록 만들어진 라이브러리
  - 비동기 실행 및 편의성 개선
    - `document.getElementById()` -> `$("#id")`
    - `$(document).ready()`
      - 페이지가 완전히 로딩된 후 실행 보장
- jQuery 예제
```javascript
// 페이지가 로딩된 후 실행될 코드를 넣음
$(document).ready(function(){
  // 모든 <p> 요소를 선택하고 해당 요소를 클릭했을 때 alert() 창을 띄우는 이벤트 핸들러
    $("p").click(function(){
        alert("You entered p1!");
    });
});
```

### 동기/비동기
- 동기와 비동기(하드웨어)
  - 동기
    - clock이 동일한 경우
    - 시스템 구성 요소가 같은 clock 속도로 동작
    - 예시
      - CPU, RAM, SSD
    - 고신뢰성
  - 비동기
    - clock이 다른 시스템 간의 통신
    - 예시
      - TCP의 AKC
        - 동기보단 낮은 신뢰성, but 유연성
- 동기와 비동기(소프트웨어)
  - 동기
    - 동기 함수 호출은 함수가 완료될 때까지 프로그램이 기다리는 방식
    - 블록킹
      - 함수가 완전히 끝나기 전까지 프로그램이 멈춘 상태로 대기하는 것
      - 성능 저하나 타임아웃 문제 발생 가능
  - 비동기
    - 함수가 리턴되길 기다리지 않고, 다른 작업 실행 가능
    - 성능 향상 기여 가능, 결과를 예측하기 어려움
- 동기/비동기(성능 및 효율)
  - 동기
    - 동기화 기법 사용, 여러 스레드나 프로세스가 일관성 있는 결과를 내도록 조정 가능
    - 이를 통해 성능과 결과의 안정성을 보장
    - 세마포어, 뮤텍스, 크리티컬 섹션
      - 다양한 동기화 기법을 통해 Thread-safe한 코드 구현 가능
  - 멀티스레딩과 멀티프로세싱
    - 멀티스레딩
      - 동기 기반 스레드 처리 포함
      - 여러 작업을 동시에 처리할 수 있지만 동기화 문제 주의
    - 멀티프로세싱
      - 각 프로세스가 독립적으로 실행
  - 자바스크립트의 비동기 특성
    - 자바스크립트는 기본적으로 비동기적으로 동작
      - 다만, 동시에 동기적 특성도 지원함
- JS의 동기 함수
  - 파일 읽기
    - 비동기 : `fs.readFile()`
    - 동기 : `fs.readFileSync()`
- 비동기 vs 동기
  - Promise(ES7 추가)
    - JS의 비동기 실행을 더 관리하기 쉽게 만들어줌
    - 비동기 작업의 완료 여부와 결과를 표현
    - 예외 처리 코드와 유사한 구조
  - Async/Await(ES8 추가)
    - 사용자가 비동기 또는 동기 실행 방식 선택
    - `async` 키워드는 함수 앞에 붙어 비동기 실행을 나타냄
    - `await` 키워드는 비동기 작업이 완료 될 때까지 기다린 후 다음 코드 실행(동기)
- Javascript Framework
  - SPA(Single Page Application)
    - 하나의 페이지에서 모든 기능을 처리하는 방식, 페이지 전체를 다시 로드하지 않고 필요한 부분만 업데이트
    - 빠른 사용자 경험 제공
  - 주요 JavaScript 프레임워크
    - Angular
      - Typescript 사용
    - React
      - Virtual DOM활용
    - Vue.js
  - Virtual Dom

### Node.js
- Standalone JavaScript
  - 브라우저 외부에서 js 실행할 수 있는 런타임 환경
  - 웹 브라우저 없이 서버에서 자바스크립트를 동작
    - 백엔드 개발에 주로 사용
- 라이브러리 및 엔진
  - 서버 작업 지원
  - 크롬의 V8엔진 기반
- 블로킹, 논블로킹, 비동기
  - 블로킹
    - 파일이나 네트워크 I/O 작업에서 결과가 나올 때까지 기다리는 방식
  - 논블로킹
    - 결과가 아직 나오지 않았더라도 즉시 제어를 반환하여 프로그램이 계속해서 실행
  - 비동기
    - 작업을 호출한 후 다른 일을 계속하다가 작업이 완료되면 알림을 통해 결과를 처리
- 논블로킹은 단순히 즉시 제어를 반환하는 것이라면, 비동기는 결과를 언제 처리할지에 대한 논리적 처리방식까지 포함

### HTTP
- ![image](https://github.com/user-attachments/assets/9be06646-c06b-460d-820d-28935583cc93)
  - http:// 
    - 프로토콜(HTTP)
  - `www.sample.com`
    - 도메인 이름
  - 80 
    - 포트 번호
  - `/a/b.html`
    - 파일 경로
  - HTTP 응답
    - HTTP/1.1 200 OK
      - 서버가 정상적으로 요청을 처리했음을 의미
    - Content-Type: text/html
      - 서버가 브라우저에 반환하는 파일의 MIME 타입 지정
    - Content-Length
      - 서버가 반환하는 HTML 파일의 크기를 바이트 단위로 나타냄
  - HTML 파일 내용이 해석 -> 브라우저가 이를 렌더링
- HTTP 프로토콜
  - 상태 코드
    - 200(정상), 302(리다이렉션), 404(파일 없음), 500(서버 오류)
  - 반드시 헤더와 파일내용 사이에 빈 줄이 들어가야 함
    - `\r\n\r\n` 윈도우
    - `\n\n` 안드로이드
- Content-Length
  - 빈 줄 다음부터 실제 파일의 길이(바이트)
  - 텍스트파일의 경우는 생략 가능(EOF로 파일의 끝을 알 수 있기 때문)
  - 바이너리 vs 텍스트 파일
    - 바이너리 파일
      - 이진 모드로 전송, 파일의 크기를 정확히 알고 있어야 함
    - 텍스트 파일
      - 파일의 끝을 EOF로 예상할 수 있음
- 파일 단위 전송
  - HTTP는 파일을 한 번에 하나씩 전송함
  - 여러 파일을 동시에 요청 시, 각 파일은 각각 GET 요청을 통해 서버로 요청됨
    - ex) .jpg, .css, .js, .html, .png 등
- HTTP 성능 개선
  - 세션 유지
    - HTTP는 세션을 열어 하나의 연결로 여러 파일 다운로드 가능
  - TCP Keep-Alive
    - HTTP 연결을 일정 시간 유지, 기존 연결을 재사용 할 수 있게 함
    - TCP 3-way 핸드셰이크를 반복하지 않음
  - HTTP/1.1
    - 이 버전에서는 여러 개의 파일을 동시에 요청할 수 있음(Multiple GET)
- HTTP 다이나믹 콘텐츠 실행 방법
  - CGI(Common Gateway Interface)
    - 웹 다이나믹 콘텐츠를 실행하는 표준 스펙
    - 멀티프로세스 기반 동작
  - 2세대 실행 아키텍쳐
    - CGI의 성능 문제 해결
    - 멀티스레드 기반으로 웹 어플리케이션을 동작시킴(ex 톰캣)
      - node는 싱글 스레드, 비동기
    - Apache, Tomcat, Node.js를 함께 사용 시 고성능 서버 구축 가능
- HTTP 암호화
  - 7계층(ALS)
    - 응용 프로그램별 암호화 담당
      - ex) shttp
  - 4계층(TLS)
    - 전송 계층에서 암호화
      - ex) HTTPs
  - 3계층(NLS)
    - IP 보안(IPSec)
- HTTP/2
  - 헤더 데이터 압축
    - 텍스트 기반 데이터 압축
  - 멀티플렉싱
    - 하나의 연결로 여러 개의 HTTP 요청 동시 처리
  - HTTPS 의무화
- HTTP/3
  - UDP 기반의 QUIC 프로토콜 사용
    - ZERO RTT
      - 연결 설정 시간을 줄여 빠른 응답
    - 패킷 손실 복구
      - 패킷 손실에 빠르게 대응
    - IP 변경 후에도 연결 유지
      - 사용자가 IP를 변경해도 연결이 끊어지지 않음
  - 영상 스트리밍 등 실시간 데이터 전송에서 큰 개선
- QUIC
  - Quick UDP Internet Connections
  - UDP 기반 새로운 인터넷 전송 프로토콜
    - Zero RTT
      - 새 연결 시 왕복 시간을 기다리지 않음
    - TLS 기본 적용
    - IP 스푸핑 및 재전송 공격 방지
    - 멀티플렉싱
- RPC(Remote Procedure Call)/RMI(Remote Method Invocation)
  - 네트워크를 통헤 서버의 함수를 호출하는 방식
    - 서버와 클라이언트 사이에서 프록시를 사용해 통신
    - 자바에서는 RMI로 구현
  - Stub
    - 클라이언트가 호출할 메서드의 대리자
  - Skeletion
    - 서버 측에서 메서드 호출을 받아 실행
- REST(Representational State Transfer)
  - 웹 서버로 데이터를 전송하거나, 서버에서 데이터를 받아오는 형식
  - HTTP 기반으로 CRUD 작업을 처리
    - PUT, GET, POST, DELETE
  - HTTP/1.1을 기반으로 개발
- gRPC
  - Google에서 개발한 RPC 프로토콜
    - HTTP/2를 기반으로 함
  - 데이터 압축을 통해 바이너리 데이터를 빠르게 전송, REST보다 성능이 뛰어남
  - 프로토콜 버퍼를 사용하여 데이터 직렬화, 더 적은 대역폭으로 데이터 교환
    - 텍스트 -> 바이너리 변환
    - JSON형태로 상호변환 지원
  - 비동기 통신 지원, Multiplexing 기능 제공