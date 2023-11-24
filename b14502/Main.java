import java.util.*;
import java.io.*;

public class Main {
    static int max = -1;
    static int n;
    static int m;
    static int[][] laboratory;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        laboratory = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                laboratory[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        buildTheWalls(0);
        bw.write(String.valueOf(max));
        bw.close();
    }

    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void buildTheWalls(int count) {
        if(count >= 3) {
            int[][] copyLab = new int[n][m];
            List<Pos> virus = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    copyLab[i][j] = laboratory[i][j];
                    if(copyLab[i][j] == 2) virus.add(new Pos(i, j));
                }
            }

            for(Pos pos : virus) {
                spreadVirus(copyLab, pos.x, pos.y);
            }
            max = Math.max(countSafeZone(copyLab), max);
            return;
        }
        for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
        if(laboratory[i][j] == 0) {
            laboratory[i][j] = 1;
            buildTheWalls(count + 1);
            laboratory[i][j] = 0;
        }
    }
    }
    }

    public static void spreadVirus(int[][] copyLab, int x, int y) {
        copyLab[x][y] = 2;
        if(x - 1 >= 0 && copyLab[x - 1][y] == 0) spreadVirus(copyLab, x - 1, y);
        if(x + 1 < n && copyLab[x + 1][y] == 0) spreadVirus(copyLab, x + 1, y);
        if(y - 1 >= 0 && copyLab[x][y - 1] == 0) spreadVirus(copyLab, x, y - 1);
        if(y + 1 < m && copyLab[x][y + 1] == 0) spreadVirus(copyLab, x, y + 1);
    }

    public static int countSafeZone(int[][] copyLab) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copyLab[i][j] == 0) count ++;
            }
        }
        return count;
    }
}
