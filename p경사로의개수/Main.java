import java.util.*;
import java.io.*;

// 시간복잡도: 100 * 2^14 + 2^12 + log10^9 * 2^18
// dp로 1번 반복했을 때 경로 수 구하고, 행렬곱으로 k번 반복했을 때 특정 지점에서 다른 지점으로 갈 때의 경로 수 구한다.
// 원래는 시작점~경유지 i번 반복 * 경유지~도착점 (k - i)반복 을 다 더해줘야 하지만 시간초과난다. 이럴 때 행렬곱으로 처리 가능
class Solution {

    private int[][] grid;
    private int[] d;
    private int k;
    private int[][][][][] dp;
    private long[][] repeat_one;
    private int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int MOD = 1000000007;
    private int n;
    private int m;

    public int solution(int[][] grid1, int[] d1, int k1) {
        grid = grid1;
        d = d1;
        k = k1;
        n = grid.length;
        m = grid[0].length;
        dp = new int[n][m][n][m][d.length];
        repeat_one = new long[n * m][n * m];

        // 한 번 반복했을 때 각 지점에서 특정 지점까지의 경사로 개수 구하기
        for(int di = 0; di < d.length; di++) {
            // di번째 루트까지 갔을 때 각 지점에서 특정 지점까지의 경사로 개수 구하기
            for(int i =0; i < n ;i++) {
                for(int j =0; j< m; j++) {
                    save_curr_to_next(i, j, di);
                }
            }
        }


        // 각 칸을 하나의 노드로 보는 행렬 만들기. 각 칸에서 다른 칸으로 갈 때 경로 저장하기
        for(int i1 = 0; i1 < n; i1++) {
            for(int j1 = 0; j1 < m ;j1 ++) {
                for(int i2 = 0; i2 < n; i2 ++) {
                    for(int j2 = 0; j2 < m; j2 ++) {
                        repeat_one[i1 * m + j1][i2 * m + j2] = dp[i1][j1][i2][j2][d.length - 1];
                    }
                }
            }
        }


        // 분할 정복으로 a^k구하기
        long[][] repeat_k = get_pow_of_matrix(k);

        // print_repeat_one(repeat_k);
        // 정담 구하기
        int result = 0;
        for(int i = 0; i < n * m; i ++ ){
            for(int j = 0; j < n * m; j++) {
                result += repeat_k[i][j] % MOD;
                result %= MOD;
            }
        }

        return result;
    }

    private void print_repeat_one(long[][] arr) {
        for(int i = 0; i < n * m; i++) {
            for(int j = 0; j < n * m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private long[][] get_pow_of_matrix(int p) {
        if(p == 1) {
            return repeat_one;
        } else if(p % 2 == 0) {
            long[][] pow = get_pow_of_matrix(p / 2);
            long[][] new_matrix = new long[n * m][n * m];

            for(int i = 0; i < n * m; i++) {
                for(int j = 0; j < n * m ;j ++) {
                    for(int s = 0; s < n * m; s ++) {
                        new_matrix[i][j] += (pow[i][s] * pow[s][j] % MOD) % MOD;
                        new_matrix[i][j] %= MOD;
                    }
                }
            }

            return new_matrix;
        }

        long[][] pow = get_pow_of_matrix(p - 1);
        long[][] new_matrix = new long[n * m][n * m];

        for(int i = 0; i < n * m; i++) {
            for(int j = 0; j < n * m ;j ++) {
                for(int s = 0; s < n * m; s ++) {
                    new_matrix[i][j] += (pow[i][s] * repeat_one[s][j] % MOD) % MOD;
                    new_matrix[i][j] %= MOD;
                }
            }
        }
        return new_matrix;
    }

    private void save_curr_to_next(int x, int y, int di) {
        // 현재 칸에서 다음 칸으로 갈 수 있다면, 다음 칸에 현재 경사로만큼 가는 경로 수는 현재 칸에 이전 경사로만큼 왔을 떄 경로 수이다.
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            // 현재 순서의 경사와 다르면 갈 수 없다.
            if(grid[nx][ny] - grid[x][y] != d[di]) continue;

            // 첫번째 경사면 현재 칸으로 오는 경로의 수가 0이다. 이번에 가면서 경로가 하나 생기므로, 1로 만들어준다.
            if(di == 0) {
                dp[x][y][nx][ny][di] = 1;
            } else {
                // 첫번째 경사가 아니라면 기존의 로직을 따라준다.
                // 모든 칸에서 다음 칸으로 가는 경로의 수를 갱신해준다.
                for(int px = 0; px < n; px ++) {
                    for(int py = 0; py < m; py ++) {
                        dp[px][py][nx][ny][di] += dp[px][py][x][y][di - 1] % MOD;
                        dp[px][py][nx][ny][di] %= MOD;
                    }
                }
            }

        }
    }
}
