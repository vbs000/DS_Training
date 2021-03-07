package org.leetcode.double_pointer;

/**
 * 167.两数之和 II - 输入有序数组
 * 难度：简单
 * 算法：双指针
 */
public class Solution167 {
    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{2,7,11,15},9));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.87%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了13.31%的用户
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0,r = numbers.length-1,sum;
        while(l < r){
            sum = numbers[l] + numbers[r];
            if(sum == target) break;
            if(sum < target) ++l;
            else --r;
        }
        return new int[]{l+1,r+1};
    }

}
