package org.test.sw.adv;

import java.util.Scanner;

public class Solution_matrix_pick_number2 {

    //要求：
    //从给定的矩阵中取出数字的最大的和（要求两两不相邻，横向，纵向，斜向都不行）
    //例：
//        6
//        3 3
//        1 1 1
//        1 99 1
//        1 1 1
//        1 6
//        5 5 10 100 10 5
//        1 3
//        1 2 3
//        1 3
//        1 20 3
//        2 3
//        1 4 5
//        2 0 0
//        2 5
//        1 2 3 4 5
//        6 7 8 9 10

    //输出
    //#1 99
    //#2 110
    //#3 4
    //#4 20
    //#5 7
    //#6 24
    private static int T;
    private static int N;//col
    private static int M;//row
    private static int[][] matrix;
    private static boolean[][] visited;
    private static final int[][] deviation = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };

    private static int Answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            matrix = new int[N][M];

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    matrix[n][m] = sc.nextInt();
                }
            }
            Answer = 0;
            //计算最大取数数量
            int maxNum = (int)Math.ceil((double)N/2)*(int)Math.ceil((double)M/2);
            //System.out.println("maxNum:"+maxNum);
            //init
            int[] pickArr = new int[N*M];
            for(int i=0;i<pickArr.length;i++){
                pickArr[i] = i;
            }
            //calc
            for(int p=1;p<=maxNum;p++){
                int data[]=new int[p];
                visited = new boolean[N][M];
                pick(pickArr, data, 0, pickArr.length-1, 0, p);
            }

            System.out.println("#" + t + " " + Answer);
        }

    }

    private static void pick(int arr[], int data[], int start,
                              int end, int index, int r) {
        if (index == r) {
            int sum = 0;
            for (int j=0; j<r; j++){
                sum += matrix[data[j]/M][data[j]%M];
                //System.out.print(data[j]+" ");
            }
            //System.out.println();
            Answer = Math.max(sum,Answer);
            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            if(checkIfOk(arr[i]/M,arr[i]%M)){
                data[index] = arr[i];
                visited[arr[i]/M][arr[i]%M] = true;
                pick(arr, data, i+1, end, index+1, r);
                visited[arr[i]/M][arr[i]%M] = false;
            }
        }
    }
    private static boolean checkIfOk(int x, int y) {
        if (!visited[x][y]) {
            for (int i = 0; i < deviation.length; i++) {
                int new_x = x + deviation[i][0];
                int new_y = y + deviation[i][1];
                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M
                        && visited[new_x][new_y]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
