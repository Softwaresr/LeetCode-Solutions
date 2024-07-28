class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        int[] previousDiff = new int[k+1];
        int[] profit = new int[k+1];
        Arrays.fill(previousDiff , Integer.MIN_VALUE);

        for(int j = 0;j<n;j++){
            for(int i=1;i<=k;i++){
                previousDiff[i] = Math.max(previousDiff[i], profit[i-1] - prices[j]);
                profit[i] = Math.max(profit[i], previousDiff[i] + prices[j]);
            }
        }

        return profit[k];
    }
}