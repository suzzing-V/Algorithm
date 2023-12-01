import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] in = new int[n + 1];
        HashSet<Integer>[] graph = new HashSet[n + 1];
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            in[b] ++;
        }

        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 1; i < in.length; i++) {
            if(in[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            bw.write(String.valueOf(now) + " " );
            Set<Integer> nowList = graph[now];
            for(int num : nowList) {
                in[num]--;
                if(in[num] == 0) {
                    queue.add(num);
                }
            }
        }
        bw.close();
    }
}