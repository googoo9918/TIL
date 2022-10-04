## TCP & UDP

### TCP/IP 4계층 모델
![image](https://user-images.githubusercontent.com/102513932/193191699-25462209-94c9-4e04-87b4-577ec0a30daf.png)
![image](https://user-images.githubusercontent.com/102513932/193173323-c62f98b1-8b6b-4415-9b57-d71990d2d0cd.png)

- TCP와 UDP는 2계층에서 동작하는 IP와 4계층에서 동작하는 애플리케이션(http 등)을 중개하는 역할
![image](https://user-images.githubusercontent.com/102513932/193192066-a4d2534e-7922-4182-8a05-68c5f623ff86.png)

- TCP는 통신 신뢰성을 높이는 실현 기능이 구현되어 있음
  - 데이터의 신뢰성을 중시 시
- UDP는 신뢰성을 높이는 기능이 없는 대신, 높은 속도와 효율성 제공
  - 빠른 속도나 실시간 통신 중시 시

### TCP 3-way handshake
![image](https://user-images.githubusercontent.com/102513932/193192730-4a73d4fe-fa01-48f8-a501-8c95a7a46a73.png)
1. SYN : 처음으로 sender은 receiver와 연결 설정을 위해 segment를 랜덤으로 설정된 SYN(Synchrnize Sequence Number)과 함께 보냄. 이 요청은 receiver에게 sender가 통신을 시작하고 싶다고 알림
2. SYN/ACK : receiver는 받은 요청을 바탕으로 SYN/ACK 신호 세트 응답. ACK(acknowledgement) 응답으로 보내는 segment가 유효한 SYN요청을 받았는지 의미
3. ACK : 마지막 단계에서, sender는 받은 ACK를 reciever에게 전송, 신뢰성 연결이 성립되었다는 사실을 양쪽에서 알 수 있음, 실제 데이터 전송 시작

### UDP
- 애플리케이션의 정교한 제어 가능
  - TCP의 경우 전송 받을 준비가 될 때까지 세그먼트를 반복적으로 재전송
  - UDP는 latency를 줄이기 위해 데이터 손실을 감수
- 연결 설정에 무관
  - TCP 3-way와 달리, udp는 예비과정 없이 바로 전송 시작
    - 설정지연이 없으니 반응속도 향상
  - TCP가 신뢰성을 위해 많은 파라미터와 정보 전달이 필요함과 달리, UDP는 연결설정 관리를 하지 않기 때문에 어떠한 파라미터도 기록하지 않음
    - 서버에서도 TCP와 비교해 더 많은 클라이언트를 수용 가능