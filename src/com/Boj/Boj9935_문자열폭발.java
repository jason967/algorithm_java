package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9935_문자열폭발 {
    static char[] str;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        int lenA = A.length();
        int lenB = B.length();
        str = new char[lenA+1];
        int p =0;
        for(int i=0;i<lenA;i++)
        {
            str[p++]=A.charAt(i);
            if(str[p-1]==B.charAt(lenB-1))
            {
                boolean can =true;
                if(p<lenB) continue;
                int h=p-1;
                for(int j=lenB-1;j>=0;j--)
                {
                    if(str[h--]!=B.charAt(j))
                    {
                        can=false;
                        break;
                    }
                }
                if(can) p-=lenB;
            }
        }
        if(p==0) System.out.print("FRULA");
        else for(int i=0;i<p;i++) System.out.print(str[i]);
    }
}
