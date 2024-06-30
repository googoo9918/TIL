class Solution {
    public int solution(int[][] board) {
        int len = board.length;
        int[][] newBoard = new int[len+2][len+2];
        for(int i=1; i<len+1; i++){
            for(int j=1; j<len+1; j++){
                if(board[i-1][j-1] == 1){
                    for(int k=-1; k<=1; k++){
                        for(int l=-1; l<=1; l++){
                            newBoard[i+k][j+l] = 1;
                        }
                    }
                }
            }
        }
        int answer = 0;
        for(int i=1; i<len+1; i++){
            for(int j=1; j<len+1; j++){
                if(newBoard[i][j]==0) answer++;
            }
        }
        return answer;
    }
}