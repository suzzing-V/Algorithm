import java.io.*;
import java.util.*;

public class Main {

    static class Number implements Comparable<Number> {
        int n1;
        int n2;

        Number(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        @Override
        public int compareTo(Number o){
            if(this.n1 == o.n1) {
                return this.n2 - o.n2;
            }
            return this.n1 - o.n1;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        Number[] numbers = new Number[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), ".");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = -1;
            if(st.hasMoreTokens()) {
                n2 = Integer.parseInt(st.nextToken());
            }
            numbers[i] = new Number(n1, n2);
        }

        Arrays.sort(numbers);
        for(int i = 0; i < n; i++) {
            if(numbers[i].n2 == -1) {
                bw.write(numbers[i].n1 + "\n");
            } else {
                bw.write(numbers[i].n1 + "." + numbers[i].n2 + "\n");
            }
        }
        bw.close();
    }
}

