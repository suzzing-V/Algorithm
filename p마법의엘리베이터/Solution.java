import java.util.*;

class Solution {

    static int storey;
    static int[] stones;

    public int solution(int storey) {
        int minStone = 0;
        int target = 0;
        while(storey > 0) {
            target = storey % 10;
            if(target < 5) {
                minStone += target;
            } else if(target > 5) {
                storey += 10;
                minStone += 10 - target;
            } else {
                int prev = storey / 10 % 10;
                if(prev >= 5) {
                    storey += 10;
                    minStone += 10 - target;
                } else {
                    minStone += target;
                }
            }
            storey /= 10;
        }

        return minStone;
    }
}
