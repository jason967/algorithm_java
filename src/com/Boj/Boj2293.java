package com.Boj;

import java.util.Scanner;

public class Boj2293 {
    static int[] d = new int[10001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K =sc.nextInt();
        d[0]=1;
        for(int i=0;i<N;i++)
        {
            int c = sc.nextInt();
            for(int j=1;j<=K;j++)
            {
                if(j>=c) d[j]+=d[j-c];
            }
        }
        System.out.print(d[K]);
    }
}
