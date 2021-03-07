package org.leetcode.greedy;

import java.util.Arrays;

/**
 * 135.分发糖果
 * 难度：困难
 * 算法：贪心（两侧分别遍历一遍）
 */
public class Solution_135_Hard {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,2,87,87,87,2,1}));
    }
    /*
     * 执行用时：3 ms, 在所有 Java 提交中击败了69.04%的用户
     * 39.4 MB, 在所有 Java 提交中击败了69.54%的用户
     */
    public static int candy(int[] ratings) {

        if(ratings == null || ratings.length==0){
            return 0;
        }
        int ratings_lens = ratings.length;
        if(ratings_lens<2){
            return ratings_lens;
        }
        int answer = ratings_lens;
        int[] plan = new int[ratings_lens];
        Arrays.fill(plan,1);
        for(int i=0;i<ratings_lens-1;i++){
            if(ratings[i+1]>ratings[i]){
                plan[i+1] = plan[i]+1;
                answer+=plan[i+1]-1;
            }
        }
        for(int i=ratings_lens-1;i>=1;i--){
            if(ratings[i-1]>ratings[i] && plan[i-1]<=plan[i]){
                answer+=plan[i]-plan[i-1]+1;
                plan[i-1] = plan[i]+1;

            }
        }
        return answer;
    }

}
