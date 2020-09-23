package com.Boj;

import java.util.Scanner;

public class Boj1로만들기 {
    private static int N;
    static  int d[] ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        d = new int[N+1];
        for(int i=2;i<=N;i++)
        {
            d[i] = d[i-1]+1;
            if(i%3==0) d[i] =Math.min(d[i],d[i/3]+1);
            if(i%2==0) d[i] =Math.min(d[i],d[i/2]+1);
        }
        System.out.print(d[N]);
    }
}
