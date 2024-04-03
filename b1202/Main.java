import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static List<Jewel> jewels;
    static int[] bags;

    public static class Jewel implements Comparable<Jewel>{
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            if(o.weight == this.weight)
                return o.value - this.value;
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewels = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, value));
        }

        bags = new int[k];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

        Collections.sort(jewels);
        Arrays.sort(bags);
        long result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0, j = 0; i < k; i++) {
            while(j < n && jewels.get(j).weight <= bags[i]) {
                    pq.add(jewels.get(j++).value);
            }

            if(!pq.isEmpty()) {
                result += pq.remove();
            }
        }

        bw.write(String.valueOf(result));
        bw.close();
    }
}
