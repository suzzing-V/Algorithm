import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    static Map<Long, Integer> idx = new HashMap<>();
    static long[] arr;

    /*
     * Complete the 'minimumLoss' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY price as parameter.
     */

    public static int minimumLoss(List<Long> price) {
        arr = new long[price.size()];
        // Write your code here
        for(int i = 0; i < price.size(); i ++) {
            long value = price.get(i);
            System.out.println(value);
            idx.put(value, i);
            arr[i] = value;
        }

        Arrays.sort(arr);
        long min = Integer.MAX_VALUE;
        for(int i = 0; i < price.size() - 1; i++) {
            long big = arr[i + 1];
            long small = arr[i];
            if(idx.get(big) < idx.get(small) && big - small < min) {
                min =big - small;
            }
        }

        return (int)min;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] priceTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> price = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long priceItem = Long.parseLong(priceTemp[i]);
            price.add(priceItem);
        }

        int result = Result.minimumLoss(price);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
