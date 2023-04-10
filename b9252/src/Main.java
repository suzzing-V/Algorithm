import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr1 = (" " + bf.readLine()).toCharArray();
        char[] arr2 = (" " + bf.readLine()).toCharArray();
        int[][] memo = new int[arr1.length + 1][arr2.length + 1];


        for(int i = 1; i < arr1.length; i++) {
            for(int j = 1; j < arr2.length; j++) {
                if(arr1[i] == arr2[j]) {
                    memo[j][i] = memo[j - 1][i - 1] + 1;
                } else {
                    memo[j][i] = Math.max(memo[j - 1][i], memo[j][i - 1]);
                }
            }
        }
        
        bw.write(Integer.toString(memo[arr2.length - 1][arr1.length - 1]));
        bw.close();
    }
}
