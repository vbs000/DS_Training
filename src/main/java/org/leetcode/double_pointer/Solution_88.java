package org.leetcode.double_pointer;

/**
 * 88.合并两个有序数组
 * 难度：简单
 * 算法：三指针
 */
public class Solution_88 {
    public static void main(String[] args) {
        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了85.55%的用户
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos = m-- + n-- -1;
        while(m >= 0 && n>= 0){
            nums1[pos--] = nums1[m] > nums2[n] ? nums1[m--]: nums2[n--];
        }
        while(n >= 0){
            nums1[pos--] = nums2[n--];
        }
    }
}
