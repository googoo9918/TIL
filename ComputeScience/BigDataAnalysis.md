# 빅데이터 분석
## 4차 산업혁명의 이해
- 데이터 유형
  - Nominal
    - 명목형 데이터
    - ex) 성별, 혈액형
  - Numeric
    - 수치형 데이터
    - 숫자로 표현
- 초연결
  - 사물과 공간, 제품과 서비스 연결성 무한 확장
    - 사물인터넷, 5G
  - 사물인터넷
    - IoT
      - 언제나, 어디서나, 어느 것과도 연결될 수 있는 새 통신 환경
    - RFID 태그를 읽는 센서 네트워크(USN) -> 사물과 사물 통신(M2M) -> 상호 통신 만물 인터넷(IoE)
  - 5G
    - 초고속, 초연결, 초저지연
  - 인공지능
    - 초지능
      - 하이퍼 인텔리전스
        - 인간과 인공지능 협력 + 사물 지능화
      - 슈퍼 인텔리전스
        - 인공지능의 지능이 인간을 넘어섬
  - 빅데이터
    - 디지털 환경에서 발생하는 모든 데이터
    - 4차 산업 전 분야에서 분석 및 활용
  - 초융합
    - 디지털 트랜스포메이션
      - 디지털 기술 활용, 프로세스 변화를 야기함
  - 데이터 과학
    - 다양한 데이터로부터 지식과 인사이트를 추출, 여러 알고리즘과 시스템을 동원
    - 데이터 과학과 IoT + 빅데이터 + AI
      - IoT 구성 센서와 노드 : 감각 및 행동 기관
      - 빅데이터: 외부 센싱 데이터, 내부 처리 결과 데이터
      - 5G : 신경계
      - AI : 두뇌
      - 다 선순환하여 발전 + 진화해야함
    - ex) 자율주행차
      - 인지, 판단, 제어 3 단계
      - 5레벨로 나뉨
    - 커넥티드 카
      - 차량과 도시의 모든 곳 연결, 위험 감지 및 거리 속도 제어
    - 스마트 시티
      - 기관들의 네트워킹이 가능하도록 통신 인프라가 갖춰진 것
    - 스마트 헬스 케어
      - 건강 관련ㅅ ㅓ비스 + 의료 IT 융합 종합 의료 서비스
## 파이썬 리뷰
- 파일 읽기
```python
fd=open("studentlist.txt")

fd.readline() # 헤딩 
lines= fd.readlines()
print ("line# : ", len(lines))
# line# : 17
```

- 필드 분할, type 가공
```python
Table=[] # 전체 레코드를 담은 테이블
# 리스트 생성, 1차원이든 2차원이든 선언 방식은 같음

for line in lines :  # 한 줄 씩  line 변수에 담아서
    
    fields= line.split(",") # , 를 기준으로 나눈다. -> fields라는 list가 만들어진다.
    # name,sex,age,grade,absence,bloodtype,height,weight,dept
    #  0    1   2    3    4        5         6     7      8

    fields[2]= int(fields[2])  # 나이를 숫자로 바꿔 넣기

    fields[6]= float(fields[6])  # 키를 숫자로 바꿔 넣기
    fields[7] = float(fields[7]) # 몸무게도 
    fields[8]= fields[8].strip()
    # 공백 제거
          
    Table.append(fields)  #레코드를 테이블에 추가 

print (Table[1:3])
# [['이미린', '여자', 22, '2', '무', 'AB', 170.1, 53.0, '03'], ['홍길동', '남자', 24, '4', '무', 'B', 175.0, 80.1, '01']]
# 파이썬도 0번째 index부터 저장, 파이썬은 슬라이싱에서 끝 인덱스가 포함되지 않음
# 두 번쨰와 세 번째 레코드가 출력됨
```

