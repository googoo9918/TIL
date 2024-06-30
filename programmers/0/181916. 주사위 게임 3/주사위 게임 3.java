import java.util.*;
class Solution {
    public int solution(int a, int b, int c, int d) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] iarr = {a, b, c, d};
        Arrays.sort(iarr);
        int q = iarr[0], w = iarr[1], e = iarr[2], r =iarr[3];
        if(q==r){
            return q*1111;
        }
        //3,1
        else if(q==e || w==r){
            if(q==e){
                return (int)Math.pow(10 * q + r,2);
            }
            else{
                return (int)Math.pow(10 * r + q,2);
            }
        }
        //2,2
        else if(q==w && e==r){
            return (q+e) * Math.abs(q-e);
        }
        //2,1,1
        else if(q==w || w==e || e==r ){
            if(q==w) return e*r;
            else if(w==e) return q*r;
            else if(e==r) return q*w;
        }
        else{
            return q;
        }
        return -1;
    }
}