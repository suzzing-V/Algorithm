import java.io.*;
import java.util.*;

// 시간복잡도: 2^20
// 두 점을 벡터로 만들고 합하는 것은 반은 좌표를 더하고, 반은 좌표를 뺀 값이다.
// 더할 좌표를 n/2개 구하고, 나머지는 뺄 좌표로 정한 후, 그 결과를 가지고 거리를 구하면 된다.
public class Main {

    private static int t;
    private static int n;
    private static Pos[] spots;
    private static double min = Long.MAX_VALUE;
//    private static Map<Integer, Integer>

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int a = 0; a < t; a++) {
            n = Integer.parseInt(bf.readLine());
            spots = new Pos[n];
            min = Long.MAX_VALUE;
//            visited = new HashSet[11];
//            for (int i = 0; i < visited.length; i++) {
//                visited[i] = new HashSet<>();
//            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                spots[i] = new Pos(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            }

            // 더할 점과 뺄점 조합 구하기
            dfs(0, 0);
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int spot, int visited) {
        // 더할 점 n/2개 구했다면 최솟값 갱신
        if(spot == n) {
            if(Integer.bitCount(visited) == n / 2) {
                long sum_x = 0;
                long sum_y = 0;
                for(int i = 0; i < n; i++) {
                    if(((1 << i) & visited) != 0) {
                        sum_x += spots[i].x;
                        sum_y += spots[i].y;
                    } else {
                        sum_x -= spots[i].x;
                        sum_y -= spots[i].y;
                    }
                }

                min = Math.min(min, Math.sqrt(Math.pow(sum_x, 2) + Math.pow(sum_y, 2)));
                return;
            }
            return;
        }

        // 현재 점 더하는 점으로 선택
        dfs(spot + 1, visited | (1 << spot));
        dfs(spot + 1, visited);
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node {
        long bx;
        long by;
        int turn;
        int used;

        Node(long bx, long by, int turn, int used) {
            this.bx = bx;
            this.by = by;
            this.turn = turn;
            this.used = used;
        }
    }
}
