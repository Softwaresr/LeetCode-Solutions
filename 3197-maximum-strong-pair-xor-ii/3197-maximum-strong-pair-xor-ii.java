class Solution {
        public int maximumStrongPairXor(int[] A) {
        int res = 0;
        for (int i = 20; i >= 0; i--) {
            res <<= 1;
            HashMap<Integer, Integer> pref = new HashMap<>(), pref2 = new HashMap<>();
            for (int a : A) {
                int p = a >> i;
                if (!pref.containsKey(p)) {
                    pref.put(p, a);
                    pref2.put(p, a);
                }
                pref.put(p, Math.min(pref.get(p), a));
                pref2.put(p, Math.max(pref2.get(p), a));
            }
            for (int x : pref.keySet()) {
                int y = res ^ 1 ^ x;
                if (x >= y && pref.containsKey(y) && pref.get(x) <= pref2.get(y) * 2) {
                    res |= 1;
                    break;
                }
            }
        }
        return res;
    }
}