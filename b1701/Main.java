import java.util.*;
import java.io.*;

// 시간복잡도: 5000*5000
public class Main {

    private static Map<String, Integer> part = new HashMap<>();
    private static String str;
    private static int[] pi;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str = bf.readLine();

        // 시작점을 다르게 하면서 pi 배열 구하기.
        for(int i = 0; i < str.length(); i++) {
            String substr = str.substring(i, str.length());
            getPi(substr);
        }

        System.out.println(answer);
    }

    private static void getPi(String pattern) {
        pi = new int[pattern.length()];

        int j = 0;
        for(int i = 1; i < pattern.length(); i++) {
            // 다르면 같을 때까지 건너뛰기
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            // 같으면 지금까지 j만큼 같은 것이므로 j가 pi의 값
            if(pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
            answer = Math.max(answer, pi[i]);
        }
    }
}
