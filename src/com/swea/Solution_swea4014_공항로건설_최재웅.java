package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_swea4014_공항로건설_최재웅 {
    static int T,N,X;
    static int[][] A;
    static boolean[] C;

    static int colCheck(int row)
    {
        C= new boolean[N];
        for(int i=1;i<N;i++)
        {
            if(Math.abs(A[row][i-1]-A[row][i])>1) return 0;
            if(A[row][i-1]>A[row][i])
            {
                for(int j=1;j<X;j++)
                {
                    if(i+j>=N) return 0;
                    if(C[i+j])return 0;
                    if(A[row][i]!=A[row][i+j]) return 0;
                    C[i+j]=true;
                }
            }
            else if(A[row][i-1]<A[row][i])
            {
                for(int j=1;j<=X;j++)
                {
                    if(i-j<0) return 0;
                    if(C[i-j]) return 0;
                    if(A[row][i-1]!=A[row][i-j]) return 0;
                    C[i-j]=true;
                }
            }
        }
        return 1;
    }
    static int rowCheck(int col)
    {
        C= new boolean[N];
        for(int i=1;i<N;i++)
        {
            if(Math.abs(A[i-1][col]-A[i][col])>1) return 0;
            if(A[i-1][col]>A[i][col])
            {
                for(int j=1;j<X;j++)
                {
                    if(i+j>=N) return 0;
                    if(C[i+j])return 0;
                    if(A[i][col]!=A[i+j][col]) return 0;
                    C[i+j]=true;
                }
            }
            else if(A[i-1][col]<A[i][col])
            {
                for(int j=1;j<=X;j++)
                {
                    if(i-j<0) return 0;
                    if(C[i-j]) return 0;
                    if(A[i-1][col]!=A[i-j][col]) return 0;
                    C[i-j]=true;
                }
            }
        }
        return 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            int ans=0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            A = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) A[i][j] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<N;i++)
            {
                ans+=rowCheck(i);
                ans+=colCheck(i);
            }
            System.out.println("#"+tc+" "+ans);

        }
    }
}
