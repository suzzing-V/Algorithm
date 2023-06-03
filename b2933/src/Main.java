import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = new String[2];
		rc = bf.readLine().split(" ");
		int r = Integer.parseInt(rc[0]);
		int c = Integer.parseInt(rc[1]);
		
		String[][] field = new String[r][c];
		for(int i = 0; i < r; i++) {
			String[] line = new String[c];
			line = bf.readLine().split("");
			for(int j = 0; j < c; j++) {
				field[i][j] = line[c];
			}
		}
		
		int n = Integer.parseInt(bf.readLine());
		String[] height = new String[n];
		height = bf.readLine().split(" ");
		
		for(int i = 0; i < n; i++) {
			throwStick(field, Integer.parseInt(height[i]), i + 1);
		}
		
		printField(field);
	}
	
	public static void throwStick(String[][] field, int h, int order) {
		if(order % 2 == 1) {
			int i = 0;
			while(i < field[0].length && [h - 1][i].equals(".")) i++;
			if(i == field[0].length) return;
			field[h - 1][i] = ".";
		} else {
			int i = field[0].length - 1;
			while(i >= 0 && field[h - 1][i].equals(".")) i--;
			if(i == -1) return;
			field[h - 1][i] = ".";
		}
		
		int min = findMinHeight();
		fallCluster(min);
	}
}