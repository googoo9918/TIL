## WEB, WAS, DMZ
- ![Image](https://github.com/user-attachments/assets/ecc86085-292d-41e6-82c7-e3fd36b7c88f)

1.	Internet
- 인터넷을 통해 조직 내부 네트워크에 접근
2.	Gateway Router
- 인터넷에서 들어오는 네트워크 트래픽을 내부 네트워크 또는 DMZ로 라우팅
- Firewall과 함께 배치, 네트워크 보호
3.	DMZ
- 외부에서 접근할 수 있는 서버를 배치하는 네트워크 영역
    - 내부 네트워크를 보호하면서 외부 서비스(웹, DNS 서버)를 제공
- Firewall
    - DMZ 내의 서버에 대한 접근 제한, 불필요한 트래픽 차단
- VPN Server
    - 원격 사용자가 보안 터널을 통해 내부 네트워크에 접근할 수 있도록 지원
    - VPN을 통해 내부 리소스에 안전하게 연결
- DNS Server
    - 도메인 이름을 IP 주소로 변환할 수 있도록 지원
- Web Server
    - 웹 애플리케이션을 이용할 수 있도록 제공
    - DDOS 공격 해킹 등에 대한 보안 중요
4.	Internal Network
- 외부 접근 차단
- DMZ 서버를 통해서만 인터넷과 통신
- Firewall
    - DMZ와 내부 네트워크 사이에서 이중 보안 레이어 역할
    - 내부 네트워크 직접 접근 방지
- Internal Router
    - 내부 네트워크 트래픽 관리 및 최적화
- DNS Server
    - 내부 시스템 도메인 기반 통신
    - 내부 전용 리소스 관리
5.	예제 상황
- 원격 직원(외부)이 VPN 서버를 통해 내부 네트워크에 접속 후, 내부 시스템에서 데이터를 조회
    - 원격 직원 -> VPN 서버 연결 요청
        - 원격 직원이 VPN 클라이언트 실행, VPN 서버에 접속 시도
        - VPN 클라이언트가 외부 DNS 서버에 `vpn.example.com`의 IP 요청
        - DMZ DNS 서버가 vpn.exapmle.com -> 203.0.113.10(VPN Server) IP 주소 반환
        - 원격 직원이 VPN 서버에 연결 요청 전송
        - 사용자 인증 수행 후 내부 네트워크 접속 허용
    - VPN을 통해 내부 네트워크 접속
        - VPN 서버가 원격 직원에게 내부 IP(10.0.0.100) 할당
        - VPN 터널 생성, 내부 네트워크와 직접 할당된 것처럼 작동
-	원격 직원이 내부 시스템에 접속하여 데이터를 조회
    - 내부 시스템 접근을 위한 DNS 조회
        - 원격 직원이 사내 포털(intranet.example.com)에 접속
        - VPN 연결 상태에서 내부 DNS 서버에 `intranet.example.com`의 IP를 요청
        - 내부 DNS 서버가 `intranet.example.com -> `10.0.0.50(WAS)로 응답`
        - 원격 직원이 사내 포털(10.0.0.50)에 접속
    - 내부 시스템의 DB 요청
        - WAS는 내부 DB Server에 데이터 요청
        - 데이터 조회 후 WAS로 반환
        - WAS가 웹 페이지 응답 반환
-	인터넷 사용자가 `example.com`에 접속, DMZ 내 Web Server에서 데이터 요청, Web Server는 Internal DB Server에서 데이터 조회 후 사용자에게 응답
    - DNS 조회 및 웹 서버 접근
        - 인터넷 사용자가 브라우저에서 `https://example.com`에 접속
        - 사용자의 브라우저가 외부 DNS 서버(DMZ)에 `example.com`의 IP 요청
        - 외부 DNS 서버(DMZ)가 `example.com` -> `203.0.113.20(Web Server)로 응답`
        - 사용자의 브라우저가 Web Server(DMZ)에 HTTP 요청을 보냄
    - Web Server가 내부 DB에서 데이터 조회
        - DMZ -> 내부 네트워크는 방화벽 정책에 의해 제한
            - 따라서 방화벽에서 특정 포트만 허용
            - 방화벽 -> Internal Router -> DB Server로 전달
            - 데이터 조화 후 Web Server로 응답 반환
        - 사용자에게 최종 응답 반환
            - 응답 패킷이 DMZ -> 외부 방화벽 -> Gateway Router -> 인터넷 사용자 브라우저로 전달
