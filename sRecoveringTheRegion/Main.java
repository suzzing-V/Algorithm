import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] sudoku;
    static int[][] region;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        sudoku = new int[n][n];
        region = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int regionNum = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(region[i][j] == 0) {
                    // System.out.println(i + " " + j + "시작");
                    makeRegion(i, j, regionNum, new ArrayList<Integer>(), new ArrayList<Pos>());
                    regionNum ++;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                bw.write(String.valueOf(region[i][j] + " "));
            }
            bw.write("\n");
        }

        bw.close();
    }

    private static boolean makeRegion(int x, int y, int regionNum, ArrayList<Integer> nums, ArrayList<Pos> poses) {
        if(nums.size() == n) {
            writeRegion(regionNum, poses);
            return true;
        }
        if(x < 0 || x >= n || y < 0 || y >= n || region[x][y] != 0 || nums.contains(sudoku[x][y])) {
            return false;
        }

        region[x][y] = regionNum;
        nums.add(sudoku[x][y]);
        Pos pos = new Pos(x, y);
        poses.add(pos);
        for(int i = 0; i < 4; i++) {
            if(makeRegion(x + dir[i][0], y + dir[i][1], regionNum, nums, poses)) {
                return true;
            }
        }
        region[x][y] = 0;
        nums.remove((Integer)sudoku[x][y]);
        poses.remove(pos);
        return false;
    }

    private static void writeRegion(int regionNum, ArrayList<Pos> poses) {
        for(Pos pos : poses) {
            // System.out.println(pos.x + " "+ pos.y + " " + regionNum);
            region[pos.x][pos.y] = regionNum;
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
