import java.util.*;
import java.io.*;

// 여러 손님의 목적지가 같을 수 있다.
public class Main {

    private static int n;
    private static int m;
    private static int energy;
    private static int[][] board_cus;
    private static int[][][] board_des;
    private static int tx;
    private static int ty;

    private static int cus = 0;

    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());
        board_cus = new int[n + 1][n + 1];
        board_des = new int[n + 1][n + 1][m + 2];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1;j  <= n; j++) {
                int b = Integer.parseInt(st.nextToken());
                board_cus[i][j] = b;
            }
        }

        st = new StringTokenizer(bf.readLine());
        tx = Integer.parseInt(st.nextToken());
        ty = Integer.parseInt(st.nextToken());

        for(int i = 1;i <= m ; i++) {
            st = new StringTokenizer(bf.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            board_cus[sx][sy] = i + 1;
//            System.out.println(dx + " "+ dy + " "+ (i + 1));
            board_des[dx][dy][i + 1] = 1;
        }

        while(cus < m) {
            // 제일 가까운 승객에게 가기
            Pos c_pos = toCustomer(tx, ty);
            if(c_pos == null || c_pos.d == -1) {
                energy = -1;
                break;
            }
            energy -= c_pos.d;
            tx = c_pos.x;
            ty = c_pos.y;
            System.out.println("승객 태움: " + c_pos.x + " " + c_pos.y);
            // 그 승객의 목적지까지 가기
            Pos d_pos = toDestination(c_pos.x, c_pos.y, c_pos.n);

            if(d_pos == null || d_pos.d == -1) {
                energy = -1;
                break;
            }
            energy += d_pos.d;
            tx = d_pos.x;
            ty = d_pos.y;
            cus ++;
        }

        System.out.println(energy);
    }

    private static Pos toCustomer(int sx, int sy) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(sx, sy, 0, board_cus[sx][sy]));
        visited[sx][sy] = true;

        while(!pq.isEmpty()) {
            Pos curr = pq.remove();
//            System.out.println(curr.x + " "+ curr.y + " " + curr.d + " "+ curr.n);

            // 승객 만나면 태우기
            if(board_cus[curr.x][curr.y] > 1) {
                board_cus[curr.x][curr.y] = 0;
                return curr;
            }

            // 연료 다 쓰면 -1 리턴해야함
            if(curr.d > energy) {
                curr.d = -1;
                return curr;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 1 || nx > n || ny < 1 || ny > n || visited[nx][ny] || board_cus[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                pq.add(new Pos(nx, ny, curr.d + 1, board_cus[nx][ny]));
            }
        }

        return null;
    }


    private static Pos toDestination(int sx, int sy, int c) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(sx, sy, 0, c));
        visited[sx][sy] = true;

        while(!pq.isEmpty()) {
            Pos curr = pq.remove();

//            System.out.println(curr.x + " "+ curr.y + " " + curr.d + " "+ curr.n);

            // 도착지 도착하면 리턴
            if(board_des[curr.x][curr.y][c] == 1) {
                board_des[curr.x][curr.y][c] = 0;
                return curr;
            }

            // 연료 다 쓰면 -1 리턴해야함
            if(curr.d > energy) {
                curr.d = -1;
                return curr;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 1 || nx > n || ny < 1 || ny > n || visited[nx][ny] || board_cus[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                pq.add(new Pos(nx, ny, curr.d + 1, c));
            }
        }

        return null;
    }

    private static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int d;
        int n;

        Pos(int x, int y, int d, int n) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.n = n;
        }

        @Override
        public int compareTo(Pos p) {
            if(this.d == p.d) {
                if(this.x == p.x) {
                    return this.y - p.y;
                }
                return this.x - p.x;
            }
            return this.d - p.d;
        }
    }
}
