import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int n;
    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(canAcrossRow(i)) {
//                System.out.println(i + " row");
                cnt ++;
            }
            if(canAcrossCol(i)) {
//                System.out.println(i + " col");
                cnt ++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.close();
    }

    private static boolean canAcrossRow(int x) {
        int cnt = 1;
        int num = map[x][0];
        for(int i = 1; i < n; i++) {
            int curr = map[x][i];
//            System.out.println("curr " + curr + " num " + num + " cnt" + cnt);
            if(curr == num) {
                cnt ++;
            } else if(Math.abs(curr - num) != 1) {
                return false;
            } else if(curr == num + 1) {
                if(cnt >= l) {
                    num = curr;
                    cnt = 1;
                } else {
                    return false;
                }
            } else if(curr == num - 1) {
                for(int j = 1; j < l; j++) {
                    if(i + j == n || map[x][i + j] != curr) return false;
                }
                num = curr;
                cnt = 0;
                i += l - 1;
            }
        }

        return true;
    }

    private static boolean canAcrossCol(int y) {
        int cnt = 1;
        int num = map[0][y];
        for(int i = 1; i < n; i++) {
            int curr = map[i][y];
//            System.out.println("curr " + curr + " num " + num + " cnt" + cnt);
            if(curr == num) {
                cnt ++;
            } else if(Math.abs(curr - num) != 1) {
                return false;
            } else if(curr == num + 1) {
                if(cnt >= l) {
                    num = curr;
                    cnt = 1;
                } else {
                    return false;
                }
            } else if(curr == num - 1) {
                for(int j = 1; j < l; j++) {
                    if(i + j == n || map[i + j][y] != curr) return false;
                }
                num = curr;
                cnt = 0;
                i += l - 1;
            }
        }

        return true;
    }
}
