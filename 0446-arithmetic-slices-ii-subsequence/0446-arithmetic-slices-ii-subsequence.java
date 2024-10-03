class Solution {
  public int numberOfArithmeticSlices(int[] nums) {
    int n = nums.length;
    Map<Integer,List<Integer>> nextMap = new HashMap();
    for(int i=0;i<n;i++){
      List<Integer> nexts = nextMap.get(nums[i]);
      if(nexts==null){
        nexts = new ArrayList<>();
        nextMap.put(nums[i],nexts);
      }
      nexts.add(i);
    }   

    int[][] dp = new int[n][n];
    int res = 0;
    for(int i=1;i<n;i++){
      for(int j=i+1;j<n;j++){
        long diff = (long)nums[j]-(long)nums[i];
        // if(diff>Integer.MAX_VALUE||diff<Integer.MIN_VALUE){
        //   continue;
        // }
        long next = 2L*nums[i]-nums[j];
        if(next>Integer.MAX_VALUE||next<Integer.MIN_VALUE){
          continue;
        }
        List<Integer> nexts = nextMap.get((int)next);
        if(nexts==null){
          continue;
        }
        int p1 = 0;
        int p2 = nexts.size()-1;
        int p = -1;
        while(p1<=p2){
          int mid = (p1+p2)/2;
          if(nexts.get(mid)<i){
            p = mid;
            p1 = mid+1;
          }else{
            p2 = mid-1;
          }
        }
        for(int k=0;k<=p;k++){
          dp[i][j]+=dp[nexts.get(k)][i]+1;
          // System.out.println(k+","+i+","+j+" add:");
        }
        res+=dp[i][j];
      }
    }
    // for(int i=0;i<dp.length;i++){
    //   System.out.println(i+" "+Arrays.toString(dp[i]));
    // }
    return res;
  }
}