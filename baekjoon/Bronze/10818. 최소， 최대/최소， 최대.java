import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[] iarr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++){
      iarr[i] = Integer.parseInt(st.nextToken());
    }
    int min =Integer.MAX_VALUE, max= Integer.MIN_VALUE;
    for(int i=0; i<iarr.length; i++){
      min = Math.min(iarr[i], min);
      max = Math.max(iarr[i], max);
    }

    System.out.println(min + " " + max);
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
