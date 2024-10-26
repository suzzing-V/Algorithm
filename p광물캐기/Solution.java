import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    static int[] picks;
    static int n;
    static String[] minerals;

    public int solution(int[] picks1, String[] minerals1) {
        picks = picks1;
        n = minerals1.length;
        minerals = minerals1;
        dfs(0, 0);
        return min;
    }

    void dfs(int idx, int tired) {
        if(idx >= n || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            min = Math.min(tired, min);
            return;
        }

        int i = idx;
        int t = tired;
        // 다이아몬드 곡괭이 사용
        if(picks[0] > 0) {
            picks[0] --;
            int cnt = 0;
            while(cnt < 5 && i < n) {
                t += getTired(0, minerals[i++]);
                cnt ++;
            }
            dfs(i, t);
            picks[0] ++;
        }
        if(picks[1] > 0) {
            picks[1] --;
            i = idx;
            t = tired;
            int cnt = 0;
            while(cnt < 5 && i < n) {
                t += getTired(1, minerals[i++]);
                cnt ++;
            }
            dfs(i, t);
            picks[1] ++;
        }
        if(picks[2] > 0) {
            picks[2] --;
            i = idx;
            t = tired;
            int cnt = 0;
            while(cnt < 5 && i < n) {
                t += getTired(2, minerals[i++]);
                cnt ++;
            }
            dfs(i, t);
            picks[2] ++;
        }
    }

    int getTired(int gok, String mineral) {
        if(gok == 0) {
            if(mineral.equals("diamond")) return 1;
            if(mineral.equals("iron")) return 1;
            if(mineral.equals("stone")) return 1;
        }
        if(gok == 1) {
            if(mineral.equals("diamond")) return 5;
            if(mineral.equals("iron")) return 1;
            if(mineral.equals("stone")) return 1;
        }
        if(gok == 2) {
            if(mineral.equals("diamond")) return 25;
            if(mineral.equals("iron")) return 5;
            if(mineral.equals("stone")) return 1;
        }
        return 0;
    }
}
