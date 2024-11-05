public class Solution {
    // Helper function to calculate binomial coefficient "n choose r" using logarithmic operations
    private long nCr(long n, long r) {
        if (r > n) 
            return 0;
    
        if (r == 0 || n == r) 
            return 1;
    
        double res = 0;
        for (int i = 0; i < r; i++) {
            res += (Math.log(n - i) - Math.log(i + 1));
        }
        
        return Math.round(Math.exp(res));
    }
    
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int n = s.length();
        final long mod = 1000000007; // 10^9 + 7
        
        // Initialize an array to store the frequency of each character in the input string
        int[] occ = new int[26];
        
        // Count the frequency of each character in the string
        for (char ch : s.toCharArray()) {
            occ[ch - 'a']++;
        }
        
        // Initialize a priority queue to maintain the 'k' characters with the highest frequency
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Iterate through the characters and update minHeap with the top 'k' characters
        for (int i = 0; i < 26; i++) {
            if (occ[i] != 0) {
                if (minHeap.size() < k) {
                    minHeap.offer(occ[i]);
                } else if (occ[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(occ[i]);
                }
            }
        }
        
        // If the size of minHeap is less than 'k', return 0
        if (minHeap.size() < k) {
            return 0;
        }
        
        // Initialize 'res' to 1, which will be used to calculate the result
        long res = 1;
        
        // Create a map to store the frequencies of characters selected for the k-subsequence
        Map<Integer, Integer> occFreqSubs = new HashMap<>();
        
        // Iterate through minHeap and update 'res' and occFreqSubs
        while (!minHeap.isEmpty()) {
            int it = minHeap.poll();
            
            res = (res * it) % mod;
            occFreqSubs.put(it, occFreqSubs.getOrDefault(it, 0) + 1);
        }
        
        // Create a map to store the total frequencies of characters in the input string
        Map<Integer, Integer> occFreqTotal = new HashMap<>();
        
        // Populate occFreqTotal
        for (int i = 0; i < 26; i++) {
            occFreqTotal.put(occ[i], occFreqTotal.getOrDefault(occ[i], 0) + 1);
        }
        
        // Iterate through occFreqSubs and update 'res' by considering combinations
        for (Map.Entry<Integer, Integer> entry : occFreqSubs.entrySet()) {
            int a = entry.getKey();
            int b = entry.getValue();
            res = (res * nCr(occFreqTotal.get(a), b)) % mod;
        }
        
        // Return the final result modulo 10^9 + 7
        return (int) (res % mod);
    }
}