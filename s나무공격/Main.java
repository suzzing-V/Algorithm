import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static int crime = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    crime ++;
                }
            }
        }

        st = new StringTokenizer(bf.readLine());
        deleteCrime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(bf.readLine());
        deleteCrime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(crime);
    }

    public static void deleteCrime(int start, int end) {
        for(int i = start - 1; i < end; i ++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) {
                    map[i][j] = 0;
                    crime --;
                    break;
                }
            }
        }
    }
}

