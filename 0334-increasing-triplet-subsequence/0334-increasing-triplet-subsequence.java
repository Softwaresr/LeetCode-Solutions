class Solution {
    public boolean increasingTriplet(int[] nums) {
        int m1=Integer.MAX_VALUE,m2=Integer.MAX_VALUE;

        for(int i:nums){
            if(i<=m1) m1=i;
            else if(i<=m2) m2=i;
            else return true;

        }
        return false;
    }
}