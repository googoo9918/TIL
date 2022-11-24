# JWT 생성 및 검증 테스트
## JWT 생성 및 검증 테스트를 위한 프로젝트 설정
- Sptirng Boot 기반의 템플릿 프로젝트 생성 및 의존 라이브러리 추가
```java
dependencies {
  // (1)
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'

  // (2)
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'
}
```
- (1)은 이후 학습에서 필요한 Spring Framework와 관계된 라이브러리
- (2)는 JWT 생성 및 검증 테스트를 수행하기 위해 필요한 JWT 라이브러리
  - jjwt 사용

## JWT 생성
### JWT 생성 기능 구현
```java
public class JwtTokenizer {
    // (1)
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // (2)
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey); // (2-1)

        return Jwts.builder()
                .setClaims(claims)          // (2-2)
                .setSubject(subject)        // (2-3)
                .setIssuedAt(Calendar.getInstance().getTime())   // (2-4)
                .setExpiration(expiration)  // (2-5)
                .signWith(key)              // (2-6)
                .compact();                 // (2-7)
    }

    // (3)
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
    
    ...
    ...

    // (4)
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);  // (4-1)
        Key key = Keys.hmacShaKeyFor(keyBytes);    // (4-2)

        return key;
    }
}
// (코드 4-56) JWT 생성을 위한 JwtTokenizer 클래스
```
- (1)의 `encodeBase64SecretKey()` 메서드는 Plain Text 형태인 Secret Key의 byte[]를 Base64 형식의 문자열로 인코딩함
  - jjwt가 버전업 되며 Plain Text 자체를 Secret Key로 사용하는 것은 권장하지 않음
    - 암호학적인 작업에 사용되는 Key가 항상 바이너리(byte array)라는 사실과 맞지 않는 것을 감안함
- (2)의 `generateAccessToken()`은 인증된 사용자에게 JWT를 최초로 발급해주기 위한 JWT 생성 메서드임
  - (2-1)에서는 Base64 형식 Secret Key 문자열을 이용해 `Key(java.security.Key)` 객체를 얻음
  - (2-2)의 `setClaims()`에는 JWT에 포함 시킬 Custom Claims를 추가
    - Custom Claims에는 주로 인증된 사용자와 관련된 정보를 추가함
  - (2-3)의 `setSubject()`에는 JWT에 대한 제목을 추가
  - (2-4)의 `setIssuedAt()`에는 JWT 발행 일자를 설정
    - 파라미터 타입은 `java.util.Date`임
  - (2-5)의 `setExpiration()`에는 JWT의 만료일시를 지정함
    - 파라미터 타입은 `java.util.Date`
  - (2-6)의 `signWith()`에 서명을 위한 Key(`java.security.Key`)객체 설정
  - (2-7)의 `compact()`를 통해 JWT를 생성하고 직렬화 함
- (3)의 `generateRefreshToken()` 메서드는 Access Token 만료 시, Access Token을 새로 생성할 수 있게 해주는 Refresh Token 생성 메서드임
  - Access Token 생성 목적이기에 별도 Custom Claims는 추가할 필요 없음
- (4)의 `getKeyFromBase64EncodedKey()` 메서드는 JWT의 서명에 사용할 Secret Key 생성
  - (4-1)의 `Decoders.BASE64.decode()` 메서드는 Base64 형식으로 인코딩 된 Secret Key를 디코딩 한 후, byte array를 반환함
  - (4-2)의 `Keys.hmacShaKeyFor()` 메서드는 key byte array를 기반으로 적절한 HMAC 알고리즘을 적용한 Key(`java.security.Key`)객체 생성

