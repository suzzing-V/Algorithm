import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = { {0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int roomCnt = 0;
        int originMax = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
//                    System.out.println("시작: " + i + " " + j + " " + countRooms(i, j));
                    originMax = Math.max(originMax, countRooms(i, j));
                    roomCnt ++;
                }
            }
        }


        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 4; k++) {
                    visited = new boolean[m][n];
                    total = 0;
//                    System.out.println(i + " " + j);
                    int crash = map[i][j] & ~(1 << k);
                    if(crash == map[i][j]) continue;
                    int original = map[i][j];
                    map[i][j] = crash;
                    visited[i][j] = true;
                    max = Math.max(max, countRooms(i, j));
                    map[i][j] = original;
                }
            }
        }

        bw.write(roomCnt + "\n" + originMax + "\n" + max);
        bw.close();
    }

    private static int countRooms(int x, int y) {
//        System.out.println(x + " "+ y);
        int cnt = 1;
        for(int i = 0; i < 4; i++) {
            if((map[x][y] & (1 << i)) != 0) continue;
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            cnt += countRooms(nx, ny);
        }
//        System.out.println(x + " " + y + " "+  cnt);
        return cnt;
    }
}
