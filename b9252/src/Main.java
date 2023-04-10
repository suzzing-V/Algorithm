import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr1 = bf.readLine().toCharArray();
        char[] arr2 = bf.readLine().toCharArray();
        int max = 0;
        boolean[] count = new boolean[91];
        int[] memo = new int[arr1.length];
        int p = 0;
        int fp = 0;;

        for(int i = 0; i < arr1.length; i++) {
            fp = p;
            while(p < arr2.length) {
                if(arr1[i] == arr2[p]) {
                    max = 0;
                    for(int j = 0; j < i; j++) {
                        if(count[arr1[j]] == true
                            && max < memo[j]) {
                            max = memo[j];
                        }
                    }
                    memo[i] = max + 1;
                    count[arr1[i]] = true;
                    break;
                }
                p++;
            }
            if(p == arr2.length) p = fp;
        }
        Arrays.sort(memo);
        bw.write(Integer.toString(memo[arr1.length - 1]));
        bw.close();
    }
}
