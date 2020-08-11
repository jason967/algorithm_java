package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int[][] d = new int[1001][1001];
        String str1 = br.readLine();
        String str2 = br.readLine();
        str1='0'+str1;
        str2='0'+str2;
        for(int i=1;i<str1.length();i++)
        {
            char se = str1.charAt(i);
            for(int j=1;j<str2.length();j++)
            {
                char ga = str2.charAt(j);
                if(se==ga) d[i][j]=d[i-1][j-1]+1;
                else
                {
                    if(d[i-1][j]>d[i][j-1]) d[i][j]=d[i-1][j];
                    else d[i][j]=d[i][j-1];
                }
            }
        }//end of for
        System.out.println(d[str1.length()-1][str2.length()-1]);
    }
}
