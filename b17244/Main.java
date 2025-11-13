import java.util.*;
import java.io.*;

// 시간복잡도: 7 * 2500 + 5! * 7
// 각 지점들 사이의 최소 거리 구하고, 물건 줍는 순서를 정한 후 그 순서에 따라 거리 합한다. 이 거리 합이 가장 작은 값 리턴
public class Main {

    private static char[][] map;
    private static int n;
    private static int m;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static List<Node> nodes = new ArrayList<>();
    private static int[][] dist;
    private static int[][] nodeNum;
    private static int t = 0;

    private static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        nodeNum = new int[m][n];
        dist = new int[8][8];
        int idx = 1;
        for(int i = 0; i < m; i++) {
            String line = bf.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S') {
                    nodes.add(new Node(0, i, j));
                    nodeNum[i][j] = 0;
                } else if(map[i][j] == 'E') {
                    nodes.add(new Node(7, i, j));
                    nodeNum[i][j] = 7;
                } else if(map[i][j] == 'X') {
                    nodeNum[i][j] = idx;
                    nodes.add(new Node(idx ++, i, j));
                    t ++;
                }
            }
        }

        // 각 노드를 시작점으로 해서 다른 노드들까지의 최소 간선 구하기
        for(int i = 0; i < nodes.size(); i++) {
            bfs(nodes.get(i));
        }

        // 순서 정하고 그 순서에 따라 거리 저장하면서 그 합의 최솟값 갱신
        dfs(new ArrayList<>());

        System.out.println(min);
    }

    private static void dfs(List<Integer> selected) {
        if(selected.size() == t) {
            min = Math.min(min, countDists(selected));
            return;
        }

        for(int i = 1; i <= t; i ++) {
            if(selected.contains(i)) continue;
            selected.add(i);
            dfs(selected);
            selected.remove(selected.size() - 1);
        }
    }

    private static int countDists(List<Integer> nodes) {
        if(nodes.size() == 0) return dist[0][7];

        int prev = 0;
        int curr;
        int sum = 0;
        for(int i = 0; i < nodes.size(); i ++) {
            curr = nodes.get(i);
            sum += dist[prev][curr];
            prev = nodes.get(i);
        }

        sum += dist[prev][7];
        return sum;
    }

    private static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new Node(0, start.x, start.y));
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.x + " " + curr.y + " " + curr.id);

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] == '#') continue;
                if(map[nx][ny] == 'X' || map[nx][ny] == 'S' || map[nx][ny] == 'E') {
                    // 노드 번호 구하고 시작노드와의 거리 저장
                    int node = nodeNum[nx][ny];
                    dist[start.id][node] = curr.id + 1;
                }
                queue.add(new Node(curr.id + 1, nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    private static class Node {
        int id;
        int x;
        int y;

        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}
