package com.Boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_17471_게리멘더링
{
    static int N;
    static int[] A;
    static int ans=Integer.MAX_VALUE;
    static boolean[] used;
    static boolean[] vis = new boolean[11];

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    /** 제 1 선거구 인원들이 묶여있는 지 체크하는 dfs*/
    static void dfs1(int node)
    {
        for(int i=0;i<adj.get(node).size();i++)
        {
            int nx = adj.get(node).get(i);
            if(vis[nx]) continue;
            boolean isHere =false;
            for(int j=1;j<=N;j++)
            {
                if(used[j])
                {
                    if(nx==j) isHere =true;
                }
            }
            if(!isHere) continue;
            vis[nx]=true;
            dfs1(nx);
        }
    }

    /** 제 2 선거구 인원들이 묶여있는 지 체크하는 dfs*/
    static void dfs2(int node)
    {
        for(int i=0;i<adj.get(node).size();i++)
        {
            int nx = adj.get(node).get(i);
            if(vis[nx]) continue;
            boolean isHere =false;
            for(int j=1;j<=N;j++)
            {
                if(!used[j])
                {
                    if(nx==j) isHere =true;
                }
            }
            if(!isHere) continue;
            vis[nx]=true;
            dfs2(nx);
        }
    }

    static void go(int cnt) {

        if (cnt == N+1) {
            int c1=0;
            int c2 =0;
            for(int i=1;i<=N;i++)
            {
                if(used[i]) c1++;
                else c2++;
            }
            //양쪽 선거구 인원들중 사람이 없는 쪽이 있다면 리턴
            if(c1==0 || c2==0) return;

            for(int i=1;i<=N;i++) vis[i]=false;
            int tmp =0;
            for(int i=1;i<=N;i++)
            {
                if(used[i])
                {
                    if(!vis[i])
                    {
                        dfs1(i);
                        tmp++;
                        //컴포넌트의 개수가 2이상이면 리턴
                        if(tmp==2) return;
                    }
                }
            }
            for(int i=1;i<=N;i++) vis[i]=false;
            tmp =0;
            for(int i=1;i<=N;i++)
            {
                if(!used[i])
                {
                    if(!vis[i])
                    {
                        dfs2(i);
                        tmp++;
                        //컴포넌트의 개수가 2 이상이면 리턴
                        if(tmp==2) return;
                    }
                }
            }
            int[] sum = new int[2];
            for(int i=1;i<=N;i++)
            {
                if(used[i]) sum[0]+=A[i];
                else sum[1]+=A[i];
            }
            int diff = Math.abs(sum[0]-sum[1]);
            if(ans>diff) ans=diff;

            return;
        }

        used[cnt] = false;
        go(cnt + 1);
        used[cnt] = true;
        go(cnt+1);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        st= new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) A[i]=Integer.parseInt(st.nextToken());
        for(int i=1;i<=N+1;i++) adj.add(new ArrayList<>());

        used = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j=0;j<num;j++)
            {
                //인접리스트를 만들어서 저장
                int n = Integer.parseInt(st.nextToken());
                adj.get(i).add(n);
                adj.get(n).add(i);
            }
        }
        go(1);
        if(ans==Integer.MAX_VALUE) ans=-1;
        System.out.println(ans);

    }
}
