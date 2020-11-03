package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_탈주범검거 {

    static private class loc {
        int y, x, dir;

        public loc(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    static int[][] A;
    static int N, M, T, L, ans;
    static int sy, sx;

    static final int dy[] = {-1, 0, 1, 0};
    static final int dx[] = {0, 1, 0, -1};

    static boolean oob(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }

    static boolean canDir(int cur, int dir) {
        if (dir == 0) {
            if (cur == 1 || cur == 2 || cur == 5 || cur == 6) return true;
        } else if (dir == 1) {
            if (cur == 1 || cur == 3 || cur == 6 || cur == 7) return true;
        } else if (dir == 2) {
            if (cur == 1 || cur == 2 || cur == 4 || cur == 7) return true;
        }
        if (dir == 3) {
            if (cur == 1 || cur == 3 || cur == 4 || cur == 5) return true;
        }
        return false;
    }

    static void bfs(int depth) {
        int visited[][] = new int[N][M];
        visited[sy][sx] = 1;
        Queue<loc> q = new LinkedList<>();
        q.offer(new loc(sy, sx, A[sy][sx]));
        for (int t = 0; t < depth - 1; t++) {
            int qsz = q.size();
            for (int sz = 0; sz < qsz; sz++) {
                int cy = q.peek().y;
                int cx = q.peek().x;
                int cdir = q.peek().dir;
                q.poll();
                for (int i = 0; i < 4; i++) {

                    if (cdir == 2 && (i == 1 || i == 3)) continue;
                    else if (cdir == 3 && (i == 0 || i == 2)) continue;
                    else if (cdir == 4 && (i == 3 || i == 2)) continue;
                    else if (cdir == 5 && (i == 0 || i == 3)) continue;
                    else if (cdir == 6 && (i == 0 || i == 1)) continue;
                    else if (cdir == 7 && (i == 1 || i == 2)) continue;
                    //이동 가능할때
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    if (oob(ny, nx) || A[ny][nx] == 0 || visited[ny][nx] != 0) continue;
                    int ndir = A[ny][nx];
                    if (canDir(ndir, i)) {
                        visited[ny][nx] = visited[cy][cx] + 1;
                        q.offer(new loc(ny, nx, ndir));
                        ans++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            ans = 1;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            sx = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            A = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) A[i][j] = Integer.parseInt(st.nextToken());
            }
            bfs(L);
            System.out.println("#" + tc + " " + ans);
        }
    }
}
