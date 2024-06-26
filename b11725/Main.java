import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static ArrayList<Integer>[] line;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        line = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            line[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            line[start].add(end);
            line[end].add(start);
        }

        parents = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(1);

        for(int i = 2; i < n + 1; i++) {
            System.out.println(parents[i]);
        }
    }

    public static void dfs(int parent) {
        for(int i : line[parent]) {
            if(visited[i]) continue;
            visited[i] = true;
            parents[i] = parent;
            dfs(i);
        }
    }
}
