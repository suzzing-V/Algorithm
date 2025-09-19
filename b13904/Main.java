import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static Stack<Task>[] tasks = new Stack[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i = 1; i <= 1000; i++) {
            tasks[i] = new Stack<>();
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            tasks[d].add(new Task(s, d));
            max = Math.max(max, d);
        }

        PriorityQueue<Task> possible = new PriorityQueue<>();
        int sum = 0;
        for(int i = max; i >= 1; i --) {
            while(!tasks[i].isEmpty()) {
                possible.add(tasks[i].pop());
            }

            if(!possible.isEmpty()) {
                sum += possible.remove().s;
            }
        }

        System.out.println(sum);
    }

    private static class Task implements Comparable<Task> {
        int d;
        int s;

        Task(int s, int d) {
            this.d = d;
            this.s = s;
        }

        @Override
        public int compareTo(Task t) {
            return t.s - this.s;
        }
    }
}
