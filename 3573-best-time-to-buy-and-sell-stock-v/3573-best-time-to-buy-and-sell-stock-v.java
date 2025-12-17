class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if(n < 2 || k == 0) return 0;

        long[][] dp = new long[k + 1][n];
        for(int i = 1; i <= k; i++){
            long maxDiffNormal = -prices[0];
            long maxDiffShort = prices[0];
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i][j - 1];
                dp[i][j] = Math.max(dp[i][j], prices[j] + maxDiffNormal);
                dp[i][j] = Math.max(dp[i][j], -prices[j] + maxDiffShort);
                long prevProfit = dp[i - 1][j - 1];
                maxDiffNormal = Math.max(maxDiffNormal, prevProfit - prices[j]);
                maxDiffShort = Math.max(maxDiffShort, prevProfit + prices[j]);

            }

        }
        return dp[k][n - 1];
        
    }
}