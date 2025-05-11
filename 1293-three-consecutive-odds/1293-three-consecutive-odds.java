class Solution {
    public boolean threeConsecutiveOdds(int[] arr) { int c=0; for(int x:arr) if((c=(x&1)==1?c+1:0)==3) return true; return false; }
}