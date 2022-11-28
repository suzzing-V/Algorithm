package b2447;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(bf.readLine());
        int[][] map = new int[n][n];
    
        star_dot(map, 0, 0, n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    bw.write("*");
                } else {
                    bw.write(" ");
                }
            }
            bw.newLine();
        }
        bw.close();
    }

    public static void star_dot(int[][] map, int i, int j, int n) {
        if(n == 3) {
            for(int k = j; k < j + 3; k++) {
                map[i][k]++;
            }
            i++;
            map[i][j] = 1;
            map[i][j + 2] = 1;
            i++;
            for(int k = j; k < j + 3 ; k++) {
                map[i][k]++;
            }
            return; //3*3 패턴 만들기
        }

        int k = j;
        int m = i;
        for(int l = 0; l < 3; l++) { //첫번쨰 줄 만들기
            star_dot(map, m, k, n / 3); 
            k += n / 3;
        }

        k = j; m += n / 3; //다음 줄 맨앞으로 이동
        star_dot(map, m, k, n / 3);
        star_dot(map, m, k + 2 * n / 3, n / 3); //두번째 줄 만들기
        
        m += n / 3;
        for(int l = 0; l < 3; l++) { //세번째 줄 만들기
            star_dot(map, m, k, n / 3);
            k += n / 3;
        }
    }
}