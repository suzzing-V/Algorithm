import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] arrStr = new String[n];
        arrStr = bf.readLine().split(" ");
        int[] arr = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(arrStr[i - 1]);
        }

        int[] ascend = new int[n + 1];
        int[] descend = new int[n + 1];
        ascend[1] = 1;
        descend[n] = 1;
        int max;
        for(int i = 2; i < n + 1; i++) {
            max = 0;
            for(int j = 1; j < i; j++) {
                if(arr[j] < arr[i] && max < ascend[j]) {
                    max = ascend[j];
                }
            }
            ascend[i] = max + 1;
        }

        max = 0;
        for(int i = n; i > 0; i--) {
            max = 0;
            for(int j = n; j > i; j--) {
                if(arr[j] < arr[i] && max < descend[j]) {
                    max = descend[j];
                }
            }
            descend[i] = max + 1;
        }

        max = 0;
        int sum;
        for(int i = 1; i < n + 1; i++) {
            sum = ascend[i] + descend[i];
            if(sum > max) { max = sum; }
        }

        bw.write(Integer.toString(max - 1));
        bw.close();
    }
}
