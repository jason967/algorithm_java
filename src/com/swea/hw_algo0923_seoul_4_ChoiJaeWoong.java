package com.swea;

import java.io.*;
import java.util.*;

public class hw_algo0923_seoul_4_ChoiJaeWoong {
    private static int N;
    private static int[][] A;
    private static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N + 2][N + 2];
        dp = new long[N + 2][N + 2][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 3; j <= N; j++) {
                if (A[i][j] == 0) {
                    // 가로
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                    // 세로
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                if (A[i][j] == 0 && A[i - 1][j] == 0 && A[i][j - 1] == 0) {
                    // 대각
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        System.out.print(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }
}
