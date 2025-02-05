import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static String s;
    static ArrayList<Road>[] roads;
    static boolean[] visited;
    static int maxHappy = 0;
    static int[][] lcs = new int[6000][6000];

    private static class Road {
        private int next;
        private String c;

        Road(int next, String c) {
            this.next = next;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = bf.readLine();
        roads = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i < n + 1; i ++) {
            roads[i] = new ArrayList<Road>();
        }
        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            roads[n1].add(new Road(n2, c));
            roads[n2].add(new Road(n1, c));
        }

        dfs(1, "");
        bw.write(String.valueOf(maxHappy));
        bw.close();
    }

    private static void dfs(int node, String route) {
        if(visited[node]) return;
        boolean isLeaf = true;
        visited[node] = true;
        int routeLen = route.length();
        int lastRouteIdx = route.length() - 1;
        if(routeLen != 0) {
            for(int i = 1; i <= s.length(); i++) {
                if(s.charAt(i - 1) == route.charAt(lastRouteIdx)) {
                    lcs[routeLen][i] = lcs[routeLen - 1][i - 1] + 1;
                } else {
                    lcs[routeLen][i] = Math.max(lcs[routeLen - 1][i], lcs[routeLen][i - 1]);
                }
            }
        }

        for(Road road : roads[node]) {
            if(!visited[road.next]) isLeaf = false;
            int next = road.next;
            dfs(next, route + road.c);
        }

        if(isLeaf) {
            maxHappy = Math.max(maxHappy, lcs[routeLen][s.length()]);
        }
    }

    private static int getLcsSize(String t) {
        int[][] lcs = new int[t.length() + 1][s.length() + 1];

        for(int i = 1; i < lcs.length; i++) {
            for(int j = 1; j < lcs[0].length; j++) {
                if(t.charAt(i - 1) == s.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[t.length()][s.length()];
    }
}
