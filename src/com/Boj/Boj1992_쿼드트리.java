package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1992_쿼드트리 {
	static char[][] A;
	static StringBuilder sb = new StringBuilder();
	
	static int oneOrZero(int y,int x,int sz)
	{
		boolean One=true;
		boolean Zero=true;
		for(int i=y;i<y+sz;i++)
		{
			for(int j=x;j<x+sz;j++)
			{
				if(A[i][j]=='1') Zero =false;
				else One= false;
			}
		}
		
		if(Zero) return 0;
		if(One) return 1;
		
		return -1;
	}

	static void recur(int y, int x, int sz) {

		if(sz==1)
		{
			sb.append(A[y][x]);
			return;
		}
		int res = oneOrZero(y, x, sz);
		if(res==0) sb.append(0);
		else if(res==1) sb.append(1);
		else
		{
		sb.append('(');
		recur(y, x, sz / 2);
		recur(y, x + sz / 2, sz / 2);
		recur(y + sz / 2, x, sz / 2);
		recur(y + sz / 2, x + sz / 2, sz / 2);
		sb.append(')');
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = new char[N][N];
		for(int i=0;i<N;i++)
		{
			String S = br.readLine();
			A[i]=S.toCharArray();
		}
		recur(0, 0, N);
		System.out.println(sb);
	}
}
