import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            List<Integer>[] connect = new ArrayList[n + 1];
            int[] teams = new int[n + 1];
            for(int j = 1; j <= n; j++) {
                int team = Integer.parseInt(st.nextToken());
                teams[j] = team;
                connect[j] = new ArrayList<>();
            }
            for(int j = 1; j <= n;j++) {
                for(int k = j + 1; k <= n; k++) {
                    connect[teams[j]].add(teams[k]);
                }
            }

            int m = Integer.parseInt(bf.readLine());
            for(int j = 1; j <= m; j++) {
                st = new StringTokenizer(bf.readLine());
                int t1 = Integer.parseInt(st.nextToken());
                int t2 = Integer.parseInt(st.nextToken());
                if(connect[t1].contains(t2)) {
                    connect[t1].remove((Integer)t2);
                    connect[t2].add(t1);
                } else {
                    connect[t2].remove((Integer)t1);
                    connect[t1].add(t2);
                }
            }

            int[] degree = new int[n + 1];
            for(int j = 1; j <= n; j++) {
                for(int next : connect[j]) {
                    degree[next] ++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for(int j = 1; j <= n; j++) {
                if(degree[j] == 0) {
                    queue.add(j);
                    degree[j] = -1;
                }
            }

            List<Integer> result = new ArrayList<>();
            boolean isNormal = true;
            while(!queue.isEmpty()) {
                int curr = queue.remove();
                result.add(curr);

                for(int next : connect[curr]) {
                    degree[next] --;
                }

                int cnt = 0;
                for(int j = 1; j <= n; j++) {
                    if(degree[j] == 0) {
                        if(cnt == 1) {
                            isNormal = false;
                            break;
                        }
                        queue.add(j);
                        degree[j] = -1;
                        cnt ++;
                    }
                }
                if(!isNormal) {
                    break;
                }
            }
            if(!isNormal) {
                bw.write("?");
            } else {
                if(result.size() != n) {
                    bw.write("IMPOSSIBLE");
                } else {
                    for(int a : result) {
                        bw.write(a + " ");
                    }
                }
            }
            bw.write("\n");
        }
        bw.close();
    }
}
