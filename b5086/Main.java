import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        int num1;
        int num2;

        while(!(line = bf.readLine()).equals("0 0")) {
            String[] split = line.split(" ");
            num1 = Integer.parseInt(split[0]);
            num2 = Integer.parseInt(split[1]);

            printRelationship(num1, num2);
        }
    }

    public static void printRelationship(int num1, int num2) {
        if(num1 % num2 == 0) {
            System.out.println("multiple");
        } else if(num2 % num1 == 0) {
            System.out.println("factor");
        } else {
            System.out.println("neither");
        }
    }
}
