import java.util.*;

public class Solution {
    public void main(String[] args) {
        int[] nums = {3, 3, 3, 2, 2, 2};
        System.out.println(solution(nums));
    }

    public int solution(int[] nums) {
        int answer = 0;
        int[] pokemon = new int[200001];
        Arrays.fill(pokemon, 0);

        for(int i = 0; i < nums.length; i++) {
            pokemon[nums[i]] ++;
        }
        Arrays.sort(nums);

        for(int i = 1; i <= nums[nums.length - 1]; i++) {
            if(pokemon[i] != 0 && answer < nums.length / 2) {
                answer++;
            }
        }
        return answer;
    }
}
