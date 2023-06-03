package b2933.src;

import java.io.*;
import java.util.*;

public class Main {
	static int min = 101;
	public static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object obj) {
			if(obj instanceof Pos) {
				Pos tmp = (Pos) obj;
				return tmp.x == x && tmp.y == y;
			}
			return false;
		}

		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = new String[2];
		rc = bf.readLine().split(" ");
		int r = Integer.parseInt(rc[0]);
		int c = Integer.parseInt(rc[1]);
		
		String[][] field = new String[r][c];
		for(int i = 0; i < r; i++) {
			String[] line = new String[c];
			line = bf.readLine().split("");
			for(int j = 0; j < c; j++) {
				field[i][j] = line[j];
			}
		}
		
		int n = Integer.parseInt(bf.readLine());
		String[] height = new String[n];
		height = bf.readLine().split(" ");
		
		for(int i = 0; i < n; i++) {
			throwStick(field, r - Integer.parseInt(height[i]), i + 1);
			//printField(field);
		}
		
		printField(field);
	}
	
	public static void throwStick(String[][] field, int x, int order) {
		int i = 0;
		if(order % 2 == 1) {
			while(i < field[0].length && field[x][i].equals(".")) i++;
			if(i == field[0].length) return;
			field[x][i] = ".";
		} else {
			i = field[0].length - 1;
			while(i >= 0 && field[x][i].equals(".")) i--;
			if(i < 0) return;
			field[x][i] = ".";
		}
		searchMineral(field, x, i);
	}

	public static void searchMineral(String[][] field, int x, int y) {
		if(x - 1 >= 0 && field[x - 1][y].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			min = 101;
			boolean up = findMinHeight(field, x - 1, y, cluster, set);
			if(up == true) {
				fallCluster(field, min, set);
				return;
			}
		}

		if(y - 1 >= 0 && field[x][y - 1].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			min = 101;
			boolean left = findMinHeight(field, x, y - 1, cluster, set);
			//System.out.println("left: " + left);
			if(left == true) {
				fallCluster(field, min, set);
				return;
			}
		}

		if(y + 1 < field[0].length && field[x][y + 1].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			min = 101;
			boolean right = findMinHeight(field, x, y + 1,cluster, set);
			if(right == true) {
				fallCluster(field, min, set);
				return;
			}
		}

		return;
	}

	public static boolean findMinHeight(String[][] field, int x, int y, Set<Pos> cluster, Set<Pos> set) {
		if(cluster.contains(new Pos(x, y))) return true;
		if(x < 0 || x >= field.length || y < 0 || y >= field[0].length 
		|| field[x][y].equals(".")) return true;
		if(x == field.length - 1) return false;

		if(x + 1 < field.length && field[x + 1][y].equals(".")) {
			set.add(new Pos(x, y));
			int tmp = getDistance(field, x, y);
			if(tmp < min) min = tmp;
		}

		//System.out.println("x y min: " + x + " " + y + " " + min);
		cluster.add(new Pos(x, y));
		if(x - 1 >= 0 && field[x - 1][y].equals("x")) {
			if(findMinHeight(field, x - 1, y, cluster, set) == false) return false;
		}
		
		if(x + 1 < field.length && field[x + 1][y].equals("x")) {
			if(findMinHeight(field, x + 1, y, cluster, set) == false) return false;
		}
		
		if(y - 1 >= 0 && field[x][y - 1].equals("x")) {
			if(findMinHeight(field, x, y - 1, cluster, set) == false) return false;
		}

		if(y + 1 < field[0].length && field[x][y + 1].equals("x")) {
			if(findMinHeight(field, x, y + 1, cluster, set) == false) return false;
		}
		return true;
	}
	
	public static void fallCluster(String[][] field, int min, Set<Pos> set) {
		Iterator<Pos> iter = set.iterator();
		while(iter.hasNext()) {
			Pos tmp = iter.next();
			int tmpX = tmp.x;
			while(tmpX >= 0 && field[tmpX][tmp.y].equals("x")) {
				field[tmpX][tmp.y] = ".";
				field[tmpX + min][tmp.y] = "x";
				tmpX--;
			}
		}
	}
	
	public static int getDistance(String[][] field, int x, int y) {
		int a = x + 1;
		int b = y;
		int save = x;
		//System.out.println("a b: " + a + " " + b);
		while(true) {
			while(field[a][b].equals(".")) {
				a++;
				if(a >= field.length) {
					return field.length - 1 - x;
				}
			}
			
			save = a;
			//System.out.println(a + " 저장");
			while(field[a][b].equals("x")) {
				a ++;
				if(a >= field.length) {
					return save - x - 1;
				}
			}
		}
	}
	public static void printField(String[][] field) {
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[0].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}
}