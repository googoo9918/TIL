import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while((line = br.readLine()) != null){
      StringTokenizer st = new StringTokenizer(line);
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      System.out.println(a+b);
    }
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
