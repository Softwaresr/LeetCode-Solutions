class Solution {
  public int maxProfit(int[] prices) {
    // int buy=prices[0];
    // int profit=0;
    // for (int i=1;i<prices.length;i++){
    //     if(buy>prices[i]){
    //         buy=prices[i];
    //     }
    //     profit=Math.max(profit,prices[i]-buy);
    // }
    // return profit;
    int buy=prices[0];
    int profit=0;
    for(int i=0;i<prices.length;i++){
        if(buy>prices[i]){
            buy=prices[i];
        }
        profit=Math.max(profit,prices[i]-buy);
    }

    return profit;
    }
    
}