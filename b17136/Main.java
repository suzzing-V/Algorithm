import java.util.*;
import java.io.*;

// 덮어야하는 공간마다 붙일 수 있는 색종이 다 붙여가면서 백트래킹. 해당 크기 색종이 다 썼을 경우, 해당 크기 색종이 붙일 수 없는 경우 가지치기

public class Main {

    private static int[][] board = new int[10][10];
    private static int min = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10 ;i ++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, new int[6]);
        if(min == 30) System.out.println(-1);
        else System.out.println(min);
    }

    private static void dfs(int x, int y, int[] papers) {
        // 끝까지 갈 수 있으면 다 덮을 수 있는 것. 사용한 색종이 개수 갱신
        if(x == 10) {
            int cnt = 0;
            for(int i = 1; i <= 5; i++) cnt += papers[i];
            min = Math.min(min, cnt);
            return;
        }

        // 덮을 공간 아니면 그냥 넘어가기
        if(board[x][y] == 0) {
            if(y + 1 == 10) {
                dfs(x + 1, 0, papers);
            } else {
                dfs(x, y + 1, papers);
            }
            return;
        }

        for(int i = 1; i <= 5; i++) {
            // 해당 크기 색종이 다 썼으면 못 붙인다.
            if(papers[i] == 5) continue;
            // 이 지점부터 시작하는 공간에 해당 크기 색종이 붙일 수 있으면 붙이고 다음으로
            if(canUse(x, y, i)) {
                stitch(x, y, i);
                papers[i] ++;
                if(y + 1 == 10) {
                    dfs(x + 1, 0, papers);
                } else {
                    dfs(x, y + 1, papers);
                }
                detach(x, y, i);
                papers[i] --;
            }
        }
    }

    private static boolean canUse(int x, int y, int paper) {
        for(int i = x; i < x + paper; i++) {
            for(int j = y; j < y + paper; j++) {
                // 해당 색종이 크기를 붙이면 범위를 넘어가거나 1이 아닌 경우는 붙일 수 없다.
                if(i >= 10 || j >= 10 || board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static void stitch(int x, int y, int paper) {
        for(int i = x; i < x + paper; i++) {
            for(int j = y; j < y + paper; j++) {
                board[i][j] = 0;
            }
        }
    }

    private static void detach(int x, int y, int paper) {
        for(int i = x; i < x + paper; i++) {
            for(int j = y; j < y + paper; j++) {
                board[i][j] = 1;
            }
        }
    }
}
