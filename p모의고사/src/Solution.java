import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] supo1 = {1,2,3,4,5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] count = new int[4];

        countAnswers(supo1, answers, count, 1);
        countAnswers(supo2, answers, count, 2);
        countAnswers(supo3, answers, count, 3);
        
        int max = 0;
        for(int i = 1; i < 4; i++) {
            if(count[i] > max) max = count[i];
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < 4; i++) {
            if(count[i] == max) list.add(i);;
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public void countAnswers(int[] supo, int[] answers, int[] count, int number) {
        int j = 0;
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == supo[j++]) {
                count[number]++;
            }
            if(j >= supo.length) j = 0;
        }
    }
}