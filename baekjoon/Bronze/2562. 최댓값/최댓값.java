import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int max = Integer.parseInt(br.readLine());
    int sec = 1;
    for(int i=2; i<10; i++){
      int tmp = Integer.parseInt(br.readLine());
      if (max < tmp) {
        max = tmp;
        sec = i;
      }
    }
    System.out.println(max);
    System.out.println(sec);
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
