package org.test.practice.p0;

public class RecursionLearning {
    public static void main(String[] args) {
        //***1.阶乘的递归计算
        try {
            long factorialResult = factorial(6);
            System.out.println("*factorial(6):"+factorialResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //***2.递归打印的简单理解
        recursion_display(6);

        //***3.斐波那契数列的计算
        //(1)线性递归的方式
        try {
            int fibResult = fib(6);
            System.out.println("*fib(6):"+fibResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //(2)尾递归的方式
        int a = 1;//第一项
        int b =1;//第二项

        int newFibResult = newFib(a,b,6);
        System.out.println("*newFib(6):"+newFibResult);

        int newFibResult_fmt2 = newFib_fmt2(a,b,6);
        System.out.println("*newFib_fmt2(6):"+newFibResult_fmt2);

        int newFibResult_fmt3 = newFib_fmt3(6,a,b);
        System.out.println("*newFib_fmt3(6):"+newFibResult_fmt3);

        //(3)循环的方式
        //此时已经给出了两项
        try {
            int newFibResult_repeat =newFib_repeat(6);
            System.out.println("*newFib_repeat(6):"+newFibResult_repeat);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 阶乘
     * @param n 阶乘参数
     * @return 阶乘结果
     * @throws Exception
     */
    public static long factorial(int n) throws Exception{
        if(n<0){
            throw new Exception("参数不能为负!");
        }else if(n==1 || n == 0){
            return 1;
        }else  {
            return n * factorial(n-1);
        }
    }

    /**
     * 理解递归<br>
     * 简单打印变量，以体现递归还原现场（出栈）的过程
     * @param n 参数
     */
    public static void recursion_display(int n){
        int temp = n;
        System.out.println("递进:"+n);
        if(n>0){
            recursion_display(--n);
        }
        System.out.println("回归:"+temp);
    }

    /**
     * 斐波那契数列计算(线性递归)
     * @param n 参数
     * @return 斐波那契数列第N项结果
     * @throws Exception
     */
    public static int fib(int n) throws Exception{
        if(n<0){
            throw new Exception("参数不能为负数!");
        }else if(n==0 || n==1){
            return n;
        }else{
            return fib(n-1)+fib(n-2);
        }
    }

    /**
     * 斐波那契数列计算(尾递归)-方法1
     * @param a 第n项
     * @param b 第n+1项
     * @param n 第几项
     * @return 第n项的值
     */
    public static int newFib(int a,int b,int n){
        if(n>2){
            return newFib(a+b,a,n-1);
        }
        return a;
    }
    /**
     * 斐波那契数列计算(尾递归)-方法2
     * @param a 第n项
     * @param b 第n+1项
     * @param n 第几项
     * @return 第n项的值
     */
    public static int newFib_fmt2(int a,int b,int n){
        if(n<=2){
            return a;
        }
        return newFib_fmt2(a+b,a,n-1);
    }

    /**
     * 斐波那契数列计算(尾递归)-方法3
     * @param n 第几项
     * @param ret1 第n项
     * @param ret2 第n+1项
     * @return 第n项的值
     */
    public static int newFib_fmt3(int n, int ret1, int ret2) {
        if(n == 1) {
            return ret1;
        }
        return newFib_fmt3(n - 1, ret2, ret1 + ret2);
    }

    /**
     * 斐波那契数列计算(循环)
     * @param n 第几项
     * @return 第n项的值
     * @throws Exception
     */
    public static int newFib_repeat(int n) throws Exception {
        if(n<1) {
            throw new Exception("参数必须为正整数!");
        }if(n<=2){//第1项和第2项都为1
            return 1;
        }else{
            int a =1;
            int b =1;
            int sum = 0;
            for(int i=0;i<n-2;i++){
                sum = a+b;
                a = b;
                b = sum;
            }
            return sum;
        }
    }

}
