import java.io.*;
import java.util.*;

public class Main {
    static String[][] map;
    static int[][] ghost_time;
    static Queue<Ghost> ghost_queue = new LinkedList<>();
    static int n;
    static int m;
    static int[][] dir = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Ghost {
        int x;
        int y;

        Ghost(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new String[n][m];
        int namX = 0;
        int namY = 0;
        ghost_time = new int[n][m];

        for(int i = 0; i < n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                map[i][j] = split[j];
                ghost_time[i][j] = 2000;
                if(map[i][j].equals("G")) {
                    ghost_queue.add(new Ghost(i, j));
                    ghost_time[i][j] = 0;
                }
                if(map[i][j].equals("N")) {
                    namX = i;
                    namY = j;
                }
            }
        }

        ghostBfs();
        bw.write(namBfs(namX, namY));
        bw.close();
    }

    private static void ghostBfs() {
        while(!ghost_queue.isEmpty()) {
            Ghost curr = ghost_queue.remove();

            for(int i = 0; i < 4; i ++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if(ghost_time[curr.x][curr.y] + 1 < ghost_time[nx][ny]) {
                    ghost_time[nx][ny] = ghost_time[curr.x][curr.y] + 1;
                    ghost_queue.add(new Ghost(nx, ny));
                }
            }
        }
    }

    private static String namBfs(int x, int y) {
        Queue<Ghost> queue = new LinkedList<>();
        int[][] visited = new int[n][m];
        queue.add(new Ghost(x, y));

        while(!queue.isEmpty()) {
            Ghost curr = queue.remove();

            if(map[curr.x][curr.y].equals("D")) {
                return "Yes";
            }
            for(int i = 0; i < 4; i ++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m|| map[nx][ny].equals("#") || visited[nx][ny] != 0) {
                    continue;
                }
                if(visited[curr.x][curr.y] + 1 < ghost_time[nx][ny]) {
                    visited[nx][ny] = visited[curr.x][curr.y] + 1;
                    queue.add(new Ghost(nx, ny));
                }
            }
        }

        return "No";
    }
}

