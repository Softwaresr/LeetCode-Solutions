class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        int max = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] > max) max = nums[i];
            if(target[i] > max) max = target[i];
        }
        
        int[] count1 = new int[max+1];
        int[] count2 = new int[max+1];
        for(int i = 0; i < n; i++) {
            count1[nums[i]]++;
            count2[target[i]]++;
        }
        
        long ans = 0;
        for(int i = 0; i < max - 1; i++) {
            int diff = count1[i] - count2[i];
            count1[i+2] += diff;
            ans = ans + Math.abs(diff);
        }
        
        if(count1[max-1] == count2[max-1] && count1[max] == count2[max])
            return ans/2;
        return -1;
    }
}