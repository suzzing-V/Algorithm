import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		String sign = bf.readLine();
		int line = sign.length() / 5;
		for(int i = 0; i < line; i++) {
			if(sign.charAt(i) == '#') {
				int k = i;
				while(sign.charAt(k) == '#') k++;
				if(k - i == 1) { i += judgeDigit(sign, 1, i, line); } //1, 4인 경우
				else { i += judgeDigit(sign, 3, i, line); } //나머지 수인 경우
			}
		}
	}
	
	public static int judgeDigit(String sign, int star, int index, int line) {
		if(star == 3) {
			String tmp = sign.substring(index + line, index + line + 3);
			if(tmp.equals("#.#")) {
				tmp = sign.substring(index + line * 2, index + line * 2 + 3);
				if(tmp.equals("#.#")) System.out.print(0);
				else {
					tmp = sign.substring(index + line * 3, index + line *3 + 3);
					if(tmp.equals("#.#")) System.out.print(8);
					else { System.out.print(9); }
				}
			} else if(tmp.equals("..#")) {
				tmp = sign.substring(index + line * 2, index + line * 2 + 3);
				if(tmp.equals("..#")) System.out.print(7);
				else {
					tmp = sign.substring(index + line * 3, index + line *3 + 3);
					if(tmp.equals("#..")) System.out.print(2);
					else { System.out.print(3); }
				}
			} else {
				tmp = sign.substring(index + line * 3, index + line * 3 + 3);
				if(tmp.equals("..#")) System.out.print(5);
				else { System.out.print(6); }
			}
			return 2;
		} else {
			String tmp = sign.substring(index + line * 3, index + line * 3 + 1);
			if(tmp.equals(".")) {
				System.out.print(4);
				return 2;
			} else {
				System.out.print(1);
				return 0;
			}
		}
	}
}