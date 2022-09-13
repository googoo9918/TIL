목차
-------------------
-[CSS 기초 문법](#csscascading-style-sheets-기초-문법)<br>
  -[CSS의 문법 구성](#0-css의-문법-구성)<br>
  -[CSS 파일 추가](#1-css-파일-추가)<br>
    -[CSS 스타일 적용](#11-css-스타일-적용)<br>
  -[기본적인 셀렉터](#2-기본적인-셀렉터-selector)<br>
  -[다양한 셀렉터](#3-다양한-셀렉터)<br>
  -[텍스트 꾸미기](#4-텍스트-꾸미기)<br>
  -[박스 모델](#5-박스-모델)<br>
    -[박스의 구성 요소](#51-박스의-구성-요소)<br>
    -[박스를 벗어나는 콘텐츠 처리](#52-박스를-벗어나는-콘텐츠-처리)<br>
    

CSS(Cascading Style Sheets) 기초 문법
===============================

#### 0. CSS의 문법 구성

- ![image](https://user-images.githubusercontent.com/102513932/186080121-597fa2b8-9ebd-4902-8a6f-a8de25bdc6e2.png)
  - 셀렉터는 특정 태그의 이름, ID, Class를 선택한다는 의미.
  - 속성명, 속성값을 통해 속성 변경 가능

#### 1. CSS 파일 추가
- ![image](https://user-images.githubusercontent.com/102513932/186081377-16e918b0-064d-417c-9823-1f5b44240d3d.png)
  - link 태그는 HTML 파일과 다른 파일을 연결하려는 목적으로 사용
  - link 태그 안에서 href 속성을 통해 파일 연결
  - rel은 연결하고자 하는 파일의 역할이나 특징을 나타낸다.
    - 기본적으로는 stylesheet, 다른 속성값 다수 존재
  - href 속성에는 파일의 위치를 추가해야 한다.
    - 두 파일이 한 폴더 내에 존재하면, 파일 이름만
    - 다른 폴더에 파일 존재시 절대 경로 or 상대 경로 입력

#### 1.1 CSS 스타일 적용
- 인라인 스타일
  - 같은 줄에서 스타일 적용
  - ![image](https://user-images.githubusercontent.com/102513932/186082331-20294b87-45e4-45a9-a7d6-8652dadbc0f4.png)
- 내부 스타일 시트
  - 별도의 파일로 구분하지 않고 style 태그 내에 작성
- 외부 스타일 시트
  - 별도 파일 작성
- 사용할 CSS의 내용이 현저히 적지 않은 이상, 별도 파일 작성 권장
  - 관심사 분리

#### 2. 기본적인 셀렉터 (selector)
- id로 이름붙여 스타일링 적용하기
  - ![image](https://user-images.githubusercontent.com/102513932/186082932-4612f7d5-afba-4daf-ac79-f6f88260a05b.png)
  - #기호 이용
  - id는 하나의 문서에서 한 요소에만 사용해야 한다.
- class로 이름붙여 스타일링 적용하기
  - ![image](https://user-images.githubusercontent.com/102513932/186083276-2748ac98-b7b7-4ee9-bfc5-e7e32d6ff2df.png)
  - class를 붙여서 같은 이름을 가진 영역간 차이를 둘 수 있다.
  - 동일한 기능을 하는 CSS를 여러 요소에 적용할 수 있다.
- 여러 개의 class를 하나의 엘리먼트에 적용하기
  - 띄어쓰기로 적용하려는 class들의 이름 구분
  - ![image](https://user-images.githubusercontent.com/102513932/186083497-4283b108-2022-4f47-9cbf-15dd2a8a79d7.png)
- id vs class 차이
  - ![image](https://user-images.githubusercontent.com/102513932/186083553-9a1b3652-af69-43c8-b8c3-1c13578b89ce.png)

#### 3. 다양한 셀렉터
- 셀렉터
  ```css
  h1{ } 
  div{ }
  ```
- 전체 셀렉터
 ```css
 * { }
 ```
- Tag 셀렉터
```css
section, h1 { }
 ```
- ID 셀렉터
```css
#only { } 
 ```
- class 셀렉터
```css
.widget{ }
.center{ }
 ```
- 후손 셀렉터
```css
header h1 {}
 ```
- 자식 셀렉터 
```css
header > p { }
 ```
- 형제 셀렉터 
```css
section ~p { }
 ```
- 인접 형제 셀렉터
```css
section + p { }
 ```

#### 4. 텍스트 꾸미기
- 색상
  - 글자 색상
    - color
  - 배경 색상
    - background-color
  - 테두리 색상
    - border-color
  - ![image](https://user-images.githubusercontent.com/102513932/186100699-4ee9813c-a3fe-4c43-a793-5572e8c21442.png)
- 글꼴
  - font-family
  - 따옴표 붙여서 적용
    - 글꼴이 존재하지 않거나, 디바이스에 따라 지원하지 않을 수 있음
    - 이 경우, fallback 글꼴을 통해 미연에 방지
      - 쉼표로 구분하여 입력, 입력된 순서대로 fallback 적용
    - 웹 폰트 기술 참고 가능
  - - ![image](https://user-images.githubusercontent.com/102513932/186101244-44cb5190-52cd-4e4e-b770-ee7c9024066c.png)
- 크기
  - font-size
    - 절대 단위 : px, pt
      - 기기나 브라우저 등의 환경에 영향받지 않는 절대적 크기를 정하는 경우
      - 사용자의 환경에 따라 크기가 의도와 다르게 나올 수 있음
      - 인쇄와 같이 화면의 크기가 정해진 경우 유리하다.
    - 상대 단위 : %, em, rem, ch, vw, vh
      - 일반적으로 상대 단위인 rem 추천
        - root의 크기(브라우저의 기본 글자 크기) -> 1 rem
        - 두배 -> 2rem / 0.8배 -> 0.8 rem
  - ![image](https://user-images.githubusercontent.com/102513932/186101784-27cdcf71-f9b9-4d77-bc46-983c6aefca7c.png)
- 기타 스타일링
  - 굵기
    - font-weight
  - 밑줄, 가로줄
    - text-decoration
  - 자간 
    - letter-spacing
  - 행간
    - line-height
  - 가로 정렬
    - text-align
      - legt, right, center, justify(양쪽 정렬)

#### 5. 박스 모델
![image](https://user-images.githubusercontent.com/102513932/186367039-e992d1a9-b842-48d7-a71b-d9bf33bfab00.png)
- 웹 페이지 내의 모든 콘텐츠는 고유의 영역을 지님
  - 이 영역은 항상 직사각형으로, 박스라 지칭
  - 일반적으로 하나의 콘텐츠로 묶이는 요소들이 하나의 박스가 된다.
  - 너비(width)와 높이(height)을 지닌다. 

![image](https://user-images.githubusercontent.com/102513932/186368415-8cf48711-6a9e-492d-af5e-b3ceb35c174f.png)
- 줄바꿈이 되는 박스(block)
  - 줄바꿈이 되는 대표적 태그 : ```<h1>```, ```<p>```
    - 크롬에서 개발자 도구(F12)를 통해 박스 종류 구분 가능
- 옆으로 붙는 박스(inline, inline-block)
  - 줄바꿈이 되지 않는 대표적 태그 : ```<span>```
    - span 태그의 경우, width와 height 속성또한 적용되지 않는다.
    - 따라서 속성을 적용하기 위해 태그에 `display: inline-block'을 추가해야 한다.
      - ![image](https://user-images.githubusercontent.com/102513932/186367157-dae64dbb-def0-45da-ab6b-38e0f3246419.png)
    - inline-block 박스는 inline 박스처럼 다른 요소의 옆으로 붙으며, 자체적으로 고유의 크기를 지닌다.
  - 크롬에서 개발자 도구(F12)를 통해 박스 종류 구분 가능

- ![image](https://user-images.githubusercontent.com/102513932/186367212-d20a6d11-a089-496c-9b7f-874a13304a53.png)

#### 5.1 박스의 구성 요소
![image](https://user-images.githubusercontent.com/102513932/186367272-751b0a98-dcc9-403c-98d4-036d44d81656.png)

- border(테두리)
  - 레이아웃을 시각적으로 확인 가능
 ``` css
 p{
    border: 1px solid red;
} // p 태그에 1px의 빨간색 실선 추가
```
  - 테두리 두께 : border-width
  - 테두리 스타일 : border-style
  - 테두리 색상 : border-color
- margin (바깥 여백)
  - 상, 우, 하, 좌로 시계방향 순서
 ```css 
 p{
    margin: 10px 20px 30px 40px;
} //p 태그의 상하좌우에 여백 추가
```
  - 크롬 개발자 도구에서 확인 가능
    - margin은 주황색으로 표시된다.
  - 값을 2개만 넣으면 위아래, 좌우에 여백 적용
```css
p {
  margin: 10px 20px;
}
```
  - 값을 1개만 넣으면 모든 방향에 적용
```css
p{
    margin: 10px;
}
```
- padding (안쪽 여백)
  - border을 기준으로 박스 내부의 여백 지정
  - 여러 규칙은 margin과 동일하다.
  - padding은 크롬 개발자 도구에서 초록색으로 표현된다.

#### 5.2 박스를 벗어나는 콘텐츠 처리
- 박스 크기보다 콘텐츠 크기가 큰 경우, 스크롤 생성 가능
```css
p{
    height : 40px;
    overflow: auto;
} overflow: auto를 통해 스크롤 생성
```
  - overflow-x/overflow-y를 통해 두 방향 모두 지정 가능
- 콘텐츠 내용을 보여주고 싶지 않은 경우, ```overflow:hidden``` 사용
- 박스 크기 측정 기준
  - 박스에 적용할 여백을 고려해야 한다.
  - ![image](https://user-images.githubusercontent.com/102513932/186369579-47bbf5c7-cea7-4f58-a4c7-fdddf9764e2a.png)
    - 레이아웃 관련 이야기는 border-box 계산법이 기준
```css
* {
    box-sizing : border-box;
}
```
- 모든 박스에서 여백과 테두리를 포함한 크기로 계산된다.
  - 레이아웃 디자인을 보다 편하게 할 수 있음. 

![image](https://user-images.githubusercontent.com/102513932/187020691-0390f320-90be-4c7f-82d8-a6e38fb0e345.png)


