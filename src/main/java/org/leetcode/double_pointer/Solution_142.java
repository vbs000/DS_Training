package org.leetcode.double_pointer;

import java.util.List;

/**
 * 142. 环形链表 II
 * 难度：中等
 * 算法：同向快慢指针，两次相遇
 */
public class Solution_142 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n1.next.next = new ListNode(0);
        n1.next.next.next = new ListNode(-4);
        n1.next.next.next.next = n2;
        System.out.println(detectCycle(n1).val);
    }
    static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了51.72%的用户
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        do{
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }while(fast != slow);
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
