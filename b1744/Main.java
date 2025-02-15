import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
    static PriorityQueue<Integer> min = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(bf.readLine());
            if(num > 0) max.add(num);
            else min.add(num);
        }

        int result = 0;
        while(!max.isEmpty()) {
            if(max.size() == 1) {
                result += max.remove();
                break;
            }
            int n1 = max.remove();
            int n2 = max.remove();
            if(n2 == 1) {
                result += n1 + n2;
                continue;
            }
//            System.out.println("max: " + n1 + " " + n2);
            result += n1 * n2;
        }

        while(!min.isEmpty()) {
            if(min.size() < 2) {
                result += min.remove();
                break;
            }
            int m1 = min.remove();
            int m2 = min.remove();
            if(m2 == 0) {
                break;
            }
//            System.out.println("min: " + m1 + " " + m2);
            result += m1 * m2;
        }
        bw.write(String.valueOf(result));
        bw.close();
    }
}
