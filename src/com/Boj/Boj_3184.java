package com.Boj;

import javax.sound.sampled.FloatControl;
import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3184 {


    static final int dy[] = {0, 0, 1, -1};
    static final int dx[] = {1, -1, 0, 0};

    static char[][] A;
    static boolean[][] vis;
    static int R, C, S, W;

    static void bfs(int y, int x) {

        Queue<info> q = new LinkedList<>();
        q.offer(new info(y, x));
        vis[y][x] = true;
        int sh = 0;
        int wo = 0;
        if (A[y][x] == 'o') sh++;
        else if (A[y][x] == 'v') wo++;
        while (q.size() > 0) {
            info cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                if (vis[ny][nx] || A[ny][nx] == '#') continue;
                vis[ny][nx] = true;
                q.offer(new info(ny, nx));
                if (A[ny][nx] == 'o') sh++;
                else if (A[ny][nx] == 'v') wo++;
            }
        }
        if (sh > wo) wo = 0;
        else sh = 0;
        S += sh;
        W += wo;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new char[R][C];
        vis = new boolean[R][C];
        for (int i = 0; i < R; i++) A[i] = br.readLine().toCharArray();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] != '#' && !vis[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.print(S+" "+W);

    }

    private static class info {
        int y, x;

        public info(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
