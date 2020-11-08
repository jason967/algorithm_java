package com.Boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {

    static int N;
    static info[] coord;
    static boolean[][] used;

    private static class info {
        int x, y, d;

        public info(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int dy[] = {0, -1, 0, 1};
    static int dx[] = {1, 0, -1, 0};

    static boolean oob(int y, int x) {
        return x < 0 || x > 100 || y < 0 || y > 100;
    }

    static void curve(int x, int y, int d, int gen) {
        coord[0] = new info(x, y, -1);
        coord[1] = new info(x + dx[d], y + dy[d], d);

        for (int i = 1; i <= gen; i++) {
            int start = (1 << (i - 1));
            int end = (1 << i);
            for (int j = start + 1; j <= end; j++) {
                coord[j] = new info(coord[j - 1].x + dx[(coord[end - j + 1].d + 1) % 4], coord[j - 1].y + dy[(coord[end - j + 1].d + 1) % 4], (coord[end - j + 1].d + 1) % 4);
            }
        }
        for (int i = 0; i <= (1 << gen); i++) {
            if (oob(coord[i].x, coord[i].y)) continue;
            used[coord[i].x][coord[i].y] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        coord = new info[1025];
        used = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curve(x, y, d, g);
        }
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (used[i][j] && used[i + 1][j] && used[i][j + 1] && used[i + 1][j + 1]) ans++;
            }
        }
        System.out.print(ans);
    }
}
