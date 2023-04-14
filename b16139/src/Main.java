import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		long q = Long.parseLong(bf.readLine());
		
		long[][] acc = new long[26][s.length()];
		acc[s.charAt(0) - 'a'][0] = 1;
		for(int i = 1; i < s.length(); i++) {
			for(int j = 0; j < 26; j++) {
				if(s.charAt(i) == j + 'a') {
					acc[j][i] = acc[j][i - 1] + 1;
				} else {
					acc[j][i] = acc[j][i - 1];
				}
			}
		}
		
		char a;
		int l, r;
		long result;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= q; i++) {
			String[] line = new String[3];
			line = bf.readLine().split(" ");
			a = line[0].charAt(0);
			l = Integer.parseInt(line[1]);
			r = Integer.parseInt(line[2]);

			if(l == 0) {
				result = acc[a - 'a'][r];
			} else {
				result = acc[a - 'a'][r] - acc[a - 'a'][l - 1];
			}
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}