# Eclipse에서 Tomcat 9 → 10 마이그레이션 시 `org.w3c.dom` Forbidden 에러 해결 가이드

> **목표**  
> - Java 17 환경에서 Eclipse를 사용하여, Tomcat 10(=Jakarta EE)로 마이그레이션했을 때 발생하는  
>   **`org.w3c.dom` Forbidden reference** 에러를 단계별로 해결한다.
> - 이 문서는 **가장 상세한** 단계별 방법들을 **모두** 소개한다.
> - 필요에 따라 순서대로 시도해보면서, 문제가 해결되는지 확인할 수 있다.

---

## 전제 / 사전 준비

1. **Eclipse**를 사용 중이며, 프로젝트의 **Java 버전**은 **17**.  
2. **Tomcat 10**이 설치되어 있고, Eclipse에 등록되어 있다.  
   - (서버 설정: `Window > Preferences > Server > Runtime Environments` 등에서 확인)  
3. 기존에는 **Tomcat 9**를 사용하다가 **Tomcat 10**으로 마이그레이션함.  
4. **Forbidden reference** 에러 메시지 중 `org.w3c.dom` 관련 오류가 발생.

---

## 단계별 시나리오

아래 순서는 **위에서부터 차례대로** 진행할 것을 권장한다.  
각 단계는 **독립적**으로도 시도 가능하지만, 상호 보완적이므로 **가능하면 순서대로** 따라가며 확인한다.

---

### 1. Eclipse에서 Java 17을 제대로 쓰고 있는지 확인

1. **Eclipse 상단 메뉴**에서 `Window > Preferences` (맥은 `Eclipse > Preferences`)  
2. **왼쪽 트리에서** `Java > Installed JREs` 진입  
   - 현재 등록되어 있는 JRE/JDK 목록에서 **Java 17(또는 JDK 17)**이  
     1) 등록되어 있는지  
     2) **기본(Default)** 체크가 되어 있는지 확인  
3. 만약 Java 17이 없다면, **Add...** 버튼을 클릭해 **JDK 17** 설치 디렉터리를 등록 후 **기본(Default)**으로 설정.  
4. **프로젝트 우클릭** → **Properties** → **Java Build Path** → **Libraries** 탭  
   - “JRE System Library” 항목이 **JavaSE-17**을 가리키는지 확인.  
5. **프로젝트 우클릭** → **Properties** → **Java Compiler**  
   - “Compiler compliance level”이 **17**로 되어 있는지 확인.  
   - 만약 `Use compliance from execution environment 'JavaSE-17'` 항목이 있으면 체크.

> **결과**:  
> - 이 단계를 통해 **Eclipse**와 **프로젝트**가 실제로 **Java 17**을 사용하는지 확실히 한다.  
> - 그렇지 않으면 뒤 단계에서 어떤 조치를 해도 Forbidden 문제가 계속 발생할 수 있음.

---

### 2. 프로젝트에서 `org.w3c.dom`을 어디서/어떻게 쓰는지 확인

이 단계는 문제의 **원인**을 좀 더 명확히 파악하기 위한 것이다.

1. **프로젝트 전체 검색**  
   - `org.w3c.dom`을 **import**하거나 사용하는 코드가 있는지 확인.  
   - 주로 XML 파싱, DOM 처리 등에서 `Document`, `Element`, `Node` 등을 쓰는 경우가 많다.  
2. (선택) **Maven/Gradle** 사용 시  
   - `pom.xml` 또는 `build.gradle`에서 `xercesImpl`, `xml-apis` 등의 라이브러리가 의존성으로 들어 있는지 검색.  
   - **Java 17**에는 이미 DOM, SAX, JAXP 등이 포함되어 있으므로, 불필요하다면 제거 or `provided` 스코프로 변경 가능.  
3. **Tomcat 10이 가져오는 jar**  
   - 이 문서의 핵심 이슈는 주로 `xml-apis.jar`, `xercesImpl.jar` 등.  
   - **Eclipse** 빌드 경로에 Tomcat 10이 자동으로 추가하는 이 jar들이 들어와서 JDK 17 표준 API(`org.w3c.dom`)와 중복 충돌을 일으키는 경우가 많다.  

> **결과**:  
> - 실제로 충돌을 일으키는 라이브러리가 있는지, 그리고 `org.w3c.dom`을 **직접** 많이 쓰고 있는지 파악.

---

### 3. (가장 깔끔한 방법) Tomcat 라이브러리에서 불필요한 jar를 “제거”하기

> **목표**:  
> - 빌드 경로에서 **`xml-apis.jar`, `xercesImpl.jar`** 등 **중복 XML 라이브러리**를 완전히 제외하여,  
>   JDK 17의 기본 API와 충돌이 일어나지 않도록 하는 것.

