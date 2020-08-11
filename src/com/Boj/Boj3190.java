package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3190 {

    static int[][] A = new int[100][100];
    static char[] M=new char[10000];
    static int dy[]={0,1,0,-1};
    static int dx[]={1,0,-1,0};
    static int N,K,L;
    static int[] Hy=new int[10000];
    static int[] Hx=new int[10000];
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int i=0;i<K;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            A[--y][--x]=1;
        }
        L = Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int T = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            M[T]=c;
        }
        int time =0;
        int dir=0;
        int tail=time;
        int cy = Hy[time];
        int cx = Hx[time];
        A[cy][cx]=2;
        while(true)
        {
            int ny = Hy[time]+dy[dir];
            int nx = Hx[time]+dx[dir];
            time++;
            if (ny < 0 || ny >= N || nx < 0 || nx >= N || A[ny][nx]==2) break;
            Hy[time]=ny;
            Hx[time]=nx;
            if(A[Hy[time]][Hx[time]]==0)
            {
                A[Hy[tail]][Hx[tail]]=0;
                tail++;
            }
            A[Hy[time]][Hx[time]]=2;
            if(M[time]=='D') dir=(dir+1)%4;
            if(M[time]=='L') dir=(dir+3)%4;
        }
        System.out.print(time);
    }
}
