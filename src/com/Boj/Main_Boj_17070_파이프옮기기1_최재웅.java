package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Boj_17070_파이프옮기기1_최재웅 {
    static int[][] A;
    static boolean[][] vis;

    static int N;

    static boolean oob(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    final static int dy[] = { 0, 1, 1 };
    final static int dx[] = { 1, 0, 1 };

    static int ans;

    //완전 탐색
    static void go(int y, int x, int dir) {
        //파이프의 한쪽 끝이 (N,N)에 도달한 경우
        if (y == N - 1 && x == N - 1) {
            ans++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            //가로->세로 or 세로->가로로 놓는 경우
            if ((dir ^ 1) == i)
                continue;
            //범위를 벗어남 or 벽 or 이미 파이프를 놓았던 적이 있는 경우
            if (oob(ny, nx) || A[ny][nx] == 1 || vis[ny][nx])
                continue;
            //대각선으로 이동할 때 밀어서 이동시킬수 없는 경우
            if (i == 2 && (A[y][x + 1] == 1 || A[y+1][x] == 1))
                continue;
            vis[ny][nx] = true;
            go(ny, nx, i);
            vis[ny][nx] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        vis = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        go(0, 1, 0);
        System.out.println(ans);
    }
}
