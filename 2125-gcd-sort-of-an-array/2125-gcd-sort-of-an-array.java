class Solution {
    
    public boolean gcdSort(int[] nums) {
        for(int x: nums) {
            int y = x;
            for(int p=2; UF[y]==0 && p*p<=y; ++p) {
                if (y % p == 0) {
                    union(x, p);
                    while(y % p == 0) y /= p;
                }
            }
            if (y!=1) union(x, y);
        }
        int[] copy = nums.clone();
        Arrays.sort(copy);
        for(int i=0; i<nums.length; i++) {
            if (find(nums[i])!=find(copy[i])) return false;
        }
        return true;
    }
    
    private int[] UF = new int[100001];
    
    private int find(int x) {
        if (UF[x]==0) return UF[x]=x;
        return UF[x]==x? x: (UF[x]=find(UF[x]));
    }
    
    private void union(int x, int y) {
        UF[find(x)] = UF[find(y)];
    }
}