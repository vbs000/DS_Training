package org.test.practice.p0;

public class DP_knapsack {
    public static void main(String[] args) {
        //定义：要装满6个单位空间，此时最小的重量
        int[] w = new int[]{5,8,10};//重量
        int[] s = new int[]{1,3,4};//空间
        int dp[] = new int[100001];
        int kind = s.length;//种类
        int target = 6;
        for(int i=1; i<=target; i++) {//要求背包必须装满时，只是在预定义时，处理方式不同
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<kind; i++) {
            for(int j=s[i]; j<=target; j++) {
                dp[j]=Math.min(dp[j],dp[j-s[i]]+w[i]);
            }
        }
        if(dp[6] != Integer.MAX_VALUE){
            System.out.println(dp[6]);
        }else{
            System.out.println("Impossible!");
        }
    }
}
