목차
---------------------
-[HTML 구성](#html-구성)<br>
-[레이아웃 리셋](#레이아웃-리셋)<br>
-[Flexbox](#flexbox)<br>
  -[display: flex 분석](#display-flex-분석)<br>
  -[부모 요소에 적용하는 Flexbox 속성]<br>(#부모-요소에-적용하는-flexbox-속성)
    -[grow](#1grow)<br>
    -[shrink](#2shrink)<br>
    -[basis](#3basis)<br>
레이아웃
==============================

HTML 구성
---------
- 수직분할
  - 콘텐츠가 가로 배치 가능하게 요소 배치
- 수평분할
  - 세로로 배치 가능하게 요소 배치
    - 수평 요소에 height 속성 추가시 보다 직관적
- 추상화 예시
  - ![image](https://user-images.githubusercontent.com/102513932/186393483-c6d0827c-867f-47b1-ad15-78104a3c9967.png)
``` html
<div id="container">
    <div class = "col w10">
        <div class="icon">아이콘 1</div>
        <div class="icon">아이콘 2</div>
        <div class="icon">아이콘 3</div>
    </div>
    <div class="col w20">
        <div class="row h40">영역1</div>
        <div class="row h40">영역2</div>
        <div class="row h40">영역3</div>
    </div>
    <div class="col w70">
        <div class="row h80">영역1</div>
        <div class="row h20">영역2</div>
    </div>
//위 레이아웃을 고려해 작성한 html 파일
```

```css
.w70 {width" 70%}
.h40 {height: 40%}
//클래스 이름에 맞는 CSS 구현
//이처럼 클래스 이름과 구현을 1:1로 일치시키는 것이 좋다.
```

레이아웃 리셋
---------------------
- HTML의 기본 스타일이 레이아웃을 잡을 때 문제가 되기도 함
  - ```<body>``` 태그각 가진 기본 스타일에 약간의 여백이 있을 때
  - width, height 계산이 여백을 포함하지 않을 때
    - box-sizing 관련 내용
  - 브라우저(크롬)마다 여백이나 글꼴과 같은 기본 스타일이 다를 때.
- 밑 코드를 사용, 기본 스타일링을 제거하여 디자인대로 레이아웃 구현 가능
```css
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
}
```

Flexbox
=======================

display: flex 분석
-----------------------

- 부모 박스 요소에 적용
  - 자식 박스의 방향과 크기를 결정

- display: flex 적용 전
```HTML
<main>
	<div>box1</div>
	<div>box2</div>
	<div>box3</div>
</main>
```

```CSS
main {
  border: 1px dotted red;
}

div {
  border: 1px solid green;
}

* {
  margin: 10px;
  padding: 10px;
}
```

![image](https://user-images.githubusercontent.com/102513932/186394269-d526044a-bc61-40b2-8e87-7d221c7cbf22.png)

- display:flex 적용 후
```css
main {
  **display: flex;**
  border: 1px dotted red;
}
```
![image](https://user-images.githubusercontent.com/102513932/186394328-321b1f0a-3437-4bf1-a28f-15ca64c5e161.png)

- 자식 요소인 ```<div>```요소에 적용
  - 가로 정렬
  - 내용만큼의 공간 차지
- Flexobx 속성 활용시
  - 정렬 (주로 부모 요소)
  - 차지하는 공간 설정(주로 자식 요소)

부모 요소에 적용하는 Flexbox 속성
----------------

1. flex-direction : 정렬 축 정하기
  - 자식 요소들을 정렬할 정렬 축 설정
  - 아무 설정 없을시, 가로 정려
```CSS
main {
	display: flex;
	**flex-direction : row;**
}
	/* 부모 요소인 main에 작성하여 자식 요소인 div들을 정렬할 축을 정합니다. */
```
![image](https://user-images.githubusercontent.com/102513932/186394451-17409bf1-9252-4107-8df2-b37b455364d5.png)

2. flex-wrap : 줄 바꿈 설정하기
    - 하위 요소의 크기가 상위 요소의 크기를 넘을 때, 자동 줄 바꿈을 할 것인지 정한다.
    - 미설정시 줄 바꿈 X
```CSS
main {
	display: flex;
	**flex-wrap : nowrap;**
}
	/* 부모 요소인 main에 작성하여 자식 요소인 div들의 줄 바꿈을 어떻게 할지 정합니다. */
```

![image](https://user-images.githubusercontent.com/102513932/186394643-070c44b5-b552-4b92-a4f7-61ce39012f9b.png)

3. justify-content : 축 수평 방향 정렬
    - 자식 요소들을 축의 수평 방향으로 어떻게 정렬할 것인가?
      - ![image](https://user-images.githubusercontent.com/102513932/186394719-00a0b1d3-2f08-4a83-a4ec-fca89acbe544.png)
      - 기본값 : row
    - ```flex-direction : row``` 인 경우
      - ![image](https://user-images.githubusercontent.com/102513932/186394993-e4a789a5-7843-4d14-9daa-52930af01d75.png)
    - ```flex- direction : column``` 인 경우
      - ![image](https://user-images.githubusercontent.com/102513932/186395087-8175e322-d81a-4cb6-9cae-fdbf7f2dfa51.png)

4. align-tiems : 축 수직 방향 정렬
   - 자식 요소들을 축의 수직 방향으로 어떻게 정렬할 것인가?
     - 기본값 : row
   - ![image](https://user-images.githubusercontent.com/102513932/186395431-6abca0fc-7c89-4b4f-9b73-557b184c8742.png)
    - ```flex-direction : row``` 인 경우
      - ![image](https://user-images.githubusercontent.com/102513932/186395629-4783f187-0f3b-47ca-8800-440429846457.png)
    - ```flex-direction : column```인 경우
      - ![image](https://user-images.githubusercontent.com/102513932/186395704-60fc052e-a273-4075-9036-29d7baaceecc.png)

자식 요소에 적용하는 Flexbox 속성
--------------------------------

- flex 속성의 값
  - 1. ```<grow(팽창 지수)>```
    - 요소의 크기가 늘어날 때 얼마나 늘어날 것인지
  - 2. ```<shrink(수축 지수)>```
    - 요소의 크기가 줄어들 때 얼마나 줄어들 것인지
  - 3. ```<basis(기본 크기)>```
    - 늘어나고 줄어듦과 상관없이 요소의 기본 크기는 얼마인지
  - 각 속성 기본값 
    - ```flex: 0 1 auto;```
```css
flex-grow: 0;
flex-shrink: 1;
flex-basis: auto;
// 따로 값 지정도 가능
```

1.grow
-----------------------
- grow는 얼마나 늘어날 것인지를 의미
  - flex 속성을 설정하기 전
    - ![image](https://user-images.githubusercontent.com/102513932/186427707-0c1b7837-5126-4084-bbfd-29c788a47830.png)
  - box1만 ```flex-gorw : 1```로 설정시 (나머지 box는 기본값인 0)
    - ![image](https://user-images.githubusercontent.com/102513932/186427871-057d295a-73b1-48cf-8747-7e361b95c383.png)
    - 남는 공간 차지 비율 1:0:0
  - box1, box2 ```flex-gorw : 1```
    - ![image](https://user-images.githubusercontent.com/102513932/186428017-a8f6a9ce-bda6-40c4-95bf-e43de718a526.png)
    - 남는 공간 차지 비율 1:1:0
  - box1, box2, box3 ```flex-gorw : 1```
    - ![image](https://user-images.githubusercontent.com/102513932/186428151-19d21130-0674-48ce-9bdf-030df7729bb9.png)
    - 남는 공간 차지 비율 1:1:1
  - ```grow```는 정렬축 방향의 빈 공간을 비율로 나눠줄 때 사용한다.
  - 즉, 자식 요소의 grow 값 / 자식 요소들의 grow값의 총합의 비율로 빈 공간을 가져간다.
 
2.shrink
----------------------------------
- grow와 반대로, 설정한 비율만큼 박스 크기가 작아진다.
- 다만, grow와 같이 조정하는 것은 권장하지 않으므로, grow만 조정하는 것을 권장한다.
  - width, flex-basis의 속성에 따른 비율이므로, 실제 크기 예측 어려움.
- 기본값인 1로 두자!

3.basis
-------------------------------
- ```basis```는 지식 박스가 ```grow```나 ```shrink```에 의해 변동되기 전 갖는 기본 크기이다.
  - ```grow```가 0일 때, basis 크기를 지정시 그 크기는 유지 된다.
  - ```display``` 속성에 ```flex```가 적용된 박스는 경우에 다라 ```basis```설정 크기가 달라질 수 있다.
- 즉, ```grow```가 0일때만, ```basis```가 정확히 보장된다.
