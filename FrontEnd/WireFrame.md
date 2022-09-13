## 목차
- [와이어 프레임](#와이어-프레임)
  - [구조 잡기](#구조-잡기)
  - [Id 및 Class 사용하기](#id-및-class-사용하기)
<br>
와이어 프레임
================================

구조 잡기
-------------------------
- 목표 사진
  - ![image](https://user-images.githubusercontent.com/102513932/186435772-f01c5553-4959-4590-8f58-daadc58e01dd.png)

1. 큰 틀에서 영역 나누기
    - 쓰기 영역
    - 읽기 영역
2. 각 영역을 태그로 표현하기
   - 쓰기 영역
     - ![image](https://user-images.githubusercontent.com/102513932/186436025-f16353f4-7ff7-48c7-917a-788aee3c3e73.png)
   - 읽기 영역 
     - ![image](https://user-images.githubusercontent.com/102513932/186436360-43aeb2dd-cdd3-4da7-948b-e7caf7ebd5ed.png)

3. 코드 작성
- 쓰기 영역
``` HTML
<div>
    <div> 댓글 9M</div>
    <input type ="text" placeholder="댓글을 입력해주세요">
    <button>등록</button>
</div> // 전체 태그를 div로 감싸는 것 주의.
``` 
- 읽기 영역

``` HTML
<ul>
    <li>
        <div>ouo0****</div>
        <div>시각장애인들을 위한 지원이 정말 반갑네요 앞으로도 ... </div>
        <span>2018-03-22 23:29:22</span>
        <button>
            <img src = "thumbup.png"> 0
        </button>
        <button>
            <img src = "thumbdown.png"> 0
        </button>
    </li>
    <li>
        ~
    </li>
</ul> // 구분해줘야하는 단위 파악 및 분류 요망
```

Id 및 Class 사용하기
-----------------
- HTML 태그 VS selector
  - ![image](https://user-images.githubusercontent.com/102513932/186441958-948df506-0c9b-412c-b764-1a2efc0327eb.png)
- id(#)
  - 고유한 영역인 경우
- class(.)
  - 반복되는 영역 유형별 분류시

- 쓰기 영역
  - ![image](https://user-images.githubusercontent.com/102513932/186442341-ef93be75-387b-4dc3-9177-064f9f7ae344.png)
  - 고유한 영역 다수, id 사용.
- 읽기 영역 
  - ![image](https://user-images.githubusercontent.com/102513932/186443204-50573e17-253a-48da-b301-034df5021f5d.png)
  - 반복되는 컨텐츠 다수
    - 같은 class를 통해 동일한 유형임을 확인.
    - 단, 읽기 영역 자체는 고유하므로 id를 사용.
