package com.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class swea_하나로 {
	private static class pos {
		int y, x;

		public pos() {

		}

	}

	private static class graph implements Comparable<graph> {
		int from, to;
		long cost;

		public graph(int from, int to, long cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(graph o) {
			if(this.cost<o.cost) return -1;
			return 1;
		}

	}

	static pos[] island;
	static double E;
	static List<graph> Info;
	static int[] P;

	static int find(int n) {
		if (P[n] == n)
			return P[n];
		return P[n] = find(P[n]);
	}

	static boolean isCycle;

	static void union(int a, int b) {
		isCycle = false;
		a = find(a);
		b = find(b);
		if (a == b)
			return;
		P[b] = a;
		isCycle = true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		//int T = 1;
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			Info = new ArrayList<>();
			P = new int[N];
			for (int i = 0; i < N; i++)
				P[i] = i;
			island = new pos[N + 1];
			for (int i = 0; i < N; i++)
				island[i] = new pos();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i].y = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i].x = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					long dist = (long)(island[i].y - island[j].y) * (island[i].y - island[j].y)
							+ (long)(island[i].x - island[j].x) * (island[i].x - island[j].x);
					Info.add(new graph(i, j, dist));
				}
			}

			Collections.sort(Info);

			double dist = 0;
			
			for (int i = 0; i < Info.size(); i++) {
				union(find(Info.get(i).from), find(Info.get(i).to));
				if (isCycle) {
					dist += Info.get(i).cost;
				}
			}
			
			dist *= E;
			System.out.println("#"+tc+" "+ Math.round(dist));

		} // end of tc
	}
}
