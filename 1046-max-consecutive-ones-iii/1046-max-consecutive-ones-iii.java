class Solution {
    public int longestOnes(int[] nums, int k) {
        int start = 0, end;
        for(end = 0;end < nums.length;end++) {
            if(nums[end] == 0) k--;

            if(k < 0) {
                k += 1 - nums[start];
                start++;
            }
            
        }

        return end - start;
    }
}