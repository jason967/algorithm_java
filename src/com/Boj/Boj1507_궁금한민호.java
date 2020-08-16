package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**플로이드 와셜 -> 인접리스트로 변환*/
public class Boj1507_궁금한민호 {
    final static int INF= 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] A  = new int[N+1][N+1];
        for(int i=1;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) A[i][j]=Integer.parseInt(st.nextToken());
        }

        for(int k=1;k<=N;k++)
        {
            for(int i=1;i<=N;i++)
            {
                for(int j=1;j<=N;j++)
                {
                    if(A[i][j]!=INF &&A[i][j]>(A[i][k]+A[k][j]))
                    {
                        System.out.print("-1");
                        return;
                    }
                    if(i==j || i==k||j==k) continue;
                    if(A[i][j]==(A[i][k]+A[k][j])) A[i][j]=INF;
                }
            }
        }//end of for
        int ans=0;
        for(int i=1;i<=N;i++) for(int j=i;j<=N;j++) if(A[i][j]!=INF) ans+=A[i][j];
        System.out.print(ans);
    }
}
