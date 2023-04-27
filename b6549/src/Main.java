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
			long[] hist = new long[n];
			if(n == 0) break;
			for(int i = 0; i < n; i++) {
				hist[i] = Long.parseLong(line[i + 1]);
			}
			
			bw.write(Long.toString(maxRectangle(hist, 0, n - 1)) + "\n");
		}
		
		bw.close();
	}
	
	public static long maxRectangle(long[] hist, int start, int end) {
		if(start == end)
			return hist[start];
		
		int mid = (start + end) / 2;
		long left = maxRectangle(hist, start, mid);
		long right = maxRectangle(hist, mid + 1, end);
		long max = Math.max(left, right);
		
		long midArea = getMidArea(hist, start, end);
		max = Math.max(max, midArea);
		return max;
	}
	
	public static long getMidArea(long[] hist, int start, int end) {
		int left = (start + end) / 2;
		int right = (start + end) / 2;
		long tmp = 0;
		long minHeight = hist[left];
		long maxArea = 0;
		while(left > start && right < end) {
			if(hist[left - 1] > hist[right + 1]) {
				left--;
				tmp = hist[left];
			} else {
				right ++;
				tmp = hist[right];
			}
			minHeight = Math.min(minHeight, tmp);
			maxArea = Math.max(minHeight * (right - left + 1), maxArea);
		}
		
		while(left > start) {
			left--;
			minHeight = Math.min(minHeight, hist[left]);
			maxArea = Math.max(minHeight * (right - left + 1), maxArea);
		}
		
		while(right < end) {
			right++;
			minHeight = Math.min(minHeight, hist[right]);
			maxArea = Math.max(minHeight * (right - left + 1), maxArea);
		}

		return maxArea;
	}
}