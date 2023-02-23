import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nums = bf.readLine().split(" ");
        
        int result = 0;
        for(String num : nums) {
            result += Integer.parseInt(num);
        }
        bw.write(String.valueOf(result));
        bw.close();
    }
}
