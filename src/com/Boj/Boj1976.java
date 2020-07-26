package com.Boj;

import java.util.Scanner;

public class Boj1976 {
    static int N,M,node;
    static int[] p = new int[201];
    static int find(int a)
    {
        if(p[a]==a) return a;
        return p[a]=find(p[a]);
    }
    static void merge(int a,int b)
    {
        a = find(a);
        b= find(b);
        if(a==b) return;
        p[a]=b;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=0;i<N;i++) p[i]=i;
        for(int i=1;i<=N;i++)
        {
            for(int j=1;j<=N;j++) {
                node = sc.nextInt();
                if (node == 1) {
                    merge(find(i), find(j));
                }
            }
        }
        node=sc.nextInt();
        int st = find(node);
        boolean flag = true;
        for(int j=1;j<=M;j++)
        {
            node = sc.nextInt();
            if(st!=find(node))
            {
                flag=false;
                break;
            }
        }
        if(flag) System.out.print("YES");
        else System.out.print("NO");

    }
}
