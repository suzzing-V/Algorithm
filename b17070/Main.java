import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] room;
    static int count;

    public static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        room = new int[n + 1][n + 1];
        for(int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j < n + 1; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new Pos(1, 1), new Pos(1, 2));
        bw.write(String.valueOf(count));
        bw.close();
    }

    public static void dfs(Pos start, Pos end) {
        if(end.x > n || end.y > n || end.x <= 0 || end.y <= 0) {
            return;
        }

        if(end.x == n && end.y == n) {
            count++;
            return;
        }

        Pos newStart = new Pos(end.x, end.y);
        if(start.x - end.x == 0 && end.y - start.y == 1) {
            if(end.y + 1 <= n && room[end.x][end.y + 1] == 0)
                dfs(newStart, new Pos(end.x, end.y + 1));
            if(end.y + 1 <= n && end.x + 1 <= n && room[end.x + 1][end.y + 1] == 0
                    && room[end.x + 1][end.y] == 0
                    && room[end.x][end.y + 1] == 0)
                dfs(newStart, new Pos(end.x + 1, end.y + 1));
        }

        if(end.x - start.x == 1 && start.y - end.y == 0) {
            if(end.x + 1 <= n && room[end.x + 1][end.y] == 0) {
                dfs(newStart, new Pos(end.x + 1, end.y));
            }
            if(end.y + 1 <= n && end.x + 1 <= n && room[end.x + 1][end.y + 1] == 0
                    && room[end.x + 1][end.y] == 0
                    && room[end.x][end.y + 1] == 0)
                dfs(newStart, new Pos(end.x + 1, end.y + 1));
        }

        if(end.x - start.x == 1 && end.y - start.y == 1) {
            if(end.y + 1 <= n && room[end.x][end.y + 1] == 0) {
                dfs(newStart, new Pos(end.x, end.y + 1));
            }
            if(end.x + 1 <= n && room[end.x + 1][end.y] == 0) {
                dfs(newStart, new Pos(end.x + 1, end.y));
            }
            if(end.y + 1 <= n && end.x + 1 <= n && room[end.x + 1][end.y + 1] == 0
                    && room[end.x + 1][end.y] == 0
                    && room[end.x][end.y + 1] == 0)
                dfs(newStart, new Pos(end.x + 1, end.y + 1));
        }
    }
}
