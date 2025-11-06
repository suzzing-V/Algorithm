import java.util.*;
import java.io.*;

// 시간복잡도: 4^10 * 16
// 종료 처리 문제 실수
public class Main {

    private static int n;
    private static int m;
    private static char[][] board;
    private static int rx;
    private static int ry;
    private static int bx;
    private static int by;
    private static int min = 20;
    private static String order;
    private static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static char[] dc = {'L', 'R', 'U', 'D'};
    private static Map<String, Integer> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for(int i = 0; i < n; i ++) {
            String line = bf.readLine();
            for(int j =0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'B') {
                    bx = i;
                    by = j;
                    board[i][j] = '.';
                } else if(board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    board[i][j] = '.';
                }
            }
        }

//        String str = rx + " " + ry + " " + bx + " " + by;
//        visited.put(str, 0);
        dfs(rx, ry, bx, by, 1, new StringBuilder());
        if(min == 20) System.out.println("-1");
        else {
            System.out.println(min);
            System.out.println(order);
        }
    }

    private static void dfs(int rx, int ry, int bx, int by, int cnt, StringBuilder sb) {
//        System.out.println(rx + " " + ry + " "+ bx + " " + by + " " + cnt + " " + sb);
        if(board[rx][ry] == 'O' && min > sb.length()) {
            order = sb.toString();
            min = sb.length();
            return;
        }
        // 빨간공이 구멍에 빠지지도 않았는데 더 굴려야하면 리턴
        if(sb.length() >= 10) return;

        for(int i = 0; i < 4; i++) {
            Result result = roll(i, rx, ry, bx, by);

//            System.out.println(dc[i] + "로 roll 후: " + result.rx + " " + result.ry + " " + result.bx + " " + result.by);
//            String str = result.rx + " " + result.ry + " "+ result.bx + " " + result.by;
//            if(visited.containsKey(str)) continue;
//            visited.put(str, 0);
            sb.append(dc[i]);
            // 같으면 함께 구멍이라는 소리. 같거나 파란색이 구멍이면 넘어감
            if(board[result.bx][result.by] == 'O') {
                sb.setLength(cnt - 1);
                continue;
            }

            dfs(result.rx, result.ry, result.bx, result.by, cnt + 1, sb);
            sb.setLength(cnt - 1);
        }
    }

    private static boolean isSame(Result result) {
        if(result.rx == result.bx && result.ry == result.by) return true;
        return false;
    }

    private static Result roll(int d, int rx, int ry, int bx, int by) {
        // 둘 중 굴리고자 하는 방향에 더 가까운 공
        boolean redFirst = isRedFirst(d, rx, ry, bx, by);
        // 빨간공 굴리기
        while(true) {
            rx += dir[d][0];
            ry += dir[d][1];

            if(board[rx][ry] == '#') {
                rx -= dir[d][0];
                ry -= dir[d][1];
                break;
            }

            if(board[rx][ry] == 'O') {
                break;
            }
        }

        // 파란공 굴리기
        while(true) {
            bx += dir[d][0];
            by += dir[d][1];

            if(board[bx][by] == '#') {
                bx -= dir[d][0];
                by -= dir[d][1];
                break;
            }

            if(board[bx][by] == 'O') {
                break;
            }
        }

        Result result = new Result(rx, ry, bx, by);
        if(isSame(result) && board[rx][ry] != 'O') {
//            System.out.println("같음");
            if(redFirst) {
//                System.out.println("red 선");
                result.bx -= dir[d][0];
                result.by -= dir[d][1];
            } else {
                result.rx -= dir[d][0];
                result.ry -= dir[d][1];
            }
        }
        return result;
    }

    private static boolean isRedFirst(int d, int rx, int ry, int bx, int by) {
        if(d == 0) {
            if(ry < by) return true;
        } else if(d== 1) {
            if(ry > by) return true;
        } else if(d == 2) {
            if(rx < bx) return true;
        } else if(d == 3) {
            if(rx > bx) return true;
        }

        return false;
    }

    private static class Result {
        int rx;
        int ry;
        int bx;
        int by;

        Result(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }
}
