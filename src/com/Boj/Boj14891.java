package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14891 {

    static char[][] A = new char[7][9];

    static void rotate(int n, int dir) {
        if (dir == 1) {
            char t = A[n][7];
            for (int i = 7; i > 0; i--)
                A[n][i] = A[n][i - 1];
            A[n][0] = t;
        } else if (dir == -1) {
            char t = A[n][0];
            for (int i = 0; i < 7; i++)
                A[n][i] = A[n][i + 1];
            A[n][7] = t;
        }
    }


    static void simulation(int num, int dir) {
        boolean[] can = new boolean[3];
        for(int i=0;i<3;i++)
        {
            if(A[i][2]==A[i+1][6]) can[i]=true;
        }
        if (num == 1)
        {
            rotate(0, dir);
            if (!can[0])
            {
                rotate(1, dir*-1);
                if (!can[1])
                {
                    rotate(2, dir);
                    if (!can[2]) rotate(3, dir*-1);
                }
            }
        }
        else if (num == 2)
        {
            if (!can[0]) rotate(0, dir*-1);
            rotate(1, dir);
            if (!can[1])
            {
                rotate(2, dir*-1);
                if (!can[2]) rotate(3, dir);
            }
        }
        else if (num == 3)
        {
            if (!can[1])
            {
                rotate(1, dir*-1);
                if (!can[0]) rotate(0, dir);
            }
            rotate(2, dir);
            if (!can[2]) rotate(3, dir*-1);
        }
        else if (num == 4)
        {
            rotate(3, dir);
            if (!can[2])
            {
                rotate(2, dir*-1);
                if (!can[1])
                {
                    rotate(1, dir);
                    if (!can[0]) rotate(0, dir*-1);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String S = br.readLine();
            A[i] = S.toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            simulation(num, dir);
        }
        int ans=0;
        for(int i=0;i<4;i++)
        {
            if(A[i][0]=='1')
            {
                ans+=(1<<(i));
            }
        }
        System.out.println(ans);
    }

}
