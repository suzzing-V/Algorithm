import java.util.*;

public class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(int n) {
        jump(n, 0, 0);
        return min;
    }
    
    public void jump(int n, int pos, int battery) {
        if(pos == n) {
                min = Math.min(min, battery);
                return;
        }
        if(pos > n) return;
        
        if(pos != 0) jump(n, pos * 2, battery);
        jump(n, pos + 1, battery + 1);

}
}