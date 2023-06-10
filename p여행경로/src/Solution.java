import java.util.*;

class Solution {
    public class Ticket {
        String depart;
        String arrive;
        
        public Ticket(String depart, String arrive) {
            this.depart = depart;
            this.arrive = arrive;
        }
    }
    
    public String[] solution(String[][] tickets) {
        boolean[] visit = new boolean[tickets.length];
        List<Ticket> list = new ArrayList<>();
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].equals(o2[0])) return o1[1].compareTo(o2[1]);
                return o1[0].compareTo(o2[0]);
            }
        });
        
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                visit[i] = true;
                list.add(new Ticket(tickets[i][0], tickets[i][1]));
                if(dfs(tickets, visit, list, tickets[i][1])) break;
                visit[i] = false;
                list.remove(list.size() - 1);
            }
        }
        
        String[] answer = listToArray(list);
        return answer;
    }
    
    public boolean dfs(String[][] tickets, boolean[] visit, List<Ticket> list, String arrive) {
        if(list.size() == tickets.length) return true;
        
        for(int i = 0; i < tickets.length; i++) {
            if(!visit[i] && tickets[i][0].equals(arrive)) {
                visit[i] = true;
                list.add(new Ticket(tickets[i][0], tickets[i][1]));
                if(dfs(tickets, visit, list, tickets[i][1])) return true;
                visit[i] = false;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
    
    public String[] listToArray(List<Ticket> list) {
        String[] result = new String[list.size() + 1];
        result[0] = list.get(0).depart;
        result[1] = list.get(0).arrive;
        for(int i = 2; i <= list.size(); i++) {
            //System.out.println(list.get(i - 1).arrive);
            result[i] = list.get(i - 1).arrive;
        }
        return result;
    }
}