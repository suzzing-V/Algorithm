import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int t;
    static int[][] room;
    static int[][] change;
    static int upAcX = -1;
    static int upAcY = -1;
    static int downAcX = -1;
    static int downAcY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        room = new int[r][c];
        change = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (upAcX == -1) {
                        upAcX = i;
                        upAcY = j;
                    } else {
                        downAcX = i;
                        downAcY = j;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            change[upAcX][upAcY] = -1;
            change[downAcX][downAcY] = -1;
            spreadDust();
            clearUpAir();
            clearDownAir();
            room = change;
            change = new int[r][c];
        }

        bw.write(String.valueOf(restDust()));
        bw.close();
    }

    public static void spreadDust() {
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(room[i][j] != -1 && room[i][j] != 0) {
                    int spread = room[i][j] / 5;
                    int count = 0;
                    if(i - 1 >= 0 && room[i - 1][j] != -1) {
                        change[i - 1][j] += spread;
                        count++;
                    }
                    if(i + 1 < r && room[i + 1][j] != -1) {
                        change[i + 1][j] += spread;
                        count++;
                    }
                    if(j - 1 >= 0 && room[i][j - 1] != -1) {
                        change[i][j - 1] += spread;
                        count++;
                    }
                    if(j + 1 < c && room[i][j + 1] != -1) {
                        change[i][j + 1] += spread;
                        count++;
                    }
                    change[i][j] += room[i][j] - count * spread;
                }
            }
        }
    }

    public static void clearUpAir() {
        int save = 0;
        int originalX = upAcX;
        int originalY = upAcY;
        upAcY ++;
        while(upAcY < c) {
                int tmp = change[upAcX][upAcY];
                change[upAcX][upAcY] = save;
                save = tmp;
            upAcY ++;
        }

        upAcY --;
        upAcX --;
        while(upAcX >= 0) {
                int tmp = change[upAcX][upAcY];
                change[upAcX][upAcY] = save;
                save = tmp;
            upAcX --;
        }

        upAcX ++;
        upAcY --;
        while(upAcY >= 0) {
                int tmp = change[upAcX][upAcY];
                change[upAcX][upAcY] = save;
                save = tmp;
            upAcY --;
        }

        upAcY ++;
        upAcX ++;
        while(upAcX < originalX) {
            int tmp = change[upAcX][upAcY];
            change[upAcX][upAcY] = save;
            save = tmp;
            upAcX ++;
        }

        while(upAcY < originalY) {
            int tmp = change[upAcX][upAcY];
            change[upAcX][upAcY] = save;
            save = tmp;
            upAcY ++;
        }
    }

    public static void clearDownAir() {
        int save = 0;
        int originalX = downAcX;
        int originalY = downAcY;

        downAcY ++;
        while(downAcY < c) {
            if(change[downAcX][downAcY] != -1) {
                int tmp = change[downAcX][downAcY];
                change[downAcX][downAcY] = save;
                save = tmp;
            }
            downAcY ++;
        }

        downAcY --;
        downAcX ++;
        while(downAcX < r) {
            int tmp = change[downAcX][downAcY];
            change[downAcX][downAcY] = save;
            save = tmp;
            downAcX ++;
        }

        downAcX --;
        downAcY --;
        while(downAcY >= 0) {
            int tmp = change[downAcX][downAcY];
            change[downAcX][downAcY] = save;
            save = tmp;
            downAcY --;
        }

        downAcY ++;
        downAcX --;
        while(downAcX > originalX) {
            int tmp = change[downAcX][downAcY];
            change[downAcX][downAcY] = save;
            save = tmp;
            downAcX --;
        }

        while(downAcY < originalY) {
            int tmp = change[downAcX][downAcY];
            change[downAcX][downAcY] = save;
            save = tmp;
            downAcY ++;
        }
    }

    public static int restDust() {
        int result = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(room[i][j] != -1) {
                    result += room[i][j];
                }
            }
        }
        return result;
    }
}
