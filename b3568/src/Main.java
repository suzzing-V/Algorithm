import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        int i = 0;
        while(line.charAt(i) != ' ') {
            i++;
        } //공백 전까지 기본 변수형
        String base = String.substring(line, 0, i);
        
        while(line.charAt(i) != ';') {
            i++;
            int tmp = i;
            while((line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') 
                || (line.charAt(i) >= 'a' && line.charAt(i) <= 'z')) {
                i++;
            } //알파벳 아닌 거 나올 때까지 변수이름
            String var = String.substring(line, tmp, i);

            tmp = i;
            while(line.charAt(i) != ',') {
                i++;
            } //, 전까지 추가 변수형
            String add = String.substring(line, tmp, i);

            StringBuilder sb = new StringBuilder();
            sb.append(base);
            for(int j = add.length() - 1; j >= 0; j--) {
                sb.append(add.charAt(j));
            }
            sb.append(" ");
            sb.append(var);
            sb.append(";");
            bw.write(sb.toString());
        }
    }
}
