import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[3];
		line = bf.readLine().split(" ");
		
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		int c = Integer.parseInt(line[2]);
		
		long result = divideAndRemain(a, b, c);
		bw.write(Long.toString(result));
		bw.close();
	}
	
	public static long divideAndRemain(int a, int b, int c) {
		if(b == 1) { return a % c; }
		long result = divideAndRemain(a, b / 2, c) % c;
		if(b % 2 == 0) { return result* result % c; }
		else { return ((result * result % c)  * a % c); }
	}
}