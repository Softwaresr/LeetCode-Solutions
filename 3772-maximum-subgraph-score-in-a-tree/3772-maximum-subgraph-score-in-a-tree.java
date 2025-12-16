import java.util.*;

class Solution {
    int n;
    List<Integer>[] g;
    int[] w;
    int[] down;
    int[] up;

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        this.n = n;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        // convert good[] -> weight: +1 for good, -1 for bad
        w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = (good[i] == 1 ? 1 : -1);
        }

        down = new int[n];
        up = new int[n];

        // root the tree at 0 (any node works)
        dfsDown(0, -1);
        dfsUp(0, -1);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = down[i] + up[i];
        }
        return ans;
    }

    // First DFS: compute down[u]
    private void dfsDown(int u, int parent) {
        int sum = w[u];
        for (int v : g[u]) {
            if (v == parent) continue;
            dfsDown(v, u);
            if (down[v] > 0) {
                sum += down[v];
            }
        }
        down[u] = sum;
    }

    // Second DFS: compute up[v] for children using up[u] and siblings
    private void dfsUp(int u, int parent) {
        // precompute total positive contribution from all children of u
        int totalPosChildren = 0;
        for (int v : g[u]) {
            if (v == parent) continue;
            if (down[v] > 0) totalPosChildren += down[v];
        }

        for (int v : g[u]) {
            if (v == parent) continue;
            // remove v's contribution from total children sum for u
            int withoutV = totalPosChildren;
            if (down[v] > 0) withoutV -= down[v];

            // parent-side contribution as if u is the root of that side
            int candidate = w[u];
            candidate += up[u];       // contribution from above u
            candidate += withoutV;    // positive siblings of v

            // only keep positive contributions; otherwise 0
            up[v] = Math.max(0, candidate);

            dfsUp(v, u);
        }
    }
}
