import java.io.*;
import java.util.*;

// 시간 복잡도: 2^25 * 7
public class Main {

    private static String[][] board = new String[5][5];
    private static boolean[][] visited = new boolean[5][5];
    private static int cnt = 0;
    private static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < 5; j++) {
                board[i][j] = split[j];
            }
        }

        boolean[][] selected = new boolean[5][5];
        dfs(0, 0, selected, 0, 0);

        System.out.println(cnt);
    }

    private static void dfs(int x, int y, boolean[][] selected, int som, int yim) {
        if(yim >= 4) return;

        if(som + yim == 7) {
            if(isConnected(selected)) {
//                for(int i = 0; i < 5; i++) {
//                    for(int j =0; j < 5; j++) {
//                        System.out.print(selected[i][j] + " ");
//                    }
//                    System.out.println();
//                }
                cnt ++;
            }
            return;
        }

        if(y >= 5) {
            x ++;
            y = 0;
        }

        if(x == 5) {
            return;
        }

        //현재 꺼 선택
        selected[x][y] = true;
        if(board[x][y].equals("S")) dfs(x, y + 1, selected, som + 1, yim);
        if(board[x][y].equals("Y")) dfs(x, y + 1, selected, som, yim + 1);
        selected[x][y] = false;

        //현재꺼 선택 안함
        dfs(x, y + 1, selected, som, yim);
    }

    private static boolean isConnected(boolean[][] selected) {
        int x = 0 ;
        int y = 0;
        for(int i = 0; i < 5; i++) {
            boolean flag = false;
            for(int j = 0;j < 5; j++) {
                if(selected[i][j]) {
                    x = i;
                    y = j;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x,y));
        boolean[][] v = new boolean[5][5];
        v[x][y] = true;
        int cnt = 0;
        while(!queue.isEmpty()) {
            Pos curr = queue.remove();
            cnt ++;
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || v[nx][ny] || !selected[nx][ny]) {
                    continue;
                }

                v[nx][ny] = true;
                queue.add(new Pos(nx, ny));
            }
        }

        if(cnt == 7) return true;
        return false;
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
