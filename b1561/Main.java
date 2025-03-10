import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(m >= n) { // 놀이기구 수가 애들 수보다 많으면 마지막 사람은 애들 수 번호에 해당하는 놀이기구 탄다.
            bw.write(String.valueOf(n));
            bw.close();
            return;
        }

        times = new int[m];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 애들 태울 수 있는 최소 시간 구하기
        long time = binarySearch(1, 60000000000L); // 20000000000명이 운영시간 30분인 놀이기구를 타는 경우가 최대로 걸리는 시간이다.
        long start = 0; // (time - 1)분에 태울 수 있는 사람 수
        for(int i = 0; i < m; i++) {
            start += (time - 1) / times[i] + 1;
        }

        // 남은 사람들 태우기
        for(int i = 0; i < m; i ++) {
            if(time % times[i] == 0) { // time분에 탈 수 있는 놀이기구는 운영시간으로 나누어 떨어지는 놀이기구다.
                start ++;
                if(start == n) { // 마지막 사람일 경우.
                    bw.write(String.valueOf(i + 1));
                    bw.close();
                    return;
                }
            }
        }
    }

    private static long binarySearch(long left, long right) {
        if(left == right) {
            return left;
        }

        long mid = (left + right) / 2;
        long people = 0;
        for(int i = 0; i < m; i++) {
            people += mid / times[i] + 1; // mid분 안에 운영시간이 times[i]인 놀이기구를 탈 수 있는 인원 수. 0일 때 다 타므로 + 1.
        }

        if(people < n) {
            return binarySearch(mid + 1, right);
        }
        return binarySearch(left, mid); // lower bound
    }
}
