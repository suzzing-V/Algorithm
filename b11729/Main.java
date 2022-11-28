package b11729;

import java.io.*;

public class Main {
    static int count = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(bf.readLine());
        int[][] move = new int[(int)Math.pow(2, n) - 1][2];
        int[] top = {n, 0, 0};
        
        hanoiTop(top, move, n, 0, 1, 2);

        bw.write(Integer.toString(count + 1));
        bw.newLine();
        
        for(int i = 0; i < count + 1; i++) {
            bw.write(Integer.toString(move[i][0]) + " " + Integer.toString(move[i][1]));
            bw.newLine();
        }
        bw.close();
    }

    public static void hanoiTop (int[] top, int[][] move, int n, int start, int help, int dest) {
        if(n == 1) {
            top[start]--;
            top[dest]++;
            count++;
            move[count][0] = start + 1;
            move[count][1] = dest + 1;
            return;
        }

        hanoiTop(top, move, n - 1, start, dest, help); //위에거 보조고리로 이동
        top[start]--;
        top[dest]++; //맨 아래판 목표고리로 이동
        count++;
        move[count][0] = start + 1;
        move[count][1] = dest + 1;
        hanoiTop(top, move, n - 1, help, start, dest); //위에거 목표고리로 이동
    }
}