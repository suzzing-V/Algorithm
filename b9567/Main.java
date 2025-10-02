import java.util.*;
import java.io.*;

// 시간복잡도: t * (1000 + 1000 * 1000)
// 끝 번호 기준으로 정렬 후, 현재 범위에서 남은 책 중 가장 책 번호 작은 것 선택.
// 그러면 뒤에 등장하는 사람들이 더 넓은 범위에서 책을 선택할 수 있다.
public class Main {

    private static int t;
    private static int n;
    private static int m;
    private static PriorityQueue<Range> ranges = new PriorityQueue<>();
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            selected = new boolean[n + 1];
            ranges = new PriorityQueue<>();
            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ranges.add(new Range(a, b));
            }

            int max = 0;
            while(!ranges.isEmpty()) {
                // 시작 번호가 작은 학생부터 책 배부.
                Range curr = ranges.remove();

                // 범위 내 책 중 존재하는 것 중 가장 안 겹치는 책 선택
                for(int s = curr.a; s <= curr.b; s++) {
                    if(!selected[s]) {
                        selected[s] = true;
                        max ++;
                        break;
                    }
                }
            }

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    private static class Range implements Comparable<Range> {
        int a;
        int b;

        Range(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Range r) {
            if(r.b == this.b) {
                return (this.b - this.a) - (r.b - r.a);
            }

            return this.b - r.b;
        }
    }
}
