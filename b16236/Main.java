import java.io.*;
import java.util.*;
public class Main {
    static int[][] space;
    static int n;
    static int sharkX;
    static int sharkY;
    static int body = 2;
    static int eat = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());

        space = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if(space[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        while(bfs());
        bw.write(String.valueOf(count));
        bw.close();
    }
    
    public static class Shark {
        int x;
        int y;
        int dist;

        Shark(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static boolean bfs() {
        boolean[][] visit = new boolean[n][n];
        Queue<Shark> queue = new LinkedList<>();
        int[][] go = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        queue.add(new Shark(sharkX, sharkY, 0));
        visit[sharkX][sharkY] = true;
        while(!queue.isEmpty()) {
            Shark now = queue.poll();

            System.out.println("x y: " + now.x + " " + now.y);
            for(int i = 0; i < 4; i++) {
                int goX = now.x + go[i][0];
                int goY = now.y + go[i][1];

                if(goX >= n || goX < 0 || goY >= n || goY < 0) continue;

                if(!visit[goX][goY]) {
                    if(space[goX][goY] < body && space[goX][goY] > 0) {
                        System.out.println("eat: " + now.dist);
                        space[goX][goY] = 0;
                        eat ++;
                        if(eat >= body) {
                            body ++;
                            eat = 0;
                        }
                        count += now.dist + 1;
                        sharkX = now.x;
                        sharkY = now.y;
                        return true;
                    } else if(space[goX][goY] == 0 || space[goX][goY] == body) {
                        System.out.println("pass: " + now.dist);
                        queue.add(new Shark(goX, goY, now.dist + 1));
                        visit[goX][goY] = true;
                    } 
                }
            }
        }
        return false;
    }
}
