# 빅데이터 분석
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
[7 9]

```