package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_swea_5656_벽돌깨기_최재웅 {

    static private class info {
        int y, x, power;

        public info(int y, int x, int power) {
            this.y = y;
            this.x = x;
            this.power = power;
        }
    }

    static int W, H, N;
    static int[][] A, CA;

    static int dy[] = {0, 0, 1, -1};
    static int dx[] = {1, -1, 0, 0};

    static boolean oob(int y, int x) {
        return y < 0 || y >= H || x < 0 || x >= W;
    }

    static void moveDown() {
        boolean isMove = false;
        for (int i = 0; i < W; i++) {
            for (int j = H - 1; j > 0; j--) {
                if (CA[j][i] == 0 && CA[j - 1][i] != 0) {
                    isMove = true;
                    int temp = CA[j][i];
                    CA[j][i] = CA[j - 1][i];
                    CA[j - 1][i] = temp;
                }
            }
        }
        if (isMove) moveDown();
    }

    static int[] used;

    static void boom(int ty, int tx, int power) {
        Queue<info> Q = new LinkedList<>();
        Q.offer(new info(ty, tx, power));
        while (Q.size() > 0) {
            int cy = Q.peek().y;
            int cx = Q.peek().x;
            int cp = Q.peek().power;
            Q.poll();
            CA[cy][cx] = 0;
            for (int dir = 0; dir < 4; dir++) {
                for (int p = 0; p < cp; p++) {
                    int ny = cy + dy[dir] * p;
                    int nx = cx + dx[dir] * p;
                    if (oob(ny, nx)) continue;
                    if (CA[ny][nx] > 1) {
                        Q.offer(new info(ny, nx, CA[ny][nx]));
                    }
                    CA[ny][nx] = 0;
                }
            }
        }
    }

    static void simulation(int col) {
        int target = -1;
        for (int i = 0; i < H; i++) {
            if (CA[i][col] != 0) {
                target = i;
                break;
            }
        }
        if (target == -1) return;
        boom(target, col, CA[target][col]);
    }

    static int ans = Integer.MAX_VALUE;

    static void go(int cnt) {
        if (cnt == N) {
            CA = new int[H][W];
            //바꾸기 위해 복사본 만들어 놓기
            for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) CA[i][j] = A[i][j];
            for (int i = 0; i < N; i++) {
                simulation(used[i]);
                moveDown();
            }
            int temp = 0;
            for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) if (CA[i][j] != 0) temp++;
            if (temp < ans) ans = temp;
            return;
        }
        for (int i = 0; i < W; i++) {
            used[cnt] = i;
            go(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            ans=Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            A = new int[H][W];
            used = new int[N];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) A[i][j] = Integer.parseInt(st.nextToken());
            }

            go(0);
            System.out.println("#"+tc+" "+ans);
        }

    }

}

