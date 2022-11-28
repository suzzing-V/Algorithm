package b10814;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        int[] age = new int[n];
        String[] name = new String[n];
        int tmp;
        String smp;

        for(int i = 0; i < n; i++) {
            String[] member = bf.readLine().split(" ");
            age[i] = Integer.parseInt(member[0]);
            name[i] = member[1];
        }

        for(int i = 0; i + 1 < n; i++) {
            for(int j = 0; j + 1 + i< n; j++) {
                if(age[j] > age[j + 1]) {
                    tmp = age[j];
                    age[j] = age[j + 1];
                    age[j + 1] = tmp;

                    smp = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = smp;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            bw.write(Integer.toString(age[i]) + " " + name[i]);
            bw.write("\n");
        }

        bw.close();
    }
}