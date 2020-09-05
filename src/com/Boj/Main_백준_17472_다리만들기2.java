package com.Boj;

import java.io.*;
import java.util.*;

public class Main_백준_17472_다리만들기2 {

	static int N, M;
	static int[][] A;
	static int[][] sect;
	static int[] P;
	static ArrayList<graph> adj = new ArrayList<>();

	final static int dy[] = { 0, 1, 0, -1 };
	final static int dx[] = { 1, 0, -1, 0 };

	static boolean oob(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}

	static void bfs(int y, int x, int isNum) {
		Queue<pos> q = new LinkedList<>();
		q.offer(new pos(y, x));
		sect[y][x] = isNum;
		while (q.size() > 0) {
			pos c = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = c.y + dy[i];
				int nx = c.x + dx[i];
				if (oob(ny, nx) || A[ny][nx] == 0 || sect[ny][nx] != 0)
					continue;
				sect[ny][nx] = isNum;
				q.offer(new pos(ny, nx));
			}
		}
	}
	

	//다리 놓기
	static void makeBridge(int y, int x, int num) {
		for (int i = 0; i < 2; i++)
		{
			int next = 0;
			int dist =0;
			int cy =y;
			int cx =x;
			while (true) 
			{
				int ny =cy + dy[i];
				int nx = cx + dx[i];
				if(oob(ny,nx)) break;
				if (sect[ny][nx] == num)
					break;
				else if (sect[ny][nx] == 0) {
					cy = ny;
					cx = nx;
					dist++;
				}
				if(sect[ny][nx]!=0)
				{
					next=sect[ny][nx];
					break;
				}
			}//end of while
			
			//next가 0이 아니고 dist가 2이상이면 인접리스트에 추가한다.
			if(next!=0 &&dist>1)
			{
				adj.add(new graph(num, next, dist));
			}
		}
	}
	
	//MST를 위한 유니온 파인드
	static int find(int x)
	{
		if(P[x]==x) return x;
		return P[x]=find(P[x]);
	}
	static boolean isCycle;
	
	static void union(int a,int b)
	{
		isCycle=false;
		a = find(a);
		b= find(b);
		if(a==b) return;
		P[b]=a;
		isCycle=true;
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		sect = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		int color = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sect[i][j] == 0 && A[i][j] == 1) {
					bfs(i, j, color++);
				}
			}
		}
		//부모 초기화
		P = new int[color+1];
		for(int i=1;i<=color;i++) P[i]=i;

		// 다리 놓기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sect[i][j] != 0) {
					makeBridge(i, j, sect[i][j]);
				}
			}
		}
		Collections.sort(adj);
		
		int ans=0;
		int con=0;
		
		for(int i=0;i<adj.size();i++)
		{
			union(adj.get(i).from,adj.get(i).to);
			if(isCycle)
			{
				ans+=adj.get(i).cost;
				con++;
			}
		}
		//섬의 개수-1 = 다리의 개수가 아니면 -1 출력
		if(con!=color-2) 
			{
				System.out.println(-1);
				return;
			}
		System.out.println(ans);
		
	}

	private static class pos {
		int y, x;

		public pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	private static class graph implements Comparable<graph> {
		int from, to, cost;

		public graph(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}


		@Override
		public int compareTo(graph o) {
			return this.cost-o.cost;
		}

	}
}
