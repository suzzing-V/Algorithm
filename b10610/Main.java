import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String num = bf.readLine();
        Integer[] arr = new Integer[num.length()];
        boolean containZero = false;
        int sum = 0;
        for(int i = 0; i < arr.length; i ++) {
            arr[i] = num.charAt(i) - '0';
            if(arr[i] == 0) containZero = true;
            sum += arr[i];
        }

        if(sum % 3 != 0 || !containZero) {
            bw.write("-1");
        } else {
            Arrays.sort(arr, Collections.reverseOrder());
            for(int i = 0; i < arr.length; i++) {
                bw.write(String.valueOf(arr[i]));
            }
        }
        bw.close();
    }
}
