class Solution {
        public int findMaximumLength(int[] A) {
        int n = A.length;
        int[] pre = new int[n + 2], dp = new int[n + 1];
        long[] acc = new long[n + 1];
        for (int i = 1; i <= n; i ++) {
            acc[i] = acc[i - 1] + A[i - 1];
        }
        for (int i = 0, j = 1; j <= n; j++) {
            i = Math.max(i, pre[j]); // if this was previously optimizesd.
            dp[j] = dp[i] + 1;
            int k = Arrays.binarySearch(acc, 2 * acc[j] - acc[i]);
            if (k < 0) {
                k = -k - 1;
            }
            pre[k] = j;
        }
        return dp[n];
    }
}