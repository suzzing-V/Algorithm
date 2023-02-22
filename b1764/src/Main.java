import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] mn = bf.readLine().split(" ");
        int n = Integer.parseInt(mn[0]);
        int m = Integer.parseInt(mn[1]);

        HashMap<Integer, String> hear = new HashMap<>(n);
        for(int i = 0; i < n; i++) {
            hear.put(i, bf.readLine());
        }

        String[] see = new String[m];
        for(int i = 0; i < m; i++) {
            see[i] = bf.readLine();
        }
        
        int count = 0;
        int k = 0;
        for(String name : see) {
            if(hear.containsValue(name)) {
                count ++;
            }
        }

        String[] result = new String[count];
        for(String name : see) {
            if(hear.containsValue(name)) {
                result[k] = name;
                k++;
            }
        }
        bw.write(String.valueOf(count) + "\n");

        Arrays.sort(result);
        for(int i = 0; i < k; i++) {
            bw.write(result[i] + "\n");
        }
        bw.close();
    }
}
