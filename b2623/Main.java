import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] next;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] input = new int[n + 1];
        next = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            next[i] = new ArrayList<>();
        }

        int before = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                int singer = Integer.parseInt(st.nextToken());
                if(j != 0) {
                    input[singer] ++;
                    next[before].add(singer);
                }
                before = singer;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(input[i] == 0) {
                queue.add(i);
                input[i] = -1;
            }
        }

        while(!queue.isEmpty()) {
            int now = queue.remove();

            result.add(now);
            for(Integer next : next[now]) {
                input[next] --;
            }

            for(int i = 1; i <= n; i++) {
                if(input[i] == 0) {
                    queue.add(i);
                    input[i] = -1;
                }
            }
        }

        if(result.size() < n) {
            System.out.println("0");
        } else {
            for(int value : result) {
                System.out.println(value);
            }
        }
    }
}
