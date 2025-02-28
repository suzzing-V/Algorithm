import java.io.*;
import java.util.*;

public class Main {

    static long n;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int bottleCnt = Long.bitCount(n);
        if(bottleCnt <= k) {
            bw.write("0");
            bw.close();
            return;
        }

        int add = 0;
        while(true) {
            n ++;
            add ++;
            bottleCnt = Long.bitCount(n);
            if(bottleCnt <= k) { // 물병의 수가 변할 때 무조건 1씩 변하므로 만약에 최종 물병 수가 k값보다 작다면 무조건 k를 거쳐서 줄어들었을 것이다. 그래서 현재 순서에서 k개로 만들 수 있다고 판단.
                break;
            }
        }
        bw.write(String.valueOf(add));
        bw.close();
    }
}
