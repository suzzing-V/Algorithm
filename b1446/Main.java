import java.io.*;
import java.util.*;

public class Main {

    static Road[] roads;

    static class Road implements Comparable<Road> {
        int start;
        int end;
        int dis;

        Road(int start, int end, int dis) {
            this.start = start;
            this.end = end;
            this.dis = dis;
        }

        @Override
        public int compareTo(Road o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        roads = new Road[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            roads[i] = new Road(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(roads);
        int[] dp = new int[d + 1];
        for(int i = 1; i <= d; i++) {
            dp[i] = i;
        }

        for(int i = 0; i < n; i ++) {
            Road road = roads[i];
            if(road.end > d) {
                continue;
            }
            dp[road.end] = Math.min(dp[road.end], dp[road.start] + Math.min(road.dis, road.end - road.start));
            for(int j = road.end + 1; j <= d; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1] + 1); // 갱신된 거 vs 기존 거
            }
        }

        System.out.println(dp[d]);
    }
}
