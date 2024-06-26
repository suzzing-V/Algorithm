import java.io.*;
import java.util.*;

public class Main {

    static int[][] chess;
    static int maxWhite = 0;
    static int maxBlack = 0;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        chess = new int[n][n];
        for(int i = 0; i < n; i ++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
               chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfsBlack(0, 0, 0);
        dfsWhite(0, 1, 0);
        System.out.println((maxWhite + maxBlack));
    }

    public static void dfsBlack(int x, int y, int count) {
        if(y >= n) {
            x ++;
            y = (x % 2 == 0)? 0: 1;
        }
        if(x == n) {
            maxBlack = Math.max(count, maxBlack);
            return;
        }

        if(chess[x][y] == 0 || isExists(x, y)) {
            dfsBlack(x, y + 2, count);
        } else {
            chess[x][y] = 2;
            dfsBlack(x, y + 2, count + 1);
            chess[x][y] = 1;
            dfsBlack(x, y + 2, count);
        }
    }
    public static void dfsWhite(int x, int y, int count) {
        if(y >= n) {
            x ++;
            y = (x%2 ==0)? 1:0;
        }
        if(x == n) {
            maxWhite = Math.max(count, maxWhite);
            return;
        }

        if(chess[x][y] == 0 || isExists(x, y)) {
            dfsWhite(x, y + 2, count);
        } else {
            chess[x][y] = 2;
            dfsWhite(x, y + 2, count + 1);
            chess[x][y] = 1;
            dfsWhite(x, y + 2, count);
        }
    }

    public static boolean isExists(int x, int y) {
        int i = x;
        int j = y;
        while(i >= 0 && j >= 0) {
            if(chess[i][j] == 2) {
                return true;
            }
            i--;
            j--;
        }

        i = x;
        j = y;
        while(i >= 0 && j <= n - 1) {
            if(chess[i][j] == 2) {
                return true;
            }
            i--;
            j++;
        }

        return false;
    }
}
