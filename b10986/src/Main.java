import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] nm = new String[2];
		nm = bf.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		int[] arr = new int[n + 1];
		String[] line = new String[n];
		line = bf.readLine().split(" ");
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(line[i - 1]);
		}
	
		long[] acc = new long[n + 1];
		acc[1] = arr[1];
		for(int i = 1; i <= n; i++) {
			acc[i] = acc[i - 1] + arr[i];
		}
		
		long[] remain = new long[n + 1];
		for(int i = 1;i <= n; i++) {
			remain[i] = acc[i] % m;
		}
		
		int count = 0;
		for(int i = 0; i <= n; i++) {
			for(int j = i + 1; j <= n; j++) {
				if(remain[i] == remain[j]) {
					count++;
				}
			}
		}
		bw.write(Integer.toString(count));
		bw.close();
	}
}