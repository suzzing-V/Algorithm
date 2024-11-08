import java.io.*;
import java.util.*;

public class Main {
    static List<Line> lines = new ArrayList<>();
    static List<Line> lis = new ArrayList<>();
    static int[] index;

    static class Line implements Comparable<Line> {
        int a;
        int b;

        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            return this.a - o.a;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b));
        }
        Collections.sort(lines);
        index = new int[n];

        lis.add(lines.get(0));
        index[0] = 0;
        for(int i = 1; i < lines.size(); i ++) {
            Line line = lines.get(i);
            if(line.b > lis.get(lis.size() - 1).b) {
                lis.add(line);
                index[i] = lis.size() - 1;
            } else if(line.b < lis.get(lis.size() - 1).b){
                int idx = bs(0, lis.size() - 1, line.b);
                index[i] = idx;
                lis.set(idx, line);
            }
        }

        System.out.println(lines.size() - lis.size());
        int idx = lis.size() - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(index[i] == idx) {
                idx --;
                lines.remove(i);
            }
        }

        for(Line line : lines) {
            System.out.println(line.a);
        }
    }

    public static int bs(int start, int end, int target) {
        if(start >= end) {
            return end;
        }
        int mid = (start + end) / 2;
        int value = lis.get(mid).b;

        if(value < target) {
            return bs(mid + 1, end, target);
        } else {
            return bs(start, mid, target);
        }
    }
}
