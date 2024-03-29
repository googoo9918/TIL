- FastReader 내부 내용에서 필요한 것만 취해볼 것! 
- 물론 전체 내용을 작성할 줄 알아야겠지? 사실 생각해보면 당연한 코드이다.

```java
public class Main {
    static FastReader scan = new FastReader();
    // 입력을 위한 객체 생성
    // 입력시 사용
    // ex)  N = scan.nextInt();
    // M = scan.nextInt();
    // 정답은 sb에 append 를 사용하여 출력
    // 만약 개행까지 출력하고 싶으면 append('\n')을 추가
    // ex for (int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
    // sb.append('\n');

    static StringBuilder sb = new StringBuilder();
    // 출력을 위한 객체 생성
    // 출력시 사용
    // String은 불변 객체이므로, 2개의 String을 더해주면 새로운 String을 생성한다.
    // 즉, String과 String을 더해주는 행위는 메모리 해제와 메모리 할당을 발생시키며
    // 성능적으로 좋지 않다. 이때 StringBuilder는  String과 문자열을 더할 때 새 객체를 생성하는게 아닌
    // 기존에 데이터에 더하는 방식을 사용하므로 속도도 빠르고 상대적으로 부하가 적다. (긴 문자열 사용할수록)

    // 사용 예제는 다음과 같다.
    // StringBuilder sb = new StringBuilder();
    // sb.append("abc");
    // sb.append("def");
    // System.out.println(sb.toString()); -> abcdef 출력

    public static void main(String[] args) {
        input();
    }
    static void input(){
        
    }
    static class FastReader {
        BufferedReader br; // 객체로 만들 필요는 없다.
        // 입력을 위한 BufferedReader
        StringTokenizer st; // 그냥 변수로 충분하다.
        // 쪼개기 위한 StringTokenizer
        public FastReader() { //public 잊지말기.
            br = new BufferedReader(new InputStreamReader(System.in)); //기본 생성자 
            // BufferedReader는 buffer를 읽고 String으로 반환한다.(엔터 경계 인식)
            // System.in은 바이트스트림인 InputStream 타입
            // 1. 바이트 스트림인 InputStream을 통해 바이트 단위 데이터 입력 받음(System.in 사용)
            // 2. 입력 데이터를 char 형태로 처리하기 위해 중개자 역할인 문자스트림 InputStreamReader로 감싸줌
            // 3. BufferedReader을 사용해 버퍼에 문자를 쌓고, 문자열로 읽음!
        }
        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s))); //txt 파일 읽을때 생성자
        }
        String next() { //String 입력 받아야될 때!
            while (st == null || !st.hasMoreElements()) {
                // st==null은 st가 아직 초기화되지 않은 경우(next 첫 호출 시)
                // 즉, st가 초기화되지 않았거나 st는 초기화되었으나, 더 이상 읽어올 토큰이 없는 경우에 br.readLine()을 통해 새로운 입력 줄을 읽어옴
                try {
                    st = new StringTokenizer(br.readLine()); //
                    // StringTokenizer 클래스는 문자열을 우리가 지정한 구분자로 쪼개주는 클래스.
                } catch (IOException e) {
                    e.printStackTrace(); // 에러 메시지의 발생 근원지를 찾아 단계별로 에러 출력
                }
            }
            return st.nextToken(); 
        }
        int nextInt() {
            return Integer.parseInt(next()); //문자열을 10진수로 변환
        }
        long nextLong() {
            return Long.parseLong(next()); //문자열을 Long형으로 변한
        }
        double nextDouble() {
            return Double.parseDouble(next()); // 문자열을 Double형으로 변환
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine(); //개행문자 포함해 한 줄을 다 읽어오는 방식.
            } catch (IOException e) {
                e.printStackTrace();// 에러 메시지의 발생 근원지를 찾아 단계별로 에러 출력
            }
            return str;
        }
    }
}
```
