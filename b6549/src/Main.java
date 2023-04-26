import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long left, right, r;
		
		while(true) {
			long max = 0;
			String[] line = new String[100000];
			line = bf.readLine().split(" ");
	
			int n = Integer.parseInt(line[0]);
			long[] arr = new long[n];
			if(n == 0) break;
			for(int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(line[i + 1]);
			}
			
			for(int i = 0; i < n; i++) {
				left = maxRight(arr[i], arr, i);
				right = maxLeft(arr[i], arr, i);
				
				r = (left + right + 1) * arr[i];
				if(r > max) max = r;
			}
			bw.write(Long.toString(max) + "\n");
		}
		
		bw.close();
	}
	
	public static long maxRight(long h, long[] arr, int c) {
		long length = 0;
		for(int i = c + 1; i < arr.length; i++) {
			if(arr[i] < h) break;
			else {
				length++;
			}
		}
		return length;
	}
	
	public static long maxLeft(long h, long[] arr, int c) {
		long length = 0;
		for(int i = c - 1; i >= 0; i--) {
			if(arr[i] < h) break;
			else {
				length++;
			}
		}
		return length;
	}
}