import java.io.*;
import java.util.*;

public class Main {

    static int[][] chess;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        chess = new int[n][n];
        for(int i = 0; i < n; i ++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
               chess[i][j] = Integer.parseInt(st.nextToken());
               if(chess[i][j] == 1) {
                   visited[i][j] = true; //true면 못 감
               }
            }
        }

        
    }
}
