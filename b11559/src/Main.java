import java.io.*;
import java.util.*;

public class Main {
	public static class Position {
		int x;
		int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof Position ) {
				Position tmp = (Position) obj;
				return (x == tmp.x) && (y == tmp.y); 
			}
			return false;
		}
		
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[][] field = new String[12][6];
		for(int i = 0; i < 12; i++) {
			String[] line = new String[6];
			line = bf.readLine().split("");
			for(int j = 0; j < 6; j++) {
				field[i][j] = line[j];
			}
		}

		int i = 11;
		int j = 0;
		int count = 0;
		while(j != 6) {
			while(j != 6) {
				if(!field[i][j].equals(".")) {
					HashSet<Position> set = new HashSet<>();
					countPuyo(i, j, field, field[i][j], set);
					//System.out.println("끝: " + set.size());
					if(set.size() >= 4) {
						//System.out.println("4 이상: " + field[i][j]);
						popPuyo(set, field);
						count ++;
						i = 11;
						j = 0;
						break;
					}
					//System.out.println("4 안됨: " + field[i][j]);
					i--;
					if(i < 0) {
						i = 11;
						j ++;
					}
				} else {
					j++;
					i = 11;
				}
			}
		}
		/*
		for(int x = 0; x < 12; x++) {
			for(int y = 0; y < 6; y++) {
				System.out.print(field[x][y]);
			}
			System.out.println();
		}
		*/
		System.out.println(count);
	}
	
	public static void countPuyo(int i, int j, String[][] field, String word, Set<Position> set) {
		if(i - 1 < 0 && j + 1 > 5 && i + 1 >= 12 && j - 1 < 0) {
			set.add(new Position(i, j));
			return;
		}
		
		if(i - 1 >= 0 && j + 1 < 6 && i + 1 < 12 && j - 1 > 0) {
			if(!field[i - 1][j].equals(word) && !field[i][j + 1].equals(word)
					&& !field[i + 1][j].equals(word) && !field[i][j - 1].equals(word)) {
				set.add(new Position(i, j));
				return;
			}
		}
		
		if(set.contains(new Position(i, j))) return;
		
		set.add(new Position(i, j));
		//System.out.println("i j: " + i + " " + j);
		if(i - 1 >= 0 && field[i - 1][j].equals(word)) {
			//System.out.println("위에있음");
			countPuyo(i - 1, j, field, word, set);
		}
		
		if(j + 1 <= 5 && field[i][j + 1].equals(word)) {
			//System.out.println("오른쪽에 있음");
			countPuyo(i, j + 1, field, word, set);
		}
		
		if(i + 1 < 12 && field[i + 1][j].equals(word)) {
			countPuyo(i + 1, j, field, word, set);
		}
		
		if(j - 1 >= 0 && field[i][j - 1].equals(word)) {
			countPuyo(i, j - 1, field, word, set);
		}
	}
	
	public static void popPuyo(Set<Position> set, String[][] field) {
		Iterator<Position> iterator = set.iterator();
		while(iterator.hasNext()) {
			Position tmp = iterator.next();
			//System.out.println("x, y: " + tmp.x + " " + tmp.y);
			field[tmp.x][tmp.y] = "."; 
		}
		//System.out.println("end");
		pushPuyo(field);
	}
	
	public static void pushPuyo(String[][] field) {
		for(int i = 0; i < 6; i++) {
			for(int j = 11; j >= 0; j--) {
				if(!field[j][i].equals(".")) {
					String tmp = field[j][i];
					field[j][i] = ".";
					int y = i;
					int x = j + 1;
					while(x < 12 && field[x][y].equals(".")) {
						x++;
					}
					field[x - 1][y] = tmp;
				}
			}
		}
	}
}