import java.util.*;
import java.io.*;

public class Main {

    private static int[][] dir = { {0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    private static int max = 0;
    private static Fish[][] space = new Fish[4][4];
    private static Fish[] pos = new Fish[17];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                space[i][j] = new Fish(n, d, i, j);
                pos[n] = new Fish(n, d, i, j);
            }
        }

        int init_n = space[0][0].n;
        int init_d = space[0][0].d;
        space[0][0] = null;
        pos[init_n] = null;
        dfs(0, 0, init_d, init_n);
        System.out.println(max);
    }

    private static void dfs(int x, int y, int d, int sum) {
//        System.out.println("상어: " + x + " " + y + " "+ d + " " + sum);
        // 현재 칸의 물고기 먹고 방향 그 물고기 방향으로 바꾸기


//        System.out.println("전");
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 4; j++) {
//                if(space[i][j] != null) System.out.print(space[i][j].n + " ");
//                else System.out.print("x ");
//            }
//            System.out.println();
//        }
//                for(int i = 1; i <= 16; i ++) {
//            if(pos[i] != null) System.out.print(pos[i].x + " " + pos[i].y + " ");
//            else System.out.println("xx");
//        }
//        System.out.println();

        // 기존 꺼 저장
        Fish[][] origin_space = new Fish[4][4];
        Fish[] origin_pos = new Fish[17];
        for(int a = 0; a < 4; a++) {
            for(int b = 0; b < 4; b++) {
                if(space[a][b] != null) origin_space[a][b] = new Fish(space[a][b].n, space[a][b].d, space[a][b].x, space[a][b].y);
            }
        }
        for(int a = 1; a <= 16; a++) {
            if(pos[a] != null) origin_pos[a] = new Fish(pos[a].n, pos[a].d, pos[a].x, pos[a].y);
        }

        // 물고기 이동
        for(int i = 1; i <= 16; i ++) {
            // 이미 잡아 먹혔으면 넘어가기
            if(pos[i] == null) continue;

            Fish fish = pos[i];
//            System.out.println("물고기 이동: " + i + fish.x + " " + fish.y);
            // 반시계방향으로 돌려가면서 그 칸에 물고기가 있거나 빈칸이면 가기. 경계 넘거나 상어 있으면 못감
            for(int j = 0; j <= 8; j++) {
                int nd = (fish.d + j) % 8 == 0 ? 8 : (fish.d + j) % 8;
                int nx = fish.x + dir[nd][0];
                int ny = fish.y + dir[nd][1];

                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == x && ny == y)) {
                    continue;
                }
                fish.d = nd;
                // 다음 칸에 물고기 있을 경우 바꾸기
                if(space[nx][ny] != null) {
//                    System.out.println("다음 물고기 있음: " + nx + " " + ny + " " + space[nx][ny].n);
                    Fish next = space[nx][ny];
                    int cx = fish.x;
                    int cy = fish.y;
                    int next_d = next.d;
                    int next_n = next.n;
                    space[nx][ny] = new Fish(i, nd, nx, ny);
                    space[cx][cy] = new Fish(next_n, next_d, cx, cy);
                    pos[i].x = nx;
                    pos[i].y = ny;
                    pos[next_n].x = cx;
                    pos[next_n].y=  cy;
                    // 없을 경우 이동
                } else {
                    space[nx][ny] = new Fish(fish.n, nd, nx, ny);
                    space[fish.x][fish.y] = null;
                    pos[i].x = nx;
                    pos[i].y = ny;
                }
                break;
            }
//            for(int a = 0; a < 4; a++) {
////            for(int b = 0; b < 4; b++) {
////                if(space[a][b] != null) System.out.print(space[a][b].n + " ");
////                else System.out.print("x ");
////            }
////            System.out.println();
////        }
//
        }
//
//        System.out.println("후");
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 4; j++) {
//                if(space[i][j] != null) System.out.print(space[i][j].n + " ");
//                else System.out.print("x ");
//            }
//            System.out.println();
//        }
//        for(int i = 1; i <= 16; i ++) {
//            if(pos[i] != null) System.out.print(pos[i].x + " " + pos[i].y + " ");
//            else System.out.println("xx");
//        }
//        System.out.println();

        // 상어 이동
        boolean flag = false;
        for(int k = 1 ; k <= 3; k++) {
            int sx = x + dir[d][0] * k;
            int sy = y + dir[d][1] * k;
            if(sx >= 0 && sx <= 3 && sy >= 0 && sy <= 3 && space[sx][sy] != null) {
                flag = true;
                int sn = space[sx][sy].n;
                int sd = space[sx][sy].d;
                space[sx][sy] = null;
                pos[sn] = null;
                dfs(sx, sy, sd, sum + sn);
                space[sx][sy] = new Fish(sn, sd, sx, sy);
                pos[sn] = new Fish(sn, sd, sx, sy);
            }
        }
        if(!flag) {
            max = Math.max(max, sum);
        }

        // 맵 원래대로 되돌리기
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(origin_space[i][j] != null) space[i][j] = new Fish(origin_space[i][j].n, origin_space[i][j].d, origin_space[i][j].x, origin_space[i][j].y);
                else space[i][j] = null;
            }
        }
        for(int i = 1; i <= 16; i++) {
            if(origin_pos[i] != null) pos[i] = new Fish(origin_pos[i].n, origin_pos[i].d, origin_pos[i].x, origin_pos[i].y) ;
            else pos[i] = null;
        }
    }

    private static class Fish {
        int n;
        int d;
        int x;
        int y;

        Fish(int n, int d, int x, int y) {
            this.n = n;
            this.d = d;
            this.x = x;
            this.y = y;
        }
    }
}
