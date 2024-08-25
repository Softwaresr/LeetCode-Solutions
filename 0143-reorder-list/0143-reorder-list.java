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
    ListNode reord(ListNode slow, ListNode fast) {
        ListNode tail = null;
        
        if (fast.next == null)  {   // odd number of nodes
            tail = slow.next;
            slow.next = null;
            return tail;
        }
        else if (fast.next.next == null) {  // even number of nodes
            tail = slow.next.next;
            slow.next.next = null;
            return tail;
        }
        
        tail = reord(slow.next, fast.next.next);
        
        ListNode tail2 = tail.next;
        tail.next = slow.next;
        slow.next = tail;
        
        return tail2;        
    }
    
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        
        reord(head, head);
    }
}