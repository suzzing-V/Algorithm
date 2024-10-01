import java.io.*;
import java.util.*;

public class Main {
    static String[][] map;
    static boolean[] key = new boolean[26];
    static int doc = 0;
    static int h;
    static int w;
    static Queue<Pos>[] doors = new Queue[26];
    static boolean[][] visited;

    static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int k = 0; k < t; k++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new String[h][w];
            doc = 0;
            key = new boolean[26];
            for (int i = 0; i < h; i++) {
                String line = bf.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = String.valueOf(line.charAt(j));
                }
            }
            String keys = bf.readLine();
            for(int i = 0; i < keys.length(); i++) {
                String next = String.valueOf(keys.charAt(i));
                if(!next.equals("0")) {
                    key[next.charAt(0) - 'a'] = true;
                }
            }
            for (int i = 0; i < 26; i++) {
                doors[i] = new LinkedList<>();
            }
            visited = new boolean[h][w];

            for (int i = 0; i < w; i++) {
                bfs(0, i);
                bfs(h - 1, i);
            }
            for (int i = 0; i < h - 1; i++) {
                bfs(i, 0);
                bfs(i, w - 1);
            }
            System.out.println(doc);
        }
    }

    public static void bfs(int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x, y));

        while(!queue.isEmpty()) {
            Pos now = queue.remove();
            if(now.x < 0 || now.x > h - 1 || now.y < 0 || now.y > w - 1 || visited[now.x][now.y] || map[now.x][now.y].equals("*")) {
                continue;
            }
            if(map[now.x][now.y].charAt(0) >= 'A' && map[now.x][now.y].charAt(0) <= 'Z') {
                if(!key[map[now.x][now.y].charAt(0) - 'A']) {
                    doors[map[now.x][now.y].charAt(0) - 'A'].add(new Pos(now.x, now.y));
                    continue;
                }
            }
            visited[now.x][now.y] = true;
            if(map[now.x][now.y].charAt(0) >= 'a' && map[now.x][now.y].charAt(0) <= 'z') {
                key[map[now.x][now.y].charAt(0) - 'a'] = true;
                while(!doors[map[now.x][now.y].charAt(0) - 'a'].isEmpty()) {
                    Pos door = doors[map[now.x][now.y].charAt(0) - 'a'].remove();
                    queue.add(door);
                }
                map[now.x][now.y] = ".";
            }
            if(map[now.x][now.y].equals("$")) {
                doc ++;
                map[now.x][now.y] = ".";
            }

            queue.add(new Pos(now.x - 1, now.y));
            queue.add(new Pos(now.x + 1, now.y));
            queue.add(new Pos(now.x, now.y - 1));
            queue.add(new Pos(now.x, now.y + 1));
        }
    }
}
