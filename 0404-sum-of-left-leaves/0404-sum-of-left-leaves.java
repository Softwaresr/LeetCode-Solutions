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
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int sum =0;
        Queue<Pair<TreeNode,Boolean>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root,false));

        while(!queue.isEmpty()){
            Pair<TreeNode,Boolean> pair = queue.poll();
            TreeNode node = pair.getKey();
            boolean left = pair.getValue();
            if(left && node.left== null && node.right == null) sum+=node.val;
            if(node.left != null){
                queue.offer(new Pair<>(node.left,true));
            }
             if(node.right != null){
                queue.offer(new Pair<>(node.right,false));
            }
        }
        return sum;
    }
}