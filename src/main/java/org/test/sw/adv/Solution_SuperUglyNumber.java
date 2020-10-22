package org.test.sw.adv;

import java.math.BigInteger;

public class Solution_SuperUglyNumber {
    public static void main(String[] args) {

        System.out.println(nthSuperUglyNumber(12,new int[]{2, 7, 13, 19}));
    }
    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0 || primes == null || primes.length == 0) {
            return -1;
        }
        int primeSize = primes.length;
        BigInteger[] uglyNums = new BigInteger[n];
        uglyNums[0] = new BigInteger("1");
        int[] indexArr = new int[primeSize];
        for (int i = 1; i < n; ++i) {
            BigInteger cur_min = null;
            for (int x = 0; x < primeSize; x++) {
                BigInteger temp_min = uglyNums[indexArr[x]].multiply(new BigInteger(String.valueOf(primes[x])));
                if (cur_min == null || cur_min.compareTo(temp_min) >= 0) {
                    cur_min = temp_min;
                }
            }
            uglyNums[i] = cur_min;
            for (int x = 0; x < primeSize; x++) {
                if (cur_min.equals(uglyNums[indexArr[x]].multiply(BigInteger.valueOf(primes[x])))) {
                    indexArr[x]++;
                }
            }

        }
        return uglyNums[n-1].intValue();
    }



}
