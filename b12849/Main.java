import java.io.*;
import java.util.*;

public class Main {
    static int d;
    static int DIVIDE = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        d = Integer.parseInt(bf.readLine());
        long[][] nums = new long[d + 1][8];
        nums[0][0] = 1;

        for(int t = 1; t <= d; t++) {
            nums[t][0] = nums[t - 1][1] % DIVIDE + nums[t - 1][2] % DIVIDE;
            nums[t][1] = nums[t - 1][0] % DIVIDE + nums[t - 1][2] % DIVIDE + nums[t - 1][3] % DIVIDE;
            nums[t][2] = nums[t - 1][0] % DIVIDE + nums[t - 1][1] % DIVIDE + nums[t - 1][3] % DIVIDE + nums[t - 1][4] % DIVIDE;
            nums[t][3] = nums[t - 1][1] % DIVIDE + nums[t - 1][2] % DIVIDE + nums[t - 1][4] % DIVIDE + nums[t - 1][5] % DIVIDE;
            nums[t][4] = nums[t - 1][2] % DIVIDE + nums[t - 1][3] % DIVIDE + nums[t - 1][5] % DIVIDE + nums[t - 1][6] % DIVIDE;
            nums[t][5] = nums[t - 1][3] % DIVIDE + nums[t - 1][4] % DIVIDE + nums[t - 1][7] % DIVIDE;
            nums[t][6] = nums[t - 1][4] % DIVIDE + nums[t - 1][7] % DIVIDE;
            nums[t][7] = nums[t - 1][5] % DIVIDE + nums[t - 1][6] % DIVIDE;
        }

        System.out.println(nums[d][0] % DIVIDE);
    }
}
