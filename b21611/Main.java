import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] dir;
    private static int[][] board;
    private static Pos[] poses;
    private static int[] answer;
    private static int[][] look = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        poses = new Pos[n * n + 1];
        dir = new int[4][n * n];
        answer = new int[4];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int i = n / 2;
        int j = n / 2;
        int d = 3;
        int cnt = 1;
        int k = 1;
        while(true) {
            for(int c = 1; c <= cnt; c ++) {
                i += look[d][0];
                j += look[d][1];
                poses[k ++] = new Pos(i, j);
            }

//            System.out.println(i + " " + j);
            if(i < 0 || i >= n || j < 0 || j >= n) {
                break;
            }

            d ++;
            if(d == 4) d = 0;
            for(int c = 1; c <= cnt; c ++) {
                i += look[d][0];
                j += look[d][1];
                poses[k ++] = new Pos(i, j);
            }
//            System.out.println(i + " " + j);
            d ++;
            if(d == 4) d = 0;
            cnt ++;
            if(i < 0 || i >= n || j < 0 || j >= n) {
                break;
            }
        }

//        for(int x = 1; x <= n * n; x++) {
//            System.out.println(x + " " + poses[x].x + " " + poses[x].y);
//        }

        // 방향별 위치정보 저장
        int pos = 1;
        i = 0;
        j = 0;
        int diff = 1;
        while(pos < n * n) {
            dir[i][j] = pos;

            i ++;
            if(i >= 4) {
                j ++;
                i = 0;
                pos += 2 * diff + 1;
                diff ++;
            } else {
                pos += 2 * diff;
            }
        }

//        for(int x = 0; x < 4; x++) {
//            for(int y = 0; y < dir[0].length; y++) {
//                System.out.print(dir[x][y] + " ");
//            }
//            System.out.println();
//        }

        for(int a = 0; a < m; a++) {
            st = new StringTokenizer(bf.readLine());
            d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            if(d == 1) d = 3;
            else if(d == 2) d = 1;
            else if(d == 3) d = 0;
            else if(d == 4) d = 2;

            boolean[] isDeleted = new boolean[n * n + 1];
            // 방향에 있는 구슬 s개만큼 파괴하기
            for(int b = 0; b < s; b++) {
//                System.out.println("파괴: " + dir[d][b]);
                isDeleted[dir[d][b]] = true;
            }

//            for(int x = 1; x <= n * n; x++) {
//                System.out.println(x + " " + poses[x].x + " " + poses[x].y);
//            }
            // 구슬 밀기
            // 현재까지 파괴된 구슬 개수
            int broken = 0;
            boolean meetZero = false;
            for(int b = 1; b < n * n; b ++) {
                // 원래 구슬 없던 자리에 도달하면 앞에 파괴된 구슬만큼 빈자리로 만들어준다.
                if(board[poses[b].x][poses[b].y] == 0) {
                    for(int c = 1; c <= broken; c ++) {
                        board[poses[b - c].x][poses[b - c].y] = 0;
                    }
                    meetZero = true;
                    break;
                }
                // 파괴된 구슬이면 broken을 증가시킨다.
                if(isDeleted[b]) {
                    broken++;
                } else {
                    // 파괴된 구슬이 아니면 지금까지 파괴된 구슬 개수만큼 앞으로 당긴다.
//                    System.out.println(poses[b - broken].x + " " + poses[b - broken].y + " " + board[poses[b].x][poses[b].y]);
                    board[poses[b - broken].x][poses[b - broken].y] = board[poses[b].x][poses[b].y];
                }
            }

            // 만약에 기존에 빈 공간이 없었으면 0을 만나서 끝날 수 없다. 따라서 끝에서부터 파괴된 구슬 개수만큼 0을 만들어줘야한다.
            if(!meetZero) {
                int start = n * n;
                for(int c = 1; c <= broken; c ++) {
                    board[poses[start - c].x][poses[start - c].y] = 0;
                }
            }

//            System.out.println("파괴 후");
//            printBoard();

//                        for(int x = 1; x < n * n; x++) {
//                System.out.println(x + " " + poses[x].x + " " + poses[x].y);
//            }

            // 구슬 4개 이상 있으면 파괴하기
            while(true) {
                cnt = 0;
                broken = 0;
                int curr = board[poses[1].x][poses[1].y];
                isDeleted = new boolean[n * n + 1];

                // 구슬 파괴하기
                for(int c = 1; c < n * n; c ++) {
                    if(curr == board[poses[c].x][poses[c].y]) {
                        cnt ++;
                    } else {
                        if(cnt >= 4) {
                            broken += cnt;
                            answer[curr] += cnt;
                            for(int z = 1; z <= cnt; z++) {
                                isDeleted[c - z] = true;
                            }
                        }
                        cnt = 1;
                        curr = board[poses[c].x][poses[c].y];
                    }
                }

                // 파괴한 구슬이 없으면 멈춘다.
                if(broken == 0) break;

                // 구슬 밀기
                broken = 0;
                meetZero = false;
                for(int b = 1; b < n * n; b ++) {
                    // 원래 구슬 없던 자리에 도달하면 앞에 파괴된 구슬만큼 빈자리로 만들어준다.
                    if(board[poses[b].x][poses[b].y] == 0) {
                        for(int c = 1; c <= broken; c ++) {
                            board[poses[b - c].x][poses[b - c].y] = 0;
                        }
                        meetZero = true;
                        break;
                    }
                    // 파괴된 구슬이면 broken을 증가시킨다.
                    if(isDeleted[b]) {
                        broken++;
                    } else {
                        // 파괴된 구슬이 아니면 지금까지 파괴된 구슬 개수만큼 앞으로 당긴다.
                        board[poses[b - broken].x][poses[b - broken].y] = board[poses[b].x][poses[b].y];
                    }
                }

                // 만약에 기존에 빈 공간이 없었으면 0을 만나서 끝날 수 없다. 따라서 끝에서부터 파괴된 구슬 개수만큼 0을 만들어줘야한다.
                if(!meetZero) {
                    int start = n * n;
                    for(int c = 1; c <= broken; c ++) {
                        board[poses[start - c].x][poses[start - c].y] = 0;
                    }
                }

//                System.out.println("폭발 후");
//                printBoard();
            }

            // 구슬 변화
            int[][] new_board = new int[n][n];
            int curr = board[poses[1].x][poses[1].y];
            cnt = 0;
            k = 1;
            for(int c = 1; c < n * n; c ++) {
                if(curr == board[poses[c].x][poses[c].y]) {
                    cnt ++;
                } else {
                    new_board[poses[k].x][poses[k].y] = cnt;
                    if(k + 1 >= n * n) {
                        break;
                    }
                    new_board[poses[k + 1].x][poses[k + 1].y] = curr;
                    if(k + 2 >= n * n) {
                        break;
                    }
                    k += 2;
                    curr = board[poses[c].x][poses[c].y];
                    cnt = 1;
                    if(curr == 0) {
                        break;
                    }
                }
            }

            board = new_board;

//            System.out.println("블리자드 후:");
//            printBoard();

        }

        System.out.println(answer[1] + answer[2] * 2 + answer[3] * 3);
    }

    private static void printBoard() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
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
