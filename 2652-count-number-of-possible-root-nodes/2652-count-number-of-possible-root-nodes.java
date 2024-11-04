class Solution {

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        
        List<Integer>[] graph = new ArrayList[n];
        Set<Integer>[] guessesGraph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            guessesGraph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        for (int[] guess : guesses) {
            guessesGraph[guess[0]].add(guess[1]);
        }

        int res = 0;
        Map<Long, Integer> memo = new HashMap<>();
        for (int i = 0; i<n; i++) {
            if (dfs(graph, guessesGraph, memo, i, -1) >= k) {
                res++;
            }
        }
        return res;
    }
    
    
    int dfs(List<Integer>[] graph, Set<Integer>[] guessesGraph, Map<Long, Integer> memo, int current, int prev) {
        long key = (long)current * 1000000 + prev;
        if (memo.containsKey(key)) return memo.get(key);
        int count = prev != -1 && guessesGraph[prev].contains(current) ? 1 : 0;
        for (int next : graph[current]) {
            if (next != prev) {
                count += dfs(graph, guessesGraph, memo, next, current);
            }
        }
        memo.put(key, count);
        return count;
    }
    
}