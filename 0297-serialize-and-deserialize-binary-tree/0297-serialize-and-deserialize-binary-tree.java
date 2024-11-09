import java.util.StringJoiner;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(final TreeNode root) {
        if(root == null)
            return "";

        final Queue<TreeNode> queue = new LinkedList<>();
        final StringJoiner sj = new StringJoiner(",");
        
        queue.offer(root);

        while(!queue.isEmpty()) {
            final TreeNode curr = queue.poll();

            if(curr == null) {
                sj.add("#");
            } else {
                sj.add(curr.val + "");

                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        return sj.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(final String data) {
        if(data.equals(""))
            return null;

        final String[] values = data.split(",");

        final Queue<TreeNode> queue = new LinkedList<>();
        final TreeNode root = new TreeNode(Integer.valueOf(values[0]));

        queue.offer(root);

        int i = 1;

        while(i < values.length) {
            final TreeNode parent = queue.poll();

            if(values[i].charAt(0) != '#') {
                final TreeNode node = new TreeNode(Integer.valueOf(values[i]));
                parent.left = node;
                queue.offer(node);
            }

            ++i;

            if(values[i].charAt(0) != '#') {
                final TreeNode node = new TreeNode(Integer.valueOf(values[i]));
                parent.right = node;
                queue.offer(node);
            }

            ++i;
        }

        return root;
    }
}

