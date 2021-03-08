package org.leetcode.binary_search;

public class Solution_69 {
    public static void main(String[] args) {

    }
    public static int mySqrt(int x) {
        if(x == 0) return x;
        int l = 1, r = x, mid, sqrt;
        while(l <= r){
            mid = l + (r - l) / 2;
            sqrt = x / mid;
            if(sqrt == mid){
                return mid;
            }else if(mid > sqrt){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return r;
    }
}
