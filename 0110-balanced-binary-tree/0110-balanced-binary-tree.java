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
    int heightCheck(TreeNode root){
        if(root == null) return 0;
        int left = heightCheck(root.left);
        int right = heightCheck(root.right);
        if(left == -1 || right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);

    }
    public boolean isBalanced(TreeNode root) {
        int ans = heightCheck(root);
        return ans == -1 ? false : true;

        
    }
}