import java.util.*;
import java.io.*;

// 일일히 하면 시간복잡도 10^6 * 10^6
// Kmp 쓰면 10^6 + 10^6
public class Main {

    private static String[] t;
    private static String[] p;
    private static int[] pi;
    private static List<Integer> poses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = bf.readLine().split("");
        p = bf.readLine().split("");
        pi = new int[p.length];

        getPi();
        kmp();

        System.out.println(poses.size());
        for(int i : poses) {
            System.out.print((i + 1) + " ");
        }
    }

    private static void kmp() {
        int j = 0;
        for(int i = 0; i < t.length; i ++) {
            // 다르면 같을 때까지 건너뛴다.
            // pi에는 현재까지 문자열에서 suffix와 prefix가 같고, 문자열과는 다른 가장 긴 문자열의 길이가 들어있다.
            // 다른 걸 만난 위치의 이전 위치까지 j만큼 t와 s는 같다. 이때 이 문자열의 prefix와 suffix가 같은 가장 긴 문자열이 suffix에서 시작되는 지점부터 현재 탐색하는 위치까지는 같다는 게 자명하다. 이전 위치에서 시작하는 건 어짜피 다르다.
            // 따라서 현재 위치에서 이전 위치의 pi부터 t와 s를 비교해준다.
            while(j > 0 && !t[i].equals(p[j])) {
                j = pi[j - 1];
            }

            // 같으면 넘어간다. s의 끝까지 같으면 i - (m - 1)이 일치하는 문자열이 시작하는 위치이다. 이걸 답에 넣어준다.
            if(t[i].equals(p[j])) {
                if(j == p.length - 1) {
                    poses.add(i - (p.length - 1));
                    // 넣어준 후에 현재 위치에서 pi부터 탐색 시작. j - 1까진 똑같다.
                    j = pi[j];
                } else {
                    // 끝이 아니면 j만큼 똑같은 것이므로 j++을 해준다.
                    j ++;
                }
            }
        }
    }

    private static void getPi() {
        int j = 0;
        for(int i = 1; i < p.length; i++) {
            // t의 i번째 문자와 p의 j번째 문자가 다르면 같을 때까지 건너뛴다.
            while(j > 0 && !p[i].equals(p[j])) {
                j = pi[j - 1];
            }

            // 같으면 pi값 증가시키면서 넘어간다.
            if(p[i].equals(p[j])) {
                pi[i] = ++j;
            }
        }
    }
}
