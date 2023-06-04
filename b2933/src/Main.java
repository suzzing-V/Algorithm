package b2933.src;

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
			boolean up = findMinHeight(field, x - 1, y, cluster, set);
			if(up == true) {
				fallCluster(field, set, cluster);
				return;
			}
		}
		
		if(x + 1 < field.length && field[x + 1][y].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			boolean down = findMinHeight(field, x + 1, y, cluster, set);
			if(down == true) {
				fallCluster(field, set, cluster);
				return;
			}
		}

		if(y - 1 >= 0 && field[x][y - 1].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			//System.out.println("왼쪽");
			boolean left = findMinHeight(field, x, y - 1, cluster, set);
			//System.out.println("left: " + left);
			if(left == true) {
				fallCluster(field, set, cluster);
				return;
			}
		}

		if(y + 1 < field[0].length && field[x][y + 1].equals("x")) {
			Set<Pos> cluster = new HashSet<>();
			Set<Pos> set = new HashSet<>();
			boolean right = findMinHeight(field, x, y + 1,cluster, set);
			if(right == true) {
				fallCluster(field, set, cluster);
				return;
			}
		}

		return;
	}

	public static boolean findMinHeight(String[][] field, int x, int y, Set<Pos> cluster, Set<Pos> set) {
		if(cluster.contains(new Pos(x, y))) return true;
		if(x < 0 || x >= field.length || y < 0 || y >= field[0].length 
		|| field[x][y].equals(".")) return true;
		if(x == field.length - 1) {
			//System.out.println("땅닿음");
			return false;
		}

		if(x + 1 < field.length && field[x + 1][y].equals(".")) {
			set.add(new Pos(x, y));
		}

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
	
	public static void fallCluster(String[][] field, Set<Pos> set, Set<Pos> cluster) {
		int min = 101;
		Iterator<Pos> iter = set.iterator();
		while(iter.hasNext()) {
			Pos tmp = iter.next();
			int dis = getDistance(field, tmp.x, tmp.y, cluster);
			if(dis < min) min = dis;
		}
		
		Iterator<Pos> iter2 = set.iterator();
		while(iter2.hasNext()) {
			Pos tmp = iter2.next();
			int tmpX = tmp.x;
			while(tmpX >= 0 && field[tmpX][tmp.y].equals("x") && cluster.contains(new Pos(tmpX, tmp.y))) {
				field[tmpX][tmp.y] = ".";
				field[tmpX + min][tmp.y] = "x";
				tmpX--;
			}
		}
	}
	
	public static int getDistance(String[][] field, int x, int y, Set<Pos> cluster) {
		int a = x + 1;
		int b = y;
		while(a < field.length) {
			if(field[a][b].equals("x") && !cluster.contains(new Pos(a, b))) {
				break;
			}
			a++;
		}
		return a - x - 1;
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