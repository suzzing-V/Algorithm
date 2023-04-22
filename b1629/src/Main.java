import java.io.*;

public class Main {
	static int[] remain = new int[1000000];
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[3];
		line = bf.readLine().split(" ");
		
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		int c = Integer.parseInt(line[2]);
		
		int result = divideAndRemain(a, b, c);
		bw.write(Integer.toString(result));
		bw.close();
	}
	
	public static int divideAndRemain(int a, int b, int c) {
		if(b == 1 || a % c == 0) { return a % c; }
		if(b < 1000000 && remain[b] != 0) { return remain[b]; }
		int r1 = divideAndRemain(a, b / 2, c);
		int r2 = divideAndRemain(a, b - b / 2, c);
		if(r1 == 0 || r2 == 0) { return 0; }
		int result = (r1 * r2) % c;
		if(b < 1000000)
			remain[b] = result;
		return result;
	}
}