import java.util.*;

class Solution {

    private PriorityQueue[] ban_str;

    public String solution(long n, String[] bans) {
        ban_str = new PriorityQueue[12];
        for(int i = 0; i <= 11; i++) {
            ban_str[i] = new PriorityQueue<String>();
        }

        // 삭제된 주무들을 ban_str에 글자수별로 담기
        for(int i = 0; i < bans.length; i++) {
            ban_str[bans[i].length()].add(bans[i]);
        }

        // 찾고자 하는 주문의 글자수 구하기
        int i = 1; // 글자수
        String answer = "";
        while(true) {
            long tmp = n - ((long)Math.pow(26, i) - ban_str[i].size()); // 지나온 번호들에 다음 글자수의 주문수 더하기
            if(tmp <= 0) { // 남은 순서에서 현재 글자수를 가지고 있는 주문수를 뺐을 때 0이거나 음수면 답은 i개의 글자수를 가지고 있다
                answer = findStrN(n, ban_str[i], 0, i);
                break;
            }

            n = tmp;
            i ++;
        }

        return answer;
    }

    // 특정 글자수의 주문들 중에 답 찾기
    private String findStrN(long rest, PriorityQueue<String> bans, int idx, int cNum) {
        if(idx == cNum) return ""; // 찾고자하는 글자 순서가 글자 수를 넘어섰을 경우 끝내야함
        char curr_c = 'a'; // 타깃 문자

        while(true) {
            PriorityQueue<String> start_curr_c = new PriorityQueue<>();
            // idx번째 문자가 현재 타깃 문자인 금지주문들 구하기
            while(!bans.isEmpty()) {
                String curr = bans.peek();
                if(curr.charAt(idx) == curr_c) {
                    bans.remove();
                    start_curr_c.add(curr);
                } else {
                    break;
                }
            }

            long tmp = rest - ((long)Math.pow(26, cNum - (idx + 1)) - start_curr_c.size());
            if(tmp <= 0) { // 남은 순서 수에서 idx번째 문자가 현재 타깃인 주문수를 뺐을 때 0보다 작거나 같으면 idx번째 문자가 현재 타깃이라는 의미 -> 그 뒤 문자 탐색
                return String.valueOf(curr_c) + findStrN(rest, start_curr_c, idx + 1, cNum);
            }

            // 0보다 클 경우 다음 문자 탐색
            rest = tmp;
            curr_c ++;
        }
    }
}
