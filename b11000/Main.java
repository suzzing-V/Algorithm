import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static PriorityQueue<Time> times = new PriorityQueue<>();
    static PriorityQueue<Integer> rooms = new PriorityQueue<>();

    static class Time implements Comparable<Time> {
        private int start;
        private int end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times.add(new Time(start, end));
        }

        rooms.add(times.remove().end);
        while(!times.isEmpty()) {
            Time curr = times.remove();
            int fastTime = rooms.peek();
            if(!(rooms.isEmpty() || curr.start < fastTime)) rooms.remove();
            rooms.add(curr.end);
        }

        bw.write(String.valueOf(rooms.size()));
        bw.close();
    }
}
