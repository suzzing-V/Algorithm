import java.io.*;
import java.util.*;

// 시간복잡도: 2^20 * 200
// 행을 뒤집는 경우의 수를 구하고, 각 열에서 tail의 수가 더 많다면 뒤집는다.
// 행뒤집는 경우의 수를 구했을 때 board를 한번만 탐색해서 tail의 최솟값을 구할 수 있다.
// 하나씩 탐색하면서 그 원소의 행이 뒤집힌 행이라면 원래 값의 반대값으로 치고, 그 열의 tail이나 head의 개수를 세준다.
// 어짜피 tail이 더 많을 경우 뒤집어서 head가 많게 만들어줄 것이다. 이럴 경우 tail의 개수는 head의 개수와 tail의 개수 중 작은 것이 된다.
public class Main {

    private static int n;
    private static int[][] board;
    private static int min = Integer.MAX_VALUE;
    private static int[][] rowFlipped;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        board = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < n ;j++) {
                board[i][j] =  line.charAt(j) == 'H' ? 1 : 0;
            }
        }

        makeRowCombi(0, 0);

        System.out.println(min);
    }

    private static void makeRowCombi(int idx, int status) {
        if(idx == n) {
//            System.out.println(Integer.toBinaryString(status));
            int tail = flipRow(status);
//            System.out.println("행 뒤집은 후");
//            printArr(flipped);
//            System.out.println("열 뒤집은 후: " + tail);
            min = Math.min(min, tail);
            return;
        }

        makeRowCombi(idx + 1, status | (1 << idx));
        makeRowCombi(idx + 1, status);
    }

    private static void printArr(int[][] arr) {
        for(int i = 0; i < n ;i++) {
            for(int j= 0; j < n ;j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int flipRow(int status) {

        int tailCnt = 0;
        // 열을 기준으로 뒤집는다.
        for(int j = 0; j < n; j++) {
            int colCnt = 0; // 현재 열에서 tail의 개수
            for(int i = 0; i < n; i++) {
                // 만약 현재 요소의 행이 뒤집힌 행이면 반대의 요소를 가지고 있다.
                if ((status & (1 << i)) != 0) {
                    if(board[i][j] == 1) colCnt ++;
                } else {
                    if(board[i][j] == 0) colCnt ++;
                }
            }

            // tail의 개수와 head의 개수 중 작은 값을 더한다. 어짜피 tail이 많을 경우 뒤집어서 head가 더 많도록 할 것이기 때문이다.
            tailCnt += Math.min(colCnt, n - colCnt);
        }

        return tailCnt;
    }

    private static int flipCol() {
        int cnt = 0;
        for(int j = 0; j < n; j++) {
            int t = 0;
            for(int i = 0; i < n; i++) {
                if(board[i][j] == 0) {
                    t ++;
                }
            }

//            System.out.println(t);
            if(t > n / 2) {
                cnt += (n - t);
            } else {
                cnt += t;
            }
        }

        return cnt;
    }
}
