import java.util.*;
import java.io.*;

// 시간복잡도: 100 * 100
// 불 켜진 곳으로 이동하면서 현재 칸에서 켤 수 있는 불을 켠다. 불을 킬 때 불을 키는 방이 이미 전에 탐색했다가 불 꺼져있어서 못 간 방이라면 다시 돌아가서 그 방부터 불을 킬 수 있으므로 탐색 시작한다.
// 따라서 불 꺼져서 이동 못할 경우에 그 칸도 방문 처리를 해줘야 한다.

public class Main {

    private static int n;
    private static int m;
    private static boolean[][] is_visited;
    private static boolean[][] is_light;
    private static List<Pos>[][] conn;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        is_visited = new boolean[n + 1][n + 1];
        is_light = new boolean[n + 1][n + 1];
        conn = new ArrayList[n + 1][n + 1];
        for(int i = 1;i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                conn[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int px = Integer.parseInt(st.nextToken());
            int py = Integer.parseInt(st.nextToken());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());

            conn[px][py].add(new Pos(cx, cy));
        }

        is_visited[1][1] = true;
        is_light[1][1] = true;
        dfs(1, 1);
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if (is_light[i][j]) cnt ++;
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int x, int y) {
//        System.out.println(x + " " + y);
        // 현재 방에서 켤 수 있는 방 불 켜기
        for(Pos pos : conn[x][y]) {
            // 만약 이미 방문했던 곳인데 불 안 켜져 있으면 그 방에서 탐색 시작
            if(!is_light[pos.x][pos.y] && is_visited[pos.x][pos.y]) {
                // 현재 칸 불 켜고 시작하지 않으면 다른 곳에서 탐색할 때 이 반복문에 걸린다. -> 무한 루프
                is_light[pos.x][pos.y] = true;
                dfs(pos.x, pos.y);
            }
            is_light[pos.x][pos.y] = true;
        }

        // 이동하기
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx <= 0 || nx > n || ny <= 0 || ny > n || is_visited[nx][ny]) continue;
            if(!is_light[nx][ny]) {
                // 불 꺼져있을 경우 방문처리 해주고 넘어가야 나중 순서에서 여기에 불켰을 때 여기 탐색할 수 있다.
                is_visited[nx][ny] = true;
                continue;
            }

            is_visited[nx][ny] = true;
            dfs(nx, ny);
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
