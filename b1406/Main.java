import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            left.add(str.charAt(i));
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
//            System.out.println("cursor: " + cursor);
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("P")) {
                left.add(st.nextToken().charAt(0));
            } else if(command.equals("L")) {
                if(!left.isEmpty()) right.add(left.pop());
            } else if(command.equals("D")) {
                if(!right.isEmpty()) left.add(right.pop());
            } else if(command.equals("B")) {
                if(!left.isEmpty()) left.pop();
            }
//            for (int j = 1; j < editer.size() - 1; j++) {
//                System.out.print(editer.get(j));
//            }
//            System.out.println();
        }

        while(!left.isEmpty()) {
            right.add(left.pop());
        }
        while(!right.isEmpty()) {
            bw.write(right.pop());
        }
        bw.close();
    }
}
