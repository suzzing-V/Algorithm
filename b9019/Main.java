import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int t = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        
        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(bf.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            bfs(before, after, bw);
        }

        bw.close();
    }

    public static class Node {
        int num;
        String cal;

        Node(int num, String cal) {
            this.num = num;
            this.cal = cal;
        }
    }

    public static void bfs(int before, int after, BufferedWriter bw) throws IOException {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visit = new boolean[10000];

        queue.add(new Node(before, ""));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.num == after) {
                bw.write(now.cal + "\n");
                return;
            }

            int d = now.num * 2;
            if(!visit[d % 10000]) queue.add(new Node(d % 10000, now.cal + "D")); 

        int s = now.num - 1;
        if(s <= 0) {
            if(!visit[9999]) {
                queue.add(new Node(9999, now.cal + "S"));
                visit[9999] = true;
            }
        } else {
            if(!visit[s]) {
                queue.add(new Node(s, now.cal + "S"));
                visit[s] = true;
            }
        }

        int l = now.num % 1000 / 100 + now.num / 1000;
        if(!visit[l]) {
            queue.add(new Node(l, now.cal + "L"));
            visit[l] = true;
        }

        int r = now.num % 10 * 1000 + now.num / 10;
        if(!visit[r]) {
            queue.add(new Node(r, now.cal + "R"));
            visit[r] = true;
        }
        }
    }
}