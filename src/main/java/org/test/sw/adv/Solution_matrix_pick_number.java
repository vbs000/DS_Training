package org.test.sw.adv;

import java.lang.reflect.Array;
import java.util.*;

public class Solution_matrix_pick_number {

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
    private static int PT_COUNT;
    private static List<int[]> ptList;
    private static List<List<int[]>> cachedPtList;
    private static List<int[]> mvList;
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


            ptList = new ArrayList<>();
            mvList = new ArrayList<>();
            cachedPtList = new ArrayList<>();
            fillList(N,M,ptList,mvList);//col first
            calc(ptList,mvList);
            fillList(M,N,ptList,mvList);//row first
            calc(ptList,mvList);

            System.out.println("#" + t + " " + Answer);
        }

    }
    private static void fillList(int A,int B,List<int[]> ptList,List<int[]> mvList){
        ptList.clear();
        mvList.clear();
        for (int n = 0; n < A; n = n + 2) {
            for (int m = 0; m < B; m = m + 2) {
                ptList.add(new int[]{n, m});
                mvList.add(new int[]{formatZero(N - n - 1), formatZero(M - m - 1)});
            }
        }
    }
    private static void calc(List<int[]> ptList,List<int[]> mvList){

        while (ptList.size() > 0) {
            if(!ifListCached(ptList)){
                List<int[]> tempCacheList = new ArrayList<>();
                tempCacheList.addAll(ptList);
                cachedPtList.add(tempCacheList);
                PT_COUNT = ptList.size();
                //System.out.println("pt size:"+ptList.size());
                List<List<int[]>> planList = new ArrayList<>();
                for (int x = 0; x < ptList.size(); x++) {
                    List<int[]> subPlanList = new ArrayList<>();
                    for (int n = 0; n <= mvList.get(x)[0]; n++) {
                        for (int m = 0; m <= mvList.get(x)[1]; m++) {
                            subPlanList.add(new int[]{ptList.get(x)[0] + n, ptList.get(x)[1] + m});
                            //System.out.println("x:"+x+".pt:"+ptList.get(x)[0]+"+"+n+","+ptList.get(x)[1]+"+"+m);
                        }
                    }
                    planList.add(subPlanList);
                }

                Stack<int[]> calPlan = new Stack<>();
                visited = new boolean[N][M];

                dfs(planList, 0, calPlan);
            }

            ptList.remove(ptList.size() - 1);
        }
    }
    private static boolean ifListCached(List<int[]> compareList){
        for(List<int[]> tempList:cachedPtList){
            if(tempList.size()== compareList.size()){
                int cacheCnt = 0;
                for(int i=0;i<tempList.size();i++){
                    for(int j=0;j<compareList.size();j++){
                        if(tempList.get(i)[0] == compareList.get(j)[0]
                        && tempList.get(i)[1] == compareList.get(j)[1]){
                            cacheCnt++;
                            break;
                        }
                    }
                }
                if(cacheCnt == tempList.size()){
                    return true;
                }
            }
        }
        return false;
    }

    private static void dfs(List<List<int[]>> planList, int deep, Stack<int[]> calPlan) {
        if (deep == PT_COUNT) {
            if (calPlan.size() == PT_COUNT) {
                int t_sum = 0;
                for (int[] ptArr : calPlan) {
                    //System.out.print(ptArr[0]+","+ptArr[1]+"|"+"="+ matrix[ptArr[0]][ptArr[1]]+" ");
                    t_sum += matrix[ptArr[0]][ptArr[1]];
                }
                //System.out.println();
                if (Answer < t_sum) {
                    Answer = t_sum;
                }
            }
        } else {
            for (int i = 0; i < planList.get(deep).size(); i++) {
                int[] pt_idx = planList.get(deep).get(i);
                if (pt_idx[0] < N && pt_idx[1] < M) {
                    if (checkIfOk(pt_idx[0], pt_idx[1])) {
                        visited[pt_idx[0]][pt_idx[1]] = true;
                        calPlan.push(new int[]{pt_idx[0], pt_idx[1]});
                        dfs(planList, deep + 1, calPlan);
                        calPlan.pop();
                        visited[pt_idx[0]][pt_idx[1]] = false;
                    }
                }
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

    private static int formatZero(int number) {
        if (number < 0) {
            number = 0;
        }
        return number;
    }
}
