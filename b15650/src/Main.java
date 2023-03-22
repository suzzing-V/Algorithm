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

        DFS(arr, 0, 0, n, bw);
        bw.close();
    }

    public static void DFS(int[] arr, int i, int v, int n, BufferedWriter bw) throws IOException {
        if(i == arr.length) {
            for(int a = 0; a < arr.length; a++) {
                bw.write(Integer.toString(arr[a]) + " ");
            }
            bw.write("\n");
            return;
        }

        for(int a = v + 1; a <= n; a++) {
            arr[i] = a;
            DFS(arr, i + 1, a, n, bw);
        }
    }
}
