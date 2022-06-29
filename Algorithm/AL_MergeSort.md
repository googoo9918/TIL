## 병합 정렬 (merge sort) 

### 1. 병합 정렬 (merge sort) 
* 재귀용법을 활용한 정렬 알고리즘
  1. 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눈다.
  2. 각 부분 리스트를 재귀적으로 합병 정렬을 이용해 정렬한다.
  3. 두 부분 리스트를 다시 하나의 정렬된 리스트로 합병한다.

#### 직접 눈으로 보면 더 이해가 쉽다: https://visualgo.net/en/sorting

<img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif" width=500/>

### 2. 알고리즘 이해
* 데이터가 네 개 일때 (데이터 갯수에 따라 복잡도가 떨어지는 것은 아니므로, 네 개로 바로 로직을 이해해보자.)
* 두 단계로 분리해서 이해할 수 있음
  - **1단계: 정렬되지 않은 배열을 끝까지 분리하는 단계**
  - **2단계: 분리한 데이터를 단계별로 합치는 단계**
<br><br>
* 예: dataList = [1, 9, 3, 2]
    - 먼저 [1, 9], [3, 2] 로 나누고
    - 다시 앞 부분은 [1], [9] 로 나누고 **(여기까지 1단계)**
    - 다시 정렬해서 합친다. [1, 9] **(이 부분부터 2단계)**
    - 다음 [3, 2] 는 [3], [2] 로 나누고
    - 다시 정렬해서 합친다 [2, 3]
    - 이제 [1, 9] 와 [2, 3]을 합친다.
      - 두 배열의 맨 앞에 위치한 데이터부터, 각각 비교하며, 정렬된 합쳐진 배열을 작성한다.
        - 1 < 2 이니 [1]
        - 9 > 2 이니 [1, 2]
        - 9 > 3 이니 [1, 2, 3]
        - 9 밖에 없으니, [1, 2, 3, 9]
      
### 3. 알고리즘 구현
- 배열을 앞뒤 두 배열로 짜르는 코드 작성해보기 (일반화)
```java
public class Split {
    public void splitFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return ;
        }
        int medium = dataList.size() / 2;
        
        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();
        
        leftArr = new ArrayList<Integer>(dataList.subList(0, medium)); // 0 부터 medium - 1 인덱스 번호까지 해당 배열 아이템을 서브 배열로 추출
        rightArr = new ArrayList<Integer>(dataList.subList(medium, dataList.size()));
        
        System.out.println(leftArr);
        System.out.println(rightArr);        
    }
}
```

### mergeSplitFunc 메서드 틀 만들기 (재귀용법 틀 만들기)
- 다음 문장을 코드로 작성해보기 (mergeFunc 함수는 아직은 없는 상태, 있다고만 가정)

  - 만약 배열 갯수가 한개이면 해당 값 리턴
  - 그렇지 않으면, 배열을 앞 뒤, 두 개로 나누기
  - leftArr = mergeSplitFunc(앞)
  - rightArr = mergeSplitFunc(뒤)
  - return mergeFunc(leftArr, rightArr) 라는 아직 정의되지 않은 메서드 호출하여, 리턴하기
  
  > mergeFunc() 메서드가 leftArr 과 rightArr 을 합쳐서 정렬한 배열을 리턴한다고 가정한다면, <br>
  > leftArr 과 rightArr 은 이미 정렬된 배열임을 알 수 있음



 ```java
public ArrayList<Integer> mergeSplitFunc(ArrayList<Integer> dataList) {
    if (dataList.size() <= 1) {
        return dataList;
    }
    int medium = dataList.size() / 2;  

    ArrayList<Integer> leftArr = new ArrayList<Integer>();
    ArrayList<Integer> rightArr = new ArrayList<Integer>();

    leftArr = mergeSplitFunc(new ArrayList<Integer>(dataList.subList(0, medium))); 
    rightArr = mergeSplitFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size()))); 

    return mergeFunc(leftArr, rightArr);
}
```

### mergeFunc 메서드 만들기
* 목표: leftList 와 rightList 의 배열 데이터를 정렬하며 합쳐서, mergedList 라는 이름으로 return 하기
* leftList 와 rightList 는 이미 정렬된 상태 또는 데이터가 하나임<br>

- ArrayList 만들기
  - leftPoint, rightPoint = 0
  - CASE1: leftList, RightList 둘 다 있을 때
    - while (leftList.size() > leftPoint && rightList.size() > rightPoint)
      - 만약 leftPoint 나 rightPoint 가 이미 leftList 또는 rightList 배열을 다 순회했다면, 그 반대쪽 데이터를 그대로 넣고, 해당 인덱스 1 증가
      -  if (leftList.get(leftPoint) > rightList.get(rightPoint))
         - mergedList.add(rightList.get(rightPoint));
         - rightPoint += 1;
      - else:
         - mergedList.add(leftList.get(leftPoint));
         - leftPoint += 1;
  - CASE2: RightList 만 없을 때: 나머지 LeftList 에 있는 데이터를 그대로 mergedList 뒤에 넣음
  - CASE3: LeftList 만 없을 때: 나머지 RightList 에 있는 데이터를 그대로 mergedList 뒤에 넣음

```java
public ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
    ArrayList<Integer> mergedList = new ArrayList<Integer>();
    int leftPoint = 0;
    int rightPoint = 0;
    
     // CASE1: left/right 둘 다 있을 때
    while (leftList.size() > leftPoint && rightList.size() > rightPoint) {
        if (leftList.get(leftPoint) > rightList.get(rightPoint)) {
            mergedList.add(rightList.get(rightPoint));
            rightPoint += 1;
        } else {
            mergedList.add(leftList.get(leftPoint));
            leftPoint += 1;
        }
    }

    // CASE2: right 데이터가 없을 때 (CASE1 -> CASE2가 되겠지)
    while (leftList.size() > leftPoint) {
        mergedList.add(leftList.get(leftPoint));
        leftPoint += 1;
    }
    
    // CASE3: left 데이터가 없을 때 (CASE1 -> CASE3이 되겠지) (CASE 1-> 2-> 3은 불가능)
    while (rightList.size() > rightPoint) {
        mergedList.add(rightList.get(rightPoint));
        rightPoint += 1;
    }
    
    return mergedList;
}
```

### 4. 알고리즘 분석
* 알고리즘 분석은 쉽지 않음, <font color='#BF360C'>이 부분은 참고로만 알아두자.</font>
  - 다음을 보고 이해해보자
    - 몇 단계 깊이까지 만들어지는지를 depth 라고 하고 i로 놓자. 맨 위 단계는 0으로 놓자.
      - 다음 그림의 예에서 $ n /  2^2 $ 는 2단계 깊이를 의미하며, 각 분리된 배열을 노드라고 부르기로 합니다.
      - 각 단계에 있는 하나의 노드 안의 배열 길이는 $\frac { n }{ 2^i }$ 가 된다.
      - 각 단계에는 $2^i$ 개의 노드가 있다.
    - 각 단계의 각 노드 안의 배열 데이터는 한번씩은 체크되므로, 각 단계는 각각 <font size=4em>$2^i * \frac { n }{ 2^i } = O(n)$ 시간 복잡도를 가짐</font>
    - 단계는 항상 $log_2 n$ 개 만큼 만들어짐, 시간 복잡도는 결국 O(log n), 2는 역시 상수이므로 삭제
    - 따라서, 단계별 시간 복잡도 O(n) * O(log n) = O(n log n)

![image](https://user-images.githubusercontent.com/102513932/176414171-23a03a06-32c9-4dec-acc3-70383d0d6c78.png)
