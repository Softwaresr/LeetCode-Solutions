import java.util.Arrays;

class Solution {
    
    /**
     * Finds the smallest index in the array `P` (up to `endIndex`) 
     * where the value is greater than or equal to `target`.
     */
    private int lowerBound(long[] P, int endIndex, long target) {
        int low = 0;
        int high = endIndex;
        int ans = endIndex + 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (P[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // RENAMED METHOD: Changed from sumOfScores to totalScore
    public long totalScore(int hp, int[] damage, int[] requirement) {
        int n = damage.length;
        
        // --- 1. Pre-calculate Prefix Sum of Damage ---
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + damage[i];
        }

        long totalScore = 0;
        
        // --- 2. Iterate through each room i ---
        for (int i = 1; i <= n; i++) {
            // i is the 1-indexed room number
            
            // C_i = P[i] - hp + requirement[i-1]
            long requiredPrefixSum = P[i] - hp + requirement[i - 1];

            // Search space for j-1 is P[0] to P[i-1].
            int searchEndIndex = i - 1; 

            // Find smallest index l in [0, i-1] such that P[l] >= requiredPrefixSum
            int l = lowerBound(P, searchEndIndex, requiredPrefixSum);
            
            if (l <= searchEndIndex) {
                // Count = i - l
                long count_i = (long)i - l;
                totalScore += count_i;
            }
        }

        return totalScore;
    }
}