- 정렬
```python
Table= sorted(Table, key = lambda col : col[2] ) # 나이로 정렬
for record in Table :
    print ( record[0], record[1], record[2])

# Table 리스트에 있는 레코드를 나이를 기준으로 오름차순 정렬
# sorted() 함수는 주어진 시퀀스의 모든 항목을 정렬한 새로운 리스트를 반환함
# 손세수 여자 20
# 박미희 여자 21
# 김민수 남자 21
# 여수근 남자 21
# 이미린 여자 22
# 강수친 여자 22
# 방희철 남자 22
# 임동민 남자 22
# 김미진 여자 22
# 김길동 남자 23
# 김철수 남자 23
# 이희수 여자 23
# 이철린 남자 23
# 이희진 여자 23
# 홍길동 남자 24
# 박수호 남자 24
# 김동수 남자 24
```

- 기술 통계: 평균 구하기, 키 평균
```python
heightSum=0
for rec in Table :
    height= rec[6]
    heightSum+=height

print (heightSum / len(Table))
# 현재 테이블은 2차원 배열임
# 행의 갯수는 len(Table), 열의 갯수는 len(Tale[0])
```

- 기술통계 : 도수 구하기
```python
dept_dic= {}  # {학과 코드: 도수}
# 위 선언문은 딕셔너리 선언문, 해쉬라고 생각하면 편하다.
for rec in Table :
    dept_code=rec[8]
    if dept_code in dept_dic:
        dept_dic[dept_code]+=1
    else :
        dept_dic[dept_code]=1

print (dept_dic)
# {'01': 6, '03': 6, '02': 5}
```

- Join
  - 학과 코드 파일 읽어서 도수 결과에 표기
```python
deptcode={}

f= open("deptcode.txt")
# 01, 소프트웨어
for line in f:
    code, name = line.split(",")
    deptcode[code] = name.strip()
    print(deptcode)
# {'01': '소프트웨어'}
# {'01': '소프트웨어', '02': 'AI융합'}
# {'01': '소프트웨어', '02': 'AI융합', '03': '통계보험'}

for key, value in deptcode.items():
    print(key, value)
# 01 소프트웨어
# 02 AI융합
# 03 통계보험

for key, value in dept_dic.items():
    print(deptcode[key], value)

# 소프트웨어 6
# 통계보험 6
# AI융합 5
```

- group by: 혈액형 별 키 평균
  - 혈액형 별 키 합계, 도수 집계
```python
b_table={}  # 집계 테이블 : { 혈액형 : [합계, 도수] }

for rec in Table :
    bloodtype=rec[5]
    if bloodtype in b_table:  # 진료과목이 있는지
        b_table[bloodtype]= [ b_table[bloodtype][0]+rec[6], b_table[bloodtype][1]+ 1]  # 키 합계, 갯수 
    else:
        b_table[bloodtype]= [rec[6], 1]  # 키 , 갯수 

print ( b_table)
# {'A': [676.3, 4], 'O': [825.7, 5], 'AB': [532.2, 3], 'B': [856.4, 5]}

for key, value in b_table.items():
    print ( key , value[0]/value[1] )
# A 169.075
# O 165.14000000000001
# AB 177.4
# B 171.28
```

- 성별, 혈액형 별 몸무게 평균
- 1
```python
b_table = {}

for record in Table :
    bloodtype = rec[5]
    sex = rec[1]
    key = (sex, bloodtype)
    if key in b_table:
        b_table[key] = [sex, bloodtype, b_table[key][2] + rec[6], b_table[key][2]+1]
    else:
        b_table[key] = [sex, bloodtype, rec[6], 1]

for key, value in b_table:
    print(key, value[0]/value[1])

# 남자 O 65.1
# 여자 AB 53.0
# 남자 B 68.95
# 남자 AB 80.75
# 여자 A 52.25
# 여자 O 50.13333333333333
# 남자 A 58.75
# 여자 B 45.2
```
- 2
```python
    
b_table = {}

for rec in Table :
    bloodtype = rec[5]
    sex = rec[1]
    key = (sex, bloodtype)
    if key in b_table:
        b_table[key] = [b_table[key][0] + rec[7], b_table[key][1]+1]
    else:
        b_table[key] = [rec[7], 1]

for key, value in b_table.items():
    # .items() 꼭 써야함 주의
    print(key, value[0]/value[1])
```

