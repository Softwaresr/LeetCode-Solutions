class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        boolean[] visited = new boolean[status.length];
        HashSet<Integer> foundBoxes = new HashSet<>();
        int totalCandy = 0;

        for (int box : initialBoxes) {
            totalCandy += dfs(box, status, candies, keys, containedBoxes, foundBoxes, visited);
        }

        return totalCandy;
    }

    public int dfs(int box, int[] status, int[] candies, int[][] keys, int[][] containedBoxes,
                   HashSet<Integer> foundBoxes, boolean[] visited) {
        if (visited[box]) {
            return 0;
        }

        if (status[box] == 0) {
            foundBoxes.add(box);
            return 0;
        }

        int totalCandies = candies[box];
        visited[box] = true;

        for (int contained : containedBoxes[box]) {
            totalCandies += dfs(contained, status, candies, keys, containedBoxes, foundBoxes, visited);
        }

        for (int key : keys[box]) {
            status[key] = 1;
            if (foundBoxes.contains(key)) {
                totalCandies += dfs(key, status, candies, keys, containedBoxes, foundBoxes, visited);
            }
        }

        return totalCandies;
    }
}