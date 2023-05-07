import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		bw.write(Integer.toString(moveCard(queue)));
		bw.close();
	}
	
	public static int moveCard(Queue<Integer> queue) {
		if(queue.size() == 1) {
			return queue.poll();
		}
		
		queue.poll();
		queue.offer(queue.poll());
		return moveCard(queue);
	}
}