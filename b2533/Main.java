import java.io.*;
import java.util.*;

public class Main {

    private static int cnt = 0;
    private static List<Integer>[] friends;
    private static int n;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        friends = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n ; i++) {
            friends[i] = new ArrayList<>();
        }
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            friends[f1].add(f2);
            friends[f2].add(f1);
        }

        dfs(1);
        bw.write(String.valueOf(cnt));
//        System.out.println(Arrays.toString(isEarly));
        bw.close();
    }

    private static int dfs(int curr) {
        visited[curr] = true;
        if(friends[curr].size() == 1 && curr != 1) { // 친구 하나인 경우가 무조건 리프노드는 아니다. 첫 노드가 친구 하나일 수도 있다. 따라서 첫 노드가 아니라는 조건을 붙여줘야 한다.
            return 1;
        }

        int not_early = 0;
//        System.out.println(curr);
        for(int f : friends[curr]) {
            if(visited[f]) continue;
            not_early += dfs(f);
//            System.out.println(f + " " + not_early);
        }
        if(not_early != 0) {
            cnt ++;
            return 0;
        }
        return 1;
    }
}
