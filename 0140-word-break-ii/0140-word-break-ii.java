class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        // Create a Set of words from the array so that the
        // lookup cost in the Set becomes O(1)
        Set<String> wordSet = new HashSet<>(wordDict);

        // Initialize the lookup table
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        // Set the first element to an empty string
        dp.get(0).add("");

        for (int i = 1; i <= n; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                String suffix = s.substring(j, i);
                // Checking if the substring from j to i is
                // present in the wordSet
                if (wordSet.contains(suffix)) {
                    // Retrieve the valid sentences from the
                    // previously computed subproblem
                    for (String substring : dp.get(j)) {
                        // Merge the suffix with the already
                        // calculated results, excluding the leading
                        // space
                        temp.add(substring + (substring.isEmpty() ? "" : " ") + suffix);
                    }
                }
            }
            dp.set(i, temp);
        }

        // Return the last element of dp array It contains all
        // the sentences formed from the complete string s.
        return dp.get(n);
    }
}