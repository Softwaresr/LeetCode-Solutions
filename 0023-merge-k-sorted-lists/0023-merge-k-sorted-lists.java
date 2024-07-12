
// use a PriorityQueue
// O(n * k * log k) runtime, O(k) space, where n is the maximum number of nodes in each list, and k is the number of lists
public class Solution {
    public class LabeledListNode implements Comparable<LabeledListNode> {
        public ListNode listNode;
        public int label;
        public LabeledListNode(ListNode node, int n) {
            listNode = node;
            label = n;
        }
        @Override
        public int compareTo(LabeledListNode another) {
            return this.listNode.val - another.listNode.val;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<LabeledListNode> pq = new PriorityQueue<LabeledListNode>(lists.length);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(new LabeledListNode(lists[i], i));
                lists[i] = lists[i].next;
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            LabeledListNode node = pq.poll();
            cur.next = node.listNode;
            cur = cur.next;
            int n = node.label;
            if (lists[n] != null) {
                pq.offer(new LabeledListNode(lists[n], n));
                lists[n] = lists[n].next;
            }
        }
        return dummy.next;
    }
}