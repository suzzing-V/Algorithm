import java.util.*;
import java.io.*;

public class Main {

    static int t;
    static int preIdx;
    static List<Integer> preOrder;
    static List<Integer> inOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            preOrder = new ArrayList<>();
            inOrder = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                preOrder.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                inOrder.add(Integer.parseInt(st.nextToken()));
            }

            preIdx = 0;
            postOrder(0, n - 1);
            System.out.println();
        }
    }

    private static void postOrder(int start, int end) {
//        System.out.println(preIdx + " " + start + " "+ end);
        if(start == end) {
            System.out.print(inOrder.get(start) + " ");
            return;
        }
        if(start > end) {
            preIdx --;
            return;
        }

        int preNode = preOrder.get(preIdx);
//        System.out.println("preNode: " + preNode);
        int divideIdx = inOrder.indexOf((Integer)preNode);

        preIdx ++;
        postOrder(start, divideIdx - 1);
        preIdx ++;
        postOrder(divideIdx + 1, end);
        System.out.print(preNode + " ");
    }
}
