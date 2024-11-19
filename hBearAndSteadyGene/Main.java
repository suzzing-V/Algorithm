import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    static int[] now = new int[4];
    static int[] over = new int[4];

    /*
     * Complete the 'steadyGene' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING gene as parameter.
     */

    public static int steadyGene(String gene) {
        // Write your code here
        int left = 0;
        int right = 0;
        int target = gene.length() / 4;

        for(int i = 0; i < gene.length(); i++) {
            char c = gene.charAt(i);
            plusGene(c, over);
        }
        // for(int i = 0; i < 4; i ++) {
        //     System.out.print(over[i] + " ");
        // }
        // System.out.println();
        int count = 0;
        for(int i = 0; i < 4; i ++) {
            if(over[i] <= target) {
                over[i] = 0;
                count ++;
            } else {
                over[i] -= target;
            }
            // System.out.print(over[i] + " ");
        }
        if(count == 4) {
            return 0;
        }
        // System.out.println();

        plusGene(gene.charAt(0), now);
        int min = 500000;
        while(left <= right) {
            boolean isInclude = checkGene(over, now);
            // System.out.println(gene.substring(left, right + 1));
            //     for(int i = 0; i < 4; i ++) {
            //     System.out.print(now[i] + " ");
            // }
            // System.out.println();
            if(!isInclude && right == gene.length() - 1) {
                break;
            }
            if(isInclude) {
                System.out.println(left + " " + right);
                     for(int i = 0; i < 4; i ++) {
                     System.out.print(over[i] + " ");
                 }
                 System.out.println();
                min = Math.min(min, right - left + 1);
                minusGene(gene.charAt(left), now);
                left ++;
            } else {
                right ++;
                plusGene(gene.charAt(right), now);
            }
        }

        // System.out.println(min);
        return min;
    }

    public static void plusGene(char c, int[] arr) {
        if(c == 'C') arr[0]++;
        if(c == 'G') arr[1]++;
        if(c == 'A') arr[2]++;
        if(c == 'T') arr[3]++;
    }

    public static void minusGene(char c, int[] arr) {
        if(c == 'C') arr[0]--;
        if(c == 'G') arr[1]--;
        if(c == 'A') arr[2]--;
        if(c == 'T') arr[3]--;
    }

    public static boolean checkGene(int[] over, int[] now) {
        for(int i = 0; i < 4; i++) {
            if(over[i] != 0) {
                if(over[i] > now[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String gene = bufferedReader.readLine();

        int result = Result.steadyGene(gene);

        System.out.println(result);
    }
}
