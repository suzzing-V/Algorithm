import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String sangStr = bf.readLine();
        String[] sang = sangStr.split(" ");
        int[] sangArr = new int[n];
        for(int i = 0; i < n; i++) {
            sangArr[i] = Integer.parseInt(sang[i]);
        }

        int m = Integer.parseInt(bf.readLine());
        String cardStr = bf.readLine();
        String[] card = cardStr.split(" ");
        int[] cardArr = new int[m];
        for(int i = 0; i < m; i++) {
            cardArr[i] = Integer.parseInt(card[i]);
        }
        Arrays.sort(sangArr);
        
        for(int c : cardArr) {
            if(binarySearch(c, sangArr, 0, n - 1)) {
                bw.write("1 ");
            } else {
                bw.write("0 ");
            }
        }
        bw.close();
    }

    public static boolean binarySearch(int c, int[] sangArr, int start, int end) {
        int mid = (start + end) / 2;
        boolean a;

        if(start > end) {
            return false;
        }
        if(c > sangArr[mid]) {
            a = binarySearch(c, sangArr, mid + 1, end);
        } else if(c == sangArr[mid]) {
            a = true;
        } else {
            a = binarySearch(c, sangArr, start, mid - 1);
        }
        return a;
    }
}
