import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] mnstr = new String[2];
        mnstr = bf.readLine().split(" ");

        int n = Integer.parseInt(mnstr[0]);
        int m = Integer.parseInt(mnstr[1]);
        String[][] chess = new String[n][m];
        for(int i = 0; i < n; i++) {
            String[] chessLine = new String[m];
            chessLine = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                chess[i][j] = chessLine[j];
            }
        }

        int count;
        int changeCount;
        int min = 64;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i + 7 < n && j + 7 < m) {
                count = 0;
                changeCount = 0;
                if(chess[i][j].equals("B")) {
                    for(int k = i; k < i + 8; k++) {
                        for(int l = j; l < j + 8; l++) {
                            if(((i + 7 - k) % 2 != 0 && (j + 7 - l) % 2 != 0)
                            || ((i + 7 - k) % 2 == 0 && (j + 7 - l) % 2 == 0)) {
                                if(chess[k][l].equals("W")) {
                                    count++;
                                }
                            } else {
                                if(chess[k][l].equals("B")) {
                                    count++;
                                }
                            }
                        }
                    }

                    for(int k = i; k < i + 8; k++) {
                        for(int l = j; l < j + 8; l++) {
                            if(((i + 7 - k) % 2 != 0 && (j + 7 - l) % 2 != 0)
                            || ((i + 7 - k) % 2 == 0 && (j + 7 - l) % 2 == 0)) {
                                if(chess[k][l].equals("B")) {
                                    changeCount++;
                                }
                            } else {
                                if(chess[k][l].equals("W")) {
                                    changeCount++;
                                }
                            }
                        }
                    }

                    if(changeCount < count) {
                        count = changeCount;
                    }
                } else {
                    for(int k = i; k < i + 8; k++) {
                        for(int l = j; l < j + 8; l++) {
                            if(((i + 7 - k) % 2 != 0 && (j + 7 - l) % 2 != 0)
                            || ((i + 7 - k) % 2 == 0 && (j + 7 - l) % 2 == 0)) {
                                if(chess[k][l].equals("B")) {
                                    count++;
                                }
                            } else {
                                if(chess[k][l].equals("W")) {
                                    count++;
                                }
                            }
                        }
                    }

                    for(int k = i; k < i + 8; k++) {
                        for(int l = j; l < j + 8; l++) {
                            if(((i + 7 - k) % 2 != 0 && (j + 7 - l) % 2 != 0)
                            || ((i + 7 - k) % 2 == 0 && (j + 7 - l) % 2 == 0)) {
                                if(chess[k][l].equals("W")) {
                                    changeCount++;
                                }
                            } else {
                                if(chess[k][l].equals("B")) {
                                    changeCount++;
                                }
                            }
                        }
                    }
                    if(changeCount < count) {
                        count = changeCount;
                    }
                }
                if(min > count) {
                    min = count;
                }
            }
            }
        }

        bw.write(Integer.toString(min));
        bw.close();
    }
}
