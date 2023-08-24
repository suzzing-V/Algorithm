import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> score = new HashMap<>();
        putScore(score, name, yearning);
        
        for(int i = 0; i < photo.length; i++) {
            int sum = 0;
            for(int j = 0; j < photo[i].length; j++) {
                if(score.containsKey(photo[i][j])) sum += score.get(photo[i][j]);
            }
            answer[i] = sum;
        }
        return answer;
    }
    
    public void putScore(Map<String, Integer> score, String[] name, int[] yearning) {
        for(int i = 0; i < name.length; i ++) {
            score.put(name[i], yearning[i]);
        }
    }
}