import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String[] wallpaper = {"#....", ".#...", "..#.."};
        int[] answer = solution(wallpaper);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(String[] wallpaper) {
        int w = wallpaper[0].length();
        int l = wallpaper.length;
        int firstRow = 0, firstCol = 0, lastRow = 0, lastCol = 0;

        for(int i = 0; i < l; i++) {
            if(wallpaper[i].indexOf("#") != -1) {
                firstRow = i;
                break;
            }
        }

        loopOut:
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < l; j++) {
                if(Character.compare(wallpaper[j].charAt(i), '#') == 0) {
                    firstCol = i;
                    break loopOut;
                }
            }
        }

        for(int i = l - 1; i >= 0; i--) {
            if(wallpaper[i].indexOf("#") != -1) {
                lastRow = i;
                break;
            }
        }

        loopOut:
        for(int i = w - 1; i >= 0; i--) {
            for(int j = l - 1; j >= 0; j--) {
                if(Character.compare(wallpaper[j].charAt(i), '#') == 0) {
                    lastCol = i;
                    break loopOut;
                }
            }
        }
        int[] answer = { firstRow, firstCol, lastRow, lastCol };
        return answer;
    }
}
