import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        List<Integer> seqA = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            seqA.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        List<Integer> seqB = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            seqB.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> result = new ArrayList<>();
        while(!seqA.isEmpty() && !seqB.isEmpty()) {
            int maxA = Collections.max(seqA);
            int maxB = Collections.max(seqB);
            if (maxA > maxB) {
                seqA.remove((Integer) maxA);
            } else if (maxA < maxB) {
                seqB.remove((Integer) maxB);
            } else {
                result.add(maxA);
                while(seqA.indexOf(maxA) != 0) {
                    seqA.remove(0);
                }
                seqA.remove(0);
                while(seqB.indexOf(maxB) != 0) {
                    seqB.remove(0);
                }
                seqB.remove(0);
            }
        }

        System.out.println(result.size());
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
