import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] nm = new String[2];
		nm = bf.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		int[][] arr = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			String[] line = new String[n];
			line = bf.readLine().split(" ");
			for(int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(line[j - 1]);
			}
		}
		
		long[][] memo = new long[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				memo[i][j] = memo[i][j - 1] + memo[i - 1][j] - memo[i - 1][j - 1] + arr[i][j];
			}
		}
		
		int x1, y1, x2, y2;
		long result;
		for(int i = 1; i <= m; i++) {
			String[] line = new String[4];
			line = bf.readLine().split(" ");
			x1 = Integer.parseInt(line[0]);
			y1 = Integer.parseInt(line[1]);
			x2 = Integer.parseInt(line[2]);
			y2 = Integer.parseInt(line[3]);
			result = memo[x2][y2] - memo[x2][y1 - 1] - memo[x1 - 1][y2] + memo[x1 - 1][y1 - 1];
			bw.write(Long.toString(result) + "\n");
		}
		bw.close();
	}
}