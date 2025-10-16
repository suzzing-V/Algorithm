import java.util.*;
import java.io.*;

// 시간복잡도: 300 * 300
// 가상의 노드 0을 두고 직접 우물을 파는 경우의 비용을 노드 0에 연결해준다. 그리고 MST를 돌린다.
// 이렇게 하면 직접 우물을 파는 경우가 더 비용이 적은 경우 먼저 선택할 수 있고, 후의 간선에서 이 노드에 더 적은 비용으로 연결할 수 있다.
// 처음에 구현했던 MST로 우물들을 연결하고 dfs로 탐색하면서 직접 우물을 파는 경우가 더 비용이 적을 경우 직접 우물을 파는 걸 선택하는 방식은, 후에 등장하는 노드를 직접 파고 거기에 연결하는 경우를 고려하지 못한다.
// 가상의 노드를 두는 방식으로 구현하면, 직접 우물을 파는 경우도 '물이 있는 우물'의 집합에 포함될 수 있으므로, 다음 노드가 더 최소 비용으로 연결하는 방법을 선택하도록 선택 폭을 넓힐 수 있다.
public class Main {

    private static int n;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();
    private static int[] parents;
    private static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        for(int i = 1; i <= n; i++) {
            // 자기 자신을 파는 경우는 0 노드에 연결해준다.
            pq.add(new Edge(0, i, Integer.parseInt(bf.readLine())));
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                int w = Integer.parseInt(st.nextToken());
                if(i != j) pq.add(new Edge(i, j, w));
            }
        }

        // 가장 가중치 작은 간선부터 선택하면서 mst 그래프 만들기
        parents = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        while(!pq.isEmpty()) {
            Edge curr = pq.remove();

            // 이미 두 노드 모두 시작 노드에 연결돼있다면 사이클 발생하므로 continue
            if(find(curr.n1) == find(curr.n2)) continue;

            // 아니면 이 간선 선택하고 연결
            union(curr.n1, curr.n2);
//            System.out.println("간선 선택: " + curr.n1 + " " + curr.n2 + " " + curr.w);
            sum += curr.w;
        }
        System.out.println(sum);
    }

    private static int find(int x) {
        if(parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int n1;
        int n2;
        int w;

        Edge(int n1, int n2, int w) {
            this.n1 = n1;
            this.n2 = n2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }
}
