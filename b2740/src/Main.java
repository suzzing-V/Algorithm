import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[2];
		line = bf.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int[][] arr1 = new int[n][m];
		for(int i = 0; i < n; i++) {
			String[] row = new String[m];
			row = bf.readLine().split(" ");
			for(int j = 0; j < m; j++) {
				arr1[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		line = bf.readLine().split(" ");
		int k = Integer.parseInt(line[1]);
		int[][] arr2 = new int[m][k];
		for(int i = 0; i < m; i++) {
			String[] row = new String[k];
			row = bf.readLine().split(" ");
			for(int j = 0; j < k; j++) {
				arr2[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		long[][] result = new long[n][k];
		int sum;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k; j++) {
				sum = 0;
				for(int l = 0; l < m; l++) {
					sum += arr1[i][l] * arr2[l][j];
				}
				result[i][j] = sum;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k; j++) {
				bw.write(Long.toString(result[i][j]) + " ");
			}
			bw.write("\n");
		}
		bw.close();
	}
}