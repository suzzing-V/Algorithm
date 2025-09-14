import java.util.*;
import java.io.*;

// 시간 복잡도: 200000 * log200000
// 시간 역순으로 현재 시간보다 데드라인이 크거나 같은 것들 중에 제일 라면 많은 문제 뽑기
// 시간 순으로 하면 뽑은 문제보다 라면수는 적지만 데드라인이 짧은 걸 골랐을 경우 더 유리한 경우가 생긴다.
// 데드라인이 짧은 것부터 뽑으면 큰 라면을 고를 수 없다.
public class Main {

    private static int n;
    private static PriorityQueue<Problem> possible = new PriorityQueue<>();
    private static int sum = 0;
    private static Stack<Problem>[] problems;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        problems = new Stack[n + 1];
        for(int i = 1; i <= n; i++) {
            problems[i] = new Stack<>();
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int dead = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            problems[dead].add(new Problem(dead, ramen));
        }

        // 현재 초보다 데드라인이 같거나 큰 문제 중 컵라면 가장 많은 문제 선택
        int t = n;
        while(t > 0) {
            // 데드라인이 t인 문제들 가능한 문제들 큐에 넣기
            while(!problems[t].isEmpty()) {
                possible.add(problems[t].pop());
            }

            // 가능한 문제들 중 라면 가장 많은 거 뽑기
            if(!possible.isEmpty()) {
                Problem curr = possible.remove();

                sum += curr.ramen;
            }
            // 만약 가능한 문제가 없으면 나중에 남은 문제들 적절히 들어갈거임

            t --;
        }

        System.out.println(sum);
    }

    private static class Problem implements Comparable<Problem> {
        int dead;
        int ramen;

        Problem(int dead, int ramen) {
            this.dead = dead;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem p) {
                return p.ramen - this.ramen;
        }
    }
}
