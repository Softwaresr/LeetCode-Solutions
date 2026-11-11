class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = Math.min(cost1, costBoth);
        long c2 = Math.min(cost2, costBoth);
        long cb = costBoth;
        if(c1 + c2 <= cb){
            return (long) need1 * c1 + (long) need2 * c2;
            
        }
        long m = Math.min(need1, need2);
        long ans = m * cb;
        ans += (long) (need1 - m) * c1;
        ans += (long) (need2 - m) * c2;
        return ans;
        
        
    }
}