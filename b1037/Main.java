import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static List<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n ;i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);
        System.out.println(arr.get(0) * arr.get(arr.size() - 1));
    }
}
