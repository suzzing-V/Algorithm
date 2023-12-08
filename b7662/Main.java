import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());
        StringTokenizer st;

        for(int i = 0; i < t; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = Integer.parseInt(bf.readLine());

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(bf.readLine());

                String cal = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if(cal.equals("I")) {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                } else if(cal.equals("D") && !map.isEmpty()) {
                    if(value == -1) {
                        int min = map.firstKey();
                        if(map.get(min) == 1) map.remove(min);
                        else map.put(min, map.get(min) - 1);
                    } else {
                        int max = map.lastKey();
                        if(map.get(max) == 1) map.remove(max);
                        else map.put(max, map.get(max) - 1);
                    }
                }
            }
            
            if(map.isEmpty()) bw.write("EMPTY\n");
            else bw.write(map.lastKey() + " " + map.firstKey() + "\n");
        }
        
        bw.close();
    }
}