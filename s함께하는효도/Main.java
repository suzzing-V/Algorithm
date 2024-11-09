import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int n;
    static int m;
    static int[][] friends;
    static int result = 0;
    static int init = 0;
    static boolean[][] visited;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        friends = new int[m][2];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1 ; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            friends[i][0] = Integer.parseInt(st.nextToken());
            friends[i][1] = Integer.parseInt(st.nextToken());
            init += map[friends[i][0]][friends[i][1]];
            visited[friends[i][0]][friends[i][1]] = true;
        }
        for(int i = 0; i < 4; i++) {
            dfs(0, friends[0][0] + dir[i][0], friends[0][1] + dir[i][1], 1, init);
        }

        System.out.println(result);
    }

    private static void dfs(int friend, int x, int y, int count, int amount) {
        // System.out.println(friend + " " + x + " " + y + " " + count + " ");
        if(count % 4 == 0) {
            if(friend + 1 >= m) {
                result = Math.max(amount, result);
                // System.out.println("끝");
                return;
            }
            // System.out.println("count:" + count + " " + friend);
            // for(int i = 1; i <= n; i++) {
            //             for(int j = 1; j <= n; j++) {
            //             System.out.print(visited[i][j] + "  ");
            //                 }
            //             System.out.println();
            //         }
            // System.out.println();
            for(int i = 0; i < 4; i++) {
                // System.out.println("진입" + (friend + 1) + " " + (friends[friend + 1][0] + dir[i][0])+ " " + (friends[friend + 1][1] + dir[i][1])+ " " + (count + 1));
                dfs(friend + 1, friends[friend + 1][0] + dir[i][0], friends[friend + 1][1] + dir[i][1], count + 1, amount);
            }
            return;
        }

        if(x <= 0 || x > n || y <= 0 || y > n || visited[x][y]) {
            // System.out.println("벽");
            return;
        }

        // System.out.println("x y: " + x + " " + y);
        // System.out.println("amount: " + amount);
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            dfs(friend, x + dir[i][0], y + dir[i][1], count + 1, amount + map[x][y]);
        }
        visited[x][y] = false;
    }
}
