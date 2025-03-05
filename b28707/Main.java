import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int m;
    static Operation[] operations;
    static Map<String, Integer> costs = new HashMap<>();
    static String sort;

    private static class Operation {
        private int a1;
        private int a2;
        private int cost;

        private Operation(int a1, int a2, int cost) {
            this.a1 = a1;
            this.a2 = a2;
            this.cost = cost;
        }
    }

    private static class Sort implements Comparable<Sort> {
        int[] arr;
        int cost;

        Sort(int[] arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(Sort s) {
            return this.cost - s.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        int[] sorted = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sorted[i] = arr[i];
        }

        m = Integer.parseInt(bf.readLine());
        operations = new Operation[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            operations[i] = new Operation(a1, a2, cost);
        }

        Arrays.sort(sorted);
        sort = Arrays.toString(sorted);

        dikstra();
        if(costs.get(sort) == null) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(costs.get(sort)));
        }
        bw.close();
    }

    private static void dikstra() {
        PriorityQueue<Sort> pq = new PriorityQueue<>();
        int[] tmp = Arrays.copyOf(arr, arr.length); // 노드에 별도의 배열 저장
        pq.add(new Sort(tmp, 0));
        costs.put(Arrays.toString(arr), 0);

        while(!pq.isEmpty()) {
            Sort curr = pq.remove();
//            System.out.println(Arrays.toString(curr.arr) + " "+ curr.cost);

            for(int i = 0; i < m; i++) {
                int a1 = operations[i].a1 - 1;
                int a2 = operations[i].a2 - 1;
                int cost = operations[i].cost;

                swap(a1, a2, curr.arr);
                String as = Arrays.toString(curr.arr);
//                System.out.println(as);
                if(costs.get(as) == null || curr.cost + cost < costs.get(as)) { // 조작한 배열에 대해 이미 더 작은 비용을 만들 수 있으면 업데이트. 한번도 방문하지 않았어도 업데이트
                    costs.put(as, curr.cost + cost);
                    int[] arr1 = Arrays.copyOf(curr.arr, curr.arr.length);
                    pq.add(new Sort(arr1, curr.cost + cost));
                }
                swap(a1, a2, curr.arr);
            }
        }
    }

    private static void swap(int a1, int a2, int[] arr) {
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }
}
