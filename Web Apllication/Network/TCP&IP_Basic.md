### 목차
- [TCP/IP 기본](#tcpip-기본)
  - [네이티브 애플리케이션 & 웹 애플리케이션](#네이티브-애플리케이션--웹-애플리케이션)
    - [네이티브 애플리케이션](#네이티브-애플리케이션)
    - [웹 애플리케이션](#웹-애플리케이션)
  - [LAN과 WAN](#lan과-wan)
  - [인터네트워킹(InterNetworking)](#인터네트워킹internetworking)
  - [프로토콜(protocol)](#프로토콜protocol)
  - [TCP /IP](#tcp-ip)
  - [주소(address)](#주소address)
    - [IP주소](#ip주소)
    - [MAC 주소](#mac-주소)
  - [패킷](#패킷)
## TCP/IP 기본

### 네이티브 애플리케이션 & 웹 애플리케이션
#### 네이티브 애플리케이션
- 특정기기에 설치해서 사용하는 애플리케이션
  - Native - application
  - Apple ios, Android OS, Windows 같은 특정 실행환경에 종속
- 장점
  - 웹애플리케이션보다 빠름
  - 설치된 기기의 시스템 / 기기의 리소스에 접근 용이
    - ex) gps, 카메라
  - 인터넷 없이 사용도 가능
  - 웹애플리케이션에 비해 안전함
- 단점
  - 웹애플리케이션에 비해 개발비가 더 들어감
    - ex) ios와 안드로이드의 멀티 플랫폼 개발
  - 빠른 업데이트가 힘듬
  - 앱스토어 승인 받기 어려움, 추가 비용 발생

#### 웹 애플리케이션
- 웹 브라우저를 통해 접근이 가능한 어플리케이션
  - 정적인 웹사이트에 비해, 다양한 동적인 응답이 가능
    - 웹 브라우저라는 소프트웨어를 통해 가능하게 함
- 장점
  - 브라우저를 통해 실행, 설치X 다운로드X
  - 유지관리 용이
  - 제작 비교적 간편
  - 스토어 승인 필요X
- 단점
  - 인터넷 필수
  - 속도 비교적 느림
  - 사용자 접근성 떨어짐 (스토어X)
  - 보안상 위험에 노출되기 쉬움
  - 
### LAN과 WAN
- LAN(Local Area Network)
  - 비교적 좁은 범위에서 연결
- WAN(Wide Area Network)
  - 수많은 LAN이 모여 구성

### 인터네트워킹(InterNetworking)
- 네트워크끼리 연결하는 네트워크
- 네트워크 확장 방식
  - 한 네트워크 확장
  - 네트워크와 네트워크 연결
    - 인터네트워킹
- 장점
  - 네트워크 일부에서 고장이 나도 영향이 퍼지지 않음
  - 불필요한 통신이 네트워크 전체로 확산하지 않음
  - 개별 네트워크를 각각의 방침에 따라 관리 가능
- 전 세계적으로 인터네트워킹 -> 인터넷

### 프로토콜(protocol)
- 컴퓨터 내부, 컴퓨터 사이에서 데이터의 교환 방식을 정의하는 규칙 체계
- 형식을 정의하는 규칙의 집합 : 프로토콜
- 신호 처리, 오류처리, 암호, 인증, 주소 등 포함

### TCP /IP
- TCP(Transmission Control Protocol)
  - 두 개의 호스트를 연결하고 데이터 스트림을 교환하게 해주는 네트워크 프로토콜
  - 데이터와 패킷이 보냊진 순서대로 전달하는 것을 보장
- IP(Internet Protocol)
  - 송신 호스트와 수신 호스트가 패킷 교환 네트워크에서 정보를 주고받는 데 사용하는 정보 위주 규약
![image](https://user-images.githubusercontent.com/102513932/193191699-25462209-94c9-4e04-87b4-577ec0a30daf.png)
![image](https://user-images.githubusercontent.com/102513932/193173323-c62f98b1-8b6b-4415-9b57-d71990d2d0cd.png)
![image](https://user-images.githubusercontent.com/102513932/193173363-d8f0b675-6304-41c7-a64b-9adf0326d5be.png)

### 주소(address)
- 네트워크 상에서 교류하기 위해서는 주소를 알아야 함.
- 특정 pc의 주소 나타내는 체계 : IP address(Internet Protocol, IP 주소)

#### IP주소
- 컴퓨터 네트워크에서 장치들이 서로를 인식하고 통신 하기 위해 사용하는 특수한 번호
- 컴퓨터, 핸드폰, 서버, 인터넷 라우터 등 각각의 네트워크 장비에 할당
- private / public
  - private은 Lan 내부에서 사용
  - public은  인터넷에서 사용
- IPv4(Internet Protocol version)
  - ip 주소체계의 네 번째 버전
  - 터미널에서 nslookup을 이용해 확인 가능
    - 도메인 네임을 통해 IP 주소 확인
    - ex) nslookup codestates.com
- localhost, 127.0.0.1 : 현재 사용 중인 로컬 pc 지칭
- 0.0.0.0 , 255.255.255.255 : broadcast address
  - 로컬 네트워크에 접속한 모든 장치와 소통하는 주소
  - 서버에서 접근 가능 ip 주소를 broadcast address로 지정시, 모든 기기에서 서버 접근 가능
- ![image](https://user-images.githubusercontent.com/102513932/193179906-cfbba556-02b9-41b8-883e-36808bf61b15.png)
  - 개인 pc 보급의 증가로 IPv4로 할당할 수 있는 PC가 한계를 넘어서게 됨
    - 점점 IPv6로 넘어가는 추세이지만, 현실적으로 녹록치 않음
  - IPv6은 표기법을 달리 책정, 2^128개의 IP 주소 표현 가능

#### MAC 주소
- 네트워크 기기는 제조사에서 할당하는 고유 시리얼 존재, MAC주소
- MAC 주소와 IP 주소 조합시 네트워크 통신 가능
  - 이더넷에서는 네트워크 송수신 상대 특정으로 MAC 주소 사용
  - TCP/TP 에서는 IP address 사용
- 같은 LAN에 속한 기기끼리 통신 시 상대방의 MAC 주소를 파악해야 함.
  - 이때 사용하는 것이 ARP(address resolution portocol) 
    - MAC 주소 파악을 위해 네트워크 전체에 브로드캐스트를 통해 패킷 전송
    - 해당 IP를 갖고 있는 컴퓨터가 자신의 MAC 주소를 Response, 통신을 가능케 하는 프로토콜 
    - ![image](https://user-images.githubusercontent.com/102513932/193181158-9bddfbdf-7603-4a3a-bcc1-8bf701fd3380.png) 

### 패킷
- 패킷 방식의 컴퓨터 네트워크가 전달하는 데이터의 형식화된 블록

- 회선 교환 
  - 하나의 회선을 할당받아 데이터를 주고받는 방식
    - 주로 음성전화 시스템 사용
    - 일대일로 데이터 교환
  - ![image](https://user-images.githubusercontent.com/102513932/193181864-2cfc3f72-82f8-46ea-b18b-4a24a2e184b0.png)
- 패킷 교환 
  - 데이터를 패킷단위로 쪼개서 전송하는 방식
    - 중간 라우터들을 거치며 store and forward 방식을 따름
    - ![image](https://user-images.githubusercontent.com/102513932/193181880-4a5b4a59-24a3-49e6-b237-5064213558b8.png)
  - ![image](https://user-images.githubusercontent.com/102513932/193182143-9de4389c-1d26-410b-b228-f2161f998f67.png)
    - 데이터 -> 패킷 단위
    - 패킷 = 헤더 + 페이로드
      - 헤더
        - 데이터의 정보(source, index, dest,...)