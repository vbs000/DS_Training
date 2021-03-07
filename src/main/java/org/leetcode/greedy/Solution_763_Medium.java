package org.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 * 难度：中等
 * 算法：贪心（最后一次出现的下标位置）
 */
public class Solution_763_Medium {
    public static void main(String[] args) {
        for(Integer ele:partitionLabels("ababcbacadefegdehijhklij")){
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了17.71%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了72.84%的用户
     * @param S
     * @return
     */
    public static List<Integer> partitionLabels(String S) {
        List<Integer> answer = new ArrayList<>();
        int i=0;
        while(i<S.length()){
            char c = S.charAt(i);
            int first = S.indexOf(c),last = S.lastIndexOf(c);
            for(int j = first +1;j<=last;j++){
                int mid_last = S.lastIndexOf(S.charAt(j));
                if(mid_last > last){
                    last = mid_last;
                }
            }
            answer.add(last-first+1);
            i = last + 1;
        }
        return answer;
    }

}
