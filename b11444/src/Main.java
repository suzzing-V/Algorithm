import java.io.*;

public class Main {
	static long mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long n = Long.parseLong(bf.readLine());
		long[][] arr = new long[2][2];
		arr[0][0] = 1; arr[0][1] = 1;
		arr[1][0] = 1; arr[1][1] = 0;
		bw.write(Long.toString((pow(arr, n - 1))[0][0]));
		bw.close();
	}
	
	public static long[][] pow(long[][] arr, long expo) {
		if(expo == 1 || expo == 0) {
			return arr;
		}
		
		long[][] tmp = pow(arr, expo / 2);
		if(expo % 2 == 0) { return multiply(tmp, tmp); }
		return multiply(multiply(tmp, tmp), arr);
	}
	
	public static long[][] multiply(long[][] arr1, long[][] arr2) {
		long[][] result = new long[2][2];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				result[i][j] = (((arr1[i][0] % mod) * (arr2[0][j] % mod)) % mod
							+ ((arr1[i][1] % mod) * (arr2[1][j] % mod)) % mod) % mod;
			}
		}
		return result;
	}
}