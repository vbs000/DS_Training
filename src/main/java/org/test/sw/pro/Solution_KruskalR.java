package org.test.sw.pro;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_KruskalR {
    static StreamTokenizer st;
    static int N,M,Q,S,X;
    static long Answer;

    public static void main(String[] args) throws IOException {
//        st = new StreamTokenizer(new BufferedReader(
//                (new FileReader("F:\\@Work\\SW\\sample_input_20220304.txt"))));
        System.setIn(new FileInputStream("F:\\@Work\\SW\\sample_input_20220304.txt"));
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer strT = new StringTokenizer(br.readLine());
//        strT.nextToken();
        int T = nextInt();
        for(int test_case=1;test_case<=T;test_case++){
            Answer = 0;
            N = nextInt();
            M = nextInt();
            Q = nextInt();
            for(int i=0;i<M;i++){
                int A = nextInt();
                int B = nextInt();
                int C = nextInt();
            }
            for(int i=0;i<M;i++){
                S = nextInt();
                X = nextInt();
            }
            System.out.println("#"+test_case+" "+Answer);
        }
    }
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
