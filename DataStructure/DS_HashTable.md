### 목차
- [해쉬 테이블 (Hash Table)](#해쉬-테이블-hash-table)
  - [1. 해쉬 테이블](#1-해쉬-테이블)
  - [2. 알아둘 용어](#2-알아둘-용어)
  - [3. 간단한 해쉬 예](#3-간단한-해쉬-예)
    - [3.1. hash table 클래스 만들기](#31-hash-table-클래스-만들기)
    - [3.2. 초간단 해쉬 함수를 만들어봅니다.](#32-초간단-해쉬-함수를-만들어봅니다)
    - [3.3 해쉬 테이블 클래스에 해쉬 함수 추가](#33-해쉬-테이블-클래스에-해쉬-함수-추가)
    - [3.3 해쉬 테이블 클래스에 데이터 저장 메서드 추가](#33-해쉬-테이블-클래스에-데이터-저장-메서드-추가)
    - [참고: 객체 배열](#참고-객체-배열)
    - [해쉬 테이블 클래스에 saveData() 메서드 추가](#해쉬-테이블-클래스에-savedata-메서드-추가)
    - [3.4. 해쉬 테이블 클래스에 key 에 대한 데이터를 가져오는 메서드 추가](#34-해쉬-테이블-클래스에-key-에-대한-데이터를-가져오는-메서드-추가)
    - [3.5. 테스트](#35-테스트)
  - [4. 자료 구조 해쉬 테이블의 장단점과 주요 용도](#4-자료-구조-해쉬-테이블의-장단점과-주요-용도)
  - [5. 충돌(Collision) 해결 알고리즘 (좋은 해쉬 함수 사용하기)](#5-충돌collision-해결-알고리즘-좋은-해쉬-함수-사용하기)
    - [기존 알고리즘의 문제점](#기존-알고리즘의-문제점)
  - [5.1. Chaining 기법](#51-chaining-기법)
    - [테스트](#테스트)
  - [5.2. Linear Probing 기법](#52-linear-probing-기법)
  - [5.3. 빈번한 충돌을 개선하는 기법](#53-빈번한-충돌을-개선하는-기법)
  - [참고: JAVA HashMap](#참고-java-hashmap)
  - [Java HashMap 사용법](#java-hashmap-사용법)
    - [해시맵(HashMap)](#해시맵hashmap)
    - [선언](#선언)
    - [기타 용법](#기타-용법)
    - [값 출력](#값-출력)
## 해쉬 테이블 (Hash Table)

### 1. 해쉬 테이블
  - 키(Key)에 데이터(Value)를 매핑할 수 있는 데이터 구조
  - 해쉬 함수를 통해, 배열에 키에 대한 데이터를 저장할 수 있는 주소(인덱스 번호)를 계산
  - Key를 통해 바로 데이터가 저장되어 있는 주소를 알 수 있으므로, 저장 및 탐색 속도가 획기적으로 빨라짐
  - 미리 해쉬 함수가 생성할 수 있는 주소(인덱스 번호)에 대한 공간을 배열로 할당한 후, 키에 따른 데이터 저장 및 탐색 지원

### 2. 알아둘 용어
* 해쉬 함수(Hash Function): 임의의 데이터를 고정된 길이의 값으로 리턴해주는 함수
  - 해쉬 (Hash), 해쉬 값(Hash Value), 또는 해쉬 주소(Hash Address): 해싱 함수를 통해 리턴된 고정된 길이의 값
    - 키를 해시함수를 사용하여 만들어진 결과물
    - 저장소에서 데이터와 매칭되어 저장됨.
* 해쉬 테이블(Hash Table): 키 값의 연산에 의해 직접 접근이 가능한 데이터 구조
  - 슬롯(Slot): 해쉬 테이블에서 한 개의 데이터를 저장할 수 있는 공간
  - ![image](https://user-images.githubusercontent.com/102513932/175220262-36198326-77d4-42e7-8a8b-9b4a6b98555c.png)

### 3. 간단한 해쉬 예
#### 3.1. hash table 클래스 만들기
```java
public class MyHash {
    public Slot[] hashTable; //hashTable은 slot 배열 형태.
    
    public MyHash(Integer size) {
        this.hashTable = new Slot[size]; //MyHash(size) 객체 생성시 slot 개수만큼 hashtable 형성 가능.
    }
    
    public class Slot { // slot 클래스 설명부
        String value;  
        Slot(String value) {
            this.value = value;
        }
    }
}
```

#### 3.2. 초간단 해쉬 함수를 만들어봅니다.
- Key 가 문자열일 때, 문자열의 앞 글자를 숫자로 변환해서, Division 기법을 사용하여, Key에 대한 주소(인덱스 번호) 계산
   - Division 기법: 가장 간단한 해쉬 함수 중 하나로, 나누기를 통해, 나머지 값을 사용하는 기법

```java
String name = "DaveLee";
name.charAt(0); //D
(int)(name.charAt(0)); //68
String name = "DaveLee";
(int)(name.charAt(0)) % 20 //8
```

#### 3.3 해쉬 테이블 클래스에 해쉬 함수 추가
```java
public int hashFunc(String key) { // divison 기법을 활용한 주소 출력 메소드.
        return (int)(key.charAt(0)) % this.hashTable.length; 
        //divison 기법을 이용하면, 0부터 length-1 까지의 값을 갖는 hashFunc이 형성됨.
        // 모든 hashFunc에서 나올 수 있는 주소에 대한 공간을 미리 확보한 func을 만들 수 있음.
    }
```

#### 3.3 해쉬 테이블 클래스에 데이터 저장 메서드 추가
#### 참고: 객체 배열
- 객체 배열 선언시, 각 배열의 아이템은 각 객체를 참조할 수 있는 주소를 담을 수 있는 공간만 할당
  - 각 아이템 생성시, 별도로 각 객체를 생성해줘야 함
  - 즉, 객체 배열 선언시, 각 생성할 객체를 가리킬 주소만 저장할 공간을 배열로 만드는 것임

```java
public class Slot {
    String value;
    Slot(String value) {
        this.value = value;
    }
}

Slot[] hashTable = new Slot[20];
System.out.println(hashTable[0]); //null 출력.
// hashTable 이라는 배열은 slot 객체를 가리킬 수 있는 주소공간만 할당함.
```

```java
public class Slot {
    String value;
    Slot(String value) {
        this.value = value;
    }
}

Slot[] hashTable = new Slot[20];
hashTable[0] = new Slot("test"); // 이처럼 실제로 생성 해줘야 함.
System.out.println(hashTable[0]); // 주소값 출력됨.
System.out.println(hashTable[0].value); // 값이 출력됨.
```

#### 해쉬 테이블 클래스에 saveData() 메서드 추가
```java
  public boolean saveData(String key, String value) { // 데이터를 저장하는 method.
        Integer address = this.hashFunc(key);  //저장하는 주소 가져오기.
        if (this.hashTable[address] != null) { // 이미 저장된 값이 있는 경우
            this.hashTable[address].value = value; // value값만 변경해주기.
        } else {
            this.hashTable[address] = new Slot(value); // 새로 저장하는 경우, slot 만들어서 저장해주기.
        }
        return true;
    }
```

#### 3.4. 해쉬 테이블 클래스에 key 에 대한 데이터를 가져오는 메서드 추가
```java
public String getData(String key) { //해당 key에 대한 data를 가져오는 메소드.
        Integer address = this.hashFunc(key); // 주소 값 구하기.
        if (this.hashTable[address] != null) { // null이 아니라면.
            return this.hashTable[address].value; // value값 가져오기
        } else {
            return null; // null이면 값 없는거지 뭐!
        }
    }
```

#### 3.5. 테스트
```java
MyHash mainObject = new MyHash(20);
mainObject.saveData("DaveLee", "01022223333");
mainObject.saveData("fun-coding", "01033334444");
mainObject.getData("fun-coding"); //01033334444
```

### 4. 자료 구조 해쉬 테이블의 장단점과 주요 용도
- 장점
  - 데이터 저장/읽기 속도가 빠르다. (검색 속도가 빠르다.)
  - 해쉬는 키에 대한 데이터가 있는지(중복) 확인이 쉬움
- 단점 
  - 일반적으로 저장공간이 좀더 많이 필요하다.
  - **여러 키에 해당하는 주소가 동일할 경우 충돌을 해결하기 위한 별도 자료구조가 필요함**
- 주요 용도
  - 검색이 많이 필요한 경우
  - 저장, 삭제, 읽기가 빈번한 경우
  - 캐쉬 구현시 (중복 확인이 쉽기 때문)

### 5. 충돌(Collision) 해결 알고리즘 (좋은 해쉬 함수 사용하기)
> 해쉬 테이블의 가장 큰 문제는 충돌(Collision)의 경우입니다. 이 문제를 충돌(Collision) 또는 해쉬 충돌(Hash Collision)이라고 부릅니다.

#### 기존 알고리즘의 문제점
```java
MyHash mainObject = new MyHash(20);
mainObject.saveData("DaveLee", "01022223333");
mainObject.saveData("fun-coding", "01033334444");
mainObject.saveData("David", "01044445555");
mainObject.saveData("Dave", "01055556666");
mainObject.getData("DaveLee"); //01055556666
```
### 5.1. Chaining 기법
- **개방 해슁 또는 Open Hashing 기법** 중 하나: 해쉬 테이블 저장공간 외의 공간을 활용하는 기법
- 충돌이 일어나면, 링크드 리스트라는 자료 구조를 사용해서, 링크드 리스트로 데이터를 추가로 뒤에 연결시켜서 저장하는 기법

```java
public class MyHash {
    public Slot[] hashTable;
    
    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }
    
    public class Slot {
        String key; // 특정 slot에 접근해도 뒤에 링크드리스트에 연결된 값은 아닌지 추가적으로 판단 해야함.
        String value;
        Slot next; // 링크드 리스트를 위한 next
        Slot(String key, String value) {
            this.key = key; 
            this.value = value;
            this.next = null;
        }
    }
    
    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }
    
    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) { // slot에 값이 들어가 있는 경우
            Slot findSlot = this.hashTable[address];
            Slot prevSlot = this.hashTable[address]; // 링크드 리스트를 순회하기 위한 임시변수 2개.
            while (findSlot != null) {  //  당연히 null이 아니겠지만..
                if (findSlot.key == key) { // 처음 slot에 있는 key가내가 저장하고자 하는 key일때.
                    findSlot.value = value; // 값을 갱신해준다.
                    return true;
                } else { //처음 slot에 있는 key가 내가 저장하고자 하는 key가 아니라면
                    prevSlot = findSlot;  //한 칸씩 넘어가기.
                    findSlot = findSlot.next; //순회하며 끝이라면 while 문 종료시켜주기.
                }
            }
            prevSlot.next = new Slot(key, value); 
            // 내가 설정한 key에 대한 슬롯(링크드리스트 슬롯)이 없는 경우
            // findSlot의 값이 들어가 있는 prevSlot에서 next를 이용해 새로운 slot 만들어 주기!
        } else { // slot에 값이 없는 경우
            this.hashTable[address] = new Slot(key, value); // 그냥 넣어주면 되겠지.
        }
        return true;
    }
    
    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address]; //순회하기 위한 임시변수 설정
            while (findSlot != null) {
                if (findSlot.key == key) { // key값이 같다면.
                    return findSlot.value; // value 리턴
                } else {
                    findSlot = findSlot.next; // 아니면 순회.
                }
            }
            return null; // 없으면 null 리턴
        } else {
            return null; // 비어있으면 null 리턴
        }
    }
}
```

#### 테스트
```java
MyHash mainObject = new MyHash(20);
mainObject.saveData("DaveLee", "01022223333");
mainObject.saveData("fun-coding", "01033334444");
mainObject.saveData("David", "01044445555");
mainObject.saveData("Dave", "01055556666");
mainObject.getData("Dave"); //01055556666
```

### 5.2. Linear Probing 기법
- **폐쇄 해슁 또는 Close Hashing 기법** 중 하나: 해쉬 테이블 저장공간 안에서 충돌 문제를 해결하는 기법
- 충돌이 일어나면, 해당 hash address의 다음 address부터 맨 처음 나오는 빈공간에 저장하는 기법
  - 저장공간 활용도를 높이기 위한 기법

```java
public class MyHash {
    public Slot[] hashTable;
    
    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }
    
    public class Slot {
        String key; //해당 슬롯이 내 key에 해당하는 값을 갖고 있는지 확인해야함.(위에서부터 밀려온걸 수 있으니까)
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }
    
    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
                if (this.hashTable[address].key == key) { //현재 hashTable의 key가 내가 저장하고자 하는 key와 같다면
                    this.hashTable[address].value = value; // 값 변경만 해주면 됨.
                    return true;
            } else { // 아니라면
                Integer currAddress = address + 1; // 현재 주소에서 1을 더한 값으로 임시변수 설정
                while (this.hashTable[currAddress] != null) { // 다음 슬롯이 존재하는지부터 확인. (다짜고짜 key랑 비교 불가)
                        if (this.hashTable[currAddress].key == key) { // 있는지 확인했으니까 key 비교하기!
                        // 현재 주소의 hashtable의 key가 내가 저장하고자 하는 key의 값과 같다면
                        this.hashTable[currAddress].value = value; // 변경해주면됨.
                        return true;
                    }   else { // 아니면
                        currAddress++; // 주소 늘려가며 순회해야겠지
                            if (currAddress >= this.hashTable.length) {
                                return false; // 전체 길이보다 커지면 (맨 끝단까지 확인했으면) false 리턴.
                            }                        
                    }
                } //while문 종료
                this.hashTable[currAddress] = new Slot(key, value);
                    //while문이 실행되지 않았다는 것은, currAddress 주소의 slot이 null 이었다는 것이므로, 값을 생성해주면 된다.
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value); //해당 주소의 슬롯이 없을때 ,그냥 생성만 해주면 됨.
        }
        return true;
    }
    
    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                return this.hashTable[address].value; // null이 아니고, key가 일치하다면 그냥 return!
            } else { //null은 아니지만 key가 일치하지 않을 때.                 
                // 예외 케이스로, 키에 해당하는 주소가 가장 마지막 슬롯일 경우, 
                // this.hashTable[address + 1] 에 해당하는 배열은 없기 때문에, 
                // 예외 케이스에서도 동작하도록 currAddress 는 address 만 대입하고 진행합니다
                Integer currAddress = address; // 수정 
                while (this.hashTable[currAddress] != null) { 
                    if (this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value; // 마찬가지로 일치한다면 return.
                    } else {
                        currAddress++; // 순회
                        if (currAddress >= this.hashTable.length) { //맨 끝편 까지 찾는다.
                            return null; // 없으면 null 리턴
                        }
                    }
                }
                return null;
            }
        } else { //slot이 없을때.
            return null;
        }
    }
}
```
```java
MyHash mainObject = new MyHash(20);
mainObject.saveData("DaveLee", "01022223333");
mainObject.saveData("fun-coding", "01033334444");
mainObject.saveData("David", "01044445555");
mainObject.saveData("Dave", "01055556666");
mainObject.getData("fun-coding"); // 01033334444
```

### 5.3. 빈번한 충돌을 개선하는 기법
- 해쉬 테이블 저장공간을 확대 및 해쉬 함수 재정의

```java
String name = "Dave";
int key = 0;
for (int i = 0; i < name.length(); i++) {
    key += name.charAt(i);
}

(int)(key) % 200 //184
```

### 참고: JAVA HashMap
- 해쉬 테이블 구조를 활용하여 구현된 JAVA Collection Framework 에 속한 클래스

```java
import java.util.HashMap;

HashMap<Integer, String> map1 = new HashMap();
map1.put(1, "사과");
map1.put(2, "바나나");
map1.put(3, "포도");

HashMap<String, String> map2 = new HashMap();
map2.put("DaveLee", "01033334444");
map2.put("Dave", "01032221111");
map2.put("David", "0104445555");
map1.get(2); //바나나
map2.get("Dave"); // 01032221111
```

### Java HashMap 사용법
#### 해시맵(HashMap)
- 해시맵은 이름 그대로 해싱(Hashing)된 맵(Map). 
- 맵은 키(Key)와 값(Value) 두 쌍으로 데이터를 보관하는 자료구조.
- 여기서 키는 맵에 하나만 존재. (같은 맵에 2개 이상 동일 키 x) 
- Hashtable은 HashMap과 상당히 유사.
- 두 클래스의 차이점은 Thread 관점에서 안전하냐(Hashtable)
- 안전하지 않은 대신 속도가 빠르냐(HashMap)
  
#### 선언
```java
import java.util.Map;
import java.util.HashMap;
HashMap<String,String> map1 = new HashMap<String,String>();
//HashMap생성
HashMap<String,String> map2 = new HashMap<>();
//new에서 타입 파라미터 생략가능
HashMap<String,String> map3 = new HashMap<>(map1);
//map1의 모든 값을 가진 HashMap생성
HashMap<String,String> map4 = new HashMap<>(10);
//초기 용량(capacity)지정
HashMap<String,String> map5 = new HashMap<>(10, 0.7f);
//초기 capacity,load factor(해시테이블의 버킷 채워진 비율)지정
HashMap<String,String> map6 = new HashMap<String,String>(){{
//초기값 지정
    put("a","b");
}};
```
#### 기타 용법
```java
HashMap<Integer,String> map = new HashMap<>();
//new에서 타입 파라미터 생략가능
HashMap<Integer,String> map2 = new HashMap<>();
map.put(1,"사과"); //값 추가
map.put(2,"바나나");
map.put(3,"포도");
map.put(3,"수박"); //같은 키의 새로운 value 들어가면 대치됨.
map.size(); // 3
map2.putAll(map); // putAll로 두개의 map을 합칠 수 있다. 
map2.equals(map); // key와 value가 같은지 확인
// 주의점 -> put과 replace로 값을 없앨 수 없음. 완전한 equal을 위해서라면 remove를 사용하자
map.remove(1); //key값 1 제거 , return값 "사과" (value값 return)
map.clear(); //모든 값 제거
map.replace(3, "단감"); // value를 교체해줌.
map.replace(3,"단감","곶감");// 인자가 3개인 replace 메소드.
// key 3의 값의 value가 "단감"일때만 "곶감"으로 변경하고 true 리턴
// "단감"이 아니라면 false를 리턴한다.
map.getOrDefault(key, 0);
// key값이 없으면 0를 return
map.containsKey(1);
map.containsValue("사과");
//--> boolean값 return
if(!map.containsKey(2))	
//키가 들어있는지 확인. 있으면 덮어쓰지 않는다.
//containsValue도 마찬가지로 사용 가능.
		map.put(2, "참외"); 

map.isEmpty(); // 비었으면 true, 값이 하나라도 있으면 false 리턴

```

#### 값 출력
```java
HashMap<Integer,String> map = new HashMap<Integer,String>(){{
    //초기값 지정
    put(1,"사과");
    put(2,"바나나");
    put(3,"포도");
}};
		
System.out.println(map); //전체 출력 : {1=사과, 2=바나나, 3=포도}
System.out.println(map.get(1));//key값 1의 value얻기 : 사과
		
//entrySet() 활용
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println("[Key]:" + entry.getKey() + " [Value]:" + entry.getValue());
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도

//KeySet() 활용 -> 저장된 key들을 set 객체로 리턴.
for(Integer i : map.keySet()){ //저장된 key값 확인
    System.out.println("[Key]:" + i + " [Value]:" + map.get(i));
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도
```
- entrySet()은 key와 value 모두가 필요할 경우 사용
- keySet()은 key 값만 필요할 경우 사용
- get(key)를 활용하여 value도 출력할 수도 있음
- 대부분 코드가 간단한 keySet을 활용
- 단, key값을 이용해서 value를 찾는 과정에서 시간이 많이 소모
- 많은 양의 데이터를 가져와야 한다면 entrySet()이 좋다.
- (약 20%~200% 성능 저하가 있음)