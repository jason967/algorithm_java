package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_SWEA_1868_파핑파핑_최재웅 {

    static int N, T;
    static char[][] A;
    static boolean[][] used;

    static PriorityQueue<loc> pq;

    static final int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};

    static boolean oob(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    static int countBomb(int y, int x) {
        int bomb = 0;
        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (oob(ny, nx)) continue;
            if (A[ny][nx] == '*') bomb++;
        }
        return bomb;
    }

    static int game() {

        int click = 0;
        while (pq.size() > 0) {
            loc cur = pq.poll();
            if (A[cur.y][cur.x] != '.') continue;
            A[cur.y][cur.x] = (char) (cur.bomb + '0');
            if (cur.bomb == 0) {
                used = new boolean[N][N];
                Queue<loc> q = new LinkedList<>();
                q.offer(new loc(cur.y, cur.x, 0));
                used[cur.y][cur.x] = true;
                while (q.size() > 0) {
                    int qsz = q.size();
                    for (int sz = 0; sz < qsz; sz++) {
                        loc c = q.poll();
                        int bomb = countBomb(c.y, c.x);
                        A[c.y][c.x] = (char) (bomb + '0');
                        if (bomb == 0) {
                            for (int i = 0; i < 8; i++) {
                                int ny = c.y + dy[i];
                                int nx = c.x + dx[i];
                                if (oob(ny, nx) || used[ny][nx] || A[ny][nx] == '*') continue;
                                used[ny][nx] = true;
                                q.offer(new loc(ny, nx, 0));
                            }
                        }
                    }
                }
            }
            click++;
        }
        return click;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            A = new char[N][N];
            pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                A[i] = br.readLine().toCharArray();
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (A[i][j] != '.') continue;
                    int bomb = countBomb(i, j);
                    pq.offer(new loc(i, j, bomb));
                }
            }
            System.out.println("#" + tc + " " + game());
        }
    }


    static private class loc implements Comparable<loc> {
        int y, x, bomb;

        public loc(int y, int x, int bomb) {
            this.y = y;
            this.x = x;
            this.bomb = bomb;
        }

        @Override
        public int compareTo(loc o) {
            return this.bomb - o.bomb;
        }
    }
}
