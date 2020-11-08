package com.Boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Boj_2564_경비원_최재웅 {

    static int R,C;
    static int N,ans;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st= new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int cur = (R+C)*2;
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        for(int i=0;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            A[i]=solve(dir,pos);
        }
        int currentPos = A[N];
        for(int i=0;i<N;i++)
        {
            int clock = Math.abs(currentPos-A[i]);
            ans+=Math.min(clock,cur-clock);
        }
        System.out.print(ans);
    }

    static int solve(int dir,int pos)
    {
        if(dir==1) return pos;
        else if(dir==2) return R+C*2-pos;
        else if(dir==3) return R*2+C*2-pos;
        else return C+pos;
    }
    private static class info
    {
        int x,y,d;

        public info(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
