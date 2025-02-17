import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int k;
    static int x;
    static List<ArrayList<Integer>> roads;
    static int[] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        roads = new ArrayList<ArrayList<Integer>>();
        dis = new int[n + 1];

        for(int i = 0; i < n + 1; i++) {
            roads.add(new ArrayList<>());
            dis[i] = -1;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            roads.get(start).add(end);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        dis[x] = 0;
        while(!q.isEmpty()) {
            int curr = q.remove();
//            System.out.println(curr);
            for(int n : roads.get(curr)) {
                if(dis[n] == -1) {
                    dis[n] = dis[curr] + 1;
//                System.out.println(n + " " + dis[n]);
                    q.add(n);
                }
            }
        }

        boolean isExist = false;
        for(int i = 0; i < n + 1; i++) {
            if(dis[i] == k) {
                bw.write(String.valueOf(i) + "\n");
                isExist = true;
            }
        }

        if(!isExist) {
            bw.write("-1");
        }
        bw.close();
    }
}
