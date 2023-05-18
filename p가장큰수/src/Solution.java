import java.util.*;
import java.util.Collections;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        List<String> arr = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++) {
            arr.add(Integer.toString(numbers[i]));
        }
        
        Collections.sort(arr, new Comparator<String>() {
            @Override 
            public int compare(String o1, String o2) {
                int tmp1 = Integer.parseInt(o1 + o2);
                int tmp2 = Integer.parseInt(o2 + o1);
                return tmp2 - tmp1;
            }
        });
        
        for(int i = 0; i < arr.size(); i++) {
            answer += arr.get(i);
        }
        if(answer.charAt(0) == '0') answer = "0";
        return answer;
    }
}