import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[][] toyBoard = new String[15][15];

        int i = 0;
        while(true) {
            String line = bf.readLine();
            if(line == null) break;
            String[] split = line.split("");

            for(int j = 0; j < split.length; j++) {
                toyBoard[i][j] = split[j];
            }
            i++;
        }

        printColumnRead(toyBoard, bw);
        bw.close();
    }

    public static void printColumnRead(String[][] toyBoard, BufferedWriter bw) throws IOException {
        for(int j = 0; j < toyBoard[0].length; j++) {
            for(int i = 0; i < toyBoard.length; i++) {
                if(toyBoard[i][j] != null) bw.write(toyBoard[i][j]);
            }
        }
    }
}