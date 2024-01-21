import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nc = new int[2];
        String[] split = bf.readLine().split(" ");
        nc[0] = Integer.parseInt(split[0]);
        nc[1] = Integer.parseInt(split[1]);

        int[] houses = new int[nc[0]];
        for(int i = 0; i < nc[0]; i++) {
            houses[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(houses);

        int length = binarySearch(houses, 1, houses[houses.length - 1] - houses[0] + 1, nc[1]);
        bw.write(String.valueOf(length - 1));
        bw.close();
    }

    public static int binarySearch(int[] houses, int start, int end, int c) {
        if(start >= end) return start;
        
        int mid = (start + end) / 2;
        int numOfRouter = getNumOfRouter(houses, mid);

        if(numOfRouter < c) return binarySearch(houses, start, mid, c);
        else return binarySearch(houses, mid + 1, end, c);
    }

    public static int getNumOfRouter(int[] houses, int min) {
        int count = 1;
        int save = 0;
        for(int i = 1; i < houses.length; i++) {
            if(houses[i] - houses[save] >= min) {
                save = i;
                count++;
            }
        }
        return count;
    }
}
