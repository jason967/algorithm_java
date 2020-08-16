package com.Boj;

import java.util.Scanner;

public class Boj1463 {
    static int N;
    static int[] d=new int[1000001];
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        d[1]=0;
        for(int i=2;i<=N;i++)
        {
            d[i]=d[i-1]+1;
            if(i%2==0 &&d[i]>d[i/2]+1) d[i]=d[i/2]+1;
            if(i%3==0 &&d[i]>d[i/3]+1) d[i]=d[i/3]+1;
        }
        System.out.print(d[N]);
    }
}
