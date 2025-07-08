import java.io.*;
import java.util.*;

public class Main {
    static int[][] cake;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        cake = new int[h][w];
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < w; j++) {
                cake[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> divide = new ArrayList<>();
        int k = 1;
        double sqrt = Math.sqrt(n);
        while(k <= sqrt) {
            if(n % k == 0 && !(k > h || k > w || n / k > h || n / k > w)) {
                divide.add(k);
                divide.add(n/k);
            }
            k++;
        }

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                for(int a = 0; a < divide.size(); a++) {
                    int newH = divide.get(a);
                    int newW = n / newH;
                    if(i + newH - 1 >= h || j + newW - 1 >= w) {
                        continue;
                    }
                    int maxTmp = 0;
                    int minTmp = Integer.MAX_VALUE;
                    for(int x = 0; x < newH; x++) {
                        for(int y = 0; y < newW; y++) {
                            maxTmp = Math.max(cake[i + x][j + y], maxTmp);
                            minTmp = Math.min(cake[i + x][j + y], minTmp);
                        }
                    }
                    max = Math.max(maxTmp - minTmp, max);
                }
            }
        }

        System.out.println(max);
    }
}
