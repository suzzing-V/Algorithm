import java.util.*;
import java.io.*;

// 시간복잡도: 2500 + 2500 + 2500
// 높이가 높은 칸부터 bfs탐색을 한다. 탐색 시작 높이보다 작거나 같은 높이의 칸으로만 가면서, 격자를 벗어나는 경우가 없으면 그 영역에 물을 채울 수 있다는 표시를 한다.
// 만약 격자를 벗어난다면, 그 영역의 같은 높이의 칸들도 물을 채우지 못한다. 하지만 탐색 높이보다 작은 칸은 누수가 일어나지 않을 수 있다. 따라서 탐색한 칸 중 탐색 높이와 같은 칸을 제외한 칸은 다시 방문 표시를 해제한다.
// 이렇게 물을 채울 수 있는 칸인지 표시하고, 칸을 순회하면서 물을 채울 수 있는 칸이면 bfs로 영역을 구하고, 탐색하면서 만난 벽들 중 가장 낮은 벽의 높이로 물을 채운다.
// 최대한 중복 방문을 하지 않도록 하자.
public class Main {

    private static int n;
    private static int m;
    private static int[][] map;
    private static boolean[][] visited;
    private static boolean[][] canFilled;
    private static PriorityQueue<Pos> poses = new PriorityQueue<>();
    private static int result = 0;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        canFilled = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0;j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
                poses.add(new Pos(i, j, map[i][j]));
            }
        }

        // 누수 체크하기
        while(!poses.isEmpty()) {
            Pos curr = poses.remove();

            if(visited[curr.x][curr.y]) {
                continue;
            }

            bfs(curr);
        }

        // 누수 안되는 칸들 물 채우기
        visited = new boolean[n][m];
        for(int i = 0; i < n ; i ++) {
            for(int j= 0; j < m; j++) {
                if(visited[i][j] || !canFilled[i][j]) continue;
                result += fillWater(i, j);
            }
        }

        System.out.println(result);
    }

    private static int fillWater(int sx, int sy) {
        Queue<Pos> queue = new LinkedList<>();
        visited[sx][sy] = true;
        Pos start = new Pos(sx, sy, map[sx][sy]);
        queue.add(start);
        List<Pos> v = new ArrayList<>();
        v.add(start);

        // 둘러싸고 있는 가장 낮은 벽
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Pos curr = queue.remove();

//            System.out.println(curr.x + " "+ curr.y + " "+ curr.h);

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                // 누수 안되는 영역만 있으므로 격자를 벗어날 일은 없다. 방문한 곳은 넘어간다.
                if(visited[nx][ny]) continue;
                // 누수된 곳이면 최소 벽 갱신하고 넘어간다.
                if(!canFilled[nx][ny]) {
                    min = Math.min(min, map[nx][ny]);
                    continue;
                }

                Pos next = new Pos(nx, ny, map[nx][ny]);
                queue.add(next);
                visited[nx][ny] = true;
                v.add(next);
            }
        }

//        printLeaked();

        // 영역에 있는 칸들 (최소 벽 - 칸 높이) 만큼 물 채운다.
        int sum = 0;
        for(int i = 0; i < v.size(); i++) {
            Pos pos = v.get(i);
            sum += (min - map[pos.x][pos.y]);
        }

        queue.clear();
        return sum;
    }

    private static void printLeaked() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(canFilled[i][j]) System.out.print("1");
                else System.out.print("0");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(Pos start) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(start);
//        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;
        List<Pos> v = new ArrayList<>();
        v.add(start);
        // 누수되는 벽의 최솟값
        boolean leak = false;

//        System.out.println(start.h + ": " + start.x + ", " + start.y);
        while(!queue.isEmpty()) {
            Pos curr = queue.remove();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                // 격자 벗어나면 누수. 멈춘다.
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    leak = true;
                    break;
                }
                // 현재 높이보다 크면 넘어간다.
                if(map[nx][ny] > curr.h) {
                    continue;
                }
                // 이미 방문했으면 탐색 멈춘다.
                if(visited[nx][ny]) continue;

                // 현재 높이랑 같거나 작으면 탐색 진행
                if(start.h >= map[nx][ny]) {
                    Pos next = new Pos(nx, ny, curr.h);
                    queue.add(next);
                    visited[nx][ny] = true;
                    v.add(next);
                }
            }
        }

        // 만약 누수되는 곳이 없으면 이 영역에 물 채울 수 있다.
        if(!leak) {
            for(int i = 0; i < v.size(); i++) {
                Pos pos = v.get(i);
                canFilled[pos.x][pos.y] = true;
            }
//            System.out.println("누수");
        } else {
            for(int i = 0; i < v.size(); i++) {
                Pos pos = v.get(i);
                if(map[pos.x][pos.y] != start.h) visited[pos.x][pos.y] = false;
            }
        }
    }

    private static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int h;

        Pos(int x, int y, int h) {
            this.x = x;
            this.y =y;
            this.h = h;
        }

        @Override
        public int compareTo(Pos p) {
            return p.h - this.h;
        }
    }
}
