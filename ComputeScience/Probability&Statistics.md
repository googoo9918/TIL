## 확률
### 사건
- 1.2.3
  - 생일 문제가 나오면, 윤년(366)을 의식할 것
### 사건의 조합
- 1.3.2 (f)
  - B ∩ A`은 B-A임
- 1.3.4
  - P(A) + P(B) - P(A∩B) <= 1
- 1.3.10
  - 확률이 명확하지 않다면 미지수로 놓고, 전체가 1임을 명심해야함
### 조건부 확률
- 1.4.1 (c)
  - P(B|A∩B) = P(B∩(A∩B))/P(A∩B)
- 1.4.10
  - '모두'와 '이거나'의 차이를 명확히 할 것
  - 교집합과 합집합의 차이
### 사건들의 교집합 확률
- 1.5.1 (c)
  - 순서가 있을 수 있음을 명심하라
- 1.5.5
  - A와 B가 독립인 경우
  - A'과 B'이 독립임을 보일 수 있어야 함
- 1.5.6
  - P(A∪B) = P(A) + P(B) - P(A∩B)
- 1.5.12
  - 7번 던지면서 6의 눈이 한 번도 나오지 않는 경우
    - 여사건이 압도적으로 불리하다
### 사후확률
- 1.6.3 (b)
  - 앞의 소문제에 잠식되지 말 것
### 셈기법
- 1.7.6 (b)
  - 여사건으로 해결해야겠지
- 1.7.7
  - 순서있는 선택과 순서 없는 선택
- 1.7.8
  - 표본은 순서와 상관없음

## 확률변수
### 이산형 확률변수
- 확률질량함수
  - 가로축 x, 세로축 Probability
- 확률분포함수(누적분포함수)
  - 가로축 x, 세로축 F(X)
  - 이산적이기 때문에, 사이의 값은 의미를 갖지 않는다.
  - F(x) = P(X<=x)로 정의된다.
- 2.1.8
  - i=1부터 무한대까지 i^-2의 합은 π^2/6으로 알려져있다.
### 연속형 확률변수
- 확률밀도함수(f(x))
  - 확률질량함수의 연속형 버전
  - 두 값 사이에 존재하는 확률은 확률밀도함수를 적분함에 의해 구할 수 있다.
  - 마찬가지로 확률분포함수(누적분포함수)가 존재한다.
    - F(x) = P(X<=x)로 정의된다.
    - f(x)를 적분한 값
- 2.2.3 (c)
  - F(x)는 연속이어야 함을 명심하라
- 2.2.4
  - 누적분포함수가 주어졌는지, 확률밀도함수가 주어졌는지 잘 확인할 것
- 2.2.5
  - 0<=x<=∞에서, F(0) =0 , F(∞) = 1이다
### 기대값
- 중앙값
  - 연속형 확률변수의 중앙값 X는 누적분포함수 F(x)에 대해 F(x) = 0.5를 만족시키는 x의 값으로 정의된다
- 기대값
  - 확률밀도함수 f(x)를 갖는 연속 확률변수의 기대값은
  - E(X) = ∫xf(x)dx이다
- 좌우대칭
  - 확률밀도함수 f(x)를 갖는 연속형 확률 변수가 한 점 A에 대해 좌우대칭이면
    - 기대값 및 중앙값은 A이다
- 2.3.6
  - 순수 기대 금액을 구할 떄는 +-가 같이 계산되어야 함

### 분산
- 분산
  - 제평평제 or E((X-E(X))^2)
- 체비세프 부등식
  - 평균이 μ, 분산이 σ^2인 확률변수 X에 대해
  - **P(μ-cσ<=X<=μ+cσ)>= 1-1/c^2**
  - 이때, c>=1
- 확률변수 X의 분위수는 F(x) = p
- 2.4.4
  - 하한 사분위수 = 0.25, 상한 사분위수 = 0.75
- 2.4.7
  - 체비세프 부등식 사용 문제, 다시 풀어볼 것

### 결합확률변수
- F(x,y) = P(X<=x, Y<=y)
- 결합확률질량함수와 주변확률질량함수를 구분할 수 있어야 함
  - 주변확률질량함수는 P_i+(열), P_+j(행)등으로 나타남
- 확률변수들의 독립
  - p_ij = Pi+ * p_+j 이거나, f(x,y) = fx(x)fy(y)인 경우 독립임
- 공분산
  - Cov(X,Y) = E(XY) - E(X)E(Y)임
  - 어떻게 함께 변하는지에 대한 지표
  - 양수: 증가 -> 증가
  - 음수: 증가 -> 감소
  - 0: 상관X
- 상관
  - Corr(X,Y) = Cov(X,Y)/(Var(X)*Var(Y))^1/2
  - -1~1의 값을 가짐
  - 1에 가까운 경우 양의 상관
    - 큰 값을 가지면 다른 변수도 큰 값을 가지는 경향
  - -1에 가까운 경우 음의 상관
    - 큰 값을 가지면 다른 변수는 작은 값을 갖는 경향
- 2.5.3 다시 풀어볼 것
- 2.5.5 다시 풀어볼 것

### 확률변수들의 조합 및 함수
- Var(X1+X2) = Var(X1) + Var(X2) + 2Cov(X1,X2)
- 독립인 경우
  - Var(X1+X2) = Var(X1) + Var(X2)
- 확률변수의 선형 결합
  - 확률 변수가 상호 독립일 떄
  - Var(a1X1 + .... + a_nXn + b) = a1^2Var(X1) + .... + a_n^2Var(Xn)
- 독립인 확률변수들의 산술 평균
  - K = (X1+....+Xn)/n
    - Var(K) = σ^2/n
- 2.6.1 (b)
  - 생각해보면 당연함
- 2.6.5
  - 선형 결합과 산술 평균을 잘 구분해야함
- 2.6.7
  - 특히 (c)를 잘 풀어볼 것
  - Fx(x) = P(X<=x)임을 명심하라
- 2.6.9 (c)
  - 선형결합임을 명심하라
- 2.6.13
  - 산술 평균과 선형 결합의 차이!!
- 2.6.15
  - 산술 평균인가 선형 결합인가?