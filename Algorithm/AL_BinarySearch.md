### 목차
- [이진 탐색 (Binary Search)](#이진-탐색-binary-search)
  - [1. 이진 탐색 (Binary Search) 이란?](#1-이진-탐색-binary-search-이란)
  - [이진 탐색의 이해 (순차 탐색과 비교하며 이해하기)](#이진-탐색의-이해-순차-탐색과-비교하며-이해하기)
  - [2. 분할 정복 알고리즘과 이진 탐색](#2-분할-정복-알고리즘과-이진-탐색)
  - [3. 어떻게 코드로 만들까?](#3-어떻게-코드로-만들까)
  - [테스트](#테스트)
  - [5. 알고리즘 분석](#5-알고리즘-분석)
## 이진 탐색 (Binary Search)

### 1. 이진 탐색 (Binary Search) 이란?
* 탐색할 자료를 둘로 나누어 해당 데이터가 있을만한 곳을 탐색하는 방법

### 이진 탐색의 이해 (순차 탐색과 비교하며 이해하기)

<img src="https://www.mathwarehouse.com/programming/images/binary-vs-linear-search/binary-and-linear-search-animations.gif">

### 2. 분할 정복 알고리즘과 이진 탐색
- 분할 정복 알고리즘 (Divide and Conquer)
  - Divide: 문제를 하나 또는 둘 이상으로 나눈다.
  - Conquer: 나눠진 문제가 충분히 작고, 해결이 가능하다면 해결하고, 그렇지 않다면 다시 나눈다.
- 이진 탐색
  - Divide: 리스트를 두 개의 서브 리스트로 나눈다.
  - Comquer
    - 검색할 숫자 (search) > 중간값 이면, 뒷 부분의 서브 리스트에서 검색할 숫자를 찾는다.
    - 검색할 숫자 (search) < 중간값 이면, 앞 부분의 서브 리스트에서 검색할 숫자를 찾는다.  

### 3. 어떻게 코드로 만들까?
* 이진 탐색은 데이터가 정렬되있는 상태에서 진행
* 데이터가 {2, 3, 8, 12, 20} 일 때,
  - searchFunc(data_list, find_data) 함수를 만들고
    - searchItem 찾는 숫자
    - dataList는 데이터 배열
    - dataList의 중간에 위치한 값을 searchItem 비교해서
      - searchItem < dataList 중간값 이라면
        - 맨 앞부터 data_list의 중간까지 에서 다시 searchItem 찾기
      - dataList의 중간값 < searchItem 이라면
        - dataList의 중간부터 맨 끝까지에서 다시 searchItem 찾기
      - 그렇지 않다면, data_list의 중간값은 searchItem 인 경우로, return dataList 중간위치

```java
import java.util.ArrayList;

public class BinarySearch {
    public boolean searchFunc(ArrayList<Integer> dataList, Integer searchItem) {
        if (dataList.size() == 1 && searchItem == dataList.get(0)) { //예외
            return true;
        } 
        if (dataList.size() == 1 && searchItem != dataList.get(0)) { //처리
            return false;
        }
        if (dataList.size() == 0) { //꼭 하기!
            return false;
        }
        
        int medium = dataList.size() / 2; 
        
        if (searchItem == dataList.get(medium)) {
            return true;
        } else {
            if (searchItem < dataList.get(medium)) {
                return this.searchFunc(new ArrayList<Integer>(dataList.subList(0, medium)), searchItem); // 재귀 넣어주기!
            } else {
                 return this.searchFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())), searchItem);// 재귀 넣어주기!
            }
        }
    }
```

### 테스트
```java
import java.util.Collections;

ArrayList<Integer> testData = new ArrayList<Integer>();

for (int index = 0; index < 100; index++) {
    testData.add((int)(Math.random() * 100));
}

Collections.sort(testData);
System.out.println(testData);
BinarySearch bSearch = new BinarySearch();
bSearch.searchFunc(testData, 2);
```

### 5. 알고리즘 분석
* n개의 리스트를 매번 2로 나누어 1이 될 때까지 비교연산을 k회 진행
  - <font size=4em>n X $\frac { 1 }{ 2 }$ X $\frac { 1 }{ 2 }$ X $\frac { 1 }{ 2 }$ ... = 1</font>
  - <font size=4em>n X $\frac { 1 }{ 2 }^k$ = 1</font>
  - <font size=4em>n = $2^k$ = $log_2 n$ = $log_2 2^k$</font>
  - <font size=4em>$log_2 n$ = k</font>
  - 빅 오 표기법으로는 k + 1 이 결국 최종 시간 복잡도임 (1이 되었을 때도, 비교연산을 한번 수행)
    - 결국 O($log_2 n$ + 1) 이고, 2와 1, 상수는 삭제 되므로, O($log n$)