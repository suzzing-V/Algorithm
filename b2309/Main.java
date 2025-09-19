import java.util.*;
import java.io.*;

public class Main {

    private static int[] heights = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i ++) {
            heights[i] = Integer.parseInt(bf.readLine());
        }
        make100(0, 0, new ArrayList<>());
    }

    private static boolean make100(int idx, int sum, List<Integer> selected) {

        if(sum == 100 && selected.size() == 7) {
            printResult(selected);
            return true;
        }
        if(idx >= 9) return false;

            sum += heights[idx];
            selected.add(heights[idx]);
            if(make100(idx + 1, sum, selected)) {
                return true;
            }
            sum -= heights[idx];
            selected.remove(selected.size() - 1);
            if(make100(idx + 1, sum, selected)) {
                return true;
            }
        return false;
    }

    private static void printResult(List<Integer> selected) {
        Collections.sort(selected);
        for(int h : selected) {
            System.out.println(h);
        }
    }
}
