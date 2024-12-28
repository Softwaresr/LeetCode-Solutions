import java.util.*;
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;  // If the tree is empty, return depth 0

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;  // Start with depth 1 as we are at the root
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // If it's a leaf node, return the current depth
                if (node.left == null && node.right == null) {
                    return depth;
                }
                
                // Add left and right children to the queue, if they exist
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        
        return 0;  // This line will never be reached if the tree is valid
    }
}