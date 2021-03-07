package org.leetcode.double_pointer;

/**
 * 76. 最小覆盖子串
 * 难度：困难
 * 算法：滑动窗口
 */
public class Solution_76_Hard {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
    }

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了76.91%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.06%的用户
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        //ASCII表
        int[] charCntArr = new int[128];
        boolean[] flagArr = new boolean[128];
        for(int i=0;i< t.length(); ++i){
            flagArr[t.charAt(i)] = true;//标记是否存在
            ++charCntArr[t.charAt(i)];//标记存在数量
        }
        int cnt = 0,l = 0, min_l = 0, min_size = s.length() +1;
        for(int r = 0;r<s.length();++r){
            if(flagArr[s.charAt(r)]){//如果存在
                if(--charCntArr[s.charAt(r)] >= 0){//可用，且还有数量可以用
                    ++cnt;//用掉总数
                }
                while(cnt == t.length()){//如果刚好已经满足了
                    if(r - l +1 < min_size){
                        min_l = l;
                        min_size = r - l + 1;
                    }
                    if(flagArr[s.charAt(l)] && ++charCntArr[s.charAt(l)] >0){
                        --cnt;//如果向右移动指针会去掉必要项目，则会跳出循环
                    }
                    ++l;//尝试将左指针向右移动
                }
            }
        }
        return min_size > s.length() ? "":s.substring(min_l,min_l+min_size);
    }
}
