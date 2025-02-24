import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static int[][] cluster;
    static int[][] size;
    static int[][] answer;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cluster = new int[n][m];
        size = new int[n][m];
        answer = new int[n][m];
        for(int i = 0; i < n; i++) {
            String row = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        int cn = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] != 0 || cluster[i][j] != 0) continue;
                int cnt = makeCluster(i, j, cn, 0);
                makeCnt(i, j, cn, cnt);
                cn ++;
            }
        }
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < m; j++) {
//                bw.write(String.valueOf(cluster[i][j]));
//            }
//            bw.write("\n");
//        }
//        bw.write("\n");

//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < m; j++) {
//                bw.write(String.valueOf(size[i][j]));
//            }
//            bw.write("\n");
//        }
//        bw.write("\n");
        makeAnswer();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                bw.write(String.valueOf(answer[i][j]));
            }
            bw.write("\n");
        }
        bw.close();
    }

    private static int makeCluster(int x, int y, int cn, int cnt) {
        cluster[x][y] = cn;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || cluster[nx][ny] != 0 || map[nx][ny] != 0) {
                continue;
            }
            cnt += makeCluster(nx, ny, cn, 0);
        }
        return cnt + 1;
    }

    private static void makeCnt(int x, int y, int cn, int cnt) {
        size[x][y] = cnt;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || size[nx][ny] != 0 || cluster[nx][ny] != cn) {
                continue;
            }
            makeCnt(nx, ny, cn, cnt);
        }
    }

    private static void makeAnswer() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] != 0) {
                    int sum = 1;
                    List<Integer> visited = new ArrayList<>();
                    for(int k = 0; k < 4; k ++) {
                        int nx = i + dir[k][0];
                        int ny = j + dir[k][1];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited.contains(cluster[nx][ny])) {
                            continue;
                        }
                        sum += size[nx][ny];
                        visited.add(cluster[nx][ny]);
                    }
                    answer[i][j] = sum % 10;
                }
            }
        }
    }
}
