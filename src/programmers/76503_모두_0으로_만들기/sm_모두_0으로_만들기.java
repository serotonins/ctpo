import java.lang.*;
import java.util.*;
class Solution {
    public long solution(int[] a, int[][] edges) {
        if(Arrays.stream(a).sum() != 0) return -1;

        int[] inLine = new int[a.length];
        boolean[] visited = new boolean[a.length];
        List<Integer>[] list = new ArrayList[a.length];
        long[] tmp = new long[a.length];

        for(int i=0; i<a.length; i++){
            list[i] = new ArrayList<>();
            tmp[i] = a[i];
        }

        for(int[] edge : edges){
            inLine[edge[0]]++;
            inLine[edge[1]]++;
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        long answer = 0;
        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<inLine.length ;i++){
            if(inLine[i] == 1) que.offer(i);
        }

        while(!que.isEmpty()){
            int curr = que.poll();
            visited[curr] = true;

            for(int nxt : list[curr]){
                if(visited[nxt]) continue;
                inLine[nxt]--;
                tmp[nxt]+=tmp[curr];
                answer+= Math.abs(tmp[curr]);
                tmp[curr] = 0;

                if(inLine[nxt] == 1) que.offer(nxt);
            }
        }


        return answer;
    }
}