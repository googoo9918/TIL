# 딥러닝 분석
## 딥러닝
### 딥러닝
- ![image](https://github.com/googoo9918/TIL/assets/102513932/ec382bb6-d69c-4687-af56-b5180d566c26)
  - 딥러닝과 머신러닝
    - 공통점
      - 손실함수를 최소화
    - 차이점
      - 딥러닝은 머신러닝에 비해 인간의 인지능력에 강점
      - 은닉층 존재 여부
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3ff859fb-0629-4ca2-8afd-b4875a92f1d4)
  - 독립변수(feature; x)들의 선형 및 비선형 결합을 통해 목적변수(종속변수, output; y)를 확률적으로 추정
  - 딥러닝은 은닉층 존재, 머신러닝은 은닉층 업음
### 은닉층
- *특성변수*는 회귀와 분류를 위한 *목적변수*를 예측하는데 이용
  - 특성변수는 *고수준의 대표성*을 지니는 경우가 많음
  - 하지만 주로 이미지나 텍스트 데이터는 *저수준 대표성*을 지님
- 은닉층은 저수준의 대표성을 가진 특성변수를 고수준 대표성을 가진 특성변수로 만드는 역할을 함
  - 입력층에 입력된 특성변수는 여러 개의 은닉층을 통해 고수준의 대표성을 가진 특성변수로 변경된 후 출력층에 전달됨
- 많을 수록 좋은가?
  - 고수준의 대표성을 갖는 특성변수가 학습데이터를 지나치게 대표하여 발생하는 과대적합(overfitting) 문제 발생
  - 선형 결합 모수(파라미터)의 숫자가 늘어나 모수 추정에 실패, 딥러닝모형 자체가 무너지는 형상 발생
    - 여러 은닉층의 모수를 줄이며, 미분사라짐을 차단하는 방향으로 진행해야함

### 딥러닝의 기본 모형
- MLP(multilayer percceptron), CNN(convolutional neural networks), RNN(recurrent neural networks)등 세 가지 기본모형으로 구성
- 딥러닝은 특성변수의 추출을 모형화함
  - 딥러닝 기본모형 이해를 위해서는 추정해야 할 모수의 수를 계산할 수 있어야 함
    - 아마 시험 나올 듯