import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        while(!(line = bf.readLine()).equals("-1")) {
            isPerfect(Integer.parseInt(line));
        }
    }

    public static void isPerfect(int num) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i < num; i++) {
            if(num % i == 0) {
                sum += i;
                list.add(i);
            }
        }

        if(sum == num) {
            System.out.print(num + " = ");
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                if(i != list.size() - 1) System.out.print(" + ");
            }
            System.out.println();
        } else {
            System.out.println(String.valueOf(num) + " is NOT perfect.");
        }
    }
}
