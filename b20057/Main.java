import java.util.*;
import java.io.*;

// 시간복잡도: 499 * 499 * 25 * 5
public class Main {

    private static int[][] a;
    private static int n;
    private static int out = 0;
    private static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static double[][] percent = {
        {0, 0, 0.02, 0, 0},
        {0, 0.1, 0.07, 0.01, 0},
        {0.05, 0, 0, 0, 0},
        {0, 0.1, 0.07, 0.01, 0},
        {0, 0, 0.02, 0, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        a = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0;j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int center = n / 2;
        tornado(center, center, 0, 1);
        System.out.println(out);
    }

    private static void tornado(int x, int y, int d, int move) {
        if(x == 0 && y == 0) return;

        double[][] rotated = rotatedPercent(d);
        // 토네이도가 이동할 좌표
        for(int i = 1; i <= move; i++) {
            int nx = x + dir[d][0] * i;
            int ny = y + dir[d][1] * i;
            // 토네이도가 격자 밖으로 나가면 소멸
            if(ny < 0) return;

            int sand = a[nx][ny];
            // y칸에 있는 모래 다 날아감
            a[nx][ny] = 0;
            blowSand(nx, ny, sand, rotated, d);
        }

        // 다음 방향으로 이동
        int nd = d == 3 ? 0 : d + 1;
        int nx = x + dir[d][0] * move;
        int ny = y + dir[d][1] * move;
        if(d == 1 || d == 3) move ++;
        tornado(nx, ny, nd, move);
    }

    private static void blowSand(int x, int y, int sand, double[][] rotated, int d) {
        int blow = 0;
        for(int i = 0; i <= 4; i++) {
            for(int j = 0; j <= 4; j++) {
                // 소수점 아래 버림
                int add = (int)Math.floor((double) sand * rotated[i][j]);
                // 적용할 격자 칸
                int bx = x - 2 + i;
                int by = y - 2 + j;

                // 격자를 벗어나는 경우 모래 양 카운트
                if(bx < 0 || bx >= n || by < 0 || by >= n) {
                    out += add;
                    // 벗어나지 않는 경우 격자칸에 모래 양 더하기
                } else {
                    a[bx][by] += add;
                }
                blow += add;
            }
        }

        // 남은 모래 a칸에 더하기
        int ax = x + dir[d][0];
        int ay = y + dir[d][1];
        // sand를 건드리면 안된다. 모래는 동시에 날아가는데 sand를 날아갈때마다 갱신해주면 원래 모래의 비율이 아닌 차감된 후 모래 양의 비율이 나온다.
        int rest = sand - blow;
        if(ax < 0 || ax >= n || ay < 0 || ay >= n) out += rest;
        else a[ax][ay] += rest;
    }

    private static double[][] rotatedPercent(int d) {
        double[][] rotated = new double[5][5];
        if(d == 0) {
            copy(percent, rotated);
        } else if(d == 1) {
            rotateReverseClock(percent, rotated);
        } else if(d == 2) {
            rotateReverseClock(percent, rotated);
            double[][] tmp = new double[5][5];
            copy(rotated, tmp);
            rotateReverseClock(tmp, rotated);
        } else {
            rotateReverseClock(percent, rotated);
            double[][] tmp = new double[5][5];
            copy(rotated, tmp);
            rotateReverseClock(tmp, rotated);
            copy(rotated, tmp);
            rotateReverseClock(tmp, rotated);
        }

//        for(int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                System.out.print(rotated[i][j] + " ");
//            }
//            System.out.println();
//        }

        return rotated;
    }

    private static void rotateReverseClock(double[][] origin, double[][] target) {
        for(int j = 4; j >= 0; j --) {
            for(int i = 0; i < 5; i++) {
                target[4 - j][i] = origin[i][j];
            }
        }
    }

    private static void copy(double[][] origin, double[][] target) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                target[i][j] = origin[i][j];
            }
        }
    }
}
