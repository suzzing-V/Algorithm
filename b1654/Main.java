import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String[] line = new String[n];
        line = bf.readLine().split(" ");
        Integer[] height = new Integer[n];
        for(int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(height);

        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        int count1 = 0;
        int count2 = 0;
        for(int i = height.length - 1; i >= 0; i-= 2) {
            left.add(height[i]);
        }

        for(int i = height.length - 2; i >= 0; i-= 2) {
            right.add(height[i]);
        }
        count1 = left.size() + right.size();

        left = new HashSet<>();
        right = new HashSet<>();
        for(int i = height.length - 1; i >= 0; i-= 2) {
            right.add(height[i]);
        }

        for(int i = height.length - 2; i >= 0; i-= 2) {
            left.add(height[i]);
        }
        count2 = left.size() + right.size();

        bw.write(String.valueOf(Math.max(count1, count2)));
        bw.close();
    }
}
