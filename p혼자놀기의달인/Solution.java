import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        List<Integer> scores = new ArrayList<>();
        for(int i = 0; i < cards.length; i++) {
            if(cards[i] != 0) {
                int j = cards[i] - 1;
                cards[i] = 0;
                int cnt = 1;
                while(cards[j] != 0) {
                    cnt ++;
                    int tmp = cards[j] - 1;
                    cards[j] = 0;
                    j = tmp;
                }
                scores.add(cnt);
            }
        }

        Collections.sort(scores);
        if(scores.size() != 1) {
            answer = scores.get(scores.size() - 1) * scores.get(scores.size() - 2);
        }
        return answer;
    }
}
