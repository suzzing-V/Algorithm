import java.util.*;
import java.io.*;

// 시간복잡도: 2 * 10^6 + 10^6 * 8
// 다음에 쓰러질 모래성을 큐에 넣고, while문 돌면서 전 턴에 추가된 모래성을 하나씩 뽑으면서 그 주변 모래성의 pot을 증가시킨다.
// 삭제할 모래성은 visited 처리를 해줘서 다시 큐에 들어가지 않도록 한다.
// 방문 처리를 해주지 않으면, 같은 턴에서 이미 삭제예정인 모래성을 또 큐에 넣을 가능성이 있다. 다음 턴에 삭제할 모래성에 .을 찍는 게 아니라 이번턴에 삭제할 모래성에 .을 찍는 것이기 때문에, 같은 턴에서 다음 턴의 모래성 삭제가 중복으로 발생할 경우, 확인할 수 없다.
// pot을 증가시켰을 때, pot에 저장된 값이 주변 모래성의 값보다 높거나 같으면 다음 턴에 삭제 예정이므로 큐에 넣는다.
public class Main {

    private static int h;
    private static int w;
    private static char[][] map;
    private static int[][] pots;
    private static Queue<Pos> sides = new LinkedList<>();
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        pots = new int[h][w];
        for(int i = 0; i < h; i++) {
            String line = bf.readLine();
            for(int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 쓰러질 모래성 저장
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == '.') continue;
                int cnt = countPot(i, j);
                pots[i][j] = cnt;
                // 가장자리면 저장
                if(cnt >= map[i][j] - '0') {
                    sides.add(new Pos(i, j));
//                    System.out.println(key);
                }
            }
        }

//        printPots();
        // 파도 횟수 세면서 모래성 삭제하기
        boolean[][] visited = new boolean[h][w];
        int wave = 0;
        while(!sides.isEmpty()) {
            wave ++;
            int qs = sides.size();

            for(int s = 0; s < qs; s++) {
                Pos curr = sides.remove();
                if(map[curr.x][curr.y] == '.') continue;
//                System.out.println(map[curr.x][curr.y] - '0');
                map[curr.x][curr.y] = '.';
                for(int i = 0; i < 8; i++) {
                    int nx = curr.x + dir[i][0];
                    int ny = curr.y + dir[i][1];

                    if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '.' || visited[nx][ny]) {
                        continue;
                    }

                    pots[nx][ny] ++;
                    if(pots[nx][ny] >= map[nx][ny] - '0') {
                        sides.add(new Pos(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
//            printMap();
//            printPots();
        }

        System.out.println(wave);
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void printMap() {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j< w; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    private static void printPots() {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j< w; j++) {
                System.out.print(pots[i][j]);
            }
            System.out.println();
        }
    }

    private static int countPot(int x, int y) {
        int cnt = 0;
        for(int i = 0; i < 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] != '.') continue;
            cnt ++;
        }
        return cnt;
    }
}
