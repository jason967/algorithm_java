package com.swea;

import java.util.Scanner;

public class swea1210 {
    static int[] dy = { 0, 0, -1 };
    static int[] dx = { -1, 1, 0 };
    static Scanner sc = new Scanner(System.in);
    static int[][] board = new int[100][100];
    static boolean[][] vis = new boolean[100][100];

    static boolean OOB(int y, int x) {
        if (y < 0 || y >= 100 || x < 0 || x >= 100)
            return true;
        return false;
    }

    public static void main(String[] args) {

        for (int tc = 1; tc <= 10; tc++) {
            for(int i=0;i<100;i++) for(int j=0;j<100;j++) vis[i][j]=false;
            int cy = 99, cx = 0;
            int T = sc.nextInt();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < 100; i++)
                if (board[99][i] == 2)
                    cx = i;

            int ans = 0;
            boolean flag = true;
            while (true) {
                if(!flag) break;
                vis[cy][cx] = true;
                for (int i = 0; i < 3; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    if (ny == 0) {
                        ans = cx;
                        flag = false;
                        break;
                    }
                    if (OOB(ny, nx) || board[ny][nx] == 0 || vis[ny][nx]) continue;
                    cy = ny;
                    cx = nx;
                    break;
                }
            }
            System.out.println("#" + tc + " " + ans);

        }

    }
}
