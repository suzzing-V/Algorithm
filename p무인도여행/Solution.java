import java.util.*;

class Solution {
    boolean[][] visited;
    String[][] maps2;
    int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int[] solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        maps2 = new String[maps.length][maps[0].length()];
        for(int i = 0; i < maps.length; i++) {
            String[] split = maps[i].split("");
            for(int j = 0; j < maps[0].length(); j++) {
                maps2[i][j] = split[j];
            }
        }

        List<Integer> list = new ArrayList<>();
        int idx = 0;
        for(int i = 0; i < maps2.length; i++) {
            for(int j = 0; j < maps2[0].length; j++) {
                if(visited[i][j] || maps2[i][j].equals("X")) {
                    continue;
                }
                int sum = dfs(i, j);
                if(sum != 0) {
                    list.add(sum);
                }
            }
        }
        if(list.size() == 0) {
            list.add(-1);
        }
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }

    private int dfs(int x, int y) {
        if(x < 0 || x >= maps2.length || y < 0 || y >= maps2[0].length
                || visited[x][y] || maps2[x][y].equals("X")) {
            return 0;
        }

        visited[x][y] = true;
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            sum += dfs(x + dir[i][0], y + dir[i][1]);
        }
        return sum + Integer.parseInt(maps2[x][y]);
    }
}
