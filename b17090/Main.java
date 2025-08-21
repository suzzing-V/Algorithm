import java.io.*;
import java.util.*;

public class Main {

    private static int[][] isOut;
    private static boolean[][] isVisited;
    private static char[][] map;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        isOut = new int[n][m];
        isVisited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                isOut[i][j] = -1;
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(canOutFrom(i, j)) {
                    cnt ++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static boolean canOutFrom(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) {
            return true;
        }
        // 이미 와봤다면 이 결과 반환
        if(isOut[x][y] != -1) {
            return isOut[x][y] == 1 ? true : false;
        }

        // 방문했었는데 isOut값이 없다면 돌고 있는 것이므로 false반환
        if(isVisited[x][y] && isOut[x][y] == -1) {
            isOut[x][y] = 0;
            return false;
        }

        isVisited[x][y] = true;
        boolean canOut = false;
        if(map[x][y] == 'U') {
            canOut = canOutFrom(x - 1, y);
        } else if(map[x][y] == 'D') {
            canOut = canOutFrom(x + 1, y);
        } else if(map[x][y] == 'L') {
            canOut = canOutFrom(x, y - 1);
        } else if(map[x][y] == 'R') {
        canOut = canOutFrom(x, y + 1);
    }
        if(canOut) {
            isOut[x][y] = 1;
        } else {
            isOut[x][y] = 0;
        }
        return canOut;
    }
}
