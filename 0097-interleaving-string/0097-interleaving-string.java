class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        Boolean[][] mem = new Boolean[s1.length() + 1][s2.length() + 1];
        return is(s1, s2, s3, 0, 0, mem);
    }

    private boolean is(String s1, String s2, String s3, int i, int j, Boolean[][] mem) {
        int idx = i + j;
        if (idx >= s3.length()) {
            return i >= s1.length() && j >= s2.length();
        }
        if (mem[i][j] != null) return mem[i][j];

        boolean res = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(idx)) {
            res = is(s1, s2, s3, i + 1, j, mem);
        }
        //if (res) return true;
        if (!res && j < s2.length() && s2.charAt(j) == s3.charAt(idx)) {
            res = is(s1, s2, s3, i, j + 1, mem);
        }
        mem[i][j] = res;
        return res;
    }
}