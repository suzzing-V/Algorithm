import java.util.*;
import java.io.*;

public class Main {

    private static boolean[][] map;
    private static boolean[][] visited;
    private static int t;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());
        for(int i = 0; i< t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map = new boolean[n][m];
            visited = new boolean[n][m];

            for(int j = 0; j < c; j++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }

//            printMap();

            int worm = 0;
            for(int a = 0; a < n; a ++) {
                for(int b = 0; b < m; b ++) {
                    if(visited[a][b] || !map[a][b]) continue;

                    bfs(b, a);
//                    printVisited();
                    worm ++;
                }
            }
            System.out.println(worm);
        }
    }

    private static void printVisited() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int sx, int sy) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(sx, sy));

        visited[sy][sx] = true;
        while(!queue.isEmpty()) {
            Pos curr = queue.remove();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][1];
                int ny = curr.y + dir[i][0];

                if(nx < 0 || nx >= map[0].length || ny < 0 || ny >= map.length || visited[ny][nx] || !map[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                queue.add(new Pos(nx, ny));
            }
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
