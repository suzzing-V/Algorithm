import java.util.*;
import java.io.*;

// 블록 두 개일 때, 메인블록보다 보조블럭 라인을 먼저 터뜨려야 한다.
// 행/열 차있는 함수, 블록 존재하는지 확인하는 함수 같이 써서 틀림.
// 시간복잡도: 10^4 * 96
public class Main {

    private static int n;
    private static int[][] blue = new int[4][6];
    private static int[][] green = new int[6][4];
    private static int[][] dir = {{1, 0}, {0, 1}};
    private static int score = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cnt = 0;
            if(t == 1) {
                cnt = 0;
                // 파란 보드
                Pos pos = move(blue, x, 0, 1, 1);
                bomb(blue, pos.x, pos.y, 0);
                if(isExist(blue, 0, 0, 0)) cnt ++;
                if(isExist(blue, 0, 1, 0)) cnt ++;
                if(cnt > 0) push(blue, cnt, 1);

                cnt = 0;
                // 초록 보드로 이동
                pos = move(green, 0, y, 0, 1);
                bomb(green, pos.x, pos.y, 1);
                if(isExist(green, 0, 0, 1)) cnt ++;
                if(isExist(green, 1, 0, 1)) cnt ++;
                if(cnt > 0) push(green, cnt, 0);
            } else if(t == 2) {
                cnt = 0;
                Pos pos = move(blue, x, 1, 1, 2);
                bomb(blue, pos.x, pos.y - 1, 0);
                bomb(blue, pos.x, pos.y, 0);
                if(isExist(blue, 0, 0, 0)) cnt ++;
                if(isExist(blue, 0, 1, 0)) cnt ++;
                if(cnt > 0) push(blue, cnt, 1);

                cnt = 0;
                pos = move(green, 0, y, 0, 3);
                bomb(green, pos.x, pos.y, 1);
                if(isExist(green, 0, 0, 1)) cnt ++;
                if(isExist(green, 1, 0, 1)) cnt ++;
                if(cnt > 0)  push(green, cnt, 0);
            } else if(t == 3) {
                cnt = 0;
                Pos pos = move(blue, x, 0, 1, 3);
//                System.out.println("움직인 후");
//                printBoard();
                bomb(blue, pos.x, pos.y, 0);
                if(isExist(blue, 0, 0, 0)) cnt ++;
                if(isExist(blue, 0, 1, 0)) cnt ++;
//                System.out.println(cnt);
                if(cnt > 0) push(blue, cnt, 1);

                cnt = 0;
                pos = move(green, 1, y, 0, 2);
                bomb(green, pos.x - 1, pos.y, 1);
                bomb(green, pos.x, pos.y, 1);
                if(isExist(green, 0, 0, 1)) cnt ++;
                if(isExist(green, 1, 0, 1)) cnt ++;
                if(cnt > 0) push(green, cnt, 0);
            }

//            printBoard();
        }

        int cnt = 0;
        for(int i = 0; i < blue.length; i++) {
            for(int j = 0; j < green.length; j++) {
                cnt += blue[i][j];
                cnt += green[j][i];
            }
        }

        System.out.println(score);
        System.out.println(cnt);
    }

    private static void printBoard() {
        for(int i = 0; i < blue.length; i++) {
            for(int j = 0; j < blue[0].length ;j++) {
                System.out.print(blue[i][j]);
            }
            System.out.println();
        }

        for(int i = 0; i < green.length; i++) {
            for(int j = 0; j < green[0].length ;j++) {
                System.out.print(green[i][j]);
            }
            System.out.println();
        }
    }

    private static void push(int[][] board, int cnt, int d) {
        if(d == 0) {
            for(int x = board.length - 1 - cnt; x >= 0; x --) {
                for(int y = 0; y < board[0].length; y++) {
                    board[x + cnt][y] = board[x][y];
                }
            }
            for(int x = 0; x < 2; x ++) {
                for(int y = 0; y < board[0].length; y++) {
                    board[x][y] = 0;
                }
            }
        } else {
            for(int y = board[0].length - 1 - cnt; y >= 0; y --) {
                for(int x = 0; x < board.length; x++) {
                    board[x][y + cnt] = board[x][y];
                }
            }

            for(int y = 0; y < 2; y ++) {
                for(int x = 0; x < board.length; x++) {
                    board[x][y] = 0;
                }
            }
        }
    }

    private static boolean isFilled(int[][] board, int x, int y, int d) {
        if(d == 0) {
            for(int i = 0; i < board.length; i++) {
//                System.out.println(i + " " + y);
                if(board[i][y] == 0) return false;
            }
        } else if(d == 1) {
            for(int i = 0; i < board[0].length; i++) {
                if(board[x][i] == 0) return false;
            }
        }

        return true;
    }

    private static boolean isExist(int[][] board, int x, int y, int d) {
        if(d == 0) {
            for(int i = 0; i < board.length; i++) {
//                System.out.println(i + " " + y);
                if(board[i][y] == 1) return true;
            }
        } else if(d == 1) {
            for(int i = 0; i < board[0].length; i++) {
                if(board[x][i] == 1) return true;
            }
        }

        return false;
    }

    private static void bomb(int[][] board, int x, int y, int d) {
        if(!isFilled(board, x, y, d)) return;

        if(d == 0) {
            for(int i = y - 1; i >= 0; i --) {
                for(int j = 0; j < board.length; j++) {
                    board[j][i + 1] = board[j][i];
                }
            }
        } else if(d == 1) {
            for(int i = x - 1; i >= 0; i --) {
                for(int j = 0; j < board[0].length; j++) {
                    board[i + 1][j] = board[i][j];
                }
            }
        }

        score ++;

        // 해당 줄 꽉 차있으면
    }

    private static Pos move(int[][] board, int sx, int sy, int d, int type) {
        if(type == 1) {
            while(true) {
                int nx = sx + dir[d][0];
                int ny = sy + dir[d][1];

                if(nx >= board.length || ny >= board[0].length || board[nx][ny] == 1) {
                    board[sx][sy] = 1;
                    break;
                }

                sx = nx;
                sy = ny;
            }
        } else if(type == 2) {
            while(true) {
                int nx = sx + dir[d][0];
                int ny = sy + dir[d][1];

                if(nx >= board.length || ny >= board[0].length || board[nx][ny] == 1) {
                    board[sx][sy] = 1;
                    board[sx - dir[d][0]][sy - dir[d][1]] = 1;
                    break;
                }

                sx = nx;
                sy = ny;
            }
        } else if(type == 3) {
            while(true) {
                int nx = sx + dir[d][0];
                int ny = sy + dir[d][1];

                if(nx >= board.length || ny >= board[0].length || board[nx][ny] == 1 || board[nx + dir[Math.abs(d - 1)][0]][ny + dir[Math.abs(d - 1)][1]] == 1) {
                    board[sx][sy] = 1;
                    board[sx + dir[Math.abs(d - 1)][0]][sy + dir[Math.abs(d - 1)][1]] = 1;
                    break;
                }

                sx = nx;
                sy = ny;
            }
        }

        return new Pos(sx, sy);
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
