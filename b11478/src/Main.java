import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = bf.readLine().split("");
        HashMap <String, String> part = new HashMap<>();

        int count = 0;
        for(int i = 1; i <= str.length; i++) {
            for(int j = 0; j < str.length - (i - 1); j++) {
                String element = str[j];
                for(int k = 1; k < i; k++) {
                    element += str[j+k];
                }
                if(!part.containsKey(element)) {
                    part.get(element);
                    count++;
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.close();
    }
}
