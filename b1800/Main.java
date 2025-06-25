import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static ArrayList<Node>[] conn;
    private static int p;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        conn = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }
        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(bf.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            conn[c1].add(new Node(c2, cost));
            conn[c2].add(new Node(c1, cost));
        }

        // 가능한 최소비용을 탐색한다. 사용되는 케이블의 수가 k보다 작거나 같을 경우 0의 비용도 가능
        System.out.println(binarySearch(0, 1000000));
    }

    private static int binarySearch(int start, int end) {
        if(start == end) {
            if(!dikstra(start)) return -1;
            return start;
        }
        int mid = (start + end) / 2;

//        System.out.println(mid);
        // mid보다 큰 케이블의 개수를 최소로 사용했을 때 그 개수가 k보다 크면 mid를 더 늘려야 한다.
        if(!dikstra(mid)) return binarySearch(mid + 1, end);
        // k보다 작거나 같을 경우 mid를 포함해 왼쪽을 탐색한다. 비용을 더 줄여도 된다.
        return binarySearch(start, mid);
    }

    // x보다 큰 간선은 k이하로 사용하면서 경로를 구성할 수 있나?
    private static boolean dikstra(int x) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100000);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.num + " " + curr.cnt);
            // 이미 저장된 값이 더 작으면 더 탐색할 필요가 없다.
            if(dist[curr.num] < curr.cnt) continue;

            for(Node next : conn[curr.num]) {
                // curr -> next로 가는 케이블이 x보다 크면 1 더해줌
                int isOverX = 0;
                if(next.cnt > x) isOverX = 1;
                // x보다 큰 케이블의 개수를 최소화해야한다.
                // >= 하면 시초나는 이유: 방문했던 거 또 방문할 수 있음 -> 더 작은 거만 갱신
                if(dist[next.num] > curr.cnt + isOverX) {
                    dist[next.num] = curr.cnt + isOverX;
                    pq.add(new Node(next.num, curr.cnt + isOverX));
                }
            }
        }

//        for(int i = 1; i <= n; i++) {
//            System.out.print(dist[i] + " ");
//        }
//        System.out.println();
        if(dist[n] <= k) return true;
        return false;
    }

    private static class Node implements Comparable<Node> {
        private int num;
        private int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
}
