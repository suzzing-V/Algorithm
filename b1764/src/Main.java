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

        String[] hear = new String[n];
        for(int i = 0; i < n; i++) {
            hear[i] = bf.readLine();
        }

        String[] see = new String[m];
        for(int i = 0; i < m; i++) {
            see[i] = bf.readLine();
        }
        Arrays.sort(hear);

        int count = 0;
        String[] result = new String[m];
        int k = 0;
        for(String name : see) {
            if(BSearch(hear, name, 0, n - 1)) {
                count ++;
                result[k] = name;
                k++;
            }
        }
        bw.write(String.valueOf(count) + "\n");
        
        for(int i = 0; i < k; i++) {
            bw.write(result[i] + "\n");
        }
        bw.close();
    }

    public static boolean BSearch(String[] hear, String name, int start, int end) {
        int mid = (start + end) / 2;
        if(start > end) {
            return false;
        }

        if(name.equals(hear[mid])) {
            return true;
        } else if(name.compareTo(hear[mid]) < 0) {
            return BSearch(hear, name, 0, mid - 1);
        } else {
            return BSearch(hear, name, mid + 1, end);
        }
    }
}
