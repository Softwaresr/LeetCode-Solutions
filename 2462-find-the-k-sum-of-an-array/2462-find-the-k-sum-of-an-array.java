class Solution {
    public long kSum(int[] nums, int k) {
        long sum=0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0)  sum+=nums[i];
            else    nums[i]=-nums[i];
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long,Integer>> pq=new PriorityQueue<>((a,b)->Long.compare(b.getKey(),a.getKey()));
        pq.offer(new Pair<>(sum,0));
        while(k-->1){
            Pair<Long, Integer> top = pq.poll();
            long s=top.getKey();
            int i=top.getValue();
            if(i<nums.length){
                pq.offer(new Pair<>(s-nums[i],i+1));
                if(i>0) pq.offer(new Pair<>(s-nums[i]+nums[i-1],i+1));
            }
        }
        return pq.peek().getKey();
    }
}