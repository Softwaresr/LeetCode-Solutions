class Solution {
    public int distributeCandies(int n, int limit) {
        int min = Math.max(0, n - 2 * limit);
        int max = Math.min(n, limit);
        int ways = 0;
        for(int i = min; i <= max; i++){
            int N = n - i;
            int minCh2 = Math.max(0, N - limit);
            int maxCh2 = Math.min(N, limit);
            ways += maxCh2 - minCh2 + 1;

        }
        return ways;
        
        
    }
}