import java.util.*;
import java.io.*;

public class Main {

    private static int n;

    private static Stack<Lecture>[] lectures = new Stack[10001];
    // 가장 마지막 날짜
    private static int max_d = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        for(int i = 1; i <= 10000; i++) {
            lectures[i] = new Stack<>();
        }

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            lectures[d].add(new Lecture(p, d));
            max_d = Math.max(max_d, d);
        }

        int sum = 0;
        PriorityQueue<Lecture> pq = new PriorityQueue<>();

        for(int i = max_d; i >= 1; i--) {
            // 데드라인이 i인 강연들 pq에 넣기. 이제 선택할 수 있다.
            while(!lectures[i].isEmpty()) pq.add(lectures[i].pop());

            // 선택할 수 있는 강의 중 가장 돈 많이 주는 강의 고른다.
            if(!pq.isEmpty()) {
                Lecture selected = pq.remove();
                sum += selected.p;
            }
            // 선택할 수 있는 강의 없으면 그냥 넘어간다.
        }

        System.out.println(sum);
    }

    private static class Lecture implements Comparable<Lecture> {
        int p;
        int d;

        Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Lecture l) {
            return l.p - this.p;
        }
    }
}
