package org.test.sw.pro;

import java.util.*;
class Solution_PrefixSum {

    // Function to calculate maximum points
    // that can be collected
    static int maxTotalPoints(int[][] arr, int M, int K) {
        int MX = (int) (2e5 + 2);
        int i, l, r, ans = 0;

        // Incremented positions by one
        // to make calculations easier.
        M++;
        int []prefix_sum = new int[MX];
        for (int []it : arr)
            prefix_sum[it[0] + 1] = it[1];
        for (i = 1; i <  M + K; i++)
            prefix_sum[i] += prefix_sum[i - 1];

        for (r = M; r < MX && r <= M + K; r++) {
            l = Math.min(M, M - (K - 2 * (r - M)));
            l = Math.max(1, l);
            ans = Math.max(ans, prefix_sum[r] -
                    prefix_sum[l - 1]);
        }

        for (l = M; l > 0 && l >= M - K; l--) {
            r = Math.max(M, M + (K - 2 * (M - l)));
            r = Math.min(MX - 1, r);
            ans = Math.max(ans, prefix_sum[r] -
                    prefix_sum[l - 1]);
        }
        return ans;
    }

    // Driver code
    public static void main(String[] args)
    {
        int [][]arr = {{2, 8}, {6, 3}, {8, 6}};
        int M = 5;
        int K = 4;
        System.out.print(maxTotalPoints(arr, M, K));
    }
}
