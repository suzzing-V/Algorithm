import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        List<Integer> tree = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            tree.add(Integer.parseInt(bf.readLine()));
        }
        
        int[] distance = new int[n - 1];
        for(int i = 0; i < n - 1; i++) {
            distance[i] = tree.get(i + 1) - tree.get(i);
        }
        Arrays.sort(distance);

        int i = 2;
        int gcd = 1;
        while(distance[0] >= i) {
            if(canDivide(distance, n, i)) {
                for(int j = 0; j < n - 1; j++) {
                    distance[j] /= i;
                }
                gcd *= i;
            } else i++;
        }
        int total = (tree.get(n - 1) - tree.get(0)) / gcd + 1;
        bw.write(Integer.toString(total - n));
        bw.close();
    }

    public static boolean canDivide(int[] distance, int n, int i) {
        for(int j = 0; j < n - 1; j++) {
            if(distance[j] % i != 0) return false;
        }
        return true;
    }
}
