class Solution {
    static final int NEG_INF = -1_000_000_000;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        // 1-based -> 0-based adjust
        int[] buy = present;
        int[] sell = future;

        // build tree
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : hierarchy) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u].add(v);
        }

        // dp0[u][b], dp1[u][b]
        int[][] dp0 = new int[n][budget + 1];
        int[][] dp1 = new int[n][budget + 1];

        dfs(0, g, buy, sell, budget, dp0, dp1);
        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            ans = Math.max(ans, dp0[0][c]);
        }
        return ans;
    }

    private void dfs(int u, List<Integer>[] g, int[] buy, int[] sell, int B,
                     int[][] dp0, int[][] dp1) {

        // temp arrays for children aggregation when u not taken or taken
        int[] base0 = new int[B + 1];
        int[] base1 = new int[B + 1];
        // initialize: no children processed yet, cost 0 -> profit 0
        for (int b = 0; b <= B; b++) {
            base0[b] = (b == 0) ? 0 : NEG_INF;
            base1[b] = (b == 0) ? 0 : NEG_INF;
        }

        // merge each child
        for (int v : g[u]) {
            dfs(v, g, buy, sell, B, dp0, dp1);

            int[] newBase0 = new int[B + 1];
            int[] newBase1 = new int[B + 1];
            for (int b = 0; b <= B; b++) {
                newBase0[b] = NEG_INF;
                newBase1[b] = NEG_INF;
            }

            // when u not taken => child parent-not-bought => use dp0[v]
            for (int c1 = 0; c1 <= B; c1++) {
                if (base0[c1] == NEG_INF) continue;
                for (int c2 = 0; c2 + c1 <= B; c2++) {
                    if (dp0[v][c2] == NEG_INF) continue;
                    newBase0[c1 + c2] = Math.max(newBase0[c1 + c2],
                                                 base0[c1] + dp0[v][c2]);
                }
            }
            // when u taken => child parent-bought => use dp1[v]
            for (int c1 = 0; c1 <= B; c1++) {
                if (base1[c1] == NEG_INF) continue;
                for (int c2 = 0; c2 + c1 <= B; c2++) {
                    if (dp1[v][c2] == NEG_INF) continue;
                    newBase1[c1 + c2] = Math.max(newBase1[c1 + c2],
                                                 base1[c1] + dp1[v][c2]);
                }
            }

            base0 = newBase0;
            base1 = newBase1;
        }

        // now decide for u itself

        // cost/profit if we buy u with parent not bought (full price)
        int costFull = buy[u];
        int profFull = sell[u] - buy[u];

        // cost/profit if we buy u with parent bought (discounted)
        int costDisc = buy[u] / 2;
        int profDisc = sell[u] - costDisc;

        // Case: parent not bought -> dp0[u]
        for (int b = 0; b <= B; b++) {
            dp0[u][b] = NEG_INF;
        }
        // option 1: skip u, children use base0
        for (int b = 0; b <= B; b++) {
            dp0[u][b] = Math.max(dp0[u][b], base0[b]);
        }
        // option 2: take u at full cost, then children state is base1
        for (int b = costFull; b <= B; b++) {
            if (base1[b - costFull] == NEG_INF) continue;
            dp0[u][b] = Math.max(dp0[u][b],
                                 base1[b - costFull] + profFull);
        }

        // Case: parent bought -> dp1[u]
        for (int b = 0; b <= B; b++) {
            dp1[u][b] = NEG_INF;
        }
        // option 1: skip u, children parent-not-bought => base0
        for (int b = 0; b <= B; b++) {
            dp1[u][b] = Math.max(dp1[u][b], base0[b]);
        }
        // option 2: take u at discounted cost, children parent-bought => base1
        for (int b = costDisc; b <= B; b++) {
            if (base1[b - costDisc] == NEG_INF) continue;
            dp1[u][b] = Math.max(dp1[u][b],
                                 base1[b - costDisc] + profDisc);
        }
    }
}
