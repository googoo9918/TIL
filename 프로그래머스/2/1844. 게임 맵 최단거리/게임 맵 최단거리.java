import java.util.*;
class Solution {
    // BFS
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0 , -1, 1};
    public int solution(int[][] maps) {
        
        //행 길이
        int n = maps.length;
        //열 길이
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        //초기값 세팅
        queue.add(new int[]{0,0});
        visited[0][0] = true;
        //첫 칸 포함, 거리 1
        int distance = 1; 
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                
                if(x == n-1 && y == m-1){
                    return distance;
                }
                
                for(int j=0; j<4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if(nx>=0 && ny>=0 && nx<n && ny<m && maps[nx][ny]==1 && visited[nx][ny]== false){
                        queue.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}