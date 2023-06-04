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
		//System.out.println("파괴: " + x + " " + i);
		searchMineral(field, x, i);
	}

	public static void searchMineral(String[][] field, int x, int y) {
		int[][] visit = new int[field.length][field[0].length];
		List<Pos> cluster = new ArrayList<>();
		
		for(int i = 0; i < field[0].length; i++) {
			if(field[field.length - 1][i].equals("x") && visit[field.length - 1][i] == 0) {
				BFS(visit, field, field.length - 1, i);
			}
		}
		findCluster(cluster, visit, field);
		fallCluster(cluster, field);
		return;
	}
	
	public static void BFS(int[][] visit, String[][] field, int x, int y) {
		Queue<Pos> queue = new LinkedList<>();
		//System.out.println("x y: " + x + " " + y);
		queue.add(new Pos(x, y));
		
		while(!queue.isEmpty()) {
			Pos tmp = queue.remove();
			
			if(tmp.x + 1 < field.length && field[tmp.x + 1][tmp.y].equals("x")
					&& visit[tmp.x + 1][tmp.y] == 0) {
				visit[tmp.x + 1][tmp.y] = 1; 
				queue.add(new Pos(tmp.x + 1, tmp.y));
			}
			if(tmp.x - 1 >= 0 && field[tmp.x - 1][tmp.y].equals("x")
					&& visit[tmp.x - 1][tmp.y] == 0) {
				visit[tmp.x - 1][tmp.y] = 1; 
				queue.add(new Pos(tmp.x - 1, tmp.y));
			}
			if(tmp.y + 1 < field[0].length && field[tmp.x][tmp.y + 1].equals("x")
					&& visit[tmp.x][tmp.y + 1] == 0) {
				visit[tmp.x][tmp.y + 1] = 1; 
				queue.add(new Pos(tmp.x, tmp.y + 1));
			}
			if(tmp.y - 1 >= 0 && field[tmp.x][tmp.y - 1].equals("x")
					&& visit[tmp.x][tmp.y - 1] == 0) {
				visit[tmp.x][tmp.y - 1] = 1; 
				queue.add(new Pos(tmp.x, tmp.y - 1));
			}
		}
		/*
		for(int i = 0; i < visit.length; i++) {
			for(int j = 0; j < visit[0].length; j++) {
				System.out.print(visit[i][j]);
			}
			System.out.println();
		}
		*/
	}

	
	public static void findCluster(List<Pos> cluster, int[][] visit, String[][] field) {
		int r = field.length;
		int c = field[0].length;
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(visit[i][j] == 0 && field[i][j].equals("x")) {
					//System.out.println("i j; " + i + " " + j);
					cluster.add(new Pos(i, j));
				}
			}
		}
	}
	
	public static void fallCluster(List<Pos> cluster, String[][] field) {
		int min = 101;
		for(Pos pos : cluster) {
			int dis = getDistance(field, pos.x, pos.y, cluster);
			if(dis < min) min = dis;
		}
		
		for(int i = field.length - 2; i >= 0; i--) {
			for(int j = 0; j < field[0].length; j++) {
				if(field[i][j].equals("x") && cluster.contains(new Pos(i, j))) {
					field[i][j] = ".";
					field[i + min][j] = "x";
				}
			}
		}
	}
	
	public static int getDistance(String[][] field, int x, int y, List<Pos> cluster) {
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