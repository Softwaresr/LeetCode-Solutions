class Solution {
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        Arrays.sort(counts);
        if (k > 26 || counts[26 - k] == 0) {
            return 0;
        }

        long result = 1, mod = (long) 1e9 + 7;
        int remaining = k, bar = counts[26 - k], a = 0, b = 0;
        for (int i = 25; i >= 0 && counts[i] >= bar; i--) {
            if (counts[i] == bar) {
                a++;
            } else {
                result = result * counts[i] % mod;
                remaining--;
            }
        }

        long comb = 1;
        for (int i = 0; i < remaining; i++) {
            result = result * bar % mod;
            // this calculates the binomial coefficient C(A, Remaining)
            // where A is the number of values equal to bar and remaining is the number
            // of values greater than bar in other words remaining = K - numGreaterThanBar;
            comb = comb * (a - i) / (i + 1);
        }

        return (int) (result * comb % mod);
    }
}