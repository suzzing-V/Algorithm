import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        int[][] move = new int[6][2];
        for(int i = 0; i < 6; i++) {
            String[] line;
            line = bf.readLine().split(" ");
            move[i][0] = Integer.parseInt(line[0]);
            move[i][1] = Integer.parseInt(line[1]);
        }

        int[] count = new int[5];
        int[] repeat = new int[2];
        int j = 0;

        for(int i = 0; i < 6; i++) {
            count[move[i][0]] ++;
            if(count[move[i][0]] == 2) {
                repeat[j] = move[i][0];
                j++;
            }
        }

        int b = 0;
        int i;
        int w, x, y, z;
        int a = 0;
        while(true) {
            i = b;
            if(move[i][0] == repeat[0] || move[i][0] == repeat[1]) {
                w = i;
                i++;
                if(i > 5) { i = 0; };
                if(move[i][0] == repeat[0] || move[i][0] == repeat[1]) {
                    x = i;
                    i++;
                    if(i > 5) { i = 0; };
                    if(move[i][0] == repeat[0] || move[i][0] == repeat[1]) {
                        y = i;
                        i++;
                        if(i > 5) { i = 0; };
                        if(move[i][0] == repeat[0] || move[i][0] == repeat[1]) {
                            z = i;
                            break;
                        }
                    }
                }
            }
            b++;
        }

        int area = move[w][1] * move[x][1] + move[y][1] * move[z][1] + move[w][1] * move[z][1];
        bw.write(Integer.toString(area * n));
        bw.close();
    }
}
