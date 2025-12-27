class Solution {
    public int mostBooked(int n, int[][] m) {
        Arrays.sort(m, (a, b) -> Integer.compare(a[0], b[0]));
        int[] c = new int[n];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < n; i++) q.offer(i);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
        for(int[] v : m){
            int s = v[0];
            while(!pq.isEmpty() && pq.peek()[0] <= s) q.offer((int)pq.poll()[1]);
            int r;
            long t;
            if(!q.isEmpty()){
                r=q.poll();
                t=v[1];

            }
            else{
                long[] top = pq.poll();
                t=top[0] + (v[1] - s);
                r = (int)top[1];
            }
            c[r]++;
            pq.offer(new long[]{t, r});
        }
        int ans=  0;
        for(int i = 0; i < n; i++){
            if(c[i] > c[ans]) ans = i;
        }
        return ans;
            
        
        
    }
    
}