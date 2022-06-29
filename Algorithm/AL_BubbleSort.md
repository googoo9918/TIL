## 버블 정렬 (bubble sort)

### 알고리즘 연습 방법
1. 연습장과 펜을 준비합니다. <br><br>
2. 알고리즘 문제를 읽고 분석한 후에, <br><br>
3. 간단하게 테스트용으로 매우 간단한 경우부터 복잡한 경우 순서대로 생각해보면서, 연습장과 펜을 이용하여 알고리즘을 생각해봅니다. <br><br>
4. 가능한 알고리즘이 보인다면, 구현할 알고리즘을 세부 항목으로 나누고, 문장으로 세부 항목을 나누어서 적어봅니다. <br><br>
5. 코드화하기 위해, 데이터 구조 또는 사용할 변수를 정리하고, <br><br>
6. 각 문장을 코드 레벨로 적습니다. <br><br>
7. 변수가 코드에 따라 어떻게 변하는지를 손으로 적으면서, 임의 데이터로 코드가 정상 동작하는지를 연습장과 펜으로 검증합니다. <br>
    
<br>

### 1. 정렬 (sorting) 이란?
- 정렬 (sorting): 어떤 데이터들이 주어졌을 때 이를 정해진 순서대로 나열하는 것
- 정렬은 프로그램 작성시 빈번하게 필요로 함
- 다양한 알고리즘이 고안되었으며, 알고리즘 학습의 필수

> 다양한 정렬 알고리즘 이해를 통해, 동일한 문제에 대해 다양한 알고리즘이 고안될 수 있음을 이해하고,
> 각 알고리즘간 성능 비교를 통해, 알고리즘 성능 분석에 대해서도 이해할 수 있음

### 2. 버블 정렬 (bubble sort) 란?
* 두 인접한 데이터를 비교해서, 앞에 있는 데이터가 뒤에 있는 데이터보다 크면, 자리를 바꾸는 정렬 알고리즘

#### 직접 눈으로 보면 더 이해가 쉽다: https://visualgo.net/en/sorting

<img src="https://upload.wikimedia.org/wikipedia/commons/9/9c/Insertion-sort-example.gif" />

### 3. 어떻게 코드로 만들까?

> 알고리즘 연습 방법에 기반해서 단계별로 생각해봅니다.

- 데이터가 두 개일 때 버블 정렬 알고리즘
  
```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<Integer> dataList = new ArrayList<Integer>();
dataList.add(4);
dataList.add(2);

if (dataList.get(0) > dataList.get(1)) {
    Collections.swap(dataList, 0, 1);
}
System.out.println(dataList);
```

- 데이터가 세 개일 때 버블 정렬 알고리즘

```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<Integer> dataList = new ArrayList<Integer>();
dataList.add(10);
dataList.add(2);
dataList.add(4);

for (int index = 0; index < dataList.size() - 1; index++) {
    if (dataList.get(index) > dataList.get(index + 1)) {
        Collections.swap(dataList, index, index + 1);
    }
}
System.out.println(dataList);
```

- 데이터가 네 개일 때 버블 정렬 알고리즘 방식으로 정렬하는 방법에 대해 생각해보며, <br> 데이터 갯수에 상관없이 동작하는 버블 정렬 알고리즘 생각
- * 데이터가 네 개 일때 (데이터 갯수에 따라 복잡도가 떨어지는 것은 아니므로, 네 개로 바로 로직을 이해해보자.)
  - 예: data_list = [1, 9, 3, 2]
    - 1차 로직 적용
      - 1 와 9 비교, 자리바꿈없음 [1, 9, 3, 2]
      - 9 와 3 비교, 자리바꿈 [1, 3, 9, 2]
      - 9 와 2 비교, 자리바꿈 [1, 3, 2, 9]
    - 2차 로직 적용
      - 1 와 3 비교, 자리바꿈없음 [1, 3, 2, 9]
      - 3 과 2 비교, 자리바꿈 [1, 2, 3, 9]
      - 3 와 9 비교, 자리바꿈없음 [1, 2, 3, 9]
    - 3차 로직 적용
      - 1 과 2 비교, 자리바꿈없음 [1, 2, 3, 9]
      - 2 과 3 비교, 자리바꿈없음 [1, 2, 3, 9]
      - 3 과 9 비교, 자리바꿈없음 [1, 2, 3, 9]

### 4. 알고리즘 구현 (프로젝트: CH14_BUBBLESORT)
* **특이점 찾아보기**
  - n개의 리스트가 있는 경우 최대 n-1번의 로직을 적용한다.
  - 로직을 1번 적용할 때마다 가장 큰 숫자가 뒤에서부터 1개씩 결정된다.
  - 로직이 경우에 따라 일찍 끝날 수도 있다. 따라서 로직을 적용할 때 한 번도 데이터가 교환된 적이 없다면 이미 정렬된 상태이므로 더 이상 로직을 반복 적용할 필요가 없다.

![image](https://user-images.githubusercontent.com/102513932/176361152-ef8a5d31-7632-4263-a0dc-6b5805768f79.png)

1. for (int index = 0; index < dataList.size() - 1; index++) 반복
2. swap = false (교환이 되었는지를 확인하는 변수를 두자)
2. 반복문안의 반복문으로 n - 1번 반복하며,
3. 반복문안의 반복문 안에서, if (dataList.get(index2) > dataList.get(index2 + 1)) 이면
4. 데이터를 스왑하고, Collections.swap(dataList, index2, index2 + 1);
5. 스왑했음을 체크하기 위해, swap 을 true 로 놓고,
6. 반복문안의 반복문을 수행 후에도 swap 이 false 이면, 전체 반복을 멈춤 (break)

```java
import java.util.ArrayList;
import java.util.Collections;

public class BubbleSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        for (int index = 0; index < dataList.size() - 1; index++) {
            boolean swap = false;
            
            for (int index2 = 0; index2 < dataList.size() - 1 - index; index2++) {
                if (dataList.get(index2) > dataList.get(index2 + 1)) {
                    Collections.swap(dataList, index2, index2 + 1);
                    swap = true;
                }
            }
            
            if (swap == false) {
                break;
            }
        }
        
        return dataList;
    }
}
```

### 랜덤 숫자 생성하기
- 디폴트로 지원하는 Math 패키지의 random() 메서드를 통해, 0 이상, 1 미만의 부동소숫점 값을 가져올 수 있음
```java
Math.random()
```
- 이를 기반으로, 정수 타입 변환 등을 통해, 다음 코드처럼 랜덤 값을 가져올 수 있음

### 테스트
```java
ArrayList<Integer> testData = new ArrayList<Integer>();
for (int i = 0; i < 100; i++) {
    testData.add((int)(Math.random() * 100));
}
BubbleSort bSort = new BubbleSort();
bSort.sort(testData);
```

### 5. 알고리즘 분석
* 반복문이 두 개 O($n^2$)
  - 최악의 경우, <font size=5em>$\frac { n * (n - 1)}{ 2 }$</font>
* 완전 정렬이 되어 있는 상태라면 최선은 O(n)
