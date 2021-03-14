package org.leetcode.binary_search;

/**
 * 69. x 的平方根
 * 难度：简单
 * 方法：二分查找
 */
public class Solution_69 {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了46.99%的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了47.88%的用户
     * @param x
     * @return
     */
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
