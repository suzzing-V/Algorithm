import java.util.*;
import java.io.*;

// 시간복잡도: 5^2 * 2^2* 5 + 4*5 + 120 * 2^10 * 5^3
// 각 판을 회전했을 때 상태를 저장하고, 판의 높이에 따른 회전 상태를 중복 순열로 저장한다.
// 각 판을 올리는 순서의 조합을 순열로 만들고, 회전 조합을 선택하고, 시작점과 끝점을 정해 확인하고, 탐색한다.
public class Main {

    private static int[][][] maze = new int[5][5][5];

    private static int[][][][] rotated = new int[5][4][5][5]; // i번 판을 j번 회전시킨 결과
    private static int min = Integer.MAX_VALUE;
    private static int[][] edge = {{0, 0, 4, 4}, {4, 0, 0, 4}, {4, 4, 0, 0}, {0, 4, 4, 0}};
    private static List<List<Integer>> angles = new ArrayList<>();
    private static int[][] dir = {{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int k = 0; k < 5; k++) {
                    maze[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 회전 결과 저장하기
        for(int i = 0; i < 5; i++) {
            // 0번째 회전은 오리지널 저장하기
            for(int x = 0; x < 5; x++) {
                for(int y = 0; y < 5; y++) {
                    rotated[i][0][x][y] = maze[i][x][y];
                }
            }
//            print_arr(rotated[i][0]);
            for(int r = 1; r < 4; r ++) {
                rotate_90(i, r);
//                print_arr(rotated[i][r]);
            }
        }

        // 회전 각도 조합 만들기
        make_angle_combi(0, new ArrayList<>());

        // 쌓는 순열 만들기
        dfs(0, new ArrayList<>());

        if(min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    private static void print_arr(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate_90(int b, int r) {
        // 현재 판의 이전 회전 결과를 가지고 90도 회전시키기
        for(int x = 0; x < 5; x ++) {
            for(int y = 0; y < 5; y++) {
                rotated[b][r][x][y] = rotated[b][r - 1][4 - y][x];
            }
        }
    }

    private static void dfs(int idx, List<Integer> boards) {
        // 5개의 보드 순열 완성 시 각 판의 회전 조합 선택하고, 시작점 끝점 확인하고, 탐색 시작
        if(idx == 5) {
//            System.out.println(boards.toString());
            for(int r = 0; r < angles.size(); r++) {
                List<Integer> angle = angles.get(r);
                for(int e = 0; e < 4; e++) {
                    Pos start = new Pos(boards.get(0), edge[e][0], edge[e][1]);
                    Pos end = new Pos(boards.get(boards.size() - 1), edge[e][2], edge[e][3]);

                    if(rotated[boards.get(0)][angle.get(0)][start.y][start.z] == 0 || rotated[boards.get(boards.size() -1)][angle.get(angle.size() - 1)][end.y][end.z] == 0) continue;
                    min = Math.min(bfs(start, end, angles.get(r), boards), min);
                }
            }
            return;
        }

        for(int i = 0; i < 5; i++) {
            if(boards.contains(i)) continue;
            boards.add(i);
            dfs(idx + 1, boards);
            boards.remove(boards.size() - 1);
        }
    }

    private static int bfs(Pos start, Pos end, List<Integer> angles, List<Integer> boards) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, start.y, start.z, 0));
        boolean[][][] visited = new boolean[5][5][5];
        visited[0][start.y][start.z] = true;
//        for(int b = 0; b < 5; b ++) {
//            print_arr(rotated[boards.get(b)][angles.get(b)]);
//        }

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            if(boards.get(curr.bi) == end.x && curr.y == end.y && curr.z == end.z) {
                return curr.d;
            }

            for(int i = 0; i < 6; i++) {
                int nbi = curr.bi + dir[i][0];
                int ny = curr.y + dir[i][1];
                int nz = curr.z + dir[i][2];

                if(nbi < 0 || nbi >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5 || visited[nbi][ny][nz]) continue;
                if(rotated[boards.get(nbi)][angles.get(nbi)][ny][nz] == 0) continue;

                visited[nbi][ny][nz] = true;
                queue.add(new Node(nbi, ny, nz, curr.d + 1));
            }
        }

        return Integer.MAX_VALUE;
    }

    private static class Node {
        // boards 배열에서의 위치
        int bi;
        int y;
        int z;
        int d;

        Node(int bi, int y, int z, int d) {
            this.bi = bi;
            this.y = y;
            this.d = d;
            this.z = z;
        }
    }

    private static class Pos {
        int x;
        int y;
        int z;

        Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static void make_angle_combi(int idx, List<Integer> combi) {
        if(combi.size() == 5) {
            // 조합 완성되면 저장하기
            List<Integer> result = new ArrayList<>();
//            System.out.println(combi.toString());
            for(int i = 0; i < 5; i++) {
                result.add(combi.get(i));
            }

            angles.add(result);
            return;
        }

        for(int i = 0; i < 4; i++) {
            combi.add(i);
            make_angle_combi(idx + 1, combi);
            combi.remove(combi.size() - 1);
        }
    }
}
