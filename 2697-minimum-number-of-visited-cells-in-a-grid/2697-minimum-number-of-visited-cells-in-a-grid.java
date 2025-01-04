class Solution {
    public int minimumVisitedCells(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    TreeSet<Integer>[] s0 = new TreeSet[m], s1 = new TreeSet[n];
    for (int i = 0; i < m; i++) {
        s0[i] = new TreeSet<>();
        for (int j = 0; j < n; j++)
            s0[i].add(j);
    }
    for (int j = 0; j < n; j++) {
        s1[j] = new TreeSet<>();
        for (int i = 0; i < m; i++) 
            s1[j].add(i);
    }
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{0, 0, 1});

    while (!q.isEmpty()) {
        int[] cell = q.poll();
        int i = cell[0], j = cell[1], d = cell[2];
        if (i == m - 1 && j == n - 1) return d;

        Integer k = s0[i].ceiling(j+1);
        while (k != null && k <= j + grid[i][j]) {
            q.offer(new int[]{i, k.intValue(), d + 1});
            s0[i].remove(k);
            s1[k.intValue()].remove(i);
            k = s0[i].ceiling(j + 1);
        }
        k = s1[j].ceiling(i+1);
        while (k != null && k <= i + grid[i][j]) {
            q.offer(new int[]{k.intValue(), j, d + 1});
            s1[j].remove(k);
            s0[k.intValue()].remove(j);
            k = s1[j].ceiling(i + 1);
        }
    }
    return -1;
}
}