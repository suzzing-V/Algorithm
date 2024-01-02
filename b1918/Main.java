import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Stack<Character> calculation = new Stack<>();
        String expression = bf.readLine();
        int len = expression.length();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; i++) {
            char now = expression.charAt(i);
            
            if(now >= 'A' && now <= 'Z') {
                sb.append(now);
            } else {
                if(now == '+' || now == '-') {
                    while(!calculation.isEmpty() && calculation.peek() != '(') 
                        sb.append(calculation.pop());
                    calculation.add(now);
                } else if(now == '*' || now == '/') {
                    while(!calculation.isEmpty() && (calculation.peek() == '*' || calculation.peek() == '/'))
                        sb.append(calculation.pop());
                    calculation.add(now);
                } else if(now == '(') {
                    calculation.add(now);
                } else if(now == ')') {
                    while(calculation.peek() != '(') {
                        sb.append(calculation.pop());
                    }
                    calculation.pop();
                }
            }
        }

        while(!calculation.isEmpty()) sb.append(calculation.pop());
        
        bw.write(sb.toString());
        bw.close();
    }
}