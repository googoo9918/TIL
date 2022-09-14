### 목차
- [탐욕 알고리즘의 이해](#탐욕-알고리즘의-이해)
  - [1. 탐욕 알고리즘 이란?](#1-탐욕-알고리즘-이란)
  - [2. 탐욕 알고리즘 예](#2-탐욕-알고리즘-예)
  - [문제1: 동전 문제](#문제1-동전-문제)
  - [문제2: 부분 배낭 문제 (Fractional Knapsack Problem)](#문제2-부분-배낭-문제-fractional-knapsack-problem)
  - [참고: 정렬 기준 정의하기](#참고-정렬-기준-정의하기)
  - [Comparable 과 Comparator 인터페이스](#comparable-과-comparator-인터페이스)
  - [Arrays.sort() 와 Comparator](#arrayssort-와-comparator)
  - [부분 배낭 문제 구현](#부분-배낭-문제-구현)
  - [3. 탐욕 알고리즘의 한계](#3-탐욕-알고리즘의-한계)
  - [예](#예)

## 탐욕 알고리즘의 이해

### 1. 탐욕 알고리즘 이란?
- Greedy algorithm 또는 탐욕 알고리즘 이라고 불리움
- 최적의 해에 가까운 값을 구하기 위해 사용됨
- 여러 경우 중 하나를 결정해야할 때마다, **매순간 최적이라고 생각되는 경우를 선택**하는 방식으로 진행해서, 최종적인 값을 구하는 방식

### 2. 탐욕 알고리즘 예
### 문제1: 동전 문제
  - 지불해야 하는 값이 4720원 일 때 1원 50원 100원, 500원 동전으로 동전의 수가 가장 적게 지불하시오.
    - 가장 큰 동전부터 최대한 지불해야 하는 값을 채우는 방식으로 구현 가능
    - 탐욕 알고리즘으로 매순간 최적이라고 생각되는 경우를 선택하면 됨

```java
public class GreedyAlgorithm {
    public void coinFunc(Integer price, ArrayList<Integer> coinList) {
        Integer totalCoinCount = 0;
        Integer coinNum = 0;
        ArrayList<Integer> details = new ArrayList<Integer>();
        
        for (int index = 0; index < coinList.size(); index++) {
            coinNum = price / coinList.get(index);
            totalCoinCount += coinNum;
            price -= coinNum * coinList.get(index);
            details.add(coinNum);
            System.out.println(coinList.get(index) + "원: " + coinNum + "개");
        }
        System.out.println("총 동전 갯수: " + totalCoinCount);
    }
}
```

```java
GreedyAlgorithm gObject = new GreedyAlgorithm();
ArrayList<Integer> coinList = new ArrayList<Integer>(Arrays.asList(500, 100, 50, 1));
gObject.coinFunc(4720, coinList);
// 500원: 9개
// 100원: 2개
// 50원: 0개
// 1원: 20개
// 총 동전 갯수: 31
```
### 문제2: 부분 배낭 문제 (Fractional Knapsack Problem)
  - 무게 제한이 k인 배낭에 최대 가치를 가지도록 물건을 넣는 문제
    - 각 물건은 무게(w)와 가치(v)로 표현될 수 있음
    - 물건은 쪼갤 수 있으므로 물건의 일부분이 배낭에 넣어질 수 있음, 그래서 Fractional Knapsack Problem 으로 부름
      - Fractional Knapsack Problem 의 반대로 물건을 쪼개서 넣을 수 없는 배낭 문제도 존재함 (0/1 Knapsack Problem 으로 부름)

![image](https://user-images.githubusercontent.com/102513932/176659774-62a79cc7-d6e1-44c2-a513-405c76038b5e.png)

//2차원 배열로 작성해보기
Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };

### 참고: 정렬 기준 정의하기
- 정렬을 위해서는 정렬 기준이 있어야 함
- 객체간 정렬을 위해서는 정렬 기준을 먼저 정의해줘야 함

```java
Integer[] iArray = new Integer[]{1, 10, 4, 3, 2};
Arrays.sort(iArray);
Arrays.toString(iArray); //[1, 2, 3, 4, 10]
```

```java
public class Edge {
    public Integer distance;
    public String vertex;
    
    public Edge (Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }
}

Edge edge1 = new Edge(10, "A");
Edge edge2 = new Edge(12, "A");
Edge edge3 = new Edge(13, "A");
Edge[] edges = new Edge[]{edge1, edge2, edge3};
Arrays.sort(edges); 
//오류 발생하게 된다!! 두 요소 중 뭘 기준으로 정렬할지 모르기 때문에.
```

### Comparable 과 Comparator 인터페이스 
- Comparable 와 Comparator 는 둘 다 인터페이스로, 정렬 기준을 구현하기 위해 사용됨
   - Comparable 인터페이스는 compareTo() 메서드를 override 해서 구현
       -  Comparable은 "자기 자신과 매개변수 객체를 비교" (매개변수 1개)
       - lang 패키지에 존재 (import 필요x)
      - 일반적으로는 정렬할 객체에 implements 로 Comparable 인터페이스를 추가하여 구현
      - ![image](https://user-images.githubusercontent.com/102513932/176645633-f8fb7a6e-dd56-4954-a1a5-d0f25a99da63.png)
      - 단, 이 경우 OverFlow와 UnderFlow에 유의하며 코드를 작성할 것.
   - Comparator 인터페이스는 compare() 메서드를 override 해서 구현
       - Comparator는 "두 매개변수 객체를 비교"
       - util 패키지에 존재
      - 일반적으로는 별도 클래스를 정의해서 구현하며, 따라서, 동일 객체에 다양한 정렬 기준을 가진 클래스를 작성 가능  
      - ![image](https://user-images.githubusercontent.com/102513932/176646157-66d506cf-bcb8-4163-8689-3a35c80384fa.png)
      - 단, 이 경우 OverFlow와 UnderFlow에 유의하며 코드를 작성할 것.

```java
public class Edge implements Comparable<Edge> {
    public Integer distance;
    public String vertex;
    
    public Edge (Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.distance - e.distance;
    }
}
Edge edge1 = new Edge(12, "A");
Edge edge2 = new Edge(10, "A");
Edge edge3 = new Edge(13, "A");
Edge[] edges = new Edge[]{edge1, edge2, edge3};
Arrays.sort(edges); //위 comprable을 통해 distance를 통해 정렬할 수 있다.
for (int index = 0; index < edges.length; index++) {
    System.out.println(edges[index].distance);
} //10 12 13
```
### Arrays.sort() 와 Comparator 
- 다음 예와 같이 Arrays.sort() 메서드는 인자를 두 개 받아서, 두 번째 인자에 Comparator 클래스를 넣어줄 수도 있음
   - Edge 객체에 Comparable 인터페이스가 정의되어 있다하더라도, Comparator 클래스의 정렬 기준으로 정렬이 됨

```java
public class Edge implements Comparable<Edge> {
    public Integer distance;
    public String vertex;
    
    public Edge (Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.distance - e.distance;
    }
}
Edge edge1 = new Edge(12, "A");
Edge edge2 = new Edge(10, "A");
Edge edge3 = new Edge(13, "A");
Edge[] edges = new Edge[]{edge1, edge2, edge3};
Arrays.sort(edges, new Comparator<Edge>() {
    @Override
    public int compare(Edge objectItem1, Edge objectItem2) {
        return objectItem2.distance - objectItem1.distance;
    }
});

for (int index = 0; index < edges.length; index++) {
    System.out.println(edges[index].distance);
}
```

### 부분 배낭 문제 구현

```java
//2차원 배열로 작성해보기
Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };
```

```java
public class GreedyAlgorithm {
    public void knapsackFunc(Integer[][] objectList, double capacity) { //2차원 배열, 무게 제한 k(용량)
        double totalValue = 0.0; //최대 가치 설정 (물건과 가치를 쪼개니까 소수점 -> double)
        double fraction = 0.0; // 해당 물건의 일부분 조각!!(조각할 수 있는 문제라 가능)
        
        Arrays.sort(objectList, new Comparator<Integer[]>() { //Integer[] 인 이유는 한 행을 한 세트로 하여 인자에 넣겠다는 의미
            @Override
            public int compare(Integer[] objectItem1, Integer[] objectItem2) { //objectitem1,2 모두 현재 배열임!!
                return (objectItem2[1] / objectItem2[0]) - (objectItem1[1] / objectItem1[0]); 
                // [1]과 [0]은 배열 안 원소를 의미하는 것  --> 무게 당 가치로 sort 하기 위해서!
            }
        });
                    
        for (int index = 0; index < objectList.length; index++) { //objectList[index][0] == 무게 objectList[index][1] == 가치 
            if ( (capacity - (double)objectList[index][0]) > 0 ) { // 배낭 총량 - 제일 가치 높은 물건 무게 빼도 무게 남을 때.
                capacity -= (double)objectList[index][0]; // 그냥 빼준다.. (fraction 할 필요 없음)
                totalValue += (double)objectList[index][1]; // totalValue에 그냥 넣어주면 되겠지?
                System.out.println("무게:" + objectList[index][0] + ", 가치:" + objectList[index][1]); 
            } else { // 배낭 총량에 넘칠때 --> fracrtion 만들어 줘야겠지
                fraction = capacity / (double)objectList[index][0];
                totalValue += (double)objectList[index][1] * fraction;
                System.out.println("무게:" + objectList[index][0] + ", 가치:" + objectList[index][1] + ", 비율:" + fraction);                
                break; // 어차피 다 넣어줬으니까 break.
            }
        }
        System.out.println("총 담을 수 있는 가치:" + totalValue);
    }
}
```

```java
GreedyAlgorithm gObject = new GreedyAlgorithm();
Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };
gObject.knapsackFunc(objectList, 30.0);
// 무게:10, 가치:10
// 무게:15, 가치:12
// 무게:20, 가치:10, 비율:0.25
// 총 담을 수 있는 가치:24.5
```

### 3. 탐욕 알고리즘의 한계
- 탐욕 알고리즘은 근사치 추정에 활용
- 반드시 최적의 해를 구할 수 있는 것은 아니기 때문
- 최적의 해에 가까운 값을 구하는 방법 중의 하나임

### 예
- ![image](https://user-images.githubusercontent.com/102513932/176660408-3702c556-e6c2-4ed0-ae07-f46719e2e18a.png)
- '시작' 노드에서 시작해서 가장 작은 값을 찾아 leaf node 까지 가는 경로를 찾을 시에
  - Greedy 알고리즘 적용시 시작 -> 7 -> 12 를 선택하게 되므로 7 + 12 = 19 가 됨 
  - 하지만 실제 가장 작은 값은 시작 -> 10 -> 5 이며, 10 + 5 = 15 가 답