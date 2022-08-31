![image](https://user-images.githubusercontent.com/102513932/187617332-7fdc7729-6b91-4b64-87db-f99b0657f661.png)

- 새 배열을 만들어야 할 때 숙지해야할 여러 방법.
```java 
// 기존 내 풀이 (ArrayList 사용)
import java.util.Arrays;
import java.util.ArrayList;
public class Solution { 
	public int[] getEvenNumbers(int[] arr) {
     ArrayList<Integer> arrayList = new ArrayList<Integer>();
            int cnt =0;
            for(int i=0; i<arr.length; i++){
                if(arr[i]%2 ==0){
                    arrayList.add(arr[i]);
                    cnt++;
                }
            }
            int[] ans = new int[cnt];
            for(int i=0; i<cnt; i++){
                ans[i] = arrayList.get(i);
            }
            return ans;
	} 
}
```

```java
//풀이 1, Arrays.copyOf사용 -> 숙지해 볼 것.
public int[] getEvenNumbers(int[] arr) {
    int[] concatArray = new int[]{};

    if(arr.length == 0) {
      return new int[]{};
    }

    for(int i = 0; i < arr.length; i++) {
      if(arr[i] % 2 == 0) {
        concatArray = Arrays.copyOf(concatArray, concatArray.length + 1);
        concatArray[concatArray.length - 1] = arr[i];
      }
    }

    return concatArray;
  }
```

```java
// 풀이 2 cnt 생성과 초기화를 통해..
public int[] getEvenNumbers(int[] arr) {
		int count = 0;

		if(arr.length == 0) {
			return new int[]{};
		}

		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 == 0) {
				count++;
			}
		}

    int[] result = new int[count];
		count = 0;

		for(int j = 0; j < arr.length; j++) {
			if(arr[j] % 2 == 0) {
				result[count] = arr[j];
				count++;
			}
		}

		return result;
	} 
```