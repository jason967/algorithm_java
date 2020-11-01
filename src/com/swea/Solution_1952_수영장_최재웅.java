package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_수영장_최재웅 {

    static int[] day;
    static int[] month;
    static int T;
    static int ans = Integer.MAX_VALUE;

    static void go(int Mon, int sum) {
        if (Mon > 12) return;
        if (Mon == 12) {
            if (ans > sum) ans = sum;
            return;
        }
        go(Mon + 1, sum + month[Mon] * day[0]);
        go(Mon + 1, sum + day[1]);
        go(Mon + 3, sum + day[2]);
        go(Mon + 12, sum + day[3]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            day = new int[4];
            month = new int[12];
            for (int i = 0; i < 4; i++) day[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) month[i] = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            go(0, 0);
            System.out.println("#" + tc + " " + ans);
        }
    }
}
