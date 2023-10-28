import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = new String[2];
        split = bf.readLine().split(" ");
        
        int k = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        
        int[] lan = new int[k];
        long max = 0;
        for(int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(bf.readLine());
            max = Math.max(max, lan[i]);
        }

        max ++;
        long result = searchMaxLength(lan, n, 1, max);
        bw.write(String.valueOf(result - 1));
        bw.close();
    }

    public static long searchMaxLength(int[] lan, int n, long start, long end) {
        if(start >= end) return start;
        long mid = (start + end) / 2;
        long madeLan = madeLan(mid, lan);

        if(madeLan < n) {
            return searchMaxLength(lan, n, start, mid);
        }
        return searchMaxLength(lan, n, mid + 1, end);
    }

    public static long madeLan(long mid, int[] lan) {
        long count = 0;
        for(int i = 0; i < lan.length; i++) {
            count += lan[i] / mid;
        }
        return count;
    }
}
