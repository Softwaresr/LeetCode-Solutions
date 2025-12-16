import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] scores;
    int[] down;
    int[] result;

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // Convert good/bad to 1/-1
        scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = (good[i] == 1) ? 1 : -1;
        }

        down = new int[n];
        result = new int[n];

        // Step 1: Calculate max scores looking down the tree
        dfsDown(0, -1);
        
        // Step 2: Calculate max scores including upward paths (re-rooting)
        dfsUp(0, -1, 0);

        return result;
    }

    private void dfsDown(int u, int p) {
        down[u] = scores[u];
        for (int v : adj[u]) {
            if (v == p) continue;
            dfsDown(v, u);
            // Only add the child's subtree score if it's positive
            if (down[v] > 0) {
                down[u] += down[v];
            }
        }
    }

    private void dfsUp(int u, int p, int upContribution) {
        // The max score for node u is its downward max + the max from its parent
        result[u] = down[u] + Math.max(0, upContribution);

        for (int v : adj[u]) {
            if (v == p) continue;
            
            // To find the 'up' value for child v:
            // Take the total result of u, but subtract v's contribution if it was used.
            int scoreWithoutV = result[u] - Math.max(0, down[v]);
            
            dfsUp(v, u, scoreWithoutV);
        }
    }
}