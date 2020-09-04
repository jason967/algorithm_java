package com.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_1681_해밀턴순환회로_최재웅
{
    static int N;
    static int[][] A;
    static boolean[] used;
    static int ans = Integer.MAX_VALUE;

    static void go(int node,int cnt,int cost)
    {
        if(cost>ans) return;
        if(cnt==N) {
            if(A[node][0]==0) return;
            int sum = cost+A[node][0];
            if (sum < ans) ans = sum;
            return;
        }
        for(int i=1;i<N;i++)
        {
            if(node==i) continue;
            if(A[node][i]!=0)
            {
                if(used[i]) continue;
                used[i] =true;
                go(i,cnt+1,cost+A[node][i]);
                used[i]=false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        used = new boolean[N+1];
        for(int i=0;i<N;i++)
        {
            st= new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++) A[i][j]=Integer.parseInt(st.nextToken());
        }
        used[0]=true;
        go(0,1,0);
        System.out.print(ans);
    }
}
