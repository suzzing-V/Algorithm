import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Math;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = bf.readLine();
        int[] count = new int[26];
        int ex = 0;

        Arrays.fill(count, 0);
        for(int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 97] ++;
        }

        for(int i = 0; i < 26; i++) {
            if(count[i] > 1) {
                ex += count[i] - 1;
            }
        }
        bw.write(String.valueOf((int)(Math.pow(2, str.length()) - 1 - ex)));
        bw.close();
    }
}
