import java.io.*;
import java.util.*;

public class Main {

    static int[][] garden;
    static boolean[][] visited;
    static int n;
    static int maxTotal = 0;
    static int[][] dir = { {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        garden = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tiedTrees(0, 0);
        bw.write(String.valueOf(maxTotal));
        bw.close();
    }

    private static void tiedTrees(int couple, int beauty) {
        // System.out.println(x + " " + y + " " + couple + " " + beauty);
        if(couple == 4) {
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) continue;
                visited[i][j] = true;

                for(int k = 0 ; k < 2; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    // System.out.println(garden[i][j] + " " + garden[nx][ny] + " " + (couple + 1) + " " + (beauty + garden[nx][ny]));
                    maxTotal = Math.max(beauty + garden[nx][ny] + garden[i][j], maxTotal);
                    tiedTrees(couple + 1, beauty + garden[nx][ny] + garden[i][j]);
                    visited[nx][ny] = false;
                }
                visited[i][j] = false;
            }
        }
    }
}