### JWT 생성 기능 테스트
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 학습이 핵심이 아니니 구글에서 검색해서 학습하세요.
public class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretKey;
    private String base64EncodedSecretKey;

    // (1)
    @BeforeAll
    public void init() {
        jwtTokenizer = new JwtTokenizer();
        secretKey = "kevin1234123412341234123412341234";  // encoded "a2V2aW4xMjM0MTIzNDEyMzQxMjM0MTIzNDEyMzQxMjM0"

        base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);
    }

    // (2)
    @Test
    public void encodeBase64SecretKeyTest() {
        System.out.println(base64EncodedSecretKey);

        assertThat(secretKey, is(new String(Decoders.BASE64.decode(base64EncodedSecretKey))));
    }

    // (3)
    @Test
    public void generateAccessTokenTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date expiration = calendar.getTime();

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        System.out.println(accessToken);

        assertThat(accessToken, notNullValue());
    }

    // (4)
    @Test
    public void generateRefreshTokenTest() {
        String subject = "test refresh token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        System.out.println(refreshToken);

        assertThat(refreshToken, notNullValue());
    }
}
// (코드 4-57) JWT 생성 기능을 테스트 하기 위한 테스트 케이스
```
- (1)에서 테스트에 사용할 Secret Key를 Base 64 형식으로 인코딩
  - 인코딩 된 Secret Key를 각 테스트 케이스에서 사용
- (2)에서는 Plain Text인 Secret Key가 Base64 형식으로 인코딩이 정상적으로 수행 되는지 테스트
  - Base64 형식으로 인코딩 된 Secret Key를 디코딩한 값이 원본 Plain Text Secret Key가 일치하는지를 테스트
- (3)에서는 JwtTokenizer가 Access Token을 정상적으로 생성하는지 테스트
  - JWT는 생성할 때 마다 그 값이 바뀜, 따라서 우선 생성된 Access Token이 null이 아닌지 여부만 테스트함
  - 생성 과정에서 Exception이 발생하지 않았기 때문에 정상적으로 생성되었다고 봐도 무방, 더 정확한 테스트는 JWT의 서명 검증에서 확인 가능
- (4)에서는 JwtTokenizer가 Refresh Token을 정상적으로 생성하는지 테스트
  - Custom Claims가 필요하지 않다는 것 외에는 Access Token과 테스트 과정은 동일함

## JWT 검증
### JWT 검증 기능 구현
```java
public class JwtTokenizer {
    ...
    ...

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)     // (1)
                .build()
                .parseClaimsJws(jws);   // (2)
    }

    ...
    ...
}
// (코드 4-58) JWT 검증을 위한 verifySignature() 메서드 추가
```
- JWT는 JWT에 포함되어 있는 Signature를 검증함으로써 JWT의 위/변조 여부 확인 가능
- jjwt에서는 JWT를 생성 시 서명에 사용된 Secret Key를 이용해 내부적으로 Signature를 검증, 검증에 성공하면 JWT를 파싱해 Claims를 얻을 수 있음
  - (1)의 `setSigningKey()` 메서드로 서명에 사용된 Secret Key 설정
  - (2)의 `parseClaimsJws()` 메서드로 JWT를 파싱해 Claims를 얻음
    - `verifySignature()` 메서드는 Signautre를 검증하는 용도로, Claims를 리턴 할 필요는 없음
      - 파라미터로 사용한 `jws`는 Signature가 포함된 JWT라는 의미

### JWT 검증 기능 테스트
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretKey;
    private String base64EncodedSecretKey;

    ...
    ...

    // (1)
    @DisplayName("does not throw any Exception when jws verify")
    @Test
    public void verifySignatureTest() {
        String accessToken = getAccessToken(Calendar.MINUTE, 10);
        assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
    }

    // (2)
    @DisplayName("throw ExpiredJwtException when jws verify")
    @Test
    public void verifyExpirationTest() throws InterruptedException {
        String accessToken = getAccessToken(Calendar.SECOND, 1);
        assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));

        TimeUnit.MILLISECONDS.sleep(1500);

        assertThrows(ExpiredJwtException.class, () -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
    }
    
    ...
    ...

    private String getAccessToken(int timeUnit, int timeAmount) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, timeAmount);
        Date expiration = calendar.getTime();
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }
}
// (코드 4-59) JWT를 검증하기 위한 테스트 케이스
```
- (1)에서는 JwtTokenizer의 `verifySignature()` 메서드가 Signature를 잘 검증하는지 테스트
  - 생성된 JWT를 `verifySignature()`로 전달하여 Exception이 발생하지 않는다면 Signautre에 대한 검증이 잘 수행된 것으로 판단
- (2)에서는 JWT 생성 시 지정한 만료일시가 지나면 JWT가 정말 만료되는지 테스트
  - 생성되는 JWT의 만료 주기를 아주 짧게 설정한 후 첫 번째 Signautre 검증을 수행
    - 이후 만료일시가 지나도록 지연시간을 준 뒤, 두 번째 Signature 검증을 수행했을 경우 `ExpiredJwtException`이 발생하면 JWT가 정상적으로 만료된 것 확인 가능

### 핵심 포인트
- Plain Text 자체를 Secret Key로 사용하는 것은 권장되지 않음
- jjwt 최신 버전(0.11.5)에서는 서명 과정에서 HMAC 알고리즘을 직접 지정하지 않음
  - **내부적으로 적절한 HMAC 알고리즘 지정**