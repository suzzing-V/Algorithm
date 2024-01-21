import java.io.*;

public class Main {
    static boolean[][] piece = { {false, false, true, false, false},
                                {false, true, false, true, false},
                                {true, true, true, true, true} };;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());

        while(piece.length < n) {
            boolean[][] tmp = new boolean[piece.length * 2][piece.length * 4 - 1];

            int firstX = 0;
            int firstY = piece.length;
            int secondX = piece.length;
            int secondY = 0;
            int thirdX = secondX;
            int thirdY = tmp.length;

            for(int i = 0; i < piece.length; i++) {
                firstY = piece.length;
                secondY = 0;
                thirdY = tmp.length;
                for(int j = 0; j < piece[0].length; j++) {
                    tmp[firstX][firstY] = tmp[secondX][secondY] = tmp[thirdX][thirdY] = piece[i][j];
                    firstY++;
                    secondY++;
                    thirdY++;
                }
                firstX++;
                secondX++;
                thirdX++;
            }

            piece = new boolean[tmp.length][tmp[0].length];
            piece = tmp;
        }

        for(int i = 0; i < piece.length; i++) {
            for(int j = 0; j < piece[0].length; j++) {
                if(piece[i][j]) bw.write("*");
                else bw.write(" ");
            }
            bw.write("\n");
        }

        bw.close();
    }
}