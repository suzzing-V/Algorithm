import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] liquids;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        liquids = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquids);

        int start = 0;
        int end = n - 1;
        long result = Long.MAX_VALUE;
        int l1 = 0;
        int l2 = 0;
        while(start < end) {
            long mix = liquids[start] + liquids[end];
            if(mix < 0) {
                if(-mix < result) {
                    result = -mix;
                    l1 = start;
                    l2 = end;
                }
                start ++;
            } else if(mix > 0) {
                if(mix < result) {
                    result = mix;
                    l1 = start;
                    l2 = end;
                }
                end --;
            } else {
                l1 = start;
                l2 = end;
                break;
            }
        }

        bw.write(liquids[l1] + " " + liquids[l2]);
        bw.close();
    }
}
