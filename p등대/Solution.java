import java.util.*;

class Solution {

    private List<List<Integer>> conn = new ArrayList<>();
    private int result = 0;
    public int solution(int n, int[][] lighthouse) {
        for(int i = 0; i <= n; i++) {
            conn.add(new ArrayList<>());
        }

        for(int i = 0; i < lighthouse.length; i++) {
            int n1 = lighthouse[i][0];
            int n2 = lighthouse[i][1];
            conn.get(n1).add(n2);
            conn.get(n2).add(n1);
        }

        dfs(1, 0);
        return result;
    }

    private int dfs(int light, int parent) {
        if(conn.get(light).size() == 1 && light != 1) return 1; // 리프노드일 경우. 연결고리가 하나인 것들 중에 리프노드 아닌 경우는 시작노드일 경우. 이거 없으면 시작노드가 1이고 그 연결고리가 1일 경우 그냥 끝내버린다

        int on = 0;
        for(int i = 0; i < conn.get(light).size(); i++) {
            int son = conn.get(light).get(i);
            if(parent == son) continue;
            on += dfs(son, light);
        }

        if(on > 0) { // 하나라도 꺼져있으면 킨다.
            result ++;
            return 0;
        }
        return 1;
    }
}
