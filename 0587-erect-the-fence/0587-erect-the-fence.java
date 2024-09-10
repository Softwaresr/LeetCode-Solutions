class Solution {
    public int[][] outerTrees(int[][] trees) {

        // Sort by x asc and then by y desc
        Arrays.sort(trees, (p1, p2) -> {
            if (p1[0] == p2[0]) {
                return p2[1] - p1[1];
                // return p1[1] - p2[1];
            }
            return p1[0] - p2[0];
        });

        Set<Point> res = new HashSet<>();
        res.add(new Point(trees[0]));

        int n = trees.length;
        int i = 0;
        while (i != n - 1) {
            // find the next best slope
            double bestSlope = Integer.MIN_VALUE;
            int[] nextPoint = trees[i + 1];
            int jCandidate = i + 1;

            for (int j = i + 1; j < n; j++) {
                double slope = calculateSlope(trees[i], trees[j]);
                if (slope > bestSlope) {
                    bestSlope = slope;
                    nextPoint = trees[j];
                    jCandidate = j;
                }
            }

            i = jCandidate;
            res.add(new Point(nextPoint));
        }

        // Reverse it
        i = n - 1;
        while (i != 0) {
            // find the next best slope
            double bestSlope = Integer.MIN_VALUE;
             int[] nextPoint = trees[i - 1];
            int jCandidate = i - 1;

            for (int j = i - 1; j >= 0; j--) {
                double slope = calculateSlope(trees[j], trees[i]);
                if (slope > bestSlope) {
                    bestSlope = slope;
                    nextPoint = trees[j];
                    jCandidate = j;
                }
            }

            i = jCandidate;
            res.add(new Point(nextPoint));
        }

        return res.stream().map(p -> new int[]{p.x, p.y}).toArray(int[][]::new);
    }

    private double calculateSlope(int[] p1, int[] p2) {
        if (p1[0] == p2[0]) { // 0 division
            return Integer.MIN_VALUE;
        }
        return (double) (p2[1] - p1[1]) / (double) (p2[0] - p1[0]);
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int[] p) {
            this.x = p[0];
            this.y = p[1];
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}