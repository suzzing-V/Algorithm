import java.util.*;
import java.io.*;

public class Main {

    private static int l;
    private static int c;
    private static String[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        alpha = new String[c];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < c; i++) {
            alpha[i] = st.nextToken();
        }

        Arrays.sort(alpha);
        dfs(0, new StringBuilder(), 0, 0);
    }

    private static void dfs(int idx, StringBuilder sb, int mo, int ja) {
        if(sb.toString().length() == l) {
            if(mo >= 1 && ja >= 2) {
                System.out.println(sb.toString());
            }
            return;
        }

        for(int i = idx; i < c; i++) {
            sb.append(alpha[i]);
            if(isMo(alpha[i])) dfs(i + 1, sb, mo + 1, ja);
            else dfs(i + 1, sb, mo, ja + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean isMo(String c) {
        if(c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u")) {
            return true;
        }

        return false;
    }
}
