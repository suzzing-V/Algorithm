import java.io.*;
import java.util.*;

public class Main {
    static int[][] dist;
    static int n;
    static int m;
    static int INF = 90000000;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        dist = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) { 
            for(int j = 1; j <= n; j++) {
                if(i != j) dist[i][j] = INF;
            }
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        floydWarshall();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void floydWarshall() {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(dist[i][j] == INF) {
                    dist[i][j] = 0;
                }
            }
        }
    }
}