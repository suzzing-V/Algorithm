import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[2];
		line = bf.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		long b = Long.parseLong(line[1]);
		int[][] arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			String[] row = new String[n];
			row = bf.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(row[j]) % 1000;
			}
		}
		
		int[][] result = pow(arr, b, n);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				bw.write(Integer.toString(result[i][j]) + " ");
			}
			bw.write("\n");
		}
		bw.close();
	}
	
	public static int[][] pow(int[][] arr, long expo, int n) {
		if(expo == 1) {
			return arr;
		}
		
		int[][] tmp = pow(arr, expo / 2, n);
		if(expo % 2 == 0) {
			return multiply(tmp, tmp, n);
		} else {
			return multiply(multiply(tmp, tmp, n), arr, n);
		}
	}
	
	public static int[][] multiply(int[][] arr1, int[][] arr2, int n) {
		int[][] result = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int tmp = 0;
				for(int k = 0; k < n; k++) {
					tmp += (arr1[i][k] * arr2[k][j]) % 1000;
				}
				result[i][j] = tmp % 1000;
			}
		}
		return result;
	}
}