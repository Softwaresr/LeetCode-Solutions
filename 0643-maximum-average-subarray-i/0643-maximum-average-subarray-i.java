class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int wind=0,i=0,j=k-1;
        double max=Integer.MIN_VALUE;
        for(int z=0;z<k;z++){
            wind=wind+nums[z];
        }
        while(j!=nums.length){
            max=Math.max(max,(double)wind/k);
j++;
            wind=wind-nums[i];
            i++;
            if(j!=nums.length)
            wind=wind+nums[j];  
        }
        return max;
    }
}