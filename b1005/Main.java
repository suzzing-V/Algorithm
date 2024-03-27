import java.io.*;
import java.util.*;

public class Main {
    static int[] time;
    static ArrayList<Node>[] rule;

    public static class Node {
        int end;
        int time;

        Node(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            time = new int[n + 1];
            rule = new ArrayList[n + 1];
            for (int j = 0; j < n + 1; j++) {
                rule[j] = new ArrayList<>();
            }

            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                time[j] = Integer.parseInt(st.nextToken());
            }

            int[] input = new int[n + 1];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                rule[start].add(new Node(end, time[end]));
                input[end] ++;
            }

            int w = Integer.parseInt(bf.readLine());

            bw.write(dijkstra(input, n, w) + "\n");
        }
        bw.close();
    }

    public static int dijkstra(int[] input, int building, int goal) {
        int[] result = new int[building + 1];
        Queue<Integer> pq = new LinkedList<>();
        for(int i = 1; i <= building; i++) {
            if(input[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int pick = pq.remove();

            for(Node node : rule[pick]) {
                result[node.end] = Math.max(result[node.end], time[pick] + result[pick]);
                input[node.end] --;
                if(input[node.end] == 0) {
                    pq.add(node.end);
                }
            }
        }

        return result[goal] + time[goal];
    }
}
