import java.util.*;
import java.io.*;

// 시간복잡도: (2^6 + 6 * 10^5) * t
// 어이없는 실수. 오른쪽 탐색을 curr * 2로 함
public class Main {

    private static int[] nodes;
    private static int h;
    private static int k;
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line;
        StringBuilder sb = new StringBuilder();
        while((line = bf.readLine()) != null) {
//            System.out.println("시작");
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            st = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            h = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
            nodes = new int[(int)Math.pow(2, h)];
            Arrays.fill(nodes, 1);

            makeTree(1, 1, n);
//            printTree();

            for(int c = 0; c < k; c++) {
                st = new StringTokenizer(bf.readLine());
                String com = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(com.equals("C")) {
                    modify(1, 1, n, a, b);
//                    printTree();
                } else if(com.equals("P")) {
                    int answer = search(1, 1, n, a, b);
                    if(answer == 0) {
                        sb.append("0");
                    } else if(answer == -1) {
                        sb.append("-");
                    } else if(answer == 1) {
                        sb.append("+");
                    }
//                    System.out.println(sb);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void printTree() {
        for(int i = 1; i < nodes.length; i++) {
            System.out.print(nodes[i] + " ");
        }
        System.out.println();
    }
    private static int makeTree(int curr, int cl, int cr) {
        if(cl == cr) {
            if(arr[cl] == 0) {
                nodes[curr] = 0;
            } else if(arr[cl] > 0) {
                nodes[curr] = 1;
            } else if(arr[cl] < 0) {
                nodes[curr] = -1;
            }

            return nodes[curr];
        }

        int mid = (cl + cr) / 2;
        int left = makeTree(curr * 2, cl, mid);
        int right = makeTree(curr * 2 + 1, mid + 1, cr);

        return nodes[curr] = left * right;
    }

    private static void modify(int curr, int cl, int cr, int target, int value) {
        if (cl == cr) {
            if(value < 0) {
                nodes[curr] = -1;
            } else if(value == 0) {
                nodes[curr] = 0;
            } else {
                nodes[curr] = 1;
            }
            return;
        }

        int mid = (cl + cr) / 2;
        if (target <= mid) {
            modify(curr * 2, cl, mid, target, value);
        } else {
            modify(curr * 2 + 1, mid + 1, cr, target, value);
        }

        nodes[curr] = nodes[curr * 2] * nodes[curr * 2 + 1];
    }


    private static int search(int curr, int cl, int cr, int tl, int tr) {
        if(cr < tl || tr < cl) {
            return 1;
        } else if(tl <= cl && cr <= tr) {
            return nodes[curr];
        }

        int mid = (cl + cr) / 2;
        int left = search(curr * 2, cl, mid, tl, tr);
        int right = search(curr * 2 + 1, mid + 1, cr, tl, tr);

        return left * right;
    }
}
