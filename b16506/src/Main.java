import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bf.readLine());
		
		HashMap<String, String> opcode = new HashMap<>(); //opcode 저장
		String[] name = {"ADD", "SUB", "MOV", "AND", "OR", "NOT",
				"MULT", "LSFTL", "LSFTR", "ASFTR", "RL", "RR"};
		for(int i = 0; i < name.length; i++) { //opcode 값 저장
			if(name[i].equals("NOT")) {
				opcode.put("NOT", "01010");
			} else {
				String bin = tenToBin(i);
				StringBuilder sb = new StringBuilder();
				for(int j = 1; bin.length() + j < 5; j++) {
					sb.append("0");
				}
				sb.append(bin);
				opcode.put(name[i], sb.toString() + "0");
				opcode.put(name[i] + "C", sb.toString() + "1");
			}
		}
		
		for(int j = 0; j < n; j++) {
			String[] line = new String[4];
			line = bf.readLine().split(" ");
			StringBuilder sb = new StringBuilder();
			sb.append(opcode.get(line[0])); //opcode 넣기
			sb.append("0"); //5비트 0
			
			int tmp = Integer.parseInt(line[1]);
			String bi = tenToBin(tmp);
			for(int i = 0; bi.length() + i < 3; i++) {
				sb.append("0");
			} //자릿수 채우기
			sb.append(bi); //rD 넣기
			
			tmp = Integer.parseInt(line[2]);
			bi = tenToBin(tmp);
			for(int i = 0; bi.length() + i < 3; i++) {
				sb.append("0");
			}
			sb.append(bi); //rA 넣기
			
			tmp = Integer.parseInt(line[3]);
			bi = tenToBin(tmp);
			if(sb.toString().charAt(4) == '0') {
				for(int i = 0; bi.length() + i < 3; i++) {
					sb.append("0");
				}
				sb.append(bi);
				sb.append("0");
			} else {
				for(int i = 0; bi.length() + i < 4; i++) {
					sb.append("0");
				}
				sb.append(bi);
			} //rB or C# 넣기
			
			bw.write(sb.toString() + "\n");
		}
		bw.close();
	}
	
	public static String tenToBin(int n) {
		if(n == 0) return "0";
		if(n == 1) return "1";
		String str = "";
		str += tenToBin(n / 2);
		str += Integer.toString(n % 2);
		return str;
	}
}