import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            String input = bf.readLine();
            LinkedList<Character> secret = new LinkedList<>();
            ListIterator<Character> iter = secret.listIterator();

            for(int j = 0; j < input.length(); j++) {
                char key = input.charAt(j);
                if(key == '<') {
                    if(!iter.hasPrevious()) continue;
                    iter.previous();
                } else if(key == '>') {
                    if(!iter.hasNext()) continue;
                    iter.next();
                } else if(key == '-') {
                    if(!iter.hasPrevious()) continue;
                    iter.previous();
                    iter.remove();
                } else {
                    iter.add(key);
                }
            }
            Iterator<Character> it = secret.iterator();
            while(it.hasNext()) {
                bw.write(it.next());
            }
            bw.write("\n");
        }

        bw.close();
    }
}
