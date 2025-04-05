import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Main {

    private static int n;
    private static String pattern;
    private static String regex;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        pattern = bf.readLine();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < pattern.length()) {
            if(pattern.charAt(i) == '*') {
                sb.append("(\\S*)");
                while (pattern.charAt(i) == '*') {
                    i++;
                    if (i >= pattern.length())
                        break;
                }
            } else {
                if(i == pattern.length() - 1) {
                    sb.append("(");
                    sb.append(pattern.charAt(i ++));
                    sb.append("$)");
                } else if(i == 0) {
                    sb.append("(^");
                    sb.append(pattern.charAt(i ++));
                    sb.append(")");
                } else {
                    sb.append(pattern.charAt(i ++));
                }
            }
        }
        regex = sb.toString();

        Matcher mt = null;
        for(int j = 0; j < n; j++) {
            String name = bf.readLine();
            mt = Pattern.compile(regex).matcher(name);
            if(mt.find()) {
                bw.write("DA\n");
            } else {
                bw.write("NE\n");
            }
        }

        bw.close();
    }
}
