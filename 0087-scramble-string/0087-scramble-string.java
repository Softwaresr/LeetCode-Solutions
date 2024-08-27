class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        Boolean[][] memo = new Boolean[n][n];
        return _isScramble(s1, s2, 0, 0, memo);
    }

    private boolean _isScramble(String src, String dst, int si, int di, Boolean[][] memo) {
        int sn = src.length(), dn = dst.length();
        if (sn == 1) {
            return src.equals(dst);
        } else if (!isAnagram(src, dst)) {
            return false;
        } else if (memo[si][di] != null) {
            return memo[si][di];
        }
        // `i` is the splitter
        for (int i = 1; i < sn; i++) {
            String srcx = src.substring(0, i), srcy = src.substring(i);
            // we check both swapped and unswapped here
            //
            // for example, given input: "abb", "bba", and i = 1
            //
            // _isScramble('a', 'b') && _isScramble('bb', 'ba')
            // or
            // _isScramble('a', 'a') && _isScramble('bb', 'bb')
            //
            if ((_isScramble(srcx, dst.substring(0, i), i, i, memo)
                    && _isScramble(srcy, dst.substring(i), sn - i, i, memo))
                || (_isScramble(srcx, dst.substring(dn - i), i, dn - i, memo)
                    && _isScramble(srcy, dst.substring(0, dn - i), sn - i, dn - i, memo))) {
                return memo[si][di] = true;
            }
        }
        return memo[si][di] = false;
    }

    private boolean isAnagram(String src, String dst) {
        int[] count = new int[26];
        for (int i = 0; i < src.length(); i++) {
            count[src.charAt(i) - 'a']++;
            count[dst.charAt(i) - 'a']--;
        }
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}