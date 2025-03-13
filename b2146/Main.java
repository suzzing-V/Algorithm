import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] map;
    static List<List<Pos>> islands = new ArrayList<>();
    static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static class Pos {
        private int x;
        private int y;

        private Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 좌표 모으기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    islands.add(new ArrayList<>());
                    getIsland(i, j, islands.size() - 1);
                }
            }
        }

        // 섬 두 개씩 조합해서 거리 계산하고 최소 거리 구하기
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < islands.size() - 1; i++) {
            List<Pos> island1 = islands.get(i);
            for(int j = i + 1; j < islands.size(); j++) {
                List<Pos> island2 = islands.get(j);
                for(Pos pos1 : island1) {
                    for(Pos pos2 : island2) {
//                        System.out.println(pos1.x + " " + pos1.y + " " + pos2.x + " "+ pos2.y);
                        min = Math.min(Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y) - 1, min);
                    }
                }
            }
        }

        bw.write(String.valueOf(min));
        bw.close();
    }

    private static void getIsland(int x, int y, int island) {
        map[x][y] = 0;
        islands.get(island).add(new Pos(x, y));

        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 0) continue;
            getIsland(nx, ny, island);
        }
    }
}
