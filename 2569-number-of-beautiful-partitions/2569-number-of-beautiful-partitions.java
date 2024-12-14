class Solution {
    private static int M = 1_000_000_007;
    private static boolean[] P = new boolean[10];
    public int beautifulPartitions(String s, int k, int minL) {
        int n = s.length();
        if (minL*k > n) return 0; // corner case 1: total length is not enough

        P[2] = P[3] = P[5] = P[7] = true;
        if (!P[s.charAt(0)-'0'] || P[s.charAt(n-1)-'0']) return 0; // corner case 2: beginning or end is not valid
        if (k == 1) return 1; //  corner case 3

        List<Integer> begins = getValidBeginIndices(s, n, minL);
        int m = begins.size();
        if (m < k - 1) return 0; // corner case 4

        int[][] dp = new int[k][m];
        for (var _a : dp) Arrays.fill(_a, -1); // -1 means not visited

        return dfs(begins, k-1, 0, 0, minL, dp); // select k-1 indices within array
    }

    private int dfs(List<Integer> begins, int k, int idx, int pre, int len, int[][] dp) {
        if (k == 0) return 1; // selected everything
        if (dp[k][idx] > -1) return dp[k][idx];

        dp[k][idx] = 0; // do not select current
        for (int i = idx; i <= begins.size()-k; i++) { // making sure there's enough left
            int cur = begins.get(i);
            if (cur - pre < len) continue;
            dp[k][idx] = (dp[k][idx] + dfs(begins, k-1, i+1, cur, len, dp)) % M;
        }

        return dp[k][idx];
    }

    private List<Integer> getValidBeginIndices(String s, int n, int minL) {
        List<Integer> begins = new ArrayList<>(n / 2 + 1);
        for (int i = minL; i <= n - minL; i++) // find all valid beginning index
            if (P[s.charAt(i)-'0'] && !P[s.charAt(i - 1)-'0']) begins.add(i);
        return begins;
    }
}