package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2458_키순서 {
    static boolean[] v;
    static int[] d;
    static ArrayList<ArrayList<Integer>> In=new ArrayList<>();
    static ArrayList<ArrayList<Integer>> Out=new ArrayList<>();

   static void dfsI(int node)
    {
        int sz = In.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int nx = In.get(node).get(i);
            if(v[nx]) continue;
            v[nx]=true;
            d[nx]++;
            dfsI(nx);
        }
    }
    static void dfsO(int node)
    {
        int sz = Out.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int nx = Out.get(node).get(i);
            if(v[nx]) continue;
            v[nx]=true;
            d[nx]++;
            dfsO(nx);
        }
    }

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++)
        {
            In.add(new<Integer> ArrayList());
            Out.add(new<Integer> ArrayList());
        }
        v=new boolean[N+1];
        d=new int[N+1];
        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            In.get(u).add(v);
            Out.get(v).add(u);
        }
        for(int i=1;i<=N;i++)
        {
            for(int j=0;j<=N;j++) v[j]=false;
            dfsI(i);
        }
        for(int i=1;i<=N;i++)
        {
            for(int j=0;j<=N;j++) v[j]=false;
            dfsO(i);
        }
        int ans=0;
        for(int i=1;i<=N;i++)
        {
            if(d[i]==(N-1)) ans++;
        }
        System.out.print(ans);
    }

}