## Numpy
- 기본
  - `ndarray`
    - 1가지 type의 값들로 구성
    - axis = 차원
  - 속성
    - `ndarray.ndim` : 차원수
    - `ndarray.shape` : 데이터 모양
    - `ndarray.size` : 전체 값 개수
    - `ndarray.dtype`: 값의 type
      - ex) int32
    - `ndarray.itemsize`: 각 값의 크기(btype)
      - ex) 4
```python
import numpy as np
ar1 = np.arange(15).reshape(3,5)

ar1
# array([[ 0,  1,  2,  3,  4],
#        [ 5,  6,  7,  8,  9],
#        [10, 11, 12, 13, 14]])

ar1.shpae
# (3,5)

ar1.ndim
# 2

ar1.size
# 15
```
- 생성
  - `np.array(파이썬 리스트)`
  - `np zeros(shape) or np.ones, np.empty`
  - `np.arrange(size) or np.arrange(시작, 끝, 간격)`
```python
np.array([1, 2, 3, 4, 5])
# array([1, 2, 3, 4, 5])

np.zeros((3,4))
# array([[0., 0., 0., 0.],
#        [0., 0., 0., 0.],
#        [0., 0., 0., 0.]])

np.arrange(15)
# array([ 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14])

np.arrange(5, 20, 2)
# array([ 5,  7,  9, 11, 13, 15, 17, 19])
```

- Operations
  - 사칙연산 : 벡터*스칼라 => 각 element와 스칼라 연산
  - 각 element에 적용되는 연산
    - exp(), sqrt()...
  - 벡터 * 벡터 => 일반 벡터연산이 아닌, 같은 위치의 element 끼리 연산
  - 벡터 곱은 @
  - +=도 동작
```python
A = np.array( [[1,2], [3,4]])
B = np.array ( [[2,3], [4,5]])

A
# array([[1, 2],
#        [3, 4]])

A*4
# array([[ 4,  8],
#        [12, 16]])

A+=3
A
# array([[4, 5],
#        [6, 7]])

A+B
# array([[ 6,  8],
#        [10, 12]])

np.sqrt(A)
# array([[2.        , 2.23606798],
#        [2.44948974, 2.64575131]])

B
# array([[2, 3],
#        [4, 5]])

A*B
# array([[ 8, 15],
#        [24, 35]])

A@B   # 내적 연산
# array([[28, 37],
#        [40, 53]])
```
- random
  - `randn(모양)`: 정규분포
  - `randint(최소값, 최대값, size=)` : 정수
- sum, max, min

```python
np.random.randn(3,2)
# array([[ 1.40289842,  1.30220811],
#        [ 0.37205391,  0.94744699],
#        [ 0.64440263, -0.91700387]])
np.random.randint(1, 100, size=6).reshape(3,2)
# array([[11, 57],
#        [86, 87],
#        [58, 62]])

A.sum()
# 22

A.min()
# 4
```

- **axis에 따른 연산**
  - 주요 파라미터
    - axis = 차원 기준
  - `sum(axis=0)`
    - 첫 번째 차원으로 합
    - 주로 *열을 따라*라는 의미로 사용
  - `sum(axis=1)1
    - 두 번째 차원으로 합
    - 주로 *행을 따라*라는 의미로 사용
```python
B=np.arange(12).reshape (3,4)  
# 첫번째 차원 =3개 element , 두번째 차원 =각기 element 는 4개 짜리 array
B
# array([[ 0,  1,  2,  3],
#        [ 4,  5,  6,  7],
#        [ 8,  9, 10, 11]])

B.sum(axis=0)
# array([12, 15, 18, 21])

B.sum(axis=1)
# array([ 6, 22, 38])

B.max(axis=0)
# array([ 8,  9, 10, 11])
```

- 1부터 30까지의 숫자로(5,3,2) 3차원을 만든 뒤, 각 숫자에 0과 1 사이의 random noise를 곱한 결과를 출력하라
```python
array = np.arrange(1,31,1).reshape(5,3,2)

random_noise = np.random.rand(5,3,2)

result = array * random_noise

