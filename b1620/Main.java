package b1620;
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> pokemon = new HashMap<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] num = bf.readLine().split(" ");
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);

        for(int i = 1; i <= n; i++) {
            pokemon.put(i, bf.readLine());
        }

        for(int i = 0; i < m; i++) {
            String quiz = bf.readLine();
            if(isNum(quiz)) {
                bw.write(pokemon.get(Integer.parseInt(quiz)));
            } else {
                bw.write(String.valueOf(getKey(pokemon, quiz)));
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

    public static <K, V> K getKey(HashMap<K, V> map, String value) {
        for(K key : map.keySet()) {
            if(map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
