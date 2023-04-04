import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N + 2];
        arr[1] = 0; arr[2] = 1;
        for(int i = 3; i <= N; i++) {
            fillArr(arr, i);
        }
        bw.write(Integer.toString(arr[N]));
        bw.close();
    }

    public static void fillArr(int[] arr, int i) {
        int three = 0; int two = 0;
        int minus = i - 1;
        if(i % 3 == 0) {
            three = i / 3;
        }
        if(i % 2 == 0) {
            two = i / 2;
        }
        if(three == 0 && two != 0) {
            arr[i] = Math.min(arr[two], arr[minus]) + 1;
        } else if ( three != 0 && two == 0) {
            arr[i] = Math.min(arr[three], arr[minus]) + 1;
        } else if (three != 0 && two != 0) {
            int min = Math.min(arr[three], arr[two]);
            min = Math.min(min, arr[minus]);
            arr[i] = min + 1;
        } else {
            arr[i] = arr[minus] + 1;
        }
    }
}