print(result)
```

- Indexing, slicing
  - 1차원: element A[2], 범위A[2:5]
    - A[(1,2,4)] : multi index
  - 2차원: element B[2,3], 범위 B[1:3, :], :는 전체를 의미함
```python
A=np.arange (5, 15, 1)
print (A)
print (A[2])

# [ 5  6  7  8  9 10 11 12 13 14]
# 7

print (A[(2,4),]) 
# 첫번째 axis에 대한 것을 명시 필요
# 즉, `,`를 통해서 뒤에 추가 차원을 지정하지 않았다는 것을 나타낼 수 있음
# print(A[[2,4]]) 등으로 대체 가능함
# [7 9]

A[1:3]
# array([6, 7])

B=np.arrange(30).reshape((5,6))
B

B[3,2]
# 20

B[3, 2:4]
array([20,21])

C=np.arrange(45).reshape((5,3,3))
C

C[:,:,1:2]
# :는 차원의 모든 요소를 의미함
# array([[[ 1],
#         [ 4],
#         [ 7]],

#        [[10],
#         [13],
#         [16]],

#        [[19],
#         [22],
#         [25]],

#        [[28],
#         [31],
#         [34]],

#        [[37],
#         [40],
#         [43]]])
# slicing과 indexing의 차이를 인지하고 있어야 함
# slicing을 할 때는 차원이 유지되지만
C[:,:,1]
# array([[ 1,  4,  7],
#        [10, 13, 16],
#        [19, 22, 25],
#        [28, 31, 34],
#        [37, 40, 43]])
# 이처럼 indexing을 진행하는 경우 3차원이 2차원으로 변경됨

# ... : 중간 inx 생략
C[..., 1]
# 위와 결과 값이 같음
# C[1, ...] , C[1, ..., 1] 처럼 사용 가능
```

- np.arrange(30).reshape(5,6)에서 0,2,4,... column(axis=1)만 뽑아서 새로운 array를 만드시오
```python
array = np.arrange(30).reshape(5,6)
new_array = array[:,[0, 2, 4]]
print(new_array)
```
## 빅데이터의 이해와 활용
- 빅데이터 분류
  - 정형 데이터
    - 고정된 필드에 저장, 스키마 형식에 맞춤
      - ex) RDB, 스프레드시트
    - 수집하기 쉬움, 처리 난이도 낮음
  - 반정형 데이터
    - 메타데이터나 스키마 등을 포함
      - ex) XML, HTML, JSON 웹 문서, 웹 로그
    - API 형태로 제공, 데이터 처리 기술 요망
      - 처리 난이도 중간
  - 비정형 데이터
    - 데이터 구조 일정하지 않음 
      - ex) 이미지, 텍스트
    - 파일을 데이터 형식으로 파싱
      - 처리 난이도 상
- 빅데이터의 특징
  - 데이터 측면 5V
    - Volume(규모)
    - Variety(다양성)
    - Velocity(속도)
    - Veracity(정확성)
    - Value(가치)
  - 혁신과 창조의 도구
    - 효율화, 개인화, 미래 예측
  - 가트너의 단계적 빅데이터 자원 확보
    - 저장 -> 공유 -> 통합 -> 공동 창출
- 빅데이터 플랫폼
  - 발전 과정
    - 파일시스템 -> 데이터베이스 -> 빅데이터 플랫폼
  - 개념
    - 빅데이터 처리, 고속 저장, 고성능 계산
    - 비식별화 중요
  - Data Sources -> Data Acquistion -> Data Storage -> Data analysis -> Reporting & visualization
- 빅데이터 분석 방법론
  - 연구 목표 설정 -> 데이터 수집 -> 데이터 준비 -> 데이터 탐색 -> 데이터 모델링 -> 결과 발표 및 자동화
  - 연구 목표 설정
    - 연구 목표 정의 + 산출물 + 일정 등 계획 합의
  - 데이터 수집
    - 필요한 데이터 위치와 형태 확인 + 원시 데이터 수집
    - 다양한 수집 기술
      - 크롤링, FTP, Open API, RSS(웹 기반 최신 정보 공유), 스트리밍, 로그 수집기, RDB 수집기
  - 데이터 준비
    - 원시 데이터 정제 후 사용 가능한 형태로 제공
      - 오류 여과, 수정하여 정제
    - 필요에 다라서 데이터 통합 + 형태 변환
  - 데이터 탐색
    - 데이터와 변수 간 관계, 상호 작용 이해
    - 변수 간 관련성, 데이터 분포, 편차, 패턴 존재 여부 확인
      - 탐색적 데이터 분석[EDA]
      - 그래프, 히스토그램, 분포도 등 사용
  - 데이터 모델링
    - 데이터 탐색 결과로 프로젝트에 대한 답을 찾는 관계
    - 변수 선택, 모델 구성, 실행 평가 과정을 반복 수행
  - 결과 발표 및 분석 자동화
    - 연구 목표 달성 여부를 이해 당사자, 의사 결정자에게 이해시킴
    - 분석 결과 발표 + 분석 과정 재사용을 위한 자동화
- 빅데이터 분석 방법론
## Pandas
- dataframe
  - 2차원 구조, column 마다 다른 데이터 type
  - row = 레코드, col = 필드
  - 데이터처리, 통계 등은 column 단위로 이루어짐
  - tabular data(일반 csv 데이터 구조 처리 적합)
- 만들기
  - 딕셔너리로부터 만들기
    - {'컬럼명': [값, 값, ...], '컬렴명':[값, 값, ...]}
    - pd.DataFrame(딕셔너리)
  - index와 column labe(컬럼명)
```python
import pandas as pd
dic= { 'gender' : [ 1, 2, 1,2], 'bloodtype': ["A", "B", "O", "AB"]}

