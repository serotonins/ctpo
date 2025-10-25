import java.util.*;

class Solution {
    boolean[] stVisit; // 홀짝트리 가능
    boolean[] reVisit; // 역트리 가능
    
    int maxi = 0;
    static int[] nodes;
    Map<Integer, List<Integer>> tree = new HashMap<>();
    
    boolean isOdd(int num) {return num % 2 == 1;}
    
    int[] parents;
    
    void union(int y, int x) {
        y = find(y);
        x = find(x);
        if (y < x) parents[x] = y;
        else parents[y] = x;
    }
    int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        Solution.nodes = nodes;
        Arrays.sort(nodes);
        
        int[] answer = new int[2];
        for (int i : nodes) {maxi = Math.max(maxi, i);}
        parents = new int[maxi+1];
        for (int i = 0; i <= maxi; i++) {parents[i]=i;}
        
        for (int[] edge: edges) {
            if (!tree.containsKey(edge[0])) tree.put(edge[0], new ArrayList<>());
            if (!tree.containsKey(edge[1])) tree.put(edge[1], new ArrayList<>());
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
            union(edge[0], edge[1]);
        }
        for (int i : nodes) {find(i);}
        
        Map<Integer, int[]> treeStReMap = new HashMap<>();
        for (int i : nodes) {
            if (!tree.containsKey(i)) tree.put(i, new ArrayList<>());
            int stRe = (isOdd(i) ^ isOdd(tree.get(i).size())) ? 1 : 0;
            treeStReMap.putIfAbsent(parents[i], new int[2]);
            treeStReMap.get(parents[i])[stRe]++;
        }
        
        for (int i : treeStReMap.keySet()) {
            if (treeStReMap.get(i)[0] == 1) answer[0]++;
            if (treeStReMap.get(i)[1] == 1) answer[1]++;
        }
        
        return answer;
    }
}
