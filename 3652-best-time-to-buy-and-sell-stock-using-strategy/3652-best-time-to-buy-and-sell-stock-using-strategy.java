class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long baseProfit = 0;
        for(int i = 0; i < n; i++){
            baseProfit += (long) strategy[i] * prices[i];


        }
        int half = k/2;
        long currentDelta = 0 ;
        for(int i = 0; i < k; i++){
            if(i < half){
                currentDelta += (0L - strategy[i]) * prices[i];

            }
            else{
                currentDelta += (1L - strategy[i]) * prices[i];
            }
        }
        long maxDelta = currentDelta;
        for(int i = 1; i <= n-k; i++){
            currentDelta -= (0L - strategy[i - 1]) * prices[i - 1];
            int midIdx = i + half - 1;
            currentDelta -= (1L - strategy[midIdx]) * prices[midIdx];
            currentDelta += (0L - strategy[midIdx]) * prices[midIdx];

            int lastIdx = i + k - 1;
            currentDelta += (1L - strategy[lastIdx]) * prices[lastIdx];
            maxDelta = Math.max(maxDelta, currentDelta);
        }
        return baseProfit + Math.max(0, maxDelta);
        
    }
}