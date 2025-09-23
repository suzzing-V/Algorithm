import java.io.*;
import java.util.*;

// 시간복잡도: 1000 * 9999 * log(10^4)
public class Main {

    private static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            int m = Integer.parseInt(bf.readLine());
            int[] arr = new int[m];
            int idx = 0;
            while(idx < m) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                while(st.hasMoreTokens()) {
                    arr[idx ++] = Integer.parseInt(st.nextToken());
                }
            }

            if(m % 2 == 0) sb.append(m / 2);
            else sb.append(m / 2 + 1);
            sb.append("\n");
            int mid = arr[0];
            sb.append(mid).append(" ");
            PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
            PriorityQueue<Integer> right = new PriorityQueue<>();
            for(int a = 1; a < m; a++) {
                if (a % 2 == 1) {
                    if(arr[a] < mid) {
                        left.add(arr[a]);
                    } else {
                        right.add(arr[a]);
                    }
                } else {
                    if(arr[a] < mid) {
                        left.add(arr[a]);
                    } else {
                        right.add(arr[a]);
                    }

                    if(left.size() < right.size()) {
                        left.add(mid);
                        mid = right.remove();
                    } else if(left.size() > right.size()) {
                        right.add(mid);
                        mid = left.remove();
                    }

                    sb.append(mid).append(" ");
                    if((a + 2) % 20 == 0) sb.append("\n");
                }
            }

            if(sb.charAt(sb.length() - 1) != '\n') {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
