import java.io.*;
import java.util.*;

// MST로 풀 경우 슈퍼컴퓨터에서 다른 컴퓨터로 가는 시간이 원래 회선보다 더 적어야한다는 조건이 맞지 않는 경우가 생긴다. 따라서 다익스트라로 푼다.
// 다익스트라로 슈퍼컴퓨터에서 다른 컴퓨터로의 최소 시간을 갱신하면서, 갱신할 때마다 간선을 사용했을 때 도달하는 노드의 부모를 현재 노드로 갱신해준다.
// 시간 복잡도: 1000 + 500 * 999
public class Main {

    private static int n;
    private static int m;
    private static List<Line>[] lines;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lines = new ArrayList[n + 1];
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            lines[i] = new ArrayList<>();
            parent[i] = i;
        }

        // 간선 정보 저장
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            lines[a].add(new Line(b, c));
            lines[b].add(new Line(a, c));
        }

        System.out.println(n - 1);
        dikstra();
        for(int i = 2; i <= n; i++) {
            System.out.println(i + " " + parent[i]);
        }
    }

    private static void dikstra() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100000);
//        dist[1] = 0;
        PriorityQueue<Line> pq = new PriorityQueue<>();
        pq.add(new Line(1, 0));

        while(!pq.isEmpty()) {
            Line curr = pq.remove();
//            System.out.println(curr.n + " "+ curr.w);

            // 같으면 탐색해 봐야됨
            if(dist[curr.n] < curr.w) continue;

            for(Line next : lines[curr.n]) {
                if(dist[next.n] > curr.w + next.w) {
                    dist[next.n] = curr.w + next.w;
                    parent[next.n] = curr.n;
                    pq.add(new Line(next.n, curr.w + next.w));
                }
            }
        }
    }

    private static class Line implements Comparable<Line> {
        int n;
        int w;

        Line(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Line l) {
            return this.w - l.w;
        }
    }
}
