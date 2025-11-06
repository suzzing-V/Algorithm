import java.util.*;
import java.io.*;

// 시간복잡도: log10^5 * 10^5 + 10^5
// 계산대 개수만큼 고객을 채우고, 계산 끝나기 전까지 종료 시간이 빠른 손님을 제거한다. 그리고 그 손님과 종료 시간이 같은 손님도 함께 제거한 후, 시간을 그 고객의 계산 종료 시간으로 업데이트해준다.
// 고객의 계산 종료 시간은 계산대에 투입된 시간 + 계산 시간 이다. 따라서 이 값이 작은 값부터 우선순위큐에서 뽑도록 구현하면 된다.
// 우선순위 큐 객체 안에서 비교할 때 compare도 활용할 것. long은 compareTo의 반환값이 될 수 없기 때문에 Long.parseLong을 사용하면 좋다.
// 반복문 종료 시점을 우선순위큐가 비었을 떄로 설정해서 처음에 틀렸다. 이렇게 설정하면 아직 고객이 남아있는데도 계산대가 모두 빈 경우 그냥 끝나버린다.
// 따라서 남아있는 고객이 없고, 우선순위큐가 비었을 때 반복문을 종료하도록 한다.
public class Main {

    private static long n;
    private static long k;
    private static long t = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        PriorityQueue<Integer> rest = new PriorityQueue<>();
        for(int i = 1; i <= k; i++) {
            rest.add(i);
        }

        int cnt = 0;
        long result = 0;
        long mul = 1;
        while(true) {
//            System.out.println("t: " + t);
            // 계산대 배치하기
            while(cnt < n && pq.size() < k) {
                st = new StringTokenizer(bf.readLine());
                long id = Long.parseLong(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                cnt ++;
                long c = rest.remove();
//                System.out.println("배치: " + id + " " + w + " "+ c);
                pq.add(new Node(id, w, c, t));
            }

            // 내보내기
            if(!pq.isEmpty()) {
                Node out = pq.remove();
//                System.out.println("내보내기: " + out.id);
                result += mul * out.id;
//                System.out.println(mul * out.id);
                rest.add((int) out.c);
                mul++;
                while(!pq.isEmpty()) {
                    Node curr = pq.peek();
                    if(curr.input + curr.w != out.input + out.w) break;
                    pq.remove();
//                    System.out.println("내보내기: " + curr.id);
//                    System.out.println(mul * curr.id);
                    result += mul * curr.id;
                    rest.add((int) curr.c);
                    mul++;
                }

                t = out.input + out.w;
            }

            if(cnt == n && pq.isEmpty()) break;
        }

        System.out.println(result);
    }

    private static class Node implements Comparable<Node> {
        long id;
        long w;
        long c;
        long input;

        Node(long id, long w, long c, long input) {
            this.id = id;
            this.w = w;
            this.c = c;
            this.input = input;
        }

        @Override
        public int compareTo(Node n) {
            // 넣고 흐른 시간에서 계산 시간을 뺀 값이 큰 것부터 뽑는다.
            if(n.input + n.w == this.input + this.w) return Long.compare(n.c, this.c);
            return Long.compare(this.input + this.w, n.input + n.w);
        }
    }
}
