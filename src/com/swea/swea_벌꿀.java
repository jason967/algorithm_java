package com.swea;

import java.util.ArrayList;
import java.util.List;

public class swea_벌꿀 {

    static int[][] A;
    static boolean[][] used;
    static int N, M, C;
    static List<info> selected1Box, selected2Box;

    private static class info {
        int y, x;

        public info(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean oob(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    static boolean can(int y, int x) {
        for (int i = x; i < x + M; i++) if (used[y][i] || oob(y, x)) return false;
        return true;
    }

    void go(int user, int Row) {
        if (user == 2) {
            return;
        }
        for (int y = Row; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (can(y, x)) {

                }
            }
        }
    }


    public static void main(String[] args) {
        N = 4;
        M = 2;
        A = new int[N][N];
        used = new boolean[N][N];

        for (int user1Y = 0; user1Y < N; user1Y++) {
            for (int user1X = 0; user1X < N; user1X++) {
                used[user1Y][user1X] = true;
                if (user1X + M >= N) continue;
                System.out.println("user1Y: " + user1Y + "  user1X: " + user1X);
                //userA 벌꿀 통 선택
                selected1Box = new ArrayList<>();

            }
        }
    }
}