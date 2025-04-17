## MyBatis를 Dto로 받으면서 생긴 문제
- 해당 문제는 Select 쿼리의 결과를 ResponseQueryDto로 받으면서 생긴 문제이다.
- 나는 굉장히 옛날에 만들어진 DB를 사용하고 있었기 때문에, DB 컬럼명과 Dto의 필드명을 똑같이 맞추진 못하고
    - Alias를 사용하여 DB에서 Dto의 필드명으로 반환할 수 있게 설정하고 있었다.
- 쿼리의 결과에서는 어쩔 수 없이 null을 반환하는 것들이 존재하였는데
    - 이때, Alias와 Dto의 필드명이 모두 정확히 일치하였음에도 불구하고
    - null이 들어가야 하는 값에 뒤 필드에 있는 값들이 들어가고 있었다.
- ResponseQueryDto에서 생긴 문제인지, 그냥 QueryDto에서 생긴 문제인지 구분할 수 없었기 때문에 직접 print를 찍어보고, mapper 구현체를 까보았다.
    - mapper 구현체에는 별다른 특이점은 보이지 않았고, ResponseQueryDto의 출력값을 확인하니 여기서 문제가 생긴 것을 확인할 수 있었다.
- 그렇다면, 그냥 Query의 반환값을 제대로 매핑하지 못하고 있다는 것인데...
    - int, long, double도 아닌 단순 String에 불과할 뿐인데 왜 문제가 생기는 것일까

### 해결책 (시도)
- 1. 일단 @Setter를 추가해 보았다.
    - QueryResponse의 필드에 setter가 없어서 값들을 제대로 넣고 있지 못하나? 라는 생각이 들어서..
    - 근데 그렇다면 다른 필드들에도 값이 제대로 들어가지 못했어야 했던거 아닐까?
    - *실패*ㅠㅠ
- 2. @Setter와 @NoArgsConstructor을 추가해 보았다.
    - mapper 구현체를 생각해보면, 기본 생성자를 통해 모든 것을 시작했던 것이 기억났기 때문
        - 직접 찾아보니 잘못된 기억이긴 했다 ㅋㅋ;
        - get을 통해 값을 다 얻어온 다음 전체 생성자로 한 번에 생성하긴 하네
    - 해결되었다!!
        - 혹시 몰라 @Setter를 제거하고, @NoArgsConstruct만 남겨두었는데도 정상 동작하였다.
        - 이건 왜일까????!?
- 3. 찾아보니, 동작과정은 다음과 같다.
    - 마이바티스는 먼저 해당 필드에 대한 Setter 메서드를 찾으려고 한다.
        - 만약 @Setter가 존재했다면, 이 방식으로 값이 채워졌을 것이다.
    - 마이바티스는 Setter 메서드를 찾지 못하면, 차선책으로 자바 리플렉션(Java Reflection) API를 사용한다.
    - **리플렉션(Reflection) 이란?**
        - 리플렉션은 프로그램 실행 중, 클래스의 구조(필드, 메서드 등)을 검사하고
        - 심지어 pricate 필드에 대해서도 직접 접근하여 값을 읽거나 쓸 수 있는 강력한 기능 제공
    - 즉, 마이바티스는 리플렉션을 이용해 private 필드에 직접 접근, DB 컬럼 값을 할당한 것이다.
    - 따라서 객체 생성을 위해 기본 생성자가 필요하기 때문에, `@NoArgsConstruct`가 필수적이었던 것.
- 4. 그렇다면, 객체 불변성을 포기하더라도, @Setter를 추가하는게 좋을까? @Setter를 사용할 때와 마이바티스가 Reflection을 사용할 때는 어떤 차이점이 존재할까?
    - `@NoArgsConstructor`을 사용하는 시점에서, 이미 객체는 '완전한 불변성'을 달성하기는 어려움
        - 빈 객체가 생성된 후 값이 채워지기 때문.
    - 다만 `@Setter`를 노출하지 않는다면, 애플리케이션 단에서는, 사용 시점의 불변성처럼 보일 수는 있겠다.
        - 이러한 측면에서, 이미 불변성을 포기했으니 차라리 `@Setter`를 추가하여 값 설정 매커니즘을 명확히 드러내는게 좋을 수도 있겠다.
    - 혹은 다음과 같이 myBatis에서 생성자를 사용할 수는 있으나....
        - lombok의 `@Value`를 사용해야 하긴 함
            - 모든 필드 private final로 선언, 모든 필드 인자 public 생성자, Getter 메서드, toString(), equals(), hashCode() 등
            - Setter는 생성되지 않아 불변성 유지
            - 엥 그러면 그냥 원래 하던대로 @Getter랑 @AllArgsConstruct만 써도 될듯?
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.UserMapper">

    <resultMap id="userResultMap" type="com.example.dto.UserDto">
        <constructor>
            <idArg column="user_id" javaType="long"/>

            <arg column="user_name" javaType="java.lang.String"/>
            <arg column="email" javaType="java.lang.String"/>
            <arg column="registration_date" javaType="java.time.LocalDateTime"/>
        </constructor>
        </resultMap>

    <select id="findUserById" resultMap="userResultMap">
        SELECT
            user_id,
            user_name,
            email,
            registration_date
        FROM
            users
        WHERE
            user_id = #{userId}
    </select>

</mapper>
```
- 보는 것과 같이 상당히.. 뭐랄까 불친절하니 잘 생각해보자 ㅠ