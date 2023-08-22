import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String strN = String.valueOf(n);
        String[] split = new String[strN.length()];
        split = strN.split("");
        
        int[] arr = new int[strN.length()];
        arr = changeToIntAndSort(split);
        
        answer = Long.parseLong(changeToStr(arr));
        return answer;
    }
    
    public int[] changeToIntAndSort(String[] split) {
        int[] arr = new int[split.length];
        for(int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        
        Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(arr2, Collections.reverseOrder());
        return Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();
    }
    
    public String changeToStr(int[] arr) {
        String str = "";
        for(int i = 0; i < arr.length; i++) {
            str += String.valueOf(arr[i]);
        }
        return str;
    }
}
