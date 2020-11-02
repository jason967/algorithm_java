package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea5644 {

    private static class userInfo
    {
        int y,x;

        public userInfo(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean oob(int y,int x)
    {
        return y<=0 || y>10 || x<=0 || x>10;
    }

    static int T;
    static int M,A;
    static int[] userA,userB;
    static ArrayList<Integer>[][] Map;
    static int[] charge;

    static  final int dy[]={0,-1,0,1,0};
    static final int dx[]={0,0,1,0,-1};

    static void spread(int y,int x,int depth,int num)
    {
        boolean[][] used=new boolean[12][12];
        used[y][x]=true;
        Queue<userInfo> q = new LinkedList<>();
        q.offer(new userInfo(y,x));
        Map[y][x].add(num);
        for(int d=1;d<=depth;d++) {
            int qsz = q.size();
            for (int i = 0; i < qsz; i++) {
                int cy = q.peek().y;
                int cx = q.peek().x;
                q.poll();
                for(int dir=1;dir<=4;dir++)
                {
                    int ny = cy+dy[dir];
                    int nx = cx + dx[dir];
                    if(oob(ny,nx) || used[ny][nx]) continue;
                    used[ny][nx]=true;
                    Map[ny][nx].add(num);
                    q.offer(new userInfo(ny,nx));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=T;tc++) {
            userInfo user1 = new userInfo(1, 1);
            userInfo user2 = new userInfo(10, 10);
            //T = 1;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            charge = new int[A + 1];
            Map = new ArrayList[11][11];
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++)
                    Map[i][j] = new ArrayList<>();
            }
            userA = new int[M + 1];
            userB = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) userA[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) userB[i] = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= A; i++) {
                st = new StringTokenizer(br.readLine());
                int y, x, depth;
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                depth = Integer.parseInt(st.nextToken());
                charge[i] = Integer.parseInt(st.nextToken());
                spread(y, x, depth, i);
            }
            int ans = 0;
            for (int t = 0; t <= M; t++) {
                int sumMAX = 0;
                user1.y += dy[userA[t]];
                user1.x += dx[userA[t]];
                user2.y += dy[userB[t]];
                user2.x += dx[userB[t]];
                if (Map[user1.y][user1.x].size() == 0 && Map[user2.y][user2.x].size() != 0) {
                    for (int i = 0; i < Map[user2.y][user2.x].size(); i++) {
                        int sum = charge[Map[user2.y][user2.x].get(i)];
                        if (sumMAX < sum) sumMAX = sum;
                    }
                } else if (Map[user2.y][user2.x].size() == 0 && Map[user1.y][user1.x].size() != 0) {
                    for (int i = 0; i < Map[user1.y][user1.x].size(); i++) {
                        int sum = charge[Map[user1.y][user1.x].get(i)];
                        if (sumMAX < sum) sumMAX = sum;
                    }
                } else if (Map[user2.y][user2.x].size() != 0 && Map[user1.y][user1.x].size() != 0) {
                    for (int i = 0; i < Map[user1.y][user1.x].size(); i++) {
                        int u1Num = Map[user1.y][user1.x].get(i);
                        for (int j = 0; j < Map[user2.y][user2.x].size(); j++) {
                            int sum = 0;
                            int u2Num = Map[user2.y][user2.x].get(j);

                            if (u1Num == u2Num) sum += charge[u1Num];
                            else sum = charge[u1Num] + charge[u2Num];
                            if (sumMAX < sum) sumMAX = sum;
                        }
                    }
                }
                ans += sumMAX;
            }
            System.out.println("#" + tc + " " + ans);
        }

    }
}