**3-1.** Tomcat 라이브러리를 **통째로 제거** 후 필요한 라이브러리만 “수동 추가”하는 방법

1. **프로젝트 우클릭** → **Properties**  
2. **Java Build Path** → **Libraries** 탭  
3. 목록에 보이는 **“Apache Tomcat v10…”** 항목을 선택  
4. **Remove** 버튼(혹은 마이너스 아이콘) 클릭  
   - 이렇게 하면, **Eclipse**에서 Tomcat 10 관련 jar들을 **컴파일 시점**에 사용하지 않게 된다.  
5. **적용(OK)** 후, **Project** → **Clean** 실행  
   - 프로젝트 빌드 경로에서 Tomcat 10 jar가 완전히 제거된다.  
6. **서블릿 API / JSP API** 등 **웹 애플리케이션**에 필요한 라이브러리는 직접 추가해야 한다.  
   - 예: Maven 프로젝트라면 `pom.xml`에  
     ```xml
     <dependency>
       <groupId>jakarta.servlet</groupId>
       <artifactId>jakarta.servlet-api</artifactId>
       <version>5.0.0</version>
       <scope>provided</scope>
     </dependency>
     ```
     같이 설정.  
   - Maven 사용이 아니라면, Tomcat 설치 폴더 `lib/` 안에서 **서블릿 API** 관련 jar만 복사해서 **Add External JARs**로 등록해도 된다.  
7. 이제 **중복 XML 라이브러리**(xml-apis, xercesImpl)는 **프로젝트 빌드 경로**에 없으므로, `org.w3c.dom` Forbidden 충돌이 발생하지 않는다.

> **장점**:  
> - Tomcat 10 컨테이너를 Eclipse 빌드 경로에서 완전히 빼버려서, **충돌 가능성**을 원천 제거.  
> - 필요한 라이브러리만 **정확히** 추가하므로, 환경이 **더 명확**해진다.

**3-2.** Tomcat 컨테이너는 유지하되, jar 개별적으로 제거하는 방법 (잘 안 되는 경우가 많음)

- Eclipse에서 **Java Build Path > Libraries** 탭에서 “Apache Tomcat v10…” 항목을 **확장(▶)**하면, 실제로 여러 jar 파일들이 나열된다.  
- `xml-apis.jar`, `xercesImpl.jar` 등이 있으면, 각각 선택 후 **Remove**로 제거를 시도할 수 있다.  
- 하지만, 일부 Eclipse 버전은 **Tomcat 컨테이너**를 자동관리하기 때문에, 다시 빌드/리프레시할 때 **해당 jar가 재추가**되어 버리는 문제가 생길 수 있다.

> **정리**:  
> - 이 방법이 먹히면 편하지만, **“Tomcat Runtime Libraries”** 자체를 Eclipse가 재동기화해버리는 경우가 있으므로 주의.  
> - **추천**: (가능하면) **3-1** 방법을 사용해 Tomcat 전체를 빼고, 필요한 것만 수동 추가.

---

### 4. Access Restrictions 해제 (Forbidden 에러만 우회)

위 **3번** 단계에서 jar 제거가 귀찮거나, **“Tomcat을 빼기 싫다”**는 경우,  
**Eclipse**가 `org.w3c.dom`을 **Forbidden**으로 막는 규칙만 **해제**하는 방법이 있다.

1. **프로젝트 우클릭** → **Properties**  
2. **Java Build Path** → **Libraries** 탭  
3. **Tomcat 10** 항목을 클릭 → **Edit** or 더블클릭  
4. 대화창 내 **Access rules** 버튼 클릭  
5. `org.w3c.dom` 관련 규칙이 **Forbidden** 또는 **Discouraged**로 되어 있으면, **Accessible** 또는 **Ignore**로 수정  
   - 없는 경우, “Add” 버튼을 눌러  
     - Pattern: `org/w3c/dom/**`  
     - Resolution: **Accessible**  
     로 추가  
6. **OK**, **Apply** 후 **Project → Clean**으로 재빌드

이렇게 하면 Eclipse가 더 이상 **Forbidden 에러**를 발생시키지 않는다.

> **주의**:  
> - jar가 여전히 중복되어 있을 수 있으므로, 런타임 시 우선순위 문제나 다른 충돌 가능성은 남아 있을 수 있다.  
> - 하지만 단순히 “Forbidden 에러”만 없어지면 괜찮은 상황이라면, 이 방법으로도 충분히 해결 가능.

---

### 5. Targeted Runtimes 설정 끄기

