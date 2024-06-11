import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Road>[] roads;

    static class Road {
        int start;
        int end;

        Road(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] input = new int[n + 1];
        roads = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());

            roads[first].add(new Road(first, last));
            input[last] ++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i < n + 1; i++) {
            if(input[i] == 0) {
                pq.add(i);
            }
        }
        while(!pq.isEmpty()) {
            int now = pq.remove();
            bw.write(String.valueOf(now) + " ");
            for(Road road : roads[now]) {
                input[road.end] --;
                if(input[road.end] == 0) pq.add(road.end);
            }
        }
        bw.close();
    }
}
