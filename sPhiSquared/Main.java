import java.io.*;
import java.util.*;

public class Main {

    static int n;

    private static class Micro {
        private int idx;
        private long size;

        Micro(int idx, long size) {
            this.idx = idx;
            this.size = size;
        }

        public int getIdx() {
            return this.idx;
        }

        public long getSize() {
            return this.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        LinkedList<Micro> micros = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            long size = Long.parseLong(st.nextToken());
            micros.add(new Micro(i, size));
        }

        while(micros.size() > 1) {
            ListIterator<Micro> iter = micros.listIterator();
            while(iter.hasNext()) {
                Micro prev = iter.hasPrevious() ? iter.previous() : null;
                if(prev != null) iter.next();
                Micro curr = iter.next();
                Micro next = iter.hasNext() ? iter.next() : null;
                if(next != null) iter.previous();
                // System.out.println("c: " + curr.getIdx());
                long currSize = curr.getSize();

                if(prev != null && prev.size <= currSize) {
                    curr.size += prev.size;
                    // System.out.println("이전 냠냠: " + curr.getSize());
                    iter.previous();
                    iter.previous();
                    iter.remove();
                    if(iter.hasNext()) iter.next().getIdx();
                }
                if(next != null && next.size <= currSize) {
                    curr.size += next.size;
                    iter.next();
                    // System.out.println("다음 냠냠: " + curr.getSize());
                    iter.remove(); // remove()는 가장 최근의 iter.next()나 iter.previous가 가리키는 요소를 삭제한다. 삭제 후에는 삭제 요소의 다음에 커서가 놓인다.
                }
            }
        }

        Micro last = micros.get(0);
        bw.write(last.getSize() + "\n" + last.getIdx());
        bw.close();
    }
}
