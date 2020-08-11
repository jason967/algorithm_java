package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Info {
    int y, x;

    Info(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Boj1941 {

    final static int dy[] = { 0, 0, 1, -1 };
    final static int dx[] = { -1, 1, 0, 0 };

    static int ans;

    static boolean oob(int y, int x) {
        return y < 0 || y >= 5 || x < 0 || x >= 5;
    }

    static boolean[][] check;
    static char[][] A = new char[6][6];
    static int[] selected = new int[8];

    static void go(int cnt, int idx) {
        if (cnt > 7)
            return;
        if (cnt == 7) {

            check = new boolean[5][5];
            int Y = 0;
            int S = 0;
            int Py = 0;
            int Px = 0;
            for (int i = 0; i < 7; i++) {
                int cy = selected[i] / 5;
                int cx = selected[i] % 5;
                if (A[cy][cx] == 'S') {
                    Py = cy;
                    Px = cx;
                    S++;
                } else if (A[cy][cx] == 'Y')
                    Y++;
                check[cy][cx] = true;
            }
            if (Y >= 4)
                return;
            if (bfs(Py, Px) == 7)
                ans++;

            return;
        }
        for (int i = idx; i < 25; i++) {
            selected[cnt] = i;
            go(cnt + 1, i);
        }

    }

    static int bfs(int y, int x) {
        Queue<Info> q = new LinkedList<>();
        boolean visited[][] = new boolean[5][5];
        q.add(new Info(y, x));
        visited[y][x] = true;
        int res = 1;
        while (!q.isEmpty()) {
            int cy = q.peek().y;
            int cx = q.peek().x;
            q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (oob(ny, nx) || visited[ny][nx] || !check[ny][nx])
                    continue;
                visited[ny][nx] = true;
                q.add(new Info(ny, nx));
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String S = br.readLine();
            A[i] = S.toCharArray();
        }
        go(0, 0);
        System.out.println(ans);
    }

}
