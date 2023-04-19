import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        long[] km = new long[n - 1];
        inputArr(km, bf, n);

        long[] city = new long[n - 1];
        inputArr(city, bf, n);

        long[] count = new long[n - 1];
        int minIndex = 0;
        count[0] = km[0];
        for(int i = 1; i < n - 1; i++) {
            if(city[minIndex] > city[i]) {
                minIndex = i;
            }
            for(int j = 0; j < km[i]; j++) {
                count[minIndex]++;
            }
        }
        
        long result = getExpense(count, city, n);
        
        bw.write(Long.toString(result));
        bw.close();
    }

    public static void inputArr(long[] arr, BufferedReader bf, int n) throws IOException {
        String[] line = new String[arr.length];
        line = bf.readLine().split(" ");
        for(int i = 0; i < n - 1; i++) {
            arr[i] = Long.parseLong(line[i]);
        }
    }

    public static long getExpense(long[] count, long[] city, int n) {
        long result = 0;
        for(int i = 0; i < n - 1; i++) {
            result += count[i] * city[i];
        }
        return result;
    }
}
