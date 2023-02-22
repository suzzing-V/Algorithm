import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] ab = bf.readLine().split(" ");
        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1]);

        String[] arrA = bf.readLine().split(" ");
        String[] arrB = bf.readLine().split(" ");

        int count = 0;
        for(String num : arrA) {
            if(BSearch(arrB, num, 0, b - 1)) {
                count++;
            }
        }

        bw.write(String.valueOf((a + b) - (count * 2)));
        bw.close();
    }

    public static boolean BSearch(String[] arrB, String num, int start, int end) {
        int mid = (start + end) / 2;
        if(start > end) {
            return false;
        }

        if(num.equals(arrB[mid])) {
            return true;
        } else if(num.compareTo(arrB[mid]) < 0) {
            return BSearch(arrB, num, start, mid - 1);
        } else {
            return BSearch(arrB, num, mid + 1, end);
        }
    }
}
