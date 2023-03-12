import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        for(int i = 0; i < n; i++) {
            String[] line = new String[6];
            line = bf.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int r1 = Integer.parseInt(line[2]);
            int x2 = Integer.parseInt(line[3]);
            int y2 = Integer.parseInt(line[4]);
            int r2 = Integer.parseInt(line[5]);

            double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            int maxR = r1 >= r2? r1 : r2;
            int minR = r1 < r2? r1 : r2;

            if(distance >= maxR) {
                if((minR + maxR) > distance) bw.write("2");
                else if(minR + maxR == distance) bw.write("1");
                else bw.write("0");
            } else if(distance == 0) {
                if(maxR == minR) bw.write("-1");
                else bw.write("0");
            } else {
                if(distance + minR < maxR) bw.write("0");
                else if(distance + minR == maxR) bw.write("1");
                else bw.write("2");
            }
            bw.write("\n");
        }
        bw.close();
    }
}
