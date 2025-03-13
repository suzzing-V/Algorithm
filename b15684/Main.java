import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int h;
    private static boolean[][] visited;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        visited = new boolean[h + 1][n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visited[a][b] = true;
        }

        dfs(1, 0); // 놓을 수 있는 사다리 조합 구하기
        if(min == Integer.MAX_VALUE) min = -1;
        bw.write(String.valueOf(min));
        bw.close();
    }

    private static void dfs(int row, int cnt) {
        if(cnt == 4) {
            return;
        }

        if(isPossible()) { // 모든 i가 i에 도착하면 최소값 갱신
            min = Math.min(min, cnt);
            return;
        }

        for(int i = row; i <= h; i++) {
            for(int j = 1; j < n; j++) {
                if(visited[i][j] || visited[i][j - 1] || visited[i][j + 1]) continue; // 이미 놓아져 있거나, 인접한 곳에 있으면 놓을 수 없음

                visited[i][j] = true;
                dfs(i, cnt + 1);
                visited[i][j] = false;
            }
        }
    }

    private static boolean isPossible() {
        for(int i = 1; i <= n; i++) {
            int curr = i;
            for(int j = 1; j <= h; j++) {
                if(visited[j][curr]) curr ++;
                else if(visited[j][curr - 1]) curr --;
            }
            if(curr != i) return false;
        }
        return true;
    }
}
