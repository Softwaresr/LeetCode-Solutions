import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // Step 2: Iterate and fix the first element (nums[i])
        // We only need to iterate up to n-3 because we need at least two more elements (left and right)
        for (int i = 0; i < n - 2; i++) {
            
            // Optimization: If the current element is positive, the sum can never be 0 
            // since all remaining elements (left and right) will be non-negative.
            if (nums[i] > 0) {
                break;
            }

            // Skip Duplicates for the first element
            // If the current element is the same as the previous one, 
            // any triplets found will be duplicates of the ones found in the previous iteration.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Target for the remaining two elements: nums[j] + nums[k] = -nums[i]
            int target = -nums[i];
            
            // Step 3: Initialize Two Pointers
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[left] + nums[right];

                if (currentSum == target) {
                    // Triplet found!
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Move pointers inward
                    left++;
                    right--;

                    // Skip Duplicates for the second element (nums[left])
                    // Ensure the new left pointer points to a unique value
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip Duplicates for the third element (nums[right])
                    // Ensure the new right pointer points to a unique value
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (currentSum < target) {
                    // Sum is too small, move left pointer right to increase sum
                    left++;
                } else { // currentSum > target
                    // Sum is too large, move right pointer left to decrease sum
                    right--;
                }
            }
        }

        return result;
    }
}