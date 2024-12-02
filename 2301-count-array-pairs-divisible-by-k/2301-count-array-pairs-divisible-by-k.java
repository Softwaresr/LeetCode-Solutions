import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countPairs(int[] nums, int k) {
        Map<Integer, Integer> gcdCount = new HashMap<>();
        long count = 0;

        for (int num : nums) {
            int gcd = gcd(num, k);
            for (int key : gcdCount.keySet()) {
                if ((long) gcd * key % k == 0) {
                    count += gcdCount.get(key);
                }
            }
            gcdCount.put(gcd, gcdCount.getOrDefault(gcd, 0) + 1);
        }

        return count;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
