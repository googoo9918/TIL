import java.util.*;

class Pair implements Comparable<Pair> {
    int value;
    int diff;

    public Pair(int value, int diff) {
        this.value = value;
        this.diff = diff;
    }

    // 거리가 짧은 순으로, 거리가 같으면 숫자가 큰 순으로 정렬
    @Override
    public int compareTo(Pair other) {
        if (this.diff != other.diff) {
            return this.diff - other.diff;
        }
        return other.value - this.value; // 거리가 같을 때는 값이 큰 숫자를 앞으로
    }
}

class Solution {
    public List<Integer> solution(int[] numlist, int n) {
        List<Pair> pairs = new ArrayList<>();
        for (int num : numlist) {
            pairs.add(new Pair(num, Math.abs(num - n)));
        }

        Collections.sort(pairs); // 정의된 compareTo에 따라 정렬

        List<Integer> result = new ArrayList<>();
        for (Pair p : pairs) {
            result.add(p.value);
        }
        return result;
    }
}