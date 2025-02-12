import java.io.*;
import java.util.*;

public class Main {

    static int[] postSt;
    static int[] preSt;
    static int n;
    static int m;
    static int first;
    static int last;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        preSt = new int[1000001];
        postSt = new int[1000001];

        st = new StringTokenizer(bf.readLine());
        int prev = 0;
        first = 0;
        last = 0;

        for(int i = 0; i < n; i++) {
            int pk = Integer.parseInt(st.nextToken());
            if(i == 0) {
                first = pk;
            }
            if(i == n - 1) {
                last = pk;
            }

            preSt[pk] = prev;
            if(prev != 0) {
                postSt[prev] = pk;
            }
            prev = pk;
        }
        preSt[first] = last;
        postSt[last] = first;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            String cmd = st.nextToken();
            int a = 0;
            int b = 0;
            if(cmd.equals("BN")) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                int nextStation = postSt[a];
                sb.append(nextStation).append("\n");
                postSt[a] = b;
                preSt[nextStation] = b;
                preSt[b] = a;
                postSt[b] = nextStation;
            } else if(cmd.equals("BP")) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                int prevStation = preSt[a];
                sb.append(prevStation).append("\n");
                preSt[a] = b;
                postSt[prevStation] = b;
                preSt[b] = prevStation;
                postSt[b] = a;
            } else if(cmd.equals("CN")) {
                a = Integer.parseInt(st.nextToken());
                int nextStation = postSt[a];
                sb.append(nextStation).append("\n");
                int nextNextStation = postSt[nextStation];
                postSt[a] = nextNextStation;
                preSt[nextNextStation] = a;
            } else if(cmd.equals("CP")) {
                a = Integer.parseInt(st.nextToken());
                int prevStation = preSt[a];
                sb.append(prevStation).append("\n");
                int prevPrevStation = preSt[prevStation];
                preSt[a] = prevPrevStation;
                postSt[prevPrevStation] = a;
            }
        }
        System.out.println(sb);
    }
}
