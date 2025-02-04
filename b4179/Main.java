import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static String[][] map;
    static int[][] fire_time;
    static int[][] man_time;
    static Queue<Pos> fire_queue = new LinkedList<>();
    static Queue<Pos> man_queue = new LinkedList<>();
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
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
        fire_time = new int[n][m];
        man_time = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                fire_time[i][j] = Integer.MAX_VALUE;
                man_time[i][j] = -1;
            }
        }

        for(int i = 0; i < n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                map[i][j] = split[j];
                if(map[i][j].equals("F")) {
                    fire_time[i][j] = 0;
                    fire_queue.add(new Pos(i, j));
                } else if(map[i][j].equals("J")) {
                    man_time[i][j] = 0;
                    man_queue.add(new Pos(i, j));
                }
            }
        }

        saveFireTime();
        int minTime = manBfs();
        if(minTime == -1) {
            bw.write("IMPOSSIBLE");
        } else {
            bw.write(String.valueOf(minTime));
        }
        bw.close();
    }

    private static void saveFireTime() {
        while(!fire_queue.isEmpty()) {
            Pos c = fire_queue.remove();

            for(int i = 0; i < 4; i++) {
                int nx = c.x + dir[i][0];
                int ny = c.y + dir[i][1];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || fire_time[nx][ny] != Integer.MAX_VALUE || map[nx][ny].equals("#")) {
                    continue;
                }
                fire_time[nx][ny] = fire_time[c.x][c.y] + 1;
                fire_queue.add(new Pos(nx, ny));
            }
        }
    }

    private static int manBfs() {
        while(!man_queue.isEmpty()) {
            Pos c = man_queue.remove();

            for(int i = 0; i < 4; i++) {
                int nx = c.x + dir[i][0];
                int ny = c.y + dir[i][1];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    return man_time[c.x][c.y] + 1;
                }
                if(map[nx][ny].equals("#") || man_time[nx][ny] != -1 || fire_time[nx][ny] <= man_time[c.x][c.y] + 1) {
                    continue;
                }
                man_time[nx][ny] = man_time[c.x][c.y] + 1;
                man_queue.add(new Pos(nx, ny));
            }
        }

        return -1;
    }
}
