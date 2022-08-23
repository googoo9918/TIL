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

  