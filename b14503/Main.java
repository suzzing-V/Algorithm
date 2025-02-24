import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int r;
    static int c;
    static int d;
    static int[][] room;
    static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        room = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = clean(r, c, d, 0);
        bw.write(String.valueOf(result));
        bw.close();
    }

    private static int clean(int x, int y, int d, int cnt) {
        if(room[x][y] == 0) {
            room[x][y] = -1;
            cnt ++;
        }

        boolean isMess = false;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(room[nx][ny] == 0) {
                isMess = true;
                break;
            }
        }

        if(!isMess) { // 청소되지 않은 빈칸이 없는 경우
            int nd = d;
            if(d == 0) nd = 2;
            else if(d == 1) nd = 3;
            else if(d == 2) nd = 0;
            else if(d == 3) nd = 1;
            int bx = x + dir[nd][0];
            int by = y + dir[nd][1];
            if(bx < 0 || bx >= n || by < 0 || by >= m || room[bx][by] == 1) {
                return cnt;
            }
            return clean(bx, by, d, cnt);
        } else { // 청소되지 않은 빈칸이 있는 경우
            d -= 1;
            if(d == -1) d = 3;
            int fx = x + dir[d][0];
            int fy = y + dir[d][1];
            if(fx >= 0 && fx < n && fy >= 0 && fy < m && room[fx][fy] == 0) {
                x = fx;
                y = fy;
            }
            return clean(x, y, d, cnt);
        }
    }
}
