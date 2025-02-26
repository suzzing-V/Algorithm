import java.io.*;
import java.util.*;

public class Main {

    static Map<String, String> parents = new HashMap<>();
    static Map<String, Integer> networks = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            parents = new HashMap<>();
            networks = new HashMap<>();
            int r = Integer.parseInt(bf.readLine());
            for(int j = 0; j < r; j++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                if(parents.get(f1) == null) {
                    parents.put(f1, f1);
                    networks.put(f1, 1);
                }
                if(parents.get(f2) == null) {
                    parents.put(f2, f2);
                    networks.put(f2, 1);
                }

                String p1 = find(f1);
                String p2 = find(f2);
                String newP = p1;

                if(!p1.equals(p2)) {
                    union(f1, f2);
                    newP = find(f1);
                    int sum = networks.get(p1) + networks.get(p2);
                    networks.put(newP, sum);
                }
                bw.write(networks.get(parents.get(newP)) + "\n");
            }
        }

        bw.close();
    }

    private static String find(String x) {
        if(parents.get(x).equals(x)) {
            return x;
        }
        parents.put(x, find(parents.get(x)));
        return parents.get(x);
    }

    private static void union(String a, String b) {
        a = find(a);
        b = find(b);

        if(a.compareTo(b) < 0) {
            parents.put(b, a);
        } else {
            parents.put(a, b);
        }
    }
}
