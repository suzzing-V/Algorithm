import java.util.*;
import java.io.*;

// 시간복잡도: 1000 + 100000
// 단순히 bfs로 구현하면 간선 개수가 너무 많아 메모리 초과가 난다.
// 하이퍼튜브를 하나의 노드로 설정하면 간선 개수가 제곱근 수준으로 적어진다. bfs 돌릴 때 다음 노드가 하이퍼튜브인 경우 count해주지 않는다.
public class Main {

    private static int n;
    private static int k;
    private static int m;
    private static Set<Integer>[] conn;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        conn = new HashSet[n + 1001];
        dist = new int[n + 1001];
        Arrays.fill(dist, 200000);

        for(int i = 0; i < conn.length; i++) {
            conn[i] = new HashSet<>();
        }

        dist[1] = 1;
        for(int i = 1; i <= m ;i++) {
            st = new StringTokenizer(bf.readLine());

            while(st.hasMoreTokens()) {
                int s = Integer.parseInt(st.nextToken());
                // 하이퍼 노드에 연결
                conn[s].add(n + i);
                conn[n + i].add(s);
            }
        }

        dikstra();
        System.out.println(dist[n]);
    }

    private static void dikstra() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1));

        while (!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.n + " " + curr.cnt);

            if(curr.n == n) {
                return;
            }

            for(int s : conn[curr.n]) {
                // 만약 다음 노드가 하이퍼노드가 아니면 개수 센다.
                if(curr.n <= n && dist[s] > curr.cnt + 1) {
                    dist[s] = curr.cnt + 1;
                    queue.add(new Node(s, curr.cnt + 1));
                } else if(curr.n > n && dist[s] > curr.cnt) {
                    // 하이퍼노드면 개수 안 센다
                    dist[s] = curr.cnt;
                    queue.add(new Node(s, curr.cnt));
                }
            }
        }

        dist[n] = -1;
    }

    private static class Node {
        int n;
        int cnt;

        Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
}
