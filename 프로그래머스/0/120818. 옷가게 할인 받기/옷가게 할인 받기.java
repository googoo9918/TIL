class Solution {
    public int solution(double price) {
        if(price< 100000){
            return (int)price;
        }
        else if(price>=100000 && price<300000){
            return (int)(price*0.95);
        }
        else if(price>=300000 && price<500000){
            return (int)(price*0.9);
        }
        else{
            return (int)(price*0.8);
        }
    }
}