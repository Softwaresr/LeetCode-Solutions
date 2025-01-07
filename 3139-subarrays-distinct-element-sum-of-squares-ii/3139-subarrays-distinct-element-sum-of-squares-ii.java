class Solution {
    class SegmentTree {
        SegmentTree left;
        SegmentTree right;
        int leftVal;
        int rightVal;
        long sum;
        long add;
    }
    int MOD = 1_000_000_007;
    SegmentTree root;

    private void buildTree(int n) {
        root = new SegmentTree();
        root.left = null;
        root.right = null;
        root.leftVal = 0;
        root.rightVal = n;
        root.sum = 0;
        root.add = 0;
    }

    private void update(int from, int to, SegmentTree me) {
        if (to < from) return;
        me.sum += to - from + 1;
        if (me.leftVal == from && me.rightVal == to) {
            me.add++;
        } else {
            int mid = me.leftVal + (me.rightVal - me.leftVal) / 2;
            if (me.left == null) {
                me.left = new SegmentTree();
                me.left.leftVal = me.leftVal;
                me.left.rightVal = mid;
                me.left.sum = (me.sum / (me.rightVal - me.leftVal + 1)) * (mid - me.leftVal + 1);
                me.left.add = 0;

                me.right = new SegmentTree();
                me.right.leftVal = mid + 1;
                me.right.rightVal = me.rightVal;
                me.right.sum = (me.sum / (me.rightVal - me.leftVal + 1)) * (me.rightVal - (mid + 1) + 1);
                me.right.add = 0;
            }
            update(from, Math.min(to, mid), me.left);
            update(Math.max(from, mid + 1), to, me.right);
        }
    }

    private long query(int from, int to, SegmentTree me) {
        if (to < from) return 0;
        if (me.leftVal ==  me.rightVal) return me.sum;
        if (me.leftVal == from && me.rightVal == to) {
            return me.sum;
        } else {
            if (me.left == null) {
                return me.add * (to - from + 1); 
            } else {
                int mid = me.leftVal + (me.rightVal - me.leftVal) / 2;
                return query(from, Math.min(mid, to), me.left) + query(Math.max(mid + 1, from), to, me.right) + me.add * (to - from + 1);
            }
        }
    }

    public int sumCounts(int[] nums) {
        /* 
        case1
        987654
        543210 -> 654321
        sum1 = 55
        sum2 = sum1 + updateLen + 2 *(query) = 55 + 6 + 2 *(15) = 91 
        */  
        /* 
        case2
        98776654
        54332210 -> 65443321
        sum2 = sum1 + updateLen + 2 *(query) = sum1  + 8 + 2 *(20) 
        */  
        /* 
        case3
        98776657, 6th -> 7th 
        5433,2210 -> 5433,3321
        sum2 = sum1 + updateLen + 2 *(query) = sum1 + 4 + 2 *(5) 
        */    
        int n = nums.length;
        buildTree(nums.length - 1);
        
        Map<Integer, Integer> lastSeenAt = new HashMap();
        lastSeenAt.put(nums[0], 0);
        long ans = 1l, rsf = 1l;
        update(0, 0, root);

        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i], lastID = -1;
            if (lastSeenAt.containsKey(num)) lastID = lastSeenAt.get(num);
            
            long temp = query(lastID + 1, i - 1, root);
            rsf = (rsf + i - lastID + temp * 2) % MOD;
            ans = (ans + rsf) % MOD;

            update(lastID + 1, i, root);
            lastSeenAt.put(num, i);
        }
        return (int)ans;
    }
}