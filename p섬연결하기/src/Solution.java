import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean[] count = new boolean[n];
        for(int i = 0; i < count.length; i++) count[i] = false;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < costs.length; i++) {
            list.add(i);
        }
        
        int c = 0;
        int index = 0;
        while(c < count.length) {
            if(count[c] == false) {
                int min = ((n - 1) * n) / 2;
                int minIndex = 0;
                for(int i = 0; i < list.size(); i++) {
                    if(costs[list.get(i)][0] == c || costs[list.get(i)][1] == c) {
                        if(costs[list.get(i)][2] < min) {
                            min = costs[list.get(i)][2];
                            minIndex = i;
                        }
                    }
                }
                //System.out.println("c: " + c);
                //System.out.println("min: " + min);
                count[costs[list.get(minIndex)][0]] = true;
                count[costs[list.get(minIndex)][1]] = true;
                list.remove(minIndex);
                answer += min;
            }
            c++;
        }
        return answer;
    }
}