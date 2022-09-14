### 목차
- [퀵 정렬 (quick sort)](#퀵-정렬-quick-sort)
  - [1. 퀵 정렬 (quick sort) 이란?](#1-퀵-정렬-quick-sort-이란)
  - [2. 어떻게 코드로 만들까?](#2-어떻게-코드로-만들까)
  - [참고](#참고)
  - [테스트](#테스트)
  - [3. 알고리즘 구현](#3-알고리즘-구현)
  - [4. 알고리즘 분석](#4-알고리즘-분석)
## 퀵 정렬 (quick sort) 
### 1. 퀵 정렬 (quick sort) 이란?
* <font color='#BF360C'>정렬 알고리즘의 꽃</font>
* 기준점(pivot 이라고 부름)을 정해서, 기준점보다 작은 데이터는 왼쪽(left), 큰 데이터는 오른쪽(right) 으로 모으는 함수를 작성함
* 각 왼쪽(left), 오른쪽(right)은 재귀용법을 사용해서 다시 동일 함수를 호출하여 위 작업을 반복함
* 함수는 왼쪽(left) + 기준점(pivot) + 오른쪽(right) 을 리턴함

### 2. 어떻게 코드로 만들까?
```java
public class Split {
    public void splitFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return ;
        }
        int pivot = dataList.get(0);
        
        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();        
        
        for (int index = 1; index < dataList.size(); index++) { //0번은 pivot이니까 1번부터 순회.
            if (dataList.get(index) > pivot) {
                rightArr.add(dataList.get(index)); //pivot보다 크면 오른쪽 list에 저장.
            } else {
                leftArr.add(dataList.get(index)); //pivot보다 작으면 왼쪽 list에 저장.
            }
        }
        
        System.out.println(leftArr);
        System.out.println(pivot);
        System.out.println(rightArr);        
    }
}
```

### 참고
- 배열을 ArrayList 로 변환하기
  - Arrays.asList() 메서드에 넣어주면, ArrayList 에서 사용할 수 있음
```java
int[] array = {4, 1, 2, 5, 7}; 
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
```
### 테스트
```java
Split sObject = new Split();
sObject.splitFunc(new ArrayList<Integer>(Arrays.asList(array))); 
//asList를 통해서 인자 옮기기!
```

```java
public class Split {
    public void splitFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return ;
        }
        int pivot = dataList.get(0);
        
        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();        
        
        for (int index = 1; index < dataList.size(); index++) {
            if (dataList.get(index) > pivot) {
                rightArr.add(dataList.get(index));
            } else {
                leftArr.add(dataList.get(index));
            }
        }
        
        ArrayList<Integer> mergedArr = new ArrayList<Integer>();
        mergedArr.addAll(leftArr);
        mergedArr.addAll(Arrays.asList(pivot));
        mergedArr.addAll(rightArr);
        
        System.out.println(mergedArr);        
    }
}
```

```java
Split sObject = new Split();
sObject.splitFunc(new ArrayList<Integer>(Arrays.asList(array)));
```

### 3. 알고리즘 구현 
* QuickSort.sort() 함수 만들기
  - 만약 리스트 갯수가 한개이면 해당 리스트 리턴
  - 그렇지 않으면, 리스트 맨 앞의 데이터를 기준점(pivot)으로 놓기
  - left, right 리스트 변수를 만들고,
  - 맨 앞의 데이터를 뺀 나머지 데이터를 기준점과 비교(pivot)
    - 기준점보다 작으면 left.add(해당 데이터)
    - 기준점보다 크면 right.add(해당 데이터)
  - 결국 QuickSort.sort(left) + pivot + QuickSort.sort(right) 을 리턴하는 방식으로 재귀 호출

```java
import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) { //반환값 설정해줌.
        if (dataList.size() <= 1) {
            return dataList; // 하나 남으면 return! (하나 되기 전까지는 계속 recursive)
        }
        int pivot = dataList.get(0);
        
        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();        
        
        for (int index = 1; index < dataList.size(); index++) {
            if (dataList.get(index) > pivot) {
                rightArr.add(dataList.get(index));
            } else {
                leftArr.add(dataList.get(index));
            }
        }
        
        ArrayList<Integer> mergedArr = new ArrayList<Integer>();
        mergedArr.addAll(this.sort(leftArr)); // 정렬이 완료되지 않은 상태일 수 있으니 다시 sort 해주기!(재귀)
        mergedArr.addAll(Arrays.asList(pivot));
        mergedArr.addAll(this.sort(rightArr)); // 정렬이 완료되지 않은 상태일 수 있으니 다시 sort 해주기!(재귀)
        
        return mergedArr;        
    }
}
```

```java
ArrayList<Integer> testData = new ArrayList<Integer>();

for (int index = 0; index < 100; index++) {
    testData.add((int)(Math.random() * 100));
}

QuickSort qSort = new QuickSort();
qSort.sort(testData);
```

### 4. 알고리즘 분석
* <font color='#BF360C'>병합정렬과 유사, 시간복잡도는 O(n log n)</font>
  - 단, 최악의 경우 
    - 이미 정렬된 배열에서 pivot이 가장 크거나, 가장 작으면 가장 큰 시간이 소요됨
    - 모든 데이터를 비교하는 상황이 나옴
    - O($n^2$)

![image](https://user-images.githubusercontent.com/102513932/176434515-4e335688-b74a-4c28-b702-3e398136e535.png)

 