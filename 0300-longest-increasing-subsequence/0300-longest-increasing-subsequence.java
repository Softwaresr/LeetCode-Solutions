class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int n: nums){
            if(res.isEmpty()||res.get(res.size()-1)<n){
                res.add(n);
            }
            else{
                int id = binary(res,n);
                res.set(id,n);

            }
            
        }return res.size();
    }

    private int binary(List<Integer> res,int n){
        int left=0;
        int right=res.size()-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(res.get(mid)==n){
                return mid;

            }else if(res.get(mid)>n){
                right=mid-1;
            }
            else {
                left=mid+1;
            }
            

            
        }
        return left;
    }
}