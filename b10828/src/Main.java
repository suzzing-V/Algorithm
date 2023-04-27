import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < n; i++) {
			String[] line = new String[2];
			line = bf.readLine().split(" ");
			if(line[0].equals("push")) {
				stack.push(Integer.parseInt(line[1]));
			} else if (line[0].equals("pop")) {
				if(stack.size() == 0) bw.write(Integer.toString(-1));
				else {
					int pop = stack.pop();
					bw.write(Integer.toString(pop));
				}
				bw.write("\n");
			} else if(line[0].equals("size")) {
				int size = stack.size();
				bw.write(Integer.toString(size));
				bw.write("\n");
			} else if(line[0].equals("empty")) {
				int tmp = (stack.size() == 0)? 1 : 0;
				bw.write(Integer.toString(tmp));
				bw.write("\n");
			} else if(line[0].equals("top")) {
				if(stack.size() == 0) bw.write(Integer.toString(-1));
				else {
					int top = stack.peek();
					bw.write(Integer.toString(top));
				}
				bw.write("\n");
			}
		}
		bw.close();
	}
}