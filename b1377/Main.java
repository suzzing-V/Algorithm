import java.util.*;
import java.io.*;

// 시간복잡도: 5 * 10^4 * (log5 + 4) + 5 * 10^4
// 정렬 후 좌측으로 인덱스 변화가 가장 큰 인덱스 차 구하기
public class Main {

    private static Element[] arr;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new Element[n];

        for(int i = 0; i < n; i++) {
            arr[i] = new Element(Integer.parseInt(bf.readLine()), i);
        }

        Arrays.sort(arr);

        int max = 0;
        for(int i = 0; i < n; i++) {
            Element e = arr[i];
            max = Math.max(e.idx - i, max);
        }

        System.out.println(max + 1);
    }

    private static class Element implements Comparable<Element> {
        private int n;
        private int idx;

        Element(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }

        @Override
        public int compareTo(Element e) {
            return this.n - e.n;
        }
    }
}
