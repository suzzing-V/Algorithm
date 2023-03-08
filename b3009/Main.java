import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("여기?");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] rectangle = new int[3][2];
        String[] line = new String[2];

        //System.out.println("여기?");
        for(int i = 0; i < 3; i++) {
            line = bf.readLine().split(" ");
            rectangle[i][0] = Integer.parseInt(line[0]);
            rectangle[i][1] = Integer.parseInt(line[1]);
        }

        int[] countX = new int[1001];
        int[] countY = new int[1001];
        int x = 0, y = 0;
        Arrays.fill(countX, 0);
        Arrays.fill(countY, 0);
        for(int i = 0; i < 3; i++) {
            countX[rectangle[i][0]] ++;
            countY[rectangle[i][1]] ++;
        }
        for(int i = 0; i < 1001; i++) {
            if(countX[i] == 1) {
                x = i;
                break;
            }
        }
        for(int i = 0; i < 1001; i++) {
            if(countY[i] == 1) {
                y = i;
                break;
            }
        }
        bw.write(String.valueOf(x) + " " + String.valueOf(y));   
        bw.close();
    }
}
