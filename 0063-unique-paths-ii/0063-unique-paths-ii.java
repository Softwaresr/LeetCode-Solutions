class Solution {
    private int c(int m,int n,int[][] dp,int[][] obstacleGrid){
        if(m==0 || n==0 || obstacleGrid[m-1][n-1] == 1) return dp[m][n] = 0;
        
        if(m ==1 && n == 1)return 1;
        if(dp[m][n]!=-1)return dp[m][n];
        return dp[m][n] = c(m-1,n,dp,obstacleGrid) + c(m,n-1,dp,obstacleGrid);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];
        // Arrays.fill(dp,-1);
        for(int i = 0;i<=m;i++){
            for(int j = 0;j<=n;j++){
                dp[i][j] = -1;
            }
        }
        return c(m,n,dp,obstacleGrid);
    }
}