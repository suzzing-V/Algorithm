import java.io.*;
import java.util.*;

public class Main {

    static class Tunnel implements Comparable<Tunnel>{
        int start;
        int end;
        int wv;

        Tunnel(int start, int end, int wv) {
            this.start = start;
            this.end = end;
            this.wv = wv;
        }

        @Override
        public int compareTo(Tunnel o) {
            return this.wv - o.wv;
        }
    }

    static class Planet implements Comparable<Planet>{
        int id;
        int val;

        Planet(int id, int val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public int compareTo(Planet o) {
            return this.val - o.val;
        }
    }

    static int n;
    static List<Planet> listX = new ArrayList<>();
    static List<Planet> listY = new ArrayList<>();
    static List<Planet> listZ = new ArrayList<>();
    static PriorityQueue<Tunnel> tunnels = new PriorityQueue<>();
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            listX.add(new Planet(i, Integer.parseInt(st.nextToken())));
            listY.add(new Planet(i, Integer.parseInt(st.nextToken())));
            listZ.add(new Planet(i, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(listX);
        Collections.sort(listY);
        Collections.sort(listZ);

        pushTunnels(listX);
        pushTunnels(listY);
        pushTunnels(listZ);

        int result = 0;

        while(!tunnels.isEmpty()) {
            Tunnel now = tunnels.remove();
            if(find(now.start) != find(now.end)) {
                result += now.wv;
                union(now.start, now.end);
            }
        }

        System.out.println(result);
    }

    private static void pushTunnels(List<Planet> list) {
        for(int i = 0; i < n - 1; i++) {
            int wv = list.get(i + 1).val - list.get(i).val;
            tunnels.add(new Tunnel(list.get(i).id, list.get(i + 1).id, wv));
        }
    }

    private static int find(int x) {
        if(x == parents[x]) {
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
}
