class Solution {
    public int largestAltitude(int[] gain) {
        int a =0;
        int m = 0;
        for(int val: gain){
            a += val;
            m = Math.max(m , a);
        }
        return m;
        

        
    }
}