import java.util.*;

public class Solution {
    public int solution(int n) {
        int[] battery = new int[n + 1];
        battery[1] = 1;
        for(int i = 2; i <= n; i++) {
            if(i % 2 == 0) battery[i] = Math.min(battery[i / 2], battery[i - 1] + 1);
            else battery[i] = battery[i - 1] + 1;
        }
        return battery[n];
    }
}