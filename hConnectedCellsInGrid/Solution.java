import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
     * Complete the 'connectedCell' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int connectedCell(List<List<Integer>> matrix) {
        n = matrix.size();
        m = matrix.get(0).size();
        map = new int[n][m];
        visited = new boolean[n][m];
        int max = 0;

        for(int i = 0; i < n; i++) {
            List<Integer> row = matrix.get(i);
            for(int j = 0; j < m; j++) {
                map[i][j] = row.get(j);
            }
        }

        // Write your code here
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(bfs(i, j), max);
                }
            }
        }
        return max;
    }

    private static int bfs(int startX, int startY) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(startX, startY));

        int cnt = 0;
        while(!queue.isEmpty()) {
            Pos now = queue.remove();
            // System.out.println(now.x + " " + now.y);

            if(visited[now.x][now.y]) {
                continue;
            }
            visited[now.x][now.y] = true;
            cnt++;

            for(int i = 0; i < 8; i++) {
                int nxtX = now.x + dir[i][0];
                int nxtY = now.y + dir[i][1];

                if(nxtX < 0 || nxtX >= n || nxtY < 0 || nxtY >= m ||map[nxtX][nxtY] == 0) {
                    continue;
                }
                queue.add(new Pos(nxtX, nxtY));
            }
        }

        // System.out.println(cnt);
        return cnt;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] matrixRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> matrixRowItems = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowTempItems[j]);
                matrixRowItems.add(matrixItem);
            }

            matrix.add(matrixRowItems);
        }

        int result = Result.connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

