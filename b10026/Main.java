import java.io.*;
import java.util.*;

public class Main {
    static String[][] pic;
    static boolean[][] visitedY;
    static boolean[][] visitedN;
    static int n;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        pic = new String[n][n];
        visitedY = new boolean[n][n];
        visitedN = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < n; j++) {
                pic[i][j] = split[j];
            }
        }

        int cntY = 0;
        int cntN = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visitedY[i][j]) {
                    dfsY(i, j);
                    cntY++;
                }
                if(!visitedN[i][j]) {
                    dfsN(i, j);
                    cntN ++;
                }
            }
        }

        System.out.println(cntN + " " + cntY);
    }

    static void dfsY(int i, int j) {
        if(visitedY[i][j]) {
            return;
        }

        String color = pic[i][j];
        visitedY[i][j] = true;
        for(int a = 0 ; a < 4; a++) {
            int nextX = i + dirX[a];
            int nextY = j + dirY[a];
            if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                continue;
            }
            if((color.equals("R") && pic[nextX][nextY].equals("B")) || (color.equals("G") && pic[nextX][nextY].equals("B"))
            || (color.equals("B") && (pic[nextX][nextY].equals("R") || pic[nextX][nextY].equals("G")))) {
                continue;
            }
            dfsY(nextX, nextY);
        }
    }

    static void dfsN(int i, int j) {
        if(visitedN[i][j]) {
            return;
        }

        String color = pic[i][j];
        visitedN[i][j] = true;
        for(int a = 0 ; a < 4; a++) {
            int nextX = i + dirX[a];
            int nextY = j + dirY[a];
            if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                continue;
            }
            if(color.equals(pic[nextX][nextY])) {
                dfsN(nextX, nextY);
            }
        }
    }
}
