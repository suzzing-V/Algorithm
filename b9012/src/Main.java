import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < n; i++) {
			String[] line = new String[50];
			line = bf.readLine().split("");
			bw.write(isVPS(line) + "\n");
		}
		bw.close();
	}
	
	public static String isVPS(String[] line) {
		Stack<String> vps = new Stack<>();
		int sum = 0;
		for(int i = 0; i < line.length; i++) {
			if(line[i].equals(")")) {
				if(vps.size() == 0) return "NO";
				else {
					vps.pop();
					sum --;
				}
			} else {
				vps.push("(");
				sum ++;
			}
		}
		
		if(sum != 0) return "NO";
		return "YES";
	}
}