## 목차
- [딥러닝 분석](#딥러닝-분석)
  - [딥러닝](#딥러닝)
    - [딥러닝](#딥러닝-1)
    - [은닉층](#은닉층)
    - [딥러닝의 기본 모형](#딥러닝의-기본-모형)
  - [Neural Networks](#neural-networks)
    - [Single Layer Neural Networks](#single-layer-neural-networks)
    - [Activation Function](#activation-function)
    - [Activation Function and Fitting](#activation-function-and-fitting)
    - [Multilayer Neural Network](#multilayer-neural-network)
    - [Lab in Python](#lab-in-python)
  - [Convolutional Nerual Networks(CNN)](#convolutional-nerual-networkscnn)
    - [How Work](#how-work)
    - [컨볼루션 계층(Convolution Layer)](#컨볼루션-계층convolution-layer)
    - [Pooling Layser](#pooling-layser)
    - [Architecture of CNN](#architecture-of-cnn)
    - [Data Augmentation(데이터 증강)](#data-augmentation데이터-증강)
    - [Lab in Python](#lab-in-python-1)
  - [Recurrent Nerual Networks](#recurrent-nerual-networks)
    - [Lasso logisitc regression vs Two-calss Neural Network](#lasso-logisitc-regression-vs-two-calss-neural-network)
    - [RNN(Recurrent Neural Networks)](#rnnrecurrent-neural-networks)
    - [RNN Architecture](#rnn-architecture)
    - [RNN and IMDB Reviews](#rnn-and-imdb-reviews)
    - [Time Series Forecasting(시계열 예측)](#time-series-forecasting시계열-예측)
    - [RNN Forecaster](#rnn-forecaster)
    - [RNN Results for NYSE Data](#rnn-results-for-nyse-data)
    - [Autoregression Forecaster](#autoregression-forecaster)
    - [Lab in Python](#lab-in-python-2)
  - [Fitting Neural Networks](#fitting-neural-networks)
  - [Tensorflow](#tensorflow)
  - [회귀(Regression)](#회귀regression)
  - [분류(Classfication)](#분류classfication)
  - [Single Layer Neural Network](#single-layer-neural-network)
    - [MNIST Digits](#mnist-digits)
    - [Lab in Python](#lab-in-python-3)
  - [CNN](#cnn)
    - [Lab in Python](#lab-in-python-4)
  - [RNN](#rnn)
    - [Lab in Python](#lab-in-python-5)
  - [Convolution Neural Network(CNN)](#convolution-neural-networkcnn)
    - [특징 추출](#특징-추출)
    - [주요 레이어 정리](#주요-레이어-정리)
    - [Convolution 레이어](#convolution-레이어)
    - [Dropout 레이어](#dropout-레이어)
    - [Fashion MNIST 데이터셋에 적용](#fashion-mnist-데이터셋에-적용)
    - [퍼포먼스 높이기](#퍼포먼스-높이기)
    - [더 많은 레이어 쌓기](#더-많은-레이어-쌓기)
    - [이미지 보강(Image augmentation)](#이미지-보강image-augmentation)
  - [순환 신경망(RNN, Recurrent Neural Network)](#순환-신경망rnn-recurrent-neural-network)
    - [SimpleRNN 레이어](#simplernn-레이어)
    - [LSTM 레이어](#lstm-레이어)
    - [GRU 레이어](#gru-레이어)
    - [Embedding 레이어](#embedding-레이어)
    - [긍정, 부정 감정 분석](#긍정-부정-감정-분석)
    - [자연어 생성](#자연어-생성)
  - [오토인코더](#오토인코더)
  - [K-평균 클러스터링](#k-평균-클러스터링)
  - [강화 학습](#강화-학습)
  - [큐러닝(Q-Learning)](#큐러닝q-learning)
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
  - 딥러닝은 은닉층 존재, 머신러닝은 은닉층 없음
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

## Neural Networks
### Single Layer Neural Networks
- ![image](https://github.com/googoo9918/TIL/assets/102513932/ab59dea5-d08b-42e0-826f-a84ab866e476)
  - 예측 함수
    - Y = f(X)
      - 입력 벡터 X로부터 응답 Y를 예측함
      - 비선형 함수(Non-linear)
    - Y
      - 응답 또는 예측하고자 하는 대상
    - X
      - p개의 변수를 포함하는 입력 벡터
  - 단일층 신경망 모델(Single layer Neural Network Model)
    - f(X) = β0 + ∑ᵏ₌₁ βk hk(X) = β₀ + ∑ᵏ₌₁ βₖ g(wₖ₀ + ∑ⱼ₌₁ᵖ wₖⱼ Xⱼ) 
      - 비선형 함수 f(X)는 바이어스 항 β0, 가중치 βk, 은닉층 활성화 함수 hk를 사용하여 계산
      - β0
        - 바이어스 항(절편)
        - 활성화 함수 출력에 더해져 최종 예측에 영향을 미침
      - βk
        - 은닉 레이어 -> 출력 레이어로 가는 연결의 가중치
      - wₖ₀
        - 은닉 레이어의 각 유닛에 대한 편향(바이어스 항)
      - wₖⱼ
        - 입력 레이어 -> 은닉 레이어로 가는 연결의 가중치
      - K
        - 은닉 유닛(은닉 노드)의 수를 나타냄
      - p
        - 입력 노드의 수를 나타냄
      - g(z)
        - 미리 지정된 비선형 활성화 함수
          - ex) 시그모이드, ReLU
    - 각 은닉 노드마다 하나씩 총 5개의 βk 가중치, 출력 노드의 편향 β0 추가 시 총 6개의 β 가중치 존재
    - 각 연결에는 고유의 가중치 wₖⱼ 존재, 4개의 입력 노드와 5개의 은닉 노드 사이에는 총 20개의 wₖⱼ 가중치 존재
      - 은닉층의 각 노드에는 자체 편향 wₖ₀이 있으므로, 5개의 은닉 노드에 각각 하나씩 총 5개의 편향이 있음
    - 총 31개의 학습 파라미터(모수) 존재
      - 20(입력-은닉 가중치)(wₖⱼ) + 5(은닉-출력 가중치)(βk) + 5(은닉 층 편향)(wₖ₀) + 1 (출력 층 편향)(β0)
      - 그냥 ( (4+1) * 5 ) + ( (5+1) *1) 이라고 생각하는게 나을듯
### Activation Function
- ![image](https://github.com/googoo9918/TIL/assets/102513932/9726ac03-abaa-47f9-8800-9f7fa7778fce)
  - A_k = h_k(X) = g(w_k0 + ∑ᵢ₌₁ᵖ w_kj X_j)
    - 활성화 함수는 신경망에서 입력 X를 받아 은닉 노드의 출력 A_k를 생성하는데 사용됨
    - g(z)는 활성화 함수(activatin function)
    - 활성화 함수
      - 시그모이드(sigmoid)
        - 1 / (1 + e^(-z))
        - (0,1) 사이의 출력을 가짐 
      - ReLU
        - max(0, z)
      - ReLU가 시그모이드에 비해 더 효율적으로 계산될 수 있고, 더 선호되는 추세
### Activation Function and Fitting
- ![image](https://github.com/googoo9918/TIL/assets/102513932/0040b37b-175a-4a6f-bc4e-de0b14949b02)
  - 활성화 함수는 주로 은닉층에서 사용
    - **비선형성**을 도입, 모델이 단순한 선형 모델을 넘어 복잡한 데이터 구조를 학습할 수 있도록 함
  - β_1, β_2는 활성화 함수의 계수, w는 활성화 함수에 대한 가중치
    - 사진의 수식을 잘 이해해 볼 것
  - 모델의 적합성은 관측된 데이터 Y_i와 모델에 의해 예측된 f(X_i)사이의 차이를 최소화 함
    - 회귀 문제에서는 오차의 제곱합을 최소화함
    - 활성화 함수를 통한 출력값은 기존 특성의 비선형 조합, 파생된 특성과 같은 역할을 하며 모델의 표현력을 증가시킴

### Multilayer Neural Network
- 다중 신경망
  - 여러 개의 은닉층을 가짐
  - 각 은닉층에 많은 수의 유닛 존재
  - 단일 은닉층을 가진 신경망이라도, 많은 수의 유닛을 가질 경우 대부분 함수를 근사(approximate)할 수 있음
    - 다만, 다중 신경망을 사용하는 것이 더 쉬움
      - 각각의 층이 상대적으로 적당한 크기를 가진다면, 신경망은 특징의 다양한 수준을 추출하고 복잡한 함수를 더 쉽게 학습할 수 있음
- Example: MNIST Digits
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/647190b6-70cc-4ffe-b751-16b16fac1c97)
  - MNIST 데이터셋은 기계 학습 분야에서 가장 기본적인 데이터셋
    - 손으로 쓴 숫자들의 큰 집합을 포함함
    - 특성
      - 28*28(=784) 픽셀의 grayscale 이미지
        - 이는 입력 벡터(X)로 사용됨 
        - 각 픽셀의 값은 0부터 255까지의 값을 가질 수 있음
      - 훈련 데이터로는 60,000(60k)이미지, 테스트 데이터로는 10,000개의 이미지
    - 라벨(출력 벡터Y)
      - 출력 벡터는 10개의 더미 변수를 가지고 있음, 이는 0부터 9까지의 숫자 클래스에 각각 대응됨
- ![image](https://github.com/googoo9918/TIL/assets/102513932/1a83a44b-cc70-4a8c-a623-2685223b8ae0)
  - 입력층은 각 픽셀 값을 나타내는 784개의 뉴런
  - 첫 번째 은닉층은 256개의 유닛으로 구성
  - 두 번째 은닉층은 128개의 유닛으로 구성
  - 출력층은 10개의 유닛
    - 각 유닛은 0부터 9까지의 MNIST 숫자 클래스 중 하나에 해당됨
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3379fcbb-d831-4b48-a20d-733fa62f6afd)
  - 첫 번째 은닉층
    - 256개의 유닛
    - 가중치 행렬 W_1의 차원은 785 * 256
      - 785는 입력 레이어의 784 픽셀에 바이어스 유닛을 추가한 것
  - 두 번째 은닉층
    - 128개의 유닛
      - 가중치 행렬 W_2의 차원은 257 * 128
        - 257은 첫 번째 은닉층의 256 유닛에 바이어스 유닛을 추가한 것
  - 출력층
    - m은 0부터 9까지의 숫자 클래스 의미
    - Z_m은 다른 선형 모델을 나타냄
    - 가중치 행렬 B의 차원은 129*10임
      - 129는 두 번째 은닉층의 128 유닛에 바이어스 유닛을 추가한 것
  - 총 매개변수
    - 총 235,146의 매개변수가 있음
- Input Data for MLP
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/388a0014-00d0-448f-91a7-dc52c2f4b723)
  - 샘플
    - 개별 데이터포인트
    - ex) 이미지, 문서, 관측값
  - 특성
    - 샘플의 속성이나 특성을 나타냄
    - ex) 이미지의 픽셀 값, 문서의 단어 빈도
  - Samples 개수와 parameter는 아무 연관이 없음
    - 샘플 수가 많아져도 신경망 구조가 같다면 파라미터 수는 동일함
### Lab in Python
- 데이터 준비
```python
# HittersModel 클래스 정의
class HittersModel(nn.Module):
  def __init__(self, input_size): 
    super(HittersModel, self).__init__()
    # 입력 데이터 1차원 배열로 평탄화
    self.flatten = nn.Flatten() 
    self.sequential = nn.Sequential(
      # 입력 차원을 50개의 뉴런으로 매핑 
      nn.Linear(input_size, 50),
      # 비선형 활성화 함수로 ReLU 사용
      nn.ReLU(), 
      # 과적합 방지
      nn.Dropout(0.4),
      # 50개 뉴런을 최종 출력인 1차원으로 매핑
      nn.Linear(50, 1)) 
  def forward(self, x): 
    # 입력을 평탄화
    x = self.flatten(x)
    # sequential 적용 후 결과를 flatten
    return torch.flatten(self.sequential(x))

# Data를 로드하고 관리하는데 사용되는 SimpleDataModule
# hit_train은 훈련 데이터
# hit_test는 테스트 데이터셋
## 완전한 모델 개발이 끝난후 모델 성능 최종 평가에 사용됨
# batch_size=32는 각 데이터 로더에서 한 번에 로드할 데이터 수를 설정
# num_workers = min(4, max_num_workers)는 데이터 로딩에 사용될 병렬 처리 워커의 수를 지정
## 로딩 속도를 향상시키기 위해 사용됨
# validation=hit_test는 검증 데이터셋 지정
## 모델 학습 과정 중 모델을 평가하고 조정하는 데 사용(과적합 감지, 조기 종료 등)
hit_dm = SimpleDataModule(hit_train, hit_test,
   batch_size=32, num_workers=min(4, max_num_workers),
   validation=hit_test)

# 회귀 모델 설정, 회귀 작업에 적합한 손실 함수와 최적화 설정을 포함
# hit_model은 HittersModel의 인스턴스, 실제 신경망 구조임
# 모델 성능 평가를 위해 평균 절대 오차(MAE)를 메트릭으로 사용함
hit_module = SimpleModule.regression(hit_model, metrics={'mae':MeanAbsoluteError()})

# 모델의 훈련 과정을 관리함
# 동일한 코드와 동일한 데이터로 여러 번 훈련되더라도 같은 결과를 내도록 하는 설정
# 최대 50 에폭 동안 모델을 훈련함
## 에폭은 전체 훈련 데이터셋이 모델을 통과하는 횟수임
# 5개의 훈련 스텝마다 로그 기록
# 로그를 기록하는 로거 객체
# 콜백은 훈련 중 특정 이벤트가 발생했을 때 호출되는 함수 집합
## ErrorTracker()는 오류를 추적함
hit_trainer = Trainer(deterministic=True,
              max_epochs=50, 
              log_every_n_steps=5, 
              logger=hit_logger, 
              callbacks=[ErrorTracker()]) 
# 모델 훈련을 시작함
# hit_moudle은 위에서 회귀 모델을 설정한 것, hittersModel의 인스턴스를 지님
# datamodule은 SimpleDataModule을 통해 설정, 데이터를 제공하는 데이터 모듈
## 훈련, 검증, 테스트 데이터셋을 로딩하고 처리
# SGD(확률적 경사 하강법)은 데이터셋의 일부만을 사용, 각 단계에서 그리디언트 계산하고 모델 매개변수를 업데이트함
# 에폭 계산, 에폭은 훈련 데이터셋을 한 번 순회하는 것을 의미함 hit_dm에서 batch_size를 32로 지정했으므로, 한 에폭은 175/32 = 5.5 SGD 단계임 --> 총 6번의 단계가 필요하게 됨 
hit_trainer.fit(hit_module, datamodule=hit_dm)

# 테스트 데이터를 통해 성능 평가
hit_trainer.test(hit_module, datamodule=hit_dm)

# 모델을 평가 모드로 설정, 드롭아웃과 같은 레이어 비활성화
# HittersModel의 인스턴스임을 명심하라
hit_model.eval()
```


- Multilayer Network on the MNIST Digit Data
```python
class MNISTModel(nn.Module):
  def __init__(self):
    super(MNISTModel, self).__init__()
    self.layer1 = nn.Sequential(
      # 1*28*28의 이미지를 28*28 요소의 1D 벡터로 평탄화, 784개의 feature을 갖는 벡터 생성됨
      nn.Flatten(),
      # 평탄화된 벡터를 256차원의 히든 레이어로 선형 변환
      # 이때 파라미터 --> 785 * 256 = 200,960
      nn.Linear(28*28, 256),
      # 활성화 함수
      nn.ReLU(),
      # 과적합 방지 드롭아웃
      nn.Dropout(0.4))
    self.layer2 = nn.Sequential(
      # 256 -> 128
      # 257 * 128 = 32,896
      nn.Linear(256, 128),
      # 활성화 함수
      nn.ReLU(),
      # 과적합 방지 드롭아웃
      nn.Dropout(0.3))
    self._forward = nn.Sequential(
            self.layer1,
            self.layer2,
            # 128 -> 10
            # 129 * 10 = 1,290
            nn.Linear(128, 10))
  def forward(self,x):
    return self._forward(x)

# data 로드 및 관리
mnist_dm = SimpleDataModule(mnist_train,
            # 학습 데이터 세트 중 20%를 검증 데이터로 분류
            mnist_test, validation=0.2, 
            # 데이터 로딩 시 사용할 병렬 처리 작업자의 수
            num_workers=max_num_workers, 
            # 한 번에 네트워크로 전달되는 데이터 샘플의 수
            batch_size=256)

# 분류 모델 설정, 내부적으로 크로스 엔트로피 손실 함수를 사용
# mnist_model은 MNISTModel() 인스턴스
# num_classes는 분류 모델이 최종적으로 생성하는 출력을 설정
mnist_module = SimpleModule.classification(mnist_model, num_classes =10)

# 모델의 훈련 과정 관리
# 이전 모델과 별반 다를 것 없음
mnist_trainer = Trainer(deterministic=True,
                max_epochs=30,
                logger=minst_logger,
                callbacks=[ErrorTracker()])

# 모델 훈련
mnist_trainer.fit(mnist_module, datamodule=mnist_dm)

# 테스트 데이터를 통한 성능 평가
mnist_trainer.test(mnist_module, datamodule=mnist_dm)
```
- Softmax는 입력의 상대적 차이에 의존
  - 과적합 문제를 지니고 있음
  - 정규화, 확률적 경사 하강법(SGD)로 해결 가능
## Convolutional Nerual Networks(CNN)
- CNN은 이미지 인식과 분류 작업에서 탁월한 딥러닝 모델
- 여러 계층으로 구성
- CIFAT100
  - 32*32 픽셀 크기의 자연 이미지
  - 100개의 다양한 클래스
    - 각 클래스가 5개의 하위 클래스를 갖는 20개의 슈퍼 클래스로 구성
- 핵심 컴포넌트
  - 컨볼루션(convolution) 계층
    - 입력 이미지의 작은 부분에 대해 필터를 적용
    - 이를 통해 이미지의 중요 특징 감지

### How Work
- 컨볼루션 계층
  - 이미지에서 작은 패턴이나 특징을 감지하는 역할
  - 선이나 가장자리와 같은 기본 형태 감지 가능
  - 필터(커널)이라는 작은 행렬을 이미지 전체에 걸쳐 슬라이딩
    - 특징 맵 생성
    - 이 맵은 입력 이미지에서 각 필터가 반응하는 영역의 강도를 나타냄
- 풀링 계층(Pooling layers)
  - 컨볼루션 계층 다음에는 풀링 계층 위치
  - 이미지의 공간 크기를 줄여주는 역할을 함
  - 이를 통해 계산량을 감소, 가장 두드러진 정보 유지 + 과적합 방지
- 계층적 구성
- 최종 분류

### 컨볼루션 계층(Convolution Layer)
- 컨볼루션 계층은 다수의 컨볼루션 필터로 구성
- 컨볼루션 필터는 컨볼루션이라고 불리는 간단한 연산에 의지함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/ffa0d075-b391-4597-93fc-d7a0afc9a043)
  - 만약 원본 이미지의 2*2 부분 행렬이 컨볼루션 필터와 유사하다면, 변환된 이미지에서 큰 값이 나타나고 아니라면 작은 값이 나타남
    - 입력 이미지의 부분 이미지가 필터와 비슷하면 점수가 높고, 그렇지 않으면 점수가 낮음
  - 필터 자체도 하나의 이미지, 작은 형태나 가장자리 등을 나타냄
    - 이 필터를 입력 이미지 위에서 이동시키면서 일치하는 부분에 대한 점수를 매김
    - 점수 매기기는 내적을 사용하여 수행됨
- 컨불루션 필터는 이미지의 다양한 부분에서 발생하는 공통 패턴을 찾기 위함임
  - 컨볼루션의 결과는 새로운 특징 맵(feature map)임

### Pooling Layser
- Pooling Layer는 큰 이미지를 작은 요약 이미지로 압축하는 츠
- Max Pooling
  - 2*2 블록의 네 개 픽셀 중 최대값을 선택, 해당 블록을 대체
  - 이미지의 크기를 각 방향으로 절반으로 줄임
  - 위치 불변성(location invariance)를 제공함
    - 하나의 픽셀이 큰 값을 갖는 경우, 블록 전체가 축소된 이미지에서도 큰 값으로 등록 -> 불변
    - 중요 특징을 유지하면서도 데이터의 양을 효과적으로 줄일 수 있음

### Architecture of CNN
- ![image](https://github.com/googoo9918/TIL/assets/102513932/4b6cc6f6-6ee5-4049-ba4d-2a042e76e428)
  - CNN은 다수의 컨볼루션 층과 풀링 층으로 구성됨
  - ex) 현재는 3개의 컨볼루션 layer와 Pooling Layer
  - 필터는 일반적으로 작은 크기(2*2)를 가짐, 각 필터는 컨볼루션 층에서 새로운 채널을 생성함
  - 폴링으로 크기가 감소함에 따라 필터의 수는 일반적으로 증가

### Data Augmentation(데이터 증강)
- 주어진 원본 데이터에 여러 변형을 적용, 데이터의 양을 인위적으로 늘리는 방법
  - 과적합을 방지
  - 파라미터를 보다 효율적으로 활용하여 학습 가능
- 각 학습 이미지는 여러번 복제 되고, 각 복제본은 임의로 왜곡됨
  - 이때 이 외곡은 확대, 수평 및 수직 이동, 회전 등을 포함함
  - 인간의 이식에는 영향을 미치지 않음
- 일종의 정규화 처럼 동작
  - 정규화는 파라미터에 직접적인 제약을 가해 과적합이 되는 것을 방지함
  - ex) 드롭아웃

- ![image](https://github.com/googoo9918/TIL/assets/102513932/f259842b-e15e-49a5-86a6-71eb59abedb5)

### Lab in Python
- CNN on CIFAR100 data
```python
# 입력 채널 수와 출력 채널 수를 기반으로 3*3 커널 크기의 convolution 연산을 정의
# 패딩이 same, 입력과 출력의 공간 크기를 유지함
# 예를 들어, 입력 이미지가 6*6이고 필터가 3*3, 스트라이드(필터가 한 번에 움직이는 칸 수)가 1이라 하자
# 패딩이 없는 경우, 출력 크기는 (입력 크기 - 필터 크기 + 1) / 스트라이드 이다.
# (6-3+1)/1 =4 --> 4*4 출력 이미지가 생성된다.
# 패딩이 있는 경우, 패딩은 (필터 크기 - 1)/2 이므로, (3-1)/2 =1 이고, 각 변에 패딩1을 추가한다.
# 즉, 입력 이미지의 각 변에 패딩을 추가하여 입력 이미지의 크기를 키우고, 전체 이미지에 대한 합성곱을 수행한다.

def __init__(self,in_channels,out_channels):
  super(BuildingBlock, self).__init__()
  # 컨볼루션 레이어 정의
  # Conv2d는 2차원 컨볼루션 레이어를 생성
  # in_channels와 out_channels를 통해 입력과 출력의 채널 수를 지정함
  self.conv = nn.Conv2d(in_channels=in_channels, 
                out_channels=out_channels, 
                # 컨볼루션 필터 크기 설정
                kernel_size=(3,3), 
                # 패딩 설정
                padding='same')
  # 활성화 함수
  self.activation = nn.ReLU()
  # 2*2 맥스 룰링이 연속적으로 적용
  self.pool = nn.MaxPool2d(kernel_size=(2,2))

def forward(self,x):
  return self.pool(self.activation(self.conv(x)))

# CIFAR100의 이미지 크기는 3 * 32 * 32임, 3개 채널, 32*32 픽셀
# 현재 필터의 크기가 3*3 이므로 BuildingBlock(3,32)에서 파라미터 개수는 다음과 같음
# input --> (3 * 3 * 3(차원)) * 32 + 32 = 896
# 32 -> 64는 어떠한가?
# 3*3*32*64 +64
# 64->128, 128->256 또한 마찬가지이다.
def __init__(self):
  super(CIFARModel, self).__init__()
        # 각 BuildingBlock에 대한 입력 및 출력 채널을 정의함
        sizes = [(3,32), (32,64), (64,128), (128,256)] 
        self.conv = nn.Sequential(*[BuildingBlock(in_, out_) for in_, out_ in sizes])
        self.output = nn.Sequential(
            nn.Dropout(0.5),
            # 평탄화된 특징 맵을 512개의 뉴런으로 연결
            # 마찬가지로 파라미터는 2*2*256*512 + 512
            nn.Linear(2*2*256, 512),
            nn.ReLU(),
            # 512개의 뉴런을 100개의 클래스로 매핑
            # 마찬가지로 파라미터는 512*100 + 100
            nn.Linear(512, 100)
        )

def forward(self, x):
  val = self.conv(x)
  val = torch.flatten(val, start_dim=1)
  return self.output(val)

cifar_optimizer = RMSprop(cifar_modelparameters(), lr=0.001)

cifar_module = SimpleModule.classification(cifar_model, num_classes=100, optimizer = cifar_optimizer)

cifar_trainer = Trainer(deterministic = True,
                max_epochs=30,
                logger=cifar_logger,
                callbacks=[ErrorTracker()])
cifar_trainer.fit(cifar_module, datamodule=cifar_dm)
```
- MAE(mean absolute error)
  - 모델의 예측 성능을 평가하는 지표
  - 낮을 수록 좋다
- R^2
  - 모델의 설명력을 나타내는 지표
  - 1에 가까울 수록 모델이 데이터를 잘 설명함
## Recurrent Nerual Networks
- IMDB(인터넷 영화 데이터베이스)에 대한 리뷰 분류
- 25,000개의 리뷰를 포함하는 세트, 테스트 세트 레이블
- 리뷰의 감정을 예측하기 위한 분류기를 구축하고자 함
- Bag of Words Model
  - 문서는 길이가 다르며 단어의 연속으로 구성됨
  - 사전에서 가장 자주 등장하는 1만 개의 단어 식별
    - 해당 단어가 나타난 모든 위치에 1을 표시
      - 없으면 0으로 표시된 이진 벡터 생성
      - 벡터들은 모두 같은 길이(1만)을 가짐, 대부분의 값이 0이기 때문에 희소 행렬이라 불림
    - 단어의 존재 유무만을 고려, 순서나 문맥은 고려하지 않음
    - 이는 유니그램으로, 이 대신 바이그램이나 m-그램을 사용할 수 있음
      - 두 단어의 조합이나 더 많은 단어의 조합
### Lasso logisitc regression vs Two-calss Neural Network
- 두 경우 모두에서 훈련 정확도가 단조롭게 증가
- 라쏘는 특성 선택(**glmnet**)을 통해 모델을 단순화하고 계산 비용을 줄일 수 있음
  - 희소 행렬 X에서 0이 아닌 항목만을 고려, 효율적으로 계산할 수 있음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/caa2a5a0-5f79-470d-af07-3b1c74995894)
  - Pr(Y=1|X)
    - 주어진 특성 X가 주어졌을 때 Y가 1일 확률
  - log 부분은 X에대해 Y가 1일 확률과 0일 확률의 비율인 오즈 로그 값을 나타냄
    - 로지스틱 회귀 모델의 예측 변수로 사용됨
  - Z1-Z0
    - 두 클래스(1, 0)에 대한 로지스틱 함수의 출력 값들 간의 차이
    - B10 - B00은 절편의 차이, B1l - B0l은 각 특성의 계수 차이
    - A_l(2)은 두 번째 층의 l번째 노드의 활성화 값
    - K_2는 두 번째 층에 있는 노드의 수

### RNN(Recurrent Neural Networks)
- 데이터가 시퀀스 형태로 발생하는 상황에서 자주 사용되는 신경망 구조
  - ex) 문서는 단어의 연속적인 시퀀스, 날씨 데이터나 금융 지수와 같은 시계열 데이터, 녹음된 음성이나 음악, 필기 데이터 등
- RNN은 이러한 데이터의 순차적 특성을 고려, 과거의 정보를 기억하는 모델을 구축함
  - 관측치의 특성은 벡터의 시퀀스, {X1, X2, ..., Xn}과 같이 표현됨
    - ex) X_12가 단어를 나타내고, 전체 X가 IMDb 영화 리뷰 문서 등을 나타낼 수 있음
- 타깃 Y는 일반적으로 단일변수나 다중 클래스를 위한 one-hot vector와 같은 형태를 취할 수 있음
  - 혹은 시퀀스일 수도 있음
- 결론적으로, RNN은 연속적 흐름을 파악, 이전 정보를 활용하는 능력을 갖추고 있고 이로 인해 시퀀스 데이터 처리 작업에 매우 적합함 

### RNN Architecture
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3a2102b2-59e4-48b6-80d6-d865fa78485b)
  - 입력: 시퀀스 X={X1, X2,...Xn}
    - Xi는 단어 수 L을 나타낼 수 있음
  - 출력: 간단한 출력 Y를 생성함
  - 은닉층: A = {A1, A2,...,Am}은 은닉 상태의 시퀀스
    - 입력 X와 이전 은닉 상태 Ai-1을 받아 새로운 은닉 상태 A_i를 계산함
    - 각 단계마다 같은 가중치 W, U 및 편향 B를 사용함
      - 반복적으로 가중치를 사용하기 때문에 recurrent, 즉 순환적임!
  - 가중치 재사용
    - 각 시간 단계에서 같은 가중치를 사용함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b16cf25c-2d0c-46ea-8281-2f22f2306e58)
  - 입력과 은닉 상태
    - X는 p개의 컴포넌트를 가진 입력 벡터
    - A는 K개의 컴포넌트를 가진 은닉 상태 벡터
  - 계산
    - 각 은닉 유닛 Ak의 계산은 입력 X와 이전 은닉 상태 A에 대한 가중치 행렬 W와 U를 사용함
    - 이 공식은 A_k = g(WkX + UkA)로 표현됨
      - g는 비선형 활성화 함수
  - 가중치
    - W: K * p행렬, 입력층의 가중치
      - 입력 피처의 수(입력 차원)d와 레이어의 유닛 수 n을 곱한 값, 즉 d*n
      - 이때 n=p, d=k임
    - U: K*K 행렬, 은닉층에서 이전 은닉 상태로부터 다음 은닉 상태로의 전이를 결정
      - n*n이라고 생각할 것
    - B: 출력층의 가중치 벡터, K+1 차원
      - b = n임
    - 결과적으로, 총 가중치는 n(d+n+1)임
  - 출력
    - 출력 O는 O = BA로 계산됨
    - B는 출력층의 가중치임

### RNN and IMDB Reviews
- 문서 특성
  - 문서의 특성은 단어의 시퀀스 W로 표현됨
  - 일반적으로 모든 문서를 동일한 단어 수 L로 자르거나 채워 문서의 길이를 통일
- 단어의 표현
  - 각 단어 Wi는 10,000길이의 one-hot 인코딩된 벡터 X로 표현됨
    - 원핫 인코딩은 단어 집합 크기만큼 벡터 차원을 가짐
    - 모두 0이고, 해당 단어의 사전 내 위치에만 1 표시
- 단어 임베딩
  - 미리 학습된 임베딩 행렬E 사용
  - 매우 큰 문서에서 PCA 변형을 사용하여 구축됨
  - 10,000 길이의 이진 특성 벡터를 m 차원의 실수 벡터로 줄여줌
- 즉, 원-핫 인코딩은 차원이 높기 때문에 비효율적, Work Embedding을 통해 효과적으로 표현
- LSTM(Long Short-Term Memory)
  - RNN을 LSTM으로 개선하자 정확도가 늘어남

### Time Series Forecasting(시계열 예측)
- 뉴욕 증권 거래소 데이터를 사용한 시계열 예측
  - 주어진 데이터를 바탕으로, 로그 거래량을 예측
    - 전체 발행 주식 수 대비 거래된 주식수(100일 이동 평균 대비 로그 스케일)
- 자기상관(autocorrelation)
  - 시계열 데이터에서, 특정 시차(lag) l에 대한 데이터 값들의 상관관계
  - Vt와 l 거래일 차이나는 Vt-l 간의 상관도를 의미함
  - 과거의 값들이 미래 값을 예측하는 데 유용하다는 것을 시사
    - 과거 데이터 패턴이 일정 부분 반복될 가능성 존재
### RNN Forecaster
- 긴 시계열 데이터에서 부분 시퀀스를 추출하여 사용
  - 사전 정의된 길이 L인 lag를 사용, 입력 시퀀스 X1, X2,,,, Xl를 생성
  - Xi는 시점 t-i에서의 값과 그 이전 값 포함 가능
  - X와 Y 쌍 생성, Y는 예측하고자 하는 다음 시점의 값
- 시리즈의 총 길이가 T=6,051이고 L=5로 설정하면, 총 6,046개의 (X,Y) 쌍을 생성할 수 있음
  - 1~5일 까지가 X1, 2~6까지가 X2 .... 6046~6050이 X6046이 되겠지
  - Y1은 여섯 번째 날, Y2는 일곱 번째 날, .... Y6046은 6051번째 날이 됨
- RNN 구성 시, 각 lag 단계마다 12개의 은닉 유닛을 사용
  - 각 시점에서의 입력이 은닉층을 통해 처리, 다음 시점의 입력으로 연결됨
### RNN Results for NYSE Data
- R^2의 값이 1에 가까울 수록 완벽한 예측을 의미함
  - RNN 모델 : 0.42
  - 전날의 로그 거래량을 사용해 당일의 로그 거래량을 예측하는 기존 모델: 0.18

### Autoregression Forecaster
- RNN은 전통적인 자기회귀(AR) 모델과 유사한 구조를 가짐
- ![image](https://github.com/googoo9918/TIL/assets/102513932/500a5eb5-2b1f-4fa4-bbe4-3ea8e4834bc6)
  - 자기회귀 모델은 L차수의 지연 변수 사용, 현재의 값을 예측함
  - y를 M 행렬의 선형 조합으로 예측
    - M 행렬은 각각의 행이 L개의 지연된 데이터 포인트를 포함하는 설계 행렬임
  - OLS 회귀 적합
    - OLS를 적용, 계수 B를 추정
      - 이 계수들은 각 지연 변수가 현재 값 y에 미치는 영향과 크기를 나타냄
- ![image](https://github.com/googoo9918/TIL/assets/102513932/49095004-b8bf-484a-b949-e777cb5f104c)

### Lab in Python
```python
def __init__(self, input_size):
  super(IMDBModel, self).__init__()
  # 입력 크기에서 16개의 노드로 데이터를 변환하는 연결층 생성
  # inputsize가 10003, output이 16 --> 10003 * 16 +16 = 160,064
  self.dense1 = nn.Linear(input_size, 16) 
  # 활성화 함수로 ReLU 사용
  self.activation = nn.ReLU() 
  # 두 번째 선형층, 16개의 노드에서 16개의 노드로 데이터 전달
  # 16*16 + 16 = 272
  self.dense2 = nn.Linear(16, 16) 
  # 출력층, 마지막 16개의 노드를 최종 출력 노드 1개로 변환
  # 16*1 + 1 = 17
  self.output = nn.Linear(16, 1)
def forward(self,x):
  val = x
  for _map in[self.dense1,
        self.activation,
        self.dense2,
        self.activation,
        self.output]:
      val = _map(val)
    return torch.flatten(Val)

# 이진 분류 작업을 위한 모듈 생성
imdb_module = SimpleModule.binary_classification(imdb_model, optimizer=imdb_optimizer)

# 모듈에 대한 훈련 실행
imdb_trainer.fit(imdb_module, datamodule=imdb_dm)

# 모델 평가
imdb_trainer.test(imdb_module, datamodule=imdb_dm)


# 입력 데이터를 32차원의 임베딩 벡터로 변환함
# 임베딩에서는 편향이 추가되지 않음 --> 10003 * 32 = 320,096
self.embedding = nn.Embedding(input_size, 32)
# LSTM 레이어 정의, 차례대로 임베딩의 출력 크기, LSTM 셀의 숨겨진 상태 크기, 입력 텐서의 첫 번쨰 차원이 배치 크기 
# 얘는 왜 8448이 나오는지 모르겠네..
# 32*32 + 32*32 + 32  32 = 2080, 2080*4 = 8320
# input_size * hidden_size + hidden_size * hidden_size + hidden_size + hidden_size
# LSTM에는 4개의 게이트가 있음
self.lstm = nn.LSTM(input_size=32, hidden_size=32, batch_first=True) 
# LSTM의 출력을 받아 최종 예측 결과를 생성하는 선형 레이어
# 얘는 당연히 33인거 알겠제?
self.dense = nn.Linear(32, 1)

# RNN for Time Series Prediction
# X 데이터프레임에 요일(dummy)을 병합 --> 모델에 요일 효과를 포함시키기 위함임
X_day = pd.merge(X, pd.get_dummies(NYSE['day_of_week’]), on='date’)

# 데이터프레임 X의 컬럼을 지정된 순서대로 재배치
X = X.reindex(columns=ordered_cols)

# 3개의 입력 특성을 받고, 12개의 숨겨짓 유닛을 가짐, 배치 크기가 첫 번째 차원으로 오도록 설정
# input_size * hidden_size + hidden_size * hidden_size + hidden_size + hidden_size
# 3*12 + 12*12 + 12 + 12 = 204
self.rnn = nn.RNN(3, 12, batch_first=True)
# 숨겨짓 유닛 -> 출력값으로 변환하기 위한 선형 레이어
self.dense = nn.Linear(12, 1)
self.dropout = nn.Dropout(0.1)
```

## Fitting Neural Networks
- CNN(합성곱 신경망)은 이미지 분류와 모델링에서 유용
  - ex) MR, x-ray
- RNN(순환 신경망)
  - 시간에 따라 변하는 데이터 또는 시퀀스 데이터 모델링에 적합
  - 말의 흐름 인식, 문맥 파악으로 번역의 정확도 높임 등
- 오캄의 면도날 원칙
  - 가능하다면 더 단순한 모델 선호, 모델이 해석하기 쉬워짐
  - 잡음이 많은 경우, 더 간단한 모델이 더 잘 작동할 수 있음
- non-convex(비블록)
  - 전역 최적점을 찾기 어려움
  - 경사 하강법(Gradient Descent)등을 이용해 해결
- 기계 학습에서 중요한 몇 가지 기법
  - Slow learning
    - 경사 하강법의 속도를 늦춤
  - Stochastic gradient descent(확률적 경사 하강법)
    - 전체 데이터 대신 미니배치를 사용하여 기울기를 계산
  - epoch
    - 전체 데이터 세트가 한 번 처리될 때까지의 미니배치 업데이트 회수
  - Regularization
    - Ridge와 lasso는 가중치를 축소
    - dropout과 augmentation은 데이터 다양성을 증가
- 드롭아웃
  - SGD 업데이트 중 특정 확률로 뉴런을 비활성화 하는 기법
    - 릿지 규제(모든 가중치를 축소)와 같이, 다른 유닛들이 제거된 유닛들을 대신하여 가중치는 더 가까워짐
- Data augmentation(데이터 증가)
  - 라벨은 변경되지 않으나, 훈련 이미지에 자연스러운 변형을 적용, 각 원본 훈련 이미지 주변에 이미지의 구름을 만듬
- Double Descent
  - 너무 적은 것보다 많은 숨겨진 유닛이나 층을 가지는 것이 더 나음
  - 모델이 복잡해짐에 따라 표현령이 증가하고, 복잡한 패턴을 학습할 수 있음
    - 그러나 과적합의 위험이 커짐
  - 실제로 이중 감소 오류 곡선은 복잡성이 증가할 수록 훈련 데이터에 대한 오류는 감소하지만 일정 지점을 넘어서면 다시 오류가 증가하는 현상이 나옴
    - 모델의 복잡성이 계속 증가하면 오류가 다시 감소하기 시작하는 이중 감소 현상이 나타남
    - 고차원에서 모델의 복잡성이 특정 임계값을 넘어가면, 추가적 모델 복합성이 모델의 성능을 개선할 수 있음

## Tensorflow
```python
# 난수 생성(shape, 최소, 최대)
tf.random.uniform([1],0,1)
# 정규분포 난수
tf.random.normal([4],0,1)
```
- 뉴런 만들기
  - 뉴런은 입력, 가중치, 활성화함수, 출력으로 구성
  - ex) 입력에 가중치를 곱한뒤 활성화함수를 취해 출력을 얻는 형태
  - 변하는 것은 가중치! 가중치가 변하며 원하는 출력에 가까워지는 가중치를 얻음
  - 활성화함수는 뉴런의 출력값을 정하는 함수
    - sigmoid보단 ReLU가 더 많이 사용됨 max(0,z)
    - 입력이 0인 경우 등을 대비해 편향(bias)을 추가할 수 있음
- AND 신경망 네트워크
  - 입력이 2개, 편향이 1개
  - Y = f(X1*w1 + X2*w2 +1*b)
- XOR 네트워크
  - 첫 번째 입력이 두 번째 입력보다 큰 영향을 미침
  - 편향은 거의 영향을 미치지 않음
  - 중간 계산값이 0에 가까워지고, 최종 출력이 0.5에 가까워지는 경향
  - 반면 AND 네트워크는 중간 계싼값이 다양하게 나옴
    - 두 가중치가 비슷, 입력 2개가 비슷한 중요도를 가짐
    - 편향은 큰 음수, 중간 계산값을 음수로 보내는 경향
  - XOR의 경우 두가중치와 편향이 매우 작은 값으로, 어떤 일을 하려는지 명확하지 않음
  - 즉, 하나의 퍼셉트론으로 간단한 XOR 연산자도 만들어 낼 수 없음
    - XOR problem
      - 여러 개의 퍼셉트론으로 해결 가능
- ![image](https://github.com/googoo9918/TIL/assets/102513932/f1580b85-ead0-4888-9bdc-83a9c91317fc)
```python
model = tf.keras.Sequential([
  # layer를 정의하는 명령, unit으로 해당 layer를 구성하는 뉴런 수 정의
tf.keras.layers.Dense(units=2, activation='sigmoid', input_shape=(2,)), 
tf.keras.layers.Dense(units=1, activation='sigmoid')
])
# optimizer는 최적화 함수로 딥러닝 학습식 정의
# SGD는 확률적 경사하강법, 기울기 낮은 쪽으로 가중치 업데이트
# 확률적 --> 전체를 한 번에 계산 x 확률 적으로 일부 샘플을 구해서 계산
# loss를 계산하기 위해 평균 제곱 오차(Mean Squared Error) 계산
model.compile(optimizer=tf.keras.optimizers.SGD(learning_rate=0.1), loss='mse')

# model.fit()을 통해 epochs에 지정된 횟수만큼 학습
# batch_size는 한 번에 학습시키는 수, x와 y는 입력과 기대 출력
history = model.fit(x, y, epochs=2000, batch_size=1)

# XOR 네트워크 평가
model.predict(x)

# XOR 네트워크의 가중치와 편향 확인
for weight in model.weights: 
  print(weight)
  # 레이어 사이 뉴런 연결 시 사용되는 가중치는 kernel
  # 편향과 연결된 가중치는 bias
  # 가중치에 이름을 붙일 때는 위첨자로 레이어, 아래첨자로 뉴런의 인덱스 표시
  # ex) W1_21은 1번 레이어의 입력측 2번 뉴련 출력측 1번 뉴런임
```

## 회귀(Regression)
- 선형 회귀(Linear Regression)
  - 데이터의 경향성을 가장 잘 설명하는 하나의 직선 예측
- 평균 제곱 오차(MSE, Mean Squared Error)
  - 실제값과 예측값간의 차이의 제곱을 취하고, 결과의 평균을 구함
  - 작을 수록 모델의 성능이 좋음
```python
# 예측값
y_pred = a*X + b
tf.reduce_mean((Y-y_pred)**2)
```
- 최적화함수로 Adam Optimizer 사용
- 다항 회귀
  - x^2, x^3등의 다항식을 이용한 비선형 회귀
- tanh
  - 실수를 입력 받아 -1과 1사이의 값을 반환하는 활성화 함수
- 보스턴 주택 가격 데이터셋
  - 훈련 데이터, 검증 데이터, 테스트 데이터로 나눠서 학습
  - train_X, train_Y로 훈련 데이터와 검증 데이터를 합쳐 받은 후 추후 분리
  - Training data가 404개, test data가 102개, 8:2 비율
    - 추후 training, validation, test 비율을 6 2 2 정도가 되도록 함
```python
# 데이터 속성이 13개, 4개의 layer
# 활성화 함수로 ReLU
## 여러 개의 layer를 겹쳐 사용할 때, 시그모이드나 tanh보다 좋은 결과를 얻을 수 있음
# 파라미터는 (13+1)*52 / (52+1)*39 / (39+1) * 26 / (26+1) * 1 이 될것임
model = tf.keras.Sequential([
tf.keras.layers.Dense(units=52, activation='relu', input_shape=(13,)),
tf.keras.layers.Dense(units=39, activation='relu'), 
tf.keras.layers.Dense(units=26, activation='relu'), tf.keras.layers.Dense(units=1)
])
# 최적화 함수로 Adam Optimizer 사용
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.07), loss='mse')

# validation_split을 0.25정도로 설정, 25%정도의 데이터를 validation data로 사용
# 25번 학습 진행, 한 번에 32만큼 학습 진행
history = model.fit(train_X, train_Y, epochs=25, batch_size=32, validation_split=0.25)

# callbacks 인수에 EarlyStopping 함수를 지정, 3회의 epochs를 수행하는 동안 최고 기록을 갱신하지 못한다면 학습 종료
# 이런식으로 과적합을 방지할 수 있음
callbacks=[tf.keras.callbacks.EarlyStopping(patience=3, monitor='val_loss')])
```

## 분류(Classfication)
- 이항 분류
  - 정답의 범주가 2개인 분류 문제
- 레드 와인, 화이트 와인 문제
```python
# 레드 와인 - 화이트 와인 순서 데이터를 sample() 함수로 랜덤하게 섞어줌
# frac=1이므로, 100% 데이터를 뽑아서 섞음
wine_shuffle = wine_norm.sample(frac=1) 

# 와인 데이터셋 분류 모델 생성
# Classfication 문제이므로 활성화 함수로는 마지막 layer에서 softmax를 사용할 예정
# e를 지수로 사용, 모두 더한 값으로 나눔
# ax함수의 약화된 버전, 확률값으로 인자의 확률 총합을 다 더하면 1이됨
# 지수함수는 큰 값은 강조, 작은 값은 약화하는 효과가 있음
import tensorflow as tf
model = tf.keras.Sequential([
tf.keras.layers.Dense(units=48, activation='relu', input_shape=(12,)), 
tf.keras.layers.Dense(units=24, activation='relu'), 
tf.keras.layers.Dense(units=12, activation='relu'), 
tf.keras.layers.Dense(units=2, activation='softmax')
])
# 최적화 함수는 Adam 사용, 손실 함수 지정
# 손실 함수로 크로스 엔트로피 사용
# 엔트로피란 확률의 역수에 로그를 취한 값임, 불확실성을 수치화하는 개념
# 확률이 높은 사건일수록 정보량(놀라움)이 적다고 판단
# 비가 올 확률이 1%, 비가 오지 않을 확률이 99%인 경우
# 비가 오는 경우(엔트로피 4.605)는 
# 비가 오지 않는 경우(엔트로피 0.010)보다 460배 더 놀라운 사건이 됨
# 엔트로피의 기대값은 각 엔트로피에 확률을 곱한 값
# 비가 오는 경우 0.0461, 비가 오지 않는 경우 0.0099
# 엔트로피의 기대값이 더 높은 사건을 더 가치 있는 사건으로 분류함
# 비가 오지 않는 사건의 엔트로피가 더 낮긴 하지만
# 전체적으로 봤을 때 비가 오는 사건의 기대 엔트로피가 더 가치 있음
# 다만 기대값이 아닌 단순 높은 엔트로피는 높은 불확실성을 의미
# 엔트로피를 줄이면 불확실성이 낮아지고 의미 있는 정보를 얻을 수 있음
# 크로스 엔트로피가 낮을 수록 예측을 잘하는 네트워크임
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.07), loss='categorical_crossentropy', metrics=['accuracy'])
```
- 다항 분류
  - 정답의 범주가 여러개인 범주 문제
  - 와인의 품질 예측
```python
# 균일한 데이터를 위해 품질을 3개의 범주로 재분류(좋음, 보통, 나쁨)
# 와인 데이터셋 다항 분류 모델 생성 및 학습
model = tf.keras.Sequential([ 
 tf.keras.layers.Dense(units=48, activation='relu', 
input_shape=(12,)),
 tf.keras.layers.Dense(units=24, activation='relu'), 
 tf.keras.layers.Dense(units=12, activation='relu'), 
 tf.keras.layers.Dense(units=3, activation='softmax') ])
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate =0.07), loss='categorical_crossentropy', metrics=['accuracy'])

history = model.fit(train_X, train_Y, epochs=25, batch_size=32, validation_split=0.25)
```

- Fashion MNIST
  - grayscale, 28 pixel, class 10, 0~255
```python
# 최소, 최대를 알고 있기에 255로 나누기만 하면 정규화 가능
# Fashion MNIST 분류 모델
model = tf.keras.Sequential([
  # 2차원 array를 Flatten을 통해 일렬로 정렬 
  tf.keras.layers.Flatten(input_shape=(28,28)), 
  tf.keras.layers.Dense(units=128, activation='relu'),
  # 마지막 레이어의 뉴런 수는 범주의 수와 같은 10개
  tf.keras.layers.Dense(units=10, activation='softmax') ])
# 정답 행렬로 one-hot encoding을 사용하지 않기 때문에
# cateogorical_crossentropy가 아니라 sprase_categorical_Crossentropy를 사용함
# [0,0,0,0,0,0,0,0,0,1] 대신 9fmf tkdyd
model.compile(optimizer=tf.keras.optimizers.Adam(), loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Fashion MNIST 분류 모델 학습
history = model.fit(train_X, train_Y, epochs=25, validation_split=0.25)
```
## Single Layer Neural Network
- dense layer를 하면 connected된다
- inputlayer의 inputvarialbe의 weight가 들어가고, bias가 포함된다.
- K는 히든 레이어에 있는 유닛 notation.. 파라미터 개수 구할 수 있어야한다!
- 이거 수식 잘 확인할 것!!!
### MNIST Digits
- Output Layer, Classfication 문제에서는 카테고리가 여러개가 존재할 수 있다
- HiddenLayer가 여러개 있는 경우 파라미터 계산!
  - Hidden layers and Output layer 페이지 잘 볼것
- MLP의 Input Data 2D Tensor: Samples, Features
  - Sample 개수와 파라미터 개수는 아무 연관이 없다는 점
  - Feature가 중요하다!

### Lab in Python
- 다른거 보지말고, 실제로 모델이 어떻게 만들어지는지를 잘 확인할 것!!!
- class HittersModel에 해당하는 내용
  - 생성자와 함수..
  - Flatten(), Sequential, Linear 등..
  - 생성이 돼서 forward를 돌리게 되면, flatten으로 피고 sequential로 돌리는..
- 실제로 돌릴 때는 Module을 만들고... ex) SimpleDataModule
  - 이때 train, test, batch_size등 이런 의미들을 알고 있어야 한다!
  - hit_trainer에서 max_epochs, callback함수 등
  - 이때 SGD는 어떻게 되는가??
  - 파라미터 개수는 어떻게 계산되는가?
- Multilayer Network on the MNIST Digit Data
  - MNISTModel 어떻게 정의되는지!
  - Trainer를 활용, fit..

## CNN
- Convolution Layer
  - Filter, 파라미터가 실제로 filter안에 들어가 있음
  - 패딩을 통해 값을 채워넣을 수 있다 ㅇㅇ
- Pooling Layer
  - 이미지 사이즈를 줄여주기 위한 작업
- Architecture of CNN
  - RGB 차원 3개 --> filter 2개니까 convolve하고 6차원됨 --> Pooling 하면 절반으로 줄어서 16*16됨... 이거 반복
- Data Augmentation 정의를 잘 알아둘 것
  - data를 늘리는 것, data를 늘린다 = parameter를 줄인다는 것인데, 어떤 의미인가?
  - regularization?
- Input data For CNN
  - 4 data!
  - Sample, Height, Width, Channel

### Lab in Python
- CIFATModel 어떻게 정의되는지?
- 4개의 convolution layer를 집어 넣겠다!
- size에서는 channel을 정의하는..
- BuildingBlock 안을 보면, convolution layer와 pooling layer 또한 확인할 수 있음
  - convolve에서 3*#, pooling 커널은 2*2
- 이렇게 정의된 Building Block을 통해 CIFAR Model 구조화 시켜서 만듬
- convolve 하고 pool 하고..
  - 2*2만큼 작아진 것을 256만큼 feature로... 512개의 output이 만들어짐
- 그래서 파라미터 어떻게 나오고 ㅇㅇ...
- Trainer에서 에폭이 어떻게 들어가는지 정도.. 확인
- pretrained는 굳이 보지 말 것!

## RNN
- Input Data for RNN
  - #Sample, #Time Steps, #Features
  - Feature는 파라미터와 관련이 있음
  - Time step은 예를 들어 단어 개수 (문서를 어디까지 볼 것인가?)
    - 주식 같은건 5일만 잘라서 보겠다 등..
- RNN Architecutre
  - 재귀적이라는 말이 나오는건, 반복이 된다는거임!!
  - 반복적으로 update 해나감..
- 수식
  - 두 개 input인데, feature는 p개의 component
  - unit 개수 같은 경우는, 해당하는 RNN Layer안에 몇 개의 Unit을 집어 넣을 것인지?
  - U와 W 를 합치면 K(K+P+1)이 되겠지
- 임베딩 레이어, 원 핫 인코딩.. 10K를 학습하기 위해서!
  - 파라미터가 확 줄고 학습이 빨라지게 됨
- RNN Forecaster
  - NYSE에서 sample 수가 어떻게 되는지?
  - feature은 각각 3개씩 존재
  - AR 모델을 활용해서 학습 가능
  - 3L+1이 된다?!
    - 16개 있다 ㅇㅇ...
    - RNN은 205개 있다 ㅇㅇ...
### Lab in Python 
- batch_size는 512개
- IMDBModel 정의
  - RNN은 안돌리고 그냥 MLP를 돌린거임 이건
  - data가 10003개로 들어가 있고..
  - 10004 * 16 하면 160,064가 나올거임
- LSTMModel은 배우지 않았음!! 근데 왜 설명하는거야?
  - 10,003개를 32개로 Embedding 하여 진행
  - LSTM 코드 시험 안낸대 ㅇㅇ
  - 파라미터 공부할 때는, 우리가 앞의 슬라이드에서 봤던걸 기준으로 공부하도록 하여라

## Convolution Neural Network(CNN)
### 특징 추출
- CNN은 이미지 뿐 아니라 텍스트나 음성 등에도 사용
- 이미지 데이터에서는 연구자가 스스로 특징을 찾아야함
  - convolution 연산은 **특징 추출(Feature extraction)** 기법 
    - 원본 이미지와 필터의 합성곱(내적)
- 특징 추출
  - 필터에 따라 컨볼루션 연산의 결과값이 달라짐
  - **Hand-crafted feature(수작업으로 설계한 특징)**임
    - 문제점
      - 전문적 지식 요망
      - 시간과 비용이 많이 듬
      - 다른 분야로 적용이 힘듬
- CNN은 특징을 추출하는 필터를 자동으로 생성
  - Hand-crafted feature의 문제점 해결

### 주요 레이어 정리
- 컨볼루션 신경망은 **특징 추출기(Feature Extractor)**, **분류기(Classifier)**가 합쳐진 상태
  - Input, Convolution, Pooling
  - Dense, Output
    - Flatten은 어디에도 들어가지 않음
- 특징 추출기
  - 컨볼루션 레이어와 풀링 레이어가 교차되며 배치
- 분류기
  - Dense레이어 배치, 과적합 방지를 위해 Dropout 레이어가 Dense 레이어 사이에 배치
  
### Convolution 레이어
- Convolution 연산 진행
- 필터는 네트워크 학습을 통해 자동으로 추출
  - 당연히 필터의 수가 많을 수록 다양한 특징 추출 가능
- 2차원을 기준으로 설명, 보통 컬러 이미지에는 R, G, B 3개 채널 존재
- **새로운 이미지(Feature map)의 마지막 차원 수는 필터의 수와 동일**
- Conv2D 레이어 생성 코드
```python
# Conv2D 레이어 생성 코드
conv1 = tf.keras.layers.Conv2D(kernel_size=(3,3),strides=(2,2),padding='valid' ,filters=16)
# 필터 행렬의 크기
# 스트라이드 크기
# valid: 패딩 사용 안함
# same: 빈 값을 넣어 출력 이미지의 크기를 입력과 같도록 보존
# filters: 필터의 개수(너무 많으면 학습 속도 저하 및 overfitting 가능)

# 인접한 픽셀은 비슷한 정보를 갖고 있는 경우가 많음
# 이미지 크기를 줄이며 중요한 정보만 남기기 위해 사용
# 효율적 메모리 사용 + overfitting 방지
# MaxPool2D 레이어 생성 코드
pool1 = tf.keras.layers.MaxPool2D(pool_size=(2,2), strides=(2,2))
# 한 번에 Max 연산을 수행할 범위
```

### Dropout 레이어
- 네트워크의 과적합을 막음
  - **공모(conspiracy)**를 막고 과적합(overfitting) 감소
- 무작위로 뉴런의 부분집합 제거
```python
# Dropout 레이어 생성 코드
pool1 = tf.keras.layers.Dropout(rate=0.3)
# 제외할 뉴런의 비율
```

### Fashion MNIST 데이터셋에 적용
- 흑백 이미지는 1개의 채널을 가짐
  - 하지만 CNN에서는 (height, width, channels)의 형태를 가져야 함
  - 따라서 데이터의 가장 뒤쪽에 채널 차원을 추가
  - 데이터의 수는 달라지지 않음
```python
# 데이터를 채널을 가진 이미지 형태(3차원)으로 바꾸기
# reshape 이전
print(train_X.shape, test_X.shape)

# -1은 자동으로 결정됨(어차피 데이터의 개수니까)
train_X = train_X.reshape(-1, 28, 28, 1) test_X = test_X.reshape(-1, 28, 28, 1)

# reshape 이후print(train_X.shape, test_X.shape)
# (60000, 28, 28) (10000, 28, 28)
# (60000, 28, 28, 1) (60000, 28, 28, 1)
# 60000, 10000은 Sample 수를 가리킴
```

```python
# Fashion MNIST 분류 컨볼루션 신경망 모델 정의
model = tf.keras.Sequential([
  # 필터의 수를 2배로 늘리고 있음
    tf.keras.layers.Conv2D(input_shape=(28, 28, 1), kernel_size=(3, 3), filters=16),
    # 출력 (None, 26, 26, 16)
    # padding이 없으므로 출력 이미지 크기 작아짐
    # 파라미터 수 160 (3*3*1*16) + 16
    # 필터 하나의 크기 3x3, 필터 수 16, 입력 채널 수 1
    # 헷갈리면 필터 한 개의 파라미터 수를 계산해보라
    # 필터 크기: 3x3 = 9, 입력 채널 수:1, 바이어스:1
    # 3*3+1 = 10, 필터 개수 16 --> 160

    tf.keras.layers.Conv2D(kernel_size=(3, 3), filters=32),
    # 출력(None, 24, 24, 32)
    # 파라미터 수: (3*3*16 + 1) * 32 = 4640

    tf.keras.layers.Conv2D(kernel_size=(3, 3), filters=64),
    # 출력(None, 22, 22, 64)
    # 파라미터 수: (3*3*32 + 1) * 64
  # 2D 출력을 1D 벡터로 변환
    tf.keras.layers.Flatten(),
    # 출력(None, 30976)
    # 22 * 22 * 64 = 30976

    tf.keras.layers.Dense(units=128, activation='relu'),
    # 파라미터 수: 30976 * 128 + 128
    
    tf.keras.layers.Dense(units=10, activation='softmax')
    # 파라미터 수: 128 * 10 + 10
])

# 모델 컴파일
model.compile(optimizer=tf.keras.optimizers.Adam(), loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Fashion MNIST 분류 컨볼루션 신경망 모델 학습
# 데이터의 25%를 검증 데이터로 사용
history = model.fit(train_X, train_Y, epochs=25, validation_split=0.25)

# 모델 평가
model.evaluate(test_X, test_Y, verbose=0)

# loss는 학습 데이터에 대한 손실 값
# val_loss는 검증 데이터에 대한 손실 값(일반화 정도를 나타냄)
# 당연히 낮아야 좋은거임

# accuracy: 학습 데이터에 대한 정확도 값
# val_accuracy: 검증 데이터에 대한 정확도 값
```

- 네트워크에 풀링 레이어와 드롭아웃 레이어 추가
```python
tf.keras.layers.MaxPool2D(strides=(2,2))
# Output Shape가 작아지기 때문에 parameter 개수가 엄청나게 줄어듬
# (None, 11, 11, 64) --> (None, 5, 5, 64)

tf.keras.layers.Dropout(rate=0.3)
```

### 퍼포먼스 높이기
- 딥러닝이 말전할 수록 컨볼루션 레이어가 중첩된 더 깊은 구조가 나타남
  - 더 많은 레이어 쌓기
  - 이미지 보강(Image Augmentation) 기법

### 더 많은 레이어 쌓기
- **VGGNet** 스타일의 컨볼루션 신경망 정의
  - 아래 예시는 VGG-7(Convolution 레이어 개수 + Dense 레이어 개수)
```python
model = tf.keras.Sequential([
    tf.keras.layers.Conv2D(input_shape=(28, 28, 1), kernel_size=(3, 3), filters=32, padding='same', activation='relu'),
    tf.keras.layers.Conv2D(kernel_size=(3, 3), filters=64, padding='same', activation='relu'),
    tf.keras.layers.MaxPool2D(pool_size=(2, 2)),
    tf.keras.layers.Dropout(rate=0.5),
    
    tf.keras.layers.Conv2D(kernel_size=(3, 3), filters=128, padding='same', activation='relu'),
    tf.keras.layers.Conv2D(kernel_size=(3, 3), filters=256, padding='valid', activation='relu'),
    tf.keras.layers.MaxPool2D(pool_size=(2, 2)),
    tf.keras.layers.Dropout(rate=0.5),
    
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(units=512, activation='relu'),
    tf.keras.layers.Dropout(rate=0.5),
    tf.keras.layers.Dense(units=256, activation='relu'),
    tf.keras.layers.Dropout(rate=0.5),
    tf.keras.layers.Dense(units=10, activation='softmax')
])
```

### 이미지 보강(Image augmentation)
- 훈련 데이터에 없는 이미지를 새롭게 만들어내서 훈련 데이터 보강
  - 가로로 뒤집거나, 회전 시키거나, 기울이거나, 일부 확대, 평행이동 등


## 순환 신경망(RNN, Recurrent Neural Network)
- **순서가 있는 데이터**(eg. 날씨, 주가)를 입력으로 받음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/1aca23ae-35c9-4b6b-9b4a-ceebf9a95a4e)
  - 새로운 입력 Xn과 출력 Yn을 동시에 입력으로 받음
  - 입력과 출력 길이에 제한이 없음
    - 다양한 형태의 네트워크 생성 가능

### SimpleRNN 레이어
- 가장 간단한 형태의 RNN
- 활성화함수
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/7022b089-a55c-4266-8f90-b80a23969ae9)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/cd91cd06-adf3-4f2f-9950-0e9c60c85f78)
- SimpleRNN 레이어 생성 코드
```python
rnn1 = tf.keras.layers.SimpleRNN(units=1, activation='tanh', return_sequences=True)
# 전체 출력

# 시퀀스 예측 모델 정의
model = tf.keras.Sequential([
  # 각 시퀀스는 4개의 타임스탭을 가지고, 입력 차원은 1임
  # [[0.0], [0.1], [0.2], [0.3]],  # 첫 번째 시퀀스
  # 각 시퀀스는 4개의 타임 스탭을 갖고 있으며, 각 타임스텝에서 입력되는 데이터는 하나의 값(1차원)임
  # 마지막 타임스텝의 출력만 반환
    tf.keras.layers.SimpleRNN(units=10, return_sequences=False, input_shape=[4,1]),
    # 파라미터 수 : (입력크기 + 유닛 수)* 유닛 수 + 유닛 수
    # (1+10) * 10 + 10

    tf.keras.layers.Dense(units=1)
    # 파라미터 수: 10*1 + 1
])

model.compile(optimizer='adam', loss='mse')
model.summary()
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/99ea3de8-1dd5-48d9-bcf7-a9dbcdb8c153)
- 다만, 학습되지 않은 데이터에 대한 예측은 좋지 않음

### LSTM 레이어
- SimpleRNN의 **장기 의존성(Long-Term Dependency)** 문제
  - 입력 데이터가 길어질 수록(데이터의 타임스텝이 커질 수록) 학습 능력이 떨어짐
  - 입력 데이터와 출력 사이의 길이가 멀어질 수록, 연관관계가 적어짐
- **LSTM(Long Short Term Memory)** 레이어
  - 출력 외에 LSTM 셀 사이에만 공유되는 **셀 상태(cell state)**를 갖는 레이어
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b58e2ff7-c932-4146-b3cd-56c3a088b333)
    - Xt: 입력 데이터
    - H_t-1 이전 타입스탭의 은닉 상태
    - C_t-1: 이전 타입스탭의 셀 상태
    - 타임스탭을 가로지르며 셀 상태가 보존됨
      - 따라서 장기 의존성 문제를 해결할 수 있음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/197e8295-e69a-4705-ad5b-49a32d453a85)
  - Ct
    - Forget게이트 출력에 이전 타임스탭의 셀 상태가 얼만큼 남길지 결정됨
    - 새로 입력된 input 게이트의 출력과 ~Ct값을 곱해 위와 더하고, 다음 스텝의 셀 상태를 만듦
  - Ht
    - 계산된 Ct(셀 상태)에 tanh를 취한 값을 output 게이트의 출력에 곱합
```python
# 곱셈 문제, 고려해야할 실수의 범위가 100개, 마킹 인덱스가 되어 있는 두 실수를 곱한 값을 출력 해야함

# LSTM 레이어를 사용한 곱셈 문제 모델 정의
model = tf.keras.Sequential([
# 파라미터 수: 2* 30 + 30*30 + 30 = 990 * 4 = 3960
# 4를 곱하는 이유는 망각 게이트, 입력 게이트, 새로운 셀 상태 생성, 출력 게이트 때문임
tf.keras.layers.LSTM(units=30, return_sequences=True, input_shape=[100, 2]),

# 파라미터 수
# 30 * 30 + 30 * 30 + 30 = 1830 * 4 = 7320
    tf.keras.layers.LSTM(units=30),

# 파라미터 수 : 30 * 1 + 1 = 31
    tf.keras.layers.Dense(units=1)
])
model.compile(optimizer='adam', loss='mse') model.summary()
``` 

### GRU 레이어
- **GRU(Gated Recurrent Unit)** 레이어는 LSTM레이어보다 구조가 간단
- ![image](https://github.com/googoo9918/TIL/assets/102513932/69cdd66d-f840-498e-8d33-5fc9f63e5c11)
  - LSTM과 다르게 셀 상태가 없고, 다만 h_t가 비슷한 역할을 함
  - r_t
    - 리셋게이트, 이전 타임스텝의 정보를 얼마나 남길 것인가?
  - z_t
    - Update 게이트
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/326a8376-4129-4d18-a89d-dbea636045fc)
    - tanh를 통과한 ~h_t와 이전 타임스텝의 출력인 h_t-1은 z_t 값에 따라 최종 출력에서 어느 정도의 비율을 점유할지 결정됨
```python
# GRU 레이어를 사용한 곱셈 문제 모델 정의
model = tf.keras.Sequential([
# 파라미터 수: 2 * 30 + 30 * 30 + 30 = 990 * 3 = 2970
tf.keras.layers.GRU(units=30, return_sequences=True, input_shape=[100,2]),

# 파라미터 수 30 * 30 + 30 * 30 + 30 = 1930 * 3 = 5490
tf.keras.layers.GRU(units=30),
tf.keras.layers.Dense(units=1)
])
model.compile(optimizer='adam', loss='mse') model.summary(
```

### Embedding 레이어
- 임베딩 레이어는 자연어를 수치 정보로 바꾸기 위한 레이어
- 원 핫 인코딩은 차원 수만큼의 단어 표현 가능, 사용 메모리에 비해 너무 적은 정보량 표현
  - 임베딩은 정수 대신 **실수 값 사용, 한정된 벡터에서도 무한한 단어 표현** 가능
- ![image](https://github.com/googoo9918/TIL/assets/102513932/8627ba9b-cd52-4d41-875b-c7a7a4840300)
  - 정수 인덱스를 단어 임베딩 벡터로 변환
  - 임베딩 레이어는 정수 인덱스에 저장된 단어 수만큼 단어 임베딩 벡터를 갖고 있음
    - 필요할 때 꺼내 쓸 수 있는 저장 공간
  - 정수 인덱스로 저장하지 않는 단어에 대한 임베딩 값도 별도로 마련함
- 입력은 토큰화(Tokenization)하고 정제(Cleaning)하여 사용
  - 띄어쓰기 단위로 나누고, 불필요한 기호 등 제거

### 긍정, 부정 감정 분석
- 입력을 문장, 단어 수준으로 분리
- 문장 길이를 단어 수로 제한(25)
- 단어의 길이도 조정(5)
- 25단어가 되지 않는 문장은 0으로 padding 추가
```python
model = tf.keras.Sequential([
  # 단어 집합의 크기, 임베딩 벡터의 차원, 입력 문장의 최대 길이(최대 25개의 단어)
  # 파라미터 수: 단어 집합의 크기 * 임베딩 벡터의 차원 = 6,000,000
    tf.keras.layers.Embedding(20000, 300, input_length=25),
  # 파라미터 수: 300 * 50 + 50 * 50 + 50 = 17,550 * 4 = 70,200
    tf.keras.layers.LSTM(units=50),
  # 파라미터 수: 50*2 + 2 = 102
    tf.keras.layers.Dense(units=2, activation='softmax')
])

model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
model.summary()

history = model.fit(train_X, train_Y, epochs=5, batch_size=128, validation_split=0.2)

model.evaluate(test_X, test_Y, verbose=0)
```

### 자연어 생성
- 문자 단위의 순환 신경망으로 Latex 등의 구조화된 문서도 생성할 수 있음
- Tokenizer를 쓰지 않고 직접 토큰화
  - Tokenizer는 단어의 빈도 수로 단어 정렬
  - 단어의 수가 많고 모든 단어를 사용하기 때문에 여기서는 사용 X
- UNK는 임의의 문장 입력 시 텍스트에 존재하지 않는 단어를 나타낼 때 사용
```python
total_words = len(vocab)

model = tf.keras.Sequential([
    tf.keras.layers.Embedding(total_words, 100, input_length=seq_length),
    tf.keras.layers.LSTM(units=100, return_sequences=True),
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.LSTM(units=100),
    tf.keras.layers.Dense(total_words, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.summary()

```

## 오토인코더
- 지도 학습
  - 하나 이상의 입력 변수를 기반, 출력 변수를 추정하거나 예측
- 비지도학습
  - 출력변수 없이 입력변수만으로 자료의 상관관계나 특징을 찾아냄
- 오토인코더
  - 입력에 대한 출력을 학습해야 한다는 점은 다른 지도학습과 동일하나
  - 출력이 입력과 동일하다는 점이 특이함
    - 차원 축소, 데이터 압축, 노이즈 제거 등에 사용
- ![image](https://github.com/googoo9918/TIL/assets/102513932/5ef71f56-e5c8-40db-bba2-780cf0228bc7)
  - 인코더
    - 입력에서 잠재변수를 만듦
  - 디코더
    - 잠재변수를 출력으로 만듦
  - 잠재변수
    - 특징 추출기인 인코더를 통해 추출된 **일차원 벡터**, 데이터의 특징을 잘 표현함
  - 인코더와 디코더는 동일한 레이어를 대칭되는 구조로 쌓음
  - 오토 인코더는 손실 압축으로 입력과 출력이 완전히 동일하지 않음
    - 다만 압축률이 크게 상승
    - 압축률을 높이는 과정에서 특징을 잘 나태는 효율적인 방법을 찾음
      - 적은 양의 데이터로 많은 양의 정보를 표현해야 하기 때문

```python
import tensorflow as tf

# 데이터 전처리
train_X = train_X.reshape(-1, 28 * 28)
test_X = test_X.reshape(-1, 28 * 28)
print(train_X.shape, train_Y.shape)

# Dense 오토인코더 모델 정의
model = tf.keras.Sequential([
  # 인코더
    tf.keras.layers.Dense(784, activation='relu', input_shape=(784,)),
  # 잠재변수 레이어는 인코더와 디코더에 비해 뉴런의 수가 꽤 적음
    tf.keras.layers.Dense(64, activation='relu'),
  # 디코더
    tf.keras.layers.Dense(784, activation='sigmoid')
])

# 모델 컴파일
model.compile(optimizer=tf.optimizers.Adam(), loss='mse')

# 모델 요약 출력
model.summary()

# Dense 오토인코더 모델 학습
# 입력 데이터와 출력 데이터가 동일
model.fit(train_X, train_X, epochs=10, batch_size=256)
```

```python
import tensorflow as tf

# 데이터 전처리
train_X = train_X.reshape(-1, 28, 28, 1)
test_X = test_X.reshape(-1, 28, 28, 1)

# 컨볼루션 오토인코더 모델 정의
model = tf.keras.Sequential([
  # 파라미터 수
  # 2*2*1*32 + 32 = 160
  # 출력 차원: (14, 14, 32)
  # strid(2,2)로 설정, 풀링 레이어를 적용한 것과 같은 효과가 나오게 함
    tf.keras.layers.Conv2D(filters=32, kernel_size=2, strides=(2,2), activation='relu', input_shape=(28, 28, 1)),
  
  # 파라미터 수
  # 2*2*32*64 + 64 = 8256
  # 출력 차원: (7, 7, 64)
    tf.keras.layers.Conv2D(filters=64, kernel_size=2, strides=(2,2), activation='relu'),

  # 출력 형태(None, 7*7*64 = 3136) 
    tf.keras.layers.Flatten(),

  # 파라미터 수: 3136 * 64 + 64 = 200768 
    tf.keras.layers.Dense(64, activation='relu'),
  # 파라미터 수: 64 * 3136 + 3136 = 203840
    tf.keras.layers.Dense(7*7*64, activation='relu'),
  # 출력 형태 (7,7,64)
    tf.keras.layers.Reshape(target_shape=(7, 7, 64)),
  # 2*2*64*32 + 32 = 8224
  # Conv2DTranspos는 Conv2D레이어가 하는 일의 반대 되는 계산
  # 입력이 되는 하나의 값을 Convolution
    tf.keras.layers.Conv2DTranspose(filters=32, kernel_size=2, strides=(2,2), padding='same', activation='relu'),
  # 2*2*32*1 + 1 = 129
    tf.keras.layers.Conv2DTranspose(filters=1, kernel_size=2, strides=(2,2), padding='same', activation='sigmoid')
])

# 모델 컴파일
model.compile(optimizer=tf.optimizers.Adam(), loss='mse')

# 모델 요약 출력
model.summary()

```
- **Conv2DTranspose**
  - 디컨볼루션 레이어
  - 입력이 되는 하나의 값을 Convolution에 통과, 여러 값을 계산해주는 레이어
- **elu**
  - ReLU와 달리 음수를 받았을 때 0보다 조금 작은 음수를 출력

## K-평균 클러스터링
- ![image](https://github.com/googoo9918/TIL/assets/102513932/7822a24e-9d9d-4bb2-9066-2f0c44e7154d)
- 주어진 입력 중 K개의 클러스터 중심을 임의로 정함
- 각 데이터와 K개의 중심과의 거리를 비교해서 가장 가까운 클러스터로 배당
- K개의 중심의 위치를 해당 클러스터로 옮기고, 이를 반복
- ![image](https://github.com/googoo9918/TIL/assets/102513932/90847c88-cc9d-4caa-9794-379d23923dc0)
  - 클러스터 집합 C_1, C_2, ...., C_K
  - 클러스터 내의 관측치들이 서로 다른 정도
  - 클러스터 C_k 내의 관측치들이 중심으로부터 얼마나 떨어져 있는지?
  - |C_k|는 k번째 클러스터 내의 관측치 수
  - x_ij는 관측치 i의 j번째 특징
  - x_tj는 클러스터 중심의 j번째 특징 값
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/fcf1bcd0-b28d-46af-9845-94fb6516f0b5)
    - 목표는 결국 W(C_K)를 최소화 하는 것!
      - 클러스터 내 변동의 총합 최소화
- K-means 알고리즘은 데이터셋을 K개의 클러스터로 나누는 **비지도 학습**
  - 1. 초기 클러스터 할당
  - 2. 반복
    - 클러스터 중심 계산
    - 가장 가까운 클러스터로 할당
- 주요 문제점
  - 지역 최적화
    - 클러스터 할당이 랜덤으로 이뤄짐
    - 최종 클러스터링 결과는 초기 할당에 따라 달라질 수 있음
- **t-SNE**
  - 고차원의 데이터를 저차원의 시각화를 위한 데이터로 변환
  - SNE: Stochastic Neightbor Embedding(확률적 이웃 임베딩)
  - 가까운 거

## 강화 학습
- 강화학습
  - 실수와 보상을 통해 배우는 알고리즘
  - 강화학습은 좋은 선택과 나쁜 선택에서 모두 배움
  - 문제가 주어진 **환경**, 문제를 풀기 위한 **에이전트** 존재
  - **행동**으로 환경에 영향을 주고, 결과에 다라 **보상**을 받음
    - 좋은 보상을 받으면 에이전트는 그 행동을 더 많이 하게 됨
    - 나쁜 보상을 받으면 그 행동을 덜 하도록 하는 것이 강화학습의 기본
- MountatinCar-v0
  - 두 바퀴가 달린 차(에이전트)로 언덕(환경)을 올라가는 문제
    - **행동 공간(actions space)**에이전트가 취할 수 있는 행동은 '왼쪽으로 이동', '정지', '오른쪽으로 이동'
    - 왼쪽과 오른쪽으로 반복해서 움직이며 가속도를 붙여야만 언덕에 오를 수 있음
  - 보상
    - 각 시간 단위마다 -1이 주어지고, 오른쪽 깃발에 도달하면 하나의 episode가 끝남
  - episode를 빨리 끝낼 수록 한 episode에 얻는 보상의 총합이 커짐
    - 따라서 가장 짧은 시간내에 언덕을 올라가야 함
    - 200스텝 안에 깃발에 도달해야 함
- 상태와 행동을 분류 신경망의 입력과 출력으로 사용해볼 수 있음

```python
# 분류 신경망 정의
import tensorflow as tf
model = tf.keras.Sequential([
  # 열만 표시되어 있음 주의!! 2개의 특징을 갖는 1차원 벡터임
tf.keras.layers.Dense(128, input_shape=(2,), activation='relu'),
tf.keras.layers.Dense(32, activation='relu'),
tf.keras.layers.Dense(3, activation='softmax')
])
model.compile(optimizer=tf.optimizers.Adam(), loss='sparse_categorical_crossentropy', metrics=['accuracy’]) model.summary()
```
- **`model.predict()`**로 행동 예측
- **`np.argmax()`**로 행동 결정

## 큐러닝(Q-Learning)
- 강화학습의 대표적인 방법론
- 행동 공간이 이산적이지 않고 연속적인 환경
  - -1과 1사이의 값을 지정, 왼쪽이나 오른쪽으로 움직일 수 있게됨
- 큐러닝
  - 관찰 상태에서 취할 수 있는 모든 행동의 **Q(Quality)** 값을 학습
  - 특정 상태에서 어떤 행동의 Q값이 다른 행동 보다 높다면, Q값이 높은 행동을 우선적으로 선택
  - 가장 높은 Q값을 가진 행동을 선택하거나, softmax 함수로 각 Q값을 입력으로 삼아 확률을 기반으로 한 행동을 선택할 수도 있음
  - 모든 행동의 Q값을 구한 것이 Q 테이블
    - Q테이블을 학습시키는 과정이 큐러닝
  - Q테이블은 관찰공간(2) + 행동공간(1) = 3차원
- 어떤 상태의 큐 함수를 구하려면 현재 가치와 미래 가치를 모두 고려해야 함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/2b39ff97-41b9-4ada-8ddc-36ac186edd45)
  - 미래 가치는 각 스텝마다 받는 보상과 행동의 결과로 바뀐 다음 상태에서 얻는 큐함수의 값
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b850577b-d207-4fb7-9990-6113860ac054)
  - 미래에 받는 보상은 불확실성 떄문에 현재보다 가치가 낮아 감가율 적용
- ![image](https://github.com/googoo9918/TIL/assets/102513932/12805240-00d9-43c9-bdac-d79354799831)
  - 취할 수 있는 행동 중 큐함수의 값이 제일 높은 행동을 할 것이기에
  - 최대값만 고려하면 됨
- ![image](https://github.com/googoo9918/TIL/assets/102513932/6ebee74d-f3ae-4e61-ac1b-af505708efb7)
  - n번째 수인 An을 더해 새로운 평균을 구하는 수식
- ![image](https://github.com/googoo9918/TIL/assets/102513932/23a697e7-3ca9-42d3-b5a1-c1c2e2d7d5ce)
  -  α(학습률)로 바꾸면 평균값을 일정하게 변화할 수 있음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/05e90340-966e-4d2f-ba83-6cd2bedd36f4)
  - 어렵구만