df1= pd.DataFrame(dic)

df1
#   gender	bloodtype
# 0	1	A
# 1	2	B
# 2	1	O
# 3	2	AB
```
- numpy array를 사용 가능
  - DataFrame(array, index= row index array, columns = 컬럼명 array)
```python
df = pd.DataFrame(np.random.randn(6,4), index=np.arrange(6). columns=list("ABCD"))
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/29625bba-5761-4fe6-a2a8-8d442f723ce8)
- 보기
  - `head()`, `tail()`
    - 최초, 마지막 5개
  - `head(n)`, `tail(n)`
    - 최초, 마지막 n개
  - index, columns: row, column 이름
    - `df.index`, `df.columns`
  - `dsecribe`
    - count, mean, std, min, 25%, 50%, 75%, max 등 제공
  - `df.sort_values(by="B")`
    - value 기준 오름차순 정렬
```python
import pandas as pd
df=pd.read_csv("studentlist.csv", encoding="cp949")
df
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/47916c1b-db0d-4965-92df-12c15ce9d586)

```python
df[0:3] #rows

df[["age", "dept"]]
# column

# 숫자 index에 의한 참조 : iloc
df.iloc[1:5, 0:3]
# 1~4, 0~2 출력

df.iloc[ [1,2,4], [0,2]]
# 1,2,4 행 0,2 열 출력

# label에 의한 참조: loc
df.loc[0:3, ["age", "grade"]]
# age와 grade열 0~3번째 행 출력
# 행과 열을 한 번에 선택할 수 있다.

# 값에 의한 참조: boolean indexing, 조건에 맞는 row 선택
df[df["bloodtype"] == "B"]

# 포함조건 isin()
df.loc[df["bloodtype"].isin(["B", "A"]), ["name", "Age"]]

# column 연산
# column.연산함수()
# 함수(column)
df["age"].mean()
np.mean(df['age'])
```

- 혈액형이 B형인 사람의 키의 평균을 구하시오
```python
df.loc[df["bloodytype"] == "B", "height"].mean()
# loc를 사용하면 행과 열을 한 번에 표시

