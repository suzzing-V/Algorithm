import java.util.*;
import java.io.*;

// 모든 노드 사이의 거리를 플로이드 워셜로 구하기.
// bfs로 그룹 만들고 그 그룹에서 돌아가면서 팀장을 맡았을 때, 그 팀장까지의 거리가 가장 먼 노드의 거리가 가장 작은 경우에 팀장을 구한다.
// union-find로 그룹을 지을 수도 있다.
// 시간 복잡도: 100 * 100 * 100 + 100 * 100 * 100

public class Main {

    private static List<Integer>[] conn;
    private static int[][] dp;
    private static boolean[] visited;
    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());
        visited = new boolean[n + 1];
        conn = new ArrayList[n + 1];
        dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n ;i++) {
            conn[i] = new ArrayList<>();
            for(int j = 1; j <= n;j ++) {
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = 200;
            }
        }

        for(int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            conn[n1].add(n2);
            conn[n2].add(n1);
            dp[n1][n2] = 1;
            dp[n2][n1] = 1;
        }

        floyd();

        int cnt = 0;
        List<Integer> answers = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(visited[i]) continue;
            List<Integer> set = bfs(i);

            int min = 200;
            int answer = 0;
            for(int king : set) {
                int max = findMaxTime(set, king);
                if(min > max) {
                    min = max;
                    answer = king;
                }
            }
            answers.add(answer);
            cnt ++;
        }

        System.out.println(cnt);
        Collections.sort(answers);
        for(int i : answers) {
            System.out.println(i);
        }
    }

    private static void floyd() {
        for(int k = 1; k <= n; k++) {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    private static List<Integer> bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        List<Integer> team = new ArrayList<>();

        while(!queue.isEmpty()) {
            int curr = queue.remove();
            team.add(curr);
            visited[curr] = true;

            for(int next : conn[curr]) {
                if(visited[next]) continue;

                queue.add(next);
            }
        }

        return team;
    }

    private static int findMaxTime(List<Integer> set, int king) {
        int max = 0;
        for(int i : set) {
            max = Math.max(dp[i][king], max);
        }

        return max;
    }
}
