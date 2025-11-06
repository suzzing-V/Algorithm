import java.util.*;
import java.io.*;

// 시간복잡도: 1000 * 4000 + 1000 * 4000
// 한번에 최대한 많은 직사각형을 그려야한다. 그후 연필을 들고, 남은 직사각형을 그린다.
// 점과 선을 모두 좌표로 표현한다. 좌표값이 짝수면 점, 홀수면 선이다. 따라서 실제 좌표에 2를 곱하면 점과 선을 고려한 좌표가 나온다.
// 그후 음수값 보정을 위해 1000을 더해준다.
// 각 직사각형이 지나는 곳을 표시하고, 그 직사각형의 시작점을 map에 저장한다.
// 만약 (0, 0)을 지나는 직사각형이 아니면, 시작하기 전에 연필을 올려야하므로 cnt를 0부터 시작한다. 아닐 경우 -1부터 시작.
// map에 직사각형이 남을 때까지 시작점을 시작으로 bfs를 돌리면서 지나는 좌표가 남아있는 직사각형에 포함되는 좌표라면 그 좌표를 map에서 삭제한다.
// 한번 bfs를 돌때마다 펜을 올려준다. cnt ++

public class Main {

    // 짝수는 점, 홀수는 선
    private static boolean[][] rec = new boolean[2002][2002];
    private static Map<String, Integer> rest = new HashMap<>();
    private static int n;
    private static boolean start00 = false;
    private static int cnt = -1;
    private static boolean[][] isRec = new boolean[2002][2002];
    private static boolean[][] isVisited = new boolean[2002][2002];
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = 1000 + Integer.parseInt(st.nextToken()) * 2;
            int y1 = 1000 + Integer.parseInt(st.nextToken()) * 2;
            int x2 = 1000 + Integer.parseInt(st.nextToken()) * 2;
            int y2 = 1000 + Integer.parseInt(st.nextToken()) * 2;
            draw(x1, y1, x2, y2);
            if(isContains00(x1, y1, x2, y2)) start00 = true;
            String pos = x1 + " " + y1;
            rest.put(pos, 0);
            rec[x1][y1] = true;
        }

//        System.out.println(start00);
        if(!start00) cnt ++;
        while(!rest.isEmpty()) {
            cnt ++;
            String key = rest.keySet().iterator().next();
//            System.out.println("시작: " + key);
            StringTokenizer st = new StringTokenizer(key);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rest.remove(key);
            bfs(x, y);
        }

        System.out.println(cnt);
    }

    private static void draw(int x1, int y1, int x2, int y2) {
        for(int x = x1; x <= x2; x++) {
            isRec[x][y1] = true;
            isRec[x][y2] = true;
        }

        for(int y = y1; y <= y2; y++) {
            isRec[x1][y] = true;
            isRec[x2][y] = true;
        }
    }

    private static boolean isContains00(int x1, int y1, int x2, int y2) {
        if(x1 <= 1000 && x2 >= 1000 && y1 == 1000) return true;
        if(x1 <= 1000 && x2 >= 1000 && y2 == 1000) return true;
        if(y1 <= 1000 && y2 >= 1000 && x1 == 1000) return true;
        if(y1 <= 1000 && y2 >= 1000 && x2 == 1000) return true;
        return false;
    }

    private static void bfs(int sx, int sy) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(sx, sy));
        isVisited[sx][sy] = true;

        while(!queue.isEmpty()) {
            Pos curr = queue.remove();

//            System.out.println(curr.x + " " + curr.y);
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx > 2000 || ny < 0 || ny > 2000 || isVisited[nx][ny] || !isRec[nx][ny]) continue;

                String pos = nx + " " + ny;
                    rest.remove(pos);
                queue.add(new Pos(nx, ny));
                isVisited[nx][ny] = true;
            }
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
