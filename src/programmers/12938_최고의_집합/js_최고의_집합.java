class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (s < n) return new int[] {-1};
        for (int i = 0; i < n; i++) {
            answer[i] = s / n + (i >= n - (s - (s/n*n)) ? 1 : 0);
        }
        return answer;
    }
}
