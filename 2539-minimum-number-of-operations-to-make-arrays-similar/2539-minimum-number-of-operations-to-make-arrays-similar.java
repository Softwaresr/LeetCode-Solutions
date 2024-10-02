//O(n*log(n)) time and O(n*log(n)) space
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        /*
            Split odd and even numbers into separate PriorityQueues because buy addding/removing 2 we will never change remainder
        */
        PriorityQueue<Integer> pqNumsOdd = new PriorityQueue<>();
        PriorityQueue<Integer> pqNumsEven = new PriorityQueue<>();
        
        PriorityQueue<Integer> pqTargetOdd = new PriorityQueue<>();
        PriorityQueue<Integer> pqTargetEven = new PriorityQueue<>();

        for(int num : nums) {
            if(num % 2 == 0) {
                pqNumsEven.add(num);
            } else {
                pqNumsOdd.add(num);
            }
        }
        
        for(int tar : target) {
            if(tar % 2 == 0) {
                pqTargetEven.add(tar);
            } else {
                pqTargetOdd.add(tar);
            }            
        }
        
        long[] evens = helper(pqNumsEven, pqTargetEven, 0L, 0L);
        //pass number of adds and subs that we used in calculation of even numbers
        long[] odds = helper(pqNumsOdd, pqTargetOdd, evens[1], evens[2]);
        
        return evens[0] + odds[0];
    }
    
    private long[] helper(PriorityQueue<Integer> pqNums, PriorityQueue<Integer> pqTarget, long add, long sub) {
        //greedy solution
        long out = 0;
        
        while(!pqNums.isEmpty()) {
            //get smallest numbers from nums and target and see if we can match them
            int num = pqNums.poll();
            int target = pqTarget.poll();
            
            if(num < target) {
                //calculate number of operations we need to use to make numbers similar and apply free adds we gained by previously using subs
                long diff = (target - num) / 2 - add;
                
                if(diff > 0) {
                    //how many operations we need to use
                    out += diff;
                    //no free adds
                    add = 0;
                    //add free subs we can use later
                    sub += diff;
                } else {
                    //still have some adds to use in the future (or 0)
                    add = -diff;
                }
            } else {
                long diff = (num - target) / 2 - sub;
                
                if(diff > 0) {
                    out += diff;
                    sub = 0;
                    add += diff;
                }
            }
        }
        
        return new long[] {out, add, sub};
    }
}