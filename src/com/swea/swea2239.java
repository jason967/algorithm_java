package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea2239 {

    static final int N = 9;
    static int[][] A = new int[9][9];
    static boolean[][] checkRow = new boolean[9][10];
    static boolean[][] checkCol = new boolean[9][10];
    static boolean[][] checkBox = new boolean[9][10];

    static int sq(int y, int x) {
        return (y / 3) * 3 + x / 3;
    }

    static boolean go(int idx) {
        if (idx == 81) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    System.out.print(A[i][j]);
                System.out.println();
            }
            return true;
        }
        int y = idx / 9;
        int x = idx % 9;

        if (A[y][x] != 0) {
            return go(idx + 1);
        }

        for (int i = 1; i <= 9; i++) {
            if (!checkBox[sq(y, x)][i] && !checkRow[y][i] && !checkCol[x][i]) {

                checkBox[sq(y, x)][i] = true;
                checkCol[x][i] = true;
                checkRow[y][i] = true;
                A[y][x] = i;
                if (go(idx + 1)) return true;
                A[y][x] = 0;
                checkBox[sq(y, x)][i] = false;
                checkCol[x][i] = false;
                checkRow[y][i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < N; j++)
                A[i][j] = S.charAt(j) - '0';
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] != 0) {
                    checkBox[sq(i, j)][A[i][j]] = true;
                    checkCol[j][A[i][j]] = true;
                    checkRow[i][A[i][j]] = true;
                }

            }
        }
        go(0);
    }
}
