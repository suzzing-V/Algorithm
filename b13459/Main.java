import java.util.*;
import java.io.*;

// 시간복잡도: 4^10 * 20
// 파란색 볼이 구멍에 빠졌을 때 바로 리턴해서 틀림. 다음 방향으로 움직엿을 때도 고려해야 하므로 continue해줘야 함. 리팩토링 과정에서 실수
public class Main {

    private static int n;
    private static int m;
    private static String[][] board;
    private static int hx;
    private static int hy;
    private static int rx;
    private static int ry;
    private static int bx;
    private static int by;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new String[n][m];

        for(int i = 0; i < n ;i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0;j < m; j++) {
                board[i][j] = split[j];
                if(board[i][j].equals("O")) {
                    hx = i;
                    hy = j;
                } else if(board[i][j].equals("R")) {
                    rx = i;
                    ry = j;
                    board[i][j] = ".";
                } else if(board[i][j].equals("B")) {
                    bx = i;
                    by = j;
                    board[i][j] = ".";
                }
            }
        }

        System.out.println(move(1, rx, ry, bx, by));
    }

    private static int move(int turn, int rx, int ry, int bx, int by) {
//        System.out.println(turn + " " + rx + " "  + ry + " " + bx + " " + by);
        if(turn == 11) {
            return 0;
        }

        for(int i = 0; i < 4; i ++) {
            Node next = moveLeft(rx, ry, bx, by, i);
            if(next.bx == hx && next.by == hy) continue;
            if(next.rx == hx && next.ry == hy) return 1;
            if(move(turn + 1, next.rx, next.ry, next.bx, next.by) == 1) {
                return 1;
            }
        }

        return 0;
    }

    private static boolean isOut(int x, int y) {
        if(x < 0 || y < 0 || x >= n || y >= m) return true;
        return false;
    }

    private static Node moveLeft(int rx, int ry, int bx, int by, int d) {
        int nrx = rx;
        int nry = ry;
        int nbx = bx;
        int nby = by;
//        System.out.println(d + "쪽 이동 전: " + rx + " "  + ry + " " + bx + " " + by);
        while(true) {
            nrx += dir[d][0];
            nry += dir[d][1];
            if(isOut(nrx, nry) || board[nrx][nry].equals("#")) {
                nrx -= dir[d][0];
                nry -= dir[d][1];
                break;
            }
            if(board[nrx][nry].equals("O")) {
                break;
            }
        }
        while(true) {
            nbx += dir[d][0];
            nby += dir[d][1];
            if(isOut(nbx, nby) || board[nbx][nby].equals("#")) {
                nbx -= dir[d][0];
                nby -= dir[d][1];
                break;
            }
            if(board[nbx][nby].equals("O")) {
                break;
            }
        }

        // 굴렸는데 구멍 아닌데 빨간공과 파란공의 위치가 같으면 하나는 옆에 둔다.
        if(!board[nrx][nry].equals("O") && nrx == nbx && nry == nby) {
            // 아래쪽 이동
            if(d == 0) {
                if(rx < bx) {
                    rx = nrx - 1;
                    bx = nrx;
                } else {
                    rx = nrx;
                    bx = nrx - 1;
                }
            } else if(d == 1) {
                if(ry < by) {
                    ry = nry - 1;
                    by = nry;
                } else {
                    ry = nry;
                    by = nry - 1;
                }
            } else if(d == 2) {
                if(rx < bx) {
                    rx = nrx;
                    bx = nrx + 1;
                } else {
                    rx = nrx + 1;
                    bx = nrx;
                }
            } else if(d == 3) {
                if(ry < by) {
                    ry = nry;
                    by = nry + 1;
                } else {
                    ry = nry + 1;
                    by = nry;
                }
            }
        } else {
            rx = nrx;
            bx = nbx;
            ry = nry;
            by = nby;
        }

//        System.out.println(d + "쪽 이동 후: " + rx + " "  + ry + " " + bx + " " + by);

        return new Node(rx, ry, bx, by);
    }

    private static class Node {
        int rx;
        int ry;
        int bx;
        int by;

        Node(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }
}
