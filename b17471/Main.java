import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] people;
    private static ArrayList<Integer>[] conn;
    private static int min = Integer.MAX_VALUE;
    private static int total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        people = new int[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            total += people[i];
        }

        conn = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            conn[i] = new ArrayList<Integer>();
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) conn[i].add(Integer.parseInt(st.nextToken()));
        }

        // 조합 만들기
        dfs(1, 0, new ArrayList<>());
        if(min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }

    private static void dfs(int node, int sum, List<Integer> selected) {
        if(node > n) { // 조합 완성
            // 선거구는 적어도 하나의 지역을 포함해야 한다.
            if(selected.size() == 0 || selected.size() == n) return;
            List<Integer> notSelected = new ArrayList<>();
            for(int i = 1; i <= n; i++) {
                if(!selected.contains(i)) notSelected.add(i);
            }
            // 두 그룹이 서로 연결되어 있다면 조건에 맞다. 따라서 두 선거구의 인구 차이의 최솟값을 갱신해준다.
            if(isConnected(selected) && isConnected(notSelected)) {
                min = Math.min(min, Math.abs(total - sum - sum));
            }
            return;
        }
        selected.add(node);
        dfs(node + 1, sum + people[node], selected);
        selected.remove(selected.size() - 1);
        dfs(node + 1, sum, selected);
    }

    private static boolean isConnected(List<Integer> nodes) {
        // 선거구가 서로 다 이어져있으려면 각각 요소들이 다 연결돼있어야 한다.
        for(int i = 0; i < nodes.size(); i++) {
            int n1 = nodes.get(i);
            for(int j = i + 1; j < nodes.size(); j++) {
                int n2 = nodes.get(j);
                // n1 에서 n2로 갈 수 없다면 이어져 있지 않은 것
                if(!canGo(n1, n2, nodes)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canGo(int start, int end, List<Integer> nodes) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        while(!q.isEmpty()) {
            int curr = q.remove();
            if(curr == end) return true;

            visited[curr] = true;
            for(int next : conn[curr]) {
                // 이미 방문했거나 현재 탐색하고 있는 그룹이 아니라면 탐색하지 않는다.
                if(visited[next] || !nodes.contains(next)) continue;
                q.add(next);
            }
        }
        return false;
    }
}
