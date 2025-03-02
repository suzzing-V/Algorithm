import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int k;
    static int[] candy;
    static int[] root;
    static int[] cNum;
    static Map<Integer, Integer> groupCandy = new HashMap<>();
    static PriorityQueue<Integer>[] group;


    private static class Node implements Comparable<Node> {
        private int c;
        private int s;

        Node(int c, int s) {
            this.c = c;
            this.s = s;
        }

        @Override
        public int compareTo(Node o) {
            double thisBy = (double) this.s / this.c;
            double oBy = (double) o.s / o.c;
            if(thisBy < oBy) return 1;
            else if(thisBy == oBy) return 0;
            else return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        candy = new int[n + 1];
        root = new int[n + 1];
        cNum = new int[n + 1];
        group = new PriorityQueue[n + 1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            root[i] = i;
            cNum[i] = 1;
            groupCandy.put(i, candy[i]);
            group[i] = new PriorityQueue<>();
        }

        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(bf.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            if(find(c1) != find(c2)) {
                union(c1, c2);
            }
        }

        for(int key : groupCandy.keySet()) {
//            System.out.println(key + " " + cNum[key] + " "+ groupCandy.get(key));
            group[cNum[key]].add(groupCandy.get(key));
        }

        int[] dp = new int[k];

        for(int key: groupCandy.keySet()) {
            for(int i = k - 1; i >= 0; i --) {
                if(i - cNum[key] < 0) continue;
                dp[i] = Math.max(dp[i], dp[i - cNum[key]] + groupCandy.get(key));
            }
        }


        bw.write(String.valueOf(dp[k - 1]));
        bw.close();
    }

    private static int find(int x) {
        if(root[x] == x) return x;

        return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            root[b] = a;
            cNum[a] += cNum[b];
            groupCandy.put(a, groupCandy.get(a) + groupCandy.get(b));
            groupCandy.remove(b);
        } else {
            root[a] = b;
            cNum[b] += cNum[a];
            groupCandy.put(b, groupCandy.get(b) + groupCandy.get(a));
            groupCandy.remove(a);
        }
    }
}
