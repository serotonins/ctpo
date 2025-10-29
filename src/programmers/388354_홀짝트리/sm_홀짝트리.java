import java.lang.*;
import java.util.*;

class Solution {
    static int[] parents;
    public int[] solution(int[] nodes, int[][] edges) {
        int maxValue = 0;
        for(int node : nodes){
            maxValue = Math.max(maxValue, node);
        }

        parents = new int[maxValue+1];
        int[] degree = new int[maxValue+1];
        for(int i=0; i<=maxValue ;i++){
            parents[i] = i;
        }

        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];

            degree[a]++;
            degree[b]++;
            merge(a, b);
        }

        Map<Integer, TreeInfo> map = new HashMap<>();
        for(int node : nodes){
            int parent = find(node);

            TreeInfo tree = map.getOrDefault(parent, new TreeInfo());

            if(node % 2 == 1 && degree[node] % 2 == 1){
                tree.odd++;
            }else if(node % 2 == 0 && degree[node]% 2 == 0){
                tree.even++;
            }else if(node% 2 == 1 && degree[node]%2 == 0){
                tree.rOdd++;
            }else{
                tree.rEven++;
            }

            map.put(parent, tree);
        }

        for(int key : map.keySet()){
            TreeInfo t = map.get(key);
            System.out.println(key);
            System.out.print(t.odd + " " + t.even + " " + t.rOdd + " " + t.rEven);
            System.out.println();
        }

        int[] answer = new int[2];
        for(TreeInfo t : map.values()){
            if((t.odd == 1 && t.even == 0) || (t.odd == 0 && t.even == 1)){
                answer[0]++;
            }

            if((t.rOdd == 1 && t.rEven == 0) || (t.rOdd == 0 && t.rEven == 1)){
                answer[1]++;
            }
        }

        return answer;
    }

    private static int find(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    private static void merge(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            parents[b] = a;
        }
    }

    static class TreeInfo{
        int odd, even, rOdd, rEven;

        public TreeInfo(){
            this.odd = 0;
            this.even = 0;
            this.rOdd = 0;
            this.rEven = 0;
        }
    }
}