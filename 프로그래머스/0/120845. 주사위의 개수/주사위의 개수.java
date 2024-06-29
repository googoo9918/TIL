class Solution {
    public int solution(int[] box, int n) {
        int wide = (box[0]/n) * (box[1]/n);
        int height = box[2]/n;
        return wide*height;
    }
}