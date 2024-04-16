import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        nodes = new String[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String root = st.nextToken();
            int rootIdx = root.charAt(0) - 'A';
            nodes[rootIdx][0] = root;
            nodes[rootIdx][1] = st.nextToken();
            nodes[rootIdx][2] = st.nextToken();
        }

        String front = frontRotate("A");
        String mid = midRotate("A");
        String back = backRotate("A");

        bw.write(front + "\n");
        bw.write(mid + "\n");
        bw.write(back + "\n");
        bw.close();
    }

    public static String frontRotate(String root) {
        int rootIdx = root.charAt(0) - 'A';
        String left = nodes[rootIdx][1];
        String right = nodes[rootIdx][2];

        if(left.equals(".") && right.equals(".")) {
            return nodes[rootIdx][0];
        } else if(left.equals(".") && !right.equals(".")) {
            return root + frontRotate(nodes[rootIdx][2]);
        } else if(!left.equals(".") && right.equals(".")) {
            return root + frontRotate(nodes[rootIdx][1]);
        }
        return root + frontRotate(nodes[rootIdx][1]) + frontRotate(nodes[rootIdx][2]);
    }

    public static String midRotate(String root) {
        int rootIdx = root.charAt(0) - 'A';
        String left = nodes[rootIdx][1];
        String right = nodes[rootIdx][2];

        if(left.equals(".") && right.equals(".")) {
            return nodes[rootIdx][0];
        } else if(left.equals(".") && !right.equals(".")) {
            return root + midRotate(nodes[rootIdx][2]);
        } else if(!left.equals(".") && right.equals(".")) {
            return midRotate(nodes[rootIdx][1]) + root;
        }
        return midRotate(nodes[rootIdx][1]) + root + midRotate(nodes[rootIdx][2]);
    }

    public static String backRotate(String root) {
        int rootIdx = root.charAt(0) - 'A';
        String left = nodes[rootIdx][1];
        String right = nodes[rootIdx][2];

        if(left.equals(".") && right.equals(".")) {
            return nodes[rootIdx][0];
        } else if(left.equals(".") && !right.equals(".")) {
            return backRotate(nodes[rootIdx][2]) + root;
        } else if(!left.equals(".") && right.equals(".")) {
            return backRotate(nodes[rootIdx][1]) + root;
        }
        return backRotate(nodes[rootIdx][1]) + backRotate(nodes[rootIdx][2]) + root;
    }
}
