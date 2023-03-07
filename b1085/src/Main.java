import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] xywhStr = new String[4];
        xywhStr = bf.readLine().split(" ");
        int[] xywh = new int[4];
        for(int i = 0; i < 4; i++) {
            xywh[i] = Integer.parseInt(xywhStr[i]);
        }
        
        int[] diff = new int[4];
        diff[0] = xywh[2] - xywh[0];
        diff[1] = xywh[3] - xywh[1];
        diff[2] = xywh[0];
        diff[3] = xywh[1];

        Arrays.sort(diff);
        bw.write(Integer.toString(diff[0]));
        bw.close();
    }
}
