import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] iarr = new int[5];
    for(int i=0; i<iarr.length; i++){
      iarr[i] = Integer.parseInt(st.nextToken());
    }

    int sum =0;
    for(int i: iarr){
      sum += i*i;
    }

    System.out.println(sum%10);
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
