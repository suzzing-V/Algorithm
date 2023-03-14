import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            String[] startEnd = new String[4];
            startEnd = bf.readLine().split(" ");
            int n = Integer.parseInt(bf.readLine());
            int count = 0;
            int x1 = Integer.parseInt(startEnd[0]), y1 = Integer.parseInt(startEnd[1]),
                x2 = Integer.parseInt(startEnd[2]), y2 = Integer.parseInt(startEnd[3]);

            for(int j = 0 ; j < n; j++) {
                String[] planet = new String[3];
                planet = bf.readLine().split(" ");
                int px = Integer.parseInt(planet[0]), py = Integer.parseInt(planet[1]),
                    pr = Integer.parseInt(planet[2]);
                double startDis = Math.sqrt(Math.pow(px - x1, 2) + Math.pow(py - y1, 2));
                double endDis = Math.sqrt(Math.pow(px - x2, 2) + Math.pow(py - y2, 2));
                if(startDis < pr || endDis < pr) {
                    count++;
                }
            }
            bw.write(Integer.toString(count) + "\n");
        }
        bw.close();
    }
}
