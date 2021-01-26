package org.leetcode;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * 难度：简单
 * 算法：贪心
 */
public class Solution_455 {

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{10,9,8,7},new int[]{5,6,7,8}));
        System.out.println(findContentChildren(new int[]{1,2},new int[]{1,2,3}));
    }
    /*
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.94%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了34.15%的用户
     */
    public static int findContentChildren(int[] g, int[] s) {
        if(g.length == 0 || s.length == 0){
            return 0;
        }
        int answer = 0;
        int g_len = g.length;
        int s_len = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int cursor_g = 0;
        int cursor_s = 0;
        while(true){
            if(s[cursor_s++]>=g[cursor_g]){
                answer++;
                cursor_g++;
            }
            if(cursor_s>=s_len || cursor_g>=g_len){
                break;
            }
        }
        return answer;
    }
}
