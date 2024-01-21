import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> rlr;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line;
        rlr = new ArrayList<>();
        while(true) {
           line = bf.readLine();
           if(line == null || line.equals("")) break;
           rlr.add(Integer.parseInt(line));
        }

        circuit(0, rlr.size() - 1);
    }

    public static void circuit(int start, int end) {
        if(start > end) {
            return;
        }
        int i = start + 1;
        while(i <= end && rlr.get(i) < rlr.get(start)) {
            i++;
        }
        circuit(start + 1, i - 1);
        circuit(i, end);
        System.out.println(rlr.get(start));
    }
}
