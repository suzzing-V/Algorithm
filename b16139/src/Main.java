import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = bf.readLine();
		long q = Long.parseLong(bf.readLine());
		
		char a;
		int l, r;
		long result;
		
		for(int i = 1; i <= q; i++) {
			String[] line = new String[3];
			line = bf.readLine().split(" ");
			a = line[0].charAt(0);
			l = Integer.parseInt(line[1]);
			r = Integer.parseInt(line[2]);
			
			long[] acc = new long[s.length()];
			getAccumulateArr(s, a, acc);
			if(l == 0) {
				result = acc[r];
			} else {
				result = acc[r] - acc[l - 1];
			}
			bw.write(Long.toString(result) + "\n");
		}
		bw.close();
		
	}
	
	public static void getAccumulateArr(String s, char a, long[] acc) {
		if(s.charAt(0) == a) { acc[0] = 1; }
		for(int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == a) { acc[i] = acc[i - 1] + 1; }
			else { acc[i] = acc[i - 1]; }
		}
	}
}