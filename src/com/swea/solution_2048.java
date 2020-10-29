package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class solution_2048 {
    static int N,T;
    static int[][] A;
    static String dir;

    static void move()
    {
        Deque<Integer> F;
        Deque<Integer> B;
        int[][] CA = new int[N][N];
        for(int i=0;i<N;i++)
        {
            F= new ArrayDeque<>();
            B = new ArrayDeque<>();
            for(int j=0;j<N;j++) F.addLast(A[i][j]);
            while(F.size()>0)
            {
                int head = F.pollLast();
                if(head==0) continue;
                if(F.size()==1)
                {
                    int temp = F.pollLast();
                    B.addFirst(temp);
                    break;
                }
                int tail = F.peekLast();
                System.out.println("head: "+ head+"tail: "+tail);
                if(head==tail)
                {
                    B.addFirst(head+tail);
                    F.pollLast();
                }
                else
                {
                    B.addFirst(head);
                }

            }
            for(int j=N-1;j>=0;j--)
            {
                if(B.size()!=0) CA[i][j]=B.pollLast();
            }
        }
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++) System.out.print(CA[i][j]);
            System.out.println();
        }
    }

    static void rotate(String s)
    {
        int Dir=0;
        if(s.equals("up")) Dir=1;
        else if(s.equals("down")) Dir=3;
        else if(s.equals("left")) Dir=2;
        else if(s.equals("right")) Dir=0;

        for(int dir=0;dir<Dir;dir++) {
            int[][] CA = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    CA[i][j] = A[N - j - 1][i];
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) A[i][j] = CA[i][j];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        dir = st.nextToken();
        A= new int[N][N];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) A[i][j]=Integer.parseInt(st.nextToken());
        }
        System.out.println("rot");
        move();
//        rotate(dir);
//        for(int i=0;i<N;i++)
//        {
//            for(int j=0;j<N;j++) System.out.print(A[i][j]+" ");
//            System.out.println();
//        }

        System.out.println(T+" "+dir);
    }
}
