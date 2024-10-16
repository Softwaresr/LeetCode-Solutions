/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
     return sortList(head,null);
    }
        private ListNode sortList(ListNode start, ListNode end) {
        if (start == null || start.next == null || start == end) return start;

        ListNode left = start, right = start, current = start.next;
        boolean isSorted = true;

        while (current != null && current != end) {
            ListNode temp = current.next;
            if (current.val < start.val) {
                // Insert current node at the beginning of left partition
                current.next = left;
                left = current;
                right.next = temp;
                isSorted = false;
            } else {
                if (current.val < right.val) isSorted = false;
                right = current;
            }
            current = temp;
        }

        if (isSorted) return left;

        // Recursively sort the left and right partitions
        left = sortList(left, start);
        start.next = sortList(start.next, end);

        return left;
        }

}