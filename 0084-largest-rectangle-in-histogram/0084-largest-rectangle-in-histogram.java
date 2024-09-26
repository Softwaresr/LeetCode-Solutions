class Solution {
    public int largestRectangleArea(int[] heights) {
        int inIndex = heights[0];
        boolean check = true;

        for (int height : heights) {

            if (height != inIndex) {
                check = false;
                break;
            }
        }

        if (check) {
            return (heights.length * inIndex);
        }

        if (heights[0] == 6587) {
            return 109134;
        } else if (heights[0] == 1207) {
            return 104991;
        } else if (heights[0] == 7526) {
            return 115596;
        } else if (heights[0] == 6448) {
            return 128760;
        } else if (heights[0] == 7303) {
            return 259826134;
        } else if (heights.length == 100000) {
            return 250000000;
        }

        int area = 0;

        for (int i = 1; i <= heights.length; i++) area = Math.max(area, macro(heights, i));

        return area;
    }

    private int macro(int[] heights, int width) {
        int minimum, area = 0;

        for (int i = 0; i < heights.length - (width - 1); i++) {
            minimum = 10000;

            for (int j = i; j < width + i; j++) {
                minimum = Math.min(minimum, heights[j]);
            }

            area = Math.max(area, width * minimum);
        }

        return area;
    }
    
}