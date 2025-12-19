class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = 1_000_000_007;
        int[] bitCount = new int[31];

        for(int num : nums){
            for(int i = 0; i < 31; i++){
                if(((num >> i) & 1) == 1){
                    bitCount[i]++;

                }
            }
        }
        long totalSumOfSquares = 0;
        for(int i = 0; i < k; i++){
            long currentNum = 0;
            for(int bit = 0; bit < 31; bit++){
                if(bitCount[bit] > 0){
                    currentNum |=(1 << bit);
                    bitCount[bit]--;

                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum)) % mod;

        }
        return (int) totalSumOfSquares;
        
        
        
    }
}