class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        // first create a adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // now store edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v); // u ----> v
            adj[v].add(u); // v ----> u
        }

        // now find if graph is cycle or not if yes find the min length of the cycle and
        // return
        int minCycle = Integer.MAX_VALUE; // first store the length of cycle by rndom value;
        for (int i = 0; i < n; i++) {
            // now make the dist array to keep the track of the dist from the starting node
            // to each node
            int[] dist = new int[n];
            Arrays.fill(dist, -1); // store the array with -1 value as we don't the starting node for now;
            // now make a queue to traverse throuch each node
            Queue<Integer> q = new LinkedList<>();
            q.offer(i); // now we have stored our first node in the queue
            dist[i] = 0; // thus now the distance of first node for itself will be 0 for exp (1 --> 1) dist will be 0.
            // now start while loop till our queue gets empty
            while (!q.isEmpty()) {
                int u = q.poll(); // the variable u(source node)
                // variable v (dest_node)
                for (int v : adj[u]) {
                    // if we found a new node and its not visited before mark it as visited by
                    // updating our current node (v) by the dist of prev node (u) + 1 and add it to
                    // the queue
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q.offer(v);
                    }
                    // or if the current node is visited and its not equal to curr_index and the
                    // dist[u] (curr_node) is greater or equal to dist[u] (prev_node) + 1
                    // it neans we have found our cycle and store the value in minCycle
                    else if (v != i && dist[v] >= dist[u]) {
                        minCycle = Math.min(minCycle, dist[u] + dist[v] + 1);
                    }
                }
            }

        }
        // if not found the cycle return -1
        if (minCycle == Integer.MAX_VALUE)
            return -1;

        // else return the final min_length of cycle
        return minCycle;
    }
}
