import java.io.*;
import java.util.Arrays;

import javax.lang.model.util.ElementScanner14;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] numStr = bf.readLine().split(" ");
        int n = Integer.parseInt(numStr[0]), m = Integer.parseInt(numStr[1]);
        
        String[] str = new String[n];
        for(int i = 0; i < n; i++) {
            str[i] = bf.readLine();
        }

        String[] test = new String[m];
        for(int i = 0; i < m; i++) {
            test[i] = bf.readLine();
        }

        Arrays.sort(str);
        int include = 0;
        for(int i = 0; i < m; i++) {
            include += testStr(str, test[i], 0, n - 1);
        }
        bw.write(String.valueOf(include));
        bw.close();
    }

    public static int testStr(String[] str, String word, int start, int end) {
        int mid = (start + end) / 2;
        
        if(start > end) {
            return 0;
        }
        if(str[mid].equals(word)) {
            return 1;
        } else if(word.compareTo(str[mid]) < 0) {
            return testStr(str, word, start, mid - 1);
        } else{
            return testStr(str, word, mid + 1, end);
        }
    }
}
