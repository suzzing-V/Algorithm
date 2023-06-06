import java.io.*;
import java.util.*;

public class Main {
	public static class Pos {
		int x;
		int y;
		int count;
		int key;
		public Pos(int x, int y, int count, int key) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.key = key;
		}
	}

	static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] maze = new char[n][m];
		Pos start = new Pos(0, 0, 0, 0);
		for(int i = 0; i < n; i++) {
			char[] line = bf.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				maze[i][j] = line[j];
				if(maze[i][j] == '0') {
					start = new Pos(i, j, 0, 0);
					maze[i][j] = '.';
				}
			}
		}
		
		System.out.println(BFS(maze, start.x, start.y, start.count, start.key));
	}
	
	public static int BFS(char[][] maze, int x, int y, int count, int key) {
		Queue<Pos> queue = new LinkedList<>();
		boolean[][][] visit = new boolean[maze.length][maze[0].length][64];
		queue.add(new Pos(x, y, count, key));
		
		while(!queue.isEmpty()) {
			Pos tmp = queue.poll();
			//System.out.println("queue: " + tmp.x + " " + tmp.y + " " + tmp.count);
			for(int i = 0; i < 4; i++) {
				int mx = tmp.x + dir[i][0];
				int my = tmp.y + dir[i][1];
				if(mx < 0 || mx >= maze.length || my < 0 || my >= maze[0].length
						|| maze[mx][my] == '#' || visit[mx][my][tmp.key]) {
					continue;
				}
				//System.out.println("mx my: " + mx + " " + my);
				if(maze[mx][my] == '1') return tmp.count + 1;
				if(maze[mx][my] >= 'a' && maze[mx][my] <= 'f') {
					int tmpKey = tmp.key | (1 << (maze[mx][my] - 'a'));
					queue.add(new Pos(mx, my, tmp.count + 1, tmpKey));
					visit[mx][my][tmpKey] = true; 
				} else if(maze[mx][my] >= 'A' && maze[mx][my] <= 'F') {
					if((tmp.key & 1 << (maze[mx][my] - 'A')) != 0) {
						queue.add(new Pos(mx, my, tmp.count + 1, tmp.key));
						visit[mx][my][tmp.key] = true;;
					} 
				} else if(maze[mx][my] == '.') {
					queue.add(new Pos(mx, my, tmp.count + 1, tmp.key));
					visit[mx][my][tmp.key] = true; 
				}
			}
		}
		return -1;
	}
}