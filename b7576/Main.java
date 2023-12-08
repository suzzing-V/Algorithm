import java.io.*;
import java.util.*;

public class Main {
    static int[][] box;
    static int countAll = 0;
    static int countFirstRiped = 0;
    static int m, n;

    public static class Tomato {
        int x;
        int y;
        int day;

        Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        Queue<Tomato> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                int status = Integer.parseInt(st.nextToken());
                box[i][j] = status;
                countAll++;
                if(status == 1) {
                    queue.add(new Tomato(i, j, 0));
                    countFirstRiped++;
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
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        int days = 0;

        while(!queue.isEmpty()) {
            Tomato now = queue.poll();
            days = now.day;

            for(int i = 0; i < 4; i++) {
                int goX = now.x + dir[i][0];
                int goY = now.y + dir[i][1];
                
                if(goX < 0 || goX >= n || goY < 0 || goY >= m || box[goX][goY] == -1 || box[goX][goY] == 1) continue;
                box[goX][goY] = 1;
                queue.add(new Tomato(goX, goY, now.day + 1));
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
                    if(box[i][j] == 0) return false;
            }
        }
        return true;
    }
}