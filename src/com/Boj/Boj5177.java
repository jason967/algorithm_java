package com.Boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5177 {

    static String[] res = new String[2];

    static boolean spe(char c) {
        if (c == '(' || c == ')' || c == ']' || c == '[' || c == '{' || c == '}' || c == ',' || c == '.' || c == ':'
                || c == ';')
            return true;
        return false;
    }

    static void check(String S, int num) {
        int len = S.length();
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            char ch = S.charAt(i);
            char prev = ' ';
            if (i > 0)
                prev = S.charAt(i - 1);
            if (spe(prev))
                flag = true;
            if (ch == ' ') {
                int cidx = 0;
                while (true) {
                    if (i + cidx + 1 >= len)
                        break;
                    char Next = S.charAt(i + cidx + 1);
                    if (Next != ' ') {
                        if (spe(Next))
                            flag = true;
                        break;
                    }
                    cidx++;
                }
                i += cidx;
                if (!flag)
                    res[num] += ' ';
            } else
                res[num] += ch;
        } // end of for
    }// end of check

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            for (int i = 0; i < 2; i++)
                res[i] = "";
            for (int i = 0; i < 2; i++) {
                String S = br.readLine();
                S = S.trim().toLowerCase();
                S = S.replace('{', '(');
                S = S.replace('[', '(');
                S = S.replace(']', ')');
                S = S.replace('}', ')');
                S = S.replace(';', ',');
                check(S, i);
            }
            if (res[0].equals(res[1])) {
                sb.append("Data Set ").append(tc).append(": equal\n").append("\n");
            } else {
                sb.append("Data Set ").append(tc).append(": not equal\n").append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
