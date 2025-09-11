import java.util.*;

// 시간복잡도: 40 * 40 * 4 * (20 * 20 + 20 * 20)
// 문제 잘 읽기. 자물쇠의 모든 홈 채워야한다.
class Solution {

    private int[][] lock;
    private int n;
    private int m;
    private int[][][] rotated;

    public boolean solution(int[][] key, int[][] lock1) {
        lock = lock1;
        n = lock.length;
        m = key.length;
        rotated = new int[4][m][m];

        rotated[0] = key;
        for(int i = 1; i < 4; i++) {
            rotated[i] = rotate(rotated[i - 1]);

            // printKey(rotated[i]);
        }

        for(int i = - (m - 1); i < n; i ++) {
            for(int j = - (m - 1); j < n; j ++) {
                // System.out.println(i + " " + j);
                // 키 돌리기
                for(int r = 0; r < 4; r ++) {
                    // i, j를 시작으로 하는 키 들어맞는지 확인하기
                    boolean flag = true;
                    for(int x = i; x < i + m; x++) {
                        for(int y = j; y < j + m; y ++) {
                            if(x < 0 || x >= n || y < 0 || y >= n) continue;
                            // 같으면 false -> 못 연다.
                            if(lock[x][y] == rotated[r][x - i][y - j]) {
                                flag = false;
                                break;
                            }
                        }
                        if(!flag) break;
                    }
                    // 자물쇠 범위에서 걸리는 거 없으면 나머지 범위 채울 필요 없는지 확인
                    if(flag) {
                        for(int x = 0; x < n; x ++) {
                            for(int y = 0; y < n ;y ++) {
                                // 열쇠 범위면 넘어가기
                                if((x >= i && x < i + m) && (y >= j && y < j + m)) continue;
                                if(lock[x][y] == 0) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if(flag) return true;
                    }
                }
            }
        }
        return false;
    }

    private void printKey(int[][] key) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] rotate(int[][] before) {
        int[][] after = new int[m][m];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                after[j][m - 1 - i] = before[i][j];
            }
        }

        return after;
    }
}