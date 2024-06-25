import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] iarr = new int[8];
    for(int i=0; i<8; i++){
      iarr[i] = Integer.parseInt(st.nextToken());
    }
    boolean checkA = true;
    boolean checkD = true;
    for(int i=1; i<8; i++){
      if(iarr[i]>iarr[i-1]){
        checkD = false;
      }
      else if(iarr[i]<iarr[i-1]){
        checkA = false;
      }
    }

    if(checkA == true && checkD == false){
      System.out.println("ascending");
    }
    else if(checkA == false && checkD == true){
      System.out.println("descending");
    }
    else{
      System.out.println("mixed");
    }
  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
