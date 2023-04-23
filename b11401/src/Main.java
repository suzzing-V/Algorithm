import java.io.*;

public class Main {
	static int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[2];
		line = bf.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		long result = ((getRemain(n, k) % mod) / (getRemain(k, k) % mod)) % mod;
		bw.write(Long.toString(result));
		bw.close();
	}
	
	public static long getRemain(int n, int k) {
		if(k == 0) {
			return 1;
		}
		
		long r = n % mod;
		return r * getRemain(n - 1, k - 1);
	}
}