**Eclipse**의 Dynamic Web Project 설정에서, **Targeted Runtimes** 기능이 **자동으로** Tomcat 라이브러리를 빌드 경로에 추가한다.  
이를 **끄는** 방법:

1. **프로젝트 우클릭** → **Properties**  
2. **Targeted Runtimes** 메뉴  
3. **Apache Tomcat v10** 체크 해제 (→ “No runtime”)  
4. OK 후 **Clean** 빌드

이렇게 하면 Eclipse가 **자동**으로 Tomcat 라이브러리를 끌어오지 않는다.  
- 이후에는 **서블릿/JSP API** 등 필요한 라이브러리를 **직접** 추가해야 한다. (위 3-1 방법과 유사)  
- 빌드 시에는 **JDK 17 + 필요한 jar**만 사용하고, 실행(배포)은 수동으로 Tomcat 10에 Deploy하면 된다.

---

### 6. Project Facet(서블릿 버전) & Module Path vs. Class Path 확인

1. **Project Facets**  
   - **프로젝트 우클릭** → **Properties** → **Project Facets**  
   - “Dynamic Web Module”이 **4.0** 이하라면 Tomcat 10(Servlet 5.0 이상)과 호환성 문제가 생길 수 있으므로, **5.0** 이상으로 설정.  
   - “Java” 버전이 17인지 확인 (개발 환경과 맞춰주기).  
2. **Module Path vs. Class Path** (Java 17)  
   - Java 9 이상은 **모듈 시스템**이 있으므로, Eclipse에서 “Tomcat 라이브러리”가 **Modulepath**로 들어갈 경우  
     `java.xml` 모듈과 충돌 가능성이 있다.  
   - **프로젝트 우클릭** → **Properties** → **Java Build Path** → **모듈 관련 탭(혹은 Libraries 탭)**  
   - 만약 Tomcat 라이브러리가 Modulepath로 잡혀 있다면, **Classpath**로 이동해서 충돌을 줄이는 방법도 가능.

---

### 7. Clean 빌드 & Eclipse 재시작

Eclipse는 설정을 변경해도 **즉시 반영되지 않는 경우**가 많다.

1. **메뉴**: Project → Clean… → 해당 프로젝트 체크 → Clean  
   - Eclipse가 새롭게 빌드 경로를 인식하고, 클래스 재컴파일을 수행  
2. **문제가 지속**되면 Eclipse를 **재시작**해본다.  
   - Targeted Runtimes나 Facet 변경이 제대로 반영되지 않았을 수 있음.

---

### 8. 최종 요약 - “단순 버전”

1. **Java 17 환경 확인**(Installed JREs, Java Build Path, Java Compiler)  
2. **Tomcat 10 라이브러리에서 `xml-apis.jar`, `xercesImpl.jar` 등 중복 XML 라이브러리 제거**  
3. **Forbidden 에러**가 뜨면 **Access Rules**를 **Accessible** 또는 **Ignore**로 변경  
4. **Clean 빌드**, 그래도 안 되면 **Eclipse 재시작**  
5. 필요하다면 **Project Facets**와 **Targeted Runtimes** 다시 확인

이 5가지만 지키면, 대부분의 `org.w3c.dom` Forbidden 문제는 해결된다.

---

## 부록: Tomcat 9 → 10 마이그레이션 시 참고 사항

- **패키지 변경**: `javax.servlet` → `jakarta.servlet`  
- **서블릿 API 버전**: Tomcat 10은 서블릿 5.0 이상 → Eclipse Facet “Dynamic Web Module 5.0”  
- **XML 파서** 충돌: Tomcat 10이 자체적으로 포함하는 XML 관련 라이브러리가 JDK 17 내장 API와 충돌 가능성  
- **Maven/Gradle**로 관리하는 경우, `pom.xml`(또는 `build.gradle`)에서 `jakarta.servlet:jakarta.servlet-api:5.0+`를 `provided` 스코프로 추가하면, 빌드 시점에는 라이브러리를 인식하되 실제 런타임엔 Tomcat이 제공.

---

# 끝

위 모든 단계를 하나씩 시도해보면, Eclipse에서의 **Forbidden reference: org.w3c.dom** 문제를 성공적으로 해결할 수 있다.  
가장 중요한 포인트는 **“Java 17에는 이미 DOM API가 들어 있으므로, 외부 jar와 중복되지 않게 하라”** + **“Eclipse의 Access Restriction을 해제/완화하라”**는 것이다.

> **Tip**:  
> - 문제가 오래 지속되면, **새 Eclipse Workspace**를 만들어 동일 프로젝트를 Import해보는 것도 방법이다.  
> - 종종 Workspace 캐시 문제로 설정이 꼬여서 계속 Forbidden 에러가 남기도 한다.
