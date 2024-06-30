
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String str = br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
		// str에서 공백을 기준으로 토큰을 나누고, st에 저장한다.
		// 공백이니까 ""사이에 기입..
		System.out.print(st.countTokens());
		// 
		
	}

}
