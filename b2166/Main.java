import java.io.*;
import java.util.*;

public class Main {

    static List<Pos> poses = new ArrayList<>();
    static class Pos {
        long x;
        long y;

        Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            Pos pos = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            poses.add(pos);
        }

        long result = 0;
        for(int i = 0; i < n - 1; i++) {
            result += poses.get(i).x * poses.get(i + 1).y;
            result -= poses.get(i).y * poses.get(i + 1).x;
        }

        result += poses.get(n - 1).x * poses.get(0).y;
        result -= poses.get(n - 1).y * poses.get(0).x;
        result = Math.abs(result);
        double answer = Math.round(((double) result / 2) * 10.0) / 10.0;
        System.out.println(String.format("%.1f", answer));
    }
}
