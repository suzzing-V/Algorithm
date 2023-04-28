import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int k = Integer.parseInt(bf.readLine());
		Stack<Integer> cog = new Stack<>();
		int sum = 0;
		
		for(int i = 0; i < k; i++) {
			int tmp = Integer.parseInt(bf.readLine());
			if(tmp == 0) {
				sum -= cog.peek();
				cog.pop();
			} else {
				cog.push(tmp);
				sum += tmp;
			}
		}

		bw.write(Integer.toString(sum));
		bw.close();
	}
}