import java.util.*;
import java.io.*;

// 시간복잡도: 25 * 25 * 24
public class Main {

    private static int r;
    private static int c;
    private static char[][] map;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int sx;
    private static int sy;
    private static Map<Integer, List<Integer>> hole = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r + 1][c + 1];
        for(int i = 1; i <= r; i++) {
            String line = bf.readLine();
            for(int j = 1; j <= c; j++) {
                map[i][j] = line.charAt(j - 1);
                if(map[i][j] == 'M') {
                    sx = i;
                    sy = j;
                }
            }
        }

        // 각 파이프가 어느 쪽으로 뚫려있는지 저장
        hole.put((int)'|', new ArrayList<>(Arrays.asList(0, 2)));
        hole.put((int)'-', new ArrayList<>(Arrays.asList(1, 3)));
        hole.put((int)'+', new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        hole.put((int)'M', new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        hole.put((int)'Z', new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        hole.put((int)'1', new ArrayList<>(Arrays.asList(0, 1)));
        hole.put((int)'2', new ArrayList<>(Arrays.asList(1, 2)));
        hole.put((int)'3', new ArrayList<>(Arrays.asList(2, 3)));
        hole.put((int)'4', new ArrayList<>(Arrays.asList(0, 3)));
        bfs();
    }

    private static boolean isOut(int x, int y) {
        if(x <= 0 || x > r || y <= 0 || y > c) return true;
        return false;
    }

    private static boolean isHoleDown(int x, int y) {
        return hole.get((int)map[x][y]).contains(0);
    }

    private static boolean isHoleUp(int x, int y) {
        return hole.get((int)map[x][y]).contains(2);
    }

    private static boolean isHoleLeft(int x, int y) {
        return hole.get((int)map[x][y]).contains(3);
    }

    private static boolean isHoleRight(int x, int y) {
        return hole.get((int)map[x][y]).contains(1);
    }

    private static boolean isBlank(int x, int y) {
        if(isOut(x, y)) return true;
        if(map[x][y] == '.') return true;
        return false;
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[r + 1][c + 1];
        // M의 다음 가기.
        // 첫 블록이 사라진 경우도 처리
        for(int i = 0; i < 4; i++) {
            int nx = sx + dir[i][0];
            int ny = sy + dir[i][1];
            if(isOut(nx, ny)) continue;
            // 사라질 블록이 적어도 하나 있어야 하므로, z로 바로 연결될 수는 없다.
            if(map[nx][ny] != '.' && map[nx][ny] != 'Z') {
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
                break;
            }
        }

        // 첫 블록이 사라진 경우 위치, 블록 선택
        if(queue.isEmpty()) {
            for(int i =0; i < 4; i++) {
                selectBlock(new Node(sx + dir[i][0], sy + dir[i][1]));
            }
        }

        // 첫 블록이 사라진 경우 m과 연결할 파이프를 찾기 위해 M의 사방을 다 뚫어놨지만, M에는 1개의 파이프, 즉 시작 파이프밖에 연결되지 않으므로 이후부터는 M의 통로를 막아야 한다.
        hole.get((int)'M').clear();
        visited[sx][sy] = true;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

//            System.out.println(curr.x + " " + curr.y);

            // 빈 공간이라면 이곳에 블록이 있었다.
            // 블록 하나씩 넣어보면서 넣었을 때 사방이 뚫려있는데 다음 칸에 비었거나 이쪽 방향으로 뚫려있지 않은 경우 이 블록 못 넣는다.
            if(map[curr.x][curr.y] == '.') {
                selectBlock(curr);
                return;
            }

            char block = map[curr.x][curr.y];
//            System.out.println(block);
            for(int i = 0; i < 4; i++) {
                // 현재 파이프가 뚫려있는 쪽으로 가야함
                if(!hole.get((int)block).contains(i)) continue;

                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(isOut(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
    }

    private static void selectBlock(Node curr) {
        if(!(isBlank(curr.x + 1, curr.y) || isBlank(curr.x - 1, curr.y)
            || isBlank(curr.x, curr.y + 1) || isBlank(curr.x, curr.y - 1)
            || !isHoleDown(curr.x -1, curr.y) || !isHoleUp(curr.x + 1, curr.y) || !isHoleRight(curr.x, curr.y - 1) || !isHoleLeft(curr.x, curr.y + 1))) {
            System.out.println(curr.x + " " + curr.y + " +");
        } else if(!(isBlank(curr.x + 1, curr.y) || isBlank(curr.x - 1, curr.y)
            || !isHoleDown(curr.x - 1, curr.y) || !isHoleUp(curr.x + 1, curr.y))) {
            System.out.println(curr.x + " " + curr.y + " |");
        } else if(!(isBlank(curr.x, curr.y + 1) || isBlank(curr.x, curr.y - 1)
            || !isHoleRight(curr.x, curr.y - 1) || !isHoleLeft(curr.x, curr.y + 1))) {
            System.out.println(curr.x + " " + curr.y + " -");
        } else if(!(isBlank(curr.x - 1, curr.y) || isBlank(curr.x, curr.y + 1)
            || !isHoleDown(curr.x - 1, curr.y) || !isHoleLeft(curr.x, curr.y + 1))) {
            System.out.println(curr.x + " " + curr.y + " 2");
        } else if(!(isBlank(curr.x + 1, curr.y)  || isBlank(curr.x, curr.y + 1)
            || !isHoleUp(curr.x + 1, curr.y) || !isHoleLeft(curr.x, curr.y + 1))) {
            System.out.println(curr.x + " " + curr.y + " 1");
        } else if(!(isBlank(curr.x - 1, curr.y) || isBlank(curr.x, curr.y - 1)
            || !isHoleRight(curr.x, curr.y - 1) || !isHoleDown(curr.x - 1, curr.y))) {
            System.out.println(curr.x + " " + curr.y + " 3");
        } else if(!(isBlank(curr.x + 1, curr.y) || isBlank(curr.x, curr.y - 1)
            || !isHoleRight(curr.x, curr.y - 1) || !isHoleUp(curr.x + 1, curr.y))) {
            System.out.println(curr.x + " " + curr.y + " 4");
        }
    }

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
