class Solution {
    public int largestRectangleArea(int[] heights) {
        //Arrays that hold the index of the next smaller index
        //to the left and right of the current bar
        int[] leftBound = new int[heights.length];
        int[] rightBound = new int[heights.length];
        

        //Populating these arrays are done in O(n)
        //Check the editorial for 739. Daily Temperatures for more information
        leftBound[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int curr = i - 1;
            //Jump until we find an index with. no less than index
            while (leftBound[curr] != -1 && heights[i] <= heights[curr]){
                curr = leftBound[curr];
            }

            //If current height is less than last least
            if (heights[i] > heights[curr]) {
                leftBound[i] = curr;
            } else {
                leftBound[i] = -1;
            }
        }
        //Populate right bound
        rightBound[rightBound.length - 1] = rightBound.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int curr = i + 1;
            //Jump until we find an index with. no less than index
            while (rightBound[curr] != rightBound.length && heights[i] <= heights[curr]){
                curr = rightBound[curr];
            }

            //If current height is less than last least
            if (heights[i] > heights[curr]) {
                rightBound[i] = curr;
            } else {
                rightBound[i] = rightBound.length;
            }
        }
        
        //Store maximum area
        int max = 0;
        //For every bar
        for (int i = 0; i < heights.length; i++) {
            //Calculate the width of the rectangle
            int width = rightBound[i] - leftBound[i] - 1;

            // Update maximal area
            max = Math.max(max, width * heights[i]);
        }
        
        return max;
        
    }
}