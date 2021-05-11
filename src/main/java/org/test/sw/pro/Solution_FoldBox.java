package org.test.sw.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

//2
//9
//3 8
//4 9
//5 10
//3 11
//4 12
//5 13
//1 17
//2 18
//3 19
//5
//1 8
//2 8
//3 8
//4 8
//5 8
public class Solution_FoldBox {
    static StreamTokenizer tok;
    static int Answer,N;
//    static int width;
//    static int height;
    static String VOL;
    static final int[][] dataArr = new int[100000][2];
    static final List<int[]> ll = new ArrayList<int[]>();;

    public static void main(String[] args) throws IOException {
        tok = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int T = nextInt();
        for(int test_case=1;test_case<=T;test_case++){
            Answer = 0;
//            width = 0;
//            height = 0;
            VOL="";
            N = nextInt();
            for(int i=0;i<N;i++){
                int W = nextInt();
                int H = nextInt();
                dataArr[i][0] = W;
                dataArr[i][1] = H;
//                System.out.println(W+","+H);
            }

            Arrays.sort(dataArr,0,N, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] != o2[0]) {
                        return o1[0] - o2[0];
                    } else {
                        return o2[1] - o1[1];
                    }
                }
            });
            ll.clear();
            calc();
            System.out.println("#"+test_case+" "+Answer+" "+VOL);
        }
    }
    static int nextInt() throws IOException {
        tok.nextToken();
        return (int) tok.nval;
    }

    static void calc() {
        HashMap<Integer,BigInteger> ansMap = new HashMap<>();
        int currentMax = 1;
        BigInteger minVol = getVol(dataArr[0][0],dataArr[0][1]);
        int[] minArr = new int[]{dataArr[0][0],dataArr[0][1]};
        ll.add(new int[]{dataArr[0][0],dataArr[0][1]});
        for (int i = 1; i < N; ++i) {
            int w = dataArr[i][0];
            int h = dataArr[i][1];
            if (h > ll.get(ll.size()-1)[1]) {
                ll.add(new int[]{w,h});
                //System.out.println("if>"+w+","+h);
                if(ll.size()>=currentMax){
                    currentMax = ll.size();
                    if(ansMap.get(ll.size()) == null
                            || getVol(w,h).compareTo(ansMap.get(ll.size()))<0){
                        ansMap.put(ll.size(),getVol(w,h));
                    }
                }
            } else {
                int index = binarySearch(h);
                ll.set(index, new int[]{w,h});
                //System.out.println("else>"+index+"|"+w+","+h);
                if(index>=currentMax){
                    currentMax = index;
                    if(ansMap.get(index) == null
                            || getVol(w,h).compareTo(ansMap.get(index))<0){
                        ansMap.put(index,getVol(w,h));
                    }
                }

            }
            if(getVol(w,h).compareTo(minVol)<0){
                minVol = getVol(w,h);
                minArr[0] = w;
                minArr[1] = h;
            }
        }
        Answer = ll.size();
        if(Answer != 1){
//            width = ll.get(ll.size()-1)[0];
//            height = ll.get(ll.size()-1)[1];
            VOL = ansMap.get(Answer).toString();
        }else{
            VOL = getVol(minArr[0],minArr[1]).toString();
        }

    }
    static BigInteger getVol(int w,int h){
        return BigInteger.valueOf(w).multiply(BigInteger.valueOf(w))
        .multiply(BigInteger.valueOf(h));
    }

    static int binarySearch(int target) {
        int low = 0, high = ll.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (ll.get(mid)[1] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
