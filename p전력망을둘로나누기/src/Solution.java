import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int[] count = new int[n + 1];
        for(int i = 0; i < wires.length; i++) {
            count[wires[i][0]]++;
            count[wires[i][1]]++;
        }
        
        int min = 100;
        for(int i = 0; i < wires.length; i++) {
            int tmp = getConnect(wires[i][0], i, count, wires) + 1;
            //System.out.println(wires[i][0] + ": " + tmp);
            if(Math.abs(tmp - (n - tmp)) < min) min = Math.abs(tmp - (n - tmp));
        }
        return min;
    }
    
    public int getConnect(int n, int index, int[] count, int[][] wires) {
        int answer = count[n] - 1;
        for(int i = 0; i < wires.length; i++) {
            if(i != index) {
                if(wires[i][0] == n) answer += getConnect(wires[i][1], i, count, wires);
                if(wires[i][1] == n) answer += getConnect(wires[i][0], i, count, wires);
            }
        }
        return answer;
    }
}