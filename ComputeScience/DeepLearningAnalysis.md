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
      - k
        - 은닉 유닛(은닉 노드)의 수를 나타냄
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

### Lab in Python
- 데이터 준비
```python
# 결측치 제거
.dropna()

# 훈련, 테스트 데이터 분할
train_test_split(X,Y, test_size = 1/3, random_state=1)
```
- 선형 회귀 모델
```python
# 회귀 모델 적합
.fit(X_train, Y_train)

# 예측
.predict(X_test)
```
- 라쏘 회귀 모델
```python
# 데이터 학습 + 변환 과정 결합
scaler.fit_transfrom(X_train)
# 적합
grid.fit(X_train, Y_train)
# 예측
.predict(X_test)
```
- 신경망 모델
```python
# 입력 데이터 1차원 배열로 평탄화
self.flatten()

# 신경망 계층 순차적 정의
# 입력 차원을 50개의 뉴런으로 매핑 / 비선형 활성화 함수 -> 음수 입력을 0으로 만듬 / 과적합 방지를 위해 뉴런 40%를 임의로 비활성화 / 50개 뉴런을 최종 출력인 1차원으로 매핑
self.sequential = nn.Sequential(
            nn.Linear(input_size, 50), nn.ReLU(), nn.Dropout(0.4), nn.Linear(50, 1)
        )
# Hitters의 19개 특성이 50차원으로 매핑 -> 50 * (19+1)의 파라미터가 됨
# 이후 40%의 드롭아웃 계층을 거침
# 마지막으로 1차원으로의 선형 매핑, 다시 편향이 도입됨 50 +1
# 따라서 전체 파라미터는 1000 + 50 + 1 --> 1051임
```
- PyTorch
```python
# NumPy배열에서 파이토치 텐서로 변환
torch.tensor(X_train.astype(np.float32))

# 회귀 모델 설정, 평가 지표로 평균 절대 오차 사용
hit_module = SimpleModule.regression(hit_model, metrics={'mae':MeanAbsoluteError()})

# SGD(확률적 경사 하강법)은 데이터셋의 일부만을 사용, 각 단계에서 그리디언트 계산하고 모델 매개변수를 업데이트함
# 에폭 계산, 에폭은 훈련 데이터셋을 한 번 순회하는 것을 의미함 hit_dm에서 batch_size를 32로 지정했으므로, 한 에폭은 175/32 = 5.5 SGD 단계임 --> 총 6번의 단계가 필요하게 됨
```

- 모델 평가
```python
# 모델을 평가 모드로 설정, 드롭아웃과 같은 레이어 비활성화
.eval()
```

- Multilayer Network on the MNIST Digit Data
```python
self.layer1 = nn.Sequential(
            nn.Flatten(),
            nn.Linear(28*28, 256),
            nn.ReLU(),
            nn.Dropout(0.4))
        self.layer2 = nn.Sequential(
            nn.Linear(256, 128),
            nn.ReLU(),
            nn.Dropout(0.3))
        self._forward = nn.Sequential(
            self.layer1,
            self.layer2,
            nn.Linear(128, 10))
# 1*28*28의 이미지를 28*28 요소의 1D 벡터로 평탄화
# 평탄화된 벡터를 256차원의 히든 레이어로 선형 변환
# 이때 파라미터 --> 785 * 256 = 200, 960

# 256 -> 128
# 257 * 128 = 32,896

# 128 -> 10
# 129 * 10 = 1,290
```
- 다중 클래스 로지스틱 회귀 모델
```python
# 분류 모델 생성, (훈련 모델, 클래스 수)
SimpleModule.classification(mlr_model, num_classes=10)

# 모델 적합
.fit(mlr_module, datamoudle=mnist_dm)

# 모델 평가
.test(mlr_module, datamodule=mnist_dm)
```

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
- 각 학습 이미지는 여러번 복제 되고, 각 복제본은 임의로 왜곡됨
  - 이때 이 외곡은 확대, 수평 및 수직 이동, 회전 등을 포함함
  - 인간의 이식에는 영향을 미치지 않음
