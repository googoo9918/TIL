### 목차
- [배열 (Array)](#배열-array)
  - [1. 배열은 왜 필요할까?](#1-배열은-왜-필요할까)
  - [참고: Primitive 자료형과 Wrapper 클래스](#참고-primitive-자료형과-wrapper-클래스)
  - [2. JAVA와 배열](#2-java와-배열)
  - [JAVA 에서는 기본 문법으로 배열 지원](#java-에서는-기본-문법으로-배열-지원)
  - [JAVA 에서 배열을 보다 손쉽게 다루기 위한 클래스등을 제공](#java-에서-배열을-보다-손쉽게-다루기-위한-클래스등을-제공)
  - [JAVA 에서 배열을 보다 손쉽게 다루기 위한 ArrayList 클래스 예](#java-에서-배열을-보다-손쉽게-다루기-위한-arraylist-클래스-예)
  - [참고: List 와 ArrayList](#참고-list-와-arraylist)
      - [JAVA 에서는 기본 문법으로 다차원 배열도 작성 가능 (2차원 배열)](#java-에서는-기본-문법으로-다차원-배열도-작성-가능-2차원-배열)
      - [JAVA 에서는 기본 문법으로 다차원 배열도 작성 가능 (3차원 배열)](#java-에서는-기본-문법으로-다차원-배열도-작성-가능-3차원-배열)

## 배열 (Array)

<br/>

### 1. 배열은 왜 필요할까?
- 같은 종류의 데이터를 효율적으로 관리하기 위해 사용
- 같은 종류의 데이터를 순차적으로 저장
- 장점: 
  - 빠른 접근 가능
    - 첫 데이터의 위치에서 상대적인 위치로 데이터 접근(인덱스 번호로 접근)
- 단점: 
  - 데이터 추가/삭제의 어려움
    - 미리 최대 길이를 지정해야 함      
  <br/><br/>
 
  ### 참고: Primitive 자료형과 Wrapper 클래스
- JAVA 에서는 int 와 Integer 같이, Primitive 자료형과 Wrapper 클래스가 있음
- Integer 와 같은 Wrapper 클래스가 다음과 같은 이유로 사용되며, 가급적 복잡도를 낮추기 위해, Primitive 와 마구 혼용하기 보다는 주로 Wrapper 클래스를 사용하기로 함 (필요 시에만 Primitive 자료형을 사용하기로 함)
   - null 을 용이하게 처리할 수 있고, 
   - ArrayList 등 객체만을 핸들링 하는 기능을 사용하기 위해
<br/><br/>
### 2. JAVA와 배열
### JAVA 에서는 기본 문법으로 배열 지원 
- 1차원 배열은 [ ] 를 통해 선언할 수 있음 
- 각 아이템은 { } 내에 콤마로 작성

```java
// new 키워드를 사용해서, 배열을 미리 선언하고, 데이터를 넣을 수도 있음
Integer[] data_list = new Integer[10];
data_list[0] = 1

// 직접 배열 데이터 선언시 넣을 수도 있음
Integer data_list1[] = {5, 4, 3, 2, 1};
Integer[] data_list2 = {1, 2, 3, 4, 5};

System.out.println(data_list2[0]);
```

### JAVA 에서 배열을 보다 손쉽게 다루기 위한 클래스등을 제공
- 예: Arrays 클래스 활용하여, 전체 데이터 출력하기

```java
import  java.util.Arrays;

System.out.println( Arrays.toString(data_list2) ); // 배열의 내용을 출력하려면, Arrays.toString(배열변수) 메서드를 사용하면 됨
```

### JAVA 에서 배열을 보다 손쉽게 다루기 위한 ArrayList 클래스 예
- ArrayList 클래스는 가변 길이의 배열 자료구조를 다룰 수 있는 기능을 제공함

### 참고: List 와 ArrayList
- 다음과 같이 List 와 ArrayList 선언의 차이점
```java
List<Integer> list1 = new ArrayList<Integer>();
ArrayList<Integer> list1 = new ArrayList<Integer>();
```

- List 는 인터페이스이고, ArrayList 는 클래스임
   - 클래스는 크게 일반 클래스와 클래스 내에 '추상 메서드' 가 하나 이상 있거나, abstract 로 정의된 추상 클래스로 나뉨
   - 인터페이스는 모든 메서드가 추상 메서드인 경우를 의미하며, 인터페이스를 상속받는 클래스는 인터페이스에서 정의된 추상 메서드를 모두 구현해야 함 (**따라서 다양한 클래스를 상속받는 특정 인터페이스는 결국 동일한 메서드를 제공함**)
   - ArrayList 가 아니라, List 로 선언된 변수는 다음과 같이 필요에 따라 다른 리스트 클래스를 쓸 수 있는 **구현상의 유연성** 을 제공함
     ```java
        List<Integer> list1 = new ArrayList<Integer>();
        list1 = new LinkedList<Integer>();
     ```
   - List는 인터페이스이고, 이 인터페이스의 추상 메소드를 다 구현한 것이 ArrayList이기 때문에 결과적으로 동일한 메서드 제공.
       - 단, 처음에 List 형으로 선언했을 때만 가능할 것이다.
   - 다만, 본 자료구조/알고리즘에서는 굳이 동일 변수에 다양한 리스트 클래스를 쓸 필요는 없으므로, 최대한 동일한 클래스로 선언하기로 함
     ```java
        ArrayList<Integer> list1 = new ArrayList<Integer>();
     ``` 
   - 이외에  JDK 1.7 이상부터는 인스턴스 생성 시 타입을 추정할 수 있는 경우에는 타입을 생략할 수 있으므로, 다음과 같이 작성 가능하지만, 가능한 JAVA 버전 제한하지 않기 위해, 가능한 위의 코드처럼 타입을 생략하지 않기로 함
     ```java
        ArrayList<Integer> list1 = new ArrayList<>();
     ``` 
   
```java
// JAVA 에서는 기본적으로 java.util 패키지에 가변 크기의 배열을 다룰 수 있는 ArrayList 클래스도 제공하고 있음
import java.util.ArrayList;

ArrayList<Integer> list1 = new ArrayList<Integer>(); 
// int 형 데이터를 담을 수 있는 가변 길이의 배열 선언
list1.add(1); 
// 배열에 아이템 추가 시 add(아이템) 메서드 사용
list1.add(1,3);
//add((index),val)
list1.add(2);
list1.get(0) //1
// 배열에 특정 아이템을 읽을 시 get(인덱스번호) 메서드 사용 (굳이 System.out.println() 을 사용하지 않아도 됨)
list1.set(0, 5); 
// 특정 인덱스에 해당하는 아이템 변경 시, set(인덱스번호, 변경할값) 메서드 사용
list1.get(0) //5
list1.remove(0); 
// 특정 인덱스에 해당하는 아이템 삭제 시, remove(인덱스번호) 메서드 사용
list1.get(0); //2
list1.size();
// 배열 길이 확인하기
list1.contains(2); // true
// 해당 값이 배열에 있는지 확인
list1.containsAll(2,0,3);
// 인자로 제공한 값이 모두 들어 있는지 확인
list1.clear();
// 값 모두 삭제
list1.isEmpty();
// 비었으면 true, 하나라도 있으면 fals
list1.addAll(lisT1);
// 리스트 합침
list1.retainAll(list1)
// 인자로 제공한 컬렉션 내에 들어있는 값 제외하고 모두 지움
list1.removeAll(list1)
// 인자로 제공한 컬렉션과 중복하는 값 모두 지움.

 
```

##### JAVA 에서는 기본 문법으로 다차원 배열도 작성 가능 (2차원 배열)
Integer data_list[][] = { {1, 2, 3}, {4, 5, 6} };
```JAVA
// 데이터 2 인덱스로 지정해서 출력해보기
System.out.println( data_list[0][1] ); //2
// 데이터 5 인덱스로 지정해서 출력해보기
System.out.println( data_list[1][1] ); //5 
```

##### JAVA 에서는 기본 문법으로 다차원 배열도 작성 가능 (3차원 배열)
```JAVA
Integer[][][] data_list = { 
        {
            {1, 2, 3}, 
            {4, 5, 6} 
        },
        {
            {7, 8, 9}, 
            {10, 11, 12} 
        }
};

// 데이터 5 인덱스로 지정해서 출력해보기
System.out.println( data_list[0][1][1] ); //5
// 데이터 12 인덱스로 지정해서 출력해보기
System.out.println( data_list[1][1][2] ); //12
```

```java
String dataset[] = {
    "Braund, Mr. Owen Harris",
    "Cumings, Mrs. John Bradley (Florence Briggs Thayer)",
    "Heikkinen, Miss. Laina",
    "Futrelle, Mrs. Jacques Heath (Lily May Peel)",
    "Allen, Mr. William Henry",
    "Moran, Mr. James",
    "McCarthy, Mr. Timothy J",
    "Palsson, Master. Gosta Leonard",
    "Johnson, Mrs. Oscar W (Elisabeth Vilhelmina Berg)",
    "Nasser, Mrs. Nicholas (Adele Achem)",
    "Sandstrom, Miss. Marguerite Rut",
    "Bonnell, Miss. Elizabeth",
    "Saundercock, Mr. William Henry",
    "Andersson, Mr. Anders Johan",
    "Vestrom, Miss. Hulda Amanda Adolfina",
    "Hewlett, Mrs. (Mary D Kingcome) ",
    "Rice, Master. Eugene",
    "Williams, Mr. Charles Eugene",
    "Vander Planke, Mrs. Julius (Emelia Maria Vandemoortele)",
    "Masselmani, Mrs. Fatima",
    "Fynney, Mr. Joseph J",
    "Beesley, Mr. Lawrence",
    "McGowan, Miss. Anna",
    "Sloper, Mr. William Thompson",
    "Palsson, Miss. Torborg Danira",
    "Asplund, Mrs. Carl Oscar (Selma Augusta Emilia Johansson)",
    "Emir, Mr. Farred Chehab",
    "Fortune, Mr. Charles Alexander",
    "Dwyer, Miss. Ellen",
    "Todoroff, Mr. Lalio"
};
```

<div class="alert alert-block" style="border: 1px solid #FFB300;background-color:#F9FBE7;font-size:1em;line-height:1.4em">
<font size="3em" style="font-weight:bold;color:#3f8dbf;">연습해보기2</font><br><br>
위 1차원 배열에서, 문자 M 을 가지고 있는 아이템의 수를 출력해보기

- 참고
   - 배열.length : 배열에 들어 있는 아이템 갯수
   - 문자열.indexof(String key) : 문자 key 가 해당 문자열에 있으면 해당 문자의 위치 (index 값) 를 리턴하고, 없으면 -1 을 리턴함 
</div>

```java
import java.lang.String;
int cnt =0;

for(int i=0; i < dataset.length; i++){
    if(dataset[i].indexOf("M")==-1){ //M에 큰따옴표 잊지말것.
    ;
    }
    else{
    cnt++;
    }
}
System.out.println(cnt);
```
