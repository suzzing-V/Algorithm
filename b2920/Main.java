import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String melody = bf.readLine();

        if(melody.equals("1 2 3 4 5 6 7 8")) {
            System.out.println("ascending");
        } else if(melody.equals("8 7 6 5 4 3 2 1")) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}
