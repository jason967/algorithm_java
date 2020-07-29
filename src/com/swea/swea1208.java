package com.swea;

import java.util.Arrays;
import java.util.Scanner;

public class swea1208 {
    static int N;
    static int[] A= new int[100];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        for(int tc=1;tc<=4;tc++)
        {
            N =sc.nextInt();
            for(int i=0;i<100;i++) A[i]=sc.nextInt();
            Arrays.sort(A);
            for(int i=0;i<N;i++)
            {
                System.out.print(A[0]+' '+A[99]+"\n");
                if(A[0]==A[99]) break;
                A[0]--;
                A[99]++;
                Arrays.sort(A);
            }
            System.out.printf("#%d %d\n", tc,A[99]-A[0]);
        }
    }
}
