package b25501;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String[] strArr = new String[n];

        for(int i = 0; i < n; i++) {
            strArr[i] = bf.readLine();
        }

        for(int i = 0; i < n; i++) {
            bw.write(Integer.toString(isPalindrome(strArr[i], 0, strArr[i].length() - 1)) + " " + Integer.toString(count));
            bw.newLine();
            count = 0;
        }
        bw.close();
    }

    public static int isPalindrome(String str, int left, int right) {
        count++;
        if(left >= right) {
            return 1;
        }
        else if(str.charAt(left) != str.charAt(right)) {
            return 0;
        }
        return isPalindrome(str, ++left, --right);
    }
}
