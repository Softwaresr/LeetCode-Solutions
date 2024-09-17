class Solution {
    public int maxPalindromes(String s, int min) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] odd = new int[n], even = new int[n], dp = new int[n + 1];
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = i > r ? 1 : Math.min(odd[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && cs[i - k] == cs[i + k]) k++;
            odd[i] = k--;
            if (r < i + k) {
                r = i + k;
                l = i - k;
            }
        }
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = i > r ? 0 : Math.min(even[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < n && cs[i - k - 1] == cs[i + k]) k++;
            even[i] = k--;
            if (r < i + k) {
                r = i + k;
                l = i - k - 1;
            }
        }   
        for (int i = n - 1; 0 <= i; --i) {
            int diff = odd[i] * 2 - 1 - min;
            if (0 <= diff) {
                diff /= 2;  
                dp[i - odd[i] + 1 + diff] = Math.max(dp[i - odd[i] + 1 + diff], 1 + dp[i + odd[i] - diff]);
            }
            diff = even[i] * 2 - min;
            if (0 <= diff) {
                diff /= 2;
                dp[i - even[i] + diff] = Math.max(dp[i - even[i] + diff], 1 + dp[i + even[i] - diff]);
            }
            dp[i] = Math.max(dp[i], dp[i + 1]);
        }
        return dp[0];
    }
}