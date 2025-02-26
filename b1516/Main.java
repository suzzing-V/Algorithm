import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] time;
    static int[] degree;
    static int[] answer;
    static List<Integer>[] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        time = new int[n + 1];
        degree = new int[n + 1];
        answer = new int[n + 1];
        connect = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            connect[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int building = Integer.parseInt(st.nextToken());
                if(building == -1) break;
                connect[building].add(i);
                degree[i] ++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                queue.add(i);
                degree[i] = -1;
                answer[i] = time[i];
            }
        }

        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int building : connect[curr]) {
                degree[building] --;
                answer[building] = Math.max(time[building] + answer[curr], answer[building]);
            }

            for(int i = 1; i <= n; i++) {
                if(degree[i] == 0) {
                    queue.add(i);
                    degree[i] = -1;
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            bw.write(answer[i] + "\n");
        }
        bw.close();
    }
}
