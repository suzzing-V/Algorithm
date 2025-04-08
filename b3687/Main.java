import java.util.*;
import java.io.*;

public class Main {

    private static int t;
    private static int n;
    private static String[] max_dp;
    private static String[] min_dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(bf.readLine());
        max_dp= new String[101];
        max_dp[2] = "1";
        max_dp[3] = "7";

        for(int i = 4; i <= 100; i ++) {
            max_dp[i] = "0";
            for(int j = 2; j <= i / 2; j++) {
                String num1 = String.valueOf(max_dp[j]);
                String num2 = String.valueOf(max_dp[i - j]);
                PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {return o2.compareTo(o1);});

                for(int k = 0; k < num1.length(); k++) {
                    pq.add(String.valueOf(num1.charAt(k)));
                }
                for(int k = 0; k < num2.length(); k++) {
                    pq.add(String.valueOf(num2.charAt(k)));
                }

                StringBuilder sb = new StringBuilder();
                while(!pq.isEmpty()) {
                    sb.append(pq.remove());
                }
                if(max_dp[i].length() < sb.toString().length()) {
                    max_dp[i] = sb.toString();
                } else if(max_dp[i].length() == sb.toString().length()) {
                    if(max_dp[i].compareTo(sb.toString()) > 0) {
                        max_dp[i] = sb.toString();
                    }
                }
            }
        }

        min_dp = new String[101];
        min_dp[2] = "1";
        min_dp[3] = "7";
        min_dp[4] = "4";
        min_dp[5] = "2";
        min_dp[6] = "6";
        min_dp[7] = "8";

        for(int i = 8; i <= 100; i ++) {
            min_dp[i] = "11111111111111111111111111111111111111111111111111";
            for(int j = 2; j <= i / 2; j++) {
                String num1 = String.valueOf(min_dp[j]);
                String num2 = String.valueOf(min_dp[i - j]);
                PriorityQueue<String> pq = new PriorityQueue<>();

                for(int k = 0; k < num1.length(); k++) {
                    pq.add(String.valueOf(num1.charAt(k)));
                }
                for(int k = 0; k < num2.length(); k++) {
                    pq.add(String.valueOf(num2.charAt(k)));
                }

                StringBuilder sb = new StringBuilder();
                int zero = 0;
                while (pq.peek().equals("0")) {
                    zero ++;
                    pq.remove();
                }
                sb.append(pq.remove());
                while(zero != 0) {
                    sb.append("0");
                    zero --;
                }
                while(!pq.isEmpty()) {
                    sb.append(pq.remove());
                }
                if(min_dp[i].length() > sb.toString().length()) {
                    min_dp[i] = sb.toString();
                } else if(min_dp[i].length() == sb.toString().length()) {
                    if(min_dp[i].compareTo(sb.toString()) > 0) {
                        min_dp[i] = sb.toString();
                    }
                }
//                System.out.println(i + " " + min_dp[i]);
            }

            String num1 = "0";
            String num2 = String.valueOf(min_dp[i - 6]);
            PriorityQueue<String> pq = new PriorityQueue<>();

            for(int k = 0; k < num1.length(); k++) {
                pq.add(String.valueOf(num1.charAt(k)));
            }
            for(int k = 0; k < num2.length(); k++) {
                pq.add(String.valueOf(num2.charAt(k)));
            }

            StringBuilder sb = new StringBuilder();
            int zero = 0;
            while (pq.peek().equals("0")) {
                zero ++;
                pq.remove();
            }
            sb.append(pq.remove());
            while(zero != 0) {
                sb.append("0");
                zero --;
            }
            while(!pq.isEmpty()) {
                sb.append(pq.remove());
            }
            if(min_dp[i].length() > sb.toString().length()) {
                min_dp[i] = sb.toString();
            } else if(min_dp[i].length() == sb.toString().length()) {
                if(min_dp[i].compareTo(sb.toString()) > 0) {
                    min_dp[i] = sb.toString();
                }
            }
        }

        for(int i = 0; i < t; i++) {
            n = Integer.parseInt(bf.readLine());
            bw.write(min_dp[n] + " " + max_dp[n] + "\n");
        }

        bw.close();
    }
}
