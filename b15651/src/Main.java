import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] arr = new int[m];
        
        DFS(arr, 0, n, bw);
        bw.close();
    }

    public static void DFS(int[] arr, int i, int n, BufferedWriter bw) throws IOException {
        if(i == arr.length) {
            for(int j = 0; j < arr.length; j++) {
                bw.write(Integer.toString(arr[j]) + " ");
            }
            bw.write("\n");
            return;
        }
        for(int v = 1; v <= n; v++) {
            arr[i] = v;
            DFS(arr, i + 1, n, bw);
        }
    }
}
