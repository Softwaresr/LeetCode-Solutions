class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i = 0; i<n; i++){
            if(color[i] == -1 && !dfs(i, 0, color, graph)){
                return false;

            }
        }
        return true;

        
    }

    private boolean dfs(int node, int c, int[] color, int[][] graph){
        color[node] = c;
        for(int neighbor : graph[node]){
            if(color[neighbor] == -1){
                if(!dfs(neighbor, 1 - c, color, graph)) return false;

            }
            else if (color[neighbor] == color[node]){
                return false;

            }
        }
        return true;
    }
}