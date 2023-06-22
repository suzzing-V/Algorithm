import java.util.*;

class Solution {
    int result = 1;
    public int solution(int[] arr) {
        LCM(arr, getMin(arr));
        return result;
    }
    
    public void LCM(int[] arr, int min) {
        for(int i = 2; i <= min; i++) {
            boolean flag = false;
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] % i != 0) {
                    flag = true;
                    break;
                }
            }
            
            if(!flag) {
                result *= i;
                int[] tmp = divideArr(arr, i);
                LCM(tmp, getMin(tmp));
                return;
            }
        }
        
        for(int i = 0; i < arr.length; i++) {
            result *= arr[i];
        }
    }
    
    public int getMin(int[] arr) {
        Arrays.sort(arr);
        return arr[0];
    }
    
    public int[] divideArr(int[] arr, int i) {
        int[] tmp = new int[arr.length];
        for(int j = 0; j < arr.length; j++) {
            tmp[j] = arr[j] / i;
        }
        return tmp;
    }
}