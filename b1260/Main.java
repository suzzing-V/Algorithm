import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int v;
    static boolean[] visited;
    static PriorityQueue<Integer>[] lines;
    static PriorityQueue<Integer>[] linesb;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        lines = new PriorityQueue[n + 1];
        linesb = new PriorityQueue[n + 1];
        for(int i = 0; i < n + 1; i++) {
            lines[i] = new PriorityQueue<Integer>();
            linesb[i] = new PriorityQueue<>();
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            lines[n1].add(n2);
            lines[n2].add(n1);
            linesb[n1].add(n2);
            linesb[n2].add(n1);
        }

        System.out.print(v + " ");
        visited[v] = true;
        dfs(v);
        System.out.println();
        visited = new boolean[n + 1];
        bfs(v);
    }

    private static void dfs(int node) {
        while(!lines[node].isEmpty()) {
            int next = lines[node].remove();
            if(visited[next]) continue;
            visited[next] = true;
            System.out.print(next + " ");
            dfs(next);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            System.out.print(curr + " ");

            while(!linesb[curr].isEmpty()) {
                int next = linesb[curr].remove();
                if(visited[next]) continue;
                visited[next] = true;
                queue.add(next);
            }
        }
    }
}
