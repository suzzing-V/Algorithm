import java.io.*;
import java.util.*;

public class Main {
	public static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static List<Integer> routine = new ArrayList<>();
	static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static Set<Integer> key = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] maze = new char[n][m];
		Pos start = new Pos(0, 0);
		for(int i = 0; i < n; i++) {
			char[] line = bf.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				maze[i][j] = line[j];
				if(maze[i][j] == '0') {
					start = new Pos(i, j);
					maze[i][j] = '.';
				}
			}
		}
		
		fourDFS(maze, start.x, start.y, 0);
		if(routine.isEmpty()) System.out.println("-1");
		else {
			Collections.sort(routine);
			System.out.println(routine.get(0));
		}
	}
	
	public static void fourDFS(char[][] maze, int x, int y, int count) {
		for(int i = 0; i < 4; i++) {
			int mx = x + dir[i][0];
			int my = y + dir[i][1];
			count++;
			if(mx < 0 || mx >= maze.length || my < 0 || my >= maze[0].length || maze[mx][my] == '#') {
				count--;
				continue;
			}
			//System.out.println("mx my: " + mx + " " + my);
			if(maze[mx][my] == '.') {
				threeDFS(maze, mx, my, i, count);
			} else if(maze[mx][my] >= 'a' && maze[mx][my] <= 'f') {
				//System.out.println(maze[mx][my] + "key 획득");
				char tmp = maze[mx][my];
				key.add(maze[mx][my] - 32);
				maze[mx][my] = '.';
				fourDFS(maze, mx, my, count);
				key.remove(tmp - 32);
				//System.out.println((tmp - 32) + "삭제");
				maze[mx][my] = tmp;
			} else if(maze[mx][my] >= 'A' && maze[mx][my] <= 'F') {
				if(key.contains((int)maze[mx][my])) {
					threeDFS(maze, mx, my, i, count);
				} else {
					count--;
					continue;
				}
			} else if(maze[mx][my] == '1') {
				//System.out.println("count: " + count);
				routine.add(count);
				return;
			}
			count --;
		}
	}
	
	public static void threeDFS(char[][] maze, int x, int y, int d, int count) {
		int nx = x - dir[d][0];
		int ny = y - dir[d][1];
		for(int i = 0; i < 4; i++) {
			int mx = x + dir[i][0];
			int my = y + dir[i][1];
			count++;
			if(mx < 0 || mx >= maze.length || my < 0 || my >= maze[0].length
					|| (mx == nx && my == ny) || maze[mx][my] == '#') {
				count--;
				continue;
			}
			//System.out.println("mx my: " + mx + " " + my);
			if(maze[mx][my] == '.') {
				threeDFS(maze, mx, my, i, count);
			} else if(maze[mx][my] >= 'a' && maze[mx][my] <= 'f') {
				//System.out.println(maze[mx][my] + "key 획득");
				key.add(maze[mx][my] - 32);
				char tmp = maze[mx][my];
				maze[mx][my] = '.';
				fourDFS(maze, mx, my, count);
				key.remove(tmp - 32);
				//System.out.println((tmp - 32) + "삭제");
				maze[mx][my] = tmp;
			} else if(maze[mx][my] >= 'A' && maze[mx][my] <= 'F') {
				if(key.contains((int)maze[mx][my])) {
					threeDFS(maze, mx, my, i, count);
				} else {
					count--;
					continue;
				}
			} else if(maze[mx][my] == '1') {
				//System.out.println("count: " + count);
				routine.add(count);
				return;
			}
			count --;
		}
	}
}