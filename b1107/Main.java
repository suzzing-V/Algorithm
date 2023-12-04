import java.io.*;
import java.util.*;

public class Main {
    static boolean[] broken;
    static long count;
    static String dest;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dest = bf.readLine();
        int breakNum = Integer.parseInt(bf.readLine());
        count = Math.abs(Integer.parseInt(dest) - 100);
        if(breakNum == 0) {
            bw.write(String.valueOf(Math.min(dest.length(), count)));
            bw.close();
            return;
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        broken = new boolean[10];
        for(int i = 0; i < breakNum; i++) {
            broken[Integer.parseInt(st.nextToken())] = true;
        }

        if(breakNum == 10) {
            bw.write(String.valueOf(count));
            bw.close();
            return;
        }

        dfs("", 0);
        
        bw.write(String.valueOf(count));
        bw.close();
    }

    public static void dfs(String made, int num) {
        if(num == 7) return;
        if(!made.equals("")) {
            count = Math.min(count, Math.abs(Integer.parseInt(made) - Integer.parseInt(dest)) + num);
        }
        for(int i = 0; i < 10; i++) {
            if(!broken[i]) {
                dfs(made + String.valueOf(i), num + 1);
            }
        }
    }
}