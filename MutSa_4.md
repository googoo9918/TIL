```java
package proset;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class pro4 {
	public static void main(String [] args){
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		Scanner in = new Scanner(System.in);
		int num =0;
		int n =1;
		System.out.println("0을 입력하시면 입력이 종료 됩니다.");
		while(true) {
			try{
			System.out.print("숫자 "+ n +" : ");
			num = in.nextInt();
			list.add(num);
			if(num ==0) {
			break;
		}
			}catch(InputMismatchException e) {
				System.out.println("[입력오류] : 숫자로 입력해주세요.");
				in.nextLine();
				continue;
			}
			n++;
		}
	System.out.print("결과 : ");
	for(int i=0; i<n-1;i++) {
		System.out.print(list.get(i));
		if(i!=n-2)
		System.out.print(", ");
	}
	in.close();
	}
}

```