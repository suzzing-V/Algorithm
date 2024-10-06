import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(bf.readLine());
            int[] select = new int[num + 1];
            visited = new boolean[select.length];
            result = num;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j < select.length; j++) {
                select[j] = Integer.parseInt(st.nextToken());
                if(j == select[j]) {
                    visited[j] = true;
                    result --;
                }
            }

            for(int j = 1; j < select.length; j++) {
                if(!visited[j]) {
                    List<Integer> team = new ArrayList<>();
                    checkTeam(select, j, team);
                }
            }
            System.out.println(result);
        }
    }

    public static void checkTeam(int[] select, int selector, List<Integer> team) {
        int selected = select[selector];
        if(visited[selector] && team.contains(selector)) {
            int idx = team.indexOf(selector);
            List<Integer> subList = team.subList(idx, team.size());
            result -= subList.size();
            return;
        }
        if(visited[selector]) {
            return;
        }



        visited[selector] = true;
        team.add(selector);
        checkTeam(select, selected, team);
    }
}
