import java.io.*;
import java.util.*;

public class Main {

    static int[][] floyd_f;
    static int[][] floyd_r;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        floyd_f = new int[n + 1][n + 1];
        floyd_r = new int[n + 1][n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            floyd_f[s1][s2] = 1;
            floyd_r[s2][s1] = 1;
        }

        floyd(floyd_f);
        floyd(floyd_r);

        int result = 0;
        for(int i = 1; i <= n; i++) {
            int conn = 0;
            for(int j = 1; j <= n; j++) {
                if(floyd_f[i][j] == 1 || floyd_r[i][j] == 1) {
                    conn ++;
                }
            }
            if(conn == n - 1) {
                result ++;
            }
        }

        bw.write(String.valueOf(result));
        bw.close();
    }

    private static void floyd(int[][] arr) {
        for(int x = 1; x <= n; x++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(arr[i][x] == 1 && arr[x][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
    }
}
