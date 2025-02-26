import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] time;
    static int[] acc;
    static int[] degree;
    static List<Integer>[] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        time = new int[n + 1];
        acc = new int[n + 1];
        degree = new int[n + 1];
        connect = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            connect[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for(int j = 0; j < b; j++) {
                int work = Integer.parseInt(st.nextToken());
                degree[i] ++;
                connect[work].add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                queue.add(i);
                degree[i] = -1;
                acc[i] = time[i];
            }
        }

        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int work : connect[curr]) {
                degree[work] --;
                acc[work] = Math.max(acc[work], acc[curr] + time[work]);
            }

            for(int i = 1; i <= n; i++) {
                if(degree[i] == 0) {
                    queue.add(i);
                    degree[i] = -1;
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, acc[i]);
        }
        bw.write(String.valueOf(max));
        bw.close();
    }
}
