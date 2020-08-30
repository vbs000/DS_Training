package org.test.sw.adv;

import java.util.*;
import java.util.LinkedList;

public class Solution_Graph_RemoveUnnecessaryEdges {

    static int myCase;
    static int N;
    static int L;
    static int[][] edges;
    static int Answer;

    static class Graph {
        private int V; //顶点数量
        private LinkedList<Integer> adj[]; //邻接表
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }
        //增加边
        void addEdge(int v, int w) {
            adj[v].add(w);
        }
        //删除边
        void removeEdge(int v, int w) {
            adj[v].remove((Integer) w);
        }
        //广度优先遍历
        Boolean isReachable(int s, int d) {
            // 初始化所有顶点未访问
            boolean visited[] = new boolean[V];
            LinkedList<Integer> queue = new LinkedList<Integer>();
            // 开始访问
            visited[s] = true;
            queue.add(s);
            Iterator<Integer> i;
            while (queue.size() != 0) {
                s = queue.poll();//出队列
                int n;
                i = adj[s].listIterator();
                // 遍历邻接链表
                while (i.hasNext()) {
                    n = i.next();
                    //如果找到了目标
                    if (n == d){
                        return true;
                    }else if (!visited[n]) {//否则，没有被访问到的话
                        visited[n] = true;
                        queue.add(n);//加入队列，继续查找
                    }
                }
            }
            // 如果没有找到
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        myCase = sc.nextInt();
        for(int i=1;i<=myCase;i++){
            N = sc.nextInt();
            L = sc.nextInt();
            edges = new int[2][L];
            Graph g = new Graph(N);
            for(int j=0;j<L;j++){
                edges[0][j] = sc.nextInt();
                edges[1][j] = sc.nextInt();
                g.addEdge(edges[0][j]-1, edges[1][j]-1);
            }
            Answer = L;//默认为最大边数
            for(int x=0;x<L;x++){
                g.removeEdge(edges[0][x]-1,edges[1][x]-1);
                if (g.isReachable(edges[0][x]-1, edges[1][x]-1)){//如果删除这条边，关联还存在
                    Answer--;
                }else{//否则加回来
                    g.addEdge(edges[0][x]-1,edges[1][x]-1);
                }
            }
            System.out.println("#"+myCase+" "+Answer);
        }
    }
}
