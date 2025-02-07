class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ball = new HashMap<>(), color = new HashMap<>();
        int n = queries.length;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int pos = queries[i][0], c = queries[i][1];
            
            // If there is already a ball at the position, decrease the count of its color
            if (ball.containsKey(pos)) {
                int oldColor = ball.get(pos);
                int oldCount = color.get(oldColor) - 1;
                if (oldCount == 0) {
                    color.remove(oldColor);
                } else {
                    color.put(oldColor, oldCount);
                }
            }
            
            // Place the new ball and increase the count of its color
            ball.put(pos, c);
            color.put(c, color.getOrDefault(c, 0) + 1);
            
            // The number of distinct colors is just the size of the color map
            ans[i] = color.size();
        }
        
        return ans;
    }
}
