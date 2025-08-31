import java.util.*;
import java.io.*;

// 프루닝 잘하기
public class Main {

    private static int r;
    private static int c;
    private static String[][] board;
    private static boolean[][] visited;
    private static int cnt = 0;
    private static int[][] move = {{-1, 1}, {0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new String[r][c];
        visited = new boolean[r][c];
        for(int i = 0; i < r; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < c; j++) {
                board[i][j] = split[j];
            }
        }

        for(int i = 0; i < r; i++) {
            cnt += dfs(i, 0);
        }
        System.out.println(cnt);
    }

    private static int dfs(int x, int y) {
//        System.out.println(x + " " + y);
        if(y == c - 1 && !visited[x][y]) {
            visited[x][y] = true;
//            System.out.println("도달");
            return 1;
        }

        visited[x][y] = true;
        for(int i = 0; i < 3; i++) {
            int nx = x + move[i][0];
            int ny = y + move[i][1];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c || board[nx][ny].equals("x") || visited[nx][ny]) continue;
            if(dfs(nx, ny) == 1) {
                return 1;
            }
        }
        // 다음 탐색 때도 이 지점에 왔을 때 끝 열에 도달할 수 있는 방법이 없다. 따라서 방문처리를 해제하지 않는다.
//        visited[x][y] = false;
        return 0;
    }
}
