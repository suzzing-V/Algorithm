import java.util.*;

class Solution {
    public class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(Object obj) { //equals 함수 재정의
		    if(obj instanceof Pos) { //비교하려는 객체가 Position일 경우
			    Pos tmp = (Pos) obj;
			    return (x == tmp.x) && (y == tmp.y); //내용물 같으면 같다고 판단
		    }
		    return false;
	    }

	    public int hashCode() {
			return Objects.hash(x, y); //x, y값 기반으로 해시코드 설정
	    }
    }
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] cBoard = new char[m][n];
        
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j++) {
                cBoard[i][j] = board[i].charAt(j);
            }
        }
        while(true) {
        Set<Pos> set = new HashSet<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char tmp = cBoard[i][j];
                if(tmp != 'O' && i + 1 < m && j + 1 < n 
                   && cBoard[i + 1][j] == tmp && cBoard[i + 1][j + 1] == tmp && cBoard[i][j + 1] == tmp) {
                    set.add(new Pos(i, j));
                    set.add(new Pos(i + 1, j));
                    set.add(new Pos(i + 1, j + 1));
                    set.add(new Pos(i, j + 1));
                }
            }
        }
        if(set.isEmpty()) break;
        answer += set.size();
        popBlock(set, cBoard);
        fallBlock(cBoard);
        }
        return answer;
    }
        
    public void popBlock(Set<Pos> set, char[][] cBoard) {
        for(Pos pos : set) {
            cBoard[pos.x][pos.y] = 'O';
        }
    }
    
    public void fallBlock(char[][] cBoard) {
        for(int j = 0; j < cBoard[0].length; j++) {
            int i = cBoard.length - 1;
            int start = -1;
            int end = -1;
            
            while(i >= 0) {
                if(start == -1 && cBoard[i][j] == 'O') {
                    start = i;
                }
                if(start != -1 && cBoard[i][j] != 'O') {
                    end = i;
                    i = pushBlock(end, start - end, j, cBoard);
                    start = -1;
                }
                i--;
            }
        }
    }
    
    public int pushBlock(int end, int dis, int column, char[][] cBoard) {
        int idx = 0;
        for(int i = end; i >= 0; i --) {
            char ch = cBoard[i][column];
            cBoard[i][column] = 'O';
            cBoard[i + dis][column] = ch;
            idx = i + dis;
        }
        
        return idx;
    }
}