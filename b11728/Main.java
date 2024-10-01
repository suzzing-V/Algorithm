import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        int[] b = new int[m];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n + m];
        int i = 0;
        int j = 0;
        int idx = 0;
        while(true) {
            if(i >= n) {
                for(int k = j; k < m; k++) {
                    result[idx++] = b[k];
                }
                break;
            }
            if(j >= m) {
                for(int k = i; k < n; k++) {
                    result[idx++] =a[k];
                }
                break;
            }

            if(a[i] < b[j]) {
                result[idx ++] = a[i ++];
            } else{
                result[idx ++] = b[j ++];
            }
        }

        StringBuilder sb = new StringBuilder(); //많은 거 출력할 떄 sb
        for(int num : result) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
}
