import java.io.*;
import java.util.*;

// 2^10 * 10 * 20
public class Main {

    private static boolean[][] check = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        // 좌표 체크
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            makeDragon(x, y, d, g);
        }

        int cnt = 0;
        for(int i = 0; i < 100; i ++) {
            for(int j = 0; j < 100; j ++) {
                if(check[i][j] && check[i + 1][j] && check[i + 1][j + 1] && check[i][j + 1]) cnt ++;
            }
        }

        System.out.println(cnt);
    }

    private static void makeDragon(int x, int y, int d, int g) {
        List<Pos> poses = new ArrayList<>();
        check[x][y] = true;
        poses.add(new Pos(x, y));

        // 두번째 좌표 설정
        int nx = x;
        int ny = y;
        if(d == 0) {
            nx ++;
        } else if(d == 1) {
            ny --;
        } else if (d == 2) {
            nx --;
        } else if(d == 3) {
            ny ++;
        }
        check[nx][ny] = true;
        poses.add(new Pos(nx, ny));

//        System.out.println(nx + " " + ny);
        for(int i = 1; i <= g; i++) {
            int tx = nx;
            int ty = ny;
            int size = poses.size() - 1;
//            System.out.println("nx ny: " + nx + " " + ny);
            // 90도 돌린 값 리스트에 넣고 체크
            for(int j = size - 1; j >= 0; j--) {
                Pos pos = poses.get(j);
                if(pos.x <= nx && pos.y <= ny) {
                    tx = nx + Math.abs(ny - pos.y);
                    ty = ny - Math.abs(nx - pos.x);
                } else if(pos.x >= nx && pos.y <= ny) {
                    tx = nx + Math.abs(ny - pos.y);
                    ty = ny + Math.abs(nx - pos.x);
                } else if(pos.x >= nx && pos.y >= ny) {
                    tx = nx - Math.abs(ny - pos.y);
                    ty = ny + Math.abs(nx - pos.x);
                } else if(pos.x <= nx && pos.y >= ny) {
                    tx = nx - Math.abs(ny - pos.y);
                    ty = ny - Math.abs(nx - pos.x);
                }

//                System.out.println(tx + " " + ty);
                poses.add(new Pos(tx, ty));
                check[tx][ty] = true;
            }

            // 끝점 갱신
            nx = tx;
            ny = ty;

        }
//        System.out.println("완성: " + nx + " " + ny);
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
