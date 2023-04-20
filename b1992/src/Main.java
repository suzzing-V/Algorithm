import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//ufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		int[][] screen = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] line = new String[n];
			line = bf.readLine().split("");
			for(int j = 0; j < n; j++) {
				screen[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		quadTree(screen, 0, 0, n);
		//bw.close();
	}
	
	public static void quadTree(int[][] screen, int x, int y, int n) throws IOException {
		if(n == 1) {
			System.out.print(screen[x][y]);
			return;
		}
		
		int tmp = screen[x][y];
		for(int i = x; i < x + n; i++) {
			for(int j = y; j < y + n; j++) {
				if(screen[i][j] != tmp) {
					//System.out.println("check: " + x + " " + y + " " + n);
					System.out.print("(");
					quadTree(screen, x, y, n / 2);
					quadTree(screen, x, y + n / 2, n / 2);
					quadTree(screen, x + n / 2, y, n / 2);
					quadTree(screen, x + n / 2, y + n / 2, n / 2);
					System.out.print(")");
					return;
				}
			}
		}
		System.out.print(tmp);
		return;
	}
}