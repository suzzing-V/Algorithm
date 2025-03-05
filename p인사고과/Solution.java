import java.util.*;

class Solution {

    private Map<Integer, Integer> noIncen = new HashMap<>();
    private List<Member> members = new ArrayList<>();

    private class Member implements Comparable<Member> {
        private int num;
        private int s1;
        private int s2;

        private Member(int num, int s1, int s2) {
            this.num = num;
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public int compareTo(Member m) {
            if(this.s1 == m.s1)  {
                return this.s2 - m.s2;
            }
            return m.s1 - this.s1;
        }
    }

    public int solution(int[][] scores) {
        int ws = scores[0][0] + scores[0][1];
        for(int i = 0; i < scores.length; i++) {
            members.add(new Member(i, scores[i][0], scores[i][1]));
        }
        Collections.sort(members);
        for(int i = 1; i < members.size(); i++) {
            Member curr = members.get(i);
            // System.out.print(curr.num + " ");
            Member prev = members.get(i - 1);
            if(curr.s2 < prev.s2) {
                if(curr.num == 0) return -1;
                members.remove(i);
                i --;
            }
        }

        int rank = 1;
        for(Member member : members) {
            if(member.s1 + member.s2 > ws) rank ++;
        }
        return rank;
    }
}
