import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = new String[2];
		line = bf.readLine().split(" ");
		int s = Integer.parseInt(line[0]);
		String n = line[1];
		
		for(int i = 0; i < n.length(); i++) {
			printOneLine(n.charAt(i), 1, s);
			System.out.print(" ");
		}
		System.out.println();
		
		for(int i = 0; i < s; i++) {
			for(int j = 0; j < n.length(); j++) {
				printOneLine(n.charAt(j), 2, s);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < n.length(); i++) {
			printOneLine(n.charAt(i), 3, s);
			System.out.print(" ");
		}
		System.out.println();
		
		for(int i = 0; i < s; i++) {
			for(int j = 0; j < n.length(); j++) {
				printOneLine(n.charAt(j), 4, s);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < n.length(); i++) {
			printOneLine(n.charAt(i), 5, s);
			System.out.print(" ");
		}
		//bw.close();
	}
	
	public static void printOneLine(char digit, int line, int s) {
		if(line == 1) {
			if(digit == '1' || digit == '4') printDigit(1, s);
			else { printDigit(2, s); }
		} else if(line == 2) {
			if(digit == '5' || digit == '6') printDigit(5, s);
			else if(digit == '1' || digit == '2' || digit == '3' || digit == '7') printDigit(4, s);
			else { printDigit(3, s); }
		} else if(line == 3) {
			if(digit == '1' || digit == '7' || digit == '0') printDigit(1, s);
			else printDigit(2, s);
		} else if(line == 4) {
			if(digit == '2') printDigit(5, s);
			else if(digit == '6' || digit == '8' || digit == '0') printDigit(3, s);
			else { printDigit(4, s); }
		} else {
			if(digit == '1' || digit == '4' || digit == '7') printDigit(1, s);
			else { printDigit(2, s); }
		}
	}
	
	public static void printDigit(int type, int s) {
		if(type == 1) {
			for(int i = 0; i < s + 2; i++) System.out.print(" ");
		} else if(type == 2) {
			System.out.print(" ");
			for(int i = 0; i < s; i++) System.out.print("-");
			System.out.print(" ");
		} else if(type == 3) {
			System.out.print("|");
			for(int i = 0; i < s; i++) System.out.print(" ");
			System.out.print("|");
		} else if(type == 4) {
			System.out.print(" ");
			for(int i = 0; i < s; i++) System.out.print(" ");
			System.out.print("|");
		} else {
			System.out.print("|");
			for(int i = 0; i < s; i++) System.out.print(" ");
			System.out.print(" ");
		}
	}
}