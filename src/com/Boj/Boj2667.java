package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


class pos{
    int y,x;
    public pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Boj2667 {

    static int N;
    static int dy[]={0,0,1,-1};
    static int dx[] ={1,-1,0,0};
    static int[][] A = new int[25][25];
    static int[][] d = new int[25][25];
    static boolean oob(int y,int x)
    {
        return y<0 || y>=N || x<0 || x>=N;
    }

    static int bfs(int y,int x,int num)
    {
        int cnt=1;
        Queue<pos> q = new LinkedList<>();
        d[y][x]=num;
        q.add(new pos(y,x));
        while(!q.isEmpty())
        {
            int cy = q.peek().y;
            int cx = q.peek().x;
            q.poll();
            for(int i=0;i<4;i++)
            {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(oob(ny,nx)||d[ny][nx]!=0 || A[ny][nx]==0) continue;
                q.add(new pos(ny,nx));
                d[ny][nx]=d[cy][cx];
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> cnt = new ArrayList<Integer>();
        for(int i=0;i<N;i++)
        {
            String S = br.readLine();
            for(int j=0;j<S.length();j++) A[i][j]=S.charAt(j)-'0';
        }
        int num=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(A[i][j]==1 && d[i][j]==0)
                {
                    num++;
                    int res = bfs(i,j,num);
                    cnt.add(res);
                }
            }
        }
        System.out.println(num);
        Collections.sort(cnt);
        for(int i=0;i<cnt.size();i++) System.out.printf("%d\n",cnt.get(i));
    }
}
