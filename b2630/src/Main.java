import java.io.*;
public class Main {
	static int white = 0;
	static int blue = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		int[][] square = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] line = new String[n];
			line = bf.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				square[i][j] = Integer.parseInt(line[j]);
			}
		}
		divideSquare(0, 0, n, square);
		bw.write(Integer.toString(white) + "\n");
		bw.write(Integer.toString(blue) + "\n");
		bw.close();
	}
	
	public static void divideSquare(int x, int y, int n, int[][] square) {
		if(n == 1) { 
			if(square[x][y] == 0) { white++; }
			else { blue++; }
			return; 
		}
		int tmp = square[x][y];
		for(int i = x; i < x + n; i++) {
			for(int j = y; j < y + n; j++) {
				if(square[i][j] != tmp) {
					divideSquare(x, y, n / 2, square);
					divideSquare(x, y + n / 2, n / 2, square);
					divideSquare(x + n / 2, y, n / 2, square);
					divideSquare(x + n / 2, y + n / 2, n / 2, square);
					return;
				}
			}
		}
		if(tmp == 0) {
			white++;
		} else {
			blue++;
		}
		return;
	}
}