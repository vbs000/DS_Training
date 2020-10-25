package org.test.sw.adv;

import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_SuperUglyNumber {
    public static void main(String[] args) {

        System.out.println(nthSuperUglyNumber(6,new int[]{3,5}));
    }
    public static String nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0 || primes == null || primes.length == 0) {
            return null;
        }
        int primeSize = primes.length;
        BigInteger[] uglyNums = new BigInteger[n];
        uglyNums[0] = new BigInteger("1");
        int[] cursorArr = new int[primeSize];
        for (int i = 1; i < n; ++i) {
            BigInteger cur_min = null;
            for (int x = 0; x < primeSize; x++) {
                BigInteger temp_min = uglyNums[cursorArr[x]].multiply(new BigInteger(String.valueOf(primes[x])));
                if (cur_min == null || cur_min.compareTo(temp_min) >= 0) {
                    cur_min = temp_min;
                }
            }
            uglyNums[i] = cur_min;
            for (int x = 0; x < primeSize; x++) {
                if (cur_min.equals(uglyNums[cursorArr[x]].multiply(BigInteger.valueOf(primes[x])))) {
                    cursorArr[x]++;
                }
            }

        }
        return uglyNums[n-1].toString();
    }

    public int nthSuperUglyNumber2(int n, int[] primes) {
        // write your code here
        int[] cursorArr = new int[primes.length];
        int[] uglyNums = new int[n];
        uglyNums[0] = 1; // first is 1

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * uglyNums[cursorArr[j]]);
            }

            uglyNums[i] = min;

            for (int j = 0; j < cursorArr.length; j++) {
                if (uglyNums[cursorArr[j]] * primes[j] == min) {
                    cursorArr[j]++;
                }
            }
        }

        return uglyNums[n - 1];
    }

    public int nthSuperUglyNumber3(int n, int[] primes) {
        // write your code here
       Integer uglyNum = -1;
        Queue<Integer> q = new PriorityQueue<>();
        q.add(1);
        while(n>0){
            while(q.peek() == uglyNum){
                q.poll();
            }
            for(int i=0;i<primes.length;i++){
                if(primes[i]*uglyNum <= Integer.MAX_VALUE){
                    q.add(primes[i]*uglyNum);
                }
            }
            --n;
        }
        return uglyNum;
    }



}
