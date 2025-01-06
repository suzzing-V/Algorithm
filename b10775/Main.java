import java.io.*;

public class Main {
    static int G;
    static int P;
    static int[] airplanes;
    static int[] parents;
    static int max  = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(bf.readLine());
        P = Integer.parseInt(bf.readLine());
        parents = new int[G + 1];
        airplanes = new int[P];
        for(int i = 0; i < P; i ++) {
            airplanes[i] = Integer.parseInt(bf.readLine());
        }
        for(int i = 1; i <= G; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < P; i++) {
            int limit = airplanes[i];
            int parent = find(limit);
            if(parent == 0) {
                break;
            } else {
                union(parent, parent - 1);
                max ++;
            }
        }
        System.out.println(max);
    }

    private static int find(int x) {
        if(parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }
}
