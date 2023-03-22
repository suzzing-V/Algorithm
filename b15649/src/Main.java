import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] arr = new int[m];
        boolean[] visit = new boolean[n + 1];
        Arrays.fill(visit, false);
        DFS(arr, visit, 0);
    }

    public static void DFS(int[] arr, boolean[] visit, int i) {
        if(arr.length == i) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
            return;
        }

        for(int j = 1; j <= visit.length - 1; j++) {
            if(!visit[j]) {
                arr[i] = j;
                visit[j] = true;
                DFS(arr, visit, i + 1);
                visit[j] = false;
            }
        } 
    }
}
