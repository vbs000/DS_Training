package org.test.sw.in;

import java.util.Scanner;

public class Solution_Chip {


    private static int T;

    private static int N;
    private static int K;

    private static int P_Count;
    private static int J_Count;
    private static int leftCount;
    private static int[] visited;

    private static char Answer1;
    private static int Answer2;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        T = scan.nextInt();
        for(int i=1;i<=T;i++){
            N = scan.nextInt();
            K = scan.nextInt();

            P_Count = N;
            J_Count = N;
            leftCount = N*2;

            boolean finded = false;
            visited = new int[N*2+1];

            while(!finded && leftCount>0){
                int picked = -1;
                if(K<=leftCount){
                    picked = setCountByIdx(K);
                }else{
                    if(leftCount==1){
                        picked = setCountByIdx(1);
                    }else{
                        int tail = (K-leftCount)%(leftCount-1);
                        if(((K-leftCount)/(leftCount-1))%2==0){
                            picked = setCountByIdx(leftCount-tail);
                        }else{
                            picked = setCountByIdx(tail+1);
                        }
                    }
                }
                if(P_Count>=2*J_Count){
                    Answer1='J';
                    Answer2=picked;
                    finded = true;
                }else if(J_Count>=2*P_Count){
                    Answer1='P';
                    Answer2=picked;
                    finded = true;
                }
            }

            System.out.println("#"+T+" "+Answer1+" "+Answer2);
        }
    }

    private static int setCountByIdx(int idx){
        int temp_idx = 0;
        for(int i=1;i<=visited.length;i++){
            if(visited[i] == 0){
                temp_idx++;
                if(temp_idx == idx){
                    visited[i]=1;
                    leftCount--;
                    if(i%2==1){
                        P_Count--;
                    }else{
                        J_Count--;
                    }
                    return (i-1)/2+1;
                }
            }
        }
        return -1;

    }
}
