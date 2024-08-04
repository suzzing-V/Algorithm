import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        while(!(line = bf.readLine()).equals("0")) {
            if(isPellindrom(line)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    public static boolean isPellindrom(String line) {
        int left = 0;
        int right = line.length() - 1;

        while(left < right) {
            if(line.charAt(left) != line.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
