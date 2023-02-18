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
        HashMap<Integer, String> pokemonInt = new HashMap<>(n);
        HashMap<String, Integer> pokemonStr = new HashMap<>(m);

        for(int i = 1; i <= n; i++) {
            String value = bf.readLine();
            pokemonInt.put(i, value);
            pokemonStr.put(value, i);
        }

        for(int i = 0; i < m; i++) {
            String quiz = bf.readLine();
            if(isNum(quiz)) {
                bw.write(pokemonInt.get(Integer.parseInt(quiz)));
            } else {
                bw.write(String.valueOf(pokemonStr.get(quiz)));
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
