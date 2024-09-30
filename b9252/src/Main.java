import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str1 = bf.readLine();
        String str2 = bf.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);


        int i = str1.length();
        int j = str2.length();
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0) {
            if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i --;
                j --;
            } else {
                if(dp[i - 1][j] > dp[i][j - 1]) {
                    i --;
                } else {
                    j --;
                }
            }
        }
        String result = sb.toString();
        for(int k = result.length() - 1; k >= 0; k --) {
            System.out.print(result.charAt(k));
        }
    }
}
