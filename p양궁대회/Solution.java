import java.util.*;

class Solution {

    private int max = 0;
    private int[] answer = new int[11];
    private int[] info;

    public int[] solution(int n, int[] info1) {
        info = info1;
        dfs(0, n, 0, new int[11]);
        if(max == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }

    private void dfs(int idx, int arrow, int score, int[] rion) {
        // System.out.println(idx + " " + arrow + " " + score);
        // for(int i = 0; i < rion.length; i++) {
        //     System.out.print(rion[i] + " ");
        // }
        // System.out.println();
        if(arrow == 0) {
            int a = 0;
            int r = 0;
            for(int i = 0; i < 11; i ++) {
                if(rion[i] == 0 && info[i] == 0) continue;
                if(rion[i] > info[i]) {
                    r += 10 - i;
                } else {
                    a += 10 - i;
                }
            }
            if(r - a > max) {
                max = r - a;
                answer = Arrays.copyOf(rion, 11);
            } else if(r - a == max) {
                for(int i = 10; i >= 0; i --) {
                    if(answer[i] > rion[i]) {
                        break;
                    } else if(answer[i] < rion[i]) {
                        answer = Arrays.copyOf(rion, 11);
                        break;
                    }
                }
            }
            return;
        }

        for(int i = idx; i < info.length; i++) {
            if(arrow - (info[i] + 1) < 0) {
                rion[i] = arrow;
            } else {
                rion[i] = info[i] + 1;
            }
            dfs(i + 1, arrow - rion[i], score + 10 - i, rion);
            rion[i] = 0;
        }
    }
}
