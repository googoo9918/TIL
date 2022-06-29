## 선택 정렬 (selection sort) 
### 1. 선택 정렬 (selection sort) 란?
* 다음과 같은 순서를 반복하며 정렬하는 알고리즘
  1. 주어진 데이터 중, 최소값을 찾음
  2. 해당 최소값을 데이터 맨 앞에 위치한 값과 교체함
  3. 맨 앞의 위치를 뺀 나머지 데이터를 동일한 방법으로 반복함

#### 직접 눈으로 보면 더 이해가 쉽다: https://visualgo.net/en/sorting

<img src="https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif" width=100>

### 2. 어떻게 코드로 만들까?

> 간단히 로직에 집중해서, 각각 데이터가 저장되어 있는 배열이 있다고 가정한다면.. 

* 데이터가 두 개 일때
  - 예: dataList = [9, 1]
    - dataList[0] > dataList[1] 이므로 dataList[0] 값과 dataList[1] 값을 교환
* 데이터가 세 개 일때
  - 예: dataList = [9, 1, 7]
    - 처음 한번 실행하면, 1, 9, 7 이 됨
    - 두 번째 실행하면, 1, 7, 9 가 됨
* 데이터가 네 개 일때
  - 예: dataList = [9, 3, 2, 1]
    - 처음 한번 실행하면, 1, 3, 2, 9 가 됨
    - 두 번째 실행하면, 1, 2, 3, 9 가 됨
    - 세 번째 실행하면, 변화 없음

### 3. 알고리즘 구현 (프로젝트: CH15_SELECTIONSORT)
1. for (int stand = 0; stand < dataList.size() - 1; stand++) 로 반복
2. lowest = stand 로 놓고,
3. for (int index = stand + 1; index < dataList.size(); index++) 로, stand 이후부터 반복
   - 내부 반복문 안에서 dataList[lowest] > dataList[index] 이면, 
     - lowest = num
4. dataList[lowest] 와 dataList[index] 스왑

```java
import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        int lowest; //최소값 저장을 위한 임시변수
        for (int stand = 0; stand < dataList.size() - 1; stand++) { //stand는 반복한 회수
            lowest = stand; 
            for (int index  = stand + 1; index < dataList.size(); index++) {
                if (dataList.get(lowest) > dataList.get(index)) {
                    lowest = index; //가장 작은 값의 index가 lowest에 저장되어 있겠지?
                }
            }
            Collections.swap(dataList, lowest, stand); // 제일 앞으로 보내주기
        }
        return dataList;
    }
}
```

### 테스트
```java
ArrayList<Integer> testData = new ArrayList<Integer>();

for (int i = 0; i < 100; i++) {
    testData.add((int)(Math.random() * 100));
}
SelectionSort sSort = new SelectionSort();
sSort.sort(testData);
```

### 4. 알고리즘 분석
* 반복문이 두 개 O($n^2$)
  - 실제로 상세하게 계산하면, <font size=5em>$\frac { n * (n - 1)}{ 2 }$</font>
