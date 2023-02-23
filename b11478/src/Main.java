import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Math;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = bf.readLine().split("");
        String[] part = new String[(int)Math.pow(2, str.length)];

        int a = 0;
        for(int i = 1; i <= str.length; i++) {
            for(int j = 0; j < str.length - (i - 1); j++) {
                String element = str[j];
                for(int k = 1; k < i; k++) {
                    element += str[j+k];
                }
                System.out.println(element);
                part[a] = element;
                a++;
            }
        }

        String[] real = Arrays.copyOfRange(part, 0, a);
        Arrays.sort(real);
        int count = 1;
        for(int i = 1; i < a; i++) {
            if(!real[i].equals(real[i - 1])) {
                count++;
            }
        }
        System.out.println(count);
        bw.close();
    }
}
