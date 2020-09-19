package com.kakao;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.*;
public class num1 {

    public static int lowerBound(List<Integer> arr, int front, int rear, int key){
        int mid;
        while(front<rear){
            mid = (front + rear) / 2;
            if(arr.get(mid) < key) front = mid + 1;
            else rear = mid;
        }
        return rear;
    }

    final static String[][] data={{"cpp","java","python","-"},{"backend","frontend","-","-"},{"junior","senior","-","-"},{"chicken","pizza","-","-"}};

    static String info[]={"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
    static String query[]={"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};


    //static int[][][][] people = new int[4][4][4][4];
    static ArrayList<Integer>[][][][] people = new ArrayList[4][4][4][4];

    public static void main(String[] args) {
        System.out.println(data[0][1]);

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                for(int k=0;k<4;k++)
                {
                    for(int l=0;l<4;l++)
                    {
                        people[i][j][k][l]= new ArrayList<>();
                    }
                }
            }
        }
        //Info 사이즈
        for (int i = 0; i < info.length; i++) {
            String[] Info = info[i].split(" ");
            int num = Integer.parseInt(Info[4]);
            int[] c = new int[4];
            for(int j=0;j<4;j++)
            {
                for(int k=0;k<4;k++)
                {
                    if(Info[j].equals(data[j][k]))
                    {
                        c[j]=k;
                    }
                }
                System.out.print(c[j]+" ");
            }
            System.out.println();
            //4개
            people[c[0]][c[1]][c[2]][c[3]].add(num);
            //3개
            people[c[0]][c[1]][c[2]][2].add(num);
            people[c[0]][c[1]][2][c[3]].add(num);
            people[c[0]][3][2][c[3]].add(num);
            people[3][c[1]][2][c[3]].add(num);
            //2개
            people[c[0]][c[1]][2][2].add(num);
            people[c[0]][3][c[2]][2].add(num);
            people[3][c[1]][c[2]][2].add(num);
            people[c[0]][3][2][c[3]].add(num);
            people[3][c[1]][2][c[3]].add(num);
            people[3][3][c[2]][c[3]].add(num);
            //1개
            people[3][3][2][c[3]].add(num);
            people[3][3][c[2]][2].add(num);
            people[3][c[1]][2][2].add(num);
            people[c[0]][3][2][2].add(num);
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<3;l++)
                    {
                        Collections.sort(people[i][j][k][l]);
                        System.out.println(i+" "+j+" "+k+" "+l+" " +people[i][j][k][l]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }
        for(int i = 0; i< query.length; i++)
        {
            int ans = 0;
            int[] tmp = new int[4];
            String[] Query = query[i].split(" ");
            String[] T = {Query[0],Query[2], Query[4],Query[6]};
            System.out.println(T[0]+" "+T[1]+" "+T[2]+" "+T[3]);
            int num = Integer.parseInt(Query[7]);
            for(int j=0;j<4;j++)
            {
                for(int k=0;k<4;k++)
                {
                    if(T[j].equals(data[j][k]))
                    {
                        tmp[j]=k;
                    }
                }

            }
            int sz = people[tmp[0]][tmp[1]][tmp[2]][tmp[3]].size();
            int res = lowerBound(people[tmp[0]][tmp[1]][tmp[2]][tmp[3]],people[tmp[0]][tmp[1]][tmp[2]][tmp[3]].get(0),people[tmp[0]][tmp[1]][tmp[2]][tmp[3]].get(sz-1),num);
            System.out.println(people[tmp[0]][tmp[1]][tmp[2]][tmp[3]]);
            System.out.println("case: "+i+" "+tmp[0]+" "+tmp[1]+" "+tmp[2]+" "+tmp[3]);
            int temp = people[tmp[0]][tmp[1]][tmp[2]][tmp[3]].size()-(res-people[tmp[0]][tmp[1]][tmp[2]][tmp[3]].get(0));
            System.out.println("temp: "+temp);
        }
        int qSize = query.length;
        int[] answer = new int[qSize];
    }
}
