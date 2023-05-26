### 3
- mmu가 없고 다 physical memory를 쓰면 프로그램 배포 조차 안됨
- virtual memory 도입으로 address pace와 physical layout을 분리해서 보게 됨
- 각자 어디 위치하는지
- 당연히 같지 않겠지

### 4
- 왜 쓰냐?
  - main memory 효율성 증가
    - 메모리 관리를 효율적으로 할 수 있음
    - process별로 겹치지 않게 해주는..
    - DRAM을 cache의 형태로 쓸 수 있게..
      - 추후 상세 설명 예정
  - 메모리 관리를 효율적으로 할 수 있음
    - 각 process가 바라보는 0번지와 kernel영역 space를 나누는게 훨씬 쉬워짐
  - address space 분리
    - process가 서로의 영역을 침범하지 못하도록..
      - 영역을 침범하려고 하면 MMU 단에서 차단해버림

### 5
- 제대로 못들음
- 2번째랑 3번째랑 모순?


### 6
- ㅠㅠ 다시 듣기


### 7
- 실제로 64에서 쓸 수 있는 address bit가 뗄거 다 떼면 48 bit밖에 안됨
- 아키텍쳐 마다 space도 다름
  - 같은 아키텍쳐에서 버전마다 다르기도 함
### 8
- virtual address가 보통 더 큼
- physical address는 다를 수 있다..정도