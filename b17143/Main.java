import java.io.*;
import java.util.*;

public class Main {

    static int r;
    static int c;
    static int m;
    static int result = 0;
    static Shark[][] map;
    static int[][] di = { {0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static class Shark {
        private int x;
        private int y;
        private int size;
        private int speed;
        private int dir;

        private Shark(int x, int y, int size, int speed, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.size = size;
            this.speed = speed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new Shark[r][c];

        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(x - 1, y - 1, size, speed, dir);
            map[x - 1][y - 1] = shark;
        }

        for(int i = 0; i < c; i++) {
            catchShark(i);
            Shark[][] next = new Shark[r][c];
            moveShark(next);
            map = next;
//            for(int a = 0; a < r; a ++) {
//                for(int b = 0; b < c; b++) {
//                    if(map[a][b] == null) System.out.print("0 ");
//                    else System.out.print(map[a][b].size + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        bw.write(String.valueOf(result));
        bw.close();
    }

    private static void catchShark(int y) {
        for(int i = 0; i < r; i++) {
            if(map[i][y] != null) {
                result += map[i][y].size;
                map[i][y] = null;
                break;
            }
        }
    }

    private static void moveShark(Shark[][] next) {
        Queue<Shark> sharks = new LinkedList<>();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] != null) sharks.add(map[i][j]);
            }
        }

        while(!sharks.isEmpty()) {
            Shark shark = sharks.remove();
            int speed = shark.speed;
            if (shark.dir == 1 || shark.dir == 2) speed %= (r - 1) * 2;
            else speed %= (c - 1) * 2;

            for (int i = 0; i < speed; i++) {
                int nx = shark.x + di[shark.dir][0];
                int ny = shark.y + di[shark.dir][1];
                if (nx < 0 || nx > r - 1 || ny < 0 || ny > c - 1) {
                    if (shark.dir == 1) shark.dir = 2;
                    else if (shark.dir == 2) shark.dir = 1;
                    else if (shark.dir == 3) shark.dir = 4;
                    else if (shark.dir == 4) shark.dir = 3;
                    shark.x += di[shark.dir][0];
                    shark.y += di[shark.dir][1];
                    continue;
                }
                shark.x = nx;
                shark.y = ny;
            }

            if (next[shark.x][shark.y] != null) {
                if (next[shark.x][shark.y].size < shark.size) {
                    sharks.remove(next[shark.x][shark.y]);
                    next[shark.x][shark.y] = shark;
                }
            } else {
                next[shark.x][shark.y] = shark;
            }
        }
    }
}
