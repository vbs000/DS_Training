package org.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 455.用最少数量的箭引爆气球
 * 难度：中等
 * 算法：贪心（区间结尾排序后，引爆有边界最靠左的）
 */
public class Solution_452_Medium {
    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
    }

    /**
     * 执行用时：19 ms, 在所有 Java 提交中击败了86.43%的用户
     * 内存消耗：46.2 MB, 在所有 Java 提交中击败了11.92%的用户
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if(points.length==0){
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return 0;
                } else {
                    return o1[1] > o2[1] ? 1 : -1;
                }
            }
        });
        int position = points[0][1];
        int answer = 1;
        for(int i=1;i<points.length;i++){
            if(points[i][0]>position){
                answer++;
                position = points[i][1];
            }
        }
        return answer;
    }
}
