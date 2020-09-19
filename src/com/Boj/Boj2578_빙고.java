package com.Boj;

import java.io.*;
import java.util.*;

public class Boj2578_빙고 {

    static int[][] A = new int[5][5];
    static int[] order = new int[26];
    static boolean[] B = new boolean[26];
    static int ans=1;

    static boolean check()
    {
        int cnt=0;
        boolean can=true;
        for(int i=0;i<5;i++) if(!B[A[i][i]]) can=false;
        if(can) cnt++;

        can= true;
        for(int i=0;i<5;i++) if(!B[A[i][4-i]]) can=false;
        if(can) cnt++;

        for(int i=0;i<5;i++)
        {
            can=true;
            for(int j=0;j<5;j++)
            {
                if(!B[A[i][j]]) can=false;
            }
            if(can) cnt++;
        }
        for(int i=0;i<5;i++) {
            can = true;
            for (int j = 0; j < 5; j++) {
                if (!B[A[j][i]]) can = false;
            }
            if (can) cnt++;
        }
        return cnt>=3;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0;i<5;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++) A[i][j]=Integer.parseInt(st.nextToken());
        }
        int idx=0;
        for(int i=0;i<5;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++) order[idx++]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<25;i++)
        {
            B[order[i]]=true;
            if(check())
            {
                System.out.print(i+1);
                return;
            }
        }
    }
}
