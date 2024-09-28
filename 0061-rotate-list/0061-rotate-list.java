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
    public ListNode rotateRight(ListNode head, int k) {
        ListNode m = head;
        int count = 0;
        while(m != null)
        {
            count++;
            m = m.next;
        }
        if(count == 1 || count == 0) return head;
        
        int x = k%count;
        ListNode n = head;

        while(x != 0 )
        {
            if(n.next.next == null)
            {
                ListNode prev = n.next;
                n.next = null;
                prev.next = head;
                head = prev;  
                n = head;
                x--;
            }
            else n = n.next;
        }
        return head;
    }
}