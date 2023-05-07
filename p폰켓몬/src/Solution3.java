import java.util.*;

class Solution3 {
    public int solution(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        
        for(int i : nums) hs.add(i);
        
        return hs.size() > nums.length / 2 ? nums.length / 2 : hs.size(); 
    }
}