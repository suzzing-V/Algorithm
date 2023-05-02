import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		int tmp = 0;
		
		for(int i = 0; i < n; i++) {
			String[] line = new String[2];
			line = bf.readLine().split(" ");
			
			if(line[0].equals("push")) {
				queue.offer(Integer.parseInt(line[1]));
				tmp = Integer.parseInt(line[1]);
			} else if (line[0].equals("pop")){
				if(queue.size() == 0) {
					bw.write("-1");
				} else {
					bw.write(Integer.toString(queue.poll()));
				}
				bw.write("\n");
			} else if (line[0].equals("size")) {
				bw.write(Integer.toString(queue.size()));
				bw.write("\n");
			} else if (line[0].equals("empty")) {
				if(queue.size() == 0) {
					bw.write("1");
				} else {
					bw.write("0");
				}
				bw.write("\n");
			} else if (line[0].equals("front")) {
				if(queue.size() == 0) {
					bw.write("-1");
				} else {
					bw.write(Integer.toString(queue.peek()));
				}
				bw.write("\n");
			} else if (line[0].equals("back")) {
				if(queue.size() == 0) {
					bw.write("-1");
				} else {
					bw.write(Integer.toString(tmp));
				}
				bw.write("\n");
			}
		}
		bw.close();
	}
}