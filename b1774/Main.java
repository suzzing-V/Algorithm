import java.util.*;
import java.io.*;

// 시간 복잡도: 1000 * 1000
public class Main {

    private static int n;
    private static int m;
    private static Pos[] nodes;
    private static List<Line> lines = new ArrayList<>();
    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new Pos[n + 1];
        roots = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            roots[i] = i;
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Pos(x, y);
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // 같은 집합에 속하게 하기
            union(n1, n2);
        }

        // 각 우주신 잇는 선분들 만들기
        for(int i = 1; i < n; i++) {
            for(int j = i + 1; j <= n; j ++) {
                double d = getDistance(nodes[i], nodes[j]);
                lines.add(new Line(i, j, d));
//                System.out.println(d);
            }
        }

        // 선분 길이 기준으로 오름차순 정렬
        Collections.sort(lines, (o1, o2) -> {return (int)(o1.d * 100 - o2.d * 100);});

        // 선분 길이 작은 것부터 탐색하면서 사이클 발생하면 사용 안하기
        double sum = 0;
        for(int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if(find(line.n1) == find(line.n2)) {
                continue;
            }

            sum += line.d;
            union(line.n1, line.n2);
        }

        System.out.println(String.format("%.2f", sum));
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) roots[b] = a;
        else roots[a] = b;
    }

    private static int find(int x) {
        if(roots[x] == x) return x;

        return roots[x] = find(roots[x]);
    }

    private static double getDistance(Pos p1, Pos p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Line{
        int n1;
        int n2;
        double d;

        Line(int n1, int n2, double d) {
            this.n1 = n1;
            this.n2 = n2;
            this.d = d;
        }
    }
}
