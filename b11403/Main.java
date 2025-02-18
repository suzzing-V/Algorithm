import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] floyd;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        floyd = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                floyd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x = 0; x < n; x++) {
            for(int i = 0; i < n; i++) {
                if(i == x) continue;
                for(int j = 0; j < n;j ++) {
                    if(floyd[i][x] == 1 && floyd[x][j] == 1) {
                        floyd[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                bw.write(floyd[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.close();
    }
}
