class Solution {

    class SegmentTree { 
            // object takes arr size as defined in constructor
            // Change as per need
            // Use updater,builder,finder helpers

            int arr_size;
            long[] seg;
            public SegmentTree(int n){
                this.seg = new long[4*n]; // Safer side
                this.arr_size = n;
            }
            void build(long[] a,int vertex,int l,int r){ // vertex=1
                if(l==r) {
                    seg[vertex] = a[l];
                    return;
                }
                int mid= l + (r-l)/2;

                build(a,2*vertex,l,mid);
                build(a,2*vertex+1,mid+1,r);
                seg[vertex] = Math.max(seg[vertex<<1],seg[(vertex<<1)|1]);
            }

            long find(int vertex,int seg_l,int seg_r,int l,int r){ // query operations here
                if(seg_l==l && seg_r==r) return seg[vertex];
                if(l>r) return 0;
                int mid= seg_l + (seg_r-seg_l)/2;
                return Math.max(find(vertex<<1,seg_l,mid,l,Math.min(mid,r)),
                        find((vertex<<1)|1,mid+1,seg_r,Math.max(mid+1,l),r));
            }

            void update(int vertex,int pos,long value,int seg_l,int seg_r){
                if(seg_l==seg_r) seg[vertex] = value;  // put that val there
                else {
                    int mid=seg_l+(seg_r-seg_l)/2;
                    if(pos<=mid) update(vertex<<1,pos,value,seg_l,mid);
                    else update(vertex<<1|1,pos,value,mid+1,seg_r);

                    seg[vertex] = Math.max(seg[vertex<<1] , seg[(vertex<<1)|1]);
                }
            }
            void builder(long[] a){
                build(a,1,0,arr_size-1);
            }
            void updater(int pos,long value){
                update(1,pos,value,0,arr_size-1);
            }
            long finder(int l,int r){
                return find(1,0,arr_size-1,l,r);
            }
    }

    int binPos(long[] a,long val){
        int l=0,r=a.length-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(a[mid] <= val) l=mid+1;
            else r=mid-1;
        }
        return l-1;
    }

    public long maxBalancedSubsequenceSum(int[] nums) {
        
        // nums[i]-num[j] >= i-j   <--> nums[i]-i >= nums[j]-j
        // nums[i] range to (-1e9 ,1e9) so first traverse whole array to 
        // get position each posible nums[i]-i values.
        // skip negatives

        int nct=0,nmax=Integer.MIN_VALUE;
        TreeSet<Long> ts=new TreeSet<>();
        int i,n=nums.length;
        for(i=0;i<n;i++){
            if(nums[i] < 0) {
                nct++;
                nmax = Math.max(nmax, nums[i]);
                continue;
            }
            ts.add((long)nums[i]-i);
        }
        if(nct == n) return nmax;
        long[] map = new long[ts.size()]; // nums[i]-i mapped on index
        i=0;
        for(long vv:ts) map[i++] = vv;
        long[] dp=new long[ts.size()];
        Arrays.fill(dp,Long.MIN_VALUE);
        SegmentTree sg = new SegmentTree(ts.size());
        sg.builder(dp);
        for(i=0;i<nums.length;i++){
            if(nums[i] < 0) continue;
            int pos = binPos(map,(long)nums[i]-i);
            long maxVal = sg.finder(0,pos);
            if(maxVal == Long.MIN_VALUE){
                if(dp[pos] < nums[i]){
                    dp[pos] = nums[i];
                    sg.updater(pos, nums[i]);
                }
            }else{
                if(nums[i]+maxVal > dp[pos]){
                    dp[pos] = nums[i] + maxVal;
                    sg.updater(pos, nums[i]+maxVal);
                }
            }
        }
        
        long max = Long.MIN_VALUE;
        for(long vv : dp){
            if(vv==Long.MIN_VALUE) continue;
            max  =Math.max(max,vv);
        }
        return max;
    }
}