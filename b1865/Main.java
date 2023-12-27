import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Way>[] way;
    static int INF = Integer.MAX_VALUE;

    public static class Way {
        int end;
        int time;

        Way(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(bf.readLine());
        
        for(int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            way = new ArrayList[n + 1];

            for(int j = 0; j < n + 1; j++) {
                way[j] = new ArrayList<>();
            }

            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(bf.readLine());
                
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                way[s].add(new Way(e, t));
                way[e].add(new Way(s, t));
            }

            for(int j = 0; j < w; j++) {
                st = new StringTokenizer(bf.readLine());
                
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                way[s].add(new Way(e, -t));
            }

            boolean isUpdated = false;
            for(int j = 1; j <= n; j++) {
                if(bellman_ford(j, n)) {
                    isUpdated = true;
                    break;
                }
            }
            
            if(isUpdated) bw.write("YES\n");
            else bw.write("NO\n");
        }

        bw.close();
    }

    public static boolean bellman_ford(int start, int n) {
        int[] visitTime = new int[n + 1];
        Arrays.fill(visitTime, INF);
        visitTime[start] = 0;

        boolean isUpdated = false;
        for(int i = 0; i < n - 1; i++) {
            isUpdated = false;
            for(int j = 1; j < n + 1; j++) {
                if(visitTime[j] == INF) continue;
                
                for(Way way : way[j]) {
                    if(visitTime[j] + way.time < visitTime[way.end]) {
                        visitTime[way.end] = visitTime[j] + way.time;
                        isUpdated = true;
                    }
                }
            }
            if(!isUpdated) return false;
        }

        for(int i = 1; i < n + 1; i++) {
            if(visitTime[i] == INF) continue;

            for(Way way : way[i]) {
                    if(visitTime[i] + way.time < visitTime[way.end]) {
                        visitTime[way.end] = visitTime[i] + way.time;
                        return true;
                    }
                }
        }
        return false;
    }
}