import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bf.readLine();
        String bomb = bf.readLine();
        int bombLen = bomb.length();
        int lastBomb = bomb.charAt(bombLen - 1);

        Stack<Character> stack = new Stack<>();
        Stack<Character> tmp = new Stack<>();
        
        for(int i = 0; i < str.length(); i++) {
            char strChar = str.charAt(i);
            stack.push(strChar);
            if(lastBomb == strChar && stack.size() >= bombLen) {
                for(int j = bombLen - 1; j >= 0; j--) {
                    char pop = stack.pop();
                    tmp.push(pop);
                    if(bomb.charAt(j) != pop) {
                        while(!tmp.isEmpty()) {
                            char tmpPop = tmp.pop();
                            stack.push(tmpPop);
                        }
                        break;
                    }
                }
                
                if(!tmp.isEmpty()) {
                    tmp.clear();
                }
            }
        }

        if(stack.isEmpty()) {
            bw.write("FRULA");
        } else {
            while(!stack.isEmpty()) {
                tmp.push(stack.pop());
            }
            while(!tmp.isEmpty()) {
                bw.write(tmp.pop());
            }
        }
        bw.close();
    }
}
