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
		
		long nF = factorial(n);
		long nkF = factorial(n - k);
		long kF = factorial(k);
		long result = (nF * powRemain((nkF * kF) % mod, mod - 2)) % mod;
		bw.write(Long.toString(result));
		bw.close();
	}
	
	public static long factorial(int n) {
		if(n == 1 || n == 0) {
			return 1;
		}
		return ((n % mod) * (factorial(n - 1) % mod)) % mod;  
	}
	
	public static long powRemain(long base, long expo) {
		if(expo == 1) {
			return base % mod;
		}
		
		long tmp = powRemain(base, expo / 2);
		if(expo % 2 == 0) {
			return (tmp * tmp) % mod;
		} else {
			return (((tmp * tmp) % mod) * (base % mod)) % mod;
		}
	}
}