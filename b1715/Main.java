import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(bf.readLine()));
        }

        int cost = 0;
        while(pq.size() != 1) {
            int i = pq.remove();
            int j = pq.remove();
            cost += (i + j);
            pq.add(i + j);
        }

        bw.write(cost + " ");
        bw.close();
    }
}
