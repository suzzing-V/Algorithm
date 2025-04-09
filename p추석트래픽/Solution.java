class Solution {
    static class Log {
        int start;
        int end;
        Log(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String[] lines) {
        Log[] logs = new Log[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] split = lines[i].split(" ");
            int endTime = toMs(split[1]);
            double duration = Double.parseDouble(split[2].substring(0, split[2].length() - 1));
            int processTime = (int)(duration * 1000);
            int startTime = endTime - processTime + 1;
            logs[i] = new Log(startTime, endTime);
        }

        int maxCount = 0;
        // 구간의 시작과 끝에서만 처리량 값이 변한다. 따라서 구간의 시작과 끝 기준 1초를 확인하면 된다.
        for (Log log : logs) {
            // 기준 시간: 시작점 기준 구간 [start, start + 999]
            int count1 = countOverlap(logs, log.start, log.start + 999);
            // 기준 시간: 끝점 기준 구간 [end, end + 999]
            int count2 = countOverlap(logs, log.end, log.end + 999);
            maxCount = Math.max(maxCount, Math.max(count1, count2));
        }

        return maxCount;
    }

    private int countOverlap(Log[] logs, int windowStart, int windowEnd) {
        int count = 0;
        for (Log log : logs) {
            if (log.end >= windowStart && log.start <= windowEnd) {
                count++;
            }
        }
        return count;
    }

    private int toMs(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        double second = Double.parseDouble(split[2]);
        return (int)((hour * 3600 + minute * 60 + second) * 1000);
    }
}

