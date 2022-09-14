- [어서와! 자료구조 알고리즘은 처음이지? OT](#어서와-자료구조-알고리즘은-처음이지-ot)
  - [자료구조](#자료구조)
  - [Java Data Type](#java-data-type)
  - [Mutable / Immutable](#mutable--immutable)
  - [Immutable Reference](#immutable-reference)
  - [시간복잡도](#시간복잡도)
어서와! 자료구조 알고리즘은 처음이지? OT
==========
자료구조
---------
![1](https://user-images.githubusercontent.com/102513932/170987972-73072f14-5a12-4f25-96b0-d1592673f329.png)
+ 변수, 배열, 리스트는 선형자료구조
+ 맵, 그래프, 트리는 비선형자료구조

데이터가 구성되어 있을 때, 4가지 작업 수행 가능.   

 1. 새로운 데이터 추가하기(Create)
 2. 저장된 데이터 읽어오기(Read)
 3. 새로운 데이터로 변경하기(Update)
 4. 불필요한 데이터 삭제하기 (Delete)   

이를 CRUD라 통칭

Java Data Type
-----
+ ![2](https://user-images.githubusercontent.com/102513932/170989873-03437b5f-1db5-4dd3-bbe6-832889b593e5.png)

+ 컴퓨터는 cpu에 따라 데이터를 표현하는 크기다 다름.  
+ java는 JVM에서 구동 됨.
+  어떠한 컴퓨터에서도 Java 프로그램은 `일관성`있게 실행됨.

Mutable / Immutable
-------
+ 변경 가능한 값 / 변경할 수 없는 값    
  
+ final이 붙은 primitive 값은 변경될 수 없으니 immutable.   
  
+ final이 붙은 reference 변수는 변경되지 못하지만, 변경되지 못하는 값은 변수가 담고 있는 `참조 주소값`이다. 
  
  + 즉, 변수가 가리키는 `인스턴스`를 변경할 수는 없지만, `인스턴스의 내용`을 변경할 수 없다는 것을 의미하지는 않는다. 

Immutable Reference
---------
* String 객체의 경우 immutable 값
* String 내용 변경시 기존 값 변경 x / 새로운 인스턴스 생성 o
  * ![4](https://user-images.githubusercontent.com/102513932/170990944-3b774adc-9f59-41f1-a7d0-8da463ac2fab.png)
  * ![5](https://user-images.githubusercontent.com/102513932/170990950-1595d8e6-23f6-4587-a3a0-0d56b45c2e85.png)
* 참조객체의 참조 주소 출력시
  * System.identityHashCode 사용.

Call by Value / Call by Reference
+ Primitive vs Referecne
  + ![6](https://user-images.githubusercontent.com/102513932/170992049-4bd8d91d-4a4f-4e5b-93cd-aa0f43107056.png)
  + ![7](https://user-images.githubusercontent.com/102513932/170992064-f005d805-f2e0-4438-82b8-c046aea7aa21.png)

시간복잡도
-----------
  ![8](https://user-images.githubusercontent.com/102513932/170991672-6bd7f2e3-793e-4591-bfe1-60dcc8cf5209.png)
* 

