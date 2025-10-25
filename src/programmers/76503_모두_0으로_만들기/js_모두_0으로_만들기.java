import java.util.*;

class Solution {
    int n;
    long answer = 0;
    List<Integer>[] ways;
    Queue<Node> que = new ArrayDeque<>();
    Map<Integer, Node> map = new HashMap<>();
    class Node implements Comparable<Node> {
        int num;
        long weight;
        TreeSet<Integer> children = new TreeSet<>();
        public Node(int num, long weight) {
            this.num = num;
            this.weight = weight;
            this.children.addAll(ways[num]);
        }
        public void bubble() {
            answer += Math.abs(weight);
            Node parent = map.get(children.first());
            parent.weight += weight;
            parent.children.remove(this.num);
            map.remove(this.num);
            if (parent.children.size() == 1) que.add(parent);
        }
        public String toString() {
            return num + " " + children;
        }
        public int compareTo(Node o) {
            return this.children.size() - o.children.size();
        }
    }
    public long solution(int[] a, int[][] edges) {
        n = a.length;
        ways = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ways[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            ways[edge[0]].add(edge[1]);
            ways[edge[1]].add(edge[0]);
        }
        
        for (int i = 0; i < n; i++) {
            map.put(i, new Node(i, a[i]));
            if (ways[i].size() == 1) que.add(map.get(i));
        }
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.children.size() == 0) {
                if (now.weight != 0) answer = -1;
                break;
            }
            now.bubble();
        }
        
        return answer;
    }
}
