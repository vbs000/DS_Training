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
        int[] charCntArr = new int[128];
        boolean[] flagArr = new boolean[128];
        for(int i=0;i< t.length(); ++i){
            flagArr[t.charAt(i)] = true;
            ++charCntArr[t.charAt(i)];
        }
        int cnt = 0,l = 0, min_l = 0, min_size = s.length() +1;
        for(int r = 0;r<s.length();++r){
            if(flagArr[s.charAt(r)]){
                if(--charCntArr[s.charAt(r)] >= 0){
                    ++cnt;
                }
                while(cnt == t.length()){
                    if(r - l +1 < min_size){
                        min_l = l;
                        min_size = r - l + 1;
                    }
                    if(flagArr[s.charAt(l)] && ++charCntArr[s.charAt(l)] >0){
                        --cnt;
                    }
                    ++l;
                }
            }
        }
        return min_size > s.length() ? "":s.substring(min_l,min_l+min_size);
    }
}
