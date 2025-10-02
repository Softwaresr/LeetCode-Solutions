class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int pref[] = new int[nums.length];
        int suf[] = new int[nums.length];

        for(int i=0; i<nums.length; i++){
            if(i%k == 0){
                pref[i] = nums[i];

            }
            else{
                pref[i] = Math.max(pref[i-1], nums[i]);

            }
        }

        suf[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2; i>=0; i--){
            if((i+1)%k == 0){
                suf[i] = nums[i];

            }
            else{
                suf[i] = Math.max(suf[i+1], nums[i]);

            }
        }

        int ans[] = new int[nums.length-k+1];
        for(int i = 0; i<ans.length; i++){
            ans[i] = Math.max(suf[i], pref[i+k-1]);

        }
        return ans;
        
    }
}