import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] arr = new int[m];
        
        DFS(arr, 0, 1, n, bw);
        bw.close();
    }

    public static void DFS(int[] arr, int i, int v, int n, BufferedWriter bw) throws IOException {
        if(i == arr.length) {
            for(int j = 0; j < arr.length; j++) {
                bw.write(Integer.toString(arr[j]) + " ");
            }
            bw.write("\n");
            return;
        }
        
        for(int j = v; j <= n; j++) {
            arr[i] = j;
            DFS(arr, i + 1, j, n, bw);
        }
    }
}
