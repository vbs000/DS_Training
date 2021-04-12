package org.test.practice.p0;


import java.util.*;
//1
//5 8
//1 2 1
//1 3 2
//1 4 3
//1 5 4
//2 5 3
//3 4 3
//2 4 2
//3 5 1
public class Prim {
    private static int T;
    private static int N;//col
    private static int M;//row
    //private static int[][] edgeArr = new int[300000][3];
    private static List<Edge>[] adjList = new ArrayList[100001];
    static {
        for(int i=0;i<100001;i++){
            adjList[i] = new ArrayList<Edge>();
        }
    }
    private static boolean[] visited = new boolean[100001];
    private static int visitedCnt;
    private static boolean[] inHeap = new boolean[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            for(int i=0;i<N;i++){
                adjList[i].clear();
            }
            for (int i = 0; i < M; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                int weight = sc.nextInt();
                //edgeArr[i] = new int[]{start,end,weight};
                adjList[start].add(new Edge(start,end,weight));
                adjList[end].add(new Edge(end,start,weight));
            }
            Arrays.fill(visited,0,N+1,false);
            Arrays.fill(inHeap,0,N+1,false);
            visitedCnt=0;
            long Answer = prim();
            System.out.println("#"+t+" "+Answer);
        }
    }
    private static long prim(){
        long cost = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2]==o2[2]){
                    return 0;
                }else{
                    return (o1[2]>o2[2])?1:-1;
                }
            }
        });
        pq.offer(new int[]{1,1,0});
        while(!pq.isEmpty()){
            int[] pt = pq.poll();

            if(!visited[pt[1]]){
                visitedCnt++;
                visited[pt[1]] = true;
                cost += pt[2];
                //System.out.println("pt:"+pt[0]+"->"+pt[1]+","+pt[2]);
            }

            if(visitedCnt==N){
                break;
            }
            //next
            for(Edge adjEdge:adjList[pt[1]]){
                if(!visited[adjEdge.end] && (adjEdge.weight>=pt[2] || pt[2]==0)){
                    pq.offer(new int[]{adjEdge.start,adjEdge.end,adjEdge.weight});
                }
            }
        }
        if(visitedCnt==N){
            return cost;
        }else{
            return -1;
        }


    }
    static class Edge{
        int start,end,weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
