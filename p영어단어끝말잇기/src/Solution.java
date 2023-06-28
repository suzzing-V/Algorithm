import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int count = 0;
        Set<String> set = new HashSet<>();
        String pre = String.valueOf(words[0].charAt(0));
        
        for(String word : words) {
            count++;
            if(set.contains(word) || !isConnect(word, pre)) {
                answer[1] = count / n + 1;
                if(count % n == 0) {
                    answer[0] = n;
                    answer[1] = count / n;
                } else {
                    answer[0] = count % n;
                    answer[1] = count / n + 1;
                }
                return answer;
            }
            set.add(word);
            pre = word;
        }
        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }
    
    public boolean isConnect(String word, String pre) {
        if(word.charAt(0) != pre.charAt(pre.length() - 1)) return false;
        return true;
    }
}