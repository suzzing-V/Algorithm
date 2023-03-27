import java.io.*;
import java.util.*;

public class Main {
    static int min = 38000;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[][] ability = new int[n][n];

        for(int i = 0; i < n; i++) {
            String[] line = new String[n];
            line = bf.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                ability[i][j] = Integer.parseInt(line[j]);
            }
        }

        int[] start = new int[n / 2];
        int[] link = new int[n / 2];
        boolean[] visit = new boolean[n];
        Arrays.fill(visit, false);
        DFS(start, link, visit, 0, 0, ability);
        bw.write(Integer.toString(min));
        bw.close();
    }

    public static void DFS(int[] start, int[] link, boolean[] visit, int s, int value, int[][] ability) {
        if(s == visit.length / 2) {
            fillLink(link, visit);
            int gap = getAbilityGap(start, link, ability);
            if(gap < min) {
                min = gap;
            }
            return;
        }

        for(int i = 0; i < visit.length; i++) {
            if(!visit[value]) {
                start[s] = value;
                visit[value] = true;
                DFS(start, link, visit, s + 1, i, ability);
                visit[value] = false;
            }
        }
    }

    public static void fillLink(int[] link, boolean[] visit) {
        int l = 0;

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) {
                link[l ++] = i;
            }
        }
        return;
    }

    public static int getAbilityGap(int[] start, int[] link, int[][] ability) {
        int startAb = 0;
        int linkAb = 0;

        for(int i = 0; i < start.length - 1; i++) {
            for(int j = i + 1; j < start.length; j++) {
                startAb += ability[start[i]][start[j]] + ability[start[j]][start[i]];
                linkAb += ability[link[i]][link[j]] + ability[link[j]][link[i]];
            }
        }

        return Math.abs(startAb - linkAb);
    }
}
