import java.io.*;
import java.util.*;

// 시간복잡도: 250 * 2500 + 250 * 250
// bfs를 활용해 하려고 했지만 순서를 고려하는 게 시간복잡도에 걸렸다.
// MST를 생각하긴 했지만 s에 연결되는 경우를 예외라고 생각했었다. 하지만 생각해보면, s도 그냥 키와 같이 하나의 노드로 보고 이 노드들을 전부 연결시킬 수 있는 최소 간선들을 선택하면 된다.

public class Main {

    private static int n;
    private static int m;
    private static char[][] board;
    private static int[][] knInfo;
    private static boolean[][] visited;
    private static Map<Integer, Pos> keys = new HashMap<>();
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static PriorityQueue<Node> edges = new PriorityQueue<>((o1, o2) -> {return o1.cnt - o2.cnt;});
    private static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][n];
        knInfo = new int[n][n];
        root = new int[m + 1];
        visited = new boolean[n][n];
        int kn = 0;
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'K' || board[i][j] == 'S') {
                    keys.put(kn, new Pos(i, j));
                    knInfo[i][j]= kn;
                    kn ++;
                }
            }
        }
        for(int i = 0; i <= m; i++) {
            root[i] = i;
        }


        // 각 노드들의 다른 키까지의 간선 구하기
        for(Integer key : keys.keySet()) {
            Pos pos = keys.get(key);
            bfs(key, pos.x, pos.y);
        }

        // MST
        int sum = 0;
        while(!edges.isEmpty()) {
            Node curr = edges.remove();

            if(find(curr.x) == find(curr.y)) continue;
            union(curr.x, curr.y);
            sum += curr.cnt;
            m --;
        }

        if(m != 0) System.out.println("-1");
        else System.out.println(sum);
    }

    private static int find(int x) {
        if(x == root[x]) return x;

        return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) root[b] = a;
        else root[a] = b;
    }

    private static void bfs(int start, int sx, int sy) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, 0));
        visited = new boolean[n][n];
        visited[sx][sy] = true;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.x + " " + curr.y + " " + curr.cnt);

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];


                if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == '1' || visited[nx][ny]) {
                    continue;
                }
                    queue.add(new Node(nx, ny, curr.cnt + 1));
                    visited[nx][ny] = true;
                    if(board[nx][ny] == 'K') {
                        edges.add(new Node(start, knInfo[nx][ny], curr.cnt + 1));
                    }
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
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
