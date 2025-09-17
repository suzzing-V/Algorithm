import java.util.*;
import java.io.*;

// 시간 복잡도: 9!
// bfs의 가중치가 1이 아니면 다익스트라.
// 모든 간선의 가중치가 1인 bfs에서는 우선순위 큐를 사용하지 않고, 넣을때만 검사하는게 효율적이다. 나중에 더 짧은 경로가 발견될 일이 없기 때문이다. 계속 증가하는 형태
// 다익스트라는 꺼낼 때, 들어갈 때 다 검사해야하고, 우선순위 큐 사용하는 게 좋다.
public class Main {

    private static Map<String, Integer> dist = new HashMap<>();
    private static int[] dir = {3, -3, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int blank = 0;
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) {
                    blank = i * 3 + j;
                }
                sb.append(num);
            }
        }

        System.out.println(bfs(blank, sb.toString()));

    }

    private static int bfs(int blank, String status) {
        Queue<Node> pq = new LinkedList<>();
        pq.add(new Node(blank, 0, status));
        dist.put(status, 0);

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.blank + " " + curr.cnt);
//            for(int i = 0; i < 3; i++) {
//                System.out.print(curr.status.charAt(i) + " ");
//            }
//            System.out.println();
//            for(int i = 3; i < 6; i++) {
//                System.out.print(curr.status.charAt(i) + " ");
//            }
//            System.out.println();
//            for(int i = 6; i < 9; i++) {
//                System.out.print(curr.status.charAt(i) + " ");
//            }
//            System.out.println();

            if(curr.status.equals("123456780")) {
                return curr.cnt;
            }

            // 이미 더 적은 횟수로 해당 상태 만든 전적이 있으면 탐색 멈춘다.
//            if(dist.get(curr.status) != null && dist.get(curr.status) < curr.cnt) {
//                continue;
//            }

            for(int i = 0; i < 2; i++) {
                int ni = curr.blank + dir[i];

                if(ni < 0 || ni >= 9) continue;

                String swap = swapBlank(curr.status, curr.blank, ni);
                if(dist.get(swap) != null && dist.get(swap) <= curr.cnt + 1) continue;

                dist.put(swap, curr.cnt + 1);
                pq.add(new Node(ni, curr.cnt + 1, swap));
            }

            for(int i = 2; i < 4; i++) {
                int ni = curr.blank + dir[i];

                if(ni < 0 || ni >= 9) continue;
                // 행 검사
                if(ni / 3 != curr.blank / 3) continue;

                String swap = swapBlank(curr.status, curr.blank, ni);
                if(dist.get(swap) != null && dist.get(swap) <= curr.cnt + 1) continue;

                dist.put(swap, curr.cnt + 1);
                pq.add(new Node(ni, curr.cnt + 1, swap));
            }
        }

        return -1;
    }

    private static String swapBlank(String status, int idx1, int idx2) {
        StringBuilder sb = new StringBuilder();
        char n1 = status.charAt(idx1);
        char n2 = status.charAt(idx2);

        for(int i = 0; i < 9; i++) {
            if(i == idx1) {
                sb.append(n2);
            } else if(i == idx2) {
                sb.append(n1);
            } else {
                sb.append(status.charAt(i));
            }
        }

        return sb.toString();
    }

    private static class Node {
        int blank;
        int cnt;
        String status;

        Node(int blank, int cnt, String status) {
            this.blank = blank;
            this.cnt = cnt;
            this.status = status;
        }
    }
}
