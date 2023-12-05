import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            String line = bf.readLine();
            int n = Integer.parseInt(bf.readLine());
            String arr = bf.readLine();
            String[] split = arr.substring(1, arr.length() - 1).split(",");

            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                list.add(Integer.parseInt(split[j]));
            }

            boolean chk = true;
            int start = 0;
            for(int j = 0; j < line.length(); j++) {
                if(line.charAt(j) == 'D') {
                    if(list.isEmpty()) {
                        bw.write("error\n");
                        chk = false;
                        break;
                    }
                    list.remove(start);
                    if(start != 0) start = list.size() - 1;
                } else if(line.charAt(j) == 'R') {
                    if(start == 0) start = list.size() - 1;
                    else start = 0;
                }
            }
            if(!chk) continue;
            printList(start, bw, list);
        }
        bw.close();
    }

    public static void printList(int start, BufferedWriter bw, List<Integer> list) throws IOException {
        bw.write("[");
        if(start == 0) {
        for(int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)));
            if(i != list.size() - 1) bw.write(",");
        }
    } else {
        for(int i = list.size() - 1; i >= 0; i--) {
            bw.write(String.valueOf(list.get(i)));
            if(i != 0) bw.write(",");
        }
    }
        bw.write("]\n");
    }
}