import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		int[] arr = new int[n];

		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		pushOrPop(arr, n, bw);
		bw.close();
	}
	
	public static void pushOrPop(int[] arr, int n, BufferedWriter bw) throws IOException {
		Stack<Integer> stack = new Stack<>();
		String[] sign = new String[n * 2];
		int p = 0, i = 1, s = 0;
		
		while(true) {
			while(stack.size() != 0 && stack.peek() == arr[p]) {
				stack.pop();
				p ++;
				sign[s++] = "-";
			}
			if(i == n + 1) break;
			stack.push(i++);
			sign[s++] = "+";
		}
		
		if(stack.size() != 0) {
			bw.write("NO");
		} else {
			for(int j = 0; j < n * 2; j++) {
				bw.write(sign[j] + "\n");
			}
		}
		return;
	}
}