import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static ArrayList<Integer>[] sub;
    private static int dp[];
    // dp[i] : 직원 i가 모든 부하 직원에게 전화돌리는 데 걸리는 시간

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        sub = new ArrayList[n];
        dp = new int[n];
        for(int i = 0; i < n; i++) {
            sub[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for(int i = 1; i < n; i++) {
            int master = Integer.parseInt(st.nextToken());
            sub[master].add(i);
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int master) {
        if(sub[master].isEmpty()) { // 부하가 없다면 전화돌리는데 걸리는 시간은 0
            return 0;
        }

        // 전화 오래 걸리는 부하직원부터 전화해야 빨리 끝낼 수 있다. 다른 부하직원한테 전화하는 동안 그 부하직원이 전화돌릴 수 있기 때문
        // 따라서 부하들을 전화 시간에 따라 정렬한다.
        List<Integer> subTime = sub[master];
        for(int i = 0; i < subTime.size(); i++) {
            dfs(subTime.get(i));
        }
        Collections.sort(subTime, (o1, o2) -> dp[o2] - dp[o1]);

        // 가장 오래 걸리는 부하부터 전화한다. 가장 큰 (그 부하한테 전화할 때까지 걸리는 시간 + 그 부하가 전화돌리는데 걸리는 시간)이 내 아래 모든 부하직원에게 전화가 돌아가는 시간이다.
        // 첫번째 직원한테 전화하는데 걸리는 시간은 1분, 두번쨰 직원한테 전화하는데 걸리는 시간은 2분...
        // 모든 부하가 전화를 끝마쳐야 한다. 따라서 전화하는 데 걸리는 시간 + 전화시간 이 가장 큰 사람이 끝나야 끝나는 것이므로 총 걸리는 최소 시간은 이 값이다.
        int max = 0;
        int c = 1;
        for(int i = 0; i < subTime.size(); i++) {
            max = Math.max(max, dp[subTime.get(i)] + c);
            c ++;
        }

        return dp[master] = max;
    }
}
