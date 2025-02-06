import java.io.*;
import java.util.*;

public class Main {

    static String str1;
    static String str2;
    static String str3;
    static int[][][] lcs;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str1 = bf.readLine();
        str2 = bf.readLine();
        str3 = bf.readLine();
        lcs = new int[str1.length() + 1][str2.length() + 1][str3.length() + 1];

        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                for(int k = 1; k <= str3.length(); k++) {
                    if(str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
                        lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
                    } else {
                        lcs[i][j][k] = Math.max(Math.max(lcs[i - 1][j][k], lcs[i][j - 1][k]), lcs[i][j][k - 1]);
                    }
                }
            }
        }

        bw.write(String.valueOf(lcs[str1.length()][str2.length()][str3.length()]));
        bw.close();
    }
}
