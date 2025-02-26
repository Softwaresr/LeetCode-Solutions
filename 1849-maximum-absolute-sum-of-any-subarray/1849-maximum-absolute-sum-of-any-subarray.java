class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int sum=0, max=0,min=0;
        for (int num : nums){
            sum += num;
            if(sum>max) max=sum;
            if(sum<min) min=sum;

        }
        return Math.abs(max-min);
    }
}