import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 4; i++) {
			String line = bf.readLine();
			if(vicEqualsDef(line) && sumFive(line) && checkTie(line) && smallerThanSix(line)) {
				System.out.print(1 + " ");
			} else {
				System.out.print(0 + " ");
			}
		}
	}
	
	public static boolean vicEqualsDef(String line) {
		int i = 0;
		int vic = 0;
		int def = 0;
		while(i < line.length()) {
			vic += line.charAt(i) - '0';
			i += 6;
		}
		
		i = 4;
		while(i < line.length()) {
			def += line.charAt(i) - '0';
			i += 6;
		}

		if(vic == def) return true;
		else return false;
	}
	
	public static boolean sumFive(String line) {
		int i = 0;
		while(i < line.length()) {
			if(line.charAt(i) - '0' + line.charAt(i + 2) - '0'
					+ line.charAt(i + 4) - '0' > 5) return false;
			i += 6;
		}
		return true;
	}
	
	public static boolean checkTie(String line) {
		int i = 2;
		int res = 0;
		while(i < line.length()) {
			if(res == 0 && line.charAt(i) != 0) {
				res += line.charAt(i) - '0';
			} else if(res != 0 && line.charAt(i) != 0) {
				res -= line.charAt(i) - '0';
			}
			i += 6;
		}
		
		if(res != 0) return false;
		else return true;
	}
	
	public static boolean smallerThanSix(String line) {
		int i = 0;
		while(i < line.length()) {
			if(line.charAt(i) - '0' > 6 && line.charAt(i) - '0' < 0) return false;
			i += 2;
		}
		return true;
	}
}