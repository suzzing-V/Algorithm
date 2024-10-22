import java.io.*;
import java.util.*;

public class Main {
    static int max = 0;
    static int n;
    public static void  main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int[][] puzzle = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        left(0, puzzle, 0);
        right(0, puzzle, 0);
        up(0, puzzle, 0);
        down(0, puzzle, 0);
        System.out.println(max);
    }

    public static void up(int preBlockSize, int[][] puzzle, int count) {
        if(count == 5) {
            return;
        }
        int blockSize = 0;
        List<Integer> results = new ArrayList<>();
        int[][] newPuzzle = new int[n][n];
        for(int j = 0; j < n; j++) {
            int saved = 0;
            int savedX = 0;
            int idx = 0;
            int lineBlock = 0;
            List<Integer> lineResults = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if(puzzle[i][j] != 0) {
                    if(saved == 0) {
                        saved = puzzle[i][j];
                    } else if(saved == puzzle[i][j]) {
                        newPuzzle[idx ++][j] = saved * 2;
                        max = Math.max(saved * 2, max);
                        lineBlock ++;
                        saved = 0;
                    } else if(saved != puzzle[i][j]) {
                        newPuzzle[idx ++][j] = saved;
                        max = Math.max(saved, max);
                        lineBlock ++;
                        saved = puzzle[i][j];
                    }
                }
            }
            // 한 줄 다 돌면 남아있는 거 넣기
            if(saved != 0) {
                max = Math.max(saved, max);
                newPuzzle[idx][j] = saved;
                lineBlock ++;
            }

            // 블록크기 더하기(끝낼지 말지 여부 결정)
            blockSize += lineBlock;
        }

        if(preBlockSize == blockSize) {
            return;
        }

        up(blockSize, newPuzzle, count + 1);
        down(blockSize, newPuzzle, count + 1);
        left(blockSize, newPuzzle, count + 1);
        right(blockSize, newPuzzle, count + 1);
    }

    public static void down(int preBlockSize, int[][] puzzle, int count) {
        if(count == 5) {
            return;
        }
        int blockSize = 0;
        int[][] newPuzzle = new int[n][n];
        for(int j = 0; j < n; j++) {
            int saved = 0;
            int idx = n - 1;
            int lineBlock = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(puzzle[i][j] != 0) {
                    if(saved == 0) {
                        saved = puzzle[i][j];
                    } else if(saved == puzzle[i][j]) {
                        newPuzzle[idx --][j] = saved * 2;
                        max = Math.max(saved * 2, max);
                        lineBlock ++;
                        saved = 0;
                    } else if(saved != puzzle[i][j]) {
                        newPuzzle[idx --][j] = saved;
                        max = Math.max(saved, max);
                        lineBlock ++;
                        saved = puzzle[i][j];
                    }
                }
            }
            // 한 줄 다 돌면 남아있는 거 넣기
            if(saved != 0) {
                max = Math.max(saved, max);
                newPuzzle[idx][j] = saved;
                lineBlock ++;
            }

            // 블록크기 더하기(끝낼지 말지 여부 결정)
            blockSize += lineBlock;
        }

        if(preBlockSize == blockSize) {
            return;
        }

        up(blockSize, newPuzzle, count + 1);
        down(blockSize, newPuzzle, count + 1);
        left(blockSize, newPuzzle, count + 1);
        right(blockSize, newPuzzle, count + 1);
    }

    public static void left(int preBlockSize, int[][] puzzle, int count) {
        if(count == 5) {
            return;
        }
        int blockSize = 0;
        int[][] newPuzzle = new int[n][n];
        for(int i = 0; i < n; i++) {
            int saved = 0;
            int idx = 0;
            int lineBlock = 0;
            for(int j = 0; j < n; j++) {
                if(puzzle[i][j] != 0) {
                    if(saved == 0) {
                        saved = puzzle[i][j];
                    } else if(saved == puzzle[i][j]) {
                        newPuzzle[i][idx ++] = saved * 2;
                        max = Math.max(saved * 2, max);
                        lineBlock ++;
                        saved = 0;
                    } else if(saved != puzzle[i][j]) {
                        newPuzzle[i][idx ++] = saved;
                        max = Math.max(saved, max);
                        lineBlock ++;
                        saved = puzzle[i][j];
                    }
                }
            }
            // 한 줄 다 돌면 남아있는 거 넣기
            if(saved != 0) {
                max = Math.max(saved, max);
                newPuzzle[i][idx] = saved;
                lineBlock ++;
            }

            // 블록크기 더하기(끝낼지 말지 여부 결정)
            blockSize += lineBlock;
        }

        if(preBlockSize == blockSize) {
            return;
        }

        up(blockSize, newPuzzle, count + 1);
        down(blockSize, newPuzzle, count + 1);
        left(blockSize, newPuzzle, count + 1);
        right(blockSize, newPuzzle, count + 1);
    }

    public static void right(int preBlockSize, int[][] puzzle, int count) {
        if(count == 5) {
            return;
        }
        int blockSize = 0;
        int[][] newPuzzle = new int[n][n];
        for(int i = 0; i < n; i++) {
            int saved = 0;
            int idx = n - 1;
            int lineBlock = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(puzzle[i][j] != 0) {
                    if(saved == 0) {
                        saved = puzzle[i][j];
                    } else if(saved == puzzle[i][j]) {
                        newPuzzle[i][idx --] = saved * 2;
                        max = Math.max(saved * 2, max);
                        lineBlock ++;
                        saved = 0;
                    } else if(saved != puzzle[i][j]) {
                        newPuzzle[i][idx --] = saved;
                        max = Math.max(saved, max);
                        lineBlock ++;
                        saved = puzzle[i][j];
                    }
                }
            }
            // 한 줄 다 돌면 남아있는 거 넣기
            if(saved != 0) {
                max = Math.max(saved, max);
                newPuzzle[i][idx] = saved;
                lineBlock ++;
            }

            // 블록크기 더하기(끝낼지 말지 여부 결정)
            blockSize += lineBlock;
        }

        if(preBlockSize == blockSize) {
            return;
        }

        up(blockSize, newPuzzle, count + 1);
        down(blockSize, newPuzzle, count + 1);
        left(blockSize, newPuzzle, count + 1);
        right(blockSize, newPuzzle, count + 1);
    }
}
