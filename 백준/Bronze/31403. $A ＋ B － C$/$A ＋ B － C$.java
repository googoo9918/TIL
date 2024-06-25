import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int a = Integer.parseInt(br.readLine());
    int b = Integer.parseInt(br.readLine());
    int c = Integer.parseInt(br.readLine());

    System.out.println(a+b-c);
    String s = String.valueOf(a) + String.valueOf(b);
    System.out.println(Integer.parseInt(s) -c);
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
