package com;

import java.util.HashMap;
        import java.util.Map;

class Solution {
    public int solution(int[][] boxes) {
        Map<Integer, Integer> mp = new HashMap<>();
        int answer = 0;
        for(int[] box: boxes){
            for(int i=0; i<2; i++){
                if(mp.containsKey(box[i])){
                    mp.put(box[i], mp.get(box[i]) + 1);
                    continue;
                }
                mp.put(box[i], 1);
            }
        }
        int boxCnt = 0;
        for(Integer value : mp.values()){
            boxCnt += value/2;
        }
        System.out.println();
        return boxes.length - boxCnt;
    }
}