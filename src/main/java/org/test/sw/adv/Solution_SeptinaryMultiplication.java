package org.test.sw.adv;

import java.util.Scanner;

public class Solution_SeptinaryMultiplication {

    private static int T;
    private static String Answer;
    private static final int BASE=7;
    private static final char ZERO_CHAR = '0';
    private static char DEFAULT_CHAR;


//    1
//    616.4 2.11

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int i=1;i<=T;i++){
            String numA = sc.next();
            String numB = sc.next();

            System.out.println("#"+i+" "+multiply( numA, numB));
        }

    }
    private static String multiply(String numA,String numB){

        int digits = getDigits(numA)+getDigits(numB);
        //System.out.println("digits:"+digits);
        numA = numA.replace(".","");
        numB = numB.replace(".","");

        char[] numA_Arr = numA.toCharArray();
        char[] numB_Arr = numB.toCharArray();
        char[][] element_Arr = new char[numB_Arr.length][numA_Arr.length + numB_Arr.length];
        for(int i=numB_Arr.length-1;i >= 0;i--){
            int cursor = element_Arr[0].length-(numB_Arr.length-i);
            int factor = 0;
            for(int j=numA_Arr.length-1;j >= 0;j--){
                int temp_result = parseInt(numA_Arr[j]) * parseInt(numB_Arr[i]) + factor;
                factor = temp_result / BASE;
                int remainder = temp_result % BASE;
                element_Arr[i][cursor--]=parseChar(remainder);
            }
            if(factor>0){
                element_Arr[i][cursor] = parseChar(factor);
            }

        }
        int[] accum_Arr = new int[numA_Arr.length + numB_Arr.length];
        int factor = 0;
        for(int j=element_Arr[0].length-1;j>=0;j--){
            for(int i=element_Arr.length-1;i>=0;i--){
                    accum_Arr[j] += parseInt(element_Arr[i][j]);
            }
            int temp_result = accum_Arr[j] + factor;
            factor = temp_result / BASE;
            int remainder = temp_result % BASE;
            accum_Arr[j] = remainder;

        }
        String resultStr="";
        String intStr = "";
        boolean ifStart = false;
        //整数部分,去掉头部的0
        for(int i=0;i<accum_Arr.length-digits;i++){
            if(!ifStart && accum_Arr[i] !=0){
                ifStart = true;
            }
            if(ifStart){
                intStr+=parseChar(accum_Arr[i]);
            }
        }
        String decStr = "";
        ifStart = false;
        //小数部分，去掉尾部的0
        for(int i=accum_Arr.length-1;i>=accum_Arr.length-digits;i--){
            if(!ifStart && accum_Arr[i] !=0){
                ifStart = true;
            }
            if(ifStart){
                decStr = parseChar(accum_Arr[i]) + decStr;
            }
        }
        if(!"".equals(decStr)){
            resultStr = intStr+"."+decStr;
        }else{
            resultStr = intStr;
        }
        return resultStr;
    }
    private static int parseInt(char num){
        if(num == DEFAULT_CHAR){
            return 0;
        }else{
            return num - ZERO_CHAR;
        }
    }
    private static char parseChar(int num){
        return (char)((int)ZERO_CHAR+num);
    }
    private static int getDigits(String num){
        if(num.indexOf(".")>=0){
            return num.length()-num.indexOf(".")-1;
        }else{
            return 0;
        }
    }




}
