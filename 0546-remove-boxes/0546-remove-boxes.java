class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] memo = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, memo);
    }

    private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] memo) {
        if (i > j) return 0;
        if (memo[i][j][k] > 0) return memo[i][j][k];
    
        int tmpi = i, tmpk = k;
        while (i + 1 <= j && boxes[i + 1] == boxes[i]) {
            i++;
            k++;
        }
        
        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, memo);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, memo) + removeBoxesSub(boxes, m, j, k + 1, memo));
            }
        }

        memo[tmpi][j][tmpk] = res;
        return res;
    }
}