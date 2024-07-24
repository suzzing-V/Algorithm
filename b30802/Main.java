import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        List<Integer> list = new ArrayList<>();

        while(st.hasMoreTokens()) {
            int size = Integer.parseInt(st.nextToken());
            list.add(size);
        }
        st = new StringTokenizer(bf.readLine());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int shirts = 0;
        for(int i : list) {
            if(i % t == 0) {
                shirts += i / t;
            } else {
                shirts += i / t + 1;
            }
        }
        System.out.println(shirts);
        System.out.println(n / p + " " + n % p);
    }
}
