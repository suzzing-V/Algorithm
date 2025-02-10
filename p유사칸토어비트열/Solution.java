import java.util.*;

class Solution {
    public int solution(int n, long l, long r) {
        int oneCnt = count(r, n) - count(l - 1, n);
        return oneCnt;
    }

    private int count(long pos, int bitCount) {
        if(bitCount == 0) {
            return 1;
        }

        int prevBitCount = bitCount - 1;
        int zone = (int) (pos / (long)Math.pow(5, prevBitCount));
        long rest = pos % (long)Math.pow(5, prevBitCount);
        if(rest == 0) {
            zone --;
            rest = (long)Math.pow(5, prevBitCount);
        }

        if(zone == 2) {
            return 2 * (int)Math.pow(4, prevBitCount);
        } else if(zone > 2) {
            return (zone - 1) * (int)Math.pow(4, prevBitCount) + count(rest, prevBitCount);
        } else if(zone < 2) {
            return zone * (int)Math.pow(4, prevBitCount) + count(rest, prevBitCount);
        }

        return 0;
    }
}
