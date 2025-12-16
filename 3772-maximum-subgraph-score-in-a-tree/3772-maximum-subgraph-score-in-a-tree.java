import java.util.*;

class Solution {
    List<Integer>[] g;
    int[] val, down, ans;

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        val = new int[n];
        for (int i = 0; i < n; i++) val[i] = good[i] == 1 ? 1 : -1;

        down = new int[n];
        ans = new int[n];

        dfs1(0, -1);
        dfs2(0, -1, 0);

        return ans;
    }

    void dfs1(int u, int p) {
        down[u] = val[u];
        for (int v : g[u]) {
            if (v == p) continue;
            dfs1(v, u);
            down[u] += Math.max(0, down[v]);
        }
    }

    void dfs2(int u, int p, int up) {
        ans[u] = down[u] + Math.max(0, up);

        for (int v : g[u]) {
            if (v == p) continue;
            int nextUp = ans[u] - Math.max(0, down[v]);
            dfs2(v, u, nextUp);
        }
    }
}
