import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][] group;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<Integer> cnts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        group = new int[n][n];
        for(int i = 0; i < n; i++) {
            String row = bf.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        int num = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] != 1 || group[i][j] != 0) continue;
                cnts.add(countGroup(i, j, num));
                num ++;
            }
        }

        bw.write(cnts.size() + "\n");
        Collections.sort(cnts);
        for(int cnt : cnts) {
            bw.write(cnt + "\n");
        }
        bw.close();
    }

    private static int countGroup(int x, int y, int num) {
        int cnt = 1;
        group[x][y] = num;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 0 || group[nx][ny] != 0) {
                continue;
            }
            cnt += countGroup(nx, ny, num);
        }
        return cnt;
    }
}
