import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] sdoku = new int[9][9];
        String[] line = new String[9];
        
        for(int i = 0; i < 9; i++) {
            line = bf.readLine().split(" ");
            for(int j = 0; j < 9; j++) {
                sdoku[i][j] = Integer.parseInt(line[j]);
            }
        }

        DFS(sdoku, 0, 0, bw);
        bw.close();
    }

    public static void DFS(int[][] sdoku, int i, int j, BufferedWriter bw) throws IOException {
        if(i == 8 && j == 9) {
            for(int a = 0; a < 9; a++) {
                for(int b = 0; b < 9; b++) {
                    bw.write(Integer.toString(sdoku[a][b]) + " ");
                }
                bw.write("\n");
            }
            return;
        }
        if(j >= 9) {
            i++; j++;
        }
        while(i < 9) {
            while(j < 9) {
                if(sdoku[i][j] == 0) {
                    for(int k = 1; k <= 9; k++) {
                        if(!isExist(sdoku, k, i, j)) {
                            sdoku[i][j] = k;
                            DFS(sdoku, i, j + 1, bw);
                            sdoku[i][j] = 0;
                        }
                    }
                    return;
                }
                j++;
            }
            j = 0;
            i++;
        }

        for(int a = 0; a < 9; a++) {
            for(int b = 0; b < 9; b++) {
                bw.write(Integer.toString(sdoku[a][b]) + " ");
            }
            bw.write("\n");
        }
        return;
    }

    public static boolean isExist(int[][] sdoku, int k, int i, int j) {
        if(isColumn(sdoku, k, j)) return true;
        if(isBox(sdoku, k, i, j)) return true;
        if(isRow(sdoku, k, i)) return true;
        return false;
    } 

    public static boolean isColumn(int[][] sdoku, int k, int j) {
        for(int a = 0; a < 9; a++) {
            if(sdoku[a][j] == k) return true;
        }
        return false;
    }

    public static boolean isRow(int[][] sdoku, int k, int i) {
        for(int a = 0; a < 9; a++) {
            if(sdoku[i][a] == k) return true;
        }
        return false;
    }

    public static boolean isBox(int[][] sdoku, int k, int i, int j) {
        if(i % 3 == 1) i -= 1;
        else if (i % 3 == 2) i -= 2;
        if(j % 3 == 1) j -= 1;
        else if(j % 3 == 2) j -= 2;

        for(int a = i; a < i + 3; a++) {
            for(int b = j; b < j + 3; b++) {
                if(sdoku[a][b] == k) return true;
            }
        }
        return false;
    }
}
