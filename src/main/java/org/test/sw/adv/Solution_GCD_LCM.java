package org.test.sw.adv;

import java.util.Scanner;

public class Solution_GCD_LCM {

    static int T;
    static long A;
    static long B;

    static long Answer1;
    static String Answer2;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for(int i=1;i<=T;i++){
            A = sc.nextLong();
            B = sc.nextLong();

            Answer1 = gcd(A,B);
            Answer2 = longDivision(multiply(String.valueOf(A),String.valueOf(B)),Answer1);

            System.out.println("#"+T+" "+Answer1+" "+Answer2);
        }
    }

    //static long gcd(long a,long b){
    //    if(a==b)return a;
    //    return a>b?gcd(a-b,b):gcd(b-a,a);
    //}
//    static long gcd(long a,long b){
//        return a%b==0?b:gcd(b,a%b);
//    }

    static long gcd(long m,long n) {
        while(n!=0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }



    public static String longDivision(String number,long divisor) {
        StringBuilder result= new StringBuilder();
        char[] dividend = number.toCharArray();
        long carry = 0;
        for (int i = 0;i < dividend.length; i++) {
            long x = carry * 10+ Character.getNumericValue(dividend[i]);
            result.append(x / divisor);
            carry = x % divisor;
        }
        for (int i = 0;i < result.length(); i++) {
            if (result.charAt(i) != '0') {
                return result.substring(i);
            }
        }
        return "";
    }
    static String multiply(String num1, String num2){
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0)
            return "0";
        int result[] = new int[len1 + len2];

        int i_n1 = 0;
        int i_n2 = 0;

        for (int i = len1 - 1; i >= 0; i--){
            int carry = 0;
            int n1 = num1.charAt(i) - '0';

            i_n2 = 0;

            for (int j = len2 - 1; j >= 0; j--){
                int n2 = num2.charAt(j) - '0';
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;
                carry = sum / 10;
                result[i_n1 + i_n2] = sum % 10;
                i_n2++;
            }
            if (carry > 0){
                result[i_n1 + i_n2] += carry;
            }
            i_n1++;
        }
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0){
            i--;
        }
        if (i == -1){
            return "0";
        }
        String s = "";
        while (i >= 0){
            s += (result[i--]);
        }
        return s;
    }
}