package com.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_swea_1767_프로세서연결하기_최재웅 {
    private static class pos {
        int y, x;

        public pos(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }

    }

    static final int dy[] = { 1, -1, 0, 0 };
    static final int dx[] = { 0, 0, 1, -1 };
    static int N;
    static int[][] A;
    static int P;
    static pos[] info = new pos[13];
    static int ans = Integer.MAX_VALUE;
    static int MaxCore ;
    static boolean isFind;

    static boolean oob(int y, int x) {
        return y <= 0 || y > N || x <= 0 || x > N;
    }

    static boolean check(int y,int x,int dir)
    {
        while(true)
        {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(oob(ny,nx)) break;
            if(A[ny][nx]==1 || A[ny][nx]==2) return false;
            y=ny;
            x=nx;
        }
        return true;
    }

    static int makeConnection(int y,int x,int dir, int state)
    {
        int len=0;
        while(true)
        {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(oob(ny,nx)) break;
            A[ny][nx]=state;
            len++;
            y=ny;
            x=nx;
        }
        return len;
    }

    static void selectProcessor(int cnt,int core,int len) {
        if(cnt>P) return;
        if(cnt==P)
        {
            if(core>MaxCore)
            {
                MaxCore=core;
                ans = len;
            }
            else if(core==MaxCore)
            {
                if(ans>len) ans=len;
            }
            return;
        }
        for(int dir=0;dir<4;dir++)
        {
            pos c = info[cnt];
            if(check(c.y,c.x,dir))
            {
                int length = makeConnection(c.y,c.x,dir,2);
                selectProcessor(cnt+1,core+1,len+length);
                makeConnection(c.y,c.x,dir,0);
            }
        }
        selectProcessor(cnt+1,core,len);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            ans = Integer.MAX_VALUE;
            MaxCore=0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = new int[N + 1][N + 1];
            P=0;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= N; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                    if (i != 1 && i != N && j != 1 && j != N && A[i][j] == 1) {
                        info[P++] = new pos(i, j);
                    }
                }
            } // end of for
            selectProcessor(0,0,0);
            System.out.println("#"+tc+" " +ans);
        }
    }
}
