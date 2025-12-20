class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int mid = n / 2;
        List<Integer> leftSums = new ArrayList<>();
        List<Integer> rightSums = new ArrayList<>();
        generateSums(nums, 0, mid, 0, leftSums);
        generateSums(nums, mid, n, 0, rightSums);
        Collections.sort(rightSums);
        int minDiff = Integer.MAX_VALUE;
        for(int s : leftSums){
            int needed = goal - s;
            int idx = Collections.binarySearch(rightSums,needed);
            if(idx >= 0){
                return 0;

            }
            else{
                int insertionPoint = -(idx + 1);
                if(insertionPoint < rightSums.size()){
                    int sum = s +rightSums.get(insertionPoint);
                    minDiff = Math.min(minDiff, Math.abs(sum - goal));

                }
                if(insertionPoint > 0){
                    int sum = s + rightSums.get(insertionPoint - 1);
                    minDiff = Math.min(minDiff, Math.abs(sum - goal));

                }
            }
        }
        return minDiff;

        
    }
    private void generateSums(int[] nums, int index, int end, int currentSums, List<Integer> sums){
        if(index == end){
            sums.add(currentSums);
            return;
        }

        generateSums(nums, index + 1, end, currentSums + nums[index], sums);
        generateSums(nums, index + 1, end, currentSums, sums);

    }
}