class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n <= 1) return 0;

        int[] first = new int[n];
        int minPrice = prices[0];
        for(int i = 1; i<n; i++){
            minPrice = Math.min(minPrice, prices[i]);
            first[i] = Math.max(first[i-1], prices[i] - minPrice );

        }
        int[] second = new int[n];
        int maxPrice  = prices[n-1];
        for(int i = n-2; i >= 0; i--){
            maxPrice = Math.max(maxPrice, prices[i]);
            second[i] = Math.max(second[i+1], maxPrice - prices[i]);

        }

        int maxProfit = 0;
        for(int i = 0; i<n; i++){
            maxProfit = Math.max(maxProfit, first[i] + second[i]);
        }

        return maxProfit;
        
    }
}