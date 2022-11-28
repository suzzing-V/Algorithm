package b1427;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String n = bf.readLine();
        int[] array_n = new int[n.length()];
        int tmp;

        for (int i = 0; i < array_n.length; i++)
            array_n[i] = n.charAt(i) - 48;

        for (int i = 0; i + 1 < n.length(); i++) {
            for (int j = 0; j + 1 < n.length(); j++) {
                if (array_n[j] <= array_n[j + 1]) {
                    tmp = array_n[j];
                    array_n[j] = array_n[j + 1];
                    array_n[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < array_n.length; i++)
            bw.write(Integer.toString(array_n[i]));

        bw.close();
    }
}
