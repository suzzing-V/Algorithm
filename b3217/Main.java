import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static Memory[] memory = new Memory[100001];
    static Map<String, Info> vals = new HashMap<>();

    private static class Memory {
        private boolean isAllocated;
        private int size;
        private int prev;

        private Memory(boolean isAllocated, int size, int prev) {
            this.isAllocated = isAllocated;
            this.size = size;
            this.prev = prev;
        }
    }

    private static class Info {
        private int size;
        private int add;

        Info(int size, int add) {
            this.size = size;
            this.add = add;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());

        memory[1] = new Memory(false, 100000, 0);
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            if(line.charAt(0) == 'f') {
                String val = line.substring(5, line.length() - 2);
                Info info = vals.get(val);
                if(info.add == 0) continue;

                memory[info.add].isAllocated = false;
                vals.put(val, new Info(0, 0));

                int prev = memory[info.add].prev;
                int next = info.add + memory[info.add].size;
                if(next <= 100000 && !memory[next].isAllocated) { // 다음 공간이 비어있을 때 합쳐줘야 함
                    memory[info.add].size += memory[next].size;

                    int next_next = next + memory[next].size;
                    memory[next].prev = 0;
                    memory[next].size = 0;

                    if(next_next <= 100000) {
                        memory[next_next].prev = info.add;
                    }
                }

                if(prev != 0 && !memory[prev].isAllocated) { // 이전 공간이 비어있을 때 합쳐줘야 함
                    memory[prev].size += memory[info.add].size;

                    memory[info.add].prev = 0;
                    memory[info.add].size = 0;
                    info.add = prev;

                    if(next <= 100000) {
                        memory[next].prev = info.add;
                    }
                }
            } else if(line.charAt(0) == 'p') {
                String val = line.substring(6, line.length() - 2);
                Info info = vals.get(val);
                bw.write(info.add + "\n");
            } else {
                String val = line.substring(0, 4);
                int size = Integer.parseInt(line.substring(12, line.length() - 2));
                int var_add = 0;

                for(int j = 1; j <= 100000; j += memory[j].size) {
                    if(!memory[j].isAllocated && memory[j].size >= size) {
                        var_add = j;
                        memory[var_add].isAllocated = true;

                        int next = j + memory[j].size;
                        int memory_leftover = memory[j].size - size;
                        if (memory_leftover > 0) {
                            memory[var_add].size = size;

                            int leftover_segment = var_add + size;
                            memory[leftover_segment] = new Memory(false, memory_leftover, var_add);
                            if (next <= 100000) {
                                memory[next].prev = leftover_segment;
                            }
                        }
                        break;
                    }
                }
                vals.put(val, new Info(size, var_add));
            }
        }
        bw.close();
    }
}
