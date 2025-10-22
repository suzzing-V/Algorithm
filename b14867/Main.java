import java.util.*;
import java.io.*;

// 각 경우의 방문을 배열로 처리하려다가, a와 b의 최댓값이 10^5이므로 배열로 만들면 10^10으로 메모리 초과라 map으로 방문처리
// 시간복잡도는 상태의 변화가 큼직큼직하므로 최대값일 때도 도달할 수 있는 상태가 많지 않을 것이라고 판단.
public class Main {

    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static Map<Node, Integer> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited.put(new Node(0, 0, 0), 0);

        bfs();
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            if(checkArrive(curr)) return;

            // A 채우기
            Node fillA = new Node(a, curr.b, curr.cnt + 1);
            if(!visited.containsKey(fillA)) {
                visited.put(fillA, 0);
                queue.add(fillA);
            }
            // B 채우기
            Node fillB = new Node(curr.a, b, curr.cnt + 1);
            if(!visited.containsKey(fillB)) {
                visited.put(fillB, 0);
                queue.add(fillB);
            }
            // A->B
            Node moveA = new Node(Math.max(curr.a + curr.b - b, 0), Math.min(curr.a + curr.b, b), curr.cnt + 1);
            if(!visited.containsKey(moveA)) {
                visited.put(moveA, 0);
                queue.add(moveA);
            }
            // A<-B
            Node moveB = new Node(Math.min(curr.a + curr.b, a), Math.max(curr.a + curr.b - a, 0), curr.cnt + 1);
            if(!visited.containsKey(moveB)) {
                visited.put(moveB, 0);
                queue.add(moveB);
            }
            // A 비우기
            Node emptyA = new Node(0, curr.b, curr.cnt + 1);

            if(!visited.containsKey(emptyA)) {
                visited.put(emptyA, 0);
                queue.add(emptyA);
            }
            // B 비우기
            Node emptyB = new Node(curr.a, 0, curr.cnt + 1);

            if(!visited.containsKey(emptyB)) {
                visited.put(emptyB, 0);
                queue.add(emptyB);
            }
        }

        System.out.println("-1");
    }

    private static boolean checkArrive(Node node) {
        if(node.a == c && node.b == d) {
            System.out.println(node.cnt);
            return true;
        }
        return false;
    }

    private static class Node {
        int a;
        int b;
        long cnt;

        Node(int a, int b, long cnt) {
            this.a = a;
            this.b =b;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj) {
            Node n = (Node) obj;
            if(n.a != this.a || n.b != this.b) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
