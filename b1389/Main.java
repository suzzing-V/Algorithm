import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] floyd;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        floyd = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) floyd[i][j] = 0;
                else floyd[i][j] = 1000;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            floyd[f1][f2] = 1;
            floyd[f2][f1] = 1;
        }

        for(int x = 1; x <= n; x ++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][x] + floyd[x][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int minF = 1;
        for(int j = 1; j <= n; j++) {
            int sum = 0;
            for(int i = 1; i <= n; i++) {
                sum += floyd[i][j];
            }
            if(min > sum) {
                minF = j;
                min = sum;
            }
        }

        bw.write(minF + " ");
        bw.close();
    }
}
