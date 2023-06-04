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
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] field = new char[r][c];
		
		for(int i = 0; i < r; i++) {
			char[] line = bf.readLine().toCharArray();
			for(int j = 0; j < c; j++) {
				field[i][j] = line[j];
			}
		}
		
		int n = Integer.parseInt(bf.readLine());
		int[] height = new int[n];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			throwStick(field, r - height[i], i + 1);
			//printField(field);
		}
		
		printField(field);
	}
	
	public static void throwStick(char[][] field, int x, int order) {
		int i = 0;
		if(order % 2 == 1) {
			while(i < field[0].length && field[x][i] == '.') i++;
			if(i == field[0].length) return;
			field[x][i] = '.';
		} else {
			i = field[0].length - 1;
			while(i >= 0 && field[x][i] == '.') i--;
			if(i < 0) return;
			field[x][i] = '.';
		}
		searchMineral(field, x, i);
	}

	public static void searchMineral(char[][] field, int x, int y) {
		if(x - 1 >= 0 && field[x - 1][y] == 'x') {
			List<Pos> cluster = new ArrayList<>();
			boolean up = findMinHeight(field, x - 1, y, cluster);
			if(up == true) {
				fallCluster(field, cluster);
				return;
			}
		}
		
		if(x + 1 < field.length && field[x + 1][y] == 'x') {
			List<Pos> cluster = new ArrayList<>();
			boolean down = findMinHeight(field, x + 1, y, cluster);
			if(down == true) {
				fallCluster(field, cluster);
				return;
			}
		}

		if(y - 1 >= 0 && field[x][y - 1] == 'x') {
			List<Pos> cluster = new ArrayList<>();
			//System.out.println("왼쪽");
			boolean left = findMinHeight(field, x, y - 1, cluster);
			//System.out.println("left: " + left);
			if(left == true) {
				fallCluster(field, cluster);
				return;
			}
		}

		if(y + 1 < field[0].length && field[x][y + 1] == 'x') {
			List<Pos> cluster = new ArrayList<>();
			boolean right = findMinHeight(field, x, y + 1,cluster);
			if(right == true) {
				fallCluster(field, cluster);
				return;
			}
		}

		return;
	}

	public static boolean findMinHeight(char[][] field, int x, int y, List<Pos> cluster) {
		if(cluster.contains(new Pos(x, y))) return true;
		if(x < 0 || x >= field.length || y < 0 || y >= field[0].length 
		|| field[x][y] == '.') return true;
		if(x == field.length - 1) {
			//System.out.println("땅닿음");
			return false;
		}

		cluster.add(new Pos(x, y));
		if(x - 1 >= 0 && field[x - 1][y] == 'x') {
			if(findMinHeight(field, x - 1, y, cluster) == false) return false;
		}
		
		if(x + 1 < field.length && field[x + 1][y] == 'x') {
			if(findMinHeight(field, x + 1, y, cluster) == false) return false;
		}
		
		if(y - 1 >= 0 && field[x][y - 1] == 'x') {
			if(findMinHeight(field, x, y - 1, cluster) == false) return false;
		}

		if(y + 1 < field[0].length && field[x][y + 1] == 'x') {
			if(findMinHeight(field, x, y + 1, cluster) == false) return false;
		}
		return true;
	}
	
	public static void fallCluster(char[][] field, List<Pos> cluster) {
		List<Pos> bottom = new ArrayList<>();
		Collections.sort(cluster, new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				if(o1.y == o2.y) return o2.x - o1.x;
				return o1.y - o2.y;
			}
		});
		
		bottom.add(new Pos(cluster.get(0).x, cluster.get(0).y));
		int cur = cluster.get(0).y;
		for(int i = 1; i < cluster.size(); i++) {
			if(cluster.get(i).y != cur) {
				bottom.add(new Pos(cluster.get(i).x, cluster.get(i).y));
				cur = cluster.get(i).y;
			}
		}
		
		int min = 101;
		for(int i = 0; i < bottom.size(); i++) {
			int dis = getDistance(field, bottom.get(i).x, bottom.get(i).y);
			if(dis < min) min = dis;
		}
		
		for(int i = 0; i < bottom.size(); i++) {
			int tmpX = bottom.get(i).x;
			int tmpY = bottom.get(i).y;
			while(tmpX >= 0) {
				if(field[tmpX][tmpY] == 'x' && cluster.contains(new Pos(tmpX, tmpY))) {
					field[tmpX][tmpY] = '.';
					field[tmpX + min][tmpY] = 'x';
				}
				tmpX--;
			}
		}
	}
	
	public static int getDistance(char[][] field, int x, int y) {
		int a = x + 1;
		int b = y;
		while(a < field.length) {
			if(field[a][b] == 'x') {
				break;
			}
			a++;
		}
		return a - x - 1;
	}
	
	public static void printField(char[][] field) {
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[0].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}
}