package com.swea;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class swea1223 {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(bf.readLine());
            Stack<Character> oper = new Stack<>();
            Stack<Integer> cal = new Stack<>();
            String s = bf.readLine();
            String pre = "";

            for (int i = 0; i < s.length(); i++) {
                char temp = s.charAt(i);
                if (temp >= '0' && temp <= '9') {
                    pre += temp;
                } else if (temp == '*') {
                    oper.add(temp);

                } else if (temp == '+') {

                    while (!oper.isEmpty()) {
                        pre += oper.pop();
                    }
                    oper.add(temp);
                }
            }
            while (!oper.isEmpty()) {
                pre += oper.pop();
            }
            // System.out.println(pre);
            // 후위 표기 완성

            for (int i = 0; i < pre.length(); i++) {
                char temp = pre.charAt(i);
                if (temp >= '0' && temp <= '9') {
                    cal.add(temp - '0');
                } else if (temp == '*') {
                    int a = cal.pop();
                    int b = cal.pop();
                    int res = a * b;
                    cal.add(res);

                } else if (temp == '+') {
                    int a = cal.pop();
                    int b = cal.pop();
                    int res = a + b;
                    cal.add(res);
                }
            }

            System.out.println("#"+tc+" "+cal.peek());
        }

    }
}
