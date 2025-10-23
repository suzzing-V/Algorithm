import java.util.*;
import java.io.*;

// 시간복잡도: 100 * 100 * 20
public class Main {

    private static char[][] board;
    private static int n;
    private static int m;
    private static int rx;
    private static int ry;
    private static int bx;
    private static int by;
    private static int hx;
    private static int hy;
    private static boolean[][][][] visited;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m][n][m];
        board = new char[n][m];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'R') {
                    board[i][j] = '.';
                    rx = i;
                    ry = j;
                }
                if(board[i][j] == 'B') {
                    board[i][j] = '.';
                    bx = i;
                    by = j;
                }
                if(board[i][j] == 'O') {
                    hx = i;
                    hy = j;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            for(int i = 0; i < 4; i++) {
                Node result = roll(i, curr.rx, curr.ry, curr.bx, curr.by);
//                System.out.println(curr.rx + "," + curr.ry + " " + curr.bx + "," + curr.by + "를 " + i + "쪽으로 굴림: " + result.rx + "," + result.ry + " " + result.bx + "," + result.by);
                if(result.rx == -1) continue;
                if(visited[result.rx][result.ry][result.bx][result.by]) continue;
                if(result.rx == hx && result.ry == hy) {
                    return curr.cnt + 1;
                }
                else {
                    queue.add(new Node(result.rx, result.ry, result.bx, result.by, curr.cnt + 1));
                    visited[result.rx][result.ry][result.bx][result.by] = true;
                }
            }
        }

        return -1;
    }

    private static Node roll(int d, int rx, int ry, int bx, int by) {
                Pos red = rollBall(d, rx, ry);
                Pos blue = rollBall(d, bx, by);

                // 만약 겹치면 후발주자를 한칸 전으로
                if(red.x == blue.x && red.y == blue.y) {
                    // 둘다 구멍이면 -1 반환
                    if(red.x == hx && red.y == hy) {
                        return new Node(-1, -1, -1, -1, -1);
                    }
                    // 아니면 원래 더 기울인 쪽에 가까운 공을 한칸 전으로
                    boolean isRedFirst = getFirstBall(d, rx, ry, bx, by);
                    if(isRedFirst) {

                        blue.x -= dir[d][0];
                        blue.y -= dir[d][1];
                    } else {
                        red.x -= dir[d][0];
                        red.y -= dir[d][1];
                    }
                } else {
                    // 둘 위치 다른데 파란 공이 구멍이면 -1 리턴
                    if(blue.x == hx && blue.y == hy) {
                        return new Node(-1, -1, -1, -1, -1);
                    }
                }
                // 둘다 구멍 아니면 그대로 리턴
                return new Node(red.x, red.y, blue.x, blue.y, 0);
    }

    private static boolean getFirstBall(int d, int rx, int ry, int bx, int by) {
        if(d == 0) {
            return rx >= bx;
        } else if(d == 1) {
            return ry >= by;
        } else if(d == 2) {
            return rx <= bx;
        }
        return ry <= by;
    }

    private static Pos rollBall(int d, int x, int y) {
        while(true) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];

            if(board[nx][ny] == '#') break;
            if(board[nx][ny] == 'O') {
                x += dir[d][0];
                y += dir[d][1];
                break;
            }

            x += dir[d][0];
            y += dir[d][1];
        }

        return new Pos(x, y);
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x =x;
            this.y =  y;
        }
    }

    private static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        Node(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
