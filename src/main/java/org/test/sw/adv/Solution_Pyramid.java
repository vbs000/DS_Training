package org.test.sw.adv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution_Pyramid {

    private static int myCase;
    private static String S;
    private static List<int[]> strategyList;

    private static int L = 8;
    private static int[][] relationArr = new int[2][L*(L+1)/2];

    public static void main(String[] args) {
        int tCount = 0;
        for(int i=1;i<=L;i++){
            for(int j=0;j<i;j++){
                System.out.println("i:"+i+",j:"+j);
                relationArr[0][tCount] = j+((i+1)*i)/2;
                relationArr[1][tCount] = relationArr[0][tCount]+1;
                tCount++;
                System.out.println(">>"+relationArr[0][i-1]+","+relationArr[1][i-1]);
            }
        }
        for(int i=0;i<relationArr[0].length-8;i++){
            System.out.println( relationArr[0][i]+","+ relationArr[1][i]);
        }

        Scanner sc = new Scanner(System.in);
        myCase = sc.nextInt();
        for(int T=1;T<=myCase;T++){
            S = sc.next();
//            int arr[] = {1, 2, 3, 4, 5};
//            int r = 3;
//            int n = arr.length;
//            int data[]=new int[r];
//            strategyList = new ArrayList<>();
//            comb(arr, data, 0, n-1, 0, r);
//            System.out.println(strategyList);

        }
    }
    static void comb(int arr[], int data[], int start, int end, int index, int r) {
        if (index == r) {
            for (int j=0; j<r; j++)
                strategyList.add(data);
            return;
        }
        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            data[index] = arr[i];
            comb(arr, data, i+1, end, index+1, r);
        }
    }

}
