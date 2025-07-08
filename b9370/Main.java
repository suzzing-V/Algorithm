import java.util.*;
import java.io.*;

// 플로이드 워셜은 시간 초과
public class Main {

    private static int MAX = 20000000;
    private static int[] distS;
    private static int[] distG;
    private static int[] distH;
    private static ArrayList[] roads;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            roads = new ArrayList[n + 1];
            for(int k = 1; k <= n; k++) {
                roads[k] = new ArrayList<Node>();
            }

            int[][] costs = new int[n + 1][n + 1];
            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(bf.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                roads[n1].add(new Node(n2, cost));
                roads[n2].add(new Node(n1, cost));
                costs[n1][n2] = cost;
                costs[n2][n1] = cost;
            }

            List<Integer> result = new ArrayList<>();
            for(int a = 0; a < t; a++) {
                int dest = Integer.parseInt(bf.readLine());
                distS = new int[n + 1];
                Arrays.fill(distS, MAX);
                distS[s] = 0;
                distG = new int[n + 1];
                Arrays.fill(distG, MAX);
                distG[g] = 0;
                distH = new int[n + 1];
                Arrays.fill(distH, MAX);
                distH[h] = 0;
                dikstra(s, distS);
                dikstra(g, distG);
                dikstra(h, distH);

                int totalMin = distS[dest]; // s ~ dest까지의 최단 거리
                // s~g~h~dest와 s~h~g~dest 중 짧은 거
                int smellMin = Math.min(distS[h] + distG[dest], distS[g] + distH[dest]) + costs[g][h];
//                System.out.println(dest + " : " + totalMin + " " + smellMin1 + " " + sm2);
                if(smellMin <= totalMin) result.add(dest); // 냄새나는 도로 지나는 게 최단 거리면 가능한 경우(최단거리로만 감)
            }

            Collections.sort(result);
            for(int dest : result) {
                System.out.print(dest + " ");
            }
            System.out.println();
        }
    }

    // 모두 구하기
    private static void dikstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < roads[start].size(); i++) {
            pq.add(new Node(start, 0));
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
            
            for(int i = 0; i < roads[curr.end].size(); i++) {
                Node next = (Node)roads[curr.end].get(i);
                if(dist[next.end] > curr.cost + next.cost) {
                    dist[next.end] = curr.cost + next.cost;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        private int end;
        private int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}
