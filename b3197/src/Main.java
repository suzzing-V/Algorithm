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
	
	static Queue<Pos> swan = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] field = new char[r][c];
		int[][] ice = new int[r][c];
		for(int i = 0; i < r; i++) {
			char[] line = bf.readLine().toCharArray();
			for(int j = 0; j < c; j++) {
				field[i][j] = line[j];
				if(field[i][j] == 'X') ice[i][j] = 1;
				if(field[i][j] == 'L') {
					if(swan.isEmpty()) {
						field[i][j] = '.';
						swan.add(new Pos(i, j));
					}
				}
			}
		}
		/*
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		/*
		for(Pos pos : swan) {
			System.out.println("x y: " + pos.x + " " + pos.y);
		}
		*/
		int day = 0;
		int[][] visit = new int[r][c];
		while(true) {
			if(moveSwan(field, visit)) break;
			/*
			for(Pos pos : swan) {
				System.out.println("x y: " + pos.x + " " + pos.y);
			}
			*/
			meltIce(field, ice, r, c);
			/*
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					System.out.print(field[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			*/
			day ++;
		}
		System.out.println(day);
	}
	
	public static void meltIce(char[][] field, int[][] ice, int r, int c) {
		List<Pos> melt = new ArrayList<>();
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(checkIce(ice, i, j) && field[i][j] == 'X') {
					field[i][j] = '.';
					melt.add(new Pos(i, j));
				}
			}
		}
		
		for(Pos pos : melt) {
			ice[pos.x][pos.y] = 0; 
		}
	}
	
	public static boolean checkIce(int[][] ice, int x, int y) {
		if(x - 1 >= 0 && ice[x - 1][y] == 0) return true;
		if(x + 1 < ice.length && ice[x + 1][y] == 0) return true;
		if(y - 1 >= 0 && ice[x][y - 1] == 0) return true;
		if(y + 1 < ice[0].length && ice[x][y + 1] == 0) return true;
		return false;
	}
	
	public static boolean moveSwan(char[][] field, int[][] visit) {
		Queue<Pos> tmpSwan = new ArrayDeque<>(swan);
		swan.clear();
		int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
		while(!tmpSwan.isEmpty()) {
			Pos tmp = tmpSwan.remove();
			for(int i = 0; i < 4; i++) {
				int mx = tmp.x + dir[i][0];
				int my = tmp.y + dir[i][1];
				//System.out.println("m: " + mx + " " + my);
				if(mx < 0 || mx >= field.length || my < 0 || my >= field[0].length || visit[mx][my] == 1) continue; 
				if(field[mx][my] == 'X') {
					visit[mx][my] = 1;
					swan.add(new Pos(mx, my));
				} else if(field[mx][my] == '.') {
					visit[mx][my] = 1;
					tmpSwan.add(new Pos(mx, my));
				} else if(field[mx][my] == 'L') {
					return true;
				}
			}
		}
		return false;
	}
}