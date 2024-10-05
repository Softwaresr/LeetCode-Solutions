class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] chrs1 = s1.toCharArray();
        char[] chrs2 = s2.toCharArray();
        int[] counts = new int['z' + 1];
        int count = 0;
        for (int i = 0; i < chrs1.length; i++) {
            if (counts[chrs1[i]]++ == 0)
                count++;
            if (counts[chrs2[i]]-- == 1)
                count--;
        }
        if (count != 0)
            return false;
        return dfs(new HashSet<>(), chrs1, chrs2, 0, 0, chrs1.length - 1, chrs1.length - 1);
    }

    public boolean dfs(HashSet<Integer> HS, char[] chrs1, char[] chrs2, int l1, int l2, int r1, int r2) {
        // System.out.println(l1 + " " + l2 + " " + r1 + " " + r2);
        if (l1 == r1)
            return true;
        int k = (l1 << 15) + (l2 << 10) + (r1 << 5) + r2;
        if (HS.contains(k))
            return false;
        int[] countsLeft = new int['z' + 1];
        int[] countsRight = new int['z' + 1];
        int countLeft = 0;
        int countRight = 0;
        int j = l2;
        for (int i = l1; i < r1; i++) {
            if (countsLeft[chrs1[i]]++ == 0)
                countLeft++;
            if (countsLeft[chrs2[j]]-- == 1)
                countLeft--;
            if (countLeft == 0 && dfs(HS, chrs1, chrs2, l1, l2, i, j) && dfs(HS, chrs1, chrs2, i + 1, j + 1, r1, r2))
                return true;
            j++;
        }
        j = r2;
        for (int i = l1; i < r1; i++) {
            if (countsRight[chrs1[i]]++ == 0)
                countRight++;
            if (countsRight[chrs2[j]]-- == 1)
                countRight--;
            if (countRight == 0 && dfs(HS, chrs1, chrs2, l1, j, i, r2) && dfs(HS, chrs1, chrs2, i + 1, l2, r1, j - 1))
                return true;
            j--;
        }
        HS.add(k);
        return false;
    }
}