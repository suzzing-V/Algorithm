import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static List<Integer>[] conn;
    static int[] degree;
    static int[] semester;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        conn = new ArrayList[n + 1];
        degree = new int[n + 1];
        semester = new int[n + 1];

        for(int i = 0; i < n + 1; i ++) {
            conn[i] = new ArrayList<>();
            semester[i] = 1;
        }

        for(int i = 0; i < m ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            degree[b] ++;
            conn[a].add(b);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int curr = queue.remove();

            for(int next : conn[curr]) {
                degree[next] --;
                semester[next] = Math.max(semester[next], semester[curr] + 1);
                if(degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            bw.write(semester[i] + " ");
        }
        bw.close();
    }
}
