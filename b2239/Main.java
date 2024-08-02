import java.io.*;
import java.util.*;

public class Main {

    static int[][] sdoku = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i ++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < 9; j ++) {
                sdoku[i][j] = Integer.parseInt(split[j]);
            }
        }

        dfs(0, 0);
    }

    public static boolean dfs(int x, int y) {
        if(y >= 9) {
            x += 1;
            y = 0;
        }
        if(x >= 9) {
            for(int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sdoku[i][j]);
                }
                System.out.println();
            }
            return true;
        }
        if(sdoku[x][y] != 0) {
            if(dfs(x, y + 1)) {
                return true;
            }
            return false;
        }

        for(int i = 1; i <= 9; i ++) {
            if(checkBox(x, y, i) && checkColumn(x, y, i) && checkRow(x, y, i)) {
                sdoku[x][y] = i;
                if(dfs(x, y + 1)) {
                    return true;
                }
                sdoku[x][y] = 0;
            }
        }
        return false;
    }

    public static boolean checkBox(int x, int y, int input) {
        int box = getBox(x, y);
        if(box == 1) {
            return checkMiniBox(0, 0, input);
        }
        if(box == 2) {
            return checkMiniBox(0, 3, input);
        }
        if(box == 3) {
            return checkMiniBox(0, 6, input);
        }
        if(box == 4) {
            return checkMiniBox(3, 0, input);
        }
        if(box == 5) {
            return checkMiniBox(3, 3, input);
        }
        if(box == 6) {
            return checkMiniBox(3, 6, input);
        }
        if(box == 7) {
            return checkMiniBox(6, 0, input);
        }
        if(box == 8) {
            return checkMiniBox(6, 3, input);
        }
        return checkMiniBox(6, 6, input);
    }

    public static boolean checkMiniBox(int x, int y, int input) {
        for(int i = x; i < x + 3; i ++) {
            for(int j = y; j < y + 3; j ++) {
                if(sdoku[i][j] == input) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkColumn(int x, int y, int input) {
        for(int i = x; i < 9; i++) {
            if(sdoku[i][y] == input) {
                return false;
            }
        }

        for(int i = x; i >= 0; i--) {
            if(sdoku[i][y] == input) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkRow(int x, int y, int input) {
        for(int i = y; i < 9; i++) {
            if(sdoku[x][i] == input) {
                return false;
            }
        }

        for(int i = y; i >= 0; i--) {
            if(sdoku[x][i] == input) {
                return false;
            }
        }

        return true;
    }

    public static int getBox(int x, int y) {
        if(x >= 0 && x <= 2 && y >= 0 && y <= 2) {
            return 1;
        }
        if(x >= 0 && x <= 2 && y >= 3 && y <= 5) {
            return 2;
        }if(x >= 0 && x <= 2 && y >= 6 && y <= 8) {
            return 3;
        }if(x >= 3 && x <= 5 && y >= 0 && y <= 2) {
            return 4;
        }if(x >= 3 && x <= 5 && y >= 3 && y <= 5) {
            return 5;
        }if(x >= 3 && x <= 5 && y >= 6 && y <= 8) {
            return 6;
        }if(x >= 6 && x <= 8 && y >= 0 && y <= 2) {
            return 7;
        }if(x >= 6 && x <= 8 && y >= 3 && y <= 5) {
            return 8;
        }
        return 9;
    }
}
