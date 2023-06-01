import java.io.*;

public class Main {
	
	public static class Position {
		int x;
		int y;
		char direction;
		int minY = 0;
		int maxY = 0;
		int minX = 0;
		int maxX = 0;
		Position(int x, int y, char direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
		int n = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < n; i++) {
			Position pos = new Position(0, 0, 'N');
			String line = bf.readLine();
			for(int j = 0; j < line.length(); j++) {
				moveTurtle(pos, line.charAt(j));
			}
			int rectangle = (pos.maxX - pos.minX) * (pos.maxY - pos.minY);
			System.out.println(rectangle);
		}
	}
	
	public static void moveTurtle(Position pos, char act) {
		if(act == 'F') {
			if(pos.direction == 'W') pos.x --;
			else if(pos.direction == 'E') pos.x ++;
			else if(pos.direction == 'S') pos.y --;
			else pos.y ++;
		} else if(act == 'L') {
			if(pos.direction == 'N') pos.direction = 'W';
			else if(pos.direction == 'S') pos.direction = 'E';
			else if(pos.direction == 'W') pos.direction = 'S';
			else pos.direction = 'N';
		} else if(act == 'R'){
			if(pos.direction == 'N') pos.direction = 'E';
			else if(pos.direction == 'S') pos.direction = 'W';
			else if(pos.direction == 'W') pos.direction = 'N';
			else pos.direction = 'S';
		} else {
			if(pos.direction == 'W') pos.x ++;
			else if(pos.direction == 'E') pos.x --;
			else if(pos.direction == 'S') pos.y ++;
			else pos.y --;
		}
		
		if(pos.x > pos.maxX) pos.maxX = pos.x;
		if(pos.x < pos.minX) pos.minX = pos.x;
		if(pos.y > pos.maxY) pos.maxY = pos.y;
		if(pos.y < pos.minY) pos.minY = pos.y;
	}
}