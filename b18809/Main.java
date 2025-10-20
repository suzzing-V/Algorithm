import java.util.*;
import java.io.*;

// 같은 턴에 같은 색이 확산될 수도 있다. 이 경우를 생각해주지 못해서 틀렸다 ㅠㅠ
// red, green 배양액을 선택하는 경우를 모두 구하고, 각각의 경우에서 배양액을 bfs로 확산시키면서 꽃의 수를 센다.
// 시간 복잡도: 3^10 * 2500
public class Main {

    private static int[][] map;
    private static int[][] red_map;
    private static int[][] green_map;
    private static int[][] red_turn;
    private static int[][] green_turn;
    private static int n;
    private static int m;
    private static int g;
    private static int r;
    private static List<Pos> can = new ArrayList<>();
    private static int max = 0;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        red_map = new int[n][m];
        green_map = new int[n][m];
        red_turn = new int[n][m];
        green_turn = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    can.add(new Pos(i, j));
                }
            }
        }

        // 초록색과 빨간색 배양액 뿌릴 위치 조합 정하기
        dfs(0, new ArrayList<>(), new ArrayList<>());


        System.out.println(max);
    }

    private static void dfs(int curr, List<Integer> green_pos, List<Integer> red_pos) {
        if(green_pos.size() > g || red_pos.size() > r || (can.size() - curr) + green_pos.size() + red_pos.size() < r + g) return;
        if(curr == can.size()) {
//            if(green_pos.toString().equals("[0, 2, 6]") && red_pos.toString().equals("[1, 4]")) {
            if(green_pos.size() == g && red_pos.size() == r) {
                // 이 위치를 시작으로 배양액 뿌린다.
//                System.out.println(green_pos.toString() + red_pos.toString());
                int flower = bfs(green_pos, red_pos);
                max = Math.max(max, flower);
            }
            return;
        }

        // 현재 위치에 초록색 뿌림
        green_pos.add(curr);
        dfs(curr + 1, green_pos, red_pos);
        green_pos.remove(green_pos.size() - 1);

        // 현재 위치에 빨간색 뿌림
        red_pos.add(curr);
        dfs(curr + 1, green_pos, red_pos);
        red_pos.remove(red_pos.size() - 1);

        // 둘다 안 뿌림
        dfs(curr + 1, green_pos, red_pos);
    }

    private static int bfs(List<Integer> green, List<Integer> red) {
        Queue<Node> queue = new ArrayDeque<>();
        green_turn = new int[n][m];
        red_turn = new int[n][m];
        for(int idx : green) {
            Pos pos = can.get(idx);
            green_turn[pos.x][pos.y] = 1;
            queue.add(new Node(pos.x, pos.y, 2, 0));
        }

        for(int idx : red) {
            Pos pos = can.get(idx);
            red_turn[pos.x][pos.y] = 1;
            queue.add(new Node(pos.x, pos.y, 2, 1));
        }

        int flower = 0;
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.x + " " + curr.y + " " + curr.turn + " " + curr.color);

            // 이미 꽃이 되어버렸으면 퍼뜨리지 않는다.
            if(green_turn[curr.x][curr.y] == -1) continue;
            for(int i = 0;i  < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(isOutOfMap(nx, ny)) continue;
                // 이전 턴에서 생긴 배양액이 존재하면 뿌리지 못한다.
                if((green_turn[nx][ny] < curr.turn && green_turn[nx][ny] > 0) || (red_turn[nx][ny] < curr.turn && red_turn[nx][ny] > 0)) continue;
                // 호수면 뿌리지 못한다.
                if(map[nx][ny] == 0) continue;
                // 꽃이 존재하면 뿌리지 못한다.
                if(green_turn[nx][ny] == -1) continue;

                // 현재 턴에서 생긴 다른 색의 배양액이 존재하면 꽃을 피운다.
                if((curr.color == 0 && red_turn[nx][ny] == curr.turn) || (curr.color == 1 && green_turn[nx][ny] == curr.turn)) {
                    red_turn[nx][ny] = -1;
                    green_turn[nx][ny] = -1;
                    flower ++;
                } else if(green_turn[nx][ny] == 0 && red_turn[nx][ny] == 0) {
                    // 비어있을 경우 그냥 배양액 뿌린다.
                    if(curr.color == 0) green_turn[nx][ny] = curr.turn;
                    else if(curr.color == 1) red_turn[nx][ny] = curr.turn;
                    queue.add(new Node(nx, ny, curr.turn + 1, curr.color));
                }
            }

//            printMap(green_turn);
//            printMap(red_turn);
        }

//        if(flower == 6) System.out.println(green.toString() + " " + red.toString());
        return flower;
    }

    private static class Node {
        int x;
        int y;
        int turn;
        int color;

        Node(int x, int y, int turn, int color) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.color = color;
        }
    }

    private static void printMap(int[][] tmap) {
        for(int i = 0; i < n; i++) {
            for(int j =0; j < m; j++) {
                System.out.print(tmap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isOutOfMap(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) return true;
        return false;
    }

    private static void spread(int mark, int[][] tmp, int[][] turn) {
        for(int i = 0; i < can.size(); i ++) {
            if((mark & (1 << i)) != 0) {
                Pos pos = can.get(i);
                tmp[pos.x][pos.y] = 1;
                turn[pos.x][pos.y] = 1;
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