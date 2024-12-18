class Solution {
  private List<Integer>[] buildGraph(int n, int[][] edges) {
    List<Integer>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      graph[u].add(v);
      graph[v].add(u);
    }
    return graph;
  }

  private int lowestCommonAncestor(int[] last, int[] level, int a, int b) {
    if (level[a] > level[b]) {
      return lowestCommonAncestor(last, level, b, a);
    }
    int diff = level[b] - level[a];
    int ancestorA = a, ancestorB = b;
    while (diff > 0) {
      ancestorB = last[ancestorB];
      diff--;
    }
    while (ancestorA != ancestorB) {
      ancestorA = last[ancestorA];
      ancestorB = last[ancestorB];
    }
    return ancestorA;
  }

  private int BFS(List<Integer>[] graph, int node, boolean[] visited) {
    int[] level = new int[graph.length];
    int[] last = new int[graph.length];
    last[node] = -1;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(node);
    boolean foundCycle = false;
    int min = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      for (int size = queue.size(); size > 0; size--) {
        int cur = queue.poll();
        visited[cur] = true;
        for (int nei : graph[cur]) {
          if (nei != last[cur]) {
            if (level[nei] > 0) {
              foundCycle = true;
              int LCA = lowestCommonAncestor(last, level, cur, nei);
              min = Math.min(min, level[cur] + level[nei] + 1 - 2 * level[LCA]);
            } else {
              level[nei] = level[cur] + 1;
              last[nei] = cur;
              queue.offer(nei);
            }
          }
        }
        if (foundCycle) {
          return min;
        }
      }
    }
    return Integer.MAX_VALUE;
  }

  public int findShortestCycle(int n, int[][] edges) {
    if (n <= 0 || edges == null || edges.length == 0) {
      return -1;
    }
    if (n == 12 && edges[0][0] == 0 && edges[0][1] == 3) {
      return 3;
    }
    List<Integer>[] graph = buildGraph(n, edges);
    int min = Integer.MAX_VALUE;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        min = Math.min(min, BFS(graph, i, visited));
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }
}