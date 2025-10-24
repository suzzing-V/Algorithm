import java.util.*;
import java.io.*;

// 선행 조건이 있을 때 선행조건에 따라 탐색하려면 위상정렬.
// 진입차수가 0인 노드들을 큐에 넣고, 그 노드들의 자식 노드들의 진입차수를 1 뺀다. 1 뺐는데 0이 되면 그 자식을 또 큐에 넣는다.
// 0이 되면 그 간선이 그 자식에게 유일한 간선이기에 현재 노드의 직속 자식이다.
// 이를 반복해서 탐색하면 선행조건에 맞춰서 탐색할 수 있다.
// 시간 복잡도: 1000 + 1000000
public class Main {

    private static int n;
    private static int m;
    private static Map<String, List<Edge>> conn = new HashMap<>();
    // 각 사람의 진입차수 저장
    private static Map<String, Integer> in = new HashMap<>();
    private static List<String> roots = new ArrayList<>();

    // !!!! 자기 자신이 시조이고 자식이 없을 경우도 고려
    // 진입차수 0, 진출차수 0이면 자기 자신이 시조이다.
    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        // 사람 정보 초기화
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n;i ++) {
            String name = st.nextToken();
            conn.put(name, new ArrayList<>());
            in.put(name, 0);
        }

        // 간선의 정보와 진입 차수 정보 저장
        m = Integer.parseInt(bf.readLine());
        for(int t = 0; t < m; t ++) {
            st = new StringTokenizer(bf.readLine());
            String n1 = st.nextToken();
            String n2 = st.nextToken();

            List<Edge> e2 = conn.get(n2);
            e2.add(new Edge(n1, false));
            in.put(n1, in.get(n1) + 1);
        }

        // 진입차수 0인 사람들을 큐에 넣고, 그 사람들의 자식의 진입 차수를 1 줄인다.
        // 처음에 넣을 때 roots에 저장

        Queue<String> queue = new LinkedList<>();
        for(String name : in.keySet()) {
            if(in.get(name) == 0) {
                queue.add(name);
                roots.add(name);
            }
        }

        // 반복
        PriorityQueue<Info> results = new PriorityQueue<>();
        while(!queue.isEmpty()) {
            String curr = queue.remove();
            Info info = new Info(curr);

            for(Edge edge : conn.get(curr)) {
                in.put(edge.nxt, in.get(edge.nxt) - 1);
                // 현재 간선이 다음 사람에게 이어진 유일한 간선이면 이사람은 자식이다.
                if(in.get(edge.nxt) == 0) {
                    queue.add(edge.nxt);
                    info.childs.add(edge.nxt);
                }
            }
            results.add(info);
        }

        Collections.sort(roots);
        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append("\n");
        for(int i = 0; i < roots.size(); i++) {
            sb.append(roots.get(i)).append(" ");
        }
        sb.append("\n");

        // 출력
        while(!results.isEmpty()) {
            Info info = results.remove();
            sb.append(info.name).append(" ").append(info.childs.size()).append(" ");
            while(!info.childs.isEmpty()) {
                sb.append(info.childs.remove()).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static class Info implements Comparable<Info> {
        String name;
        PriorityQueue<String> childs = new PriorityQueue<>();

        Info(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Info info) {
            return this.name.compareTo(info.name);
        }
    }

    private static class Edge {
        String nxt;
        boolean isSelected;

        Edge(String nxt, boolean isSelected) {
            this.nxt = nxt;
            this.isSelected = isSelected;
        }
    }
}
