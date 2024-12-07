class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        
        HashMap<Integer, Integer> stoneIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            stoneIndexMap.put(stones[i], i);
        }
        
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true; 
       
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int distance = stones[i] - stones[j];
                if (distance > j + 1) {
                    break;  
                }
                
                if (dp[j][distance - 1] || dp[j][distance] || dp[j][distance + 1]) {
                    dp[i][distance] = true;
                    if (i == n - 1) {
                        return true;  
                    }
                }
            }
        }
        
        return false;
    }
}