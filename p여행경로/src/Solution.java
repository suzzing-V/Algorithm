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
        String[] answer = listToArray(bfs(tickets));
        return answer;
    }
    
    public List<Ticket> bfs(String[][] tickets) {
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].equals(o2[0])) return o1[1].compareTo(o2[1]);
                return o1[0].compareTo(o2[0]);
            }
        });
        
        int n = tickets.length;
        boolean[] visit = new boolean[n];
        Queue<Ticket> queue = new LinkedList<>();
        List<Ticket> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(tickets[i][0].equals("ICN")) {
                queue.add(new Ticket(tickets[i][0], tickets[i][1]));
                visit[i] = true;
                break;
            }
        }
        
        while(!queue.isEmpty()) {
            Ticket tmp = queue.poll();
            //System.out.println("tmp: " + tmp.depart + " " + tmp.arrive);
            if(list.size() == n - 1) {
                list.add(new Ticket(tmp.depart, tmp.arrive));
                break;
            }
            for(int i = 0; i < n; i++) {
                if(visit[i]) continue;
                if(tickets[i][0].equals(tmp.arrive)) {
                    list.add(new Ticket(tmp.depart, tmp.arrive));
                    queue.add(new Ticket(tickets[i][0], tickets[i][1]));
                    visit[i] = true;
                    break;
                }
            }
        }
        
        return list;
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