package org.leetcode;
/**
 * 605. 种花问题
 * 难度：简单
 * 算法：贪心（理想间隔模型）
 */
public class Solution_605 {
    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1},1));
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1},2));
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,0,0,1},2));
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,0,1},2));
        System.out.println(canPlaceFlowers(new int[]{0},1));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了22.68%的用户
     * @param flowerbed
     * @param n
     * @return
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int fb_lens = flowerbed.length;
        if(n==0){
            return true;
        }else if(fb_lens==0 || Math.ceil((double)fb_lens/2)<n){
            return false;
        }
        boolean answer = false;
        int set_cnt = 0;
        for(int i=0;i<fb_lens;i++){
            if(flowerbed[i]==0 &&
                (i-1 == -1 || flowerbed[i-1]==0) &&
                (i+1 == fb_lens || flowerbed[i+1]==0)
            ){
                flowerbed[i] = 1;
                if(++set_cnt==n) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }
}
