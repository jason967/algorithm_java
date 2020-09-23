package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea4613 {
    static final int MOD = 1000000000;
    static final int limit = 1<<10;
    public static void main(String[] args) throws IOException {

        int[][][] memo = new int[101][10][limit];//[자리수][마지막숫자][지금까지 사용한 숫자 비트마스킹]

        for (int i = 1; i < 10; i++) { //길이가 1인수의 개수, 0으로 시작하는 숫자는 불가능 하므로 0갸
            memo[1][i][1<<i]=1;
        }
        for (int i = 2; i <=100; i++) {//길이가 2자리 ~N자리까지를 작성
            for (int j = 0; j < 10; j++) {//마지막 숫자
                for (int k = 0; k < limit; k++) {//비트마스크 한 값
                    int temp = k|(1<<j);
                    switch (j)
                    {
                        case 0:
                            memo[i][j][temp] = (memo[i][j][temp]+ memo[i - 1][j + 1][k])%MOD;
                            break;
                        case 9:
                            memo[i][j][temp] = (memo[i][j][temp]+memo[i - 1][j - 1][k])%MOD;
                            break;
                        default:
                            memo[i][j][temp] = ((memo[i][j][temp]+ memo[i - 1][j - 1][k])%MOD + memo[i - 1][j + 1][k])%MOD;
                            break;
                    }
                }
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());
        for(int tc =1;tc<=TC;tc++)
        {
            int N = Integer.parseInt(br.readLine());
            int ans =0;
            for(int i=0;i<10;i++) {
                ans = (ans+memo[N][i][limit-1])%MOD;
            }
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
