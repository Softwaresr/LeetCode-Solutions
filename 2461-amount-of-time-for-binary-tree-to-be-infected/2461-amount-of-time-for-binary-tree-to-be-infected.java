public class Solution {
    private int maxi = Integer.MIN_VALUE;

    public int amountOfTime(TreeNode root, int start) {
        calculateInfectionTime(root, start, true);
        return maxi;
    }

    private int calculateInfectionTime(TreeNode root, int start, boolean isStartNode) {
        if (root == null) return 0;

        if (root.val == start && isStartNode) {
            maxi = Math.max(maxi, calculateInfectionTime(root, start, false) - 1);
            return -1;
        }

        int leftHeight = calculateInfectionTime(root.left, start, isStartNode);
        int rightHeight = calculateInfectionTime(root.right, start, isStartNode);

        if (leftHeight < 0 || rightHeight < 0) {
            maxi = Math.max(maxi, Math.abs(leftHeight) + Math.abs(rightHeight));
            return Math.min(leftHeight, rightHeight) - 1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}