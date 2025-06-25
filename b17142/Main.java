import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static String[][] map;
    private static List<Pos> viruses;
    private static int min = Integer.MAX_VALUE;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new String[n][n];
        viruses = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                int c = Integer.parseInt(st.nextToken());
                if(c == 0) map[i][j] = "o";
                else if(c == 1) map[i][j] = "-";
                else if(c == 2) {
                    map[i][j] = "*";
                    viruses.add(new Pos(i, j));
                }
            }
        }

        // 활성 바이러스 조합 만들기
        dfs(0, new ArrayList<>());
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void dfs(int idx, List<Pos> selected) {
        if(selected.size() == m) {
//            for(Pos pos : selected) {
//                System.out.print(pos.x + "," + pos.y + " ");
//            }
//            System.out.println();
            int time = play(selected);
            min = Math.min(min, time);
            return;
        }

        for(int i = idx; i < viruses.size(); i++) {
            Pos v = viruses.get(i);
            selected.add(v);
            map[v.x][v.y] = "#";
            dfs(i + 1, selected);
            map[v.x][v.y] = "*";
            selected.remove(selected.size() - 1);
        }
    }

    private static int play(List<Pos> selected) {
        int[][] record = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                record[i][j] = -1;
            }
        }

        Queue<Node> q = new LinkedList<>();
        for(Pos pos : selected) {
            record[pos.x][pos.y] = 0;
            q.add(new Node(pos.x, pos.y, 0));
        }

        while(!q.isEmpty()) {
            Node curr = q.remove();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(map[nx][ny].equals("-")) continue;
                // 이미 방문했는데 저장돼있는 값이 가려는 값보다 작거나 같으면 이미 이 칸까지 오는 최소 경로로 가는 노드가 있기 때문에 더 탐색할 필요 없다.
                if(record[nx][ny] != -1 && record[nx][ny] <= curr.time + 1) continue;
                // 비활성바이러스가 있는 칸은 굳이 안 가도 된다. 이미 바이러스기때문에. 하지만 이 비활성 바이러스들을 건너서 갈 수 있는 빈칸이 있을 수 있으므로 그냥 방문처리만 하고 탐색만 더 한다. 걸리는 시간은 저장하지 않는다.
                if(map[nx][ny].equals("*")) {
                    q.add(new Node(nx, ny, curr.time + 1));
                    record[nx][ny] = 0;
                    continue;
                }
                record[nx][ny] = curr.time + 1;
//                max = Math.max(record[nx][ny], max);
                q.add(new Node(nx, ny, curr.time + 1));
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 벽이나 비활성 바이러스가 아닌데 방문처리가 안돼있으면 바이러스가 퍼지지 않은 것이므로 불가능한 경우다.
                if(!(map[i][j].equals("-") || map[i][j].equals("*")) && record[i][j] == -1) return Integer.MAX_VALUE;
                max = Math.max(max, record[i][j]);
//                System.out.print(record[i][j] + " ");
            }
//            System.out.println();
        }
//        System.out.println(max);
        return max;
    }

    private static class Node {
        private int x;
        private int y;
        private int time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }


    private static class Pos {
        private int x;
        private int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
