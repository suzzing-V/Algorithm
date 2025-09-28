import java.io.*;
import java.util.*;

// 시간복잡도: 21^10 이지만 중복 검사로 줄일 수 있다.
// 같은 횟수를 바꿨을 때 나오는 숫자를 중복검사해준다.
// 그냥 그리디하게 제일 큰 수를 먼저 앞에 두는 방식을 사용하면, 큰 수가 여러 개 있을 때 몇번째 수를 선택해야 할지 애매하다.
// 가장 뒤에 있는 큰 수를 선택해서 바꾸면 중간에 k를 다 쓸 경우에는 최대값을 도출할 수 있지만, 다 배치해도 k가 남을 경우 이 방법이 최댓값이 아닐 수 있다.
// 1220 2의 경우, 2210 -> 2201이 되므로, 이땐 뒤의 2를 선택하는 것보다 앞의 2를 선택하는 게 더 큰 값을 도출할 수 있다.
public class Main {

    private static String str_n;
    private static int n;
    private static int k;
    private static Set<String>[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        str_n = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        n = str_n.length();
        visited = new HashSet[k + 1];
        if(str_n.length() == 1) {
            System.out.println(-1);
            return;
        }

        for(int i = 1; i <= k ; i++) {
            visited[i]= new HashSet<>();
        }

        System.out.println(bfs());
    }

    private static String bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, str_n));

        int max = -1;
        // 하나씩 다 바꿔본다.
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.cnt + " " + String.join("", curr.nums));

            // 횟수 다 썼으면 최댓값 갱신
            if(curr.cnt == k) {
//                System.out.println("다씀");
                max = Math.max(max, Integer.parseInt(curr.num));
                continue;
            }

            // 현재 횟수에서만 중복검사한다.
            for(int i = 0; i < n - 1; i ++) {
                for(int j = i + 1; j < n; j ++) {
                    StringBuilder sb = new StringBuilder(curr.num);
                    char c1 = curr.num.charAt(i);
                    char c2 = curr.num.charAt(j);

                    sb.setCharAt(i, c2);
                    sb.setCharAt(j, c1);
                    if(sb.charAt(0) == '0' || visited[curr.cnt + 1].contains(sb.toString())) {
                        continue;
                    }

                    visited[curr.cnt + 1].add(sb.toString());
                    queue.add(new Node(curr.cnt + 1, sb.toString()));
                }
            }
        }

        return String.valueOf(max);
    }

    private static class Node {
        int cnt;
        String num;

        Node(int cnt, String num) {
            this.cnt = cnt;
            this.num = num;
        }
    }
}