- 학습 데이터 셋을 상당히 확장하여 다른 예제를 생성하는 방법
  - 이로써 과적합을 방지함
- 일종의 정규화 처럼 동작

- ![image](https://github.com/googoo9918/TIL/assets/102513932/f259842b-e15e-49a5-86a6-71eb59abedb5)

### Lab in Python
- CNN on CIFAR100 data
```python
# 텐서들을 하나의 텐서로 결합
torch.stack()

# 난수 생성기 객체 rng 초기화 --> 시드 값은 결과의 재현성 보장
rng = np.random.default_rng(4)

# 데이터 셋에서 고유한 인덱스 무작위 선택
rng.choice()

# 입력 채널 수와 출력 채널 수를 기반으로 3*3 커널 크기의 convolution 연산을 정의
# 패딩이 same, 입력과 출력의 공간 크기를 유지함
# 예를 들어, 입력 이미지가 6*6이고 필터가 3*3, 스트라이드(필터가 한 번에 움직이는 칸 수)가 1이라 하자
# 패딩이 없는 경우, 출력 크기는 (입력 크기 - 필터 크기 + 1) / 스트라이드 이다.
# (6-3+1)/1 =4 --> 4*4 출력 이미지가 생성된다.
# 패딩이 있는 경우, 패딩은 (필터 크기 - 1)/2 이므로, (3-1)/2 =1 이고, 각 변에 패딩1을 추가한다.
# 즉, 입력 이미지의 각 변에 패딩을 추가하여 입력 이미지의 크기를 키우고, 전체 이미지에 대한 합성곱을 수행한다.
        self.conv = nn.Conv2d(in_channels=in_channels, out_channels=out_channels, kernel_size=(3,3), padding='same')
        self.activation = nn.ReLU()
        # 2*2 맥프 룰링이 연속적으로 적용
        self.pool = nn.MaxPool2d(kernel_size=(2,2))

# CIFAR100의 이미지 크기는 3 * 32 * 32임, 3개 채널, 32*32 픽셀
# 현재 필터의 크기가 3*3 이므로 BuildingBlock(3,32)에서 파라미터 개수는 다음과 같음
# input --> (3 * 3 * 3(차원)) * 32 + 32
# 32 -> 64는 어떠한가?
# 3*3*32*64 +64
# 64->128, 128->256 또한 마찬가지이다.
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
```

- Using Pretrained CNN Models
```python
# mps와 같은 GPU 기반 가속화 기술을 통해 훈련 속도를 크게 높임
cifar_trainer_mps = Trainer(accelerator='mps', deterministic=True, max_epochs=30) cifar_trainer_mps.fit(cifar_module, datamodule=cifar_dm) cifar_trainer_mps.test(cifar_module, datamodule=cifar_dm)
```

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
  - 라쏘 회귀와 두 개의 은닉층을 가진 신경망 모델 비교
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
  - 혹은 시퀀스일 수도 이씅ㅁ
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
    - 각 은닉 유닛 Ak의 계싼은 입력 X와 이전 은닉 상태 A에 대한 가중치 행렬 W와 U를 사용함
    - 이 공식은 A_k = g(WkX + UkA)로 표현됨
      - g는 비선형 활성화 함수
  - 가중치
    - W: K * (p+1)행렬, 입력층의 가중치
    - U: K*K 행렬, 은닉층에서 이전 은닉 상태로부터 다음 은닉 상태로의 전이를 결정
    - B: 출력층의 가중치 벡터, K+1 차원
  - 출력
    - 출력 O는 O = BA로 계산됨
    - B는 출력층의 가중치임

### RNN and IMDB Reviews
- 문서 특성
  - 문서의 특성은 단어의 시퀀스 W로 표현됨
  - 일반적으로 모든 문서를 동일한 단어 수 L로 자르거나 채워 문서의 길이를 통일
- 단어의 표현
  - 각 단어 Wi는 10,000길이의 one-hot 인코딩된 벡터 X로 표현됨
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

# 이진 분류 작업을 위한 모듈 생성
imdb_module = SimpleModule.binary_classification( imdb_model, optimizer=imdb_optimizer)

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

### 숙제
- 숙제를 보면