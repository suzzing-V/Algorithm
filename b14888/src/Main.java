import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int max = -1000000001;
    static int min = 1000000001;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] arrStr = new String[2];
        arrStr = bf.readLine().split(" ");
        String[] operatorStr = new String[4];
        operatorStr = bf.readLine().split(" ");

        int[] arr = new int[n];
        int[] operator = new int[4];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }
        for(int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(operatorStr[i]);
        }

        int[] seq = new int[n - 1];
        DFS(arr, operator, seq, 0);
        bw.write(Integer.toString(max) + "\n");
        bw.write(Integer.toString(min) + "\n");
        bw.close();
    }

    public static void DFS(int[] arr, int[] operator, int[] seq, int s) {
        if(s == seq.length) {
            int result = arr[0];
            for(int i = 1; i < arr.length; i++) {
                if(seq[i - 1] == 0) result += arr[i];
                else if(seq[i - 1] == 1) result -= arr[i];
                else if(seq[i - 1] == 2) result *= arr[i];
                else result /= arr[i];
            }
            if(max <= result) max = result;
            if(min >= result) min = result;
            return;
        }

        int o = 0;
        while(o <= 3) {
            if(operator[o] != 0) {
                seq[s] = o;
                operator[o] --;
                DFS(arr, operator, seq, s + 1);
                operator[o] ++;
            }
            o++;
        }
    }
}
