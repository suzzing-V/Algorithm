import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        bw.write(checkPelindrom(word));
        bw.close();
    }

    public static String checkPelindrom(String word) {
        int l = word.length();
        for(int i = 0; i < l / 2; i++) {
            if(word.charAt(i) != word.charAt(l - i - 1)) return "0";
        }
        return "1";
    }
}