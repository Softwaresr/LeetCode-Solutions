class Solution {
    public int sum(int num1, int num2) {
        int[] A = new int[] {num1, num2};
        int[] prefixSum = new int[A.length+1];
        for(int i = 0; i<A.length; i++){
            prefixSum[i+1] = prefixSum[i] + A[i];

        }
        return prefixSum[A.length];
        
    }
}