import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static List<Cctv> cctvs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int[][] tmpMap;
    static int[][] dir1 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][][] dir2 = {{{0, 1}, {0, -1}}, {{1, 0}, {-1, 0}}};
    static int[][][] dir3 = {{{0, 1}, {-1, 0}}, {{1, 0}, {0, 1}}, {{1, 0}, {0, -1}}, {{0, -1}, {-1, 0}}};
    static int[][][] dir4 = {{{0, -1}, {-1, 0}, {0, 1}}, {{1, 0}, {0, 1}, {-1, 0}}, {{1, 0}, {0, -1}, {0, 1}}, {{0, -1}, {-1, 0}, {1, 0}}};

    private static class Cctv {
        private int x;
        private int y;
        private int num;
        private int dir;

        private Cctv(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) cctvs.add(new Cctv(i, j, map[i][j]));
            }
        }

        dfs(0);
        bw.write(String.valueOf(min));
        bw.close();
    }

    private static void dfs(int idx) {
        if(idx == cctvs.size()) {
            tmpMap = new int[n][m];
            for(int i = 0; i < n; i++) {
                tmpMap[i] = Arrays.copyOf(map[i], m);
            }

            for(Cctv cctv : cctvs) {
                checkMonitoring(cctv);
            }
//            for(int i = 0; i < n; i++) {
//                System.out.println(Arrays.toString(tmpMap[i]));
//            }
//            System.out.println();

            min = Math.min(min, countSquare());
            return;
        }
//        System.out.println(idx + " " + cctvs.get(idx).num);

        Cctv cctv = cctvs.get(idx);
        int kind = 0;
        if(cctv.num == 1 || cctv.num == 3 || cctv.num == 4) kind = 4;
        else if(cctv.num == 2) kind = 2;
        else if(cctv.num == 5) kind = 1;

        for(int i = 0; i < kind; i++) {
            cctv.dir = i;
            dfs(idx + 1);
            cctv.dir = -1;
        }
    }

    private static int countSquare() {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tmpMap[i][j] == 0) {
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    private static void checkMonitoring(Cctv cctv) {
        if(cctv.num == 1) {
            check(dir1[cctv.dir], cctv.x, cctv.y);
        } else if(cctv.num == 2) {
            for(int i = 0; i < 2; i++) {
                check(dir2[cctv.dir][i], cctv.x, cctv.y);
            }
        } else if(cctv.num == 3) {
            for(int i = 0; i < 2; i++) {
                check(dir3[cctv.dir][i], cctv.x, cctv.y);
            }
        } else if(cctv.num == 4) {
            for(int i = 0; i < 3; i++) {
                check(dir4[cctv.dir][i], cctv.x, cctv.y);
            }
        } else {
            for(int i = 0; i < 4; i++) {
                check(dir1[i], cctv.x, cctv.y);
            }
        }
    }

    private static void check(int[] dir, int sx, int sy) {
        while(true) {
            int nx = sx + dir[0];
            int ny = sy + dir[1];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m || tmpMap[nx][ny] == 6) {
                break;
            }
            if(tmpMap[nx][ny] == 0) {
                tmpMap[nx][ny] = -1; // 감시 영역
            }
            sx = nx;
            sy = ny;
        }
    }
}
