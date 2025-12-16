import java.util.*;

class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // bit[i] will store whether chars[i-1] == chars[i]
        int[] bit = new int[n + 1];
        
        // Initial setup for adjacent pairs
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                update(bit, i, 1);
            }
        }
        
        List<Integer> resultList = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int j = q[1];
                // Flipped index j affects pairs (j-1, j) and (j, j+1)
                
                // 1. Remove old contributions
                if (j > 0 && chars[j-1] == chars[j]) update(bit, j-1, -1);
                if (j < n - 1 && chars[j] == chars[j+1]) update(bit, j, -1);
                
                // 2. Flip
                chars[j] = (chars[j] == 'A') ? 'B' : 'A';
                
                // 3. Add new contributions
                if (j > 0 && chars[j-1] == chars[j]) update(bit, j-1, 1);
                if (j < n - 1 && chars[j] == chars[j+1]) update(bit, j, 1);
                
            } else {
                int l = q[1];
                int r = q[2];
                if (l == r) {
                    resultList.add(0);
                } else {
                    // Count pairs s[i] == s[i+1] for i from l to r-1
                    resultList.add(query(bit, r - 1) - query(bit, l - 1));
                }
            }
        }
        
        int[] ans = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) ans[i] = resultList.get(i);
        return ans;
    }

    private void update(int[] bit, int idx, int val) {
        idx++; // BIT is 1-indexed
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    private int query(int[] bit, int idx) {
        idx++; 
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}