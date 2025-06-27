import java.util.*;

// 같은 열과 행 조합으로 뒤집는다면 순서 상관없이 같은 결과가 나온다. 하나의 동전을 생각해보면 뒤집는 횟수가 똑같기 때문이다.
// 두번 뒤집으면 원상복귀다. 따라서 같은 열이나 행을 두번 뒤집으면 안 뒤집은 거랑 같으므로 서로 다른 결과를 구하기 위해서는 같은 행과 열을 두번 뒤집지 않는다.
// 뒤집을 행과 열을 선택하고 그 결과가 target과 같은지 비교한다. 뒤집을 행을 선택하는 경우의 수는 최대 2^10, 열을 선택하는 경우의 수도 최대 2^10이다.
// 뒤집을 행과 열을 선택할 때 비트마스킹을 사용할 수 있다. 예를 들어 2행과 4행을 뒤집었다면 01010로 표현 가능.
// 뒤집을 행과 열 조합을 각각 선택하고, 그에 따라 뒤집는다. 만약에 특정 칸의 행도 뒤집고, 열도 뒤집거나 둘다 뒤집지 않는 경우에는 결국 그냥 초기상태 그대로이다. 따라서 뒤집지 않는다. 행과 열 중 하나를 뒤집는다면 뒤집는다.
// 이 말인 즉슨 해당 칸의 행의 비트와 해당 칸의 열의 비트가 1,1 0,0로 같으면 결과는 초기상태이다. 다르면 뒤집은 상태이다.
// 이 작업을 2^10 * 2^10마다 수행해야하므로 최종 시간복잡도는 2^10 * 2^10 * 10 * 10
class Solution {
    private int n;
    private int m;

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        int min = Integer.MAX_VALUE;

        // 행 뒤집는 조합 구하기 00000~11111
        // 2^n은 100000이므로 2^n - 1까지만
        for(int i = 0; i < (int)Math.pow(2, n); i++) {
            String combi = Integer.toBinaryString(i);
            combi = "0".repeat(Math.max(0, n - combi.length())) + combi;

            // 열 뒤집는 조합 구하기 00000~11111
            for(int j = 0; j < (int)Math.pow(2, m); j++) {
                String combi2 = Integer.toBinaryString(j);
                combi2 = "0".repeat(Math.max(0, m - combi2.length())) + combi2;

                int[][] tmp = new int[n][m];
                for(int a = 0; a < n; a++) {
                    for(int b = 0; b < m; b++) {
                        // 만약 해당 칸의 행과 열에서 모두 뒤집거나, 모두 뒤집지 않는다면(combi의 a번째 비트와 combi2의 b번째 비트가 같다면) 초기상태이다.
                        // 둘 중 하나를 뒤집는다면(두 비트가 다르다면) 최종적으로 뒤집는다.
                        // ^(XOR): 두 비트 다르면 1, 같으면 0으로 바꿈
                        if(((combi2.charAt(b) - '0')^(combi.charAt(a) - '0')) == 0) {
                            tmp[a][b] = beginning[a][b];
                        }

                        // ~beginning이 안되는 이유: ~1은 0이라고 생각할 수 있지만 자바 int는 32bit이므로 00000000 00000000 00000000 00000001 을 xor한 값이다.
                        // 11111111 11111111 11111111 11111111 11111110이 되므로 0이 나오지 않는다.
                        else tmp[a][b] = (beginning[a][b] == 1) ? 0 : 1;
                    }
                }

                // 바로 리턴하면 안되는 이유는 탐색 순서가 0, 1, 10, 100, 1000 이렇게 1이 적은 순으로 탐색하는 게 아닌 0, 1, 10, 11, 100, 101 이렇게 탐색하므로 먼저 조건을 만족하는 조합을 만났다고 해도 뒤에 1이 더 적은 조합이 있을 수 있기 때문이다.
                if(isSame(tmp, target)) min = Math.min(min, Integer.bitCount(i) + Integer.bitCount(j));
                //     for(int x = 0; x < n; x++) {
                //     for(int y = 0; y < m; y ++) {
                //         System.out.print(tmp[x][y] + " ");
                //     }
                //     System.out.println();
                // }
                // System.out.println();
            }
        }


        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }

    private boolean isSame(int[][] board, int[][] target) {
        for(int i =0 ; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
}