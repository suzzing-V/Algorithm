import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 4; i++) {
			String line = bf.readLine();
			int[][] result = new int[6][3];
			int j = 0, k = 0;
			while(k < line.length()) {
				result[j][0] = line.charAt(k) - '0';
				k += 2;
				result[j][1] = line.charAt(k) - '0';
				k += 2;
				result[j][2] = line.charAt(k) - '0';
				k += 2;
				//System.out.println("result: " + result[j][0] + " " + result[j][1] + " " + result[j][2]);
				j++;
			}
			if(dfs(0,1, result)) System.out.print(1 + " ");
			else System.out.print(0 + " ");
		}
	}
	
	public static boolean dfs(int t1, int t2, int[][] result) {
		if(t2 == 6) {
			t1 ++;
			t2 = t1 + 1;
		}
		if(t1 == 5) return true;
		
		//System.out.println("t1 t2: " + t1 + " " + t2);
		//t1 win
		result[t1][0] -= 1;
		result[t2][2] -= 1;
		if(result[t1][0] >= 0 && result[t2][2] >= 0) {
			if(dfs(t1, t2 + 1, result)) return true;
		}
		result[t1][0] += 1;
		result[t2][2] += 1;
		//System.out.println("win pass");
		//draw
		result[t1][1] -= 1;
		result[t2][1] -= 1;
		if(result[t1][1] >= 0 && result[t2][1] >= 0) {
			if(dfs(t1, t2 + 1, result)) return true;
		}
		result[t1][1] += 1;
		result[t2][1] += 1;
		//System.out.println("draw pass");
		//t1 lose
		result[t1][2] -= 1;
		result[t2][0] -= 1;
		if(result[t1][2] >= 0 && result[t2][0] >= 0) {
			if(dfs(t1, t2 + 1, result)) return true;
		}
		result[t1][2] += 1;
		result[t2][0] += 1;
		//System.out.println("lose pass");
		
		return false;
	}
}