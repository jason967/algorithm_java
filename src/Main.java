
import java.util.Scanner;

public class Main {
    static int N,M;
    static int[] p =new int[1000001];

    static int find(int n)
    {
        if(p[n]==n)return n;
        return p[n]=find(p[n]);
    }

    static void merge(int a,int b)
    {
        a  = find(a);
        b= find(b);
        if(a==b) return;
        p[a]=b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();
        for(int i=0;i<=N;i++) p[i]=i;
        for(int i=0;i<M;i++)
        {
            int A,a,b;
            A=sc.nextInt();
            a =sc.nextInt();
            b = sc.nextInt();
            if(A==1)
            {
                if(find(a)==find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
            else merge(a,b);
        }

    }
}
