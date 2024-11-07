class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], 0}); // normal edge, weight 0
            graph.get(edge[1]).add(new int[]{edge[0], 1}); // reverse edge, weight 1    
        }
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = dfs1(0, -1, graph);
        dfs2(0, -1, graph, dp);
        return dp;
    }
    
    private int dfs1(int u, int fa, Map<Integer, List<int[]>> graph) {
        int res = 0;
        for (int[] next : graph.get(u)) {
            int v = next[0], w = next[1];
            if (v == fa) continue;
            
            res += dfs1(v, u, graph) + w;
        }
        return res;
    }
    
    private void dfs2(int u, int fa, Map<Integer, List<int[]>> graph, int[] dp) {
        for (int[] next : graph.get(u)) {
            int v = next[0], w = next[1];
            if (v == fa) continue;
            dp[v] = dp[u];
            if (w == 1) dp[v]--;
            else dp[v]++;
            dfs2(v, u, graph, dp);
        }
    }
}