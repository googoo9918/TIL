HTML 기초 문법
==============================

#### 0. HTML 요소
![image](https://user-images.githubusercontent.com/102513932/186053947-67f5abf8-1d86-48a3-a40d-3e253d5a976d.png) <br>


#### 1. div , span
- div는 줄 바꿈 / span은 옆으로 붙는다
- 텍스트 표현시 div는 사각형 박스로 구역 지정 / span은 문장 단위 지정
  - div는 width, height 크기 지정 가능
  - span은 inline 속성을 가져 지정 불가능
- div의 margin은 4방향 모두 적용, 위 아래 여백 상쇄
- span의 margin은 양 옆으로만 적용, 겹쳐지지 않아 더 큰 여백
<br><br>

![image](https://user-images.githubusercontent.com/102513932/186052736-7faa04ea-ef20-487a-b523-a9d7845015a4.png)

#### 2. 이미지
- 닫는 태그 필요 x
- src : 이미지의 경로
- alt : 이미지를 표시할 수 없을 때 출력할 내용
  - alt 속성이 없는 것을 검색 엔진이 싫어함. 공백이라도 넣어주자
- width : 이미지의 가로 크기
- height : 이미지의 세로 크기
- ex)
  -  ``` <img src="images/200x200.png" alt="My Image"> ```
  -  ``` <img src="images/200x200.png" alt="My Image" width="100" height="200"> ```
![image](https://user-images.githubusercontent.com/102513932/186056519-676fadb0-816c-4be3-a5d6-f00ae2a82c57.png)

#### 3. 링크 삽입
- 기본형식
  - ```<a>``` 태그 
  - ``` <a href="이동할 위치">링크를 걸어 줄 내용물(텍스트, 이미지)</a> ```
- href = "URL"
  - 링크를 통해 이동하려는 곳의 주소 설정
    - ``` <a href="http://www.naver.com">네이버</a> ```
- target = "창이름"
  - 링크가 클릭 되었을 때 내용이 표시될 창 지정
    - _blank : 새로운 창(탭) 이용 
    - _self  : 현재의 창(탭) 이용

![image](https://user-images.githubusercontent.com/102513932/186053171-ee8ae106-9ef5-450a-a911-4483b43f1742.png)

#### 4. 리스트

- ```<ol>```
  - ordered list, 숫자나 알파벳 등 순서 있는 목록
  - ![image](https://user-images.githubusercontent.com/102513932/186058868-4d61c986-f9b6-4d6b-a80e-6ce65bbfa2ea.png)
  - ![image](https://user-images.githubusercontent.com/102513932/186058901-03b6317d-f2a3-4f9c-9f8b-e0c6a5cca171.png)
- ```<ul>```
  - unordered list, 순서가 필요 없는 목록
  - ![image](https://user-images.githubusercontent.com/102513932/186053136-d2ae7463-8443-4ab0-8163-7927c114532d.png) <br>
- ```<dl>```
  - definition list, 용어를 설명하는 목록
  - ![image](https://user-images.githubusercontent.com/102513932/186059393-b3040aac-1ef8-4ea7-9821-81283094ed50.png)
  - ![image](https://user-images.githubusercontent.com/102513932/186059405-4d5bcb99-eef5-4e4c-a003-eeba43b604b2.png)
- ```<li>```
  - list, 목록을 만드는 태그 , 단독 사용X

#### 5. 다양한 입력 폼
- ``` <input> ``` 태그의 다양한 옵션 존재
    - ``` <input type="text"> ```
      - ![image](https://user-images.githubusercontent.com/102513932/186068387-a7965437-afb4-4410-bc62-d52d2b6d84cc.png)

      - First name:<br>
         <input type="text" name="firstname"><br>
        Last name:<br>
        <input type="text" name="lastname">
    - ``` <input type="password"> ```
      - ![image](https://user-images.githubusercontent.com/102513932/186068438-614c8ca4-c9e4-44a9-9542-02a0b7c31bc3.png)
      -  User name:<br>
  <input type="text" name="username"><br>
  User password:<br>
  <input type="password" name="psw">

    - ``` <input type="submit"> ```
      -  ![image](https://user-images.githubusercontent.com/102513932/186068601-4a193ab3-bd5d-4254-b9a6-cbca54dc7f2a.png)
      -  <input type="submit" value="Submit">
    - ``` <input type="radio"> ```
      - 하나만 선택 가능
      - ![image](https://user-images.githubusercontent.com/102513932/186068850-c5d8dab6-cb7f-421a-9207-f09314520fa2.png)
 <input type="radio" id="male" name="gender" value="male">
  <label for="male">Male</label><br>
  <input type="radio" id="female" name="gender" value="female">
  <label for="female">Female</label><br>
  <input type="radio" id="other" name="gender" value="other">
  <label for="other">Other</label>


    - ``` <input type="checkbox"> ```
      - 중복 선택 가능
      - ![image](https://user-images.githubusercontent.com/102513932/186069015-e27ecf30-f23a-41ad-a0df-83d386b5535d.png)
  <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
  <input type="checkbox" name="vehicle2" value="Car"> I have a car

    - ``` <input type="button"> ```
      - ![image](https://user-images.githubusercontent.com/102513932/186069134-7c557819-65f4-4705-9783-6e2ef5481bcf.png)
      - <input type="button" onclick="alert('Hello World!')" value="Click Me!"> 
- ```<textarea>``` 태그
  - 여러줄 문자열 입력 가능
    - ```<textarea cols="50" rows="10"></textarea>```
    - <textarea cols="50" rows="10"></textarea>
      - placeholder 속성으로 안내 문구를 넣을 수 있다.
![image](https://user-images.githubusercontent.com/102513932/186053212-aa3b8041-4d07-4aed-ac1d-24fe35d0a88d.png)
