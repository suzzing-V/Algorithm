import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Map<Character, Integer> pos = new HashMap<>();
    static int[] alpha = new int[26];
    static PriorityQueue<Alpha> pq = new PriorityQueue<>();
    static String[] strs;

    static class Alpha implements Comparable<Alpha> {
        private char c;
        private int posSum;

        Alpha(char c, int posSum) {
            this.c = c;
            this.posSum = posSum;
        }

        @Override
        public int compareTo(Alpha o) {
            return o.posSum - this.posSum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        strs = new String[n + 1];
        for(int i = 1; i < n + 1; i++) {
            String word = bf.readLine();
            strs[i] = word;
            for(int j = 0; j < word.length(); j++) {
                int posSum = pos.getOrDefault(word.charAt(j), 0);
                pos.put(word.charAt(j), posSum + (int)Math.pow(10, word.length() - j - 1));
            }
        }

        for(int i = 'A'; i <= 'Z'; i++) {
            if(pos.containsKey((char)i)) {
                pq.add(new Alpha((char)i, pos.get((char)i)));
            }
        }

        int num = 9;
        while(!pq.isEmpty()) {
            Alpha a = pq.remove();
//            System.out.println(a.c);
            alpha[a.c - 'A'] = num --;
        }

        int result = 0;
        for(int i = 1; i <= n; i++) {
            String word = strs[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < word.length(); j++) {
                sb.append(alpha[word.charAt(j) - 'A']);
            }
            result += Integer.parseInt(sb.toString());
        }
        bw.write(String.valueOf(result));
        bw.close();
    }
}
