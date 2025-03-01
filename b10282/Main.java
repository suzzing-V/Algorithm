import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int d;
    static int c;
    static List<Node>[] lines;
    static int[] time;

    private static class Node implements Comparable<Node> {
        private int num;
        private int v;

        private Node(int num, int v) {
            this.num = num;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            lines = new ArrayList[n + 1];
            time = new int[n + 1];
            Arrays.fill(time, Integer.MAX_VALUE); // 최소 시간을 구해야하므로 최대값으로 초기화
            for(int j = 0; j <= n; j++) {
                lines[j] = new ArrayList<>();
            }

            for(int j = 0; j < d; j++) {
                st = new StringTokenizer(bf.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                lines[n2].add(new Node(n1, v)); // n2 -> n1
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(c, 0));
            time[c] = 0;
            while(!pq.isEmpty()) {
                Node curr = pq.remove();
                if(curr.v > time[curr.num]) continue; // 이미 저장된 값이 더 작으므로 해당 노드는 방문할 필요 없다

                for(Node next : lines[curr.num]) {
                    if(time[next.num] > curr.v + next.v) { // 지금 꺼 경유 vs 기존 값
                        time[next.num] = curr.v + next.v;
                        pq.add(new Node(next.num, curr.v + next.v));
                    }
                }
            }

            int cnt = 0;
            int totalTime = 0;
            for(int j = 1; j <= n; j++) {
                if(time[j] == Integer.MAX_VALUE) continue;
                cnt ++;
                totalTime = Math.max(totalTime, time[j]);
            }
            bw.write(cnt + " " + totalTime + "\n");
        }
        bw.close();
    }
}
