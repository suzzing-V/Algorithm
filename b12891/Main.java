import java.io.*;
import java.util.*;

public class Main {

    static int s;
    static int p;
    static String dna;
    static int[] condition = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        dna = bf.readLine();
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < 4; i++) {
            condition[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = p - 1;
        int[] acgt = new int[4];
        for(int i = 0; i < end; i++) {
            char c = dna.charAt(i);
            if(c == 'A') acgt[0] ++;
            if(c == 'C') acgt[1] ++;
            if(c == 'G') acgt[2] ++;
            if(c == 'T') acgt[3] ++;
        }

        int answer = 0;
        while(end < s) {
            char ec = dna.charAt(end);
            if(ec == 'A') acgt[0] ++;
            if(ec == 'C') acgt[1] ++;
            if(ec == 'G') acgt[2] ++;
            if(ec == 'T') acgt[3] ++;

            if(acgt[0] >= condition[0] && acgt[1] >= condition[1] && acgt[2] >= condition[2] && acgt[3] >= condition[3]) {
                answer ++;
            }
            char sc = dna.charAt(start);
            if(sc == 'A') acgt[0] --;
            if(sc == 'C') acgt[1] --;
            if(sc == 'G') acgt[2] --;
            if(sc == 'T') acgt[3] --;
            start ++;
            end ++;
        }

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
