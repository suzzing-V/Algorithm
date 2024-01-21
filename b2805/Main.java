import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = bf.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        split = bf.readLine().split(" ");
        int[] trees = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(split[i]);
            max = Math.max(trees[i], max);
        }

        int result = findMaxHeight(0, max, m, trees);
        bw.write(String.valueOf(result - 1));
        bw.close();
    }

    public static int findMaxHeight(int start, int end, int m, int[] trees) {
        if(start >= end) return start;
        int mid = (start + end) / 2;
        
        if(cutTrees(trees, mid) >= m) {
            return findMaxHeight(mid + 1, end, m, trees);
        } else {
            return findMaxHeight(start, mid, m, trees);
        }
    }

    public static long cutTrees(int[] trees, int mid) {
        long total = 0;
        for(int i = 0; i < trees.length; i++) {
            if(trees[i] > mid) total += trees[i] - mid; 
        }
        return total;
    }
}
