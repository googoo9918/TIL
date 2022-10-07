### 목차
- [AJAX](#ajax)
  - [1. AJAX란?](#1-ajax란)
  - [2. AJAX의 두 가지 핵심 기술](#2-ajax의-두-가지-핵심-기술)
  - [3. AJAX의 장점](#3-ajax의-장점)
  - [4. AJAX의 단점](#4-ajax의-단점)
## AJAX
### 1. AJAX란?
  - Asynchronous JvaScript And XMLHttpRequest
  - JavaScript, DOM, Fetch, XMLHttpRequest, HTML 등의 다양한 기술을 사용하는 웹 개발 기법
  - 웹 페이지에 필요한 부분에 필요한 데이터만 비동기적으로 받아와 화면에 그려낼 수 있음
    - ex) 구글 홈페이지에서 검색창은 검색창에 한 글자를 입력할 때마다, 다른 추천검색어를 보여줌
    - 검색창에서는 필요한 데이터만 비동기적으로 받아와 렌더링, 여기에 AJAX가 사용됨
    - ex2) 무한 스크롤
    - 무한 스크롤이 발생할 때마다 Fetch를 통해 데이터를 가져와 업데이트하고 렌더링함
### 2. AJAX의 두 가지 핵심 기술
  - JavaScript, DOM 그리고 Fetch
  - 전통적인 웹 애플리케이션에서는 ```<form>``` 태그를 이용해 서버에 데이터를 전송함
    - 서버는 요청에 대한 응답으로 새로운 웹 페이지를 제공함
    - 다시 말해, 요청을 보내면 매번 새로운 페이지로 이동해야 했음 
  - Fetch 이용 시, 페이지를 이동하지 않아도 서버로부터 필요한 데이터를 받아올 수 있음
    - Fetch는 사용자가 현재 페이지에서 작업을 하는 동안 서버와 통신할 수 있도록 함
    - 브라우저는 Fetch가 서버에 요청을 보내고 응답을 받을 동안 계속해서 페이지를 사용할 수 있게 하는 비동기적인 방식 사용
  - 자바스크립트에서 DOM을 사용해 조작할 수 있음
    - Fetch를 통해 전체 페이지가 아닌 필요한 데이터만 가져와 DOM에 적용, 새 페이지로 이동하지 않고 기존 페이지에서 필요한 부분만 변경할 수 있음
  - Fetch 이전에는 XHR을 주로 사용
    -  Fetch는 XHR의 단점을 보완한 새로운 Web API
       -  XML보다 가볍고 JavaScript와 호환되는 JSON 사용
   - 오늘날에는 XHR보다는 Fetch 많이 사용
### 3. AJAX의 장점
  - 서버에서 HTML을 완성하여 보내지 않아도 웹 페이지를 만들 수 있음
    - 필요한 데이터를 비동기적으로 가져와 브라우저에서 화면의 일부만 업데이트하여 렌더링 가능
    - XHR이 표준화 되면서 브라우저에 상관없이 AJAX 사용 가능
    - 필요한 일부분만 렌더링 하기 때문에 빠르고, 더 많은 상호작용이 가능한 애플리케이션을 만들 수 있음
    - 더 작은 대역폭을 지님
      - 필요한 데이터를 텍스트 형태(JSON, XML)로 보내면 되기 때문에 비교적 데이터의 크기가 작음
### 4. AJAX의 단점
  - Search Engine Optimization(SEO)에 불리함
    - 처음 받는 HTML 파일에는 틀만 작성되어 있는 경우가 많음
      - 뼈대만 있고 데이터는 없기 때문에 사이트에서 크롤링 하기 어려움
  - 뒤로가기 버튼 문제
    - 일반적으로 뒤로가기 버튼을 누르면 이전 상태로 돌아갈 수 있을 것이라 생각하지만
      - AJAX에서는 이전 상태를 기억하지 않기 때문에 불가능함
    - 뒤로가기 등의 기능을 구현하기 위해서는 History API를 사용해야 함