df[df['bloodtype'] == 'B']['height'].mean()
# 값에 의한 참조는 행과 열을 따로 표시
```

- Operations
  - `concat(objects, axis =0 or 1)`
```python
df2 = df[1:3]
df3 = df[5:8]
df4 = pd.concat([df2, df3], axis=0)
df4
```
- axis 0인경우
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c311a631-cb0e-474d-86e0-4ff79bf848d4)
- axis 1인경우
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9fc54336-1b23-427d-a28b-0233789c56d8)

```python
df2= df[["age", "grade"]]
df3= df[["height", "weight"]]
df4=pd.concat([df2, df3], axis=1)
df4
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/3a03d33b-1e3d-47fa-ba6c-ebaeb19c2699)

- 각 사람의 BMI(몸무게 kg/(키 m^2))를 구하여 column을 추가하여라
```python
df['BMI'] = df['weight'] / (df[height]/100)**2
```

- `merge(A,B, on=키)`
  - 기본 inner join
  - `df3 = pd.merge(df, dff, left_on='name', right_on='realname')`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/59fb8649-3b68-4e9c-8609-ae411ebcfcb9)
- grouping
  - groupby
  - `df.groupby("grade").mean()`
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/409b56e8-9ff8-4f33-8e3a-2e0f895fde4e)
  - `df.groupby(["sex","bloodtype"]).mean()`
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/471c848e-b4cf-40bb-9b7c-e4ee97d25058)
  - `df.groupby(["sex", "bloodtype"]).size()`
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/27d62ab4-d6f7-44c1-9b61-635773838da6)

## matplotlib
- 기본 내용
  - `plt.plot(x축 데이터, y축 데이터, plottype)`
  - `plt.plot([1, 2, 3, 4], [1, 4, 2, 3])`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b73a9e71-427a-42b9-aa1c-be33efc0fdfb)
- input data 형식
  - numpy array 이어야 함
  - pandas column도 가능
- plot type
  - "ro" red circle
  - "b-" solid blue line default
  - "r--" red dasesh
  - "bs" blue squares
  - "g^" green trianles
- `plt.plot( df[["height", "weight"]] )`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/75aca7bf-1704-4acc-8cf1-fabfb8df71f8)
- `plt.plot( df["height"], df["weight"] , 'ro')`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b336ff63-dd7f-4a75-b27f-f3dda5091df3)
- `t = np.arrange(0., 5., 0.2)`
  - `plt.plot(t, t, 'r--', t, t**2, 'bs', t, t**3, 'g^')`
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/261dae8f-f637-41a1-ac51-3b6618d00f52)
- 여러 chart
  - `figure`
    - 여러 axes를 포함
  - `subplot()`
    - 하나의 axes를 만듬
  - `bar(), scatter(), plot()`
    - 각기 다른 모양 plot()의 파라미터로도 표현 가능
```python
names = ['group_a', 'group_b', 'group_c']  # X
values = [1, 10, 100] # Y

plt.figure(figsize=(7, 3))  #전체 가로세로 크기

plt.subplot(231)   # 2 x 3 구성의 1번
# 여러개의 그래프를 하나의 그림 안에 배치할 수 있게 해줌
# 2x3 격자에서 1번 위치에 서브플롯을 생성
plt.bar(names, values)  
plt.subplot(232)
plt.scatter(names, values)
plt.subplot(236)
plt.plot(names, values)
plt.suptitle('Categorical Plotting')
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/1f42f22d-765e-45c9-acb2-6e48a2f80fe4)

- studentlist의 혈액형 별 도수를 bar 차트로 그리시오
```python
bloodtype_counts = df['bloodtype'].value_counts()
# df.groupby('bloodtype').size()도 가능함
bloodtype_counts.plot(kind='bar')

plt.bar(bloodtype_counts.index,bloodtype_counts.values)
```

- 2가지 이상의 정보를 2차원에 표시
  - `scatter(x, y, c=유형벌 컬러, s=사이즈, data = label을 가진 원본 데이터)`
  - `plt.scatter("height", "weight", c="grade", data=df)`
    - height가 x축, weight가 y축, grade를 통해 색깔을 결정, 원본 데이터는 df
- studentlist의 키와 몸무게를 시각화하되, 학과 별로 다른 색상으로 학년을 크기로 표현하는 차트를 그리시오
```python

```