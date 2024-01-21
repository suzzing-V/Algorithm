import java.io.*;
import java.util.*;

public class Main {
    static int[][][] box;
    static int countFirstRiped = 0;
    static int countAll = 0;
    static int m;
    static int n;
    static int h;

    public static class Tomato {
        int x;
        int y;
        int h;
        int day;
        
        Tomato(int x, int y, int h, int day) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.day = day;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        box = new int[n][m][h];
        Queue<Tomato> queue = new LinkedList<>();
        for(int k = 0; k < h; k++) {
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                int status = Integer.parseInt(st.nextToken());
                    box[i][j][k] = status;
                    countAll++;
                    if(status == 1) {
                        queue.add(new Tomato(i, j, k, 0));
                        countFirstRiped++;
                    }
            }
        }
        }

        int result = 0;
        if(countFirstRiped == countAll) {
            result = 0;
        } else if(countFirstRiped == 0) {
            result = -1;
        } else {
            result = bfs(queue);
        }
        bw.write(String.valueOf(result));
        bw.close();
    }

    public static int bfs(Queue<Tomato> queue) {
        int[][] dir = { {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1} };
        int days = 0;

        while(!queue.isEmpty()) {
            Tomato now = queue.poll();
            days = now.day;

            for(int i = 0; i < 6; i++) {
                int goX = now.x + dir[i][0];
                int goY = now.y + dir[i][1];
                int goH = now.h + dir[i][2];
                
                if(goX < 0 || goX >= n || goY < 0 || goY >= m || goH < 0 || goH >= h || box[goX][goY][goH] == -1 || box[goX][goY][goH] == 1) continue;
                box[goX][goY][goH] = 1;
                queue.add(new Tomato(goX, goY, goH, now.day + 1));
            }
        }
        if(!checkAllRiped()) {
            return -1;
        }
        return days;
    }

    public static boolean checkAllRiped() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < h; k++) {
                    if(box[i][j][k] == 0) return false;
                }
            }
        }
        return true;
    }
}