package org.test.practice.p0;


import org.test.org.practice.diy_utils.LinkedList;

public class LinkedList_Palindrome {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello Algorithm!");
        LinkedList<String> lkList = new LinkedList<>();
        lkList.add("A");
        lkList.add("B");
        lkList.add("C");
        lkList.add("C");
        lkList.add("B");
        lkList.add("A");
        //lkList.add(null);

        lkList.insert(4,"X");
        lkList.delete(4);

        System.out.println("lkList size:"+lkList.size);
        lkList.printAll();

        System.out.println(lkList.isPalindrome(lkList.head));

        System.out.println(""+System.currentTimeMillis()/1000);


    }


}
