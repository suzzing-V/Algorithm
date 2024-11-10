import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Bridge implements Comparable<Bridge>{
        int start;
        int end;
        int len;

        Bridge(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.len - o.len;
        }
    }
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<ArrayList<Pos>> islands = new ArrayList<>();
    static PriorityQueue<Bridge> bridges = new PriorityQueue<>();
    static int[][] dir = { {0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n;i ++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n;i ++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    islands.add(new ArrayList<>());
                    makeIsland(i, j);
                }
            }
        }

        for(ArrayList<Pos> list : islands) {
            for(Pos pos : list) {
                for(int i = 0; i < 4; i++) {
//                    System.out.println("start: " + (pos.x + dir[i][0]) + " " + (pos.y + dir[i][1]));
                    makeBridge(pos.x + dir[i][0], pos.y + dir[i][1], islands.indexOf(list), i, 0);
                }
            }
        }

        parents = new int[islands.size()];
        for(int a = 0; a < parents.length; a ++) {
            parents[a] = a;
        }

        int result = 0;
        int cnt = 0;
        while(!bridges.isEmpty()) {
            Bridge bridge = bridges.remove();
            if(find(bridge.start) != find(bridge.end)) {
                result += bridge.len;
                union(bridge.start, bridge.end);
                cnt++;
            }
        }
        if(result == 0 || cnt != islands.size() - 1) {
            result = -1;
        }

        System.out.println(result);
    }

    private static void makeIsland(int startX, int startY) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(startX, startY));

        while(!queue.isEmpty()) {
            Pos now = queue.remove();

            visited[now.x][now.y] = true;
            islands.get(islands.size() - 1).add(new Pos(now.x, now.y));
            for(int i = 0; i < 4; i ++) {
                int nextX = now.x + dir[i][0];
                int nextY = now.y + dir[i][1];
                if(nextX >= 0 && nextY >= 0 && nextX < n && nextY < m && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    queue.add(new Pos(nextX, nextY));
                }
            }
        }
    }

    private static int findIsland(int x, int y) {
        for(ArrayList<Pos> list : islands) {
            for(Pos pos : list) {
                if(x == pos.x && y == pos.y) {
                    return islands.indexOf(list);
                }
            }
        }
        return 0;
    }

    private static int find(int x) {
        if(x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static void makeBridge(int x, int y, int num, int d, int len) {
//        System.out.println("len : " + len);
        if(x < 0 || y < 0 || x >= n || y >= m) {
            return;
        }

        int island = findIsland(x, y);
        if(map[x][y] == 1 && island == num) return;
        if(map[x][y] == 1 && island != num) {
            if(len >= 2) {
                bridges.add(new Bridge(num, island, len));
//                System.out.println(num + " " + island + " " + len + " " + d);
                return;
            } else return;
        }

        int nx = x + dir[d][0];
        int ny = y + dir[d][1];
        makeBridge(nx, ny, num, d, len + 1);
    }
}
