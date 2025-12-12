class Solution {
    public long totalScore(int hp, int[] damage, int[] req) {
        int n = damage.length;
        long[] pre = new long[n + 1];

        for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + damage[i - 1];

        long ans = 0;

        for (int i = 1; i <= n; i++) {
            long need = pre[i] - (hp - req[i - 1]);

            int idx = lowerBound(pre, need);  // first pos where pre[pos] >= need

            if (idx <= i - 1) {
                ans += (i - idx);  // count of valid starts j
            }
        }

        return ans;
    }

    private int lowerBound(long[] arr, long target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
