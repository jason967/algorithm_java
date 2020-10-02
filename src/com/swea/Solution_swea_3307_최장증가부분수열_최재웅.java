package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_swea_3307_최장증가부분수열_최재웅 {
    static int T,N;
    static int[] A,d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            N = Integer.parseInt(br.readLine());
            A = new int[N + 1];
            d = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
            int ans=1;
            for(int i=0;i<N;i++)
            {
                d[i]=1;
                for(int j=0;j<i;j++)
                {
                    if(A[i]>A[j] && d[j]>=d[i])
                    {
                        d[i]=d[j]+1;
                        if(ans<d[i]) ans=d[i];
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, ans);
        }
    }
}
