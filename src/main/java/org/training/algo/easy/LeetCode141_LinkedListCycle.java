package org.training.algo.easy;

import java.util.HashSet;
import java.util.Set;

public class LeetCode141_LinkedListCycle {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    public boolean hasCycleWithHashSet(ListNode head){
        Set<ListNode> set = new HashSet<>();
        for(ListNode p = head;p != null;p = p.next){
            if(set.contains(p)){
                return true;
            }else{
                set.add(p);
            }
        }
        return false;
    }

    public boolean hasCycleWithTwoPointer(ListNode head){
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
    public void test1(){
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);
        ListNode ln6 = new ListNode(2);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln6;
        System.out.println("TEST1:");
        System.out.println(hasCycleWithHashSet(ln1));
        System.out.println(hasCycleWithTwoPointer(ln1));
        ln6.next = ln2;
        System.out.println(hasCycleWithHashSet(ln1));
        System.out.println(hasCycleWithTwoPointer(ln1));
    }

    public void test2(){
        int[] nodeArr = new int[]{1,2,3,4,5,6,7,8,9,10,1,2,3,4};
        ListNode head = null;
        ListNode cursor = null;
        for (int value : nodeArr) {
            if (head == null) {
                head = new ListNode(value);
                cursor = head;
            } else {
                cursor.next = new ListNode(value);
                cursor = cursor.next;
            }
        }
        System.out.println("TEST2:");
        System.out.println(hasCycleWithHashSet(head));
        System.out.println(hasCycleWithTwoPointer(head));
        cursor.next = head;
        System.out.println(hasCycleWithHashSet(head));
        System.out.println(hasCycleWithTwoPointer(head));

    }
    public static void main(String[] args) {
        LeetCode141_LinkedListCycle main = new LeetCode141_LinkedListCycle();
        main.test1();
        main.test2();
    }
}
