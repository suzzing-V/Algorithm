import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Queue<String> queue = new LinkedList<>();
        String back = "";

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            if(command.equals("push")) {
                String add = st.nextToken();
                queue.add(add);
                back = add;
            } else if(command.equals("pop")) {
                if(queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.remove());
                }
            } else if(command.equals("size")) {
                System.out.println(queue.size());
            } else if(command.equals("empty")) {
                if(queue.isEmpty()) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            } else if(command.equals("front")) {
                if(queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.peek());
                }
            } else {
                if(queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(back);
                }
            }
        }
    }
}
