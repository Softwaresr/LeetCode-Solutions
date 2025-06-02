class Solution {
    public int candy(int[] ratings) {
        int total = ratings.length;
        for (int index = 0; index < ratings.length - 1;) {
            int[] result = processSegment(ratings, index);
            index = result[0];
            total += result[1];
        }
        return total;
    }

    private int[] processSegment(int[] ratings, int begin) {
        int length = ratings.length;
        int extraCandies = 0;
        int up = 0;
        int down = 0;
        boolean descending = false;
        int index = begin;

        for (; index < length - 1; index++) {
            boolean isUp = ratings[index] < ratings[index + 1];
            boolean isDown = ratings[index] > ratings[index + 1];
            if (!isUp && !isDown) {
                index++;
                break;
            }
            if (isUp) {
                if (descending) break;
                up++;
                extraCandies += up;
            } else {
                descending = true;
                down++;
                extraCandies += down;
            }
        }

        extraCandies -= Math.min(up, down);  // Avoid double-counting the peak
        return new int[] { index, extraCandies };
    }
}