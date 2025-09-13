import java.util.*;
import java.io.*;

// 각 원판마다 시작점을 저장해놓고, 회전할 때마다 이 시작점을 조정한다.
public class Main {

    private static int n;
    private static int m;
    private static int t;
    private static int[][] board;
    private static int sum = 0;
    private static int cnt = 0;
    private static int[] pointer;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        pointer = new int[n];

        cnt = n * m;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m ;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                sum += board[i][j];
            }
        }

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 회전 시키기
            for(int a = x - 1; a < n; a += x) {
                if(d == 0) pointer[a] -= k;
                else pointer[a] += k;

                // 범위 넘어갈 경우 조정
                int rest = (pointer[a] % m);
                if(pointer[a] < 0) pointer[a] = m + (pointer[a] % m) == m ? 0 : m + (pointer[a] % m);
                else if(pointer[a] >= m) pointer[a] = rest;

            }

//            System.out.println("회전");
//            printBoard();

            // 원판에 수가 남아있지 않으면 멈춘다.
            if(sum == 0) {
                break;
            }

            // 인접한 수 찾아서 없애기
            boolean[][] visited = new boolean[n][m];
            int deleted = 0;
            for(int a = 0; a < n; a ++) {
                for(int b = 0; b < m; b ++) {
                    int tx = a;
                    int ty = b + pointer[a];
                    int rest = (ty % m);
                    if(ty < 0) ty = m + (ty % m) == m ? 0 : m + (ty % m);
                    else if(ty >= m) ty = rest;
                    if(visited[tx][ty] || board[tx][ty] == -1) continue;
//                    System.out.println("bfs : " + tx + " " + ty);
                    deleted += bfs(tx, ty, b, visited);
                }
            }
//            System.out.println("인접 수 삭제");
//            printBoard();

            // 지운 수 없으면 평균보다 큰 수에서 -1, 작은 수에서 +1
            if(deleted == 0) {
                double avg = (double) sum / cnt;
                adjustByAvg(avg);
            }

//            System.out.println("평균 조정");
//            printBoard();
        }

        System.out.println(sum);
    }

    private static void printBoard() {
        for(int i =0 ;i < n; i++) {
            System.out.print("시작점: " + pointer[i] + " ");
            for(int j = 0; j < m ;j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void adjustByAvg(double avg) {
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == -1) continue;
                if(board[i][j] < avg) {
                    board[i][j] ++;
                    sum ++;
                }
                else if(board[i][j] > avg) {
                    board[i][j] --;
                    sum --;
                }
            }
        }
    }

    private static int bfs(int sx, int sy, int b, boolean[][] visited) {
        Queue<Pos> q = new LinkedList<>();
        // y에는 시작점 적용 안 한 값을 넣는다. 비교할 때 시작점 적용하기 위함
        q.add(new Pos(sx, b));
        visited[sx][sy] = true;
        int num = board[sx][sy];

        int deleted = 0;
        while(!q.isEmpty()) {
            Pos curr = q.remove();

//            System.out.println(curr.x + " " + curr.y);
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                if(nx < 0 || nx >= n) continue;
                int ny = curr.y + dir[i][1] + pointer[nx];

                // 원판 안의 숫자는 원형이다.
                int rest = (ny % m);
                if(ny < 0) ny = m + (ny % m) == m ? 0 : m + (ny % m);
                else if(ny >= m) ny = rest;

                if(visited[nx][ny]) continue;
                if(board[nx][ny] != num) continue;

                deleted ++;
                cnt --;
                sum -= board[nx][ny];
//                System.out.println(board[nx][ny] + " 뺀 후 합: " + sum);
                board[nx][ny] = -1;
                visited[nx][ny] = true;
                q.add(new Pos(nx, curr.y + dir[i][1]));
            }
        }

        if(deleted == 0) return 0;
            deleted ++;
            cnt --;
            sum -= board[sx][sy];
            board[sx][sy] = -1;
        return deleted;
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
