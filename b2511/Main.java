import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int d;
    static int k;
    static int c;
    static int[] sushi;
    static Map<Integer, Integer> select = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[n + k];
        for(int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(bf.readLine());
        }
        for(int i = n; i < n + k; i++) {
            sushi[i] = sushi[i - n];
        }

        int start = 0;
        int end = k - 1;
        int max = 0;
        for(int i = 0; i < k - 1; i++) {
            select.put(sushi[i], select.getOrDefault(sushi[i], 0) + 1);
        }
        select.put(c, select.getOrDefault(c, 0) + 1);

        while(end < n + k) {
            select.put(sushi[end], select.getOrDefault(sushi[end], 0) + 1);
            max = Math.max(max, select.size());
            if(select.get(sushi[start]) == 1) {
                select.remove(sushi[start]);
            } else {
                select.put(sushi[start], select.get(sushi[start]) - 1);
            }
            start ++;
            end ++;
        }

        bw.write(String.valueOf(max));
        bw.close();
    }
}
