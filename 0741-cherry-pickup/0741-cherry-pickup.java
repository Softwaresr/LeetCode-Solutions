class Solution {
    int dfs(int[][] grid, int i1, int j1, int i2, Integer[][][] dp) {
        int j2 = i1 + j1 - i2;
        if (i1 >= grid.length || j1 >= grid[0].length || i2 >= grid.length || j2 >= grid[0].length || grid[i1][j1] == -1
                || grid[i2][j2] == -1)
            return Integer.MIN_VALUE;

        if (i1 == grid.length - 1 && j1 == grid[0].length - 1 && i2 == grid.length - 1 && j2 == grid[0].length - 1)
            return grid[i1][j1];

        if (dp[i1][j1][i2] != null)
            return dp[i1][j1][i2];

        int cherry = grid[i1][j1];
        if (i1 != i2 || j1 != j2)
            cherry += grid[i2][j2];

        cherry += Math.max(
                Math.max(dfs(grid, i1 + 1, j1, i2 + 1, dp),
                        dfs(grid, i1 + 1, j1, i2, dp)),
                Math.max(dfs(grid, i1, j1 + 1, i2 + 1, dp),
                        dfs(grid, i1, j1 + 1, i2, dp)));
        return dp[i1][j1][i2] = cherry;

    }

    public int cherryPickup(int[][] grid) {
        Integer[][][] dp = new Integer[grid.length][grid[0].length][grid.length];
        return Math.max(dfs(grid, 0, 0, 0, dp), 0);
    }
}