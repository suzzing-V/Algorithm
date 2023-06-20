import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < t; i++) {
			char[] a = bf.readLine().toCharArray();
			char[] b = bf.readLine().toCharArray();
			int count = 0;
			
			for(int j = 0; j < a.length; j++) {
				if((a[j] ^ b[j]) == 1) count++;
			}
			
			System.out.println("Hamming distance is " + count + ".");
		}
	}
}