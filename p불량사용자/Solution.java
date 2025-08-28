import java.util.*;

// 시간 복잡도 : 8! * 8
class Solution {

    private String[] user_id;
    private String[] banned_id;
    private boolean[] status;
    private int ul;
    private int cnt = 0;

    public int solution(String[] user_id1, String[] banned_id1) {
        user_id = user_id1;
        banned_id = banned_id1;
        ul = user_id1.length;
        status = new boolean[(int)Math.pow(2, ul)];

        dfs(0, 0);
        return cnt;
    }

    private void dfs(int bidx, int visited) {
        // 모든 bann_id에 user 할당했으면 그 경우 세기
        if(bidx >= banned_id.length) {
            // 이 조합으로 만든 적이 있으면 안센다
            if(!status[visited]) {
                status[visited] = true;
                cnt ++;
            }
            return;
        }

        for(int i = 0; i < ul; i++) {
            if((visited & (1 << i)) != 0) continue;

            String id = user_id[i];
            if(isMatched(banned_id[bidx], id)) {
                dfs(bidx + 1, visited | (1 << i));
            }
        }
    }

    private boolean isMatched(String masked, String origin) {
        if(masked.length() != origin.length()) return false;

        for(int i = 0; i < masked.length(); i++) {
            if(masked.charAt(i) == '*') continue;
            if(masked.charAt(i) != origin.charAt(i)) return false;
        }

        return true;
    }
}