import java.io.*;
import java.util.*;

// 시간복잡도: 전체 과정에서 우선순위 큐에 들어올 수 있는 요소의 총합이 10^5이므로, 2 * 10^5
// 도착지점을 기준으로, 같다면 출발지점을 기준으로 오름차순 정렬한 후, 각 요소의 끝 지점을 끝으로 하는 철로를 설정하면서 그 시점에 범위를 벗어나는 시작점을 제거하고, 현재 요소가 철로에 포함된다면 더한다.
public class Main {

    private static int n;
    private static PriorityQueue<Integer> starts = new PriorityQueue<>();
    private static Pos[] people;
    private static int d;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        people = new Pos[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // 사무실과 회사 위치 상관 없으므로 작은 값을 시작값으로, 큰 값을 끝 값으로 지정한다.
            people[i] = new Pos(Math.min(s, e), Math.max(s, e));
        }
        d = Integer.parseInt(bf.readLine());

        // 끝 지점 기준으로 오름차순 정렬
        Arrays.sort(people, (o1, o2) -> {
            if(o1.e == o2.e) {
                return o1.s - o2.s;
            }
            return o1.e - o2.e;
        });

        // 사람들의 끝지점을 기준으로 철로를 설정하면서 최대 사람 수 구하기
        int max = 0;
        int curr_cnt = 0;
        for(int i =0; i < people.length; i++) {
            int ts = people[i].e - d;
            int te = people[i].e;

            // 만약 현재 사람이 철로에 포함된다면 현재 상태에서 사람 수에 더하기
            if(people[i].s >= ts) {
                curr_cnt ++;
                starts.add(people[i].s);
            }

            // 철로위치가 바뀌면서 시작점이 철로에서 벗어난 사람들 빼기
            // 우선순위큐에서 가장 시작점 작은 사람부터 뺴야 포함되는 사람 나타날 때까지 뺼 수 있다.
            while(!starts.isEmpty()) {
                int curr = starts.peek();

                if(curr < ts) {
                    starts.remove();
                    curr_cnt --;
                } else {
                    break;
                }
            }

            // 최댓값 갱신
            max = Math.max(max, curr_cnt);
        }

        System.out.println(max);
    }

    private static class Pos {
        int s;
        int e;

        Pos(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
