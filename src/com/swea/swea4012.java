package com.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4012 {
	static int N;
	static int[][] A;
	static int ans;
	static boolean[] used;

	static void go(int idx, int cnt) {

		if (cnt == N / 2) {
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					if (used[i] && used[j])
						sum1 += A[i][j];
					if (!used[i] && !used[j])
						sum2 += A[i][j];
				}
			}
			int diff = Math.abs(sum1 - sum2);
			if (diff < ans)
				ans = diff;
			return;
		}
		for (int i = idx; i < N; i++) {
			if (used[i])
				continue;
			used[i] = true;
			go(i, cnt + 1);
			used[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			A = new int[N][N];
			used = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					A[i][j] = Integer.parseInt(st.nextToken());
			}
			go(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}
}
