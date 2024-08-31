class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current < 0) {
                // Swap maxProduct and minProduct when current is negative
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }
            
            maxProduct = Math.max(current, maxProduct * current);
            minProduct = Math.min(current, minProduct * current);
            
            result = Math.max(result, maxProduct);
        }
        
        return result;
    }
}