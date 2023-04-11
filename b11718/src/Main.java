import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String str;
        for(int i = 0; i < 100; i++) {
            str = sc.nextLine();
            if(str.length() == 0)
                break;
            System.out.println(str);
        }
        sc.close();
    }
}
