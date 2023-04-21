import java.io.*;

public class Main {
	static int minus, zero, one;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		
		int[][] paper = new int[n][n];
		for(int i = 0; i < n; i++) {
			String[] line = new String[n];
			line = bf.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		dividePaper(paper, 0, 0, n);
		bw.write(Integer.toString(minus) + "\n");
		bw.write(Integer.toString(zero) + "\n");
		bw.write(Integer.toString(one) + "\n");
		bw.close();
	}
	
	public static void dividePaper(int[][] paper, int x, int y, int n) {
		if(n == 1) {
			if(paper[x][y] == -1) { minus ++; }
			else if(paper[x][y] == 0) { zero ++; }
			else if(paper[x][y] == 1){ one ++; }
			return;
		}
		int tmp = paper[x][y];
		for(int i = x; i < x + n; i++) {
			for(int j = y; j < y + n; j++) {
				if(paper[i][j] != tmp) {
					for(int a = 0; a < 3; a++) {
						for(int b = 0; b < 3; b++) {
							dividePaper(paper, x + a * (n / 3), y + b * (n / 3), n / 3);
						}
					}
					return;
				}
			}
		}
		
		if(tmp == -1) { minus ++; }
		else if(tmp == 0) { zero ++; }
		else if(tmp == 1){ one ++; }
		return;
	}
}