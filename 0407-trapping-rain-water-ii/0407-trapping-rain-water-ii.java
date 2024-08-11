
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int rows = heightMap.length, cols = heightMap[0].length;
        if(heightMap == null || rows < 3 || cols < 3)  return 0;        
        int[][] water = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(heightMap[i], 0, water[i], 0, cols);
        }
        boolean updated = true, init = true;
        while (updated) {
            updated = false;
            for (int i = 1; i < rows - 1; i++) {
                for (int j = 1; j < cols - 1; j++) {
                    int val = Math.max(heightMap[i][j], Math.min(water[i - 1][j], water[i][j - 1]));
                    if (init || val < water[i][j]) {
                        water[i][j] = val;
                        updated = true;
                    }
                }
            }
            init = false;
            for (int i = rows - 2; i > 0; i--) {
                for (int j = cols - 2; j > 0; j--) {
                    int val = Math.max(heightMap[i][j], Math.min(water[i + 1][j], water[i][j + 1]));
                    if (val < water[i][j]) {
                        water[i][j] = val;
                        updated = true;
                    }
                }
            }
        }
        int rs = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (water[i][j] - heightMap[i][j] > 0) {
                    rs += water[i][j] - heightMap[i][j];
                }
            }
        }
        return rs;
    }
}