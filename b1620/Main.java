package b1620;
import java.io.*;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] num = bf.readLine().split(" ");
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);
        HashMap<String, String> pokemon = new HashMap<>(n);

        for(int i = 1; i <= n; i++) {
            pokemon.put(String.valueOf(i), bf.readLine());
        }
        Set<Entry<String, String>> entrySet = pokemon.entrySet();
        for(int i = 0; i < m; i++) {
            String quiz = bf.readLine();
            if(isNum(quiz)) {
                bw.write(pokemon.get(quiz));
            } else {
                for(Entry<String, String> entry : entrySet) {
                    if(entry.getValue().equals(quiz)) {
                        bw.write(entry.getKey());
                    }
                }
            }
            bw.write("\n");
        }
        
        bw.close();
    }

    public static boolean isNum(String quiz) {
        for(int i = 0; i < quiz.length(); i++) {
            if(!Character.isDigit(quiz.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
