import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static class Node {
        private int x;
        private int y;
        private int cnt;

        private Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String row = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.close();
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            if(curr.x == n - 1 && curr.y == m - 1) return curr.cnt;

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, curr.cnt + 1));
            }
        }

        return 0;
    }
}
