import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int count = 0;
        int triple = 666;

        while(true) {
            String tripleStr = Integer.toString(triple);

            for(int i = 0; i < tripleStr.length() - 2; i++) {
                if(tripleStr.charAt(i) == '6' && tripleStr.charAt(i + 1) == '6' 
                    && tripleStr.charAt(i + 2) == '6') {
                        count++;
                        break;
                }
            }
            
            if(count == n) {
                bw.write(tripleStr);
                break;
            }
            triple++;
        }
        bw.close();
    }
}
