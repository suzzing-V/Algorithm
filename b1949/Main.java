import java.io.*;
import java.util.*;

public class Main {

    private static class Applicant {
        private int first;
        private int second;

        Applicant(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            List<Applicant> applicants = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                applicants.add(new Applicant(first, second));
            }
            Collections.sort(applicants, (o1, o2) -> {return o1.first - o2.first;});
            for(int j = 1; j < applicants.size(); j++) {
                Applicant curr = applicants.get(j);
                Applicant prev = applicants.get(j - 1);
                if(curr.second > prev.second) {
                    applicants.remove(curr);
                    j --;
                }
            }
            Collections.sort(applicants, (o1, o2) -> {return o1.second - o2.second;});
            for(int j = 1; j < applicants.size(); j++) {
                Applicant curr = applicants.get(j);
                Applicant prev = applicants.get(j - 1);
                if(curr.first > prev.first) {
                    applicants.remove(curr);
                    j --;
                }
            }
            bw.write(applicants.size() + "\n");
        }
        bw.close();
    }
}
