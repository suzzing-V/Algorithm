import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n) {
        hanoiTop(n, 1, 3);
        int[][] answer = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++) {
            answer[i][0] = list.get(i)[0];
            answer[i][1] = list.get(i)[1];
        }
        return answer;
    }
    
    public void hanoiTop(int n, int depart, int arrive) {
        if(n == 1) {
            list.add(new int[] {depart, arrive});
            return;
        }
        
        int mid = 0;
        for(int i = 1; i <= 3; i++) {
            if(i != depart && i != arrive) mid = i;
        }
        hanoiTop(n - 1, depart, mid);
        hanoiTop(1, depart, arrive);
        hanoiTop(n - 1, mid, arrive);
    }
}