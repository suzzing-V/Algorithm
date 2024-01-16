import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        board = new int[n][3];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        dp(n);
        bw.write(String.valueOf(max) + " " + String.valueOf(min));
        bw.close();
    }

    public static void dp(int n) {
        int[][] maxBoard = new int[n][3];
        int[][] minBoard = new int[n][3];

        maxBoard[0][0] = minBoard[0][0]= board[0][0];
        maxBoard[0][1] = minBoard[0][1]= board[0][1];
        maxBoard[0][2] = minBoard[0][2]= board[0][2];

        for(int i = 1; i < n; i++) {
            maxBoard[i][0] = Math.max(maxBoard[i - 1][0], maxBoard[i - 1][1]) + board[i][0];
            minBoard[i][0] = Math.min(maxBoard[i - 1][0], maxBoard[i - 1][1]) + board[i][0];

            maxBoard[i][1] = Math.max(Math.max(maxBoard[i - 1][0], maxBoard[i - 1][1]), maxBoard[i - 1][2]) + board[i][1];
            minBoard[i][1] = Math.min(Math.min(minBoard[i - 1][0], minBoard[i - 1][1]), minBoard[i - 1][2]) + board[i][1];

            maxBoard[i][2] = Math.max(maxBoard[i - 1][1], maxBoard[i - 1][2]) + board[i][2];
            minBoard[i][2] = Math.min(minBoard[i - 1][1], minBoard[i - 1][2]) + board[i][2];
        }

        max = Math.max(Math.max(maxBoard[n - 1][0], maxBoard[n - 1][2]), maxBoard[n - 1][1]);
        min = Math.min(Math.min(minBoard[n - 1][0], minBoard[n - 1][2]), minBoard[n - 1][1]);
    }
}