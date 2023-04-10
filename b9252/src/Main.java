import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr1 = (bf.readLine()).toCharArray();
        char[] arr2 = (bf.readLine()).toCharArray();
        int[][] memo = new int[arr1.length + 1][arr2.length + 1];

        for(int i = 1; i <= arr1.length; i++) {
            for(int j = 1; j <= arr2.length; j++) {
                if(arr1[i - 1] == arr2[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        
        bw.write(Integer.toString(memo[arr1.length][arr2.length]));
        bw.close();
    }
}
