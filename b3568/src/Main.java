import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        int i = 0;
        while(line.charAt(i) != ' ') {
            i++;
        } //���� ������ �⺻ ������
        String base = String.substring(line, 0, i);
        
        while(line.charAt(i) != ';') {
            i++;
            int tmp = i;
            while((line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') 
                || (line.charAt(i) >= 'a' && line.charAt(i) <= 'z')) {
                i++;
            } //���ĺ� �ƴ� �� ���� ������ �����̸�
            String var = String.substring(line, tmp, i);

            tmp = i;
            while(line.charAt(i) != ',') {
                i++;
            } //, ������ �߰� ������
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
