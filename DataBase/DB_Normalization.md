### 목차
- [데이터베이스 정규화](#데이터베이스-정규화)
  - [Data redundancy](#data-redundancy)
  - [Data integrity](#data-integrity)
  - [Anomaly](#anomaly)
    - [갱신 이상 (update anomaly)](#갱신-이상-update-anomaly)
    - [삽입 이상 (insertion anomaly)](#삽입-이상-insertion-anomaly)
    - [삭제 이상 (deletion anomaly)](#삭제-이상-deletion-anomaly)
## 데이터베이스 정규화
- Database Normalization
- 데이터베이스 정규화는 데이터베이스의 설계와 연관
  - 설계에서 중복을 최소화하게 데이터를 구조화하는 프로세스
  - 목표는 이상이 있는 관계를 재구성하여 작고 잘 조직된 관계를 생성하는 것
### Data redundancy
  - 데이터 중복
    - 데이터 중복은 실제 데이터의 동일한 복사본이나 부분적인 복사본을 뜻함
    - 데이터 복구 시 더 수월할 수 있으나, 문제점을 지니기도 함
      - 일관된 자료 처리의 어려움
      - 저장 공간 낭비
      - 데이터 효율성 감소
### Data integrity
  - 데이터 무결성
    - 데이터의 수명 주기 동안 정확성과 일관성을 유지하는 것
    - 입력된 데이터가 오염되지 않고 입력된 그대로 데이터를 사용할 수 있다는 뜻
    - 데이터 정규화는 데이터 무결성을 강화하기 위한 목적도 지님
### Anomaly
  - 데이터 이상 현상
#### 갱신 이상 (update anomaly)
      - 동일한 데이터가 여러 행에 걸쳐 있을 때 어느 데이터를 갱신해야 하는지에 대한 논리적 일관성 부재 시 발생
      - ![image](https://user-images.githubusercontent.com/102513932/194449125-52d12808-c724-420a-af21-fa33a6296404.png)
        - 중복된 Employee ID중 어떤 데이터를 갱신 해야 하는가?
#### 삽입 이상 (insertion anomaly)
      - 데이터 삽입을 못하는 경우
      - ![image](https://user-images.githubusercontent.com/102513932/194449222-cc265dd1-1fca-4c52-becd-3a8f973265fb.png)
        - Course Code가 NULL이 아닌 이상, 신규 학생은 데이터에 추가되지 못함
#### 삭제 이상 (deletion anomaly)
      - 데이터의 특정 부분을 지울 때 의도치 않게 다른 부분들도 함께 지워지는 현상