/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        
        // Determine the maximum frequency of sums
        int maxFrequency = 0;
        for (int freq : map.values()) {
            maxFrequency = Math.max(maxFrequency, freq);
        }
        
        // Collect sums with the maximum frequency
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                result.add(entry.getKey());
            }
        }
        
        // Convert result list to array
        int[] resArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArray[i] = result.get(i);
        }
        
        return resArray;
    }

    int helper(TreeNode node, HashMap<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left, map);
        int right = helper(node.right, map);
        
        int sum = left + node.val + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        return sum;
    }
}