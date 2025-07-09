import java.util.*;

// 첫줄 돌리는 경우에 따라 나머지 판 완성하고 마지막 줄이 다 0이 아니라면 만들지 못한 것
// 첫줄만 확인하므로 시간 복잡도 4 ^ 8 * n ^ 2
class Solution {

    private int n;
    private int min = Integer.MAX_VALUE;
    private int[][] clockHands;

    public int solution(int[][] clockHands1) {
        clockHands = clockHands1;
        n = clockHands.length;

        // 모든 요소가 0인 첫 줄 만들기
        int[] rotate = new int[n];
        makeFirstLine(rotate, 0);
        return min;
    }

    private void makeFirstLine(int[] rotate, int y) {
        if(y == n) {
            // 첫줄 완성 시 그에 따라 board 만들고 마지막 줄이 다 0이면 최소값 갱신
            min = Math.min(makeBoard(rotate), min);
            return;
        }

        for(int i = 0; i < 4; i ++) {
            rotate[y] = i;
            makeFirstLine(rotate, y + 1);
        }
    }

    private int makeBoard(int[] first) {
        int cnt = 0;
        // 돌린 결과
        int[][] rotate = new int[n][n];

        // 복사
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotate[i][j] = clockHands[i][j];
            }
        }

        // 첫줄 돌리기
        for(int j= 0; j< n; j++) {
            int rc = first[j];
            rotate[0][j] = (rotate[0][j] + rc) % 4;
            rotate[1][j] = (rotate[1][j] + rc) % 4;
            if(j - 1 >= 0) rotate[0][j - 1] = (rotate[0][j - 1] + rc) % 4;
            if(j + 1 < n) rotate[0][j + 1] = (rotate[0][j + 1] + rc) % 4;
            // if(first[0] == 0 && first[1] == 0 && first[2] == 0 && first[3] == 0) System.out.print(rotate[0][j]);
            cnt += rc;
        }

        // i - 1행이 다 0이 될 수 있도록 i행을 돌린다
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 위에 맞출려면 돌려야하는 횟수
                int rc = 4 - rotate[i - 1][j];
                if(rc == 4) rc = 0;
                cnt += rc;
                // if(first[0] == 0 && first[1] == 0 && first[2] == 0 && first[3] == 0) System.out.print(rc);
                rotate[i][j] = (rotate[i][j] + rc) % 4;
                rotate[i - 1][j] = (rotate[i - 1][j] + rc) % 4;
                if(i + 1 < n) rotate[i + 1][j] = (rotate[i + 1][j] + rc) % 4;
                if(j - 1 >= 0) rotate[i][j - 1] = (rotate[i][j - 1] + rc) % 4;
                if(j + 1 < n) rotate[i][j + 1] = (rotate[i][j + 1] + rc) % 4;
            }
            // if(first[0] == 0 && first[1] == 0 && first[2] == 0 && first[3] == 0) {
            //     System.out.println();
            //     for(int k = 0; k < n; k++) {
            //         System.out.print(rotate[i][k]);
            //     }
            //     System.out.println();
            // }
        }
        // 마지막 줄이 다 0인지 확인
        for(int i = 0; i < n; i++) {
            if(rotate[n - 1][i] != 0) return Integer.MAX_VALUE;
        }

        return cnt;
    }
}