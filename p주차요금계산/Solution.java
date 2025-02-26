import java.util.*;

class Solution {

    private Map<Integer, Integer> ins = new HashMap<>();
    private Map<Integer, Integer> carTimes = new HashMap<>();
    private List<Integer> carNums = new ArrayList<>();
    private int[] fees;

    public int[] solution(int[] fees1, String[] records) {
        fees = fees1;
        for(int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            int carNum = Integer.parseInt(st.nextToken());
            String action = st.nextToken();
            st = new StringTokenizer(time, ":");
            int m = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

            if(action.equals("IN")) {
                ins.put(carNum, m);
                if(!carNums.contains(carNum)) {
                    carNums.add(carNum);
                }
            } else {
                int inM = ins.get(carNum);
                ins.remove(carNum);
                int total = m - inM;
                carTimes.put(carNum, carTimes.getOrDefault(carNum, 0) + total);
            }
        }

        for(int carNum : ins.keySet()) {
            int inM = ins.get(carNum);
            int total = 23 * 60 + 59 - inM;
            carTimes.put(carNum, carTimes.getOrDefault(carNum, 0) + total);
        }

        Collections.sort(carNums);
        int[] answer = new int[carNums.size()];
        for(int i = 0; i < carNums.size(); i++) {
            answer[i] = calFee(carTimes.get(carNums.get(i)));
        }

        return answer;
    }

    private int calFee(int time) {
        time = time > fees[0] ? time - fees[0] : 0;
        int unit = time / fees[2];
        if(time % fees[2] != 0) {
            unit ++;
        }
        return fees[1] + unit * fees[3];
    }
}
