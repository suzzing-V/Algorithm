import java.util.StringTokenizer;

import java.io.*;

public class Main {
    static int max = -1;
    static int[][] paper;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        paper = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] tet1 = {{1, 1, 1, 1}};
        int[][] tet2 = { {1, 1}, {1, 1}};
        int[][] tet3 = { {1, 0}, {1, 0}, {1, 1}};
        int[][] tet4 = { {1, 0}, {1, 1}, {0, 1}};
        int[][] tet5 = { {1, 1, 1}, {0, 1, 0}};

        getMax(n, m, tet1);
        getMax(n, m, tet2);
        getMax(n, m, tet3);
        getMax(n, m, tet4);
        getMax(n, m, tet5);

        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void getMax(int n, int m, int[][] tet) {
        int[][] rotate = tet;
        for(int i = 0; i < 4; i++) {
            putTet(rotate);
            rotate = rotateTet(rotate);
        }

        int[][] reverse = reverseTet(rotate);
        for(int i = 0; i < 4; i++) {
            putTet(reverse);
            reverse = rotateTet(reverse);
        }
    }

    public static int[][] reverseTet(int[][] tet) {
        int[][] result = new int[tet.length][tet[0].length];
        
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = tet[i][result[0].length - 1 - j];
            }
        }

        return result;
    }

    public static int[][] rotateTet(int[][] tet) {
        int[][] result = new int[tet[0].length][tet.length];

        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = tet[result[0].length - 1 - j][i];
            }
        }
        return result;
    }

    public static void putTet(int[][] tet) {
        int tetN = tet.length;
        int tetM = tet[0].length;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            if(n - i >= tetN) {
                for(int j = 0; j < m; j++) {
                    if(m - j >= tetM) {
                        int tetX = 0;
                        for(int x = i; x < i + tetN; x++) {
                            int tetY = 0;
                            for(int y = j; y < j + tetM; y++) {
                                if(tet[tetX][tetY] == 1) sum += paper[x][y];
                                tetY++;
                            }
                            tetX++;
                        }
                        max = Math.max(max, sum);
                        sum = 0;
                    }
                }
            }
        }
    }
}
