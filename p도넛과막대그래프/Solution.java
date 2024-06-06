import java.util.*;

class Solution {

    public static ArrayList<Line>[] in;
    public static ArrayList<Line>[] out;
    public static int maxNode = 0;
    public static int donutCount = 0;
    public static int stickCount = 0;

    public static class Line {
        int start;
        int end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        in = new ArrayList[edges.length + 2];
        out = new ArrayList[edges.length + 2];

        for(int i = 1; i < edges.length + 2; i++) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            maxNode = Math.max(Math.max(maxNode, start), end);
            in[end].add(new Line(start, end));
            out[start].add(new Line(start, end));
        }

        int newNode = 0;
        int graph8Count = 0;
        List<Integer> startNode = new ArrayList<>();
        boolean[] visited = new boolean[maxNode + 1];
        for(int i = 1; i <= maxNode; i++) {
            if(in[i].size() == 0 && out[i].size() >= 2) {
                newNode = i;
                for(Line line : out[i]) {
                    startNode.add(line.end);
                }
                visited[i] = true;
            } else if(out[i].size() == 2) {
                graph8Count ++;
                visited[i] = true;
                checkVisited(i, visited);
            }
        }

        for(int node : startNode) {
            if(visited[node]) {
                continue;
            }

            checkDonutOrStick(node);
        }

        answer[0] = newNode;
        answer[1] = donutCount;
        answer[2] = stickCount;
        answer[3] = graph8Count;
        return answer;
    }

    public static void checkDonutOrStick(int node) {
        boolean[] visited = new boolean[maxNode + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            int now = queue.remove();

            if(visited[now]) {
                donutCount++;
                return;
            }
            if(out[now].size() == 0) {
                stickCount++;
                return;
            }
            queue.add(out[now].get(0).end);
            visited[now] = true;
        }
    }

    public static void checkVisited(int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(out[node].get(0).end);
        queue.add(out[node].get(1).end);

        while(!queue.isEmpty()) {
            int now = queue.remove();

            if(!visited[now]) {
                visited[now] = true;
                queue.add(out[now].get(0).end);
            }
        }
    }
}