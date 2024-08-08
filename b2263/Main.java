import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> pre = new ArrayList<>();
    static int[] in;
    static int[] post;
    static int[] inIndex;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        in = new int[n];
        post = new int[n];

        for(int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        inIndex = new int[n + 1];
        for(int i = 0; i < n; i++) {
            inIndex[in[i]] = i;
        }

        makePre(0, n - 1, 0, n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print(pre.get(i) + " ");
        }
    }

    public static void makePre(int inStart, int inEnd, int postStart, int postEnd) {
        if(inStart == inEnd) {
            pre.add(in[inStart]);
            return;
        }
        if(inStart > inEnd) {
            return;
        }
        int root = post[postEnd];
        pre.add(root);
        int inIdx = inIndex[root];
        makePre(inStart, inIdx - 1, postStart, postStart + (inIdx - 1 - inStart));
        makePre(inIdx + 1, inEnd, postEnd - 1 - (inEnd - (inIdx + 1)), postEnd - 1);
    }
}
