package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class solution_보급로 {

    static char[][] A;
    static int[][] d;
    static int N,T;

    private static class info implements Comparable<info>{
        int y,x,cost;

        public info(int y,int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(info o) {
            return this.cost-o.cost;
        }
    }

    static int dy[] ={0,0,1,-1};
    static int dx[]={1,-1,0,0};

    static boolean oob(int y,int x)
    {
        return y<0 || y>=N || x<0|| x>=N;
    }

    static int dijkstra()
    {
        d= new int[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) d[i][j]=Integer.MAX_VALUE;
        PriorityQueue<info> pq = new PriorityQueue<>();
        pq.add(new info(0,0,0));
        d[0][0]=0;
        while(pq.size()>0)
        {
            int cy= pq.peek().y;
            int cx= pq.peek().x;
            int cost = pq.peek().cost;
            pq.poll();
            for(int i=0;i<4;i++)
            {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(oob(ny,nx)) continue;
                int nextCost = cost+(A[ny][nx]-'0');
                if(d[ny][nx]>nextCost)
                {
                    d[ny][nx]=nextCost;
                    pq.offer(new info(ny,nx,nextCost));
                }
            }
        }
        return d[N-1][N-1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //T = Integer.parseInt(br.readLine());
        T=1;
        for(int tc=1;tc<=T;tc++) {
            N = Integer.parseInt(br.readLine());

            A = new char[N][];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                A[i] = s.toCharArray();
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) System.out.print(A[i][j] - '0' + 1 + " ");
                System.out.println();
            }
            System.out.println("#"+tc+" "+ dijkstra());
        }
    }
}
