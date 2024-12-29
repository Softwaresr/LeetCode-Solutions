class Solution {
    public int minimumScore(String s, String t) {
    int ss = s.length(), st = t.length(), k = st - 1;
    int[] dp = new int[st];
    Arrays.fill(dp, -1);
    for (int i = ss - 1; i >= 0 && k >= 0; --i)
        if (s.charAt(i) == t.charAt(k))
            dp[k--] = i;
    int res = k + 1;
    for (int i = 0, j = 0; i < ss && j < st && res > 0; ++i)
        if (s.charAt(i) == t.charAt(j)) {
            for (; k < st && dp[k] <= i; ++k);
            res = Math.min(res, k - (++j));
        }
    return res;
}
}