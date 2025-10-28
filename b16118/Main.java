import java.util.*;
import java.io.*;

// 여우의 dist와 늑대의 dist를 따로 저장한다.
// 늑대의 경우, 각 지점에 도착하는 경우가 두 가지이다. 하나는 짝수개의 간선을 거쳐온 경우, 하나는 홀수개의 간선을 거쳐온 경우이다. 이 두 경우로 해당 지점에 도착했을 떄의 최소거리를 저장한다.
// 현재 탐색 중인 노드가 홀수개의 간선을 거쳐왔을 때는 다음은 짝수번쨰 간선이므로, 가중치를 두배해준 값으로 다음 노드로 갔을 때의 최소거리를 비교하고, 짝수개의 간선을 거쳐왔을 경우에는 /2해준 값으로 다음 노드로 갔을 때의 최소거리를 비교한다.
// 마지막으로 각 지점에서 늑대가 해당 지점에 도착하는 최소 거리들이 여우보다 크면 카운트해준다.
// 처음에 틀렸던 이유: 늑대가 1번 지점을 다시 방문해서 최소 루트로 갈 수도 있다. 1번을 다시 방문하지 못하도록 0으로 초기화해서는 안된다.
// 시간복잡도: (E + V)logV + (2E + 2V)log2V = 200000 * 16 + 400000 * 18 = 10400000
public class Main {

    private static double[] distF;
    private static double[][] distW;
    private static int n;
    private static int m;
    private static List<Edge>[] conn;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distF = new double[n + 1];
        distW = new double[n + 1][2];
        conn = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            conn[i] = new ArrayList<>();
            distW[i][0] = Integer.MAX_VALUE;
            distW[i][1] = Integer.MAX_VALUE;
            distF[i] = Integer.MAX_VALUE;
        }

        distW[1][0] = Integer.MAX_VALUE;
        distW[1][1] = Integer.MAX_VALUE;
        distF[1] = 0;
        for(int t = 0; t < m; t ++ ){
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            conn[n1].add(new Edge(n2, w));
            conn[n2].add(new Edge(n1, w));
        }

        dikstra1();
        dikstra2();

        int cnt = 0;
        for(int i = 2; i <= n; i++) {
            // 홀수 개수로 갈 수 있는 방법이 없으면 세기
//            System.out.println(distF[i] + " " + distW[i][0] + " " + distW[i][1]);
            if(distF[i] < distW[i][0] && distF[i] < distW[i][1]) cnt ++;
        }
        System.out.println(cnt);
    }

    private static void dikstra1() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            if(distF[curr.n] < curr.d) continue;
//            if(dist[curr.n][0] == curr.d) {
//                if(curr.e % 2 == 0 && dist[curr.n][2] != -1) {
//                    continue;
//                }
//                if(curr.e % 2 != 0 && dist[curr.n][1] != -1) {
//                    continue;
//                }
//            }

            for(Edge edge : conn[curr.n]) {
                int next = edge.n;
                if(distF[next] >  curr.d + edge.w) {
                    distF[next] = curr.d + edge.w;
                    queue.add(new Node(next, curr.d + edge.w, curr.e + 1));
                }
            }
        }
    }

    private static void dikstra2() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

//            System.out.println(curr.n + " " + curr.d + " " + curr.e);
            if(distW[curr.n][curr.e % 2] < curr.d) continue;
//            if(dist[curr.n][0] == curr.d) {
//                if(curr.e % 2 == 0 && dist[curr.n][2] != -1) {
//                    continue;
//                }
//                if(curr.e % 2 != 0 && dist[curr.n][1] != -1) {
//                    continue;
//                }
//            }

            for(Edge edge : conn[curr.n]) {
                int next = edge.n;
                if(curr.e % 2 == 0 && distW[next][1] > curr.d + (double)edge.w / 2) {
                    distW[next][1] = curr.d + (double)edge.w / 2;
                    queue.add(new Node(next, distW[next][1], curr.e + 1));
                } else if(curr.e % 2 != 0 && distW[next][0] > curr.d + edge.w * 2) {
                    distW[next][0] = curr.d + edge.w * 2;
                    queue.add(new Node(next, distW[next][0], curr.e + 1));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int n;
        double d;
        int e;

        Node(int n, double d, int e) {
            this.n = n;
            this.d = d;
            this.e = e;
        }

        @Override
        public int compareTo(Node n) {
            if(this.d < n.d) return -1;
            else if(this.d == n.d) return 0;
            return 1;
        }
    }

    private static class Edge {
        int n;
        int w;

        Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
