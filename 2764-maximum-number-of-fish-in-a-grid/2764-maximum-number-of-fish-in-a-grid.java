class Solution {
    public int findMaxFish(int[][] grid) {

        int maxFish = 0;
        
        for (int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                
                if (grid[i][j] > 0) {
                
                    int fishInRegion = bfs(grid, i, j);
                
                    maxFish = Math.max(maxFish, fishInRegion);
            }
                
            }
        }
        
        return maxFish;
        
    }

    public int bfs(int[][] grid, int i, int j){
        
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0){
            return 0;
        }
        
        int fish = grid[i][j];

    
        grid[i][j] = 0;
        fish += bfs(grid, i + 1, j);
        fish += bfs(grid, i - 1, j);
        fish += bfs(grid, i, j + 1);
        fish += bfs(grid, i, j - 1);

        
        return fish;
    }
}