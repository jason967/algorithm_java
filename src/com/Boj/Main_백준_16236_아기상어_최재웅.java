package com.Boj;

import java.io.*;
import java.util.*;

public class Main_백준_16236_아기상어_최재웅 {

    final static int dy[] = {0, 0, 1, -1};
    final static int dx[] = {1, -1, 0, 0};
    static int N;
    static int time;

    static int[][] A, dist;
    static Shark shark;

    /**
     * 1. 상어를 BFS통해 탐색하면서 먹을 수 있는 물고기라면 ArrayList에 넣는다.
     *
     * 2. ArrayList에 존재하는 생선들을 *주어진 조건*에 따라 정렬한다.
     *
     *     1) 가장 가까운 물고기를 먹는다.
     *     2) 거리가 같다면 y가 작은 순, y도 같다면 x가 작은 순으로 정렬한다.
     *     3) 물고기의 크기는 고려대상이 아니다.
     *
     * 3. ArrayList에 0번 인덱스에 존재하는 물고기가 조건에 충족하는 물고기 이므로 상어는 그 위치로 이동한다.
     *
     * 4. time변수에 상어가 물고기를 먹으로 이동한 거리(이동시간)를 더해준다
     *
     * 5. 더 이상 먹을 물고기가 없을 때까지 시뮬레이션한다.
     */

    /**
     * y와 x가 범위를 벗어나는지 체크하는 메서드
     */
    static boolean oob(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    /**
     * 상어의 움직임을 시뮬레이션하는 메서드
     */
    static boolean sharkMove() {
        Queue<Shark> q = new LinkedList<>();
        List<Fish> fish = new LinkedList<>();
        q.add(new Shark(shark.y, shark.x, shark.stack, shark.size));
        dist[shark.y][shark.x] = 0;
        while (!q.isEmpty()) {
            Shark cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                //자신보다 사이즈가 큰 물고기가 있는 칸으로는 이동 할 수 없다.
                if (oob(ny, nx) || A[ny][nx] > cur.size || dist[ny][nx] != -1) continue;
                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                q.add(new Shark(ny, nx, cur.stack, cur.size));

                //자신과 크기가 같은 물고기가 있는 칸은 '이동은 할 수 있다' 하지만 해당 물고기를 먹을 순 없다,
                if (shark.size > A[ny][nx] && A[ny][nx] != 0) {
                    //먹을 수 있는 물고기를 ArrayList에 삽입한다.
                    fish.add(new Fish(ny, nx, dist[ny][nx]));
                }
            }
        }
        //만약 먹을 물고기가 없다면 false 리턴 후 종료
        if (fish.size() == 0) return false;
        //물고기 우선순위에 맞도록 정렬
        Collections.sort(fish);

        //정렬된 ArrayList에서 0번 인덱스에 존재하는 물고기가 요건에 맞는 물고기가 됌
        Fish eat = fish.get(0);

        //물고기를 죽이고, 상어를 물고기의 위치로 이동
        A[eat.y][eat.x] = 9;
        A[shark.y][shark.x] = 0;

        //상어의 위치를 변경
        shark.y = eat.y;
        shark.x = eat.x;

        //상어의 스택+1
        shark.stack++;

        //만약 상어의 사이즈와 스택 사이즈가 같아졌다면 size++, 다시 stack=0
        if (shark.stack == shark.size) {
            shark.size++;
            shark.stack = 0;
        }
        //전역변수에 time에 상어가 물고기를 먹기까지 이동한거리 더해줌
        time += eat.dist;

        //다음 시뮬레이션에 사용하기 위해 -1로 다시 초기화
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) dist[i][j] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        dist = new int[N][N];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) dist[i][j] = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == 9) {
                    shark = new Shark(i, j, 0, 2);
                }
            }
        }
        //상어의 움직임이 더이상 없을 때까지 반복
        while (sharkMove()) ;
        System.out.print(time);
    }

    private static class Fish implements Comparable<Fish> {
        int y, x, dist;

        public Fish(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (o.dist == this.dist) {
                if (o.y == this.y) {
                    return this.x - o.x;
                }
                return this.y - o.y;
            }
            return this.dist - o.dist;
        }
    }

    private static class Shark {
        int y, x, stack, size;

        public Shark(int y, int x, int stack, int size) {
            this.y = y;
            this.x = x;
            this.stack = stack;
            this.size = size;
        }

    }

}
