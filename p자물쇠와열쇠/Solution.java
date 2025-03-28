
// 문제 조건 잘 확인하기
class Solution {

    private int[][] key_extend;
    private int[][] lock;
    private int n;
    private int m;

    public boolean solution(int[][] key, int[][] lock1) {
        lock = lock1;
        n = lock.length;
        m = key.length;
        key_extend = new int[n * 2 + m - 2][n * 2 + m - 2];
        // key 채우기
        for(int i = n - 1; i < n - 1 + m; i++) {
            for(int j = n - 1; j < n - 1 + m; j++) {
                key_extend[i][j] = key[i - n + 1][j - n + 1];
            }
        }

        // 가능한 시작점 돌면서 그 판 회전시키면서 가능한지 확인
        for(int i = 0; i < n - 1 + m; i++) {
            for(int j = 0; j < n - 1 + m; j++) {
                int[][] tmp = new int[n][n];
                for(int a = 0; a < n; a ++) {
                    for(int b = 0; b < n; b++) {
                        tmp[a][b] = key_extend[i + a][j + b];
                    }
                }
                if(check_0(tmp)) return true;
                if(check_90(tmp)) return true;
                if(check_180(tmp)) return true;
                if(check_270(tmp)) return true;
            }
        }
        return false;
    }

    private boolean check_0(int[][] tmp) {
        int a = 0;
        for(int i = 0; i < n; i++) {
            int b = 0;
            for(int j = 0; j < n; j++) {
                if(lock[a][b ++] == tmp[i][j]) return false;
            }
            a ++;
        }
        System.out.println();
        return true;
    }

    private boolean check_90(int[][] tmp) {
        int a = 0;
        for(int i = n - 1; i >= 0; i--) {
            int b = 0;
            for(int j = 0; j < n; j++) {
                if(lock[a][b ++] == tmp[j][i]) return false;
            }
            a ++;
        }
        return true;
    }

    private boolean check_180(int[][] tmp) {
        int a = 0;
        for(int i = n - 1; i >= 0; i--) {
            int b = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(lock[a][b ++] == tmp[i][j]) return false;
            }
            a ++;
        }
        return true;
    }

    private boolean check_270(int[][] tmp) {
        int a = 0;
        for(int i = 0; i < n; i++) {
            int b = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(lock[a][b] == tmp[j][i]) return false;
                b++;
            }
            a++;
        }
        return true;
    }
}
