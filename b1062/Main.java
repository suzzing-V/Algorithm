import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static List<Integer> alpha_combi = new ArrayList<>();
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        makeAlphaCombi(0, 0, 0);
        cnt = new int[alpha_combi.size()];

        for(int i = 0; i < n; i++) {
            String s = bf.readLine();
            int usedAlpha = 0;
            for(int j = 0; j < s.length(); j++) {
                int alpha = s.charAt(j) - 'a';
                usedAlpha |= (1 << alpha);
            }

            for(int j = 0; j < alpha_combi.size(); j++) {
                int combi = alpha_combi.get(j);
//                System.out.println(Integer.toBinaryString(usedAlpha) + " " + Integer.toBinaryString(combi));
//                System.out.println(usedAlpha + " " + combi);
                if(Integer.bitCount(usedAlpha) > Integer.bitCount(combi) || Integer.bitCount(combi | usedAlpha) != Integer.bitCount(combi)) {
                    continue;
                }
                cnt[j] ++;
            }
        }

        int max = 0;
        for(int i = 0; i < alpha_combi.size(); i++) {
            max = Math.max(max, cnt[i]);
        }
        bw.write(String.valueOf(max));
        bw.close();
    }

    private static void makeAlphaCombi(int alpha, int combi, int cnt) {
        if(alpha == 27) {
            return;
        }

        if(cnt == k) {

            alpha_combi.add(combi);
//            System.out.println(Integer.toBinaryString(combi));
            return;
        }

        makeAlphaCombi(alpha + 1, combi, cnt);
        makeAlphaCombi(alpha + 1, combi | (1 << alpha), cnt + 1);
    }
}
