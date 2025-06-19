import java.util.*;

class Solution {

    private PriorityQueue<Member> pq = new PriorityQueue<>();
    private List<Member> members = new ArrayList<>();

    public int solution(int[][] scores) {
        // 직원 정보 리스트에 담기
        for(int i = 0; i < scores.length; i++) {
            members.add(new Member(i, scores[i][0], scores[i][1]));
        }

        // 태도평가 내림차순, 직원평가 오름차순 정렬
        Collections.sort(members, (o1, o2) -> {
            if(o1.s1 == o2.s1) {
                return o1.s2 - o2.s2;
            }
            return o2.s1 - o1.s1;
        });

        // 태도평가 점수 바뀌는 지점에서 앞의 직원의 동료평가 점수보다 뒤의 직원의 동료평가 점수가 작으면 pq에 넣지 않기
        pq.add(members.get(0));
        for(int i = 1; i < members.size(); i++) {
            Member prev = members.get(i - 1);
            Member curr = members.get(i);

            if(prev.s1 != curr.s1 && curr.s2 < prev.s2) {
                members.remove(i);
                i --;
            } else {
                pq.add(curr);
            }
        }

        // 원호의 등수 세기
        int rank = 1;
        while(!pq.isEmpty()) {
            Member curr = pq.remove();
            // System.out.println(curr.s1 + " " + curr.s2);
            int sum = curr.s1 + curr.s2;
            int cnt = 1;
            if(curr.id == 0) {
                return rank;
            }

            while(!pq.isEmpty()) {
                Member same = pq.peek();
                if(same.s1 + same.s2 != sum) {
                    break;
                }
                // System.out.println(same.s1 + " " + same.s2);
                if(same.id == 0) {
                    return rank;
                }

                pq.remove();
                cnt ++;
            }
            rank += cnt;
        }
        return -1;
    }

    class Member implements Comparable<Member> {
        int id;
        int s1;
        int s2;

        Member(int id, int s1, int s2) {
            this.id = id;
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public int compareTo(Member m) {
            return (m.s1 + m.s2) - (this.s1 + this.s2);
        }
    }
}