package org.test.sw.adv;

import java.util.Scanner;

public class Solution_numSquare {
    private static int T;
    private static int N;
    private static int Answer;
    private static int[] resultArr;
    private static int cachedIdx;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        resultArr = new int[100001];
        resultArr[0] = 0;
        cachedIdx = 0;
        for(int i=1;i<=T;i++){
            N = sc.nextInt();
            Answer = numSquareDP(N);
            System.out.println("#"+i+" "+Answer);
        }
    }
    public static int numSquareDP(int n){
        if(n>cachedIdx){
            for(int i = cachedIdx+1;i <= n; ++i){
                resultArr[i] = i;//worst
                for(int j = 1;j*j <= i; ++j){
                    resultArr[i] = Math.min(resultArr[i],resultArr[i-j*j]+1);
                }
            }
            cachedIdx = n;
        }
        return resultArr[n];
    }

    public int numSquareMath(int n){
        if(isSquare(n)){
            return  1;
        }
        for(int i = 1; i*i <= n;++i){
            if(isSquare(n - i*i)){
                return 2;
            }
        }
        while(n % 4 == 0){
            n /= 4;
        }
        if(n % 8 != 7){
            return 3;
        }
        return 4;
    }
    private boolean isSquare(int n){
        int result = (int)Math.sqrt(n);
        return result*result == n;
    }
}
