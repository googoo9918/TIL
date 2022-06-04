어서와! 자료구조 알고리즘은 처음이지? List
=======================================

Array
-------------
![1](https://user-images.githubusercontent.com/102513932/171852670-5c65fd2b-a82d-485f-b10c-ef855fddec3e.png)
![2](https://user-images.githubusercontent.com/102513932/171852679-141803a4-222d-4286-9816-e7f919127032.png)

List
-------------------
![4](https://user-images.githubusercontent.com/102513932/171852685-a8f20ec5-b4e0-4c4d-8386-a75f73d3b55e.png)
![5](https://user-images.githubusercontent.com/102513932/171852694-f7a659cd-3417-4c0a-8c5c-fb467b4d2f6e.png)
![6](https://user-images.githubusercontent.com/102513932/171852697-f35c4d1f-8257-4bc9-8fc2-e0b123e5e985.png)

```java
    class MyData{
	int value;
	public MyData(int value) {
		this.value = value;
	} // 생성자 블록
	static MyData create(int v) {
		return new MyData(v);
	} // 메소드 블록
}
public class MakeList {

	/**
	 * Java에서는 linked list를 제공하고 있다.
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		// list의 요소로 들어갈 타입을 generic으로 지정.
		// generic type이기 때문에 primitive타입은 값 저장 불가능.
		// int 타입은 불가능. int 타입의 wrapper class인 Integer 사용 가능.
		
		list.add(1); // 리스트의 값을 추가할때는 add 사용.
		list.add(2);
		list.add(3);
		
		list.add(1, 5); // 1번 index 자리에 5를 삽입! 123 -> 1523
	
		list.remove(2); // 2번 index 삭제. 1523 -> 153
		
		list.addFirst(6); //153-> 6153
		
		list.addLast(7); //6153 -> 61537
		
		list.addAll(list); // list를 추가할 수 있음!( 61537 -> 6153761537 
		
		// list.clear(); list의 모든 요소 삭제
		
		System.out.println(list.contains(8)); 
		// 포함되어있는지  확인. 받아들이는 타입이 object 타입임.
		// primitive 값을 넣어도 작동하는 이유는,
		// 자동으로 boxing되어 Integer.valueOf(5)값으로 들어가기 때문이다.
		
		System.out.println(list);
		// list에는 toString이 오버라이딩 되어있어서 내용이 바로 출력됨.
		
		System.out.println(list.get(2));
		// index를 설정하여 값을 하나만 꺼내올 수 있다.
		
		LinkedList<MyData> list2 = new LinkedList<>();
		// 순서를 지켜서 사용할 수 있도록 설정 되어 있음.
		list2.add(MyData.create(1));
		list2.add(new MyData(1));
		
		System.out.println(list2);
		//list2 의 reference값 출력됨. 
	
	}
```