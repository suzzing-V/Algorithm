import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double result = 0;
        double total = 0;
        while(true) {
            String line = bf.readLine();
            if(line == null) break;
            String[] split = line.split(" ");
            double subjectGrade= 0;
            if(split[2].equals("P")) subjectGrade = 0.0;
            else subjectGrade= Double.parseDouble(split[1]);
            double grade = changeToDouble(split[2]);
            
            result += subjectGrade * grade;
            total += subjectGrade;
        }

        if(total == 0) bw.write("0.0");
        else {
            result /= total;
            bw.write(String.valueOf(result));
        }
        bw.close();
    }

    public static double changeToDouble(String str) {
        if(str.equals("A+")) return 4.5;
        else if(str.equals("A0")) return 4.0;
        else if(str.equals("B+")) return 3.5;
        else if(str.equals("B0")) return 3.0;
        else if(str.equals("C+")) return 2.5;
        else if(str.equals("C0")) return 2.0;
        else if(str.equals("D+")) return 1.5;
        else if(str.equals("D0")) return 1.0;
        else return 0.0;
    }
}