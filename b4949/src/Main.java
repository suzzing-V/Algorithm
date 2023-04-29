import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
              String[] line = new String[101];
    line = bf.readLine().split("");
    if(line[0].equals(".")) break;
            bw.write(isBalanced(line));
        }
       bw.close();
}
public static String isBalanced(String[] line) {
    Stack<String> str = new Stack<>();
    int sum = 0;
    for(int i = 0; i < line.length; i++) {
        if(line[i].equals("(") || line[i].equals("[")){
            str.push(line[i]);
            sum ++;
        } else if(line[i].equals(")")){
            if(str.size() == 0) return "no";
            else if((str.peek()).equals("(")) {
                sum--;
                str.pop();
            } else {
                 return "no";
            }
       } else if(line[i].equals("]")) {
             if(str.size() == 0) return "no";
            else if((str.peek()).equals("[")) {
                sum--;
                str.pop();
            } else {
                 return "no";
            }
       }
}

if(sum != 0) return "no";
return "yes";
}
                  }