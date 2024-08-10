import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while(!(input = bf.readLine()).equals("0 0 0")) {
            StringTokenizer st = new StringTokenizer(input);
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());

            int max = Math.max(Math.max(first, second), third);
            int result = 0;

            if(first != max) {
                result += first * first;
            }
            if(second != max) {
                result += second * second;
            }
            if(third != max) {
                result += third * third;
            }

            if(max * max == result) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}
