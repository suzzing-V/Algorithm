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
        
        int[] stair = new int[n];
        for(int i = 0; i < n; i++) {
            stair[i] = Integer.parseInt(bf.readLine());
        }

        int[] startFirst = new int[n];
        int[] startSecond = new int[n];
        int[] count = new int[n];
        if(n == 1) {
            bw.write(Integer.toString(stair[0]));
        } else if (n == 2) {
            bw.write(Integer.toString(stair[0] + stair[1]));
        } else {
            startFirst[0] = stair[0]; startFirst[1] = stair[0] + stair[1];
            startSecond[1] = stair[1]; startSecond[2] = stair[1] + stair[2];
        
            count[0] = 1; count[1] = 2;
            for(int i = 2; i < n; i++) {
                if(count[i - 1] == 2) {
                    startFirst[i] = startFirst[i - 2] + stair[i];
                    count[i] = 1;
                } else {
                    if(startFirst[i - 2] >= startFirst[i - 1]) {
                        startFirst[i] = startFirst[i - 2] + stair[i];
                        count[i] = 1;
                    } else {
                        startFirst[i] = startFirst[i - 1] + stair[i];
                        count[i] = 2;
                    }   
                }
            }

            count[1] = 1; count[2] = 2;
            for(int i = 3; i < n; i++) {
                if(count[i - 1] == 2) {
                    startSecond[i] = startSecond[i - 2] + stair[i];
                    count[i] = 1;
                } else {
                    if(startSecond[i - 2] >= startSecond[i - 1]) {
                        startSecond[i] = startSecond[i - 2] + stair[i];
                        count[i] = 1;
                    } else {
                        startSecond[i] = startSecond[i - 1] + stair[i];
                        count[i] = 2;
                    }   
                }
            }
            bw.write(Integer.toString(Math.max(startFirst[n - 1], startSecond[n - 2])));
    }
    bw.close();
}
}
