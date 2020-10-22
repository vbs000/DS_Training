package org.test.sw.adv;

import javax.imageio.ImageReader;
import java.util.Scanner;

public class Solution_MonitoredMaze {

    static int T;
    static int ROW;
    static int COL;
    static int P;
    static char matrix[][];
    static int Answer;

    static int[][] deviation={
            {-1,0,0},
            {-1,1,1},
            {0,1,0},
            {1,1,1},
            {1,0,0},
            {1,-1,1},
            {0,-1,0},
            {-1,-1,1}
    };

    //1
    //6 6 2
    //......
    //##...#
    //##....
    //......
    //....#.
    //...#$.
    //1 6 1 8
    //2 3 2 5
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int i=1;i<=T;i++){
            ROW = sc.nextInt();
            COL = sc.nextInt();
            P = sc.nextInt();

            matrix = new char[ROW][COL];

            for(int x=0;x<ROW;x++){
                matrix[x] = sc.next().toCharArray();
            }

            for(int x=0;x<P;x++){
                int guard_x = sc.nextInt()-1;
                int guard_y = sc.nextInt()-1;
                int guard_range = sc.nextInt();
                int guard_direction = sc.nextInt();
                //开始拟合
                //需要定义a方向，b方向和mid方向
                int[] mid_dv;
                int[] a_dv;
                int[] b_dv;
                int temp_range = 1;
                while(temp_range<=guard_range){
                    //直接指向的点
                    if(temp_range == 1){
                        //初始3点
                        int[] p_mid = new int[]{guard_x+deviation[guard_direction][0],guard_y+deviation[guard_direction][1],-1,-1,-1,-1};
                        int[] p_a;
                        int[] p_b;
                        //定性
                        if(guard_direction==0){//直线
                            //如果x有变，那就是y的+-
                            //如果y有变，就是x的+-
                            p_a = new int[]{p_mid[0]+((deviation[guard_direction][1]!=0)?1:0),p_mid[1]+((deviation[guard_direction][0]!=0)?1:0)};//+
                            p_b = new int[]{p_mid[0]-((deviation[guard_direction][1]!=0)?1:0),p_mid[1]-((deviation[guard_direction][0]!=0)?1:0)};//-
                        }else{//斜线
                            //x,y都有变，变的逆向
                            p_a = new int[]{p_mid[0]-deviation[guard_direction][0],p_mid[1]};//x
                            p_b = new int[]{p_mid[0],p_mid[1]-deviation[guard_direction][1]};//y
                        }

                        mid_dv = new int[]{p_mid[0]-guard_x,p_mid[1]-guard_y};
                        a_dv = new int[]{p_a[0]-guard_x,p_a[1]-guard_y};
                        b_dv = new int[]{p_b[0]-guard_x,p_b[1]-guard_y};
                        //设置child结点
                        if(isReachable(p_a)){
                            matrix[p_a[0]][p_a[1]] = 'P';
                        }
                        if(isReachable(p_b)){
                            matrix[p_b[0]][p_b[1]] = 'P';
                        }
                        if(isReachable(p_mid)){
                            matrix[p_mid[0]][p_mid[1]] = 'P';
                        }
                    }else{
                        //验证，并生成child结点
                    }
                    temp_range++;
                }
            }

            Answer = -1;
            System.out.println("#"+T+" "+Answer);
        }
    }
    static boolean isReachable(int[] point){
        int x = point[0];
        int y= point[1];
        if(x>=0 && y>=0 && x<ROW && y<COL && matrix[x][y] == '.'){
            return true;
        }else{
            return false;
        }
    }
}
